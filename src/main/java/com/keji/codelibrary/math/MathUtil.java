package com.keji.codelibrary.math;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * 数学工具类
 *
 * @author keji
 * @version $Id: MathUtil.java, v 0.1 2018-10-15 20:17 keji Exp $$
 */
public class MathUtil {

    public static void main(String[] args) {

        create();
        add();
        subtract();
        multiply();
        divide();
        compareTo();
    }

    private static void compareTo() {
        BigDecimal a = BigDecimal.valueOf(1);
        BigDecimal a1 = BigDecimal.valueOf(1);
        BigDecimal a2 = new BigDecimal(1);
        BigDecimal b = BigDecimal.valueOf(1.5);
        BigDecimal c = BigDecimal.valueOf(1.4);

        int i = a.compareTo(a1);
        int i1 = a.compareTo(a2);
        int i2 = a.compareTo(b);
        int i3 = b.compareTo(c);
        System.out.println(i);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
    }

    private static void divide() {
        BigDecimal a = BigDecimal.valueOf(5);
        BigDecimal b = BigDecimal.valueOf(3);

        BigDecimal divide = a.divide(b, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("divide: "+divide);
    }

    private static void multiply() {
        BigDecimal a = BigDecimal.valueOf(1);
        BigDecimal b = BigDecimal.valueOf(1.5);
        BigDecimal c = BigDecimal.valueOf(1.4);

        BigDecimal multiply = a.multiply(b);
        BigDecimal multiply1 = a.multiply(b,new MathContext(2));
        BigDecimal multiply2 = a.multiply(b,new MathContext(1));
        BigDecimal multiply3 = a.multiply(c,new MathContext(1));

        System.out.println("multiply: "+multiply);
        System.out.println("multiply1: "+multiply1);
        System.out.println("multiply2: "+multiply2);
        System.out.println("multiply3: "+multiply3);
    }

    private static void subtract() {
        BigDecimal a = BigDecimal.valueOf(1);
        BigDecimal b = BigDecimal.valueOf(1.5);
        BigDecimal c = BigDecimal.valueOf(1.4);

        BigDecimal subtract = a.subtract(b);
        BigDecimal subtract1 = a.subtract(b,new MathContext(2));
        BigDecimal subtract2 = a.subtract(b,new MathContext(1));
        BigDecimal subtract3 = a.subtract(c,new MathContext(1));

        System.out.println("subtract: "+subtract);
        System.out.println("subtract1: "+subtract1);
        System.out.println("subtract2: "+subtract2);
        System.out.println("subtract3: "+subtract3);
    }

    private static void add() {
        BigDecimal a = BigDecimal.valueOf(1);
        BigDecimal b = BigDecimal.valueOf(1.5);
        BigDecimal c = BigDecimal.valueOf(1.4);

        BigDecimal addResult = a.add(b);
        BigDecimal addResult1 = a.add(b,new MathContext(2));
        BigDecimal addResult2 = a.add(b,new MathContext(1));
        BigDecimal addResult3 = a.add(c,new MathContext(1));

        System.out.println("addResult: "+addResult);
        System.out.println("addResult1: "+addResult1);
        System.out.println("addResult2: "+addResult2);
        System.out.println("addResult3: "+addResult3);
    }

    private static void create() {

        BigDecimal a = new BigDecimal(1);
        BigDecimal a1 = BigDecimal.valueOf(1);

        BigDecimal b = new BigDecimal(1.1);
        BigDecimal b1 = BigDecimal.valueOf(1.1);

        int i = b.compareTo(b1);

        System.out.println("new的值a: "+a);
        System.out.println("valueOf()的值a1: "+a1);
        System.out.println("new的值b: "+b);
        System.out.println("valueOf()的值b1: "+b1);
        System.out.println("i:"+i);

    }

}
