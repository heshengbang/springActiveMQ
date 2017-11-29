package com.hsb.activemq.spring.producer.queue;/*
 * Copyright ©2011-2016 hsb
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * 队列消息生产者，发送消息到队列
 */
@Component("queueSender")
public class QueueSender {

    private final JmsTemplate jmsTemplate;//通过@Qualifier修饰符来注入对应的bean

    @Autowired
    public QueueSender(@Qualifier("jmsQueueTemplate") JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * 发送一条消息到指定的队列（目标）
     * @param queueName 队列名称
     * @param message 消息内容
     */
    public void send(String queueName,final String message){
        jmsTemplate.send(queueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

}
