package top.danny.tools.net;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 访问csdn博客，增加访问量，纯属娱乐
 * 采用多线程，增加访问速度
 */
public class CSDNBlog {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            RequestBlogRun conn = new RequestBlogRun();
            Thread t = new Thread(conn);
            t.start();
        }
    }

    /**
     * 构造对博客的请求
     *
     */
    public static class RequestBlogRun implements Runnable {
        public static final String BLOG_URL = "https://blog.csdn.net";
        //请求的阻塞队列
        private BlockingQueue<HttpURLConnection> bq = new ArrayBlockingQueue<HttpURLConnection>(2);
        //请求的线程池
        private ExecutorService service = Executors.newFixedThreadPool(3);

        @Override
        public void run() {
            boolean flag = true;
            while (flag) {
                try {
                    URLConnection conn = URI.create(BLOG_URL).toURL().openConnection();
                    conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
                    conn.setConnectTimeout(20000);
                    HttpURLConnection httpConn = (HttpURLConnection) conn;
                    bq.put(httpConn);
                    service.execute(new ConnBlog(bq));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 
     *实际访问blog
     */
    public static class ConnBlog implements Runnable {
        private static int i = 0;
        private BlockingQueue<HttpURLConnection> bq;
        public ConnBlog(BlockingQueue<HttpURLConnection> bq) {
            this.bq = bq;
        }
        public void run() {
            HttpURLConnection conn = null;
            try {
                conn = bq.take();
                System.out.println("responseCode:"+conn.getResponseCode());
                i++;
                System.out.println("times:" + i);
                if(i > 1000000){
                    //为了提高效率，没有加锁，次数有出入
                    System.out.println("program finshed,will exit!");
                    System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conn.disconnect();
                conn.setDoOutput(false);
                conn=null;
            }
        }
    }
}