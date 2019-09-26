package project.yxm.travel.web.servlet;


import org.springframework.util.StringUtils;

import java.io.IOException;

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
        String rname = request.getParameter("rname");
        //处理从页面返回的乱码值问题
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
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
        if (!StringUtils.isEmpty(cidStr) && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        //rname搜索关键字
        if (StringUtils.isEmpty(rname) || "null".equals(rname)) {
            rname = null;
        }
        //3.调用service查询pageBean对象
        PageBean<Route> pageBean = routeservice.pageQuery(cid, pageSize, currentPage, rname);

        //4.将pageBean对象序列化并返回
        writeJsonToReponse(pageBean, response);
    }

    /**
     * 根据id查询一个旅游线路的详细信息
     * @param request
     * @param response
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rid = request.getParameter("rid");
        Route route = routeservice.findOne(rid);
        writeJsonToReponse(route, response);
    }

}
