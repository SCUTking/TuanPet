package com.tuanpet.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
class TuanPegUserApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testGenerateToken(){
        // 指定token过期时间为10秒
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);

        String token = JWT.create()
                .withHeader(new HashMap<>())  // Header
                .withClaim("userId", 21)  // Payload
                .withExpiresAt(calendar.getTime())  // 过期时间
                .sign(Algorithm.HMAC256("!34ADAS"));  // 签名用的secret

        System.out.println(token);
    }
    @Test
    public void testGet() throws IOException {
        /**
         * 1.实例化HttpClient对象
         */
        HttpClient httpClient= HttpClients.createDefault();
        /**
         * 2.定义远程访问的url地址
         */
        String url="http://www.baidu.com";
        /**
         * 3.定义请求类型的对象
         */

        HttpGet httpGet=new HttpGet(url);

        /**
         * 4.发起http请求，获取响应结果
         */
        HttpResponse httpResponse=httpClient.execute(httpGet);
        /**
         * 5.对返回值结果进行校验，获取真实的数据信息
         */
        //404：找不到页面，406：参数异常，500：后端服务器异常，504：超时，502：访问网址不存在
        int status=httpResponse.getStatusLine().getStatusCode();
        if(status==200){
            //获取响应结果：
            HttpEntity entity=httpResponse.getEntity();
            String result= EntityUtils.toString(entity,"UTF-8");
            System.out.println(result);
        }
    }





}
