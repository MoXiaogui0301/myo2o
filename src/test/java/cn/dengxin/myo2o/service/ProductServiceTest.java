package cn.dengxin.myo2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dengxin.myo2o.BaseTest;
import cn.dengxin.myo2o.dto.ImageHolder;
import cn.dengxin.myo2o.dto.ProductExecution;
import cn.dengxin.myo2o.entity.Product;
import cn.dengxin.myo2o.entity.ProductCategory;
import cn.dengxin.myo2o.entity.Shop;
import cn.dengxin.myo2o.enums.ProductStateEnum;
import cn.dengxin.myo2o.exceptions.ProductOperationException;
import cn.dengxin.myo2o.exceptions.ShopOperationException;

public class ProductServiceTest extends BaseTest {

	@Autowired
	private ProductService productService;
	
	@Test
	@Ignore
	public void testAddProduct() throws ProductOperationException,FileNotFoundException{
		//创建shopId为2且productCategoryId为1的商品实例，并给其成员变量赋值
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(2L);
		product.setShop(shop);
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(1L);
		product.setProductCategory(productCategory);
		product.setProductName("松风拳");
		product.setProductDesc("青城派入门拳法");
		product.setPriority(2);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		//创建缩略图文件流
		File thumbnaiFile = new File("/Users/dengxin/Pictures/dandelion_logo.jpeg");
		InputStream is = new FileInputStream(thumbnaiFile);
		ImageHolder thumbnail = new ImageHolder(thumbnaiFile.getName(), is);
		//创建两个商品详情图文件，并添加到详情图列表
		File productImg1 = new File("/Users/dengxin/资料/myo2o/images/item/shop/20/20170606203630923430.jpg");
		File productImg2 = new File("/Users/dengxin/资料/myo2o/images/item/shop/20/20170606203631972862.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgs = new ArrayList<>();
		productImgs.add(new ImageHolder(productImg1.getName(), is1));
		productImgs.add(new ImageHolder(productImg2.getName(), is2));
		//添加商品并验证
		ProductExecution pe = productService.addProduct(product, thumbnail, productImgs);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
	
	@Test
	@Ignore
	public void testModifyProduct() throws ShopOperationException,FileNotFoundException {
		//创建shopId为2且productCategoryId为1的商品实例并给其成员变量赋值
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(2L);
		ProductCategory pc = new ProductCategory();
		pc.setProductCategoryId(1L);
		product.setProductId(10L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("正式的商品");
		product.setProductDesc("正式商品描述");
		//创建缩略图文件流
		File thumbnailFile = new File("/Users/dengxin/资料/myo2o/images/item/shop/20/20170606203631552081.png");
		InputStream is = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
		//创建两个商品详情图文件流并将他们添加到详情图列表中
		File productImg1 = new File("/Users/dengxin/资料/myo2o/images/item/shop/20/20170606203846623120.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("/Users/dengxin/资料/myo2o/images/item/shop/20/2017060609163395401.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<>();
		productImgList.add(new ImageHolder(productImg1.getName(), is1));
		productImgList.add(new ImageHolder(productImg2.getName(), is2));
		//添加商品并验证
		ProductExecution pe = productService.modifyProduct(product, thumbnail, productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
	}
	
	@Test
	public void testGetProductList() {
		Product product = new Product();
		product.setProductId(2L);
		ProductExecution pe = productService.getProductList(product, 2, 5);
		System.out.println("该页共有数据：" + pe.getProductList().size());
		System.out.println("总共查询到数据：" + pe.getCount());
	}
	
}
