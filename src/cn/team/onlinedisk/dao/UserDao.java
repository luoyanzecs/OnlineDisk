package cn.team.onlinedisk.dao;

import cn.team.onlinedisk.domain.User;

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
     * @return: boolean
     */
    boolean isExist(String name);

    /**
     * 验证用户信息是否正确。
     *
     * @param user
     * @return: boolean
     */
    boolean isInfoRight(User user);

    /**
     * 执行增删该查sql。
     *
     * @param user : 待添加的user
     * @return: int : 返回影响的条数。
     */
    int update(User user);

    /**
     * 添加新用户到数据库内
     *
     * @param user
     * @return: boolean
     */
    boolean addNewUser(User user);
}
