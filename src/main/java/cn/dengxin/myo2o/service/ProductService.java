package cn.dengxin.myo2o.service;

import java.util.List;

import cn.dengxin.myo2o.dto.ImageHolder;
import cn.dengxin.myo2o.dto.ProductExecution;
import cn.dengxin.myo2o.entity.Product;
import cn.dengxin.myo2o.exceptions.ProductOperationException;

public interface ProductService {

	/**
	 * 添加商品信息及图片处理
	 * 
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgList) throws ProductOperationException;

	/**
	 * 通过商品Id查询唯一的商品信息
	 * 
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);

	/**
	 * 修改商品信息及图片处理
	 * @param product
	 * @param thumbnail
	 * @param productImgList
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution modifyProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgList) throws ProductOperationException;
	
	/**
	 * 根据输入条件获取店铺商品列表
	 * @param shopId
	 * @param pageIndex
	 * @param page
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
}
