package com.keji.codelibrary.thread;

/**
 *
 * @author keji
 * @version $Id: RunAndStartDemo.java, v 0.1 2018-12-27 10:47 keji Exp $
 */
public class RunAndStartDemo {

    public static void main(String[] args) {
        Thread printNum = new Thread(new PrintNum(),"打印数字线程");
        //printNum.start();
        //System.out.println("start 方法已经返回,当前线程name="+Thread.currentThread().getName());
        System.out.println("调用run方法开始,当前线程name="+Thread.currentThread().getName());
        printNum.run();
        System.out.println("调用run方法结束,当前线程name="+Thread.currentThread().getName());

    }
}


class PrintNum implements Runnable{

    @Override
    public void run() {
        System.out.println("任务开始,当前线程name="+Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        System.out.println("任务结束,当前线程name="+Thread.currentThread().getName());
    }
}
