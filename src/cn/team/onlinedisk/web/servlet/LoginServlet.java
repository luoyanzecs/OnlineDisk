package cn.team.onlinedisk.web.servlet;

import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.service.impl.UserServiceImpl;
import cn.team.onlinedisk.utils.bean.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        List<User> pack = new BeanUtils<User>(User.class).pack(parameterMap);
        User user = pack.get(0);
        UserServiceImpl usi = new UserServiceImpl();
        request.setAttribute("users", user);
        HttpSession session = request.getSession();
        session.setAttribute("user_msg", user);
        boolean login = usi.login(user);
        if (login){
            request.getRequestDispatcher("/loginSuccess.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
