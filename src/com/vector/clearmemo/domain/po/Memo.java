package com.vector.clearmemo.domain.po;

import java.io.Serializable;

public class Memo implements Serializable {
	private int id;
	private String title;
	private String createdate;
	private String clockdate;
	private String contentsummary;
	private String path;
	private int type_id;
	private int user_id;

	public Memo() {
		super();
	
	}

	public Memo(String title, String createdate, String clockdate,
			String contentsummary, String path, int type_id, int user_id) {
		id = -1;
		this.title = title;
		this.createdate = createdate;
		this.clockdate = clockdate;
		this.contentsummary = contentsummary;
		this.path = path;
		this.type_id = type_id;
		this.user_id = user_id;
	}

	public Memo(int id, String title, String createdate, String clockdate,
			String contentsummary, String path, int type_id, int user_id) {
		super();
		this.id = id;
		this.title = title;
		this.createdate = createdate;
		this.clockdate = clockdate;
		this.contentsummary = contentsummary;
		this.path = path;
		this.type_id = type_id;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getClockdate() {
		return clockdate;
	}

	public void setClockdate(String clockdate) {
		this.clockdate = clockdate;
	}

	public String getContentsummary() {
		return contentsummary;
	}

	public void setContentsummary(String contentsummary) {
		this.contentsummary = contentsummary;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
