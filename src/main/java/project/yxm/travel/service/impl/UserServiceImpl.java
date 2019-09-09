package project.yxm.travel.service.impl;

import project.yxm.travel.dao.UserDao;
import project.yxm.travel.dao.impl.UserDaoImpl;
import project.yxm.travel.domain.User;
import project.yxm.travel.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 先查找,再保存
     **/
    @Override
    public Boolean regist(User user) {
        //1.根据用户名查询用户是否已存在
        User u = userDao.findUserByName(user.getUsername());
        if (u != null) {
            //不为空代表数据库中已经存在该用户
            return false;
        }
        //没有在数据库中找到用户说明该用户可以注册
        userDao.saveUser(user);
        //2.保存用户信息
        return true;
    }

    /**
     * 根据用户名在数据库中查找用户
     **/
    @Override
    public Boolean isUserExist(String userName) {
        User u = userDao.findUserByName(userName);
        if (u != null) {
            //不为空代表数据库中已经存在该用户
            return false;
        }
        return true;
    }

}
