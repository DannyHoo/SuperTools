package top.danny.tools.test.thread;

/**
 * @author huyuyang@lxfintech.com
 * @Title: MyTask
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-11 11:30:00
 */
public class MyThread implements Runnable {

    private StaticData staticData;

    public MyThread(StaticData staticData) {
        this.staticData = staticData;
    }

    @Override
    public void run() {
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        staticData.setCount(staticData.getCount() + 1);
    }

}
