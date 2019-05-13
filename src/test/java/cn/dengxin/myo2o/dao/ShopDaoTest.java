package cn.dengxin.myo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dengxin.myo2o.BaseTest;
import cn.dengxin.myo2o.entity.Area;
import cn.dengxin.myo2o.entity.PersonInfo;
import cn.dengxin.myo2o.entity.Shop;
import cn.dengxin.myo2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest {

	Logger logger = LoggerFactory.getLogger(ShopDaoTest.class);
	
	@Autowired
	private ShopDao shopDao;

	@Test
	public void testQueryShopListAndCount() {
		Shop shopCondition = new Shop();
		ShopCategory childCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(12L);
		childCategory.setParent(parentCategory);
//		shopCondition.setShopCategory(childCategory);
		shopCondition.setShopName("小黄人主题奶茶店");
//		shopCondition.setEnableStatus(1);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 6);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println("店铺列表的大小：" + shopList.size());
		System.out.println("店铺总数为：" + count);
	}
	
	@Test
	@Ignore
	public void testQueryByShopId() {
		long shopId = 2;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("Area Name:" + shop.getArea().getAreaName());
	}
	
	@Test
	@Ignore
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		Area area = new Area();
		area.setAreaId(1);
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(personInfo);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("百宝斋");
		shop.setShopDesc("各式装备一应俱全");
		shop.setShopAddr("长安东门茂业坊12号");
		shop.setPhone("13888899988");
		shop.setPriority(6);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("金牌卖家，职业忽悠");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(2L);
		PersonInfo owner = new PersonInfo();
		owner.setUserId(2L);
		shop.setOwner(owner);
		Area area = new Area();
		area.setAreaId(2);
		shop.setArea(area);
		shop.setAdvice("百年老店，值得信赖");
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
	}
}
