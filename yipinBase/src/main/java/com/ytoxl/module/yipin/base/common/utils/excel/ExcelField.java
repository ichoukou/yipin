package com.ytoxl.module.yipin.base.common.utils.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.ytoxl.module.yipin.base.common.utils.excel.FormatType.STRING;
import static com.ytoxl.module.yipin.base.common.utils.excel.Type.FIELD;
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface ExcelField {
	String filedName() default "";

	int sort() default 99;

	boolean isKey() default false;

	Type type() default FIELD;

	FormatType formatType() default STRING;

	String pattern() default "";
}
