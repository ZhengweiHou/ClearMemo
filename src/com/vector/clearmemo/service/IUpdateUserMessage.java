package com.vector.clearmemo.service;

import com.vector.clearmemo.domain.po.User;

public interface IUpdateUserMessage {
	// 昵称的修改
	public User updateNicheng(User user);

	// 电话的修改
	public User updateTel(User user);

	// 性别的修改
	public User updateSex(User user);

	// 地区的修改
	public User updateAddress(User user);

	// 个性签名的修改
	public User updateGexingqianming(User user);
}
