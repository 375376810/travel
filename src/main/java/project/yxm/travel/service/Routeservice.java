package project.yxm.travel.service;

import project.yxm.travel.domain.PageBean;
import project.yxm.travel.domain.Route;

public interface Routeservice {

    /**
     * 分页查询
     *  @param cid 分类Id
     * @param pageSize 每页显示的条数
     * @param currentPage 当前页码
     */
    PageBean<Route> pageQuery(int cid, int pageSize, int currentPage);
}
