package com.keji.codelibrary.java8.optional;

/**
 * @author keji
 * @version $Id: User.java, v 0.1 2018-10-20 18:28 keji Exp $$
 */
public class User {

    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
