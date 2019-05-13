package cn.dengxin.myo2o.service;

import cn.dengxin.myo2o.entity.HeadLine;

import java.io.IOException;
import java.util.List;

/**
 * @Author: Dengxin
 * @Date: 2019/5/6 7:06 PM
 */
public interface HeadLineService {

    List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException;
}
