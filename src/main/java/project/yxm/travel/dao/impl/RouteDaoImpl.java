package project.yxm.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import project.yxm.travel.dao.RouteDao;
import project.yxm.travel.domain.Route;
import project.yxm.travel.util.JDBCUtils;

public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据cid查询总记录数
     * @param cid 分类id
     * @return 总记录数
     */
    @Override
    public int findTotalCount(int cid) {
        String sql = "select count(*) from tab_route where cid = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class,cid);
    }

    /**
     * 根据cid,start,pageSize查询当前页的数据集合
     * @param cid      分类id
     * @param start    起始
     * @param pageSize 每页显示的数目
     * @return
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize) {
        String sql = "select * from tab_route where cid = ? limit ? , ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Route>(Route.class),cid,start,pageSize);
    }

}
