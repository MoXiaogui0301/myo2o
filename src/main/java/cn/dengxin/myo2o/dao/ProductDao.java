package cn.dengxin.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dengxin.myo2o.entity.Product;

public interface ProductDao {

	/**
	 * 插入商品信息
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);
	
	/**
	 * 通过商品Id查询商品
	 * @param productId
	 * @return
	 */
	Product queryProductById(long productId);
	
	/**
	 * 修改商品信息
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);
	
	/**
	 * 按店铺分页查询商品
	 * @param shopCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> queryProductList(@Param("productCondition") Product productCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	/**
	 * 返回queryProductList总数
	 * @param shopId
	 * @return
	 */
	int queryProductCount(@Param("productCondition") Product productCondition);
	
	/**
	 * 删除商品类别前，将相关商品的商品类别Id设为空
	 * @param productCategoryId
	 * @return
	 */
	int updateProductCategoryToNull(long productCategoryId);
}
