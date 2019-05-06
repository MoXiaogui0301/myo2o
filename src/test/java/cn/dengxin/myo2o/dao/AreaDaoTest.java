package cn.dengxin.myo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dengxin.myo2o.BaseTest;
import cn.dengxin.myo2o.entity.Area;

public class AreaDaoTest extends BaseTest {

	@Autowired
	private AreaDao areaDao;
	
	@Test
	public void testQueryArea() {
		List<Area> areaList = areaDao.queryArea();
		System.out.println(areaList.size());
		assertEquals(4, areaList.size());
	}
}
