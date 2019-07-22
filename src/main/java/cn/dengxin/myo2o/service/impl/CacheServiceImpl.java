package cn.dengxin.myo2o.service.impl;

import cn.dengxin.myo2o.cache.JedisUtil;
import cn.dengxin.myo2o.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Dengxin on 2019/7/20 1:37 PM
 */
@Service
public class CacheServiceImpl implements CacheService {
    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    public void removeFromCache(String keyPrefix) {
        Set<String> keySet = jedisKeys.keys(keyPrefix+"*");
        for (String key : keySet) {
            jedisKeys.del(key);
        }
    }
}
