package cn.team.onlinedisk.service.impl;

import cn.team.onlinedisk.dao.UserFileInfoDao;
import cn.team.onlinedisk.dao.impl.UserFileInfoDaoImpl;
import cn.team.onlinedisk.domain.FileInfo;
import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.domain.UserFileInfo;
import cn.team.onlinedisk.service.UserFileInfoService;
import cn.team.onlinedisk.utils.bean.BeanUtils;

import java.io.File;
import java.sql.ResultSet;
import java.util.List;

/**
 * @ClassName UserFileInfoServiceImpl
 * @Description TODO
 * @Author luoyanze
 * @Date 2020/7/29 9:41 下午
 * @Version 1.0
 */


public class UserFileInfoServiceImpl implements UserFileInfoService {
    @Override
    public Boolean saveFile(UserFileInfo usi, File file) {
        return null;
    }

    @Override
    public int countAllFiles(User user) {
        UserFileInfoDao ufiDao = new UserFileInfoDaoImpl();
        int i = ufiDao.countFile(user);
        return i;
    }

    @Override
    public List<FileInfo> findAllFiles(User user) {
        UserFileInfoDao usiDao = new UserFileInfoDaoImpl();
        ResultSet res = usiDao.query(user, "all");
        List<FileInfo> pack = new BeanUtils<FileInfo>(FileInfo.class).pack(res);
        return pack;
    }

    @Override
    public List<FileInfo> findFileByPages(User user, int start, int sum) {
        UserFileInfoDao usiDao = new UserFileInfoDaoImpl();
        ResultSet res = usiDao.queryForPage(user, start, sum);
        List<FileInfo> pack = new BeanUtils<FileInfo>(FileInfo.class).pack(res);
        return pack;

    }
}








