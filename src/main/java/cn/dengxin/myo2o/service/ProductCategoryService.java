package cn.dengxin.myo2o.service;

import java.util.List;

import cn.dengxin.myo2o.dto.ProductCategoryExecution;
import cn.dengxin.myo2o.entity.ProductCategory;
import cn.dengxin.myo2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	
	List<ProductCategory> getProductCategoryList(long shopId);
	
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

	/**
	 * 删除指定shop下的指定商品类别(删除类别前需先将该商品类别下的商品的类别id置为空)
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException;
}
