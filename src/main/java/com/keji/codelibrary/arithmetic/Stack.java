package com.keji.codelibrary.arithmetic;

/**
 * 数据结构 栈
 *
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/12/18
 */
public interface Stack<T> {

    /**
     * 弹栈
     *
     * @return Object
     */
    T pop();

    /**
     * push
     *
     * @param element element
     * @return Boolean
     */
    Boolean push(T element);

    /**
     * 元素个数
     *
     * @return i
     */
    int size();

    /**
     * 取顶部元素，但不删除
     *
     * @return T
     */
    T top();

    /**
     * 是否为空
     *
     * @return Boolean
     */
    Boolean isEmpty();
}
