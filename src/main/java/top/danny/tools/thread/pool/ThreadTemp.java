package top.danny.tools.thread.pool;

import java.util.Random;

/**
 * @author huyuyang@lxfintech.com
 * @Title: ThreadTemp
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-11-21 19:14:21
 */
public class ThreadTemp implements Runnable {
    private long startTime;
    private long endTime;
    private long consumeTime;

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        try {
            Thread.currentThread().sleep(new Random().nextInt(1000) + 5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();
        consumeTime = endTime - startTime;
        System.out.println(Thread.currentThread().getName() + " consume time is :" + consumeTime);
    }
}
