package com.su.hello.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.su.hello.server.HelloServer;
import com.su.hello.server.WordServer;

@Controller
@RequestMapping(value = "hell/")
public class HelloController {

	/**
	 * @Autowired（spring注解）与@Resource(jdk注解)都可以用来装配bean. 都可以写在字段上,或写在setter方法上
	 * @Autowired是基于类型装配bean，required属性可以允许注入的对象是否可以为null
	 */
	@Autowired(required = false) // 注入的对象可以为null
	@Qualifier(value = "myHelloService") // 结合此注解可以基于名称注入bean
	private HelloServer helloServer;

	private WordServer wordServer;

	/**
	 * @Resource(jdk注解)可以用来装配bean.使用的是名称装配， 都可以写在字段上,或写在setter方法上
	 * @param wordServer
	 */
	@Resource(name = "myWordServer")
	public void setWordServer(WordServer wordServer) {
		this.wordServer = wordServer;
	}

	public WordServer getWordServer() {
		return wordServer;
	}

	@RequestMapping(value = "query/user/{token}", method = RequestMethod.POST)
	public String queryUser(@PathVariable(value = "token") String token, @RequestParam(value = "userId") String userId) {
		return "/index";
	}

}
