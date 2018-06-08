package top.danny.tools.thread;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import top.danny.tools.net.httpclient.HttpHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: MyTask
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-09-18 11:20:17
 */
public class MyTask implements Runnable{

    public static void main(String[] args){
        List<NameValuePair> nameValuePairList=new ArrayList<>();
        nameValuePairList.add(new BasicNameValuePair("stm", "1505704415483"));
        String response="";
        long time=0;
        while(true){
            response=HttpHandler.sendPost("https://api.growingio.com/v2/8ce4221ed3d00df0/web/action?stm=1505704415483",nameValuePairList);
            System.out.println("第"+(time++)+"次访问");
        }
        //System.out.println(response);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        //执行业务逻辑
    }

}
