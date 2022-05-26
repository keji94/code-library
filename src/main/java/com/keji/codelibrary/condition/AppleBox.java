package com.keji.codelibrary.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有一个苹果箱，有10个人向这个箱子中每次随机放入一个苹果，有10个人每次随机从这个箱子中随机拿走一个苹果，同时需要满足箱子中的苹果总数不能超过50个。请用代码实现上面的场景（不能使用并发集合框架）
 *
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/7/21
 */
public class AppleBox {

    /**
     * 苹果个数
     */
    private int appleCount;
    /**
     * 锁
     */
    private static final Lock LOCK = new ReentrantLock();
    /**
     * 满箱条件
     */
    private static final Condition PROVIDER_CONDITION = LOCK.newCondition();
    /**
     * 空箱条件
     */
    private static final Condition CONSUMER_CONDITION = LOCK.newCondition();

    public void providerApple() {
        //获取独占锁
        LOCK.lock();
        try {
            //当箱子个数大于10，等待
            while (appleCount >= 10) {
                try {
                    PROVIDER_CONDITION.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //小于等于10，往箱子放苹果
            appleCount++;
            System.out.println("放入一个,当前盒子中苹果数:" + appleCount);
            //唤醒消费者消费苹果
            CONSUMER_CONDITION.signalAll();
        } finally {
            //释放独占锁
            LOCK.unlock();
        }
    }

    public void consumerApple() {
        //获取独占锁
        LOCK.lock();
        try {
            //如果苹果数量<=0停止消费
            while (appleCount <= 0) {
                try {
                    CONSUMER_CONDITION.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //数量>=0，消费
            appleCount--;
            System.out.println("拿走一个,当前盒子中苹果数:" + appleCount);
            //唤醒生产者生产苹果
            PROVIDER_CONDITION.signalAll();
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * 消费者
     */
    private static class AppleConsumer implements Runnable {

        private final AppleBox appleBox;

        public AppleConsumer(AppleBox appleBox) {
            this.appleBox = appleBox;
        }

        @Override
        public void run() {
            while (true) {
                appleBox.consumerApple();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class AppleProvider implements Runnable {

        private final AppleBox appleBox;

        public AppleProvider(AppleBox appleBox) {
            this.appleBox = appleBox;
        }

        @Override
        public void run() {
            while (true) {
                appleBox.providerApple();
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String[] args) {
        AppleBox appleBox = new AppleBox();

        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(new AppleProvider(appleBox));
            t.setName("AppleProvider:" + i);
            t.start();
        }

        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(new AppleConsumer(appleBox));
            t.setName("AppleConsumer:" + i);
            t.start();
        }

    }
}


