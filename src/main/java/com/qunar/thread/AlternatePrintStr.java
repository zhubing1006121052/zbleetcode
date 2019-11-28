package com.qunar.thread;

import java.util.concurrent.Semaphore;

/**
 * 1195. 交替打印字符串
 */
public class AlternatePrintStr {

    public static volatile Integer count = 1;
    public static Object obj = new Object();

    public static Semaphore  objA = new Semaphore(1);
    public static Semaphore  objB = new Semaphore(0);
    public static Semaphore  objC = new Semaphore(0);
    public static Semaphore  objD = new Semaphore(0);
    public static void main(String[] args) {


        SemaPhoreResolve();
    }

    /**
     * 使用信号量解决多个线程交替打印字符串的问题
     *
     */
    public static void SemaPhoreResolve(){
        new Thread(){
            @Override
            public void run() {
                while (count < 15){
                    try {
                        objA.acquire();
                        if(count%3 == 0 && count%5 == 0){
                            System.out.println("fizzbuzz ");
                            Thread.sleep(100);
                            count++;
                            objA.release();
                        }else{
                            Thread.sleep(100);
                            objB.release();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while (count < 15){
                    try {
                        objB.acquire();
                        if(count%3 == 0){
                            System.out.println("fizz ");
                            Thread.sleep(100);
                            count++;
                            objA.release();
                        }else{
                            Thread.sleep(100);
                            objC.release();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while (count < 15){
                    try {
                        objC.acquire();
                        if(count%5 == 0){
                            System.out.println("buzz ");
                            Thread.sleep(100);
                            count++;
                            objA.release();
                        }else{
                            Thread.sleep(100);
                            objD.release();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                while (count < 15){
                    try {
                        objD.acquire();
                        System.out.println(count + " ");
                        Thread.sleep(100);
                        objA.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }

            }
        }.start();
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
