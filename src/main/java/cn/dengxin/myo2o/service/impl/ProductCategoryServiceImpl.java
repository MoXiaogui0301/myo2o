package cn.dengxin.myo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dengxin.myo2o.dao.ProductCategoryDao;
import cn.dengxin.myo2o.dao.ProductDao;
import cn.dengxin.myo2o.dto.ProductCategoryExecution;
import cn.dengxin.myo2o.entity.ProductCategory;
import cn.dengxin.myo2o.enums.ProductCategoryStateEnum;
import cn.dengxin.myo2o.exceptions.ProductCategoryOperationException;
import cn.dengxin.myo2o.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Autowired
	private ProductDao productDao;

	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategoryList(shopId);
	}

	@Transactional
	public ProductCategoryExecution batchAddProductCategory(
			List<ProductCategory> productCategories)
			throws ProductCategoryOperationException {
		if (productCategories != null && productCategories.size() > 0) {
			try {
				int effectedNum = productCategoryDao
						.batchInsertProductCategory(productCategories);
				if (effectedNum <= 0) {
					throw new ProductCategoryOperationException("店铺类别创建失败");
				} else {
					return new ProductCategoryExecution(
							ProductCategoryStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new ProductCategoryOperationException(
						"batchAddProductCategory error:" + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(
					ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Transactional
	public ProductCategoryExecution deleteProductCategory(
			long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		//解除tb_product里的商品与该商品类别的关联
		try {
			int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
			if (effectedNum < 0) {
				throw new ProductCategoryOperationException("商品类别更新失败");
			}
		}catch (Exception e) {
			throw new ProductCategoryOperationException("deleteProductCategory error:" + e.getMessage());
		}
		//删除该商品类别
		try {
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effectedNum > 0) {
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}else {
				throw new ProductCategoryOperationException("删除商品类别失败");
			}
		}catch (Exception e) {
			throw new ProductCategoryOperationException("deleteProductCategory error:" + e.getMessage());
		}
	}

}
