package com.ytoxl.module.yipin.content.dataobject;

import com.ytoxl.module.yipin.base.dataobject.Prop;
import com.ytoxl.module.yipin.content.dataobject.tbl.AdvertisementTbl;

/**广告明细
 * @author zengzhiming
 *
 */
public class Advertisement extends AdvertisementTbl{
	
	/** 多对一 广告位	 */
	private AdvPosition position;
	/** 对应商品分类或是所在地*/
	private Prop p;
	/** 正常	 */
	public static final Integer ADV_NORMAL=0;
	/** 删除  */
	public static final Integer ADV_DELETE=1;
	
	
	
	
	
	
	public Prop getP() {
		return p;
	}

	public void setP(Prop p) {
		this.p = p;
	}

	public AdvPosition getPosition() {
		return position;
	}

	public void setPosition(AdvPosition position) {
		this.position = position;
	}
	
	
	
	
	
}
