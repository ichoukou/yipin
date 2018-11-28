package com.ytoxl.module.yipin.base.dataobject;

import com.ytoxl.module.yipin.base.dataobject.tbl.RegionTbl;



/**地址
 * @author liuchunyan
 *
 */
public class Region extends RegionTbl {
	
	//根据regionId查询详细信息返回的名称
	protected String county;    //县名称
	protected String city;      //市名称
	protected String province;  //省名称
	
	public final String PATH_SEPARATOR=" ";
	
	
	
	@Override
	public String toString() {
		return this.province+PATH_SEPARATOR+this.city+PATH_SEPARATOR+this.county;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
}
