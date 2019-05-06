package cn.dengxin.myo2o.enums;

public enum ProductStateEnum {
	
	SUCCESS(1,"创建成功"),PASS(2,"通过认证"),INNER_ERROR(-1001,"内部系统错误"),EMPTY_LIST(-1002,"添加数少于1"),EMPTY_PRODUCT(-1003,"商品为空");
	
	private int state;
	private String stateInfo;
	
	private ProductStateEnum(int state,String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	//根据传入的state值返回相应的enum值
	public static ProductStateEnum stateOf(int state) {
		for(ProductStateEnum stateEnum:values()) {
			if (stateEnum.getState() == state) {
				return stateEnum;
			}
		}
		return null;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
}
