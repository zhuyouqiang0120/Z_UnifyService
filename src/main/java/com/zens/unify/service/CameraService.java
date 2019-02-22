package com.zens.unify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zens.unify.entity.Page;
import com.zens.unify.entity.Camera;
import com.zens.unify.entity.CameraExample;
import com.zens.unify.entity.CameraExample.Criteria;
import com.zens.unify.mapper.CameraMapper;
import com.zens.unify.utils.StringUtils;
/**
 * todo：
 * @author vector
 * @date 2014年7月15日 下午2:44:33
 */
@Service
public class CameraService extends AbstraceService<Camera> {

	@Autowired
	CameraMapper mapper;
	
	@Override
	public Integer save(Camera entity) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(entity);
	}

	@Override
	public Page<Camera> find(Page<Camera> page) {
		// TODO Auto-generated method stub

		Camera cfg = page.getObject();
		CameraExample example = createExample(cfg);

		if(page.getPageSize() != null && page.getPageSize() > 0){
			example.setLimit(page.getPageSize());
			example.setStart(page.getFirst());
			page.setTotalCount(mapper.countByExample(example));
		}
		page.setResult(mapper.selectByExample(example));
		
		return page;
	}

	@Override
	public Integer delete(Long id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(Camera t) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public List<Camera> find(Camera t) {
		// TODO Auto-generated method stub
		CameraExample example = createExample(t);
		
		return mapper.selectByExample(example);
	}
	
	private CameraExample createExample(Camera camera){
		
		CameraExample example = new CameraExample();
		if(camera != null){
			Criteria criteria = example.createCriteria();
			if(StringUtils.hasText(camera.getName())){
				criteria.andNameLike(like(camera.getName()));
			}
			if(camera.getRegionsId() != null)
				criteria.andRegionsIdEqualTo(camera.getRegionsId());
			if(StringUtils.hasText(camera.getCnName())){
				criteria.andCnNameLike(like(camera.getCnName().toUpperCase()));
			}
			if(camera.getId() != null)
				criteria.andIdEqualTo(camera.getId());
		}
		return example;
	}
}