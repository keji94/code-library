package com.keji.codelibrary.cache.redis;

import java.util.List;

/**
 * redis操作工具类
 *
 * @author wb-ny291824
 * @version $Id: RedisClient.java, v 0.1 2018-03-20 13:59 wb-ny291824 Exp $$
 */
public interface RedisClient {

    /**
     * 放缓存
     *
     * @param key   缓存的 key
     * @param value 缓存的value
     * @return 操作结果
     */
    String set(String key, String value);

    /**
     * 放缓存，并设置过期时间
     *
     * @param key   缓存的 key
     * @param value 缓存的value
     * @param seconds  过期时间(秒)
     * @return 操作结果
     */
    String set(String key, String value, int seconds);

    /**
     * 批量set 缓存
     *
     * @param keysvalues key value
     * @return 操作结果
     */
    String mSet(String... keysvalues);

    /**
     * 获取缓存
     *
     * @param key 缓存的key
     * @return 操作结果
     */
    String get(String key);

    /**
     * 批量获取
     *
     * @param keys keys
     * @return  List<String>
     */
    List<String> mGet(String... keys);
    /**
     * 是否存在当前key
     *
     * @param key 缓存的key
     * @return 操作结果
     */
    Boolean exists(String key);

    /**
     * 为已经存在的key设置过期时间
     *
     * @param key     缓存的key
     * @param seconds 秒
     * @return 操作结果
     */
    Long expire(String key, int seconds);

    /**
     * 查询当前key的过期时间，单位秒
     *
     * @param key 缓存的key
     * @return 操作结果
     */
    Long ttl(String key);

    /**
     * 将 key 中储存的数字值增一 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     *
     * @param key 缓存的key
     * @return 操作结果
     */
    Long incr(String key);

    /**
     * 将哈希表 key 中的域 field 的值设为 value，如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作
     *
     * @param key   缓存的key
     * @param field 域
     * @param value 缓存的value
     * @return 操作结果
     */
    Long hset(String key, String field, String value);

    /**
     * 返回哈希表 key 中给定域 field 的值。
     *
     * @param key   缓存的key
     * @param field 域
     * @return 操作结果
     */
    String hget(String key, String field);

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     * @param key 缓存的key
     * @param field 域
     * @return 操作结果
     */
    Long hdel(String key, String... field);

    /**
     * 使用list 列表数据结构，相当于LinkedList。从左只有Push value
     *
     * @param key key
     * @param values value
     * @return Long
     */
    Long rpush(String key,String... values);

    /**
     * 从左边取出列表数据。右边进，左边出(先进先出)队列
     *
     * @param key key
     * @return value
     */
    String lpop(String key);

    /**
     * 从右边取出列表数据
     *
     * @param key key
     * @return value 右边进，右边出(先进后出) 栈
     */
    String rpop(String key);
}
