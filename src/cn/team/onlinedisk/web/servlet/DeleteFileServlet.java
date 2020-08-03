package cn.team.onlinedisk.web.servlet;

import cn.team.onlinedisk.domain.FileInfo;
import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.domain.UserFileInfo;
import cn.team.onlinedisk.service.impl.UserFileInfoServiceImpl;
import cn.team.onlinedisk.utils.bean.BeanUtils;
import cn.team.onlinedisk.utils.cache.CacheNewUtils;
import cn.team.onlinedisk.utils.md5.Encryption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/deleteFile")
public class DeleteFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] filenames = request.getParameterValues("filename");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user_msg");
        new UserFileInfoServiceImpl().deleteFile(filenames, user);
        request.getRequestDispatcher("/select").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
