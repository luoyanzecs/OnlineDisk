package cn.team.onlinedisk.service;

import cn.team.onlinedisk.domain.FileInfo;
import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.domain.UserFileInfo;

import java.io.File;
import java.util.List;

public interface UserFileInfoService {


    /**
     * 保存用户上传的文件: 只需将文件的名称加入数据库即可;
     *
     * @param ufi: 用户文件信息类
     * @return: void
     */
    void saveFile(UserFileInfo ufi);

    /**
     * 获取一个可用的文件名
     *
     * @param usi:
     */
    String getAvailPath(UserFileInfo usi);


    int countAllFiles(User user);

    /**
     * 查询用户文件信息;
     *
     * @param user:
     * @return: java.util.List<cn.team.onlinedisk.domain.FileInfo>
     */
    List<FileInfo> findAllFiles(User user);

    /**
     * 在数据库中分页查找
     *
     * @param user:
     * @param start: 开始索引
     * @param sum:  每页的数量
     * @return: java.util.List<cn.team.onlinedisk.domain.FileInfo>
     */
    List<FileInfo> findFileByPages(User user, int start, int sum);

    /**
     * 在cache中分页查找；
     *
     * @param user:
     * @param start: 开始索引
     * @param sum:  每页的数量
     * @return: java.util.List<cn.team.onlinedisk.domain.FileInfo>
     */
    List<FileInfo> findFileByPagesInCache(User user, int start, int sum);

    /**
     * 获取文件路径名
     *
     * @param filename:
     * @param user:
     * @return: java.lang.String
     */
    String getFilePath(String filename, User user);

    /**
     * 删除文件
     *
     * @param filenames:
     * @param user:
     * @return: void
     */
    void deleteFile(String[] filenames, User user);
}
