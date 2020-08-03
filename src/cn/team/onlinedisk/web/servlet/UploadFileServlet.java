package cn.team.onlinedisk.web.servlet;


import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.domain.UserFileInfo;
import cn.team.onlinedisk.service.UserFileInfoService;
import cn.team.onlinedisk.service.impl.UserFileInfoServiceImpl;
import cn.team.onlinedisk.utils.cache.CacheNewUtils;
import cn.team.onlinedisk.utils.cache.CacheUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@MultipartConfig
@WebServlet("/upload")
public class UploadFileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");
        Part part = request.getPart("file");
        String filename = null;
        if (part != null && (filename = part.getSubmittedFileName()).length() > 0) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user_msg");
            UserFileInfoService ufiService = new UserFileInfoServiceImpl();
            UserFileInfo ufInfo = new UserFileInfo(user, filename);
            ufiService.saveFile(ufInfo);
            String path = ufiService.getAvailPath(ufInfo);
            part.write(path);
            //如果想只用文件上传的功能的话把这个注释打开
            //String goalPath = CacheNewUtils.DIR_PATH + File.separator + filename;
            //this.copyFile(path, goalPath);

            response.getWriter().write("上传成功");
        }else {
            response.getWriter().write("上传失败");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.doPost(request, response);
    }

    private boolean isFileExist(String absPath){
        File file = new File(absPath);
        return file.exists();
    }

    private void copyFile(String path, String goalPath) throws IOException {
        File file1 = new File(path);
        FileInputStream fis = new FileInputStream(file1);
        File file2 = new File(goalPath);
        FileOutputStream fos = new FileOutputStream(file2);
        byte[] bytes = new byte[8192];
        int len = 0;
        while ((len = fis.read()) != -1){
            fos.write(bytes, 0, len);
        }
        fis.close();
        fos.close();
    }



}
