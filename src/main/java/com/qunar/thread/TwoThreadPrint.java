package com.qunar.thread;

/**
 * 两个线程交替打印
 */
public class TwoThreadPrint {

    private static volatile Integer ac = new Integer(0);
    private static final Object objA = new Object();

    /**
     * 为什么要在线程1和线程2之间睡眠10秒保证线程1有限执行
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        /**
         * 线程1
         */
        new Thread(){
            @Override
            public void run() {
                try {
                    while(ac < 100){
                        synchronized(objA){
                            objA.wait();
                            System.out.println("THREAD-B:"+(++ac));
                            Thread.sleep(10);
                            objA.notify();
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        Thread.sleep(10);
        /**
         * 线程2
         */
        new Thread(){
            @Override
            public void run() {
                try {
                    while(ac < 100){
                        synchronized(objA){
                            objA.notify();
                            System.out.println("THREAD-A:"+(++ac));
                            Thread.sleep(10);
                            objA.wait();
                        }

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

}
