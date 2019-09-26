package project.yxm.travel.service.impl;

import java.util.List;

import project.yxm.travel.dao.RouteDao;
import project.yxm.travel.dao.RouteImgDao;
import project.yxm.travel.dao.SellerDao;
import project.yxm.travel.dao.impl.RouteDaoImpl;
import project.yxm.travel.dao.impl.RouteImgDaoImpl;
import project.yxm.travel.dao.impl.SellerDaoImpl;
import project.yxm.travel.domain.PageBean;
import project.yxm.travel.domain.Route;
import project.yxm.travel.domain.RouteImg;
import project.yxm.travel.domain.Seller;
import project.yxm.travel.service.Routeservice;

public class RouteServiceImpl implements Routeservice {

    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();

    /**
     * 分页查询
     *  @param cid 分类Id
     * @param pageSize 每页显示的条数
     * @param currentPage 当前页码
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int pageSize, int currentPage,String rname) {
        //封装PageBean
        PageBean<Route> page = new PageBean<Route>();
        //设置当前页码
        page.setCurrentPage(currentPage);
        //设置每页显示条数
        page.setPageSize(pageSize);
        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        page.setTotalCount(totalCount);
        //设置当前页应该显示的数据
        int start = (currentPage - 1)*pageSize;//开始的记录数
        List<Route> list = routeDao.findByPage(cid, start, pageSize,rname);
        page.setList(list);
        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount%pageSize == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        page.setTotalPage(totalPage);

        return page;
    }

    /**
     * 通过rid查询旅游路线详细信息
     *
     * @param rid
     * @return
     */
    @Override
    public Route findOne(String rid) {
        //1.根据id到route表中查询route对象
        Route route = routeDao.findOnne(Integer.parseInt(rid));
        //2.根据route的id查询图片集合信息
        List<RouteImg> list = routeImgDao.findByRid(route.getRid());
        //3.将list设置到route对象中
        route.setRouteImgList(list);
        //4.根据route的sid查询商家信息
        Seller seller = sellerDao.findBySid(route.getSid());
        route.setSeller(seller);
        return route;
    }

}
