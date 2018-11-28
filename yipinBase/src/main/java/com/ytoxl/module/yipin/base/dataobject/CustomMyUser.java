package com.ytoxl.module.yipin.base.dataobject;

import com.ytoxl.module.user.security.CustomUserDetails;

/**自定义User
 * @author zengzhiming
 *
 */
public class CustomMyUser extends CustomUserDetails{
	/**用户类型	 */
	private Integer userType;

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
}
