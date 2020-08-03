package cn.team.onlinedisk.web.servlet;

import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.service.UserService;
import cn.team.onlinedisk.service.impl.UserFileInfoServiceImpl;
import cn.team.onlinedisk.service.impl.UserServiceImpl;
import cn.team.onlinedisk.utils.bean.BeanUtils;
import cn.team.onlinedisk.utils.cache.CacheNewUtils;
import cn.team.onlinedisk.utils.md5.Encryption;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<User> pack = new BeanUtils<User>(User.class).pack(parameterMap);
        session.setAttribute("user_msg", pack.get(0));
        request.setAttribute("users", pack.get(0));

        UserService us = new UserServiceImpl();
        boolean flag = us.register(pack.get(0));
        if (flag){
            request.getRequestDispatcher("/login").forward(request, response);
            //为用户创建一个保存自己文件的文件夹
            String dirPath =  CacheNewUtils.DIR_PATH + File.separator + Encryption.md5(request.getParameter("username"));
            File file =new File(dirPath);
            file.mkdirs();
            System.out.println(file.getAbsolutePath());
        }else {
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
