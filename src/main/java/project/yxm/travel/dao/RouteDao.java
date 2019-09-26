package project.yxm.travel.dao;

import java.util.List;

import project.yxm.travel.domain.Route;

public interface RouteDao {

    /**
     * 根据cid查询总记录数
     * @param cid 分类id
     * @return 总记录数
     */
    int findTotalCount(int cid,String rname);

    /**
     * 根据cid,start,pageSize查询当前页的数据集合
     * @param cid 分类id
     * @param start 起始
     * @param pageSize 每页显示的数目
     * @return
     */
    List<Route> findByPage(int cid, int start, int pageSize, String rname);

    /**
     * 根据id查询route
     * @param rid
     * @return
     */
    Route findOnne(int rid);

}
