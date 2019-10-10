package com.keji.codelibrary.basic;

import java.util.HashMap;

/**
 *
 * @author keji
 * @version $Id: HashMapDemo.java, v 0.1 2018/12/13 9:34 AM keji Exp $
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>(4);
        map.put(123, 123);
        map.put(1234, 123);
        map.put(12345, 123);
        map.put(123456, 1234);

    }
}
