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
     * @param usi: 用户文件信息类
     * @param file:  用户上传的文件;
     * @return: java.lang.Boolean
     */
    Boolean saveFile(UserFileInfo usi , File file);

    int countAllFiles(User user);

    /**
     * 查询用户文件信息;
     *
     * @param user:
     * @return: java.util.List<cn.team.onlinedisk.domain.FileInfo>
     */
    List<FileInfo> findAllFiles(User user);

    List<FileInfo> findFileByPages(User user, int start, int sum);
}
