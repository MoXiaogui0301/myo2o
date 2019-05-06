package cn.dengxin.myo2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dengxin.myo2o.dao.ShopDao;
import cn.dengxin.myo2o.dto.ImageHolder;
import cn.dengxin.myo2o.dto.ShopExecution;
import cn.dengxin.myo2o.entity.Shop;
import cn.dengxin.myo2o.enums.ShopStateEnum;
import cn.dengxin.myo2o.exceptions.ShopOperationException;
import cn.dengxin.myo2o.service.ShopService;
import cn.dengxin.myo2o.util.ImageUtil;
import cn.dengxin.myo2o.util.PageCalculator;
import cn.dengxin.myo2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	ShopDao shopDao;

	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
		int count = shopDao.queryShopCount(shopCondition);
		ShopExecution se = new ShopExecution();
		if (shopList != null) {
			se.setShopList(shopList);
			se.setCount(count);
		}else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}
	
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail) {
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("更新店铺信息失败");
			} else {
				if (thumbnail.getImage() != null) {
					try {
						addShopImg(shop, thumbnail);
					} catch (Exception e) {
						throw new ShopOperationException("更新店铺图片失败");
					}
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
		} catch (Exception e) {
			throw new ShopOperationException(
					"add shop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String ShopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		shop.setShopImg(ShopImgAddr);
	}

	public Shop getByShopId(long shopId) {
		return shopDao.queryByShopId(shopId);
	}

	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}else {
			try {
			//1.判断是否需要处理图片
			if (thumbnail.getImage() != null && thumbnail.getImageName() != null && !"".equals(thumbnail.getImageName())) {
				Shop tempShop = shopDao.queryByShopId(shop.getShopId());
				if (tempShop.getShopImg() != null) {
					ImageUtil.deleteFileOrPath(tempShop.getShopImg());
				}
				addShopImg(shop, thumbnail);
			}
			//2.更新店铺信息
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.updateShop(shop);
			if (effectedNum <= 0) {
				return new ShopExecution(ShopStateEnum.INNER_ERROR);
			}else {
				shop = shopDao.queryByShopId(shop.getShopId());
				return new ShopExecution(ShopStateEnum.SUCCESS,shop);
			}
			}catch (Exception e) {
				throw new ShopOperationException("modifyShop error:" + e.getMessage());
			}
		}
	}

}
