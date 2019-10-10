package com.keji.codelibrary.basic;

/**
 * Copyright (c) 2019 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2019-05-12
 */
public class StaticDemo extends ParentStaticDemo {

    static {
        System.out.println("static block run");
    }

    public StaticDemo() {
        System.out.println("child constructor run");
    }

    public static void main(String[] args) {
        new StaticDemo();
    }

}

class ParentStaticDemo {

    static {
        System.out.println("parent static block run ");
    }

    public ParentStaticDemo() {
        System.out.println("parent constructor run");
    }
}
