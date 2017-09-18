package com.hsb.activemq.spring.consumer.topic;/*
 * Copyright ©2011-2016 hsb
 */

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


/**
 * Topic消息监听器
 */
@Component
public class TopicReceiver1 implements MessageListener{


    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("TopicReceiver1接收到消息："+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}

