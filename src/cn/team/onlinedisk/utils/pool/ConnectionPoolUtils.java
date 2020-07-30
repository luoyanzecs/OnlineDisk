package cn.team.onlinedisk.utils.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName ConnectionPoolUtils
 * @Description TODO
 * @Author luoyanze
 * @Date 2020/7/29 9:20 下午
 * @Version 1.0
 */


public class ConnectionPoolUtils {
    /**
     * 获取数据库连接池对象。
     *
     * @return: java.sql.Connection
     */
    public static Connection getConnection(){
        return DataConnectionPool.getConnection();
    }

    /**
     * 将使用完毕的Connection对象回收到数据库连接池中。
     *
     * @param conn:
     * @return: void
     */
    public static void close(Connection conn){
        DataConnectionPool.connectionRecycle(conn);
    }


    /**
     *Releasing resources Statement;
     *
     * @param stat:
     * @return: void
     */
    public static void close(Statement stat){
        if(stat != null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Releasing resource ResultSet;
     *
     * @param res:
     * @return: void
     */
    public static void close(ResultSet res){
        if(res != null){
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *  Releasing resources Statement, Connection.
     *
     * @param stat
     * @param conn
     * */
    public static void close(Statement stat, Connection conn){
        if(stat != null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(conn);
    }

    /**
     *  Releasing resources Statement, Connection, ResultSet.
     *
     * @param stat
     * @param conn
     * @param res
     *
     * */
    public static void close(Statement stat, Connection conn, ResultSet res){
        if(stat != null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(conn);

        if(res != null){
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
