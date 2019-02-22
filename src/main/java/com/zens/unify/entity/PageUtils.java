package com.zens.unify.entity;

/**
 * 页面路径
 * @author vector
 * @time 2014年7月16日 下午3:19:06
 *
 */
public interface PageUtils {
	
	public static final String JSON = "json";

	public static final String DATA_INDEX = "data";

	public static final String REGIONS = "regions";

	public static final String CAMERA = "camera";
	
	
	public interface TaskConfig{
		public static final String INDEX = "config";
		
		public static final String REDIRECT = "redirect:/config";
	}
	

}
