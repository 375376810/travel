package project.yxm.travel.service.impl;

import java.util.List;

import project.yxm.travel.dao.RouteDao;
import project.yxm.travel.dao.impl.RouteDaoImpl;
import project.yxm.travel.domain.PageBean;
import project.yxm.travel.domain.Route;
import project.yxm.travel.service.Routeservice;

public class RouteServiceImpl implements Routeservice {

    private RouteDao routeDao = new RouteDaoImpl();

    /**
     * 分页查询
     *  @param cid 分类Id
     * @param pageSize 每页显示的条数
     * @param currentPage 当前页码
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int pageSize, int currentPage) {
        //封装PageBean
        PageBean<Route> page = new PageBean<Route>();
        //设置当前页码
        page.setCurrentPage(currentPage);
        //设置每页显示条数
        page.setPageSize(pageSize);
        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid);
        page.setTotalCount(totalCount);
        //设置当前页应该显示的数据
        int start = (currentPage - 1)*pageSize;//开始的记录数
        List<Route> list = routeDao.findByPage(cid, start, pageSize);
        page.setList(list);
        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount%pageSize == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        page.setTotalPage(totalPage);

        return page;
    }
}
