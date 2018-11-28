package com.ytoxl.module.yipin.base.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.user.dataobject.Uresource;

/**资源Mapper
 * @author zengzhiming
 *
 * @param <T>
 */
public interface MyUresourceMapper<T extends Uresource> extends BaseSqlMapper<T> {
	/**根据角色得到对应资源
	 * @param uroleId
	 * @return
	 * @throws DataAccessException
	 */
	List<Uresource> getUresourcesByUroleId(Integer uroleId) throws DataAccessException;
	
	/**根据角色删除对应资源
	 * @param uroleId
	 * @throws DataAccessException
	 */
	void deleteUroleUresourceByUroleId(Integer uroleId) throws DataAccessException;
	
	/**创建某角色对应的资源
	 * @param uroleId
	 * @param uresourceIds
	 * @throws DataAccessException
	 */
	void addUroleUresources(@Param("uroleId")Integer uroleId,@Param("uresourceIds")List<Integer> uresourceIds)throws DataAccessException;
}
