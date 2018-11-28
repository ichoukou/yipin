/**
 * ZoneSale.java
 * Created at Jan 10, 2014
 * Created by kuiYang
 * Copyright (C) 2014 SHANGHAI YUANTONG XINLONG E-Business Co.,Ltd, All rights reserved.
 */
package com.ytoxl.module.yipin.base.dataobject;

import com.ytoxl.module.yipin.base.dataobject.tbl.ZoneSaleTbl;

/**
 * <p>
 * ClassName: ZoneSale
 * </p>
 * <p>
 * Description: 专区销售
 * </p>
 * <p>
 * Author: Kui.Yang
 * </p>
 * <p>
 * Date: Jan 10, 2014
 * </p>
 */
public class ZoneSale extends ZoneSaleTbl {
	
	/**关联专区对象**/
	private Zone zone;

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}
	
}
