package project.yxm.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.yxm.travel.domain.ResultInfo;
import project.yxm.travel.domain.User;
import project.yxm.travel.service.UserService;
import project.yxm.travel.service.impl.UserServiceImpl;

@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从请求端获取到用户输入的验证码
        String web_check = request.getParameter("check");
        //从session中获取到服务器生成的验证码
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        //最后将session中的验证码及时清除,防止验证码遭多次使用
        request.getSession().removeAttribute("CHECKCODE_SERVER");
        //比较是否一致
        if (checkcode_server == null || !web_check.equalsIgnoreCase(checkcode_server)) {
            String err = "验证码错误";
            //验证码错误,将错误信息写入resultInfo中
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg(err);
            //将错误信息封装成json格式发回客户端页面
            String json_err = new ObjectMapper().writeValueAsString(resultInfo);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json_err);
            return;
        }

        //1.获取数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service完成注册
        UserService service = new UserServiceImpl();
        Boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        //4.响应结果
        if (flag) {
            //注册成功
            info.setFlag(true);
        } else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        //将info对象序列化为json
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(info);
        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
