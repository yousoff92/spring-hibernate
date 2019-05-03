package com.yousoff.spring.application;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.CollectionUtils;

import com.yousoff.spring.config.SpringConfig;
import com.yousoff.spring.entity.Item;
import com.yousoff.spring.entity.User;
import com.yousoff.spring.service.ItemService;
import com.yousoff.spring.service.UserService;

/**
 * 
 * @author yousoff
 *
 *
 * Reference :
 * https://howtodoinjava.com/spring5/core/spring-bean-container-java-configuration-example/
 * https://howtodoinjava.com/automation/lombok-eclipse-installation-examples/
 *
 *
 */
public class MainApplication {

	
	public static void main(String[] args) {
		System.out.println("Start program...");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(SpringConfig.class);
        ctx.refresh();
        
        try {
			testItemService(ctx);
			testUserService(ctx);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			System.out.println("Closing context...");
			ctx.close();
		}
        
        System.out.println("End of program...");
	}


	private static void testItemService(AnnotationConfigApplicationContext ctx) throws Exception {
		ItemService itemService = ctx.getBean(ItemService.class);
		List<Item> items = itemService.getAllItems();
		for(Item item : items) {
			System.out.println(item);
		}
		items.clear();
		
		List<Item> items = itemService.getEnabledItemsByUserId(Integer.valueOf(1));
		for(Item item : items) {
			System.out.println(item);
		}
	}
	
	private static void testUserService(AnnotationConfigApplicationContext ctx) {
		ItemService itemService = ctx.getBean(ItemService.class);
		UserService userService = ctx.getBean(UserService.class);
		List<User> users = userService.getAllUser();
		for (User user : users) {
			System.out.println(user);
			System.out.println(user.getName() + " has item : ");
			
			List<Item> items = itemService.getItemsByUserId(user.getId());
			if(!CollectionUtils.isEmpty(items)) {
				System.out.println(items);
			}
			System.out.println();
		}
	}
}
