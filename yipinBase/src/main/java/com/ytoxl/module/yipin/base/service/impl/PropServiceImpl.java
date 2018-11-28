package com.ytoxl.module.yipin.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ytoxl.module.yipin.base.common.CommonConstants;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Prop;
import com.ytoxl.module.yipin.base.mapper.PropMapper;
import com.ytoxl.module.yipin.base.service.PropService;

/**
 * @作者：罗典
 * @时间：2014-01-10
 * @描述：分类层级基础数据操作
 * */
@Service
public class PropServiceImpl implements PropService {

	@Autowired
	private PropMapper<Prop> propMapper;

	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：以实体为条件查询信息集合
	 * */
	public List<Prop> listByProp(Prop prop)  throws YiPinStoreException{
		return propMapper.listByProp(prop);
	}
	
	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：根据编码批量修改状态(包括子集状态)
	 * */
	public int updateStatusByCode(Prop prop) throws YiPinStoreException{
		return propMapper.updateStatusByCode(prop);
	}
	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：以实体为条件查询信息集合总数
	 * */
	public int listCountByProp(Prop prop) throws YiPinStoreException{
		return propMapper.listCountByProp(prop);
	}
	
	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：分类层级基础数据新增
	 * */
	@Override
	public int add(Prop prop)  throws YiPinStoreException{
		return propMapper.add(prop);
	}

	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：分类层级基础数据修改(根据ID)
	 * */
	@Override
	public int update(Prop prop)  throws YiPinStoreException{
		return propMapper.update(prop);
	}

	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：分类层级基础数据查询(根据ID)
	 * */
	@Override
	public Prop get(Integer propId)  throws YiPinStoreException{
		return propMapper.get(propId);
	}

	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：分类层级基础数据删除(根据ID)
	 * */
	@Override
	public int del(Integer propId)  throws YiPinStoreException{
		return propMapper.del(propId);
	}
	
	/**
	 * @作者：罗典
	 * @时间：2014-01-15
	 * @描述：根据CODE查询单个实体
	 * */
	@Override
	public Prop getByCode(String code)throws YiPinStoreException{
		return propMapper.getByCode(code); 
	}

	@Override
	public List<Prop> getPropByIds(String[] ids) throws YiPinStoreException {
		return propMapper.getPropByIds(ids);
	}

	@Override
	public List<Prop> listParentProps(String code, Integer level)
			throws YiPinStoreException {
		List<Prop> props = new ArrayList<Prop>();
		if(level > 1){
			String[] str = code.split(CommonConstants.LEVEL_CODE_SPLIT);
			String prefix = str[0] + CommonConstants.LEVEL_CODE_SPLIT + str[1];
			props = propMapper.listParentProps(prefix, level);
		}
		return props;
	}
}
