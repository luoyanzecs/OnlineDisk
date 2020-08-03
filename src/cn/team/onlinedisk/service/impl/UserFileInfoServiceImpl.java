package cn.team.onlinedisk.service.impl;

import cn.team.onlinedisk.dao.UserFileInfoDao;
import cn.team.onlinedisk.dao.impl.UserFileInfoDaoImpl;
import cn.team.onlinedisk.domain.FileInfo;
import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.domain.UserFileInfo;
import cn.team.onlinedisk.service.UserFileInfoService;
import cn.team.onlinedisk.utils.bean.BeanUtils;
import cn.team.onlinedisk.utils.cache.CacheCell;
import cn.team.onlinedisk.utils.cache.CacheDataArray;
import cn.team.onlinedisk.utils.cache.CacheNewUtils;
import cn.team.onlinedisk.utils.cache.CacheUtils;
import cn.team.onlinedisk.utils.md5.Encryption;

import java.io.File;
import java.sql.ResultSet;
import java.util.*;

/**
 * @ClassName UserFileInfoServiceImpl
 * @Description TODO
 * @Author luoyanze
 * @Date 2020/7/29 9:41 下午
 * @Version 1.0
 */


public class UserFileInfoServiceImpl implements UserFileInfoService {
    @Override
    public void saveFile(UserFileInfo ufi) {
        CacheNewUtils.addFile(ufi);
        UserFileInfoDao ufiDao = new UserFileInfoDaoImpl();
        ufiDao.addNewFile(ufi);
    }

    @Override
    public String getAvailPath(UserFileInfo ufi) {
        return ufi.getFile().getAbsolutePath();
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
        return pack.size() > 0 ? pack : null;
    }

    @Override
    public List<FileInfo> findFileByPages(User user, int start, int sum) {
        UserFileInfoDao usiDao = new UserFileInfoDaoImpl();
        ResultSet res = usiDao.queryForPage(user, start, sum);
        List<FileInfo> pack = new BeanUtils<FileInfo>(FileInfo.class).pack(res);
        return pack.size() > 0 ? pack : null;

    }

    @Override
    public List<FileInfo> findFileByPagesInCache(User user, int start, int sum) {
        List<FileInfo> listFile = new ArrayList<>();
        int index = user.hashCode() & CacheDataArray.MAX_GROUP;
        //CacheCell cacheCell = CacheDataArray.CACHE_CELLS[index];
        CacheCell cacheCell = CacheDataArray.CACHE_CELLS[index];
        LinkedHashMap<String, File> value = cacheCell.cacheFileInfo.get(user.getUsername());
        for (Map.Entry<String, File> fileMapEntry : value.entrySet()) {
            if (start > 0) {
                start--;
            }else {
                if (sum > 0){
                    FileInfo fileInfo = new FileInfo(fileMapEntry.getKey(), fileMapEntry.getValue().getName());
                    listFile.add(fileInfo);
                    sum--;
                }
            }
        }

        return listFile.size() > 0 ? listFile : null;
    }

    @Override
    public String getFilePath(String filename, User user) {
        String dir = CacheNewUtils.DIR_PATH + File.separator + Encryption.md5(user.getUsername());
        return dir + File.separator + Encryption.md5(filename);
    }

    @Override
    public void deleteFile(String[] filenames, User user) {
        new UserFileInfoDaoImpl().deleteFile(filenames, user);
        int length = filenames.length;
        for (int i = 0; i < length; i++) {
            CacheNewUtils.deleteFile(user, filenames[i]);
            String filePath = this.getFilePath(filenames[i], user);
            File file = new File(filePath);
            file.delete();
        }
    }
}








