package top.danny.tools.thread.pool;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huyuyang@lxfintech.com
 * @Title: MultipleThreadExecutor
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-11-21 18:16:58
 */
public class MultipleThreadExecutor {
    private double[] timeConsume;//每个线程耗时时间

    private CountDownLatch startGate;
    private CountDownLatch endGate;

    public long executeRun(String jobName, int threadNum) {
        startGate = new CountDownLatch(1);
        endGate = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(new MultipleThreadExecutor.worker());
            thread.start();
        }

        long start = System.currentTimeMillis();
        //所有阻塞的任务同时开始
        startGate.countDown();
        try {
            //主线程阻塞,等待其他所有 worker 线程完成后再执行

            endGate.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("用时: " + (end - start) + "ms");

        return end - start;
    }

    class worker implements Runnable {
        public void run() {
            try {
                startGate.await();
                runFunction();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                endGate.countDown();
            }
        }
    }

    public void runFunction() {

    }

}
