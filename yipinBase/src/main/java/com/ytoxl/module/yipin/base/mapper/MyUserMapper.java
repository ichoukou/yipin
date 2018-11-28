package com.ytoxl.module.yipin.base.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.dataobject.MyUser;

/**用户Mapper
 * @author zengzhiming
 *
 * @param <T>
 */
public interface MyUserMapper<T extends MyUser> extends BaseSqlMapper<T> {
	/**获得父节点 所创建的用户集合
	 * @param createUserId
	 * @return
	 * @throws DataAccessException
	 */
	List<MyUser> listUserBycreateUserId(Integer createUserId) throws DataAccessException;
	/**添加角色对应用户数据
	 * @param uroleId
	 * @param userId
	 * @throws DataAccessException
	 */
	void addUserUroles(@Param("uroleId")Integer uroleId,@Param("userId")Integer userId)throws DataAccessException;
	/**根据用户id删除用户角色表数据
	 * @param userId
	 * @throws DataAccessException
	 */
	void delUserUroles(Integer userId) throws DataAccessException;
	/**更改用户状态
	 * @param myUser
	 * @throws DataAccessException
	 */
	void updateStatusByUserId(MyUser myUser) throws DataAccessException;
}
