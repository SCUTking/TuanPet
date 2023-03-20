package com.tuanpet.common.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tuanpet.common.commonVo.SocialUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.HashMap;

@Slf4j
public class JwtUtil {


    /**
     * 获取微信Api返回的SocialUser
     * @param code
     * @return
     */
    public static SocialUser getSocialUser(String code) throws Exception {
        String appid="wx72cce02fe11dc39f";
        String secret="9240e85c149b9707884611f4c6e9cbce";
        String js_code=code;
        String grant_type="authorization_code";
        HashMap<String, String> map = new HashMap<>();
        map.put("appid","wx72cce02fe11dc39f");
        map.put("secret","9240e85c149b9707884611f4c6e9cbce");
        map.put("js_code",code);
        log.info("code:"+code);
        map.put("grant_type","authorization_code");

        //获取openId，session_key
        HttpResponse post = HttpUtils.doGet("https://api.weixin.qq.com", "/sns/jscode2session", "get", null, map);
        //获取openID：{
        //    "session_key": "O8RH9G8Ffjc+QdXcyYtUSg==",
        //    "openid": "ouVcW40tfsrif3vsCzfF7Er4jNm8",
        //    "unionid": "o9XLI6E9JQ_iNaoJSDquzZB3QfIg"}
        //}
        //请求发送成功
        log.info(post.toString());
        SocialUser socialUser=null;
        if(post.getStatusLine().getStatusCode()==200){
            log.info(post.getEntity().toString());
            String json = EntityUtils.toString(post.getEntity());
            log.info(json);
            socialUser= JSON.parseObject(json, SocialUser.class);
        }
        return socialUser;
    }

    /**
     * 生成token
     * @param openid
     * @param session_key
     * @return
     */
    public static String getToken(String openid,String session_key){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);

        String token = JWT.create()
                .withHeader(new HashMap<>())  // Header
                .withClaim("openid", openid)  // Payload
                .withClaim("session_key", session_key)
                .withExpiresAt(calendar.getTime())  // 过期时间
                .sign(Algorithm.HMAC256("!34ADAS"));  // 签名用的secret

        return token;
    }


    /**
     *     解析出token中的openid
     */
    public static String getOpenIdByToken(String token){
        DecodedJWT decode = JWT.decode(token);
        String openid = decode.getClaim("openid").asString();
        if(StringUtils.hasText(openid)){
            return openid;
        }
        return null;

    }


}
