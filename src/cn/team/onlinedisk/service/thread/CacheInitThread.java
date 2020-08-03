package cn.team.onlinedisk.service.thread;

import cn.team.onlinedisk.dao.impl.UserFileInfoDaoImpl;
import cn.team.onlinedisk.domain.FileInfo;
import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.utils.bean.BeanUtils;
import cn.team.onlinedisk.utils.cache.CacheNewUtils;
import cn.team.onlinedisk.utils.cache.CacheUtils;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.util.List;

/**
 * @ClassName CacheFileInfoThread
 * @Description 该线程类是用来在用户登陆之后，用来处理缓存数据的存。将该用户的所有文件信息存储到缓存中。
 *                 应该在用户登陆成功之后直接调用。
 * @Author luoyanze
 * @Date 2020/7/30 8:39 下午
 * @Version 1.0
 */


public class CacheInitThread implements Runnable {

    private User user;

    public CacheInitThread(@NotNull User user) {
        this.user = user;
    }

    @Override
    public void run() {
        CacheNewUtils.addFileMap(user);
    }
}














