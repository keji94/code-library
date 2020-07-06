package com.keji.codelibrary.volatileDemo;

/**
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/6/17
 */
public class VolatileDemo {

    volatile long count = 0L;

    public void set(long l) {
        count = l;
    }

    public void getAndIncrement() {
        count++;
    }

    public long get() {
        return count;
    }

}

class VolatileDemo2 {
    long count = 0L;

    public synchronized void set(long l) {
        count = l;
    }

    public void getAndIncrement() {
        long temp = get();
        temp += 1L;
        set(temp);
    }

    public synchronized long get() {
        return count;
    }

}
