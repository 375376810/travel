package project.yxm.travel.service;

import project.yxm.travel.domain.User;

public interface UserService {
    /** 保存新注册的用户信息 **/
    Boolean regist(User user);

    /** 根据用户名在数据库中查找用户 **/
    Boolean isUserExist(String userName);

    /** 激活用户 **/
    boolean active(String code);

    User login(User user);
}
