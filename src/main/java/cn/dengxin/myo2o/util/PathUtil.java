package cn.dengxin.myo2o.util;

/*
 * 获取路径的工具类
 */
public class PathUtil {

	//获取系统目录分隔符
	private static String separator = System.getProperty("file.separator");
	
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/projectdev/image/";
		}else {
			basePath = "/Users/dengxin/资料/技术资料/myo2o/image/";
		}
		//更换分隔符
		basePath = basePath.replace("/", separator);
		return basePath;
	}
	
	/**
	 * 获取店铺图片的存储路径(将各店铺图片存储在各店铺目录下)
	 * @param shopId
	 */
	public static String getShopImagePath(long shopId) {
		String imagePath="upload/item/shop/"+shopId+"/";
		return imagePath.replace("/", separator);
	}
}
