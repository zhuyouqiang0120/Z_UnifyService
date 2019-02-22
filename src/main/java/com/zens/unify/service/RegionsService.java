package com.zens.unify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zens.unify.entity.Page;
import com.zens.unify.entity.Regions;
import com.zens.unify.entity.RegionsExample;
import com.zens.unify.entity.RegionsExample.Criteria;
import com.zens.unify.mapper.RegionsMapper;
import com.zens.unify.utils.StringUtils;
/**
 * todo：
 * @author vector
 * @date 2014年7月15日 下午2:44:33
 */
@Service
public class RegionsService extends AbstraceService<Regions> {

	@Autowired
	RegionsMapper mapper;
	
	@Override
	public Integer save(Regions entity) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(entity);
	}

	@Override
	public Page<Regions> find(Page<Regions> page) {
		// TODO Auto-generated method stub

		Regions cfg = page.getObject();
		RegionsExample example = createExample(cfg);

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
	public Integer update(Regions t) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public List<Regions> find(Regions t) {
		// TODO Auto-generated method stub
		RegionsExample example = createExample(t);
		return mapper.selectByExample(example);
	}
	
	private RegionsExample createExample(Regions regions){
		
		RegionsExample example = new RegionsExample();
		if(regions != null){
			Criteria criteria = example.createCriteria();
			if(StringUtils.hasText(regions.getName())){
				criteria.andFnameLike(like(regions.getName()));
			}
			if(StringUtils.hasText(regions.getCnName()))
				criteria.andFcnnameLike(like(regions.getCnName()));
			if(regions.getParentRegionsId() != null){
				criteria.andParentRegionsIdEqualTo(regions.getParentRegionsId());
			}
			if(regions.getId() != null)
				criteria.andIdEqualTo(regions.getId());
		}
		return example;
	}
}