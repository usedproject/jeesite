package com.zhidisoft.business.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhidisoft.business.dao.CustomerDao;
import com.zhidisoft.business.dao.PersonDao;
import com.zhidisoft.business.entity.Customer;
import com.zhidisoft.business.entity.Person;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Resource
	PersonDao personDao;	
	
	@Resource
	CustomerDao customerDao;
	
	@Override
	public List<Person> getListByPage(Person person, Integer page, Integer rows) {
		return personDao.getListByPage(person,(page-1)*rows,rows);
	}

	@Override
	public Integer count(Person person) {
		return personDao.count(person);
	}

	@Override
	public Customer selectByIdOrName(String name, String idcard) {
		
		return customerDao.selectByIdOrName(name,idcard);
	}

	@Override
	public int add(Person person) {
		if (person.getWorked().equals("")) {
			person.setWorked(null);
		}
		if (person.getPersoninfo().equals("")) {
			person.setPersoninfo(null);
		}
		person.setCreatetime(new Date());
		if (person.getRemark().equals("")) {
			person.setRemark(null);
		}
		person.setStatus("0");
		return personDao.insert(person);
	}

	@Override
	public Person selectByIdCard(String idcard) {
		return personDao.selectByIdCard(idcard);
	}

	@Override
	public void delete(Person person2) {
		personDao.deleteByPrimaryKey(person2.getId());
	}

	@Override
	public Person selectById(Integer id) {
		return personDao.selectByPrimaryKey(id);
	}

	@Override
	public int update(Person person) {
		person.setUpdatetime(new Date());
		if (person.getWorked().equals("")) {
			person.setWorked(null);
		}
		if (person.getPersoninfo().equals("")) {
			person.setPersoninfo(null);
		}
		person.setStatus("0");
		if (person.getRemark().equals("")) {
			person.setRemark(null);
		}
		return personDao.updateByPrimaryKey(person);
	}

}
