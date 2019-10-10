package com.keji.codelibrary.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的几种方式：
 * 1.创建一个类继承Thread类。new 该类的对象即创建一个线程。调用start()方法启动线程
 * 2.实现Runnable接口。重写run()方法
 * 3.使用Callable和Future接口创建有返回值的线程
 *
 * @author wb-ny291824
 * @version $Id: CreateThreadDemo.java, v 0.1 2018-02-28 10:34 wb-ny291824 Exp $$
 */
public class CreateThreadDemo {
    public static void main(String[] args) throws Exception{
        //创建线程1
        MyThread myThread = new MyThread();

        //创建线程2
        MyThread2 myThread2 = new MyThread2();
        Thread thread2 = new Thread(myThread2, "使用Runnable接口创建的线程");

        //创建线程3
        MyThread3 myThread3 = new MyThread3();

        Thread thread = new Thread();
        thread.start();

        FutureTask<String> futureTask = new FutureTask<>(new CallerTask());
        Thread thread3 = new Thread(futureTask);
        //启动线程
        thread3.start();

        //任务执行完毕，返回结果
        String result = futureTask.get();
        System.out.println(result);
    }
}
class CallerTask implements Callable<String>{
    @Override
    public String call() throws Exception {
        return "hello";
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("线程1执行了");
    }
}

class MyThread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("线程2执行了");
    }
}



class MyThread3 implements Callable<Integer> {

    public static void main(String[] args) {
        MyThread3 myThread3 = new MyThread3();
        //使用FutureTask包装
        FutureTask<Integer> futureTask = new FutureTask<>(myThread3);
        for(int i = 0;i < 100;i++)
        {
            System.out.println(Thread.currentThread().getName()+" 的循环变量i的值"+i);
            if(i==20)
            {
                new Thread(futureTask,"有返回值的线程").start();
            }
        }
        try
        {
            System.out.println("子线程的返回值："+futureTask.get());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("线程3执行了");
        return 1;
    }
}
