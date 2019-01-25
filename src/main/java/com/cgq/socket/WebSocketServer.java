package com.cgq.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by 1 on 2018/9/20.
 */
@Component
@ServerEndpoint("/endpointYyy/{sid}")
public class WebSocketServer {
    Logger logger= LoggerFactory.getLogger(WebSocketServer.class);


    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    private Session session;

    private String sid="";

    @OnOpen
    public void onOpen(Session session, @PathParam("sid")String sid){
        this.session=session;
        webSocketSet.add(this);
        addOnLineCount();
        logger.info("开始连接-------------"+sid+"连接数为---------------"+getOnLineCount());
        this.sid=sid;
        try{
            sendMessage("连接成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        subOnLineCount();
        logger.info("有一连接关闭！当前连接数为---"+getOnLineCount());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        logger.info("收到信息---"+sid+"----"+message);

        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws Exception {
        this.session.getBasicRemote().sendText(message);
    }
    public void sendMessage(Object o) throws Exception{
        this.session.getBasicRemote().sendObject(o);
    }
    public static synchronized int getOnLineCount() {
        return onlineCount;
    }

    public static synchronized void addOnLineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnLineCount() {
        WebSocketServer.onlineCount--;
    }


}
