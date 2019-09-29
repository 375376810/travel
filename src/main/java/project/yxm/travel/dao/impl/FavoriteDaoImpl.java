package project.yxm.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

import project.yxm.travel.dao.FavoriteDao;
import project.yxm.travel.domain.Favorite;
import project.yxm.travel.util.JDBCUtils;

public class FavoriteDaoImpl implements FavoriteDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid = ? and uid = ?";
            favorite = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class),rid,uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return favorite;
    }

    /**
     * 查询收藏次数
     *
     * @param rid
     * @return
     */
    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class,rid);
    }

    /**
     * 添加收藏
     *
     * @param uid
     * @param rid
     */
    @Override
    public void add(int uid, int rid) {
        String sql = "insert into tab_favorite values (?,?,?)";
        jdbcTemplate.update(sql,rid,new Date(),uid);
    }

}
