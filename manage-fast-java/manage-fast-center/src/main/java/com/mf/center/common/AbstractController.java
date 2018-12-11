package com.mf.center.common;

import com.mf.common.domain.user.User;
import org.apache.shiro.SecurityUtils;

public class AbstractController {

	protected User getUser() {
		return (User) SecurityUtils.getSubject().getPrincipal();
	}

	protected Long getUserId() {
		return getUser().getId();
	}
}
