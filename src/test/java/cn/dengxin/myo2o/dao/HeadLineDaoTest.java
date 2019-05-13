package cn.dengxin.myo2o.dao;

import cn.dengxin.myo2o.BaseTest;
import cn.dengxin.myo2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author: Dengxin
 * @Date: 2019/5/6 6:41 PM
 */
public class HeadLineDaoTest extends BaseTest {
    @Autowired
    private HeadLineDao headLineDao;

    @Test
    public void testQueryHeadLine() {
        List<HeadLine> headLineList = headLineDao.queryHeadLine(new HeadLine());
        assertEquals(4,headLineList.size());
    }
}
