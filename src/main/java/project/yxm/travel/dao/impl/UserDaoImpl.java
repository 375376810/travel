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
            String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
            //2.执行sql
            jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(),user.getStatus(),user.getCode());
        } catch (Exception e) {
            System.out.println("发生了异常");
            e.printStackTrace();
        }
        System.out.println("保存?");
    }

    /**
     * 根据激活码查询用户
     * @param code
     **/
    @Override
    public User findByCode(String code) {
        User user = null;
        try {

            String sql = "select * from tab_user where code=?";
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 修改用户的激活状态
     * @param user
     **/
    @Override
    public void updateStatus(User user) {
        String sql = "update tab_user set status='Y' where uid = ?";
        jdbcTemplate.update(sql,user.getUid());
    }

    /**
     * 通过用户名和密码查询用户
     * @param name
     * @param password
     **/
    @Override
    public User findUserByNameAndPassword(String name, String password) {
        User user = null;
        String sql = "select * from tab_user where username = ? and password = ?";
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
