package project.yxm.travel.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class CharacterFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
        System.out.println("****** 处理post请求乱码专用过滤器init ******");
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //将父接口转为子接口
        //HttpServletRequest request = (HttpServletRequest) req;
        //HttpServletResponse response = (HttpServletResponse) resp;
        //获取请求方法
        //String method = request.getMethod();
        //解决post请求中文乱码问题
        //if (method.equalsIgnoreCase("post")) {
         //   request.setCharacterEncoding("utf-8");
        //}
        //response.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=utf-8");
        //chain.doFilter(req, resp);
        //chain.doFilter(request, response);
        //处理响应乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        chain.doFilter(req, resp);
    }
    public void destroy() {
        System.out.println("*** 过滤器销毁... ***");
    }
}
