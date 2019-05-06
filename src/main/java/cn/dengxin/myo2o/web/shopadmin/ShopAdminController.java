package cn.dengxin.myo2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="shopadmin",method= {RequestMethod.GET})
public class ShopAdminController {
	
	@RequestMapping(value = "/shopoperation")
	public String shopOperation() {
		//已设置前缀为/WEB-INF/html/，后缀为.html
		return "shop/shopoperation";
	}
	
	@RequestMapping(value = "/shoplist")
	public String shopList() {
		return "shop/shoplist";
	}
	
	@RequestMapping(value = "/shopmanagement")
	public String shopManagement() {
		return "shop/shopmanagement";
	}
	
	@RequestMapping(value = "/productcategorymanagement")
	public String productCategoryManageMent() {
		return "shop/productcategorymanage";
	}
	
	@RequestMapping(value = "/productoperation")
	public String productOperation() {
		return "shop/productoperation";
	}
	
	@RequestMapping(value = "/productmanagement")
	public String productManagement() {
		//转发至商品管理页面
		return "shop/productmanagement";
	}
}
