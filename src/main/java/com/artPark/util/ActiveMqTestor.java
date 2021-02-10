package com.artPark.util;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @Author lbc on 2020/12/8  17:45.
 */
public class ActiveMqTestor {
    public static void activeMqProducer() throws URISyntaxException, JMSException, InterruptedException {
        ConnectionFactory cf = new ActiveMQConnectionFactory("admin","admin_joe","tcp://47.112.179.125:61616");
        Connection c = cf.createConnection();
        c.start();
        Session s = c.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
        Queue q = s.createQueue("HelloWorld");
        MessageProducer producer = s.createProducer(q);
        for (int i =0;i<100;i++){
            Message msg = s.createTextMessage("vampire "+i);
            producer.send(msg);
            Thread.sleep(5000);
        }
        s.close();
        c.close();
        System.out.println("已成功发送queue");
    }



    public static void main(String[] args) throws URISyntaxException, JMSException, IOException, InterruptedException {
        activeMqProducer();
    }
}
