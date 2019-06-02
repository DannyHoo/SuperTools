package top.danny.tools.net.httpclient;

import com.alibaba.fastjson.JSONObject;
import top.danny.tools.thread.consumeTime.Executor;
import top.danny.tools.thread.consumeTime.ExecutorInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Danny
 * @Title: HttpPost
 * @Description:
 * @Created on 2019-03-09 12:37:44
 */
public class HttpPost {

    public static void main1(String[] args) {

        Executor executor = new Executor(new ExecutorInterface() {
            @Override
            public void executeJob() throws IOException {
                for (int i = 0; i < 10000; i++) {
                    JSONObject param = getJSONObject();
                    String result = HttpClientUtils.doGet("http://www..com", "");
                    System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " 请求结果" + result);
                }
            }
        });
        executor.start(1000);
    }

    public static void main(String[] args) {
        Executor executor = new Executor(new ExecutorInterface() {
            @Override
            public void executeJob() throws IOException {
                for (int i = 0; i < 2000; i++) {
                    JSONObject param = getJSONObject();
                    String result = "";
                    try {
                        HttpClientUtils.doPost("http://192.168.2.185:10010/app/order/createOrder", param, 180000);
                        System.out.println("请求结果" + JSONObject.parseObject(result).get("code"));
                    } catch (Exception e) {
                    }
                }
            }
        });
        executor.start(100);
    }

    public static void main3(String[] args) {
        JSONObject param = getJSONObject();
        String result = HttpClientUtils.doPost("http://192.168.2.185:10010/app/order/createOrder", param, 180000);
        System.out.println("请求结果" + JSONObject.parseObject(result).get("code"));
    }

    private static JSONObject getJSONObject() {
        JSONObject param = new JSONObject();

        List<JSONObject> jsonObjectList = new ArrayList<>();
        JSONObject listObject = new JSONObject();
        listObject.put("goodsNo", "G20180614160305682062");
        listObject.put("goodsNum", 1);
        jsonObjectList.add(listObject);

        param.put("userName", "82Z76oIu");
        param.put("orderDetailList", jsonObjectList);
        return param;
    }
}
