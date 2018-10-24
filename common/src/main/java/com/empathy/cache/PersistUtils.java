package com.empathy.cache;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Map;
import java.util.Queue;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author tybest
 * @email tybest@163.com
 * @date 2017/7/5 17:11
 * @desc
 */
public final class PersistUtils {

    private static final Logger LOG = LoggerFactory.getLogger(PersistUtils.class);

    private static final String linux_dir = "/temp/cache/";

    private static final String window_dir = "D:/temp/cache/";

    private static final String DATA_FILE = "cache.dat";//二进制文件

    private static final String TMP_DATA_FILE = "tmp.data";

    private final static Queue<CacheEventNode> queues = new LinkedBlockingQueue<>(10000);

    private static String CUR_DIR = null;

    private static final String SEPARATOR = System.getProperty("line.separator");

    public static volatile boolean open = false;

    public static volatile boolean done = false;

    static {
        if (OsInfo.isLinux()) {
            CUR_DIR = linux_dir;
        } else if (OsInfo.isWindows()) {
            CUR_DIR = window_dir;
        }
    }

    public static void startPersistTask() {
        LOG.info("start persist task");
        open = true;
        final PersistTask task = new PersistTask();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(task, 60, 60, TimeUnit.SECONDS);
    }


    public static void fireEvent(String flag, String key, CacheNode node) {
        queues.add(new CacheEventNode(flag, key, node));
    }

    /**
     * 重新序列化
     *
     * @param cache
     */
    public static void reload(final Map<String, CacheNode> cache) {
        File file = new File(CUR_DIR + DATA_FILE);
        if (file.exists()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] datas = line.split("/");
                    final String flag = datas[0];
                    final String key = datas[1];
                    final String node = datas[2];
                    if (StringUtils.isEmpty(key)) {
                        continue;
                    }
                    if ("1".equals(flag)) {
                        CacheNode cn = JSON.parseObject(node, CacheNode.class);
                        if (cn != null) {
                            if (!CacheUtils.isExpire(cn)) {
                                cache.put(key, cn);
                            }
                        }
                    } else {
                        //cache.remove(key);
                    }
                }
            } catch (FileNotFoundException e) {
                LOG.error("持久化恢复数据错误：文件未找到");
            } catch (IOException e) {
                LOG.error("持久化恢复数数据错误", e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        LOG.error("持久化恢复数数据关闭输出流错误", e);
                    }
                }
                done = true;
            }
        } else {
            done = true;
        }
    }

    private static class PersistTask extends TimerTask {

        private static final int DEFAULT_BATCH_SIZE = 50;

        @Override
        public void run() {
            CacheEventNode entry = null;
            File dir = new File(CUR_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(CUR_DIR + DATA_FILE);
            BufferedWriter writer = null;
            try {
                if (file.exists()) {
                    writer = new BufferedWriter(new FileWriter(file, true));
                } else {
                    writer = new BufferedWriter(new FileWriter(file));
                }
                if (done) {//load completely
                    for (int index = 0; index <= DEFAULT_BATCH_SIZE && !queues.isEmpty(); index++) {
                        entry = queues.poll();
                        if (entry != null) {
                            writer.append(entry.getFlag() + "/" + entry.getKey() + "/" + JSON.toJSONString(entry.getNode())).append(SEPARATOR);
                        }
                    }
                }
//                else {//清空
//                    writer.write("");
//                }

            } catch (FileNotFoundException e) {
                LOG.error("持久化恢复数据错误：文件未找到");
            } catch (IOException e) {
                LOG.error("持久化恢复数数据错误", e);
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        LOG.error("持久化恢复数数据关闭输出流错误", e);
                    }
                }
            }

        }
    }
}

