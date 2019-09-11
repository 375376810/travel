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

    /** 根据激活码查询用户 **/
    User findByCode(String code);

    /** 修改用户的激活状态 **/
    void updateStatus(User user);
}
