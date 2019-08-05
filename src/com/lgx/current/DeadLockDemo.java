package com.lgx.current;

/**
 * 死锁分析
 * @author admin
 *
 */
class MyDeadLock implements Runnable{
	private String lockA;
	private String lockB;
	public MyDeadLock(String lockA,String lockB) {
		this.lockA = lockA;
		this.lockB = lockB;
	}
	@Override
	public void run() {
		synchronized(lockA) {
			System.out.println(Thread.currentThread().getName() + "\t正在持有" + lockA + "\t希望获得" + lockB);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lockB) {
				System.out.println(Thread.currentThread().getName() + "\t正在持有" + lockB + "\t希望获得" + lockA);
			}
		}
	}
}

public class DeadLockDemo {
	
	
	
	public static void main(String[] args) {
		
		String lockA = "lockA";
		String lockB = "lockB";
		new Thread(new MyDeadLock(lockA,lockB),"线程A").start();
		new Thread(new MyDeadLock(lockB,lockA),"线程B").start();
	}

}
