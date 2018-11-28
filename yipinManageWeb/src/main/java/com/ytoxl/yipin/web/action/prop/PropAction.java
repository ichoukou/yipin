package com.ytoxl.yipin.web.action.prop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ytoxl.module.core.common.pagination.BasePagination;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.Prop;
import com.ytoxl.module.yipin.base.service.PropService;
import com.ytoxl.yipin.web.action.BaseAction;

/**
 * @作者：罗典
 * @描述：基础信息操作请求
 * @时间：2014-01-10
 * */
@Controller
public class PropAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(PropAction.class);
	private List<Prop> propList = new ArrayList<Prop>();
	// 层级基础信息
	private Prop prop;
	// 大区名
	private String regionName;
	// 省份名
	private String provinceName;
	@Autowired
	private PropService propService;
	// 分页查询
	private BasePagination<Prop> propPage;
	// 子集是否能进行操作(为0时不可操作)
	private Integer isCanOperate;
	/**
	 * @作者：罗典
	 * @描述: 新增基础信息
	 * @时间：2014-01-10
	 * */
	public String addProp() {
		try {
			if(prop != null){
				propService.add(prop);
			}
		} catch (YiPinStoreException e) {
			logger.error("新增基础信息", e);
		} catch (Exception e) {
			logger.error("新增基础信息", e);
		}
		return "loadRegion";
	}
	
	/**
	 * @作者：罗典
	 * @描述：编辑基础信息
	 * @时间：2014-01-11
	 * */
	public String editProp(){
		try {
			// 传入status时为激活禁用功能，否则则为编辑功能
			if(prop != null && prop.getStatus() == null){
				propService.update(prop);
			}else if(prop != null && prop.getStatus() != null){
				propService.updateStatusByCode(prop);
			}
		} catch (YiPinStoreException e) {
			logger.error("新增基础信息", e);
		} catch (Exception e) {
			logger.error("新增基础信息", e);
		}
		return "loadRegion";
	}
	
	/**
	 * @作者：罗典
	 * @描述: 区域信息加载
	 * @时间：2014-01-10
	 * */
	public String loadRegion(){
		try {
			// 行政等级为空时，默认查询区域信息
			if(prop == null){
				prop = new Prop();
				prop.setLevel(Prop.LEVEL_PLACE);
				prop.setCode(Prop.CODE_PLACE);
				// 查询原产地的ID
				List<Prop> propLists = propService.listByProp(prop);
				if(propLists != null && propLists.size() > 0){
					prop = propLists.get(0);
					// 将原产地的 ID作为parentId传到界面以便于新增下级大区
					prop.setParentId(prop.getPropId());
					prop.setLevel(prop.getLevel()+1);
					prop.setParentCode(Prop.CODE_PLACE);
				}
			}
			// 对于加载省市时，加入上级名称
			if(prop.getLevel() == Prop.LEVEL_PROVINCE && (regionName == null || "".equals(regionName))){
				this.regionName = prop.getName();
			}else if(prop.getLevel() == Prop.LEVEL_CITY && (provinceName == null || "".equals(provinceName))){
				this.provinceName = prop.getName();
			}
			Prop propCondition = new Prop();
			// 分页条件
			if(propPage == null){
				propPage = new BasePagination<Prop>();
				propCondition.setLevel(prop.getLevel());
				propCondition.setParentId(prop.getParentId());
				propPage.setParams(this.convertToMap(propCondition));
			}
			propCondition = this.convertToProp(propPage);
			propList = propService.listByProp(propCondition);
			int totalCount = propService.listCountByProp(propCondition);
			propPage.setResult(propList);
			propPage.setTotal(totalCount);
		} catch (YiPinStoreException e) {
			logger.error("区域信息加载", e);
		} catch (Exception e) {
			logger.error("区域信息加载", e);
		}
		return SUCCESS;
	}

	// 根据实体条件转换成map对象
	private Map<String,String> convertToMap(Prop prop){
		Map<String,String> map = new HashMap<String,String>();
		map.put("level", prop.getLevel() == null ? null:prop.getLevel().toString());
		map.put("code",prop.getCode());
		map.put("parentId", prop.getParentId() == null ? null : prop.getParentId().toString());
		return map; 
	}
	
	// 根据MAP对象转换成实体条件
	private Prop convertToProp( BasePagination<Prop> basePagination){
		Map<String,String> pramas = basePagination.getParams();
		Prop condition = new Prop();
		condition.setLevel(Integer.parseInt(pramas.get("level")));
		if(pramas.containsKey("parentId") && pramas.get("parentId") != null && !"".equals(pramas.get("parentId"))){
			condition.setParentId(Integer.parseInt(pramas.get("parentId")));
		}else{
			condition.setCode(pramas.get("code"));
		}
		condition.setLimit(basePagination.getLimit());
		condition.setStart(basePagination.getCurrentPage()*basePagination.getLimit());
		return condition;
	}
	
	public List<Prop> getPropList() {
		return propList;
	}

	public void setPropList(List<Prop> propList) {
		this.propList = propList;
	}

	public Prop getProp() {
		return prop;
	}

	public void setProp(Prop prop) {
		this.prop = prop;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public BasePagination<Prop> getPropPage() {
		return propPage;
	}

	public void setPropPage(BasePagination<Prop> propPage) {
		this.propPage = propPage;
	}

	public Integer getIsCanOperate() {
		return isCanOperate;
	}

	public void setIsCanOperate(Integer isCanOperate) {
		this.isCanOperate = isCanOperate;
	}

}
