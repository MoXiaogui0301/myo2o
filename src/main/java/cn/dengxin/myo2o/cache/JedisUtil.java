package cn.dengxin.myo2o.cache;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dengxin on 2019/7/20 10:09 AM
 */
public class JedisUtil {
    //缓存生存时间
    private final int expire = 60000;
    //操作key的方法
    public Keys KEYS;
    //对存储结构为String/List/Set/HashMap类型的操作
    public Strings STRINGS;
    public Lists LISTS;
    public Sets SETS;
    public Hash HASH;

    //Redis连接池对象
    private JedisPool jedisPool;

    //获取redis连接池
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    //设置redis连接池
    public void setJedisPool(JedisPoolWriper jedisPoolWriper) {
        this.jedisPool = jedisPoolWriper.getJedisPool();
    }

    //从连接池获取jedis对象
    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    //设置key的过期时间
    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        jedis.close();
    }

    //设置key的过期时间为默认值
    public void expire(String key) {
        expire(key, expire);
    }

    //Keys
    public class Keys {
        //清空所有key
        public String flushAll() {
            Jedis jedis = getJedis();
            String stada = jedis.flushAll();
            jedis.close();
            return stada;
        }

        //更改key
        public String rename(String oldKey, String newKey) {
            return rename(SafeEncoder.encode(oldKey),SafeEncoder.encode(newKey));
        }

        //更改key，仅当新key不存在时才执行
        public long renamenx(String oldkey, String newKey) {
            Jedis jedis = getJedis();
            long status = jedis.renamenx(oldkey, newKey);
            jedis.close();
            return status;
        }

        public String rename(byte[] oldkey, byte[] newkey) {
            Jedis jedis = getJedis();
            String status = jedis.rename(oldkey, newkey);
            jedis.close();
            return  status;
        }

        //设置key的过期时间，以秒为单位
        public long expired(String key, int seconds) {
            Jedis jedis = getJedis();
            long count = jedis.expire(key, seconds);
            jedis.close();
            return count;
        }

        //设置key的过期时间，他是距历园(即格林威治标准时间1970年1月1日的0点0分0秒)的偏移量
        public long expireAt(String key, int timestamp) {
            Jedis jedis = getJedis();
            long count = jedis.expireAt(key, timestamp);
            jedis.close();
            return count;
        }

        //查询key的过期时间，以秒为单位的时间表示
        public long ttl(String key) {
            Jedis sjedis = getJedis();
            long len = sjedis.ttl(key);
            sjedis.close();
            return len;
        }

        //取消对key过期时间的设置
        public long persist(String key) {
            Jedis jedis = getJedis();
            long count = jedis.persist(key);
            jedis.close();
            return count;
        }

        //删除keys对应的记录，可以是多个key
        public long del(String... keys) {
            Jedis jedis = getJedis();
            long count = jedis.del(keys);
            jedis.close();
            return count;
        }

        public long del(byte[]... keys) {
            Jedis jedis = getJedis();
            long count = jedis.del(keys);
            jedis.close();
            return count;
        }

        public boolean exists(String key) {
            Jedis sjedis = getJedis();
            boolean exist = sjedis.exists(key);
            sjedis.close();
            return exist;
        }

        public List<String> sort(String key) {
            Jedis sjedis = getJedis();
            List<String> list = sjedis.sort(key);
            sjedis.close();
            return list;
        }

        public List<String> sort(String key, SortingParams parame) {
            Jedis sjedis = getJedis();
            List<String> list = sjedis.sort(key, parame);
            sjedis.close();
            return list;
        }

        //返回指定key存储的类型
        public String type(String key) {
            Jedis sjedis = getJedis();
            String type = sjedis.type(key);
            sjedis.close();
            return type;
        }

        //查找所有匹配给定的模式的键
        public Set<String> keys(String pattern) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.keys(pattern);
            jedis.close();
            return set;
        }
    }

    public class Strings {
        // 根据key获取记录
        public String get(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            String value = sjedis.get(key);
            sjedis.close();
            return value;
        }

        // 根据key获取记录
        public byte[] get(byte[] key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            byte[] value = sjedis.get(key);
            sjedis.close();
            return value;
        }

        // 添加记录,如果记录已存在将覆盖原有的value
        public String set(String key, String value) {
            return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        // 添加记录,如果记录已存在将覆盖原有的value
        public String set(String key, byte[] value) {
            return set(SafeEncoder.encode(key), value);
        }

        // 添加记录,如果记录已存在将覆盖原有的value
        public String set(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            String status = jedis.set(key, value);
            jedis.close();
            return status;
        }

        // 添加有过期时间的记录
        public String setEx(String key, int seconds, String value) {
            Jedis jedis = getJedis();
            String str = jedis.setex(key, seconds, value);
            jedis.close();
            return str;
        }

        // 添加有过期时间的记录
        public String setEx(byte[] key, int seconds, byte[] value) {
            Jedis jedis = getJedis();
            String str = jedis.setex(key, seconds, value);
            jedis.close();
            return str;
        }

        // 添加一条记录，仅当给定的key不存在时才插入
        public long setnx(String key, String value) {
            Jedis jedis = getJedis();
            long str = jedis.setnx(key, value);
            jedis.close();
            return str;
        }

        // 从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据
        public long setRange(String key, long offset, String value) {
            Jedis jedis = getJedis();
            long len = jedis.setrange(key, offset, value);
            jedis.close();
            return len;
        }

        public long append(String key, String value) {
            Jedis jedis = getJedis();
            long len = jedis.append(key, value);
            jedis.close();
            return len;
        }

        public long decrBy(String key, long number) {
            Jedis jedis = getJedis();
            long len = jedis.decrBy(key, number);
            jedis.close();
            return len;
        }

        public long incrBy(String key, long number) {
            Jedis jedis = getJedis();
            long len = jedis.incrBy(key, number);
            jedis.close();
            return len;
        }

        public String getrange(String key, long startOffset, long endOffset) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            String value = sjedis.getrange(key, startOffset, endOffset);
            sjedis.close();
            return value;
        }

        public String getSet(String key, String value) {
            Jedis jedis = getJedis();
            String str = jedis.getSet(key, value);
            jedis.close();
            return str;
        }

        public List<String> mget(String... keys) {
            Jedis jedis = getJedis();
            List<String> str = jedis.mget(keys);
            jedis.close();
            return str;
        }

        public String mset(String... keysvalues) {
            Jedis jedis = getJedis();
            String str = jedis.mset(keysvalues);
            jedis.close();
            return str;
        }

        public long strlen(String key) {
            Jedis jedis = getJedis();
            long len = jedis.strlen(key);
            jedis.close();
            return len;
        }
    }

    // *******************************************Sets*******************************************//
    public class Sets {

        public long sadd(String key, String member) {
            Jedis jedis = getJedis();
            long s = jedis.sadd(key, member);
            jedis.close();
            return s;
        }

        public long sadd(byte[] key, byte[] member) {
            Jedis jedis = getJedis();
            long s = jedis.sadd(key, member);
            jedis.close();
            return s;
        }

        public long scard(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            long len = sjedis.scard(key);
            sjedis.close();
            return len;
        }

        public Set<String> sdiff(String... keys) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.sdiff(keys);
            jedis.close();
            return set;
        }

        public long sdiffstore(String newkey, String... keys) {
            Jedis jedis = getJedis();
            long s = jedis.sdiffstore(newkey, keys);
            jedis.close();
            return s;
        }

        public Set<String> sinter(String... keys) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.sinter(keys);
            jedis.close();
            return set;
        }

        public long sinterstore(String newkey, String... keys) {
            Jedis jedis = getJedis();
            long s = jedis.sinterstore(newkey, keys);
            jedis.close();
            return s;
        }

        public boolean sismember(String key, String member) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            boolean s = sjedis.sismember(key, member);
            sjedis.close();
            return s;
        }

        public Set<String> smembers(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            Set<String> set = sjedis.smembers(key);
            sjedis.close();
            return set;
        }

        public Set<byte[]> smembers(byte[] key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            Set<byte[]> set = sjedis.smembers(key);
            sjedis.close();
            return set;
        }

        public long smove(String srckey, String dstkey, String member) {
            Jedis jedis = getJedis();
            long s = jedis.smove(srckey, dstkey, member);
            jedis.close();
            return s;
        }

        public String spop(String key) {
            Jedis jedis = getJedis();
            String s = jedis.spop(key);
            jedis.close();
            return s;
        }

        public long srem(String key, String member) {
            Jedis jedis = getJedis();
            long s = jedis.srem(key, member);
            jedis.close();
            return s;
        }

        public Set<String> sunion(String... keys) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.sunion(keys);
            jedis.close();
            return set;
        }

        public long sunionstore(String newkey, String... keys) {
            Jedis jedis = getJedis();
            long s = jedis.sunionstore(newkey, keys);
            jedis.close();
            return s;
        }
    }

    // *******************************************Hash*******************************************//
    public class Hash {

        public long hdel(String key, String fieid) {
            Jedis jedis = getJedis();
            long s = jedis.hdel(key, fieid);
            jedis.close();
            return s;
        }

        public long hdel(String key) {
            Jedis jedis = getJedis();
            long s = jedis.del(key);
            jedis.close();
            return s;
        }

        public boolean hexists(String key, String fieid) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            boolean s = sjedis.hexists(key, fieid);
            sjedis.close();
            return s;
        }

        public String hget(String key, String fieid) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            String s = sjedis.hget(key, fieid);
            sjedis.close();
            return s;
        }

        public byte[] hget(byte[] key, byte[] fieid) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            byte[] s = sjedis.hget(key, fieid);
            sjedis.close();
            return s;
        }

        public Map<String, String> hgetAll(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            Map<String, String> map = sjedis.hgetAll(key);
            sjedis.close();
            return map;
        }

        public long hset(String key, String fieid, String value) {
            Jedis jedis = getJedis();
            long s = jedis.hset(key, fieid, value);
            jedis.close();
            return s;
        }

        public long hset(String key, String fieid, byte[] value) {
            Jedis jedis = getJedis();
            long s = jedis.hset(key.getBytes(), fieid.getBytes(), value);
            jedis.close();
            return s;
        }

        public long hsetnx(String key, String fieid, String value) {
            Jedis jedis = getJedis();
            long s = jedis.hsetnx(key, fieid, value);
            jedis.close();
            return s;
        }

        public List<String> hvals(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<String> list = sjedis.hvals(key);
            sjedis.close();
            return list;
        }

        public long hincrby(String key, String fieid, long value) {
            Jedis jedis = getJedis();
            long s = jedis.hincrBy(key, fieid, value);
            jedis.close();
            return s;
        }

        public Set<String> hkeys(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            Set<String> set = sjedis.hkeys(key);
            sjedis.close();
            return set;
        }

        public long hlen(String key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            long len = sjedis.hlen(key);
            sjedis.close();
            return len;
        }

        public List<String> hmget(String key, String... fieids) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<String> list = sjedis.hmget(key, fieids);
            sjedis.close();
            return list;
        }

        public List<byte[]> hmget(byte[] key, byte[]... fieids) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<byte[]> list = sjedis.hmget(key, fieids);
            sjedis.close();
            return list;
        }

        public String hmset(String key, Map<String, String> map) {
            Jedis jedis = getJedis();
            String s = jedis.hmset(key, map);
            jedis.close();
            return s;
        }

        public String hmset(byte[] key, Map<byte[], byte[]> map) {
            Jedis jedis = getJedis();
            String s = jedis.hmset(key, map);
            jedis.close();
            return s;
        }

    }

    // *******************************************Lists*******************************************//
    public class Lists {
        public long llen(String key) {
            return llen(SafeEncoder.encode(key));
        }

        public long llen(byte[] key) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            long count = sjedis.llen(key);
            sjedis.close();
            return count;
        }

        public String lset(byte[] key, int index, byte[] value) {
            Jedis jedis = getJedis();
            String status = jedis.lset(key, index, value);
            jedis.close();
            return status;
        }

        public String lset(String key, int index, String value) {
            return lset(SafeEncoder.encode(key), index, SafeEncoder.encode(value));
        }

        public long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
            return linsert(SafeEncoder.encode(key), where, SafeEncoder.encode(pivot), SafeEncoder.encode(value));
        }

        public long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.linsert(key, where, pivot, value);
            jedis.close();
            return count;
        }

        public String lindex(String key, int index) {
            return SafeEncoder.encode(lindex(SafeEncoder.encode(key), index));
        }

        public byte[] lindex(byte[] key, int index) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            byte[] value = sjedis.lindex(key, index);
            sjedis.close();
            return value;
        }

        public String lpop(String key) {
            return SafeEncoder.encode(lpop(SafeEncoder.encode(key)));
        }

        public byte[] lpop(byte[] key) {
            Jedis jedis = getJedis();
            byte[] value = jedis.lpop(key);
            jedis.close();
            return value;
        }

        public String rpop(String key) {
            Jedis jedis = getJedis();
            String value = jedis.rpop(key);
            jedis.close();
            return value;
        }

        public long lpush(String key, String value) {
            return lpush(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        public long rpush(String key, String value) {
            Jedis jedis = getJedis();
            long count = jedis.rpush(key, value);
            jedis.close();
            return count;
        }

        public long rpush(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.rpush(key, value);
            jedis.close();
            return count;
        }

        public long lpush(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.lpush(key, value);
            jedis.close();
            return count;
        }

        public List<String> lrange(String key, long start, long end) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<String> list = sjedis.lrange(key, start, end);
            sjedis.close();
            return list;
        }

        public List<byte[]> lrange(byte[] key, int start, int end) {
            // ShardedJedis sjedis = getShardedJedis();
            Jedis sjedis = getJedis();
            List<byte[]> list = sjedis.lrange(key, start, end);
            sjedis.close();
            return list;
        }

        public long lrem(byte[] key, int c, byte[] value) {
            Jedis jedis = getJedis();
            long count = jedis.lrem(key, c, value);
            jedis.close();
            return count;
        }

        public long lrem(String key, int c, String value) {
            return lrem(SafeEncoder.encode(key), c, SafeEncoder.encode(value));
        }

        public String ltrim(byte[] key, int start, int end) {
            Jedis jedis = getJedis();
            String str = jedis.ltrim(key, start, end);
            jedis.close();
            return str;
        }

        public String ltrim(String key, int start, int end) {
            return ltrim(SafeEncoder.encode(key), start, end);
        }
    }

}
