package project.yxm.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import project.yxm.travel.dao.UserDao;
import project.yxm.travel.domain.User;
import project.yxm.travel.util.JDBCUtils;

public class UserDaoImpl implements UserDao {

    //数据源连接池
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByName(String userName) {
        User user = null;
        try {
            //1.定义sql
            String sql = "select * from tab_user where username = ?";
            //2.执行sql
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userName);
        } catch (Exception e) {

        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        try {
            //1.定义sql
            String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email) values(?,?,?,?,?,?,?)";
            //2.执行sql
            jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail());
        } catch (Exception e) {
            System.out.println("发生了异常");
            e.printStackTrace();
        }
        System.out.println("保存?");
    }
}
