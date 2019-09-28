package project.yxm.travel.dao;

import project.yxm.travel.domain.Favorite;

public interface FavoriteDao {
    Favorite findByRidAndUid(int rid, int uid);
}
