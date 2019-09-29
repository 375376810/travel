package project.yxm.travel.dao;

import project.yxm.travel.domain.Favorite;

public interface FavoriteDao {
    Favorite findByRidAndUid(int rid, int uid);

    /**
     * 查询收藏次数
     * @param rid
     * @return
     */
    int findCountByRid(int rid);

    /**
     * 添加收藏
     * @param uid
     * @param i
     */
    void add(int uid, int i);
}
