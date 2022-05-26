package com.keji.codelibrary.thread.ThreadLoacalDemo;

import java.util.function.Supplier;

/**
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/7/27
 */
public class ThreadLocalDemo2 {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            threadLocal.set("我是"+Thread.currentThread().getName()+"的本地变量");
            System.out.println(threadLocal.get());
        });
        Thread threadTwo = new Thread(() -> {
            System.out.println("threadTwo set 前"+threadLocal.get());
            threadLocal.set("我是"+Thread.currentThread().getName()+"的本地变量");
            System.out.println("threadTwo set 后"+threadLocal.get());
        });

        threadOne.setName("线程一");
        threadTwo.setName("线程二");
        threadOne.start();
        threadTwo.start();
    }

    public void newThreadLocal() {
        ThreadLocal<Object> threadLocal = ThreadLocal.withInitial(() -> "1");
    }
}
