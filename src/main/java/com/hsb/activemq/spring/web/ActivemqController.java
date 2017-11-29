package com.hsb.activemq.spring.web;/*
 * Copyright ©2011-2016 hsb
 */

import com.hsb.activemq.spring.producer.queue.QueueSender;
import com.hsb.activemq.spring.producer.topic.TopicSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/springActiveMQ/activemq")
public class ActivemqController {

    @Resource
    private
    QueueSender queueSender;
    @Resource
    private
    TopicSender topicSender;

    /**
     * 发送消息到队列
     * Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
     *
     * @param message
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "queueSender", method = RequestMethod.POST)
    public String queueSender(@RequestParam("message") String message) {
        System.out.println("准备通过P2P方式发送消息：" + message);
        String index;
        try {
            queueSender.send("test.queue", message);
            index = "QUEUE";
        } catch (Exception e) {
            index = e.getCause().toString();
        }
        return index;
    }

    /**
     * 发送消息到主题
     * Topic主题 ：放入一个消息，所有订阅者都会收到
     * 这个是主题目的地是一对多的
     *
     * @param message
     * @return String
     */
    @ResponseBody
    @RequestMapping(value = "topicSender", method = RequestMethod.POST)
    public String topicSender(@RequestParam("message") String message) {
        System.out.println("准备通过PUB/SUB方式发送消息：" + message);
        String index;
        try {
            topicSender.send("test.topic", message);
            index = "TOPIC";
        } catch (Exception e) {
            index = e.getCause().toString();
        }
        return index;
    }

}
