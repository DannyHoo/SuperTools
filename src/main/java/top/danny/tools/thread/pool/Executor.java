package top.danny.tools.thread.pool;

import java.util.Random;

/**
 * @author huyuyang@lxfintech.com
 * @Title: Executor
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-11-21 18:59:55
 */
public class Executor extends MultipleThreadExecutor {

    public static void main(String[] args) {
        //new Executor().executeRun("测试",10);
        long startTime = System.currentTimeMillis();
        ThreadTemp threadTemp1 = new ThreadTemp();
        Thread thread1 = new Thread(threadTemp1);
        thread1.start();

        ThreadTemp threadTemp2 = new ThreadTemp();
        Thread thread2 = new Thread(threadTemp2);
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            System.out.println("执行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void runFunction() {
        Random random = new Random();
        int num = random.nextInt(1000) + 1000;
        System.out.println(Thread.currentThread().getName() + " start and sleep: " + num + "ms");
        try {
            Thread.currentThread().sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}