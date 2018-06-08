package top.danny.tools.thread.pool;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author huyuyang@lxfintech.com
 * @Title: Main
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-11-21 18:23:09
 */
public class Main {

    public static void main(String[] args) {
        new Executor().executeRun("测试",5);

    }



    public void fun1(){
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
