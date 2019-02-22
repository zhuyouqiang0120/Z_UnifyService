package com.zens.unify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zens.unify.entity.Data;
import com.zens.unify.entity.DataExample;
import com.zens.unify.entity.DataExample.Criteria;
import com.zens.unify.entity.Page;
import com.zens.unify.mapper.DataMapper;
/**
 * 数据接口
 * @author vector
 * @time 2014年7月16日 下午2:05:27
 *
 */
@Service
public class DataService extends AbstraceService<Data> {

	@Autowired
	private DataMapper mapper;
	
	@Override
	public Integer save(Data entity) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(entity);
	}

	@Override
	public Integer delete(Long id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer update(Data t) {

		return mapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public List<Data> find(Data t) {
		DataExample example = createExample(t);
		
		return mapper.selectByExample(example);
	}

	@Override
	public Page<Data> find(Page<Data> page) {
		// TODO Auto-generated method stub
		DataExample example = createExample(page.getObject());
		page.setTotalCount(mapper.countByExample(example));
		page.setResult(mapper.selectByExample(example));
		return page;
	}

	/**
	 * 
	 * @param taskConfigId
	 * @return
	 */
	public Data getDataByTaskConfigId(Long taskConfigId){
		return mapper.getDataByTaskConfigId(taskConfigId);
	}
	
	/**
	 * 创建查询条件
	 * @param data
	 * @return
	 */
	private DataExample createExample(Data data){
		DataExample example = new DataExample();
		Criteria criteria = example.createCriteria();
//		if(StringUtils.hasText(data.getCity()))
//			criteria.andFcityEqualTo(data.getCity());
		if(data.getTaskConfigId() != null)
			criteria.andFtaskconfigidEqualTo(data.getTaskConfigId());
		
		return example;
	}
}
