package com.ytoxl.module.yipin.content.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.user.service.UserService;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Prop;
import com.ytoxl.module.yipin.content.dataobject.Advertisement;
import com.ytoxl.module.yipin.content.mapper.AdvertisementMapper;
import com.ytoxl.module.yipin.content.service.AdvertisementService;

/**广告明细ServiceImpl
 * @author zengzhiming
 *
 */
@Service
public class AdvertisementServiceImpl implements  AdvertisementService {
	@Autowired
	private AdvertisementMapper<Advertisement> advertisementMapper;
	@Autowired 
	private UserService userService;
	@Override
	public List<Advertisement> searchAdvertisementByCondition(
			Map<String, Object> searchParams) throws DataAccessException {
		return advertisementMapper.searchAdvertisementByCondition(searchParams);
	}
	
	
	public void showAdvertisement(BasePagination<Advertisement> advPage)throws YiPinStoreException {
		Map<String, Object>  params =  advPage.getSearchParamsMap();
		if(advPage.isNeedSetTotal()){//是否分页
			Integer total = advertisementMapper.searchAdvertisementByConditionCount(params);
			advPage.setTotal(total);
		}
		List<Advertisement> result = advertisementMapper.searchAdvertisementByCondition(params);
		advPage.setResult(result);
	}
	/**根据主键
	 * @param advertisementId
	 * @return
	 * @throws DataAccessException
	 */
	public Advertisement get(Integer advertisementId){
		return advertisementMapper.get(advertisementId);
	}
	/**更新广告明细
	 * @param adv
	 * @throws YiPinStoreException
	 */
	public int updateAdvertisement(Advertisement adv)throws YiPinStoreException {
		return advertisementMapper.update(adv);
	}
	
	/**新增广告明细
	 * @param adv
	 * @throws YiPinStoreException
	 * @throws YtoxlUserException 
	 */
	public int addAdvertisement(Advertisement adv)throws YiPinStoreException, YtoxlUserException {
		adv.setCreateUserId(userService.getCurrentUser().getUserId());
		return advertisementMapper.add(adv);
	}

	/**删除广告明细
	 * @throws YiPinStoreException
	 */
	public void deleteAdv(Advertisement adv)throws YiPinStoreException {
		advertisementMapper.deleteAdv(adv.getAdvertisementId());
	}
	
	/**根据大区id得到广告
	 * @return
	 * @throws YiPinStoreException
	 */
	public List<Map<String,List<Advertisement>>> selectAdvByAddressAndCategory(List<Prop> ps )throws YiPinStoreException {
		List<Map<String,List<Advertisement>>> listMap = null;
		if(ps!=null&&ps.size()>0){
			listMap = new ArrayList<Map<String,List<Advertisement>>>();
			for(Prop pp:ps){
					Map<String,List<Advertisement>> map =  selectAdvByAddressAndCategory(pp.getPropId());
					listMap.add(map);
			}
		}
		return listMap;
	}
	
	
	
	
	/**根据大区id得到广告
	 * @return
	 * @throws YiPinStoreException
	 */
	public Map<String,List<Advertisement>> selectAdvByAddressAndCategory(Integer parentId)throws YiPinStoreException {
		//得到二级地区对应的广告
		List<Advertisement> list = advertisementMapper.selectAdvByAddressAndCategory(parentId);
		Map<String,List<Advertisement>> map = new HashMap<String,List<Advertisement>>();
		if(list!=null&&list.size()>0){//遍历
			for(Advertisement adv :list){
				Prop p = adv.getP();
				List<Advertisement> l = map.get(p.getPropId()+"-"+p.getName());//判断有没有这个map
				if(l!=null){
					l.add(adv);
					map.put(p.getPropId()+"-"+p.getName(), l);
				}else{
					l = new ArrayList<Advertisement>();
					l.add(adv);
					map.put(p.getPropId()+"-"+p.getName(), l);
				}
			}
		}
		return map;
	}
	/**根据positionCode得到广告
	 * @param code
	 * @return
	 * @throws DataAccessException
	 */
	public List<Advertisement> selectAdvPic(String code)throws YiPinStoreException {
		return advertisementMapper.selectAdvPic(code);
	}
}
