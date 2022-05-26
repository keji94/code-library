package com.keji.codelibrary.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/7/21
 */
public class ConditionDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        lock.lock();
        try {
            System.out.println("begin wait");
            condition.await();
            System.out.println("end wait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        Thread thread = new Thread(() -> condition.signal());

        thread.start();

        try {
            System.out.println("begin signal");
            condition.signal();
            System.out.println("end signal");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
