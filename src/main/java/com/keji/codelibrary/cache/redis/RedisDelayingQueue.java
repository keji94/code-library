package com.keji.codelibrary.cache.redis;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import redis.clients.jedis.Jedis;

/**
 * Copyright (c) 2018 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * redis延迟队列
 *
 * @author keji
 * @since 2018/8/27
 */
public class RedisDelayingQueue<T> {

    static class TaskItem<T> {
        private String id;

        private T msg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public T getMsg() {
            return msg;
        }

        public void setMsg(T msg) {
            this.msg = msg;
        }
    }

    /**
     * fastjson 序列化对象中存在 generic 类型时，需要使用 TypeReference
     */
    private Type taskType = new TypeReference<TaskItem<T>>(){}.getType();

    private Jedis jedis;
    private String queueKey;

    private RedisDelayingQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public void delay(T msg) {
        TaskItem<T> task = new TaskItem<>();
        task.setId(UUID.randomUUID().toString());
        task.setMsg(msg);
        String taskJson = JSON.toJSONString(task);
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, taskJson);
    }

    public void loop() {
        while (!Thread.interrupted()) {
            // 只取一条
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String s = values.iterator().next();
            if (jedis.zrem(queueKey, s) > 0) {
                // 抢到了
                TaskItem<T> task = JSON.parseObject(s, taskType);
                this.handleMsg(task.msg);
            }
        }
    }

    public void handleMsg(T msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        RedisDelayingQueue<String> queue = new RedisDelayingQueue<>(jedis, "q-demo");
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.delay("codehole" + i);
            }
        });
        Thread consumer = new Thread(() -> queue.loop());
        producer.start();
        consumer.start();
        try {
            producer.join();
            Thread.sleep(6000);
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
        }
    }

}
