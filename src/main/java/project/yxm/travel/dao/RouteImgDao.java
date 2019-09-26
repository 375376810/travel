package project.yxm.travel.dao;

import java.util.List;

import project.yxm.travel.domain.RouteImg;

public interface RouteImgDao {
    /**
     * 根据rid查询route的img集合
     * @param rid
     * @return
     */
    List<RouteImg> findByRid(int rid);
}
