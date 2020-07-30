package cn.team.onlinedisk.service;

import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.domain.UserFileInfo;

import java.io.File;

public interface UserFileInfoService {


    /**
     * 保存用户上传的文件: 只需将文件的名称加入数据库即可;
     *
     * @param usi: 用户文件信息类
     * @param file:  用户上传的文件;
     * @return: java.lang.Boolean
     */
    Boolean saveFile(UserFileInfo usi , File file);
}
