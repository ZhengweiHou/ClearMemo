package com.vector.clearmemo.dao;

import com.vector.clearmemo.domain.po.User;

public interface IUserDao {
	// 添加用户
	public void addUser(String account, String password, String nicheng);

	// 查询用户
	public User findUser(String account);

	// 修改用户昵称
	public void updatenicheng(User user);

	// 修改用户密码
	public void updatepassword(User user);

	// 修改用户电话
	public void updatetel(User user);

	// 修改用户性别
	public void uppdatesex(User user);

	// 修改用户地区
	public void updateaddress(User user);

	// 修改用户个性签名
	public void updategexingqianming(User user);
}
