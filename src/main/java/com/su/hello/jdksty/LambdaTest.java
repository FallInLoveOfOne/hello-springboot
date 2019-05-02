package com.su.hello.jdksty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Lambda表达式函数编程测试 20190502
 * @author SS
 *
 */
public class LambdaTest {

	public static void main(String[] args) throws InterruptedException {
		eachList();
		eachMap();
		Thread.sleep(1000);
		lamThread();
	}
	
	public static void eachList() {
		List<String> items = new ArrayList<>();
		items.add("A");
		items.add("B");
		items.add("C");
		items.add("D");
		items.add("E");
		// 输出：A,B,C,D,E
		items.forEach(item -> System.out.println(item));
		// 输出 : C
		items.forEach(item -> {
			if ("C".equals(item)) {
				System.out.println(item);
			}
		});
	}
	
	public static void eachMap() {
		Map<String, Integer> items = new HashMap<>();
		items.put("A", 10);
		items.put("B", 20);
		items.put("C", 30);
		items.put("D", 40);
		items.put("E", 50);
		items.put("F", 60);
		items.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));
		items.forEach((k,v)->{
			System.out.println("Item : " + k + " Count : " + v);
			if("E".equals(k)){
				System.out.println("Hello E");
			}
		});
	}
	
	public static void lamThread() {
		new Thread(()->{
			for(int n=0;n<10;n++) {
				System.out.println(Thread.currentThread().getName()+"--"+n);
			}
		}).start();
	}
	
}
