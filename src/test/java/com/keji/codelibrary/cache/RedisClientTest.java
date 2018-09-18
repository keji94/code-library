package com.keji.codelibrary.cache;

import java.util.List;

import com.keji.codelibrary.CodeLibraryApplicationTests;
import com.keji.codelibrary.cache.redis.RedisClient;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Strings;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

/**
 * Copyright (c) 2018 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2018/8/22
 */

public class RedisClientTest extends CodeLibraryApplicationTests {

    @Autowired
    private RedisClient redisClient;

    @Test
    public void testRedisClient() {

        redisClient.mSet("name1", "张三", "name2", "李四");

        String name1 = redisClient.get("name1");
        System.out.println(name1);

        List<String> strings = redisClient.mGet("name1", "name2");
        System.out.println(strings);

        Long rpush = redisClient.rpush("list", "value1", "value2", "value3");
        System.out.println(rpush);

        String value;
        while (StringUtils.isNotEmpty(value = redisClient.lpop("list"))) {
            System.out.println(value);
        }

        System.out.println(redisClient.lpop("list"));
        System.out.println(redisClient.lpop("list"));
        System.out.println(redisClient.lpop("list"));
        System.out.println(redisClient.lpop("list"));

    }

}
