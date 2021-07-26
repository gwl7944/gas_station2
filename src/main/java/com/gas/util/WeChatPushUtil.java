package com.gas.util;


import com.alibaba.fastjson.JSONObject;
import com.gas.pojo.Open;
import com.gas.pojo.Site;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: gas_station
 * @Package: com.gas.util
 * @ClassName: WeChatPushUtil
 * @Author: gwl
 * @Description:
 * @Date: 2021/3/22 15:31
 * @Version: 1.0
 */


public class WeChatPushUtil {

    public static Map<String,String> weChatGetOpenid(Open open){
        //System.out.println("open>>>"+open);
        //微信公众号获取openID
        String str = sendGet("https://api.weixin.qq.com/sns/oauth2/access_token","appid="+open.getAppId()+"&secret="+open.getSecret()+"&code="+open.getCode()+"&grant_type=authorization_code");
        //System.out.println("str>>>"+str);
        if (str!=null && !"".equals(str)){
            JSONObject jsonObject = JSONObject.parseObject(str);
            String openid = jsonObject.getString("openid");
            System.out.println("jsonObject》》》"+jsonObject);
            String access_token = jsonObject.getString("access_token");
            //获取微信用户信息
            String userInfo = sendGet("https://api.weixin.qq.com/sns/userinfo","access_token="+access_token+"&openid="+openid+"&lang=zh_CN");

            Map<String,String> map = new HashMap<>();
            map.put("openid",openid);
            map.put("userInfo",userInfo);
            return map;
        }else {
            return null;
        }

    }
    //https://api.weixin.qq.com/cgi-bin/token?grant_type= 'client_credential'&appid='wx111e40902b22b38e'&secret='66252beaa3351ddd1bdf18404baf3953'


    //获取小程序token并推送
    /*public static String weChatPush(String data){
        String token = getAccessToken();
        //将字符串转化为map集合
        JSONObject parseObject1 = JSONArray.parseObject(token);
        System.err.println(parseObject1);
        String accessToken = parseObject1.getString("access_token");
        //将消息内容推送到客户
        String str = sendPost("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+accessToken,data);
        return str;
    }*/


    //获取小程序token
    public static String getAccessToken(Site site){
        //通过微信公众号的AppId和AppSecret(当前ip地址必须在微信公众号的白名单内)获取微信公众号的access_token(7200秒有效)
        String site_appid = site.getSite_appid();
        String site_secret = site.getSite_secret();
        String site_code = site.getSite_code();
        String str1 = sendGet("https://api.weixin.qq.com/cgi-bin/token","grant_type=client_credential&"+"appid="+site_appid+"&secret="+site_secret+"&code="+site_code);
        return str1;
    }


    //获取小程序二维码
    public static byte[] getUnlimited(String data,String access_token){
        byte[] post = post("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+access_token, data);
        return post;
    }

    public static String getJsapiTicket(Open open) {
        //System.out.println("open>>>"+open);
        //微信公众号获取openID
        //String str = sendGet("https://api.weixin.qq.com/cgi-bin/token","appid="+open.getAppId()+"&secret="+open.getSecret()+"&grant_type=client_credential");
        String str = TokenSave.getToken(open);
        //System.out.println("str>"+str);

        JSONObject jsonObject = JSONObject.parseObject(str);
        String access_token = jsonObject.getString("access_token");
        String str1 = sendGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket","access_token="+access_token+"&type=jsapi");
        return str1;
    }


    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString =url+"?"+param;
            //System.out.println("urlNameString》》"+urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }



    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url,String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }




    /* 发送 post请求 用HTTPclient 发送请求*/
    public static byte[] post(String URL,String json) {
        String obj = null;
        InputStream inputStream = null;
        Buffer reader = null;
        byte[] data = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(URL);
        httppost.addHeader("Content-type", "application/json;charset=utf-8");
        httppost.setHeader("Accept", "application/json");
        try {
            StringEntity s = new StringEntity(json, Charset.forName("utf-8"));
            s.setContentEncoding("utf-8");
            httppost.setEntity(s);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                // 获取相应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    inputStream = entity.getContent();
                    data = readInputStream(inputStream);
                }
                return data;
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    /**  将流 保存为数据数组
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }

}
