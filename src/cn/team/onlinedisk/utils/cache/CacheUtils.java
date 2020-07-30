package cn.team.onlinedisk.utils.cache;

import cn.team.onlinedisk.domain.FileInfo;
import cn.team.onlinedisk.domain.User;
import cn.team.onlinedisk.domain.UserFileInfo;

import java.io.File;
import java.util.*;

/**
 * TODO  未测试！
 * TODO  未测试！
 * TODO  未测试！
 * TODO  未测试！
 *
 * @ClassName CacheUtils
 * @Description 缓存用户信息和用户文件信息为具体信息
 * @Author luoyanze
 * @Date 2020/7/29 10:03 下午
 * @Version 1.0
 */

public class CacheUtils {
    private static int fileCacheMax = 20;
    private static int userCacheMax = 20;

    final private static Object lockFile = new Object();
    final private static Object lockUser = new Object();

    final public static LinkedHashMap<String, HashMap<String, File>> cacheFileInfo = new LinkedHashMap<>();
    final public static LinkedHashMap<String, User> cacheUserInfo = new LinkedHashMap<>();

    /**
     * 该方法是在用户上传了新文件的时候将其存储到缓存中。
     *
     * @param usi
     * @return: boolean
     */
    public static boolean addFile(UserFileInfo usi){
        Map<String, File> stringFileMap = cacheFileInfo.get(usi.getUsername());
        synchronized (lockFile){
            if(stringFileMap == null){
                HashMap<String, File> tempMap =new HashMap<>();
                tempMap.put(usi.getFilename(),usi.getFile());
                cacheFileInfo.put(usi.getUsername(),tempMap);
            }else {
                stringFileMap.put(usi.getFilename(), usi.getFile());
            }
            return true;
        }

    }

    /**
     * 该方法是在用户登陆之后提供的一个将该用户的文件信息初始化到缓存的一个方法.
     *
     * @param list: FileInfo的集合
     * @param usr:  用户类
     * @return: boolean
     */
    public static boolean initFile(List<FileInfo> list, User usr){
        synchronized (lockFile){
            Map<String, File> stringFileMap = cacheFileInfo.get(usr.getUsername());
            for (FileInfo fileInfo : list) {
                stringFileMap.put(fileInfo.getFilename(),
                        new File(usr.getUsername() + File.separator + fileInfo.getFilename_encryption()));
            }
            return true;
        }
    }

    /**
     * 添加user进入缓存列表
     *
     * @param user:
     * @return: boolean
     */
    public static boolean addUser(User user){
        synchronized (lockUser){
            if (cacheUserInfo.get(user.getUsername()) != null) {
                cacheUserInfo.remove(user.getUsername());
            }
            cacheUserInfo.put(user.getUsername(),user);
        }
        return true;
    }

    /**
     * 定时检查缓存file是否超过规定大小，若超过则更新map保留0.75。
     *
     */
    public static void timingCheckFile(){
        synchronized (lockFile){
            if (cacheFileInfo.size() >= fileCacheMax){
                int sum = (fileCacheMax>>1 + fileCacheMax)>>1;
                Iterator<Map.Entry<String, HashMap<String, File>>> it = cacheFileInfo.entrySet().iterator();
                for (int i = 0; i < sum; i++) {
                    it.next();
                }
                ArrayList<String> keys = new ArrayList<>();
                while(it.hasNext()){
                    keys.add(it.next().getKey());
                }

                keys.stream().forEach(cacheFileInfo::remove);
//                for (String key : keys) {
//                    cacheFileInfo.remove(key);
//                }
            }
        }
    }

    /**
     * 定时检查缓存user是否超过规定大小，若超过则更新map保留0.75。
     *
     */
    public static void timingCheckUser(){
        synchronized (lockUser){
            if(cacheUserInfo.size() >= userCacheMax){
                int sum = (userCacheMax>>1 + userCacheMax)>>1;
                Iterator<Map.Entry<String, User>> it = cacheUserInfo.entrySet().iterator();
                for (int i = 0; i < sum; i++) {
                    it.next();
                }
                ArrayList<String> keys = new ArrayList<>();
                while(it.hasNext()){
                    keys.add(it.next().getKey());
                }
                keys.stream().forEach(cacheUserInfo::remove);
            }
        }
    }


    public static int getFileCacheMax() {
        return fileCacheMax;
    }

    public static void setFileCacheMax(int fileCacheMax) {
        CacheUtils.fileCacheMax = fileCacheMax;
    }

    public static int getUserCacheMax() {
        return userCacheMax;
    }

    public static void setUserCacheMax(int userCacheMax) {
        CacheUtils.userCacheMax = userCacheMax;
    }
}



