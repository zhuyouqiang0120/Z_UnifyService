package com.zens.unify.service;

import java.util.List;

import com.zens.unify.entity.Page;

/**
 * todo：公用接口
 * @author vector
 * @date 2014年7月3日 下午1:55:57
 * @param <T>
 */
public interface Service<T> {
	
	String $ = "%";
	
	/**
	 * 保存对象
	 * @param entity
	 */
	public Integer save(T entity);
	
	/**
	 * 根据主键ID删除对应的实体
	 * @param id
	 */
	public Integer delete(Long id);
	/**
	 * 条件删除
	 * @param t
	 */
	public Integer delete(T t);
	
	/**
	 * 根据id集合删除
	 * @param id
	 */
	public Integer delete(List<Long> id);
	/**
	 * 修改
	 * @param t
	 * @return
	 */
	public Integer update(T t);
	
	/**
	 * 根据主键ID获取实体
	 * @param id
	 * @return
	 */
	public T get(Long id) ;
	/**
	 * 根据条件查询对象
	 * @param t
	 * @return
	 */
	public T get(T t);
	/**
	 * 查询复合条件的实体
	 * @param t
	 * @return
	 */
	public List<T> find(T t);
	
	/**
	 * 根据分页对象、过滤集合参数，分页查询列表
	 * @param page
	 * @param filters
	 * @return
	 */
	public Page<T> find(final Page<T> page);
	
	/**
	 * 获取所有实体
	 * @return
	 */
	public List<T> getAll();
	
}
