package project.yxm.travel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import project.yxm.travel.dao.CategoryDao;
import project.yxm.travel.dao.impl.CategoryDaoImpl;
import project.yxm.travel.domain.Category;
import project.yxm.travel.service.CategoryService;
import project.yxm.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        //1.从redis中查询
        //1.1获取Jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2使用sortedset排序查询
        //Set<String> categorySet = jedis.zrange("category", 0, -1);
        //1.3查询sortedset中的分数(cid)和值(cname)
        Set<Tuple> categorySet = jedis.zrangeWithScores("category", 0, -1);
        List<Category> categoryList = null;
        //2判断查询的数据是否为空
        if (categorySet == null || categorySet.size() == 0) {
            //3.如果为空,需要从数据库查询,再将数据存入redis
            //3.1将集合数据存储到redis的category的key
            categoryList = categoryDao.findAll();
            //3.2将集合数据遍历并存储到redis中
            for (int i = 0; i < categoryList.size(); i++) {
                jedis.zadd("category", categoryList.get(i).getCid(), categoryList.get(i).getCname());
            }
        } else {
            //4.如果不为空,将set数据存入list
            categoryList = new ArrayList<Category>();
            for (Tuple tuple : categorySet) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                categoryList.add(category);
            }
        }
        return categoryList;
    }

}
