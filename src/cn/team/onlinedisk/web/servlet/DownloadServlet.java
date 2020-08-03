package cn.team.onlinedisk.web.servlet;

import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.service.impl.UserFileInfoServiceImpl;
import cn.team.onlinedisk.utils.cache.CacheNewUtils;
import cn.team.onlinedisk.utils.cache.CacheUtils;
import cn.team.onlinedisk.utils.md5.Encryption;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user_msg");
        String filename = request.getParameter("filename");
        String filePath = new UserFileInfoServiceImpl().getFilePath(filename, user);
        String mimeType = this.getServletContext().getMimeType(filename);
        response.setHeader("content-type", mimeType);
        response.setHeader("content-disposition","attachment;filename="+filename);
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        ServletOutputStream ops = response.getOutputStream();
        byte[] bytes = new byte[8092];
        int len = 0;
        while((len = fis.read(bytes)) != -1){
            ops.write(bytes, 0, len);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
