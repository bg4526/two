package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.mapper.BaseDictMapper;
import com.crm.pojo.BaseDict;
import com.crm.service.BaseDictService;
@Service
public class BaseDictServiceImpl implements BaseDictService {
	
	@Autowired
	private BaseDictMapper baseDictMapper;
	@Override
	public List<BaseDict> queryBaseDictByDictTypeCode(String dictTypeCode) {
		
		return baseDictMapper.queryBaseDictByDictTypeCode(dictTypeCode);
	}

}
