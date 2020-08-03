package cn.team.onlinedisk.utils.cache;


import cn.team.onlinedisk.dao.UserFileInfoDao;
import cn.team.onlinedisk.dao.impl.UserFileInfoDaoImpl;
import cn.team.onlinedisk.domain.FileInfo;
import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.domain.UserFileInfo;
import cn.team.onlinedisk.utils.bean.BeanUtils;
import cn.team.onlinedisk.utils.md5.Encryption;
import cn.team.onlinedisk.utils.pool.DataConnectionPool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @ClassName CacheNewUtils
 * @Description 用户文件信息的map缓存工具类;
 * @Author luoyanze
 * @Date 2020/8/2 9:14 上午
 * @Version 1.0
 */


public class CacheNewUtils {

    public static String DIR_PATH;

    static {
        Properties prop = new Properties();
        InputStream is = DataConnectionPool.class.getClassLoader().getResourceAsStream("loadPath.properties");

        try {
            prop.load(is);
            DIR_PATH = prop.getProperty("dir");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从缓存中获取用户文件信息
     *
     * @param username:  用于获取用户姓名
     * @return: java.util.Map<java.lang.String,java.io.File>
     */
    public static Map<String, File> getFileMap(String username){
        int index = Objects.hashCode(username) & CacheDataArray.MAX_GROUP;
        /////////////////////////
        CacheCell cacheCell = CacheDataArray.CACHE_CELLS[index];
        return cacheCell.cacheFileInfo.get(username);
    }

    public static Map<String, File> getFileMap(User user){
        int index = user.hashCode() & CacheDataArray.MAX_GROUP;
        CacheCell cacheCell = CacheDataArray.CACHE_CELLS[index];
        return cacheCell.cacheFileInfo.get(user.getUsername());
    }

    public static File getFile(String username, String filename){
        int index = Objects.hashCode(username) & CacheDataArray.MAX_GROUP;
        CacheCell cacheCell = CacheDataArray.CACHE_CELLS[index];
        HashMap<String, File> fileMap = cacheCell.cacheFileInfo.get(username);
        return fileMap.get(filename);
    }

    public static File getFile(UserFileInfo ufi){
        int index = Objects.hashCode(ufi.getUsername()) & CacheDataArray.MAX_GROUP;
        CacheCell cacheCell = CacheDataArray.CACHE_CELLS[index];
        HashMap<String, File> fileMap = cacheCell.cacheFileInfo.get(ufi.getUsername());
        return fileMap.get(ufi.getFilename());
    }

    /**
     * 将数据库内的文件信息添加到数据库内;
     *
     * @return: void
     */
    public static void addFileMap(User user){
        boolean flag = false;
        String username = user.getUsername();
        int index = user.hashCode() & CacheDataArray.MAX_GROUP;
        CacheCell cacheCell = CacheDataArray.CACHE_CELLS[index];
        synchronized (cacheCell.lockFile){
            LinkedHashMap<String, File> fileMap = cacheCell.cacheFileInfo.remove(username);
            if (fileMap != null){
                //不为空则证明内部有该内容，则继续将他加入map保证他处于数据的末尾;
                //最早加入的在最前面
                cacheCell.cacheFileInfo.put(username, fileMap);
                flag = false;
            } else {
                flag = true;
            }
        }

        /**
         * 为了防止在访问数据库时候占用时间过多，将数据库操作部分至synchronized外部处理，
         * 查询完毕之后再进行同步操作。
         */
        if (flag){
            //从数据库中查询该用户的数据并且保存到缓存中;
            UserFileInfoDao ufi = new UserFileInfoDaoImpl();
            ResultSet res = ufi.query(user, "all");
            List<FileInfo> pack = new BeanUtils<FileInfo>(FileInfo.class).pack(res);
            LinkedHashMap<String, File> mapInsert = new LinkedHashMap<>();
            for (FileInfo fileInfo : pack) {
                //获取file的路径
                String filePath = DIR_PATH + File.separator +
                        Encryption.md5(username) + File.separator +fileInfo.getFilename_encryption();
                File filePut = new File(filePath);
                mapInsert.put(fileInfo.getFilename(), filePut);
            }
            synchronized (cacheCell.lockFile){
                cacheCell.cacheFileInfo.put(username, mapInsert);
            }
        }

    }

    /**
     * 将一个文件信息类对象加入到缓存中
     *
     * @param ufi: 文件信息对象
     * @return: void
     */
    public static void addFile(UserFileInfo ufi){
        String username = ufi.getUsername();
        int index = Objects.hash(username) & CacheDataArray.MAX_GROUP;
        CacheCell cacheCell = CacheDataArray.CACHE_CELLS[index];
        synchronized (cacheCell.lockFile){
            LinkedHashMap<String, File> fileMap = cacheCell.cacheFileInfo.get(username);
            if (fileMap != null){
                fileMap.put(ufi.getFilename(), ufi.getFile());
            } else {
                LinkedHashMap<String, File> newMap = new LinkedHashMap<>();
                newMap.put(ufi.getFilename(), ufi.getFile());
                cacheCell.cacheFileInfo.put(username, newMap);
            }
        }
    }

    /**
     * 检查并控制缓存的大小
     *
     * @return: void
     */
    public static void monitorCache(){
        int maxSize = CacheCell.getFileCacheMax();
        for (int i = 0; i < CacheDataArray.MAX_GROUP; i++) {
            CacheCell cacheCell = CacheDataArray.CACHE_CELLS[i];
            int holdCount = (maxSize >> 1) + maxSize;
            synchronized (cacheCell.lockFile){
                Iterator<Map.Entry<String, LinkedHashMap<String, File>>> iterator = null;
                iterator = cacheCell.cacheFileInfo.entrySet().iterator();
                while(iterator.hasNext()){
                    if (holdCount > 0){
                        --holdCount;
                    }else {
                        iterator.remove();
                    }
                }
            }
        }
    }

    /**
     * 删除一个文件从cache中
     *
     * @param user:
     * @param filename:
     * @return: void
     */
    public static void deleteFile(User user, String filename){
        int index = user.hashCode() & CacheDataArray.MAX_GROUP;
        //CacheCell cacheCell = CacheDataArray.CACHE_CELLS[index];
        CacheCell cacheCell = CacheDataArray.CACHE_CELLS[index];
        synchronized (cacheCell.lockFile){
            //修改之后要保证顺序
            LinkedHashMap<String, File> fileMap = cacheCell.cacheFileInfo.remove(user.getUsername());
            fileMap.remove(filename);
            cacheCell.cacheFileInfo.put(user.getUsername(), fileMap);
        }
    }

}




