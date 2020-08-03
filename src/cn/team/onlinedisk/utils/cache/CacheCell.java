package cn.team.onlinedisk.utils.cache;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @ClassName CacheMap
 * @Description map缓存的每组实类
 * @Author luoyanze
 * @Date 2020/8/2 9:01 上午
 * @Version 1.0
 */

public class CacheCell {
    private static int fileCacheMax = 20;
    final public  Object lockFile;

    /**
     * 最外层{@code HashMap}可以实现将最新的元素放在最开始的位置，在缓存存满后则可以遍历删除最老元素。
     * 内层的{@code LinkedHashMap}可以实现顺序存放。
     *
     */
    final public HashMap<String, LinkedHashMap<String, File>> cacheFileInfo;

    public CacheCell() {
        lockFile = new Object();
        cacheFileInfo = new HashMap<>();
    }

    public static int getFileCacheMax() {
        return fileCacheMax;
    }

    public static void setFileCacheMax(int fileCacheMax) {
        CacheCell.fileCacheMax = fileCacheMax;
    }
}
