package cn.dengxin.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dengxin.myo2o.entity.ProductCategory;

public interface ProductCategoryDao {

	/**
	 * 通过shop id 查询店铺商品类别
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);
	
	/**
	 * 批量增添商品类别
	 * @param productCategories
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategories);
	
	/**
	 * 删除商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	int deleteProductCategory(@Param("productCategoryId")long productCategoryId, @Param("shopId")long shopId);
}
