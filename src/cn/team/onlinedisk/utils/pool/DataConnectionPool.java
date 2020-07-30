package cn.team.onlinedisk.utils.pool;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;


/**
 * @ClassName DataConnectionPool
 * @Description TODO
 * @Author luoyanze
 * @Date 2020/7/29 9:19 下午
 * @Version 1.0
 */


public class DataConnectionPool  {
    static private int maxPoolSize;
    static private int maxWait;
    static private String url;
    static private String username;
    static private String password;
    final private static LinkedList<Connection> connectionsPool = new LinkedList<>();
    static int currentSize;

    /**
     * 数据库连接池扩容量
     */
    final static int step = 10;

    static {
        Properties prop = new Properties();
        InputStream is = DataConnectionPool.class.getClassLoader().getResourceAsStream("config.properties");

        try {
            prop.load(is);
            String driverClassName = prop.getProperty("driverClassName");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
            int initialSize = Integer.parseInt(prop.getProperty("initialSize"));
            maxPoolSize = Integer.parseInt(prop.getProperty("maxPoolSize"));
            maxWait = Integer.parseInt(prop.getProperty("maxWait"));

            Class.forName(driverClassName);
            for (int i = 0; i < initialSize; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                connectionsPool.add(connection);
            }

            currentSize = initialSize;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @description 对数据库连接对象的回收，添加到数据库连接池中
     * @param con:  mysql数据库连接对象
     * @return: void
     */
    public static synchronized void connectionRecycle(@NotNull Connection con) {
           connectionsPool.add(con);
    }


    /**
     * 获取数据库连接对象，当检测到数据库连接池中没有剩余的连接对象时对数据库进行扩容;
     *
     * @return: java.sql.Connection
     */
    public static synchronized Connection getConnection() {
        if(connectionsPool.size() == 0){

            if(currentSize >= maxPoolSize){
                System.out.println("数据库连接池达到最大值");
                return null;
            }else {
                for (int i = 0; i < step; i++) {
                    Connection connection = null;
                    try {
                        connection = DriverManager.getConnection(url, username, password);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    connectionsPool.add(connection);
                    currentSize++;
                }
            }
        }
        return connectionsPool.removeLast();
    }

}
