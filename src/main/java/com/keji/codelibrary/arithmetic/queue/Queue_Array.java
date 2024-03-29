package com.keji.codelibrary.arithmetic.queue;

/**
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/12/28
 */
public class Queue_Array implements Queue {
    public static final int CAPACITY = 1000;//数组的默认容量
    protected int capacity;//数组的实际容量
    protected Object[] Q;//对象数组
    protected int f = 0;//队首元素的位置
    protected int r= 0;//队尾元素的位置

    //构造方法(空队列)
    public Queue_Array() {
        this(CAPACITY);
    }

    //按指定容量创建对象
    public Queue_Array(int cap) {
        capacity = cap;
        Q = new Object[capacity];
    }

    //查询当前队列的规模
    @Override
    public int getSize() {
        return (capacity - f + r) % capacity;
    }
    //判断队列是否为空
    public boolean isEmpty() {
        return (f == r);
    }

    //入队
    @Override
    public void enqueue(Object obj) throws RuntimeException {
        if (getSize() == capacity - 1) {
            throw new RuntimeException("Queue overflow.");
        }
        Q[r] = obj;
        r = (r + 1) % capacity;
    }

    //出队
    @Override
    public Object dequeue() {
        Object elem;
        if (isEmpty()) {
            throw new RuntimeException("意外:队列空");
        }
        elem = Q[f];
        Q[f] = null;
        f = (f + 1) % capacity;
        return elem;
    }

    //取(并不删除)队首元素
    @Override
    public Object front() throws RuntimeException {
        if (isEmpty()) {
            throw new RuntimeException("意外:队列空");
        }
        return Q[f];
    }

    //遍历(不属于ADT)
    @Override
    public void Traversal() {
        for (int i = f; i < r; i++) {
            System.out.print(Q[i] + " ");
        }
        System.out.println();
    }
}
