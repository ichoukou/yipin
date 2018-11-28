package com.ytoxl.module.yipin.content.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.persistence.BaseSqlMapper;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.content.dataobject.Advertisement;

/**广告明细
 * @author zengzhiing
 *
 * @param <T>
 */
public interface AdvertisementMapper  <T extends Advertisement> extends BaseSqlMapper<T> {
	/** 根据查询条件查询广告明细
	 * @param searchParams
	 * @return
	 * @throws DataAccessException
	 */
	public List<Advertisement> searchAdvertisementByCondition(Map<String, Object> searchParams) throws DataAccessException;
	
	/**得到广告明细总数
	 * @param searchParams
	 * @return
	 * @throws DataAccessException
	 */
	public Integer searchAdvertisementByConditionCount(Map<String, Object> searchParams) throws DataAccessException;
	/**删除广告明细
	 * @throws YiPinStoreException
	 */
	public void deleteAdv(Integer  advertisementId)throws DataAccessException ;
	
	/**根据大区id得到广告
	 * @return
	 * @throws YiPinStoreException
	 */
	public List<Advertisement> selectAdvByAddressAndCategory(@Param("parentId")Integer parentId)throws DataAccessException ;
	
	/**根据positionCode得到广告
	 * @param code
	 * @return
	 * @throws DataAccessException
	 */
	public List<Advertisement> selectAdvPic(@Param("code")String code)throws DataAccessException ;
}
