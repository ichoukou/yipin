package com.ytoxl.module.yipin.base.mapper;


import java.util.List;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.dataobject.MyUrole;

/**角色表
 * @author zengzhiming
 *
 * @param <T>
 */
public interface MyUroleMapper<T extends MyUrole> extends BaseSqlMapper<T> {
	/**得到制定角色的用户id
	 * @param uroleId
	 * @return
	 * @throws DataAccessException
	 */
	List<Integer> listUserIdsByUroleId(Integer uroleId) throws DataAccessException;
	/**查询用户id的角色
	 * @param createByUserId
	 * @return
	 * @throws DataAccessException
	 */
	List<MyUrole> searchUroleByCreateUserId(Integer createByUserId)throws DataAccessException;
	/**判断当前角色对象数据是否重复
	 * @param urole
	 * @return
	 * @throws DataAccessException
	 */
	MyUrole checkUroleName(MyUrole urole)throws DataAccessException;
	/**得到当前用户id的角色
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 */
	MyUrole getUroleByuserId(Integer userId)throws DataAccessException;
}
