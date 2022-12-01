package com.vector.clearmemo.service;

import java.util.List;

import com.vector.clearmemo.domain.po.Memo;

public interface IMemoService {
	// 添加备忘条目
	public void addMemo(Memo memo);

	// 查找用户memo
	public List<Memo> selectMemo(int uid);

	// 修改memo
	public void updateMemo(Memo memo);

	// 修改memo
	public void deleteMemo(int id);

	// 获取目标memo的内容
	public String getmemocontent(Memo memo);

	// 查找用户的某类型memo
	public List<Memo> selectMemobyuidandtype(int uid, int typeid);


	/**
	 * 根据搜索内容模糊查找memo
	 * @param searchinputcontent
	 * @return 
	 */
	public List<Memo> selectMemoBySearchContent(String searchinputcontent);

	public Memo selectMemobyid(int id);
	
}
