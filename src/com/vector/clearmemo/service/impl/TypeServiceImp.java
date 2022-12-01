package com.vector.clearmemo.service.impl;

import java.util.List;

import android.content.Context;

import com.vector.clearmemo.dao.ITypeDao;
import com.vector.clearmemo.dao.impl.TypeDaoImp;
import com.vector.clearmemo.domain.po.Type;
import com.vector.clearmemo.service.ITypeService;

public class TypeServiceImp implements ITypeService {
	
	ITypeDao typeDao;

	public TypeServiceImp(Context context) {
		super();
		typeDao = new TypeDaoImp(context);
	}


	@Override
	public List<Type> selectType() {

		
		return typeDao.selectTypeAll();
	}
	

}
