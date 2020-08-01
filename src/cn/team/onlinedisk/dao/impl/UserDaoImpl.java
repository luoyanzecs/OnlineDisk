package cn.team.onlinedisk.dao.impl;

import cn.team.onlinedisk.dao.UserDao;
import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.utils.md5.Encryption;
import cn.team.onlinedisk.utils.pool.ConnectionPoolUtils;

import java.sql.*;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author luoyanze
 * @Date 2020/7/29 9:24 下午
 * @Version 1.0
 */


public class UserDaoImpl implements UserDao {
    @Override
    public ResultSet query(String sql) {
        Connection conn = ConnectionPoolUtils.getConnection();
        Statement stat = null;
        ResultSet res = null;
        try {
            stat = conn.createStatement();
            res = stat.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolUtils.close(stat, conn, res);
            return res;
        }
    }

    @Override
    public boolean isExistUser(String name) {
        String md5Name = Encryption.md5(name);
        boolean flag = false;
        String sql = "select * from user_info where username = ? limit 1";
        Connection connection = ConnectionPoolUtils.getConnection();
        PreparedStatement stat = null;
        ResultSet res = null;
        try {
            stat = connection.prepareStatement(sql);
            stat.setString(1,md5Name);
            res = stat.executeQuery();
            flag = res.next() ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolUtils.close(stat,connection,res);
            return flag;
        }
    }

    @Override
    public boolean isInfoRight(User user) {
        String username = Encryption.md5(user.getUsername());
        String password = Encryption.md5(user.getPassword());

        boolean flag = false;
        String sql = "select * from user_info where username = ? and password = ? limit 1";
        Connection conn = ConnectionPoolUtils.getConnection();
        PreparedStatement stat = null;
        ResultSet res = null;
        try {
            stat = conn.prepareStatement(sql);
            stat.setString(1,username);
            stat.setString(2,password);
            res = stat.executeQuery();
            flag = res.next() ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionPoolUtils.close(stat,conn,res);
            return flag;
        }
    }

    @Override
    public int update(User user) {
        String username = Encryption.md5(user.getUsername());
        String password = Encryption.md5(user.getPassword());
        String tel = Encryption.md5(user.getTel());

        String sql ="update user_info set password=?, tel=? where username=?";
        Connection conn = ConnectionPoolUtils.getConnection();
        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            stat.setString(3, username);
            stat.setString(1, password);
            stat.setString(2, tel);
            stat.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if (conn != null){
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
            ConnectionPoolUtils.close(stat,conn);
        }
        return 0;
    }

    @Override
    public boolean addNewUser(User user) {
        String username = Encryption.md5(user.getUsername());
        String password = Encryption.md5(user.getPassword());
        String tel = Encryption.md5(user.getTel());
        String sql = "insert into user_info(username, password, tel) values(?,?,?)";

        Connection conn = ConnectionPoolUtils.getConnection();
        PreparedStatement stat = null;

        try {
            stat = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            stat.setString(1, username);
            stat.setString(2, password);
            stat.setString(3, tel);
            stat.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if (conn != null){
                try {
                    conn.rollback();
                    return false;
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally {
                ConnectionPoolUtils.close(stat,conn);
        }
        return true;
    }
}



