package project.yxm.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.yxm.travel.domain.ResultInfo;
import project.yxm.travel.service.UserService;
import project.yxm.travel.service.impl.UserServiceImpl;

@WebServlet("/checkUserNameServlet")
public class CheckUserNameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户输入的用户名
        String userName = request.getParameter("username");
        //2.在数据库中查询词用户名是否已经有人使用了
        UserService userService = new UserServiceImpl();
        Boolean flag = userService.isUserExist(userName);
        ResultInfo resultInfo = new ResultInfo();
        if (!flag) {
            //数据库中已经存在该用户名,向前台发送错误消息
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名已被人占用");
        } else {
            //用户名没被占用,返回true
            resultInfo.setFlag(true);
        }
        //将resualtInfo序列化成json
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(resultInfo);
        //将转换好的json发回前端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
