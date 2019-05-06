package cn.dengxin.myo2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dengxin.myo2o.dao.ShopCategoryDao;
import cn.dengxin.myo2o.entity.ShopCategory;
import cn.dengxin.myo2o.service.ShopCategoryService;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition){
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}
}
