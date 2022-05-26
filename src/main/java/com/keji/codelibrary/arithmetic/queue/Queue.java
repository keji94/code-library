package com.keji.codelibrary.arithmetic.queue;

/**
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/12/23
 */
public interface Queue {

    public int getSize();//返回队列中元素数目 public boolean isEmpty();//判断队列是否为空

    public Object front() ; //取队首元素(但不删除)

    public void enqueue(Object obj) ;//入队

    public Object dequeue();//出队

    public void Traversal();//遍历
}
