package com.keji.codelibrary.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author keji
 * @version $Id: HashCodeAndEquals.java, v 0.1 2018/12/12 6:40 PM keji Exp $
 */
public class HashCodeAndEquals {

    public static void main(String[] args) {
        // 新建Person对象，
        User p1 = new User(100,"eee");
        User p2 = new User(100,"eee");
        User p3 = new User(200,"aaa");

        ArrayList<User> list1 = new ArrayList<>();
        list1.add(p1);
        list1.add(p2);
        list1.add(p3);
        ArrayList<User> list2 = new ArrayList<>();
        list2.add(p1);
        list2.add(p2);
        list2.add(p3);
        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        System.out.printf("p1.equals(p3) : %s; p1(%d) p3(%d)\n", p1.equals(p3), p1.hashCode(), p3.hashCode());
        System.out.printf("list1.equals(list2) :%s\n",list1.equals(list2));


        HashSet<User> hashSet = new HashSet<>();
        hashSet.add(p1);
        hashSet.add(p2);
        hashSet.add(p3);

        System.out.printf("p1.equals(p2) : %s; p1(%d) p2(%d)\n", p1.equals(p2), p1.hashCode(), p2.hashCode());
        System.out.printf("set:%s\n", hashSet);
    }

    private static class User{

        private int age;

        private String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User)o;
            return age == user.age && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name);
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }
}
