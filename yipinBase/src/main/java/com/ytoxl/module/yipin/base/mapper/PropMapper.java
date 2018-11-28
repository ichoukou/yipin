package com.ytoxl.module.yipin.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.dataobject.Prop;

/**
 * @作者：罗典
 * @时间：2014-01-10
 * @描述：分类层级基础数据操作
 * */
public interface PropMapper<T extends Prop> extends BaseSqlMapper<T> {
	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：以实体为条件查询信息集合
	 * */
	public List<Prop> listByProp(Prop prop) throws DataAccessException;
	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：根据编码批量修改状态(包括子集状态)
	 * */
	public int updateStatusByCode(Prop prop) throws DataAccessException;
	/**
	 * @作者：罗典
	 * @时间：2014-01-10
	 * @描述：以实体为条件查询信息集合总数
	 * */
	public int listCountByProp(Prop prop) throws DataAccessException;
	/**
	 * @作者：罗典
	 * @时间：2014-01-15
	 * @描述：根据CODE查询单个实体
	 * */
	public Prop getByCode(String code) throws DataAccessException;
	
	public List<Prop> getPropByIds(String[] ids) throws DataAccessException;
	
	/**
	 * 通过子点节查询所有父节点
	 * @param code
	 * @param level
	 * @return
	 */
	public List<Prop> listParentProps(@Param("prefix")String prefix, @Param("level")Integer level) throws DataAccessException;
}
