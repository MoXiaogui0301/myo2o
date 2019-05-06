package cn.dengxin.myo2o.dto;

import java.util.List;

import cn.dengxin.myo2o.entity.ProductCategory;
import cn.dengxin.myo2o.enums.ProductCategoryStateEnum;

public class ProductCategoryExecution {
	
	private int state;
	private String stateInfo;
	private List<ProductCategory> productCategoryList;
	
	//空构造方法
	public ProductCategoryExecution() {
	}
	
	//构造方法：操作失败，返回错误码和错误信息
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//构造方法：操作成功，返回状态码和状态信息，以及操作对象
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum,List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
	}
	

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}

}
