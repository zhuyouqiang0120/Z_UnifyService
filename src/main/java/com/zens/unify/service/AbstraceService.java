package com.zens.unify.service;

import java.util.List;

import com.zens.unify.entity.Page;

/**
 * 实现Service接口
 * todo：
 * @author vector
 * @date 2014年7月3日 下午2:16:19
 * @param <T>
 */
public abstract class AbstraceService<T> implements Service<T> {

	@Override
	public abstract Integer save(T entity);

	@Override
	public Integer delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(List<Long> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> find(Page<T> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> find(T t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 条件
	 * @param like
	 * @return
	 */
	public String like(String like){
		return $+like+$;
	}
}
