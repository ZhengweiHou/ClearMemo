package com.vector.clearmemo.dao;

import java.util.List;

import com.vector.clearmemo.domain.po.Memo;

public interface IMemoDao {

	// ��ӱ���¼memo
	public void insertmemo(Memo memo);

	// ���������û�memo
	public List<Memo> selectMemoByUid(int uid);

	// �޸�memo
	public void updatememo(Memo memo);

	// ɾ��memo
	public void deleteMemoByid(int id);

	// ���������û�ĳ����memo
	public List<Memo> selectMemoByUidAndTypeid(int uid, int typeid);

	/**
	 * ������������ģ������memo
	 * @param searchinputcontent
	 * @return
	 */
	public List<Memo> selectMemoSearchContent(String searchinputcontent);

	public Memo selectMemoByid(int id);
}
