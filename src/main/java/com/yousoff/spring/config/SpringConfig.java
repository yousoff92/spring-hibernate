package com.yousoff.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.yousoff.spring.config","com.yousoff.spring.service", 
	"com.yousoff.spring.dao"})
public class SpringConfig {

}
