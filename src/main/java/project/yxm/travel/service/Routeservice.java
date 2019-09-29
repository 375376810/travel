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
    PageBean<Route> pageQuery(int cid, int pageSize, int currentPage,String rname);

    /**
     * 通过rid查询旅游路线详细信息
     * @param rid
     * @return
     */
    Route findOne(String rid);

    /**
     * 添加收藏
     * @param uid
     * @param rid
     */
    void add(int uid, String rid);
}
