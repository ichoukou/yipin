package com.ytoxl.module.yipin.content.dataobject;

import java.util.List;

import com.ytoxl.module.yipin.content.dataobject.tbl.AdvPositionTbl;

/** 广告位
 * @author zengzhiming
 *
 */
public class AdvPosition extends AdvPositionTbl{
	
	/** 广告明细	 */
	private List<Advertisement> advs;
	/**所在地	 */
	public static  final String  ADRESS_PRODUCT="ADRESS_PRODUCT";
	/**轮播图	 */
	public static  final String  SHUFFLING_FIGURE="SHUFFLING_FIGURE";
	/**商品分类	 */
	public static final String PRODUCT_CATEGORY="PRODUCT_CATEGORY";
	
	
	
	
	public List<Advertisement> getAdvs() {
		return advs;
	}

	public void setAdvs(List<Advertisement> advs) {
		this.advs = advs;
	}
	
	
	
}
