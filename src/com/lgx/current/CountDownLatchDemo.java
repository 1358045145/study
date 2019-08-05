package com.lgx.current;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 用来控制一个线程等待多个线程。
   维护了一个计数器 cnt，每次调用 countDown() 方法会让计数器的值减 1，减到 0 的时候，那些因为调用 await() 方
   法而在等待的线程就会被唤醒。
 * @author admin
 *
 *模拟教室所有人走了才能关门
 */
public class CountDownLatchDemo {
	
	private static CountDownLatch countDownLatch = new CountDownLatch(6);  //六个人
	public static void main(String[] args) throws InterruptedException {
		
		for(int i = 1 ; i <= 6 ; i ++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "\t离开了教室");
					countDownLatch.countDown();
				}
			}, i + "同学").start();
		}
		countDownLatch.await();
		System.out.println("班长开始关门了。。。。");
	}
	
}
