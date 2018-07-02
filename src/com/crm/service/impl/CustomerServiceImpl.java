package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crm.mapper.CustomerMapper;
import com.crm.pojo.Customer;
import com.crm.pojo.QueryVo;
import com.crm.service.CustomerService;
import com.crm.utils.Page;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public Page<Customer> queryCustomerByQueryVo(QueryVo queryVo) {
		//设置查询条件,从那一条开始查询
		queryVo.setStart((queryVo.getPage() - 1) * queryVo.getRows());
		//查询数据结果集
		List<Customer> list = customerMapper.queryCustomerByQueryVo(queryVo);
		int total = customerMapper.queryCountByQueryVo(queryVo);
		//封装page对象
		Page<Customer> page = new Page<>(total,queryVo.getPage(),queryVo.getRows(),list);
		
		return page;
	}

	@Override
	public Customer queryCustomerById(Long id) {
		return customerMapper.queryCustomerById(id);
	}

	@Override
	public void updateCustomerById(Customer customer) {
		customerMapper.updateCustomerById(customer);
		
	}

	@Override
	public void deleteCustomerById(Long id) {
		customerMapper.deleteCustomerById(id);
		
	}

}
