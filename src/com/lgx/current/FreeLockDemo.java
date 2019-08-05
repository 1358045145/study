package com.lgx.current;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁的案例
 * @author admin
 *
 */
public class FreeLockDemo {
	
	private static AtomicReference<Thread> af = new AtomicReference<>();
	
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (!af.compareAndSet(null, Thread.currentThread())) {

					}
					System.out.println(Thread.currentThread().getName() + "\t进入了");
					Thread.sleep(3000);  //睡五秒，占用着
					while(!af.compareAndSet(Thread.currentThread(), null)) {
						
					}
					System.out.println(Thread.currentThread().getName() + ":舒服了,溜了溜了");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "ThreadAAA").start();
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
					do {
						 System.out.println(Thread.currentThread().getName() + "\t我去自旋吧");
						 try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}while(!af.compareAndSet(null, Thread.currentThread()));
					System.out.println(Thread.currentThread().getName() + ":终于进来了,哭了。。。。");
			}
		}, "ThreadBBB").start();
		
	}

}
