package com.zens.unify.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.zens.unify.entity.Page;
import com.zens.unify.entity.PageUtils;
import com.zens.unify.entity.Camera;
import com.zens.unify.service.CameraService;
import com.zens.unify.utils.FileUtils;
import com.zens.unify.utils.MD5Utils;
import com.zens.unify.utils.PinYinUtils;
import com.zens.unify.utils.StringUtils;

/**
 * 区域管理
 * @author vector
 * @time 2014年7月16日 下午3:16:45
 *
 */
@Controller
@RequestMapping("/camera")
public class CameraController {
	String basePath = "resources/cameras/imgs/";
	Logger log = Logger.getLogger(getClass());

	@Autowired
	CameraService cameraService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(){
		return PageUtils.CAMERA;
	}
	
	@RequestMapping("/data")
	public String data(Model model,Page<Camera> page,Camera camera){
		page.setPageSize(-1);
		return getData(model, page, camera);
	}
	
	private String getData(Model model,Page<Camera> page,Camera camera){
		page.setObject(camera);
		cameraService.find(page);
		
		model.addAttribute("result", new Gson().toJson(page));

		return PageUtils.JSON;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String list(Model model,Page<Camera> page,Camera camera){
		
		return getData(model, page, camera);
	}
	
	@RequestMapping("update")
	public String update(Model model,Camera camera,@RequestParam("file")MultipartFile file,HttpServletRequest request){
		if(StringUtils.hasText(camera.getName())){
			camera.setCnName(PinYinUtils.getFirstSpell(camera.getName()).toUpperCase());
		}
		if(!file.isEmpty()){
			try {
				String realPath = request.getSession().getServletContext().getRealPath("/");
				//项目名称
				String contextPath = request.getContextPath().concat("/");
				String fileName = file.getOriginalFilename();
				
				String suffix = FileUtils.getFileSuffix(fileName);
				
				fileName = FileUtils.getFileName(fileName);
				fileName = StringUtils.uuidOrigin(MD5Utils.getMD5String(file.getBytes()));
				
				String filePath = realPath.concat("/").concat(basePath);
				FileUtils.dirExists(filePath);
				file.transferTo(new File(filePath.concat(fileName).concat(suffix)));
				
				camera.setImg(contextPath.concat(basePath).concat(fileName).concat(suffix));
			} catch (IllegalStateException e) {
				log.debug(e.getMessage());
			} catch (IOException e) {
				log.debug(e.getMessage());
			}
		}
		int i;
		if(camera.getId() != null)
			i = cameraService.update(camera);
		else
			i = cameraService.save(camera);
		model.addAttribute("result", i);
		return PageUtils.JSON;
	}
	@RequestMapping("delete/{id}")
	public String delete(Model model,@RequestParam("id") String id){
		String [] idArr = id.split(",");
		for(String idStr : idArr){
			Long ID = Long.valueOf(idStr);
			cameraService.delete(ID);
		}
		model.addAttribute("result", 1);
		return PageUtils.JSON;
	}
}