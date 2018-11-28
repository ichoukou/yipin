package com.ytoxl.module.yipin.base.dataobject;

import java.util.List;

import com.ytoxl.module.yipin.base.dataobject.tbl.CityTbl;
/**
 * 
 * @author admin
 *
 */
public class City extends CityTbl{
	
	private List<Brand> brands;

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
	

}
