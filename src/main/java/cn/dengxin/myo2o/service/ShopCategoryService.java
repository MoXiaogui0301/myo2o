package cn.dengxin.myo2o.service;

import java.util.List;
import cn.dengxin.myo2o.entity.ShopCategory;

public interface ShopCategoryService {
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
