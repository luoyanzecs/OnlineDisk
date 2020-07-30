package cn.team.onlinedisk.dao.impl;

import cn.team.onlinedisk.dao.UserFileInfoDao;
import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.domain.UserFileInfo;
import cn.team.onlinedisk.utils.md5.Encryption;
import cn.team.onlinedisk.utils.pool.ConnectionPoolUtils;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.List;

/**
 * @ClassName UserFileInfoDaoImpl
 * @Description TODO
 * @Author luoyanze
 * @Date 2020/7/29 9:47 下午
 * @Version 1.0
 */


public class UserFileInfoDaoImpl implements UserFileInfoDao {

    @Override
    public ResultSet query(@NotNull User user, String fileName) {
        String sql = null;
        if("all".equals(fileName)){
            sql = "select * from " + Encryption.md5(user.getUsername()) + "_file";
        }else {
            sql = "select * from "+ Encryption.md5(user.getUsername()) + "_file" + "where filename = " + fileName;
        }

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
    public int addNewFile(UserFileInfo usi) {
        String sql = "insert into ? values (?, ?)";

        Connection conn = ConnectionPoolUtils.getConnection();
        PreparedStatement stat = null;
        ResultSet res = null;
        int i = 0;

        try {
            conn.setAutoCommit(false);
            stat = conn.prepareStatement(sql);
            stat.setString(1,Encryption.md5(usi.getUsername()) + "_file");
            stat.setString(2, usi.getFilename());
            stat.setString(3, Encryption.md5(usi.getFilename()));
            i = stat.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            ConnectionPoolUtils.rollback(conn);
            e.printStackTrace();
        }finally {
            ConnectionPoolUtils.close(stat, conn, res);
            return i;
        }
    }

    @Override
    public void deleteFile(List<UserFileInfo> list) {
        String sql = "delete from ? where filename = ?";
        Connection conn = ConnectionPoolUtils.getConnection();
        PreparedStatement stat = null;
        ResultSet res = null;

        for (UserFileInfo usi : list) {
            try {
                conn.setAutoCommit(false);
                stat = conn.prepareStatement(sql);
                stat.setString(1,Encryption.md5(usi.getUsername()) + "_file");
                stat.setString(2, usi.getFilename());
                stat.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                ConnectionPoolUtils.rollback(conn);
                e.printStackTrace();
            }
        }
        ConnectionPoolUtils.close(stat, conn, res);
    }

    @Override
    public ResultSet queryForPage(User user, int startIndex, int number) {

        String sql = "select * from " + Encryption.md5(user.getUsername()) +
                "_file limit "+ startIndex + "," + number;

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
    public boolean creatTable(User user) {
        String username = Encryption.md5(user.getUsername());
        String tableName = username + "_file";
        String sql = "create table "+ tableName +"(filename varchar(32) primary key ,filename_encryption varchar(32))";
        boolean execute =false;

        Connection conn = ConnectionPoolUtils.getConnection();
        Statement stat = null;
        try {
            stat = conn.createStatement();
            execute = stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return execute;
    }
}
