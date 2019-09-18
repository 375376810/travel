package project.yxm.travel.service.impl;

import project.yxm.travel.dao.UserDao;
import project.yxm.travel.dao.impl.UserDaoImpl;
import project.yxm.travel.domain.User;
import project.yxm.travel.service.UserService;
import project.yxm.travel.util.MailUtils;
import project.yxm.travel.util.UuidUtil;

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
        //2.保存用户信息
        //设置激活码,唯一字符串,激活状态
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userDao.saveUser(user);
        //发送激活邮件
        String content = "<a href='http://localhost/travel/activeUserServlet?code=" + user.getCode() + "'>点击激活[旅游网]</a>";
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
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

    /**
     * 激活用户
     * @param code 激活码
     **/
    @Override
    public boolean active(String code) {
        //根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if (user != null) {
            //调用dao修改激活状态
            userDao.updateStatus(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User login(User user) {
        return userDao.findUserByNameAndPassword(user.getUsername(),user.getPassword());
    }

}
