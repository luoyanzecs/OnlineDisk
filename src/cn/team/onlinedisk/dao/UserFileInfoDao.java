package cn.team.onlinedisk.dao;

import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.domain.UserFileInfo;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.util.List;

public interface UserFileInfoDao {

    /**
     * 查询 用户的文件表 表返回一个Result集合。
     *
     * @param user : 用户对象.
     * @param fileName : 文件名，如果为"all" 则查询所有。
     * @return: java.sql.ResultSet
     */
    ResultSet query(@NotNull User user, String fileName);

    /**
     * 执行增删该查sql。
     *
     * @param usi:  用户文件信息的封装。
     * @return: int : 返回影响的条数。
     */
    int addNewFile(UserFileInfo usi);

    /**
     * 统计文件数目
     *
     * @param user:
     * @return: int
     */
    int countFile(User user);

    /**
     * 删除用户文件；
     *
     * @param list:  用于存储的待删除的列表
     * @return: void
     */
    void deleteFile(List<UserFileInfo> list);

    /**
     * 分页 查询 user 表返回一个Result集合。
     *
     * @param user:
     * @param startIndex: 开始索引
     * @param number:  每页的条目数量
     * @return: java.sql.ResultSet
     */
    ResultSet queryForPage(User user, int startIndex, int number);

    /**
     * 根据用户类创建一张table
     *
     * @param user:
     * @return: boolean
     */
    boolean creatTable(User user);
}
