package project.yxm.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import project.yxm.travel.dao.RouteDao;
import project.yxm.travel.domain.Route;
import project.yxm.travel.util.JDBCUtils;

public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据cid查询总记录数
     *
     * @param cid 分类id
     * @return 总记录数
     */
    @Override
    public int findTotalCount(int cid, String rname) {
        String sql = "select count(*) from tab_route where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (!StringUtils.isEmpty(rname)) {
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();
        return jdbcTemplate.queryForObject(sql, Integer.class, params.toArray());
    }

    /**
     * 根据cid,start,pageSize查询当前页的数据集合
     *
     * @param cid      分类id
     * @param start    起始
     * @param pageSize 每页显示的数目
     * @return
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        //String sql = "select * from tab_route where cid = ? and rname like ? limit ? , ?";
        String sql = "select * from  tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        ArrayList params = new ArrayList();
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (!StringUtils.isEmpty(rname)) {
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);
        sql = sb.toString();
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }

}
