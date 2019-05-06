package cn.dengxin.myo2o.service;

import cn.dengxin.myo2o.dto.ImageHolder;
import cn.dengxin.myo2o.dto.ShopExecution;
import cn.dengxin.myo2o.entity.Shop;
import cn.dengxin.myo2o.exceptions.ShopOperationException;

public interface ShopService {

	/**
	 * 根据shopCondition分页返回相应店铺数据
	 * @param shopCondition
	 * @param pageIndex
	 * @param page
	 * @return
	 */
	ShopExecution getShopList(Shop shopCondition, int pageIndex, int page);
	
	/**
	 * 通过店铺ID获取店铺信息
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * 注册店铺信息，包括图片处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
	
	/**
	 * 增加店铺
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail);
}
