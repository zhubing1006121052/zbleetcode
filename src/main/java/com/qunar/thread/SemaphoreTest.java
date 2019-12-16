package com.qunar.thread;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                try {
                    semaphore.acquireUninterruptibly();
                    semaphore.acquire();//sync.acquireSharedInterruptibly(1);  公平锁，如果没有获取那么排队
                    semaphore.tryAcquire();//return sync.nonfairTryAcquireShared(1) >= 0;非公平锁   没有获取到就直接返回false, 不进入AQS队列排队
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
