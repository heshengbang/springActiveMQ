package com.hsb.activemq.spring.producer.topic;/*
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

@Component("topicSender")
public class TopicSender {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public TopicSender(@Qualifier("jmsTopicTemplate") JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * 发送一条消息到指定的队列（目标）
     *
     * @param topicName 主题名
     * @param message   消息内容
     */
    public void send(String topicName, final String message) {
        jmsTemplate.send(topicName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

}
