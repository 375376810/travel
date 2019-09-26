package project.yxm.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import project.yxm.travel.dao.SellerDao;
import project.yxm.travel.domain.Seller;
import project.yxm.travel.util.JDBCUtils;

public class SellerDaoImpl implements SellerDao {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据sid查找商家
     *
     * @param sid
     * @return
     */
    @Override
    public Seller findBySid(int sid) {
        String sql = "select * from tab_seller where sid = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class),sid);
    }


}
