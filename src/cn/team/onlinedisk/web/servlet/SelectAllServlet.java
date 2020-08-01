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
@WebServlet("/selectAll")
public class SelectAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user_msg");
        UserFileInfoService usis = new UserFileInfoServiceImpl();
        int fileCount = usis.countAllFiles(user);
        int pageSum = fileCount/10 + 1;
        int currentPage = 1;
        List<FileInfo> fileByPages = usis.findFileByPages(user, 0, 10);
        System.out.println(fileByPages);
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
