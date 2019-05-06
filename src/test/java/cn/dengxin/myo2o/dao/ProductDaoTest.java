package cn.dengxin.myo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dengxin.myo2o.BaseTest;
import cn.dengxin.myo2o.entity.Product;
import cn.dengxin.myo2o.entity.ProductCategory;
import cn.dengxin.myo2o.entity.Shop;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
	
	@Autowired
	private ProductDao productDao;
	
	@Test
	@Ignore
	public void testAInsertProduct() {
		Product product = new Product();
		product.setProductName("泰山十八盘");
		product.setProductDesc("泰山派绝技");
		product.setNormalPrice("200");
		product.setPromotionPrice("180");
		product.setPriority(3);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(1L);
		product.setProductCategory(pc);
		Shop shop = new Shop();
		shop.setShopId(2L);
		product.setShop(shop);
		product.setCreateTime(new Date());
		product.setEnableStatus(1);
		product.setLastEditTime(new Date());
		int effectedNum = productDao.insertProduct(product);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testBQueryByIdProduct() {
		long productId = 10L;
		Product product = productDao.queryProductById(productId);
		System.out.println(product.getPromotionPrice());
		assertEquals("175", product.getPromotionPrice());
	}
	
	@Test
	@Ignore
	public void testCUpdateProduct() {
		long productId = 10L;
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(2L);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(2L);
		product.setProductId(productId);
		product.setProductName("更新商品");
		product.setProductDesc("更新商品备注");
		product.setLastEditTime(new Date());
		product.setShop(shop);
		product.setProductCategory(productCategory);
		int effectedNum = productDao.updateProduct(product);
		assertEquals(1, effectedNum);
	}
	
	@Test
	@Ignore
	public void testDQueryProductListAndCount() {
		Shop shop = new Shop();
		shop.setShopId(2L);
		Product product = new Product();
		product.setShop(shop);
		List<Product> products = productDao.queryProductList(product, 0, 10);
		System.out.println(products.size());
		int count = productDao.queryProductCount(product);
		System.out.println(count);
		assertEquals(10, products.size());
	}
	
	@Test
	public void testEUpdateProductCategoryToNull() {
		long productCategoryId = 2L;
		int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
		assertEquals(effectedNum, 1);
	}

}
