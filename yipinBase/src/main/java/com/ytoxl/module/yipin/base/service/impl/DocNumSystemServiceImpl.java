package com.ytoxl.module.yipin.base.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ytoxl.module.yipin.base.common.CodeConstants;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;
import com.ytoxl.module.yipin.base.dataobject.FormNumber;
import com.ytoxl.module.yipin.base.dataobject.Product;
import com.ytoxl.module.yipin.base.dataobject.ProductSku;
import com.ytoxl.module.yipin.base.dataobject.ProductSkuOptionValue;
import com.ytoxl.module.yipin.base.mapper.FormNumberMapper;
import com.ytoxl.module.yipin.base.service.DocNumSystemService;

@Service
public class DocNumSystemServiceImpl implements DocNumSystemService {
	private static Logger logger = LoggerFactory.getLogger(DocNumSystemServiceImpl.class);
	@Autowired
	private FormNumberMapper<FormNumber> numberMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class)
	public String getOrderNum() throws YiPinStoreException {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
    	String formPrefix = df.format(date);
    	FormNumber number = null;
    	try {
    		number =  numberMapper.getFormNumberByPrefix(formPrefix);
		} catch (DataAccessException e) {
			logger.error("生成订单号出错",e);
			throw new YiPinStoreException(CodeConstants.ORDER_CREATE_ORDERNO_ERROR, "提交数据有误，请核对后再提交！");
		}
    	String orderNum = null;
    	if(number!=null){
    		if(formPrefix.equals(number.getFormPrefix())){
    		    int curIndex=number.getFormIndex() + 1;
    		    int flag= numberMapper.updateIndex(number.getFormNumberId(), curIndex);
    		    if(flag != 1)
    		    	throw new YiPinStoreException("Fail to update FormNumIndex ! ");
    		    orderNum = formPrefix + getStrCode(curIndex,5);
    		}
    		else {
    			int flag=numberMapper.updateIndex(number.getFormNumberId(),1);
    			if(flag!=1)
     		    	throw new YiPinStoreException("Fail to update FormNumIndex ! ");
    			orderNum= formPrefix + getStrCode(1,5);
    		}
    	}
    	else {
    		number=new FormNumber();
    		number.setFormPrefix(formPrefix);
    		number.setFormIndex(1);
    		numberMapper.add(number);
    		orderNum = formPrefix + getStrCode(1, 5);
    	}
    	return orderNum;
   }
	
	
	/**
	 * 获取多位编码
	 * @param num
	 * @return
	 */
	private String getStrCode(Integer num,Integer bit){
		 if(num<1||bit<1)
    		 return null;
		String s=num.toString();
		int length=s.length();
		if(length>bit)
			return null;
		int add=bit-length;
		String prefix="";
		for(int i=0;i<add;i++){
			prefix+="0";
		}
		return prefix+s;
	}
}
