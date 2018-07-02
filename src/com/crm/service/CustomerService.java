package com.crm.service;

import com.crm.pojo.Customer;
import com.crm.pojo.QueryVo;
import com.crm.utils.Page;

public interface CustomerService {
	/**
	 * 根据条件分页查询客户
	 * @param queryVo
	 * @return
	 */
	Page<Customer> queryCustomerByQueryVo(QueryVo queryVo);
	/**
	 * 根据id查询客户
	 * @param id
	 * @return
	 */
	Customer queryCustomerById(Long id);
	/**
	 * 根据id编辑客户数据
	 * @param customer
	 * @return 
	 */
	void updateCustomerById(Customer customer);
	/**
	 * 根据id 删除客户
	 * @param id
	 */
	void deleteCustomerById(Long id);
}
