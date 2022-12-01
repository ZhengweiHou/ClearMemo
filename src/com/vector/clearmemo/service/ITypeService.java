package com.vector.clearmemo.service;

import java.util.List;

import com.vector.clearmemo.domain.po.Type;

public interface ITypeService {
	
	//查找所有分类type
	public List<Type> selectType();

}
