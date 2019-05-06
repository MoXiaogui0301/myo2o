package cn.dengxin.myo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dengxin.myo2o.BaseTest;
import cn.dengxin.myo2o.entity.ProductCategory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest extends BaseTest {

	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Test
	public void testBQueryProductCategory() {
		long shopId = 2L;
		List<ProductCategory> productCategories = productCategoryDao
				.queryProductCategoryList(shopId);
		System.out.println("商品种类共有：" + productCategories.size() + "种");
	}

	@Test
	public void testABatchInsertProductCategory() {
		ProductCategory pc1 = new ProductCategory();
		ProductCategory pc2 = new ProductCategory();
		pc1.setProductCategoryName("材官");
		pc1.setProductCategoryId(5L);
		pc1.setPriority(6);
		pc1.setCreateTime(new Date());
		pc1.setShopId(2L);
		pc2.setProductCategoryName("宠物");
		pc2.setProductCategoryId(6L);
		pc2.setCreateTime(new Date());
		pc2.setPriority(6);
		pc2.setShopId(2L);
		List<ProductCategory> productCategories = new ArrayList<>();
		productCategories.add(pc1);
		productCategories.add(pc2);
		int effectedNum = productCategoryDao
				.batchInsertProductCategory(productCategories);
		System.out.println("effectedNum：" + effectedNum);
		assertEquals(2, effectedNum);
	}

	@Test
	public void testCDeleteProductCategory() {
		long shopId = 2L;
		List<ProductCategory> productCategories = productCategoryDao
				.queryProductCategoryList(shopId);
		for (ProductCategory productCategory : productCategories) {
			if (productCategory.getProductCategoryName().equals("材官")
					|| productCategory.getProductCategoryName().equals("宠物")) {
				int effectedNum = productCategoryDao.deleteProductCategory(
						productCategory.getProductCategoryId(),
						productCategory.getShopId());
				assertEquals(1, effectedNum);
			}
		}
	}

}
