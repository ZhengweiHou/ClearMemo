package com.vector.clearmemo.service;

import com.vector.clearmemo.domain.po.User;

public interface IManagerService {
	// 用户管理界面的信息查询
	public User select(User user);
}
