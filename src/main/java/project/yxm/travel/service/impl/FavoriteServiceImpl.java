package project.yxm.travel.service.impl;

import project.yxm.travel.dao.FavoriteDao;
import project.yxm.travel.dao.impl.FavoriteDaoImpl;
import project.yxm.travel.domain.Favorite;
import project.yxm.travel.service.FavorateService;

public class FavoriteServiceImpl implements FavorateService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorate(int rid,int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(rid, uid);
        //如果对象有值,则为true,反之为false
        return favorite != null;
    }
}
