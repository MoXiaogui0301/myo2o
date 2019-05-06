package cn.dengxin.myo2o.dto;

import java.util.List;
import cn.dengxin.myo2o.entity.Product;
import cn.dengxin.myo2o.enums.ProductStateEnum;

public class ProductExecution {

	private int state;
	private String stateInfo;
	private int count;
	private Product product;
	private List<Product> productList;

	// 空构造方法
	public ProductExecution() {
		}

	// 构造方法：操作失败，返回错误码和错误信息
	public ProductExecution(ProductStateEnum stateEnum) {
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
		}

	// 构造方法：操作成功，返回状态码和状态信息，以及操作对象
	public ProductExecution(ProductStateEnum stateEnum,Product product) {
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
			this.product = product;
		}

	// 构造方法：操作成功，返回状态码和状态信息，以及shop列表
	public ProductExecution(ProductStateEnum stateEnum,List<Product> productList) {
			this.state = stateEnum.getState();
			this.stateInfo = stateEnum.getStateInfo();
			this.productList = productList;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

}
