package com.tuanpet.websocket.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{activityThoughtId}/{username}")
@Log4j2
public class WebSocket {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 在线人数
     */
    public static int onlineNumber = 0;
    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();

    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<String,Map<String, WebSocket>> highClients = new ConcurrentHashMap<String,Map<String, WebSocket>>();
    /**
     * 会话
     */
    private Session session;
    /**
     * 用户名称
     */
    private String username;


    /**
     * 活动笔记ID
     */
    private String activityThoughtId;

    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("activityThoughtId") String activityThoughtId,
                       @PathParam("username") String username,
                       Session session) {
        onlineNumber++;
        logger.info("现在来连接的客户id：" + session.getId() + "用户名：" + username+"活动笔记ID"+activityThoughtId);
        this.username = username;
        this.session = session;
        this.activityThoughtId=activityThoughtId;
        logger.info("有新连接加入！ 当前在线人数" + onlineNumber);
        try {
            //messageType 1代表上线 2代表下线 3代表在线名单 4代表普通消息
            //先给所有人发送通知，说我上线了
            Map<String, Object> map1 = new HashMap<>();
            map1.put("messageType", 1);
            map1.put("username", username);
            sendMessageAll(JSON.toJSONString(map1), username);

            //把自己的信息加入到map当中去
            clients.put(username, this);

            //构建map
            boolean containsKey = highClients.containsKey(activityThoughtId);
            if(containsKey){
                //这个活动笔记有人在线
                highClients.get(activityThoughtId).put(username,this);
            }
            else{
                //这个活动笔记没有人在线
                ConcurrentHashMap<String, WebSocket> tempMap = new ConcurrentHashMap<>();
                tempMap.put(username,this);
                highClients.put(activityThoughtId,tempMap);
            }



            //给自己发一条消息：告诉自己现在都有谁在线
            Map<String, Object> map2 =new HashMap<>();
            map2.put("messageType", 3);
            //移除掉自己
            Set<String> set = clients.keySet();
            map2.put("onlineUsers", set);
            sendMessageTo(JSON.toJSONString(map2), username);
        } catch (IOException e) {
            logger.info(username + "上线的时候通知所有人发生了错误");
        }


    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("服务端发生了错误" + error.getMessage());
//        error.printStackTrace();
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
        //webSockets.remove(this);
        clients.remove(username);

        //高级的删除
        Map<String, WebSocket> eachMap = highClients.get(activityThoughtId);
        eachMap.remove(username);



        try {
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String, Object> map1 = new HashMap<>();
            map1.put("messageType", 2);
            map1.put("onlineUsers", clients.keySet());
            map1.put("username", username);
            sendMessageAll(JSON.toJSONString(map1), username);
        } catch (IOException e) {
            logger.info(username + "下线的时候通知所有人发生了错误");
        }
        logger.info("有连接关闭！ 当前在线人数" + onlineNumber);
    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            logger.info("来自客户端消息：" + message + "客户端的id是：" + session.getId());
            JSONObject jsonObject = JSON.parseObject(message);
            String textMessage = jsonObject.getString("message");
            String fromusername = jsonObject.getString("username");
            String activityThought = jsonObject.getString("activityThought");
            String tousername = jsonObject.getString("to");
            //如果不是发给所有，那么就发给某一个人
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String, Object> map1 = new HashMap<>();
            map1.put("messageType", 4);
            map1.put("textMessage", textMessage);
            map1.put("fromusername", fromusername);
            if (tousername.equals("All")) {
                //发送给全部人
                map1.put("tousername", "所有人");
                sendMessageAll(JSON.toJSONString(map1), fromusername);
            }
            else if(activityThought!=null){
                map1.put("activityThought", activityThought);
                sendMessageToActivityThought(JSON.toJSONString(map1),activityThought);
            }
            else {
                //发送给个人
                map1.put("tousername", tousername);
                sendMessageTo(JSON.toJSONString(map1), tousername);
            }
        } catch (Exception e) {

            logger.info("发生了错误:"+e.getMessage());
        }

    }


    public void sendMessageTo(String message, String ToUserName) throws IOException {
        for (WebSocket item : clients.values()) {
            if (item.username.equals(ToUserName)) {
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }

    public void sendMessageAll(String message, String FromUserName) throws IOException {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageToActivityThought(String message,String activityThoughtId) throws IOException {
        Map<String, WebSocket> stringWebSocketMap = highClients.get(activityThoughtId);
        for (WebSocket item : stringWebSocketMap.values()) {
            item.session.getAsyncRemote().sendText(message);
        }

    }


    public static synchronized int getOnlineCount() {
        return onlineNumber;
    }

}

