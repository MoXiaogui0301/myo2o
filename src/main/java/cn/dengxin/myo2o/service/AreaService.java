package cn.dengxin.myo2o.service;

import java.util.List;

import cn.dengxin.myo2o.entity.Area;

public interface AreaService {

	String AREALISTKEY = "arealist";

	List<Area> getAreaList();
}
