package cn.team.onlinedisk.domain;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName Cache
 * @Description 缓存用户信息和用户文件信息为具体信息
 * @Author luoyanze
 * @Date 2020/7/29 10:03 下午
 * @Version 1.0
 */

public class Cache{

    public static LinkedHashMap<String, List<String>> cacheFileInfo = new LinkedHashMap<>();

    public static LinkedHashMap<String, User> cacheUserInfo = new LinkedHashMap<>();
}
