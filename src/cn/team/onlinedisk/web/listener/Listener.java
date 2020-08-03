package cn.team.onlinedisk.web.listener;

import cn.team.onlinedisk.utils.cache.CacheNewUtils;
import cn.team.onlinedisk.utils.cache.CacheUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Timer;
import java.util.TimerTask;

@WebListener
public class Listener implements ServletContextListener{

    // Public constructor is required by servlet spec
    public Listener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */

      /**
       * 初始化数据库连接池和缓存cache.
       */
        try {
            Class.forName("cn.team.onlinedisk.utils.pool.DataConnectionPool");
            System.out.println("数据库连接池初始化成功");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("cn.team.onlinedisk.utils.cache.CacheNewUtils");
            System.out.println("缓存初始化成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("cn.team.onlinedisk.utils.cache.CacheDataArray");
            System.out.println("缓存初始化成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         *
         * 创建线程来监督缓存的大小;
         */

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("启动线程监视缓存");
                CacheNewUtils.monitorCache();
            }
        }, 0, 1000*60*10);

        

    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */

    }

}
