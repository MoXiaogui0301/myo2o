package cn.dengxin.myo2o.web.frontend;

import cn.dengxin.myo2o.dto.ShopExecution;
import cn.dengxin.myo2o.entity.Area;
import cn.dengxin.myo2o.entity.Shop;
import cn.dengxin.myo2o.entity.ShopCategory;
import cn.dengxin.myo2o.service.AreaService;
import cn.dengxin.myo2o.service.ShopCategoryService;
import cn.dengxin.myo2o.service.ShopService;
import cn.dengxin.myo2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dengxin on 2019/5/9 11:21 AM
 */

@Controller
@RequestMapping("/frontend")
public class ShopListController {
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private ShopService shopService;

    /**
     * 获取指定条件下的商品类别和区域信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/listshopspageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listShopPageInfo(HttpServletRequest request)  {
        Map<String,Object> modelMap = new HashMap<>();
        long parentId = HttpServletRequestUtil.getLong(request,"parentId");
        List<ShopCategory> shopCategoryList = null;
        if (parentId != -1)  {
            try {
                ShopCategory shopCategoryCondition = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId(parentId);
                shopCategoryCondition.setParent(parent);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
            } catch (Exception e)  {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
            }
        }else {
            try {
                //如果parentId不存在，则取出所有一级ShopCategory(用户在首页选择的是全部商店列表)
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            } catch (Exception e)  {
                modelMap.put("success",false);
                modelMap.put("errMsg",e.getMessage());
            }
        }
        modelMap.put("shopCategoryList",shopCategoryList);
        List<Area> areaList = null;
        try {
            //获取区域列表信息
            areaList = areaService.getAreaList();
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        } catch (Exception e)  {
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
        }
        return modelMap;
    }

    /**
     * 获取指定查询条件下的店铺列表
     * @param request
     * @return
     */
    @RequestMapping(value="/listshops",method=RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> listShops(HttpServletRequest request)  {
        Map<String,Object> modelMap = new HashMap<>();
        //获取页码
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        //获取一页需要显示的数据条数
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        if ((pageIndex > -1)&&(pageSize > -1))  {
            long parentId = HttpServletRequestUtil.getLong(request,"parentId");
            long shopCategoryId = HttpServletRequestUtil.getLong(request,"shopCategoryId");
            int areaId = HttpServletRequestUtil.getInt(request,"areaId");
            String shopName = HttpServletRequestUtil.getString(request,"shopName");
            //获取组合之后的查询条件
            Shop shopCondition = compactShopCondition4Search(parentId,shopCategoryId,areaId,shopName);
            ShopExecution se = shopService.getShopList(shopCondition,pageIndex,pageSize);
            modelMap.put("shopList",se.getShopList());
            modelMap.put("count",se.getCount());
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageSize or pageIndex");
        }
        return modelMap;
    }

    private Shop compactShopCondition4Search(long parentId,long shopCategoryId,int areaId,String shopName)  {
        Shop shopCondition = new Shop();
        if (parentId != -1L) {
            ShopCategory childCategory = new ShopCategory();
            ShopCategory parentCategory = new ShopCategory();
            parentCategory.setShopCategoryId(parentId);
            childCategory.setParent(parentCategory);
            shopCondition.setShopCategory(childCategory);
        }
        if (shopCategoryId != -1L) {
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shopCondition.setShopCategory(shopCategory);
        }
        if (areaId != -1L) {
            Area area = new Area();
            area.setAreaId(areaId);
            shopCondition.setArea(area);
        }
        if (shopName != null) {
            shopCondition.setShopName(shopName);
        }
        //前端展示的店铺都是审核成功的店铺
//        shopCondition.setEnableStatus(1);
        return shopCondition;
    }
}
