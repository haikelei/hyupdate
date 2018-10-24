package com.empathy.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tybest
 * @email tybest@163.com
 * @date 2017/7/5 17:10
 * @desc
 */
public final class CacheUtils {

    private static final Logger LOG = LoggerFactory.getLogger(CacheUtils.class);

    private static final Map<String, CacheNode> cache = new ConcurrentHashMap<>();

    private static long interval = 30 * 60 * 1000;//30分钟
    private static volatile long lastClearTime = getNow();

    public static void realod() {
        PersistUtils.reload(cache);
    }

    public static void startPersist() {
        PersistUtils.startPersistTask();
    }

    static {
        //服务器宕机持久化缓存到磁盘
//        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//            @Override
//            public void run() {
//                LOG.info("persist cache to disk");
//                PersistUtils.saveToDisk(cache);
//            }
//        },"Persist-JVM-Cache"));

    }

    private static long getNow() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static void main(String[] args) {
//        put("one","one datasdasdasa");
//        put("two","two data");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        PersistUtils.readFromDisk(cache);
//        System.out.println(cache.size());
    }

    /**
     * @param key
     * @param data   数据
     * @param expire 毫秒
     */
    public static void put(String key, Object data, long expire) {
        if (null == key || key.trim().equals("") || data == null) {
            return;
        }
        expire = getNow() + expire;
        try {
            CacheNode node = new CacheNode(data, expire);
            cache.put(key, node);
            if (PersistUtils.open) {
                PersistUtils.fireEvent("1", key, node);
            }
        } catch (Exception ex) {
            LOG.error("存入缓存错误：", ex);
        }

    }

    public static void put(String key, Object data) {
        if (null == key || key.trim().equals("") || data == null) {
            return;
        }
        try {
            CacheNode node = new CacheNode(data, 0L);
            cache.put(key, node);
            if (PersistUtils.open) {
                PersistUtils.fireEvent("1", key, node);
            }
        } catch (Exception ex) {
            LOG.error("存入缓存错误：", ex);
        }
    }

    public static Object get(String key) {
        clear();
        if (null == key || key.trim().equals("")) {
            return null;
        }
        CacheNode node = cache.get(key);
        if (node != null) {
            if (isExpire(node)) {
                cache.remove(key);
                PersistUtils.fireEvent("0", key, node);
                return null;
            }
            return node.getData();
        }
        return null;
    }

    public static boolean isExpire(CacheNode node) {
        long now = getNow();
        if (node.getExpire() > 0 && now > node.getExpire()) {
            return true;
        }
        return false;
    }

    private static void clear() {
        long now = getNow();
        if ((now - lastClearTime) > interval) {
            Iterator<Map.Entry<String, CacheNode>> it = cache.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, CacheNode> map = it.next();
                final CacheNode node = map.getValue();
                final String key = map.getKey();
                if (node != null && isExpire(node)) {
                    LOG.info("删除key={}对应的对象", key);
                    cache.remove(map.getKey());
                    PersistUtils.fireEvent("0", key, node);
                }
            }
        }
        lastClearTime = getNow();
    }
}
