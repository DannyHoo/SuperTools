package top.danny.tools.thread.exception;

/**
 * @author huyuyang@lxfintech.com
 * @Title: ExceptionThread2
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2018-01-22 16:14:40
 */
public class ExceptionThread1 implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
