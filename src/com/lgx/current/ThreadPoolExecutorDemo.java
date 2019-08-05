package com.lgx.current;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的测试
 * 核心参数：  核心线程数，最大线程数，存活时间，时间单位，阻塞队列，线程工程，饱和拒绝策略
 * 原理： 当请求到达时，先判断活跃线程数是否大于核心线程数，如果不大于，直接执行；如果大于，将进来的任务放入阻塞队列，
 * 判断阻塞队列是否满，没满，加入，满了则去判断线程数是否到达了最大线程数，如果没有，则创建非核心线程执行，到达了最大
 * 线程数则使用饱和拒绝策略(1.AbortPolicy:直接抛出异常。 2.CallerRunsPolicy:返回给调用者。3.DiscardOldestPolicy:丢弃队列中等待最久的  4.DiscardPolicy:直接丢弃)。
 * @author admin
 *
 */
public class ThreadPoolExecutorDemo {
	
	public static void main(String[] args) {
		ExecutorService es = new ThreadPoolExecutor(
				2,
				5,
				3L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(3),
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy()
				);
		init(es);
	}
	
	public static void init(ExecutorService es) {
		try {
			//模拟10个用户办理业务的场景
			for(int i = 1 ; i <= 9 ; i ++) {
				es.execute(new Runnable() {
					@Override
					public void run() {
						System.out.println(Thread.currentThread().getName() + "\t办理业务");
					}
				});
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			es.shutdown();
		}
	}

}
