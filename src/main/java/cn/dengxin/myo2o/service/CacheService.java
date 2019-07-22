package cn.dengxin.myo2o.service;

/**
 * Created by Dengxin on 2019/7/20 1:35 PM
 */
public interface CacheService {
    //依前缀删除匹配该模式下的所有key-value
    void removeFromCache(String keyPrefix);
}
