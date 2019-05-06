package cn.dengxin.myo2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cn.dengxin.myo2o.BaseTest;
import cn.dengxin.myo2o.dao.ShopDaoTest;
import cn.dengxin.myo2o.dto.ImageHolder;
import cn.dengxin.myo2o.dto.ShopExecution;
import cn.dengxin.myo2o.entity.Area;
import cn.dengxin.myo2o.entity.PersonInfo;
import cn.dengxin.myo2o.entity.Shop;
import cn.dengxin.myo2o.entity.ShopCategory;
import cn.dengxin.myo2o.enums.ShopStateEnum;
import cn.dengxin.myo2o.exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest {

	Logger logger = LoggerFactory.getLogger(ShopDaoTest.class);
	
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testGetShopList() {
		Shop shopCondition= new Shop();
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(3L);
		shopCondition.setShopCategory(sc);
		ShopExecution se = shopService.getShopList(shopCondition, 2, 3);
		System.out.println("该页共有数据：" + se.getShopList().size());
		System.out.println("总共查询到数据：" + se.getCount());
	}
	
	@Ignore
	@Test
	public void testModifyShop() throws ShopOperationException, FileNotFoundException {
		Shop shop = shopService.getByShopId(3L);
		shop.setShopName("万象阁");
		File shopImg = new File("/Users/dengxin/Pictures/WechatIMG273.jpeg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder("WechatIMG273.jpeg", is);
		ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
		System.out.println(shopImg.getName());
		System.out.println("新的图片地址为：" + shopExecution.getShop().getShopImg());
	}
	
	@Test
	@Ignore
	public void testInsertShop() throws FileNotFoundException {
		Shop shop = new Shop();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(2L);
		Area area = new Area();
		area.setAreaId(3);
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(personInfo);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("腾云阁");
		shop.setShopDesc("让您起飞");
		shop.setShopAddr("洛阳南门大兴街8号");
		shop.setPhone("13674837678");
		shop.setPriority(3);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("执念改变世界");
		File shopImg = new File("/Users/dengxin/Pictures/dandelion_logo.jpeg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder(shopImg.getName(), is);
		ShopExecution shopExecution = shopService.addShop(shop, imageHolder);
		assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
	}
}
