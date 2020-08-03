package cn.team.onlinedisk.web.servlet;

import cn.team.onlinedisk.dao.impl.UserDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registerCheck")
public class RegisterCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");

        //返回的数据{"isExsit" : true, "msg" : "该名称已被占用"}
        //         {"isExsit" : fasle, "msg" : "该名称未被占用"}
        Map<String, Object> map = new HashMap<>();

        UserDaoImpl userDao = new UserDaoImpl();
        boolean flag = userDao.isExistUser(username);
        if (flag){
            map.put("isExsit" , true);
            map.put("msg", "该名称已被占用");
        }else{
            map.put("isExsit" , false);
            map.put("msg", "该名称未被占用");
        }


        //将map转为json,并且传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
