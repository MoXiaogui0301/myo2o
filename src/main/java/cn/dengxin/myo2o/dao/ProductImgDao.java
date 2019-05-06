package cn.dengxin.myo2o.dao;

import java.util.List;
import cn.dengxin.myo2o.entity.ProductImg;

public interface ProductImgDao {

	/**
	 * 批量添加商品详情图片
	 * @param productImgList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);
	
	/**
	 * 根据商品Id删除商品图片
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);
	
	/**
	 * 根据商品Id查询商品详情图片
	 * @param productId
	 * @return
	 */
	List<ProductImg> queryProductImgList(long productId);
}
