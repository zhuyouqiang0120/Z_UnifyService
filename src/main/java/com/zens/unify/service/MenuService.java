package com.zens.unify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zens.unify.entity.Menu;
import com.zens.unify.entity.MenuExample;
import com.zens.unify.entity.MenuExample.Criteria;
import com.zens.unify.mapper.MenuMapper;
import com.zens.unify.utils.StringUtils;
/**
 * todo：
 * @author vector
 * @date 2014年7月15日 下午2:44:33
 */
@Service
public class MenuService extends AbstraceService<Menu> {

	@Autowired
	MenuMapper mapper;
	
	@Override
	public Integer save(Menu entity) {
		// TODO Auto-generated method stub'
		int i = mapper.updateByExampleSelective(entity, createExample(entity));
		return i > 0 ? i : mapper.insertSelective(entity);
	}

	
	@Override
	public Menu get(Menu t) {

		return mapper.selectByExample(t);
	}

	private MenuExample createExample(Menu menu){
		
		MenuExample example = new MenuExample();
		if(menu != null){
			Criteria criteria = example.createCriteria();
			if(StringUtils.hasText(menu.getDevice())){
				criteria.andDeviceEqualTo(menu.getDevice());
			}
		}
		return example;
	}
}