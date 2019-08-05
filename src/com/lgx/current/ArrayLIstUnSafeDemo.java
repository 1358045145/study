package com.lgx.current;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList线程不安全的例子    抛出CurrentModificationException异常
 * @author admin
 *
 */
public class ArrayLIstUnSafeDemo {
	
	//private static List list = new CopyOnWriteArrayList<String>();  //写时复制，线程安全的ArrayList
	private static List list = new ArrayList<String>();
	//private static List list = Collections.synchronizedList(new ArrayList<String>()); //加锁
	
	public static void main(String[] args) {
		for(int i = 1 ; i <= 30 ; i ++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					list.add(UUID.randomUUID().toString().substring(0, 5));
					System.out.println(list);
				}
			}).start();
		}
	}
}
