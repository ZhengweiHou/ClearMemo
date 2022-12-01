package com.vector.clearmemo.domain.po;

import java.io.Serializable;

public class Type implements Serializable{
	private int id;
	private int icon;
	private String name;
	
	
	
	public Type() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Type(int id, int icon, String name) {
		super();
		this.id = id;
		this.icon = icon;
		this.name = name;
	}
	
	
	public Type(int icon, String name) {
		super();
		id = -1;
		this.icon = icon;
		this.name = name;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
