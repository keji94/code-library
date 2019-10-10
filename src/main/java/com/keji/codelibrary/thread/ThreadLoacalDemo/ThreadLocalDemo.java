package com.keji.codelibrary.thread.ThreadLoacalDemo;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal保证不会出现竞争条件。ThreadLocal对象通常当做静态域存储。get()方法返回与其线程
 * 相关联的对象的副本，而set()会将参数插入到为其线程存储的对象中，并返回存储中原有的对象。
 *
 *
 * @author keji
 * @version $Id: ThreadLocalDemo.java, v 0.1 2018/12/13 5:05 PM keji Exp $
 */
public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(3);
        executorService.shutdownNow();
    }

}

class Accessor implements Runnable {

    private final int id;

    public Accessor(int idn){
        id = idn;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThradLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#" + id + ": " + ThradLocalVariableHolder.get();
    }
}

class ThradLocalVariableHolder{

    /**
     * 可以理解为 又建立了一个新的类,它继承于ThreadLocal,只是没有名字而已,大括号里面是重写ThreadLocal里面的方法
     */
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        private Random rand = new Random(47);

        @Override
        protected synchronized Integer initialValue() {
            return rand.nextInt(10000);
        }
    };

    public static void increment(){
        value.set(value.get() +1);
    }

    public static int get() {
        return value.get();
    }

}