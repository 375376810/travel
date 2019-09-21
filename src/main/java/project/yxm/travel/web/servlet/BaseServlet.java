package project.yxm.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所有servlet的基类,简化所有servlet,完成方法分发
 */
public class BaseServlet extends HttpServlet {

    /**
     * 覆盖父类的service方法,实现子类的方法分发
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.从uri中获取方法名称(/travel/user/add)
        String requestURI = req.getRequestURI();
        String methodName = requestURI.substring(requestURI.lastIndexOf('/') + 1);
        //2.通过反射获取子类中的相应方法,并invoke
        //巧妙使用调用者的引用this,谁调用,this就是谁,通过this反射其method
        try {
            Method method = this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req, resp );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将对象序列化为json后直接写回客户端
     * @param object
     * @param response
     * @throws IOException
     */
    public void writeJsonToReponse(Object object, HttpServletResponse response) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getOutputStream(), object);
    }

    /**
     * 将传入的对象序列化为json格式的字符串并返回
     * @param object
     * @return
     * @throws JsonProcessingException
     */
    public String writeValueAsString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
