package com.vector.clearmemo.domain.po;

import java.io.Serializable;

import com.example.firstandroidprojectclearmemo.R;

import android.location.Address;

public class User implements Serializable{
	private int id;
	private String nicheng;
	private String account;
	private String password;
	private int icon;
	private String tel;
	private String address;
	private String sex;
	private String gexingqianming;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String account, String password) {
		super();
		this.id = -1;
		this.nicheng = "сн©м";
		this.account = account;
		this.password = password;
		this.icon = 0;
		this.tel = "null";
		this.address = "null";
		this.sex = "н╢ж╙";
		this.gexingqianming = "null";
	}

	public User(int id, String nicheng, String account, String password,
			int icon, String tel, String address, String sex,
			String gexingqianming) {
		super();
		this.id = id;
		this.nicheng = nicheng;
		this.account = account;
		this.password = password;
		this.icon = icon;
		this.tel = tel;
		this.address = address;
		this.sex = sex;
		this.gexingqianming = gexingqianming;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNicheng() {
		return nicheng;
	}

	public void setNicheng(String nicheng) {
		this.nicheng = nicheng;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getGexingqianming() {
		return gexingqianming;
	}

	public void setGexingqianming(String gexingqianming) {
		this.gexingqianming = gexingqianming;
	}

}
