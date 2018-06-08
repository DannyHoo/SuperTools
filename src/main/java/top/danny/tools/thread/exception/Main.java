package top.danny.tools.thread.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author huyuyang@lxfintech.com
 * @Title: Main
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2018-01-22 16:16:32
 */
public class Main {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new CatchExceptionHandler());
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread1());
        exec.execute(new ExceptionThread2());
    }
}
