package com.ytoxl.module.yipin.content.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Prop;
import com.ytoxl.module.yipin.content.dataobject.Advertisement;

/** 广告明细Service
 * @author zengzhiming
 *
 */
public interface AdvertisementService {
	/** 根据查询条件查询广告明细
	 * @param searchParams
	 * @return
	 * @throws DataAccessException
	 */
	public List<Advertisement> searchAdvertisementByCondition(Map<String, Object> searchParams) throws DataAccessException;

	/**得到所有广告明细
	 * @return
	 */
	public void showAdvertisement(BasePagination<Advertisement> advPage)throws YiPinStoreException ;
	
	/**根据主键
	 * @param advertisementId
	 * @return
	 * @throws DataAccessException
	 */
	public Advertisement get(Integer advertisementId);
	
	/**更新广告明细
	 * @param adv
	 * @throws YiPinStoreException
	 */
	public int updateAdvertisement(Advertisement adv)throws YiPinStoreException ;
	
	/**新增广告明细
	 * @param adv
	 * @throws YiPinStoreException
	 */
	public int addAdvertisement(Advertisement adv)throws YiPinStoreException,YtoxlUserException;
	
	/**删除广告明细
	 * @throws YiPinStoreException
	 */
	public void deleteAdv(Advertisement adv)throws YiPinStoreException ;
	
	/**根据大区id得到广告
	 * @return
	 * @throws YiPinStoreException
	 */
	public Map<String,List<Advertisement>> selectAdvByAddressAndCategory(Integer parentId)throws YiPinStoreException ;
	
	
	/**根据positionCode得到广告
	 * @param code
	 * @return
	 * @throws DataAccessException
	 */
	public List<Advertisement> selectAdvPic(String code)throws YiPinStoreException ;
	
	/**根据大区id得到广告
	 * @return
	 * @throws YiPinStoreException
	 */
	public List<Map<String,List<Advertisement>>> selectAdvByAddressAndCategory(List<Prop> ps )throws YiPinStoreException ;
}
