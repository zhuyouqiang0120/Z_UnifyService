package com.zens.unify.task.shanghai;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.zens.unify.entity.Camera;
import com.zens.unify.entity.CameraBase;
import com.zens.unify.entity.Menu;
import com.zens.unify.entity.TaskConfig;
import com.zens.unify.service.CameraService;
import com.zens.unify.service.MenuService;
import com.zens.unify.service.TaskConfigService;
import com.zens.unify.task.handler.LinuxSSh;
import com.zens.unify.utils.StringUtils;

/**
 * todo：定时任务
 * @author vector
 * @date 2014年7月15日 下午2:40:38
 */
public class MenuJob{
	
	Logger log = Logger.getLogger(getClass());
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	TaskConfigService taskConfigService;
	@Autowired
	CameraService cameraService;
	
//	private String path = "/vault/sata/media/c4cache/c4cam_record/CamRec_10.5.1.";
//	private String path = "/vault/sa120/c4cache/c4cam_record/CamRec_10.5.1.";
//	private String path = "D:\\Users\\record\\CamRec_10.5.1.";
	/**
	 * 定时抓取信息
	 * @return
	 */
	public void grab(){
		grabByFilepath();
	}
	public void grabByLinuxSSH(){
		
		TaskConfig config = taskConfigService.get(new TaskConfig("menu"));
		
		CameraBase base = new Gson().fromJson(config.getExpand(), CameraBase.class);
		Camera c = new Camera();
		List<Camera> cameras = cameraService.find(c);
		
		LinuxSSh ssh = new LinuxSSh(base.getSsh());
		for(Camera camera : cameras){
			if(StringUtils.hasText(camera.getUrl())){
			
				Set<String> YMD = new HashSet<String>();//年月日
				Map<String, Set<Integer>> hhMap = new HashMap<String, Set<Integer>>();//小时
				Map<String, List<String>> hHSSMap = new HashMap<String, List<String>>();//小时
				
				if(!config.getUrl().endsWith("/"))
					config.setUrl(config.getUrl() + "/");
				
				String baseDir = base.getDirPrefix() + camera.getUrl() + base.getDirSuffix();
				if(!baseDir.endsWith("/")){
					baseDir += "/";
				}
				String currentDir = config.getUrl() +baseDir + base.getFilePrefix()+"*"+base.getFileSuffix();//"CamRec_10.5.1." + i + "-554/";
				
				String command = "ls "+currentDir;
				//执行命令
				List<String> fileNames = ssh.exec(command);
				
				//获取日期
				for(String fileName : fileNames){
					fileName = fileName.substring(fileName.lastIndexOf("/")+1);
					String date = fileName.replace(base.getFilePrefix(), "").replace(base.getFileSuffix(), "").replace(":", "");
					String[] dd = date.split("-");
					
					String ymd = dd[0];
					int totalSs = Integer.parseInt(dd[1]);
					String flag = dd[2];
	
					int hh = totalSs / 3600;//小时
					int MM = (totalSs - hh * 3600)/600 * 10;//分钟
					int ss = (totalSs - hh * 3600 - MM * 60) + MM % 10;//秒
					
					//获取已有日期的小时
					Set<Integer> hhDatas = hhMap.get(ymd);
					if(hhDatas == null){
						hhDatas = new HashSet<Integer>();
					}
					hhDatas.add(hh);
					hhMap.put(ymd, hhDatas);
					
					String ymdHH = ymd+"_"+hh;
					List<String> mmssDatas = hHSSMap.get(ymdHH);
					if(mmssDatas == null)
						mmssDatas = new ArrayList<String>();
					mmssDatas.add("(\'mm\':"+MM+",\'ss\':"+ss+",\'flag\':"+flag+",\'path\':\'"+baseDir + fileName+"\')");
					hHSSMap.put(ymdHH, mmssDatas);
					
					YMD.add(ymd);
				}
				
				Gson gson = new Gson();
				StringBuilder date = new StringBuilder("'dList':"+gson.toJson(YMD) + ",");
				date.append(gson.toJson(hhMap).replace("{", "").replace("}", "") + ",");
				date.append(gson.toJson(hHSSMap).replace("{", "").replace("}", "").replace("\"(", "{").replace(")\"", "}").replace("\\u0027", "'"));
				
				Menu entity = new Menu();
				entity.setDate(date.toString());
				entity.setDevice(base.getDirPrefix() + camera.getUrl() + base.getDirSuffix());
				menuService.save(entity);
				
			}
		}
		ssh.close();
	}

	public void grabByFilepath(){
		
		TaskConfig config = taskConfigService.get(new TaskConfig("menu"));
		
		CameraBase base = new Gson().fromJson(config.getExpand(), CameraBase.class);
		Camera c = new Camera();
		List<Camera> cameras = cameraService.find(c);
		
		LinuxSSh ssh = new LinuxSSh(base.getSsh());
		for(Camera camera : cameras){
			if(StringUtils.hasText(camera.getUrl())){
			
				Set<String> YMD = new HashSet<String>();//年月日
				Map<String, Set<Integer>> hhMap = new HashMap<String, Set<Integer>>();//小时
				Map<String, List<String>> hHSSMap = new HashMap<String, List<String>>();//小时
				
				if(!config.getUrl().endsWith("/"))
					config.setUrl(config.getUrl() + "/");
				
				String baseDir = base.getDirPrefix() + camera.getUrl() + base.getDirSuffix();
				if(!baseDir.endsWith("/")){
					baseDir += "/";
				}
				String currentDir = config.getUrl() +baseDir + base.getFilePrefix()+"*"+base.getFileSuffix();//"CamRec_10.5.1." + i + "-554/";
	System.out.println("currentDir:"+currentDir);
				
				File dir = new File(currentDir);
				String[] fileNames = dir.list(new DirFilter(".ts"));
	System.out.println("fileNames:"+fileNames.length);
				/*
				String command = "ls "+currentDir;
				//执行命令
				List<String> fileNames = ssh.exec(command);*/
				
				//获取日期
				for(String fileName : fileNames){
//					fileName = fileName.substring(fileName.lastIndexOf("/")+1);
					String date = fileName.replace(base.getFilePrefix(), "").replace(base.getFileSuffix(), "").replace(":", "");
					String[] dd = date.split("-");
					
					String ymd = dd[0];
					int totalSs = Integer.parseInt(dd[1]);
					String flag = dd[2];
	
					int hh = totalSs / 3600;//小时
					int MM = (totalSs - hh * 3600)/600 * 10;//分钟
					int ss = (totalSs - hh * 3600 - MM * 60) + MM % 10;//秒
					
					//获取已有日期的小时
					Set<Integer> hhDatas = hhMap.get(ymd);
					if(hhDatas == null){
						hhDatas = new HashSet<Integer>();
					}
					hhDatas.add(hh);
					hhMap.put(ymd, hhDatas);
					
					String ymdHH = ymd+"_"+hh;
					List<String> mmssDatas = hHSSMap.get(ymdHH);
					if(mmssDatas == null)
						mmssDatas = new ArrayList<String>();
					mmssDatas.add("(\'mm\':"+MM+",\'ss\':"+ss+",\'flag\':"+flag+",\'path\':\'"+baseDir + fileName+"\')");
					hHSSMap.put(ymdHH, mmssDatas);
					
					YMD.add(ymd);
				}
				
				Gson gson = new Gson();
				StringBuilder date = new StringBuilder("'dList':"+gson.toJson(YMD) + ",");
				date.append(gson.toJson(hhMap).replace("{", "").replace("}", "") + ",");
				date.append(gson.toJson(hHSSMap).replace("{", "").replace("}", "").replace("\"(", "{").replace(")\"", "}").replace("\\u0027", "'"));
				
				Menu entity = new Menu();
				entity.setDate(date.toString());
				entity.setDevice(base.getDirPrefix() + camera.getUrl() + base.getDirSuffix());
				menuService.save(entity);
				
			}
		}
		ssh.close();
	}

	class DirFilter implements FilenameFilter {
		private String type;
		public DirFilter(String tp) {
			this.type = tp;
		}

		public boolean accept(File fl, String path) {
			File file = new File(path);
			String filename = file.getName();
			return filename.indexOf(type) != -1;
		}

	}
	
	@Deprecated
	public void localFileList(){

		TaskConfig config = taskConfigService.get(new TaskConfig("menu"));

		CameraBase base = new Gson().fromJson(config.getExpand(), CameraBase.class);
		List<Camera> cameras = cameraService.find(new Camera());
		for (Camera camera : cameras) {

			if (StringUtils.hasText(camera.getUrl())) {

				Set<String> YMD = new HashSet<String>();// 年月日
				Map<String, Set<Integer>> hhMap = new HashMap<String, Set<Integer>>();// 小时
				Map<String, List<String>> hHSSMap = new HashMap<String, List<String>>();// 小时

				if (!config.getUrl().endsWith("/"))
					config.setUrl(config.getUrl() + "/");

				String baseDir = base.getDirPrefix() + camera.getUrl()
						+ base.getDirSuffix();
				if (!baseDir.endsWith("/")) {
					baseDir += "/";
				}
				String currentDir = config.getUrl() + baseDir;// "CamRec_10.5.1."
																// + i + "-554";

				File dir = new File(currentDir);
				if (dir.exists()) {
					// 获取该文件夹下所有ts文件名
					String[] fileNames = dir.list(new DirFilter(base
							.getFileSuffix()));
					if (fileNames.length == 0)
						continue;

					// 获取日期
					for (String fileName : fileNames) {
						String date = fileName
								.replace(base.getFilePrefix(), "").replace(
										base.getFileSuffix(), "");
						String[] dd = date.split("-");

						String ymd = dd[0];
						int totalSs = Integer.parseInt(dd[1]);
						String flag = dd[2];

						int hh = totalSs / 3600;// 小时
						int MM = (totalSs - hh * 3600) / 600 * 10;// 分钟
						int ss = (totalSs - hh * 3600 - MM * 60) + MM % 10;// 秒

						// 获取已有日期的小时
						Set<Integer> hhDatas = hhMap.get(ymd);
						if (hhDatas == null) {
							hhDatas = new HashSet<Integer>();
						}
						hhDatas.add(hh);
						hhMap.put(ymd, hhDatas);

						String ymdHH = ymd + "_" + hh;
						List<String> mmssDatas = hHSSMap.get(ymdHH);
						if (mmssDatas == null)
							mmssDatas = new ArrayList<String>();
						mmssDatas.add("(\'mm\':" + MM + ",\'ss\':" + ss
								+ ",\'flag\':" + flag + ",\'path\':\'"
								+ baseDir + fileName + "\')");
						hHSSMap.put(ymdHH, mmssDatas);

						YMD.add(ymd);
					}

					Gson gson = new Gson();
					StringBuilder date = new StringBuilder("'dList':"
							+ gson.toJson(YMD) + ",");
					date.append(gson.toJson(hhMap).replace("{", "")
							.replace("}", "")
							+ ",");
					date.append(gson.toJson(hHSSMap).replace("{", "")
							.replace("}", "").replace("\"(", "{")
							.replace(")\"", "}").replace("\\u0027", "'"));

					Menu entity = new Menu();
					entity.setDate(date.toString());
					entity.setDevice(base.getDirPrefix() + camera.getUrl()
							+ base.getDirSuffix());
					menuService.save(entity);
				}
			}
		}
	}
}
