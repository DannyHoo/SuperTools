package top.danny.tools.thread.consumeTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author huyuyang@lxfintech.com
 * @Title: Executor
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-11-21 22:35:36
 */
public class Executor {

    private ExecutorInterface executorInterface;
    private ExecutorService threadPool = Executors.newCachedThreadPool();
    private List<FutureTask<Long>> futureTasks;
    private long[] timeConsume;//每个线程耗时时间
    private int threadNum;

    public Executor(ExecutorInterface executorInterface) {
        this.executorInterface = executorInterface;
    }

    public void start(int threadNum) {
        //初始化相关容器
        futureTasks = new ArrayList<>(threadNum);
        //每个线程耗时记录
        timeConsume = new long[threadNum];
        //线程池
        threadPool = Executors.newCachedThreadPool();
        long startTime = System.currentTimeMillis();
        long timeConsumeTotal = 0;

        //创建、添加、执行任务
        for (int i = 0; i < threadNum; i++) {
            addCallable(i);
            System.out.println("put完毕：" + i);
        }

        //输出每个任务耗时情况
        for (int i = 0; i < threadNum; i++) {
            try {
                timeConsumeTotal += timeConsume[i] = futureTasks.get(i).get();
                System.out.println("第" + i + "个任务执行完毕，耗时：" + timeConsume[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        sort(timeConsume);
        System.out.println("总耗时：" +(System.currentTimeMillis()-startTime));
        System.out.println("平均耗时：" + (timeConsumeTotal/threadNum));
        System.out.println("最大耗时：" + timeConsume[threadNum-1]);
        System.out.println("最小耗时：" + timeConsume[0]);
        //关闭线程池
        threadPool.shutdown();
    }

    public Thread addCallable(int index) {
        Callable<Long> callable = new Callable<Long>() {
            public Long call() throws Exception {
                long startTime = System.currentTimeMillis();

                executorInterface.executeJob();

                long endTime = System.currentTimeMillis();
                long consumeTime = endTime - startTime;
                return consumeTime;
            }
        };
        FutureTask<Long> future = new FutureTask<Long>(callable);
        futureTasks.add(future);
        Thread thread = new Thread(future);
        thread.start();
        return thread;
    }

    public static long[] sort(long[] args) {//插入排序算法
        for (int i = 1; i < args.length; i++) {
            for (int j = i; j > 0; j--) {
                if (args[j] < args[j - 1]) {
                    long temp = args[j - 1];
                    args[j - 1] = args[j];
                    args[j] = temp;
                } else break;
            }
        }
        return args;
    }
}
