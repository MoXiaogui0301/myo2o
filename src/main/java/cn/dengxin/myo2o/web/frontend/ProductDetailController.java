package cn.dengxin.myo2o.web.frontend;

import cn.dengxin.myo2o.entity.Product;
import cn.dengxin.myo2o.service.ProductService;
import cn.dengxin.myo2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dengxin on 2019/5/11 9:56 PM
 */
@Controller
@RequestMapping("/frontend")
public class ProductDetailController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/listproductdetailpageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String ,Object> listProductDetailPageInfo(HttpServletRequest request)  {
        Map<String,Object> modelMap = new HashMap<>();
        //获取前台传递过来的productId
        long productId = HttpServletRequestUtil.getLong(request,"productId");
        Product product = null;
        if (productId != -1)  {
            product = productService.getProductById(productId);
            modelMap.put("product", product);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty productId");
        }
        return modelMap;
    }
}
