package cn.dengxin.myo2o.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dengxin.myo2o.BaseTest;
import cn.dengxin.myo2o.entity.ProductCategory;

public class ProductCategoryServiceTest extends BaseTest {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Test
	public void testGetProductCategory() {
		long shopId = 2L;
		List<ProductCategory> productCategories = productCategoryService.getProductCategoryList(shopId);
		System.out.println("商品种类共有：" + productCategories.size() + "种");
	}

}
