package cn.dengxin.myo2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dengxin.myo2o.dao.ProductDao;
import cn.dengxin.myo2o.dao.ProductImgDao;
import cn.dengxin.myo2o.dto.ImageHolder;
import cn.dengxin.myo2o.dto.ProductExecution;
import cn.dengxin.myo2o.entity.Product;
import cn.dengxin.myo2o.entity.ProductImg;
import cn.dengxin.myo2o.enums.ProductStateEnum;
import cn.dengxin.myo2o.exceptions.ProductOperationException;
import cn.dengxin.myo2o.service.ProductService;
import cn.dengxin.myo2o.util.ImageUtil;
import cn.dengxin.myo2o.util.PageCalculator;
import cn.dengxin.myo2o.util.PathUtil;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductImgDao productImgDao;
	
	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		if (productList != null) {
			pe.setProductList(productList);
			pe.setCount(count);
		}else {
			pe.setState(ProductStateEnum.INNER_ERROR.getState());
		}
		return pe;
	}

	@Override
	@Transactional
	// 1.处理缩略图，获取缩略图相对路径并赋值给product
	// 2.往tb_product写入商品信息，获取productId
	// 3.结合productId批量处理商品详情图
	// 4.将商品详情图列表批量插入tb_product_img表中
	public ProductExecution addProduct(Product product, ImageHolder thumbnail,
			List<ImageHolder> productImgHolderList)
			throws ProductOperationException {
		if (product != null && product.getShop() != null
				&& product.getShop().getShopId() != null) {
			// 给商品设置默认的创建时间和更新时间
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			// 默认为上架状态
			product.setEnableStatus(1);
			// 若商品缩略图不为空则添加
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				// 创建商品信息
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品失败!");
				}
			} catch (Exception e) {
				throw new ProductOperationException("创建商品失败:" + e.toString());
			}
			// 若商品详情图不为空则添加
			if (productImgHolderList != null
					&& productImgHolderList.size() != 0) {
				addProductImgList(product, productImgHolderList);
			} else {
				return new ProductExecution(ProductStateEnum.EMPTY_LIST);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY_PRODUCT);
		}
	}

	/**
	 * 通过商品Id获取商品
	 */
	public Product getProductById(long productId) {
		return productDao.queryProductById(productId);
	}

	@Transactional
	//1.若缩略图参数有值，则处理缩略图
	//若原先存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋值给product
	//2.若商品详情图列表参数有值，对商品详情图片列表进行同样的操作
	//3.将tb_product_img下面的该商品原先的商品记录详情图记录全部清除
	//4.更新tb_product_img及tb_product的信息
	public ProductExecution modifyProduct(Product product,
			ImageHolder thumbnail, List<ImageHolder> productImageHolderList)
			throws ProductOperationException {
		if (product!=null && product.getShop()!=null && product.getShop().getShopId()!=null) {
			//给商品设置上默认属性
			product.setLastEditTime(new Date());
			//若商品缩略图不为空且原有缩略图不为空，则删除原缩略图并添加新缩略图
			if (thumbnail!=null) {
				//获取原有缩略图信息并删除
				Product tempProduct = productDao.queryProductById(product.getProductId());
				if (tempProduct.getImgAddr()!=null) {
					ImageUtil.deleteFileOrPath(tempProduct.getImgAddr());
				}
				//添加新缩略图
				addThumbnail(product, thumbnail);
			}
			//若有新存入的商品详情图，则将原详情图删除，并添加新图片
			if (productImageHolderList != null && productImageHolderList.size() > 0) {
				deleteProductImgList(product.getProductId());
				addProductImgList(product, productImageHolderList);
			}
			try {
				//更新商品信息
				int effectedNum = productDao.updateProduct(product);
				if (effectedNum <= 0) {
					throw new ProductOperationException("更新商品信息失败");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS,product);
			}catch (Exception e) {
				throw new ProductOperationException("更新商品信息失败：" + e.toString());
			}
		}else {
			return new ProductExecution(ProductStateEnum.EMPTY_PRODUCT);
		}
	}

	/**
	 * 添加缩略图
	 * 
	 * @param product
	 * @param thumbnail
	 */
	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}

	private void addProductImgList(Product product,
			List<ImageHolder> productImageHolderList) {
		// 获取图片存储路径，直接放在相应店铺的文件夹中
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgs = new ArrayList<>();
		// 遍历图片，添加信息到ProductImg实体中，并生成productImgs
		for (ImageHolder productImgHolder : productImageHolderList) {
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder,
					dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgs.add(productImg);
		}
		// 如果productImgs中确实有图片，则进行批量添加
		if (productImgs.size() > 0) {
			try {
				int effectedNum = productImgDao
						.batchInsertProductImg(productImgs);
				if (effectedNum <= 0) {
					throw new ProductOperationException("创建商品详情图片失败");
				}
			} catch (Exception e) {
				throw new ProductOperationException(
						"创建商品详情图片失败:" + e.toString());
			}
		}
	}
	
	private void deleteProductImgList(long productId) {
		//根据productId获取原来的图片
		List<ProductImg> productImgs = productImgDao.queryProductImgList(productId);
		//删除原来的图片
		for(ProductImg productImg : productImgs) {
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		//删除数据库里原有图片的信息
		productImgDao.deleteProductImgByProductId(productId);
	}

}
