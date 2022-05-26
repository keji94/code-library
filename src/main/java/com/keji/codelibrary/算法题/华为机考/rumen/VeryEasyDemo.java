package com.keji.codelibrary.算法题.华为机考.rumen;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
 *
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/7/29
 */
public class VeryEasyDemo {

    public static void main(String[] args) {
        //计算二进制中的1();

        //getInt();
    }

    /**
     * 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
     * 输出描述：这个数转换成2进制后，输出1的个数
     */
    private static void demo1() {
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            int i = scanner.nextInt();
            String binaryString = Integer.toBinaryString(i);

            //for (int j = 0; j < binaryString.length(); j++) {
            //    if (binaryString.charAt(j) == '1') {
            //        count++;
            //    }
            //}

            for (char c : binaryString.toCharArray()) {
                if (c == '1') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    /**
     * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
     */
    private static void demo2() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            double d = scanner.nextDouble();
            BigDecimal bigDecimal = BigDecimal.valueOf(d);
            BigDecimal divide = bigDecimal.setScale(0,RoundingMode.HALF_UP);
            System.out.println(divide);
        }
    }
}
