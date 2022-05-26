package com.keji.codelibrary.arithmetic;

/**
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/12/16
 */
public class 进制转换 {

    public static void main(String[] args) {
        System.out.println(convertDecimal(101L,3));
    }

    private static long convertDecimal(Long num, Integer i) {

        StringBuilder str = new StringBuilder();

        long mod = num;
        long n;

        while (mod != 0) {
            mod = mod/i;
            n = mod % i;
            str.append(n);
        }

        return Long.parseLong(str.toString());
    }

}
