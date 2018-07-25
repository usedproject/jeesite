package com.zhidisoft.business.service;

import java.util.List;

import com.zhidisoft.business.entity.Customer;
import com.zhidisoft.business.entity.Person;

public interface PersonService {

	List<Person> getListByPage(Person person, Integer page, Integer rows);

	Integer count(Person person);

	Customer selectByIdOrName(String name, String idcard);

	int add(Person person);

	Person selectByIdCard(String idcard);

	void delete(Person person2);

	Person selectById(Integer id);

	int update(Person person);

}
