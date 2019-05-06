package cn.dengxin.myo2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.dengxin.myo2o.entity.Area;
import cn.dengxin.myo2o.service.AreaService;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
	@Autowired
	private AreaService areaService;
	
	Logger logger = LoggerFactory.getLogger(AreaController.class);
	
	@RequestMapping(value="/listarea",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listArea(){
		logger.info("{} enter listarea",System.currentTimeMillis());
		logger.error("{} enter listarea",System.currentTimeMillis());
		Map<String, Object> modelMap = new HashMap<String,Object>();
		List<Area> list = new ArrayList<Area>();
		try {
			list = areaService.getAreaList();
			modelMap.put("rows", list);
			modelMap.put("total", list.size());
		}catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		logger.info("{} complete listarea", System.currentTimeMillis());
		logger.error("{} complete listarea",System.currentTimeMillis());
		return modelMap;
	}
}
