package cn.dengxin.myo2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dengxin.myo2o.BaseTest;
import cn.dengxin.myo2o.entity.Area;

public class AreaServiceTest extends BaseTest {
	@Autowired
	private AreaService areaService;
	@Test
	public void testGetAreaList() {
		List<Area> areaList = areaService.getAreaList();
		System.out.println(areaList.get(0).getAreaName());
		assertEquals("玄武", areaList.get(0).getAreaName());
	}

}
