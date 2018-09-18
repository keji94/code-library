package com.keji.codelibrary.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Jedis;

/**
 * Copyright (c) 2018 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * 掘进小册：https://juejin.im/book/5afc2e5f6fb9a07a9b362527/section/5afc2e5f51882542714ff291
 *
 * @author keji
 * @since 2018/8/22
 */
public class RedisDemo {


    public static void main(String[] args) {

        Jedis jedis = new Jedis();

        //列表
        list(jedis);
        //字典
        hash(jedis);
        //无序集合
        set(jedis);
        //有序字典
        zset(jedis);
        //位图（做签到）
        bit(jedis);
        //hyperLogLog
        hyperLogLog(jedis);
        //布隆过滤器
        bloomFilter(jedis);

    }

    /**
     *布隆过滤器可以理解为一个不怎么精确的 set 结构，当你使用它的 contains 方法判断某个对象是否存在时，它可能会误判。但是布隆过滤器也不是特别不精确，只要参数设置的合理，它的精确度可以控制的相对足够精确，只会有小小的误判概率。
     *当布隆过滤器说某个值存在时，这个值可能不存在；当它说不存在时，那就肯定不存在。
     * 场景：推送去重
     *
     *
     * @param jedis jedis
     */
    private static void bloomFilter(Jedis jedis) {

    }

    /**
     * 提供不精确的去重计数方案,标准误差是 0.81%，相比set可以节省很多空间。场景：统计网站UV。
     * 注意：
     *  1.占据12k的存储空间，如果用户上亿，成本很大
     *  2.也不必过于当心，因为 Redis 对 HyperLogLog 的存储进行了优化，在计数比较小时，它的存储空间采用稀疏矩阵存储，空间占用很小，仅仅在计数慢慢变大，稀疏矩阵占用空间渐渐超过了阈值时才会一次性转变成稠密矩阵，才会占用 12k 的空间。
     *
     * @param jedis jedis
     */
    private static void hyperLogLog(Jedis jedis) {

        jedis.del("codehole");
        jedis.del("codehole2");
        jedis.del("newColeHole");
        jedis.pfadd("codehole", "user1");
        long codeholeCount = jedis.pfcount("codehole");
        jedis.pfadd("codehole", "user2");
        long codeholeCount2 = jedis.pfcount("codehole");
        jedis.pfadd("codehole", "user3");
        long codeholeCount3 = jedis.pfcount("codehole");
        jedis.pfadd("codehole", "user4","user5","user6","user7");
        jedis.pfadd("codehole2", "user4","user5","user6","user7");
        long codeholeCount4 = jedis.pfcount("codehole");

        //将多个key的统计值合并成一个
        String pfmerge = jedis.pfmerge("newColeHole", "codehole", "codehole2");
        long newColeHole = jedis.pfcount("newColeHole");

        //原理实现
        hyperLogLogTheory();

    }

    private static void hyperLogLogTheory() {
        for (int i = 100000; i < 1000000; i += 100000) {
            Experiment exp = new Experiment(i);
            exp.work();
            double est = exp.estimate();
            System.out.printf("%d %.2f %.2f\n", i, est, Math.abs(est - i) / i);
        }
    }

    /**
     * 位图不是特殊的数据结构，它的内容其实就是普通的字符串，也就是 byte 数组。我们可以使用普通的 get/set 直接获取和设置整个位图的内容，也可以使用位图操作 getbit/setbit 等将 byte 数组看成「位数组」来处理。
     *
     * @param jedis jedis
     */
    private static void bit(Jedis jedis) {
        //零存整取,将hello转换为 ASCII 码 h:01101000 e:01100101
        jedis.setbit("s", 1, true);
        jedis.setbit("s", 2, true);
        jedis.setbit("s", 4, true);
        jedis.setbit("s", 9, true);
        jedis.setbit("s", 10, true);
        jedis.setbit("s", 13, true);
        jedis.setbit("s", 15, true);

        String s = jedis.get("s");

        //零存零取
        jedis.setbit("w", 1, true);
        jedis.setbit("w", 2, true);
        jedis.setbit("w", 4, true);

        Boolean w = jedis.getbit("w", 2);
        Boolean w1 = jedis.getbit("w", 4);
        Boolean w2 = jedis.getbit("w", 5);

        //整存零取
        jedis.set("w1", "h");
        Boolean w11 = jedis.getbit("w1", 1);
        Boolean w12 = jedis.getbit("w1", 2);
        Boolean w13 = jedis.getbit("w1", 4);
        Boolean w14 = jedis.getbit("w1", 5);

        //如果对应位的字节是不可打印字符，会返回该字符的16进制
        jedis.setbit("x", 0, true);
        jedis.setbit("x", 1, true);
        String x = jedis.get("x");

        //bitcount 和 bitpos bitcount 统计用户一共签到了多少天，通过 bitpos 指令查找用户从哪一天开始第一次签到。如果指定了范围参数[start, end](指定的位范围必须是 8 的倍数)，
        // 就可以统计在某个时间范围内用户签到了多少天
        jedis.set("w", "hello");
        //第一个字符中1的位数
        Long bitcount = jedis.bitcount("w");
        //前两个字符中1的位数
        Long bitcount1 = jedis.bitcount("w", 0L, 0L);
        //第一个 0 位
        Long bitpos = jedis.bitpos("w", false);
        //第一个 1 位
        Long bitpos1 = jedis.bitpos("w", true);
        //从第二个字符算起，第一个 1 位
        Long bitpos2 = jedis.bitpos("w", true, new BitPosParams(1, 1));
        //从第三个字符算起，第一个 1 位
        Long bitpos3 = jedis.bitpos("w", true, new BitPosParams(2, 2));

        //bitfield 有三个子指令，分别是 get/set/incrby，它们都可以对指定位片段进行读写，但是最多只能处理 64 个连续的位，如果超过 64 位，就得使用多个子指令，bitfield 可以一次执行多个子指令。

        jedis.set("w", "hello");
        //从第一个位开始取 4 个位，结果是无符号数 (u)
        List<Long> bitfield = jedis.bitfield("w", "get", "u4", "0");
        //从第三个位开始取 3 个位，结果是无符号数 (u)
        List<Long> bitfield2 = jedis.bitfield("w", "get", "u3", "2");
        //从第一个位开始取 4 个位，结果是有符号数 (i)
        List<Long> bitfield3 = jedis.bitfield("w", "get", "i4", "0");
        //从第三个位开始取 3 个位，结果是有符号数 (i)
        List<Long> bitfield4 = jedis.bitfield("w", "get", "i3", "2");

        //一次执行多个子指令
        List<Long> longList = jedis.bitfield("w", "get", "u4", "0", "get", "u3", "2", "get", "i4", "0", "get", "i3",
                "2");

        //incrgy 子指令
        jedis.set("w", "hello");
        //从第三个位开始，对接下来的 4 位无符号数 +1
        List<Long> bitfield1 = jedis.bitfield("w", "incrby", "u4", "2", "1");
        List<Long> bitfield21 = jedis.bitfield("w", "incrby", "u4", "2", "1");
        List<Long> bitfield31 = jedis.bitfield("w", "incrby", "u4", "2", "1");
        List<Long> bitfield41 = jedis.bitfield("w", "incrby", "u4", "2", "1");
        List<Long> bitfield5 = jedis.bitfield("w", "incrby", "u4", "2", "1");
        //溢出折返了,bitfield 指令提供了溢出策略子指令 overflow，用户可以选择溢出行为，默认是折返 (wrap)，还可以选择失败 (fail) 报错不执行，以及饱和截断 (sat)
        List<Long> bitfield6 = jedis.bitfield("w", "incrby", "u4", "2", "1");

        //饱和截断SAT
        jedis.set("w", "hello");
        List<Long> bitfield7 = jedis.bitfield("w", "overflow", "sat", "incrby", "u4", "2", "1");
        List<Long> bitfield8 = jedis.bitfield("w", "overflow", "sat", "incrby", "u4", "2", "1");
        List<Long> bitfield9 = jedis.bitfield("w", "overflow", "sat", "incrby", "u4", "2", "1");
        List<Long> bitfield10 = jedis.bitfield("w", "overflow", "sat", "incrby", "u4", "2", "1");
        List<Long> bitfield11 = jedis.bitfield("w", "overflow", "sat", "incrby", "u4", "2", "1");
        List<Long> bitfield12 = jedis.bitfield("w", "overflow", "sat", "incrby", "u4", "2", "1");
        //保持最大值
        List<Long> bitfield13 = jedis.bitfield("w", "overflow", "sat", "incrby", "u4", "2", "1");

        //失败不执行FAIL
        jedis.set("w", "hello");
        List<Long> bitfield14 = jedis.bitfield("w", "overflow", "fail", "incrby", "u4", "2", "1");
        List<Long> bitfield15 = jedis.bitfield("w", "overflow", "fail", "incrby", "u4", "2", "1");
        List<Long> bitfield16 = jedis.bitfield("w", "overflow", "fail", "incrby", "u4", "2", "1");
        List<Long> bitfield17 = jedis.bitfield("w", "overflow", "fail", "incrby", "u4", "2", "1");
        List<Long> bitfield18 = jedis.bitfield("w", "overflow", "fail", "incrby", "u4", "2", "1");
        //不执行
        List<Long> bitfield19 = jedis.bitfield("w", "overflow", "fail", "incrby", "u4", "2", "1");

    }

    /**
     * SortedSet 和 HashMap 的结合体.一方面它是一个 set，保证了内部 value 的唯一性，另一方面它可以给每个 value 赋予一个 score，
     * 代表这个 value 的排序权重。它的内部实现用的是一种叫做「跳跃列表」的数据结构。
     *
     * 可以用来存粉丝列表，value 值是粉丝的用户 ID，score 是关注时间
     * 可以用来存储学生的成绩，value 值是学生的 ID，score 是他的考试成绩
     *
     * @param jedis jedis
     */
    private static void zset(Jedis jedis) {
        jedis.del("books");

        jedis.zadd("books", 9.0, "think in java");
        jedis.zadd("books", 8.9, "java concurrency");
        jedis.zadd("books", 8.6, "java cookbook");
        Set<String> books = jedis.zrange("books", 0, -1);

        Long count = jedis.zcard("books");

        Double zscore = jedis.zscore("books", "concurrency");

        //排名
        Long zrank = jedis.zrank("books", "java concurrency");

        //根据分值区间遍历 zset
        Set<String> books1 = jedis.zrangeByScore("books", 0, 0.81);

        Long zrem = jedis.zrem("books", "java concurrency");

        Set<String> books2 = jedis.zrange("books", 0, -1);
    }

    /**
     * Redis 的集合相当于 Java 语言里面的 HashSet，它内部的键值对是无序的唯一的。
     * 它的内部实现相当于一个特殊的字典，字典中所有的 value 都是一个值NULL。
     * 当集合中最后一个元素移除之后，数据结构自动删除，内存被回收。
     *
     * @param jedis jedis
     */
    private static void set(Jedis jedis) {
        jedis.del("books");
        jedis.sadd("books", "python");
        jedis.sadd("books", "python");
        jedis.sadd("books", "java", "golang");

        //获取members
        Set<String> books = jedis.smembers("books");
        //判断某个value是否存在
        Boolean sismember = jedis.sismember("books", "java");
        Boolean aBoolean = jedis.sismember("books", "rust");

        //统计长度
        Long books1 = jedis.scard("books");

        //弹出一个
        String books2 = jedis.spop("books");
    }

    /**
     *
     * Redis 的字典相当于 Java 语言里面的 HashMap，它是无序字典。内部实现结构上同 Java 的 HashMap 也是一致的，同样的数组 + 链表二维结构。
     * 第一维 hash 的数组位置碰撞时，就会将碰撞的元素使用链表串接起来。
     *
     * @param jedis jedis
     */
    private static void hash(Jedis jedis) {

        jedis.hset("books", "java", "think in java");
        jedis.hset("books", "go", "concurrency in go");
        jedis.hset("books", "python", "python cookbook");
        Map<String, String> books = jedis.hgetAll("books");
        String hget = jedis.hget("books", "java");
        Long hset = jedis.hset("books", "go", "hello go");
        String hget1 = jedis.hget("books", "go");
        String books1 = jedis.hmset("books", books);

    }

    /**
     * Redis 的列表相当于 Java 语言里面的 LinkedList，注意它是链表而不是数组。
     * 这意味着 list 的插入和删除操作非常快，时间复杂度为 O(1)，但是索引定位很慢，时间复杂度为 O(n)
     *
     * 首先在列表元素较少的情况下会使用一块连续的内存存储，这个结构是 ziplist，也即是压缩列表。
     * 它将所有的元素紧挨着一起存储，分配的是一块连续的内存。当数据量比较多的时候才会改成 quicklist。
     * 因为普通的链表需要的附加指针空间太大，会比较浪费空间，而且会加重内存的碎片化。
     * 比如这个列表里存的只是 int 类型的数据，结构上还需要两个额外的指针 prev 和 next 。
     * 所以 Redis 将链表和 ziplist 结合起来组成了 quicklist。也就是将多个 ziplist 使用双向指针串起来使用。
     * 这样既满足了快速的插入删除性能，又不会出现太大的空间冗余。...
     *
     *
     * @param jedis jedis
     */
    private static void list(Jedis jedis) {
        jedis.del("books");

        //列表
        jedis.rpush("books", "python", "java", "go");
        //获取列表长度
        Long books = jedis.llen("books");
        //根据索引读取,索引可以为负数.-1表示倒数第一个元素，-2表示倒数第二个元素
        String book = jedis.lindex("books", 1);
        //获取列表所有元素
        List<String> list = jedis.lrange("books", 0, -1);
        //截取
        String boks = jedis.ltrim("books", 1, -1);

        List<String> list2 = jedis.lrange("books", 0, -1);

        jedis.del("books");
    }

    static class BitKeeper {
        private int maxbits;

        public void random(long value) {
            int bits = lowZeros(value);
            if (bits > this.maxbits) {
                this.maxbits = bits;
            }
        }

        private int lowZeros(long value) {
            int i = 1;
            for (; i < 32; i++) {
                if (value >> i << i != value) {
                    break;
                }
            }
            return i - 1;
        }
    }

    static class Experiment {
        private int n;
        private int k;
        private BitKeeper[] keepers;

        public Experiment(int n) {
            this(n, 1024);
        }

        public Experiment(int n, int k) {
            this.n = n;
            this.k = k;
            this.keepers = new BitKeeper[k];
            for (int i = 0; i < k; i++) {
                this.keepers[i] = new BitKeeper();
            }
        }

        public void work() {
            for (int i = 0; i < this.n; i++) {
                long m = ThreadLocalRandom.current().nextLong(1L << 32);
                BitKeeper keeper = keepers[(int) (((m & 0xfff0000) >> 16) % keepers.length)];
                keeper.random(m);
            }
        }

        public double estimate() {
            double sumbitsInverse = 0.0;
            for (BitKeeper keeper : keepers) {
                sumbitsInverse += 1.0 / (float) keeper.maxbits;
            }
            double avgBits = (float) keepers.length / sumbitsInverse;
            return Math.pow(2, avgBits) * this.k;
        }
    }
}
