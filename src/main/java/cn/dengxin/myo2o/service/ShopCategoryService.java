package cn.dengxin.myo2o.service;

import java.io.IOException;
import java.util.List;
import cn.dengxin.myo2o.entity.ShopCategory;

public interface ShopCategoryService {

	String SCLISTKEY = "shopcategorylist";
	/**
	 * 根据查询条件获取ShopCategory列表
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);

//	/**
//	 *
//	 * @param parentId
//	 * @return
//	 * @throws IOException
//	 */
//	List<ShopCategory> getShopCategoryList(Long parentId) throws IOException;

}
