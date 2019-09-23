package project.yxm.travel.web.servlet;


import org.springframework.util.StringUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.yxm.travel.domain.PageBean;
import project.yxm.travel.domain.Route;
import project.yxm.travel.service.Routeservice;
import project.yxm.travel.service.impl.RouteServiceImpl;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    Routeservice routeservice = new RouteServiceImpl();

    /**
     * 分页查询
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        //2.将页面传回的值转换为Int型
        int currentPage = 0;
        int pageSize = 0;
        int cid = 0;
        if (!StringUtils.isEmpty(currentPageStr)) {
            //当前页码,如果不传递,则默认当前为第一页
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        //每页显示的条数,默认为5条每页
        if (!StringUtils.isEmpty(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }
        //类别id
        if (!StringUtils.isEmpty(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        //3.调用service查询pageBean对象
        PageBean<Route> pageBean = routeservice.pageQuery(cid,pageSize,currentPage);

        //4.将pageBean对象序列化并返回
        writeJsonToReponse(pageBean, response);

    }

}