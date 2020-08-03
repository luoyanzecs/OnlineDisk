package cn.team.onlinedisk.utils.cache;


/**
 * @ClassName CacheData
 * @Description 用户文件缓存map的集合封装类
 * @Author luoyanze
 * @Date 2020/8/2 9:05 上午
 * @Version 1.0
 */


public class CacheDataArray {
    /**
     * 设置map缓存的最大分组量。
     */
    final public static  int MAX_GROUP = (0xFF);
    final public static CacheCell[] CACHE_CELLS = new CacheCell[256];

    static {
        for (int i = 0; i < MAX_GROUP; i++) {
            CACHE_CELLS[i] = new CacheCell();
        }
    }


}
