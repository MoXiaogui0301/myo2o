package cn.dengxin.myo2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dengxin.myo2o.entity.ShopCategory;

public interface ShopCategoryDao {
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")ShopCategory shopCategoryCondition);

//	List<ShopCategory> queryShopCategory

}
