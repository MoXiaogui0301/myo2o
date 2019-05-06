package cn.dengxin.myo2o.dto;

import java.util.List;

import cn.dengxin.myo2o.entity.Shop;
import cn.dengxin.myo2o.enums.ShopStateEnum;

public class ShopExecution {
	
	private int state;
	private String stateInfo;
	private int count;
	private Shop shop;
	private List<Shop> shopList;
	
	//空构造方法
	public ShopExecution() {
	}
	
	//构造方法：操作失败，返回错误码和错误信息
	public ShopExecution(ShopStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//构造方法：操作成功，返回状态码和状态信息，以及操作对象
	public ShopExecution(ShopStateEnum stateEnum,Shop shop) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}
	
	//构造方法：操作成功，返回状态码和状态信息，以及shop列表
	public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	
}
