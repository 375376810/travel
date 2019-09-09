package project.yxm.travel.dao;

import project.yxm.travel.domain.User;

public interface UserDao {

    /**
     * 根据用户名查询用户
     */
    public User findUserByName(String userName);

    /**
     * 保存用户
     */
    public void saveUser(User user);
}
