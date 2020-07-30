package cn.team.onlinedisk.dao;

import java.sql.ResultSet;

public interface UserFileInfoDao {

    /**
     * 查询 user 表返回一个Result集合。
     *
     * @param sql : 查询语句。
     * @return: java.sql.ResultSet
     */
    ResultSet query(String sql);

    /**
     * 执行增删该查sql。
     *
     * @param sql:  增删该查语句。
     * @return: int : 返回影响的条数。
     */
    int update(String sql);

    /**
     * 分页 查询 user 表返回一个Result集合。
     *
     * @param sql:
     * @param startIndex: 开始索引
     * @param number:  每页的条目数量
     * @return: java.sql.ResultSet
     */
    ResultSet query(String sql, int startIndex, int number);
}
