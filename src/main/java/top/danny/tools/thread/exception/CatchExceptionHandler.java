package top.danny.tools.thread.exception;

/**
 * @author huyuyang@lxfintech.com
 * @Title: CatchExceptionHandler
 * @Copyright: Copyright (c) 2016
 * @Description: 为捕获线程异常处理的类
 * @Company: lxjr.com
 * @Created on 2018-01-22 16:15:14
 */
public class CatchExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable e) {
        System.out.println("线程" + thread + "捕获到了异常，捕获的异常对象为：" + e);
    }

}
