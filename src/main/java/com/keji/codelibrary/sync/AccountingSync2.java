package com.keji.codelibrary.sync;

import java.util.Vector;

/**
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/7/1
 */
public class AccountingSync2 implements Runnable{

    static final AccountingSync2 instance = new AccountingSync2();

    static int i = 0;

    public static synchronized void increase(){
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {



        //显示创建线程，两个线程都指向同一个Runnable接口实例(instance对象)
        Thread t1 = new Thread(new AccountingSync2());
        Thread t2 = new Thread(new AccountingSync2());

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    public String[] concatString() {
        Vector<String> vector = new Vector<>();
        for (int j = 0; j < 100; j++) {
            vector.add(Integer.toString(i));
        }
        return vector.toArray(new String[]{});
    }
}

