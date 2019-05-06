package cn.dengxin.myo2o.web.shopadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dengxin.myo2o.dto.ProductCategoryExecution;
import cn.dengxin.myo2o.dto.Result;
import cn.dengxin.myo2o.entity.ProductCategory;
import cn.dengxin.myo2o.entity.Shop;
import cn.dengxin.myo2o.enums.ProductCategoryStateEnum;
import cn.dengxin.myo2o.exceptions.ProductCategoryOperationException;
import cn.dengxin.myo2o.service.ProductCategoryService;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {

	@Autowired
	private ProductCategoryService productCategoryService;

	@RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
	@ResponseBody
	private Result<List<ProductCategory>> getProductCategoryInfo(
			HttpServletRequest request) {
		Shop currentShop = (Shop) request.getSession()
				.getAttribute("currentShop");
		List<ProductCategory> list = null;
		if (currentShop != null && currentShop.getShopId() > 0) {
			list = productCategoryService
					.getProductCategoryList(currentShop.getShopId());
			return new Result<List<ProductCategory>>(true, list);
		} else {
			ProductCategoryStateEnum pStateEnum = ProductCategoryStateEnum.INNER_ERROR;
			return new Result<List<ProductCategory>>(false,
					pStateEnum.getState(), pStateEnum.getStateInfo());
		}
	}

	@RequestMapping(value="/addproductcategories", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addProductCategories(
			@RequestBody List<ProductCategory> productCategories,
			HttpServletRequest request) {
		Map<String,Object> modelMap = new HashMap<>();
		Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
		for(ProductCategory pc : productCategories) {
			pc.setShopId(currentShop.getShopId());
		}
		if (productCategories != null && productCategories.size() > 0) {
			try {
				ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(productCategories);
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg" , pe.getStateInfo());
				}
			}catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少输入一个商品类别");
		}
		return modelMap;
	}
	
	@RequestMapping(value="/removeproductcategory", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> removeProductCategory(
			Long productCategoryId, HttpServletRequest request) {
		Map<String,Object> modelMap = new HashMap<>();
		Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
		if (productCategoryId != null && productCategoryId > 0) {
			try {
				ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
				if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg" , pe.getStateInfo());
				}
			}catch (ProductCategoryOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请至少选择一个商品类别");
		}
		return modelMap;
	}

}
