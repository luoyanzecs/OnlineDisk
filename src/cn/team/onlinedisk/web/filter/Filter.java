package cn.team.onlinedisk.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "*.jsp",dispatcherTypes = DispatcherType.REQUEST)
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        HttpServletRequest request = (HttpServletRequest)req;
        String servletPath = request.getServletPath();
        if (servletPath.equals("/index.jsp")) {
            chain.doFilter(req, resp);
        }else{
            resp.getWriter().write("无法访问");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
