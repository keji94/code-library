package com.keji.codelibrary.thread.CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.BiConsumer;

import com.google.common.collect.Lists;
import com.keji.codelibrary.threadpool.CustomThreadPoolExecutor;

/**
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/6/16
 */
public class CompletableFutureAllOfTest {

    public static void main(String[] args) throws Exception {
        CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
        // 1.初始化
        ThreadPoolExecutor executor = exec.init();
        //method1(executor);
        //method2(executor);
        method3(executor);
    }

    /**
     * 拆解写法
     *
     * @param executor
     */
    public static void method1(ExecutorService executor) {
        long start = System.currentTimeMillis();
        // 定义第一个任务
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "cf1";
        }, executor);

        cf1.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String t, Throwable u) {
                System.out.println("hello " + t);
            }
        });

        // 定义第二个任务
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "cf2";
        }, executor);

        cf2.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String t, Throwable u) {
                System.out.println("hello " + t);
            }
        });
        // 开始等待所有任务执行完成
        CompletableFuture<Void> all = CompletableFuture.allOf(cf1, cf2);
        System.out.println("start block");
        all.join();
        System.out.println("block finish, consume time:" + (System.currentTimeMillis() - start));
    }

    /**
     * 合并写法
     *
     * @param executor
     */
    public static void method2(ExecutorService executor) {
        List<String> testList = Lists.newArrayList();
        testList.add("cf1");
        testList.add("cf2");
        long start = System.currentTimeMillis();
        CompletableFuture<Void> all = null;
        for (String str : testList) {
            // 定义任务
            CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return str;
            }, executor);

            cf.whenComplete(new BiConsumer<String, Throwable>() {
                @Override
                public void accept(String t, Throwable u) {
                    System.out.println("hello " + t);
                }
            });
            all = CompletableFuture.allOf(cf);
        }
        System.out.println("start block");
        // 开始等待所有任务执行完成
        all.join();
        System.out.println("block finish, consume time:" + (System.currentTimeMillis() - start));
    }

    /**
     * 通过Java8的stream实现，非常简洁
     *
     * @param executor
     */
    @SuppressWarnings("rawtypes")
    public static void method3(ExecutorService executor) {
        List<String> testList = Lists.newArrayList();
        testList.add("cf1");
        testList.add("cf2");
        long start = System.currentTimeMillis();
        CompletableFuture[] cfArr = testList.stream().
                map(t -> CompletableFuture.supplyAsync(() -> pause(t), executor).whenComplete((result, th) -> {
                    System.out.println("hello" + result);
                })).toArray(CompletableFuture[]::new);
        // 开始等待所有任务执行完成
        System.out.println("start block");
        CompletableFuture.allOf(cfArr).join();
        System.out.println("block finish, consume time:" + (System.currentTimeMillis() - start));
    }

    public static String pause(String name) {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;
    }
}
