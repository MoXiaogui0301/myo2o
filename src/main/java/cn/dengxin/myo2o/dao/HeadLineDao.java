package cn.dengxin.myo2o.dao;

import cn.dengxin.myo2o.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Dengxin
 * @Date: 2019/5/6 6:21 PM
 */
public interface HeadLineDao {

    /**
     * 根据传入的查询条件(头条名查询头条)
     * @param headLineCondition
     * @return
     */
    List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}
