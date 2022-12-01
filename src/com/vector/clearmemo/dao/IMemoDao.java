package com.vector.clearmemo.dao;

import java.util.List;

import com.vector.clearmemo.domain.po.Memo;

public interface IMemoDao {

	// 添加备忘录memo
	public void insertmemo(Memo memo);

	// 查找所有用户memo
	public List<Memo> selectMemoByUid(int uid);

	// 修改memo
	public void updatememo(Memo memo);

	// 删除memo
	public void deleteMemoByid(int id);

	// 查找所有用户某类型memo
	public List<Memo> selectMemoByUidAndTypeid(int uid, int typeid);

	/**
	 * 根据搜索内容模糊查找memo
	 * @param searchinputcontent
	 * @return
	 */
	public List<Memo> selectMemoSearchContent(String searchinputcontent);

	public Memo selectMemoByid(int id);
}
