package cn.team.onlinedisk.utils.pool;

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
    final private static LinkedList<Connection> connectionsPool = new LinkedList<>();
    static int currentSize;

    static {
        Properties prop = new Properties();
        InputStream is = DataConnectionPool.class.getClassLoader().getResourceAsStream("config.properties");

        try {
            prop.load(is);
            String driverClassName = prop.getProperty("driverClassName");
            url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
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
     * @description
     * @param con:  mysql数据库连接对象
     * @return: void
     */
    public synchronized void connectionRecycle(Connection con) {
        if (con != null){
           connectionsPool.add(con);
        }
    }


    public synchronized Connection getConnection() throws SQLException {
        return connectionsPool.removeLast();
    }

}
