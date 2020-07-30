package cn.team.onlinedisk.dao;

import java.sql.ResultSet;

public interface UserDao {

    /**
     * 查询 user 表返回一个Result集合。
     *
     * @param sql : 查询语句。
     * @return: java.sql.ResultSet
     */
    ResultSet query(String sql);

    /**
     * 验证是否存在用户。
     *
     * @param name : 用户的姓名
     * @return: java.lang.Boolean
     */
    Boolean isExist(String name);

    /**
     * 验证用户信息是否正确。
     *
     * @param name:
     * @param password:
     * @return: java.lang.Boolean
     */
    Boolean isInfoRight(String name , String password);

    /**
     * 执行增删该查sql。
     *
     * @param sql:  增删该查语句。
     * @return: int : 返回影响的条数。
     */
    int update(String sql);
}
