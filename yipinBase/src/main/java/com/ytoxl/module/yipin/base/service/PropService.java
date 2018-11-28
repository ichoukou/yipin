package com.ytoxl.module.yipin.base.service;

import java.util.List;

import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Prop;

/**
 * @作者：罗典
 * @时间：2014-01-10
 * @描述：分类层级基础数据操作
 * */
public interface PropService {
	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：以实体为条件查询信息集合
	 * */
	public List<Prop> listByProp(Prop prop) throws YiPinStoreException;
	
	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：根据编码批量修改状态(包括子集状态)
	 * */
	public int updateStatusByCode(Prop prop) throws YiPinStoreException;
	
	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：以实体为条件查询信息集合总数
	 * */
	public int listCountByProp(Prop prop) throws YiPinStoreException;
	
	
	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：分类层级基础数据新增
	 * */
	public int add(Prop prop) throws YiPinStoreException;

	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：分类层级基础数据修改(根据ID)
	 * */
	public int update(Prop prop) throws YiPinStoreException;

	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：分类层级基础数据查询(根据ID)
	 * */
	public Prop get(Integer propId) throws YiPinStoreException;

	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：分类层级基础数据删除(根据ID)
	 * */
	public int del(Integer propId) throws YiPinStoreException;
	/**
	 * @作者：罗典
	 * @时间：2014-01-15
	 * @描述：根据CODE查询单个实体
	 * */
	public Prop getByCode(String code)throws YiPinStoreException;
	
	public List<Prop> getPropByIds(String[] ids) throws YiPinStoreException;
	
	/**
	 * 通过子点节查询所有父节点
	 * @param code
	 * @param level
	 * @return
	 */
	public List<Prop> listParentProps(String code, Integer level) throws YiPinStoreException;
}
