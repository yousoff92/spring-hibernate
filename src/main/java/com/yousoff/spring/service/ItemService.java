package com.yousoff.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yousoff.spring.common.Constant.Operator;
import com.yousoff.spring.common.Expression;
import com.yousoff.spring.dao.ItemDao;
import com.yousoff.spring.entity.Item;

@Service
@Transactional
public class ItemService {

	@Autowired
	private ItemDao itemDao;

	public List<Item> getAllItems() {
		List<Item> items = itemDao.findAll(Item.class);
		return items;
	}
	
	public List<Item> getItemsByUserId(int userId) {
		
		List<Expression> exps = new ArrayList<Expression>();
		Expression exp = new Expression("userId", Operator.EQUAL, userId);
		exps.add(exp);
		
		List<Item> items = itemDao.getObjectListByCriteria(Item.class, exps);
		return items;
	}
	
	public List<Item> getEnabledItemsByUserId(Integer userId) throws Exception {
		Item input = new Item();
		input.setUserId(userId);
		
		List<Item> items = itemDao.getObjectListByStoredProcedure(input, "P_ITEM_ENABLED_BY_USER", new String[]{"userId"});
		return items;
	}

}
