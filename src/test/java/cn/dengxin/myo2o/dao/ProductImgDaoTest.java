package cn.dengxin.myo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dengxin.myo2o.BaseTest;
import cn.dengxin.myo2o.entity.ProductImg;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest {

	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	@Ignore
	public void testABatchInsertProductImg() throws Exception {
		ProductImg productImg1 = new ProductImg();
		productImg1.setProductId(1L);
		productImg1.setImgAddr("测试图片1");
		productImg1.setImgDesc("测试图片1描述");
		productImg1.setPriority(5);
		productImg1.setCreateTime(new Date());
		ProductImg productImg2 = new ProductImg();
		productImg2.setProductId(1L);
		productImg2.setImgAddr("测试图片2");
		productImg2.setImgDesc("测试图片2描述");
		productImg2.setPriority(4);
		productImg2.setCreateTime(new Date());
		List<ProductImg> productImgs = new ArrayList<>();
		productImgs.add(productImg1);
		productImgs.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgs);
		assertEquals(2, effectedNum);
	}
	
	@Test
	@Ignore
	public void testCDeleteProductImgByProductId() throws Exception{
		//删除新增的两条商品详情图片记录
		long productId = 7L;
		int effectedNum = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2, effectedNum);
	}
	
	@Test
	public void testBQueryProductImgByProductId() throws Exception{
		long productId = 4L;
		List<ProductImg> list = new ArrayList<>();
		list = productImgDao.queryProductImgList(productId);
		System.out.println(list.size());
		assertEquals(2, list.size());
	}
}
