package com.lgx.current;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。对多个资源的并发访问。
 * 模拟六辆车去抢占三个停车位
 * @author admin
 *
 */
public class SemaphoreDemo {
	
	private static Semaphore se =new Semaphore(3);  //三个停车位
	
	public static void main(String[] args) {
		
		for (int i = 1; i <= 6; i++) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						se.acquire();
						System.out.println(Thread.currentThread().getName() + "\t抢到了车位");
						Thread.sleep(3000);
						se.release();
						System.out.println(Thread.currentThread().getName() + "\t停车三秒后离开了车位");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "车子" + i).start();
		}

	}

}
