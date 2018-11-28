package com.ytoxl.yipin.web.action.advertisement;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;

import org.apache.axis2.databinding.utils.BeanUtil;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.user.common.exception.YtoxlUserException;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Prop;
import com.ytoxl.module.yipin.base.service.PropService;
import com.ytoxl.module.yipin.content.dataobject.AdvPosition;
import com.ytoxl.module.yipin.content.dataobject.Advertisement;
import com.ytoxl.module.yipin.content.service.AdvPositionService;
import com.ytoxl.module.yipin.content.service.AdvertisementService;
import com.ytoxl.yipin.web.action.BaseAction;

/**广告位
 * @author user
 *
 */
public class AdvertisementAction extends BaseAction{

	/**	 */
	private static final long serialVersionUID = -3067586586386175669L;
	private static Logger logger = LoggerFactory.getLogger(AdvertisementAction.class);
	
	@Autowired
	private AdvertisementService advertisementService;
	@Autowired
	private AdvPositionService advPositionService;
	@Autowired
	private PropService propService;
	
	/**分页对象	 */
	private BasePagination<Advertisement> advPage;
	/**广告明细	 */
	private Advertisement adv;
	/**廣告位	 */
	private AdvPosition position;
	/**跳转标示	 */
	private String index;
	/**跳转Action	 */
	private String nextAction;
	/**大区	 */
	private List<Prop> regionP;
	/**省份	 */
	private List<Prop> provinceP;
	/**广告位	 */
	private List<AdvPosition> positions;
	/**广告明细List	 */
	private List<Advertisement> advs;
	
	/**	 */
	private static final String ADD_ADV="addadv";
	/**	 */
	private static final String ADD_GO="go";
	/**广告明细不存在	 */
	private static final String DEL_ADV_INEXISTENCE="1";//
	/**删除广告明细异常	 */
	private static final String DEL_ADV_EXCEPTION="2";//
	/**删除广告明细成功	 */
	private static final String DEL_ADV_SUCCESS="3";//
	/** 展示广告明细列表
	 * @return
	 */
	public String show(){
		try{
			//查詢所有分頁的 廣告  
			if(advPage==null){//第一次点击
				advPage = new BasePagination<Advertisement>();
			}
			advPage.setSort("adv.createTime desc,adv.rank");//设置排序
			advPage.setDir("desc");
			positions = advPositionService.listPosition();//加載所有
			advertisementService.showAdvertisement(advPage);
			advertisementService.selectAdvByAddressAndCategory(10003);
		}catch(Exception e){
			logger.error("AdvertisementAction show :",e);
		}
		return SUCCESS;
	}
	
	/**新增或修改广告明细
	 * @return
	 */
	public String addAdv(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			Advertisement advClone = advertisementService.get(adv.getAdvertisementId());
			positions = advPositionService.listPosition();//加載所有
			if(advClone.getP()!=null){
				Prop p = advClone.getP();//得到二级
				Prop temp = new Prop();
				Prop temp1 = new Prop();
				temp.setLevel(Prop.LEVEL_REGION);//查找大区
				temp1.setParentId(p.getParentId());
				//需要区分 轮播图 所在地 商品分类
				if(advClone.getPosition().getCode().equals(AdvPosition.ADRESS_PRODUCT)){//一级 二级 地区
					temp.setCode(Prop.CODE_PLACE);
				}else if(advClone.getPosition().getCode().equals(AdvPosition.PRODUCT_CATEGORY)){//一级 二级 商品
					temp.setCode(Prop.CODE_PRODUCT);
				}/*else if(advClone.getPosition().getCode().equals(AdvPosition.SHUFFLING_FIGURE)){}*/
				regionP = propService.listByProp(temp);
				provinceP = propService.listByProp(temp1);
			}
			request.setAttribute("adv", advClone);
		}catch(Exception e){
			logger.error("AdvertisementAction addAdv:", e);
		}
		return ADD_ADV;
	}
	
	
	/**添加广告明细
	 * @return
	 * @throws YtoxlUserException 
	 */
	public String saveAdv(){
		try {
			if(adv!=null){
				if(adv.getAdvertisementId()!=null && !adv.getAdvertisementId().equals("")){//新增
					advertisementService.updateAdvertisement(adv);
				}else{//更新
					advertisementService.addAdvertisement(adv);
				}
			}
		} catch (YiPinStoreException e) {
			logger.error("AdvertisementAction saveAdv:", e);
		}catch(YtoxlUserException e){
			logger.error("AdvertisementAction saveAdv:", e);
		}
		this.nextAction="show.htm";
		return "saveAdv";
	}
	
	/**删除广告明细
	 * @return
	 */
	public String delAdv(){
		try{
			if(adv.getAdvertisementId()==null || adv.getAdvertisementId().equals("")){//为空则不对
				setMessage(DEL_ADV_INEXISTENCE,"广告明细不存在!");
				return JSONMSG;
			}
			advertisementService.deleteAdv(adv);
			setMessage(DEL_ADV_SUCCESS,"广告明细删除成功!");
		}catch(Exception e){
			logger.error("AdvertisementAction saveAdv:", e);
			setMessage(DEL_ADV_EXCEPTION,"广告明细删除异常");
		}
		return JSONMSG;
	}
	
	/** 跳转到添加广告页面
	 * @return
	 */
	public String goToAdd(){
		try {
			position = advPositionService.getPositionByCode(index);//根據code得到position
			Prop temp = new Prop();
			temp.setLevel(Prop.LEVEL_REGION);//查找大区
			if(index.equals(AdvPosition.ADRESS_PRODUCT)){//所在地
				temp.setCode(Prop.CODE_PLACE);
			}else if(index.equals(AdvPosition.PRODUCT_CATEGORY)){//商品类型
				temp.setCode(Prop.CODE_PRODUCT);
			}
			regionP = propService.listByProp(temp);
		} catch (YiPinStoreException e) {
			logger.error("AdvertisementAction goToAdd:",e);
		}//加載所有
		
		return ADD_GO;
	}
	
	public String showCategory(){
		try {
			Prop temp = new Prop();
			temp.setParentId(adv.getAdvertisementPositionId());
			List<Prop> list = propService.listByProp(temp);
			JSONArray jsonArray = JSONArray.fromObject(list);
			setMessage("1",jsonArray.toString());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("",e);
		}
		return JSONMSG;
	}
	
	
	
	public List<Prop> getRegionP() {
		return regionP;
	}

	public void setRegionP(List<Prop> regionP) {
		this.regionP = regionP;
	}

	public List<Prop> getProvinceP() {
		return provinceP;
	}

	public void setProvinceP(List<Prop> provinceP) {
		this.provinceP = provinceP;
	}

	public AdvPosition getPosition() {
		return position;
	}

	public void setPosition(AdvPosition position) {
		this.position = position;
	}


	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getNextAction() {
		return nextAction;
	}

	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}

	public Advertisement getAdv() {
		return adv;
	}

	public void setAdv(Advertisement adv) {
		this.adv = adv;
	}

	public List<Advertisement> getAdvs() {
		return advs;
	}

	public void setAdvs(List<Advertisement> advs) {
		this.advs = advs;
	}

	public List<AdvPosition> getPositions() {
		return positions;
	}

	public void setPositions(List<AdvPosition> positions) {
		this.positions = positions;
	}

	public BasePagination<Advertisement> getAdvPage() {
		return advPage;
	}

	public void setAdvPage(BasePagination<Advertisement> advPage) {
		this.advPage = advPage;
	}
	
	
	
}
