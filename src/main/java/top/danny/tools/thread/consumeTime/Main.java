package top.danny.tools.thread.consumeTime;

import java.util.Random;

/**
 * @author huyuyang@lxfintech.com
 * @Title: Main
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-11-22 11:20:01
 */
public class Main {
    public static void main(String[] args) {
        Executor executor=new Executor(new ExecutorInterface() {
            @Override
            public void executeJob() {
                System.out.println("Job begin……");
                try {
                    Thread.sleep(2000+new Random().nextInt(1000));//模仿耗时
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Job end……");
            }
        });
        executor.start(1000);
    }
}
