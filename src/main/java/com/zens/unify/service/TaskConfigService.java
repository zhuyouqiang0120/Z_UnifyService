package com.zens.unify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zens.unify.entity.Page;
import com.zens.unify.entity.TaskConfig;
import com.zens.unify.entity.TaskConfigExample;
import com.zens.unify.entity.TaskConfigExample.Criteria;
import com.zens.unify.mapper.TaskConfigMapper;
/**
 * todo：
 * @author vector
 * @date 2014年7月15日 下午2:44:33
 */
@Service
public class TaskConfigService extends AbstraceService<TaskConfig> {

	@Autowired
	TaskConfigMapper mapper;
	
	@Override
	public Integer save(TaskConfig entity) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(entity);
	}

	@Override
	public Page<TaskConfig> find(Page<TaskConfig> page) {
		// TODO Auto-generated method stub

		TaskConfig cfg = page.getObject();
		TaskConfigExample example = createExample(cfg);

		if(page.getPageSize() != null && page.getPageSize() > 0){
			example.setLimit(page.getPageSize());
			example.setStart(page.getFirst());
			page.setTotalCount(mapper.countByExample(example));
		}
		page.setResult(mapper.selectByExample(example));
		
		return page;
	}

	@Override
	public TaskConfig get(Long id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer update(TaskConfig t) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public TaskConfig get(TaskConfig t) {
		TaskConfigExample example = createExample(t);
		List<TaskConfig> configs = mapper.selectByExample(example);
		return configs.isEmpty() ? null : configs.get(0);
	}

	@Override
	public List<TaskConfig> find(TaskConfig t) {
		// TODO Auto-generated method stub
		TaskConfigExample example = createExample(t);
		example.setOrderByClause(" ID DESC ");
		return mapper.selectByExample(example);
	}
	

	private TaskConfigExample createExample(TaskConfig cfg){
		
		TaskConfigExample example = new TaskConfigExample();
		if(cfg != null){
			Criteria criteria = example.createCriteria();
			if (StringUtils.hasText(cfg.getCode()))
				criteria.andFcodeEqualTo(cfg.getCode());
			if (StringUtils.hasText(cfg.getName()))
				criteria.andFnameEqualTo(cfg.getName());
			if(cfg.getStatus() != null)
				criteria.andStatusEqualTo(cfg.getStatus());
			if(cfg.getIsTask() != null)
				criteria.andFistaskEqualTo(cfg.getIsTask());
				
		}
		return example;
	}
}