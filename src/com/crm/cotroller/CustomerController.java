package com.crm.cotroller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.pojo.BaseDict;
import com.crm.pojo.Customer;
import com.crm.pojo.QueryVo;
import com.crm.service.BaseDictService;
import com.crm.service.CustomerService;
import com.crm.utils.Page;

@Controller
@RequestMapping("customer")
public class CustomerController {
	//客户来源
	@Value("${CUSTOMER_FROM_TPYE}")
	private String CUSTOMER_FROM_TPYE;
	
	@Value("${CUSTOMER_INDUSTRY_TYPE}")
	private String CUSTOMER_INDUSTRY_TYPE;
	
	@Value("${CUSTOMER_LEVEL_TPYE}")
	private String CUSTOMER_LEVEL_TPYE;
	
	@Autowired
	private BaseDictService baseDictService;
	
	@Autowired
	private CustomerService customerService;
	/**
	 * 显示所有用户
	 * @return
	 */
	@RequestMapping("list")
	public String queryCustomerList(QueryVo queryVo,Model model) {
		try {
			if(StringUtils.isNotBlank(queryVo.getCustName())) {
				queryVo.setCustName(new String(queryVo.getCustName().getBytes("ISO-8859-1"),"UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//客户来源
		List<BaseDict> fromType = baseDictService.queryBaseDictByDictTypeCode(CUSTOMER_FROM_TPYE);
		//所属行业
		List<BaseDict> industryType = baseDictService.queryBaseDictByDictTypeCode(CUSTOMER_INDUSTRY_TYPE);
		//客户级别
		List<BaseDict> levelType = baseDictService.queryBaseDictByDictTypeCode(CUSTOMER_LEVEL_TPYE);
		//把前段页面需要显示的数据存到模型中
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);
		
		//分页查询所有数据
		Page<Customer> page = customerService.queryCustomerByQueryVo(queryVo);
		model.addAttribute("page", page);
		//数据回显
		model.addAttribute("custName", queryVo.getCustName());
		model.addAttribute("custSource", queryVo.getCustSource());
		model.addAttribute("custIndustry", queryVo.getCustIndustry());
		model.addAttribute("custLevel", queryVo.getCustLevel());
		
		
		return "customer";
		
	}
	
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public Customer queryCustomerById(Long id) {
		return customerService.queryCustomerById(id);
	}
	
	/**
	 * 根据id 查询用户,返回更新后返回客户json数据格式
	 * @param customer
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public String updateCustomerById(Customer customer){
		customerService.updateCustomerById(customer);
		return "ok";
	}
	/**
	 * 根据id 删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String deleteCustomerById(Long id){
		customerService.deleteCustomerById(id);
		return "ok";
	}
	
}
