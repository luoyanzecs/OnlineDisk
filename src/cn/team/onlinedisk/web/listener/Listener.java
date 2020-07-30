package cn.team.onlinedisk.web.listener;

import cn.team.onlinedisk.utils.cache.CacheUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener{

    private boolean flagUser = true;
    private boolean flagFile = true;
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

            Class.forName("cn.team.onlinedisk.utils.cache.CacheUtils");
            System.out.println("缓存初始化成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /**
         *
         * 创建线程来监督缓存的大小;
         */

        new Thread(()->{
            System.out.println("文件缓存监督线程启动");
            while(flagFile){
                try {
                    Thread.sleep(1000*60*5);
                    CacheUtils.timingCheckFile();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            System.out.println("用户缓存监督线程启动");
            while(flagUser){
                try {
                    Thread.sleep(1000*60*5);
                    CacheUtils.timingCheckUser();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */

      this.flagUser =false;
      this.flagFile =false;
    }

}
