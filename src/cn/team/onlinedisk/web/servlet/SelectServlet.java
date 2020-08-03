package cn.team.onlinedisk.web.servlet;

import cn.team.onlinedisk.domain.FileInfo;
import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.service.UserFileInfoService;
import cn.team.onlinedisk.service.impl.UserFileInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 查询所有的文件内容
 *
 */
@WebServlet("/select")
public class SelectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user_msg");
        UserFileInfoService usis = new UserFileInfoServiceImpl();
        String totalPage = request.getParameter("totalPage");
        int pageSum = (totalPage == null) ? usis.countAllFiles(user) : Integer.parseInt(totalPage);
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int start = (currentPage - 1) * 10;
        //现在cache中查找
        List<FileInfo> fileByPages = usis.findFileByPagesInCache(user, start, 10);
        //若cache中没有则去数据库中查找
        if (fileByPages == null){
            fileByPages = usis.findFileByPages(user, start, 10);
        }
        request.setAttribute("users", user);
        request.setAttribute("fileList", fileByPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPage", pageSum);
        request.getRequestDispatcher("loginSuccess.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
