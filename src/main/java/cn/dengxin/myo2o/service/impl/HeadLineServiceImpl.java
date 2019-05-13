package cn.dengxin.myo2o.service.impl;

import cn.dengxin.myo2o.dao.HeadLineDao;
import cn.dengxin.myo2o.entity.HeadLine;
import cn.dengxin.myo2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author: Dengxin
 * @Date: 2019/5/6 7:06 PM
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    private HeadLineDao headLineDao;

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {
        return headLineDao.queryHeadLine(headLineCondition);
    }
}
