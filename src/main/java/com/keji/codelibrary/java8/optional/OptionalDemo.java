package com.keji.codelibrary.java8.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author keji
 * @version $Id: OptionalDemo.java, v 0.1 2018-10-20 16:57 keji Exp $$
 */
public class OptionalDemo {

    public static void main(String[] args) {

        create();

        Optional<Integer> optional = Optional.of(1);
        Optional<Object> emptyOptional = Optional.empty();

        User user = new User("李四", 20);
        Optional<User> userOptional = Optional.of(new User("李四", 20));
        Optional<Integer> optionalInteger = userOptional.map(User::getAge);
        System.out.println(optionalInteger);

        Optional<Long> optionalLong = optional.map(Integer::longValue);

        Optional<User> user1 = userOptional.flatMap(e -> {
            if (e.getAge() > 19) {
                return Optional.of(new User("张三", 21));
            } else {
                return Optional.of(new User("王五", 18));
            }
        });

        System.out.println(user1.get().getName());




        Optional<Integer> optionalO = optional.filter(e -> e.equals(1));
        Optional<Object> optionalO1 = emptyOptional.filter(e -> e.equals(1));

        System.out.println(optionalO);
        System.out.println(optionalO1);

        Optional<Long> longOptional = Optional.ofNullable(2L);
        System.out.println(optional);
        System.out.println(emptyOptional);
        System.out.println(longOptional);

        boolean isPresent = optional.isPresent();
        boolean emptyOptionalPresent = emptyOptional.isPresent();

        optional.ifPresent(e -> System.out.println((Integer)e + 1));

        Object orElse = optional.orElse(2);
        Object orElse2 = emptyOptional.orElse(2);



        List<User> userList = Arrays.asList(new User("李四", 20), new User("张三", 18), new User("王五", 25));

        //User user = userList.stream().filter(e -> e.getAge() > 25).findFirst().orElse(new User("王老五", 30));
        //System.out.println(user.getName());

        Optional<List<User>> optionalUserList = Optional.of(userList);
        Optional<Integer> two = Optional.of(2);
        Optional<Integer> three = Optional.of(3);

        Optional<List<User>> integer = optionalUserList.filter(e -> e.size() > 0);
        System.out.println(integer);

        String name = "王老五";
        Object orElseGet = emptyOptional.orElseGet(name::length);

        Object orElseThrow = emptyOptional.orElseThrow(()->new RuntimeException("程序异常"));

        System.out.println(orElseGet);
        //System.out.println(user.getName());

        Optional<User> optionalUser = userList.stream().filter(e -> e.getAge() > 18).findFirst();
        boolean present = optionalUser.isPresent();



        System.out.println(orElse);
        System.out.println(orElse2);

    }

    private static void create() {

        Optional<Object> emptyOptional = Optional.empty();
        System.out.println(emptyOptional);
    }

}