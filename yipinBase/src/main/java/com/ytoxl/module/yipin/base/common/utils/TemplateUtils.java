package com.ytoxl.module.yipin.base.common.utils;

import java.io.IOException;
import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.ytoxl.module.yipin.base.common.CommonConstants;
import com.ytoxl.module.yipin.base.common.exception.YiPinStoreException;

import freemarker.template.Template;
import freemarker.template.TemplateException;

@Component
public class TemplateUtils {
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;  
    
	public String getFreeMarkerText(String template, Object obj) throws YiPinStoreException{
		String htmlText = null;
        try {
        	Template	fmTemplate = new Template(CommonConstants.FREEMARK_WAYBILLNAME, new StringReader(template), freeMarkerConfigurer.getConfiguration());
        	// 解析模板并替换动态数据  		 
			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(fmTemplate, obj);
		} catch (IOException e) {
			throw new YiPinStoreException(e);
		} catch (TemplateException e) {
			throw new YiPinStoreException(e);
		}  
        return htmlText;
	}
}
