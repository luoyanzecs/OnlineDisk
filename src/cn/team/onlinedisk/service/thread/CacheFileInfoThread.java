package cn.team.onlinedisk.service.thread;

import cn.team.onlinedisk.dao.impl.UserFileInfoDaoImpl;
import cn.team.onlinedisk.domain.FileInfo;
import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.utils.bean.BeanUtils;
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


public class CacheFileInfoThread implements Runnable {

    private User user;

    public CacheFileInfoThread(@NotNull User user) {
        this.user = user;
    }

    @Override
    public void run() {
        /**
         * TODO 未测试res内没有查询结果时运行是否正确
         */
        ResultSet res = new UserFileInfoDaoImpl().query(this.user, "all");
        List<FileInfo> fileInfoList = new BeanUtils<FileInfo>(FileInfo.class).pack(res);
        CacheUtils.initFile(fileInfoList, this.user);
    }
}














