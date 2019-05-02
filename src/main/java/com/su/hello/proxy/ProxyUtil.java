package com.su.hello.proxy;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

/**
 * 反射代理工具類
 * 
 * @author SS
 *
 */
public class ProxyUtil {

	/**
	 * 反射+代理动态创建对象
	 * 
	 * @param class_na
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T geneBean(String className, Class<T> resType)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		String class_na = getProper(className/* "personClassName" */);
		Class clazz = Class.forName(class_na);
		System.out.println("clazz-" + clazz.getName());

		/**
		 *
		 * 卧槽注意！Proxy.getProxyClass方法的第二个参数！！！！
		 */
		Class clazzProxy = Proxy.getProxyClass(clazz.getClassLoader(), clazz/* clazz.getInterfaces() */);
		System.out.println("clazzProxy-" + clazzProxy.getName());
		Class[] inters = clazzProxy.getInterfaces();
		for (Class inite : inters) {
			System.out.println("interface-" + inite.getName());
		}
		Constructor cons = clazzProxy.getDeclaredConstructor(InvocationHandler.class);
		System.out.println("cons-" + cons.getName());
		Class[] args = cons.getParameterTypes();
		for (Class arg : args) {
			System.out.println("cons-args:" + arg.getName());// java.lang.reflect.InvocationHandler;
		}
		System.out.println(cons.isAccessible());
		cons.setAccessible(true);
		System.out.println(cons.isAccessible());
		Person proxyObj = (Person) cons.newInstance(new MyInvocationHandler(new Man()));
		proxyObj.say();
		T res = (T) cons.newInstance(new MyInvocationHandler(new Man()));
		return res;
	}

	/**
	 * 获取配置文件中的参数 通过当前的类加载器加载获取文件，常用的框架也是这样做的
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getProper(String key) throws IOException {
		// 通过当前的类加载器加载获取文件，常用的框架也是这样做的
		InputStream in = ProxyUtil.class.getResourceAsStream("applicatio.properties");
		Properties properties = new Properties();
		properties.load(in);
		in.close();
		return (String) properties.get(key);
	}

	/**
	 * 主方法
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//		String className = getProper("personClassName");
		Person per = geneBean("personClassName", Person.class);
		per.say();
	}

	/**
	 * 自定義InvocationHandler中間對象，用於包裝目標對象（真實被代理的對象）
	 * 
	 * @author SS
	 *
	 */
	static class MyInvocationHandler implements InvocationHandler {

		// 目標對象
		private Object tar;

		public MyInvocationHandler(Object tar) {
			this.tar = tar;
		}

		/**
		 * 參數説明：proxy:代理對象、method:代理對象的方法、args:代理對象method方法的參數
		 */
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("执行前----");
			System.out.println("方法名稱：" + method.getName());
//			proxy = tar;
			System.out.println("proxy:" + proxy.getClass());
			System.out.println("tar:" + tar.getClass());
			System.out.println(proxy.getClass() == tar.getClass());
			Object rs = method.invoke(this.tar, args);
			System.out.println("执行后----");
			return rs;// 此结果对反射不造成影响
		}

	}

}
