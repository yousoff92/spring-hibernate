package com.yousoff.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yousoff.spring.dao.UserDao;
import com.yousoff.spring.entity.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	public List<User> getAllUser() {
		List<User> users = userDao.findAll(User.class);
		return users;
	}

}
