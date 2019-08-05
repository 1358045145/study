package com.lgx.current;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 用来控制多个线程互相等待，只有当多个线程都到达时，这些线程才会继续执行
 * 集齐7颗龙珠才能召唤神龙
 * @author admin
 *
 */
public class CyclicBarrierDemo {
	
	private static CyclicBarrier cyc = new CyclicBarrier(7, new Runnable() {
		@Override
		public void run() {
			System.out.println("7颗龙珠集齐，召唤出神龙");
		}
	});
	public static void main(String[] args) throws InterruptedException {
		
		for(int i = 1 ; i <= 7 ; i++) {
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + "收集成功");
						cyc.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			},"龙珠" + i).start();
		}
		
	}

}
