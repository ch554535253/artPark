package com.artPark.util;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @Author lbc on 2020/12/9  14:51.
 */
public class ActiveMqConsumer {
    public static void activeMqConsumer() throws Exception {
        ConnectionFactory cf = new ActiveMQConnectionFactory("admin","admin_joe","tcp://47.112.179.125:61616");
        Connection c = cf.createConnection();
        c.start();
        Session s = c.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
        Queue q = s.createQueue("HelloWorld");
        MessageConsumer consumer = s.createConsumer(q);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    try{
                        System.out.println("你好！ "+((TextMessage) message).getText()+"当前线程ID："+Thread.currentThread().getId());
//                        message.acknowledge();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        System.out.println("总线程ID："+Thread.currentThread().getId());
        Thread.sleep(60000);
//        System.in.read();
        s.close();
        c.close();
    }

    public static void main(String[] args) throws Exception{
        activeMqConsumer();
    }
}
