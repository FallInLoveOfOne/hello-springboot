package com.su.hello.lock;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.google.common.collect.Maps;

public class ReadWriteLock {
	
	private static Map<String, String> cache = Maps.newHashMap();
	private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	static {
//		cache.put("key", "wo shi cahe");
	}
	
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);
		for(int i=0;i<10;i++) {
			Thread th = new Thread(new Runnable() {
				
				@Override
				public void run() {
					get();
				}
			});
			threadPool.execute(th);
		}
		threadPool.shutdown();
		
	}
	
	public static Object get() {
		rwl.readLock().lock();
		String val = null;
		try {
			val = cache.get("key");
			if(null==val) {
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					if(null==val) {
						System.out.println("线程:"+Thread.currentThread().getName()+"，为cache赋值");
						cache.put("key", "wo shi cache");
					}
					
				} finally {
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		} finally {
			rwl.readLock().unlock();
		}
		System.out.println(Thread.currentThread().getName()+":get():"+val);
		return val;
	}

}
