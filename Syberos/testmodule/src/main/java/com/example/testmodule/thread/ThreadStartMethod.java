package com.example.testmodule.thread; //cmd 编译 需要将 package 去掉

/**
 * Created by Administrator on 2018/8/6.
 * 启动线程 5 中方式
 */

public class ThreadStartMethod {
    public static void main(String[] args) {
//        startThread();
        System.out.println("Test ");
    }
//    public static void startThread(){
//        //使用匿名内部类创建线程
//        Thread t1 = new Thread(){
//            public void run(){
//                System.out.println("匿名内部类创建线程成功");
//                while (true){
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("t1 is running");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        t1.start();
//
//        //使用runnable接口创建线程
//        Runnable r = new Runnable(){
//            public void run(){
//                System.out.println("runnable接口创建线程成功");
//                while (true){
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("t2 is running");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        Thread t2 = new Thread(r);
//        t2.start();
//
//        //使用runnable接口创建创建匿名类，创建线程实例
//        Thread t3 = new Thread(new Runnable(){
//            @Override
//            public void run() {
//                System.out.println("使用runnable接口创建创建匿名类，创建线程实例成功");
//                while (true){
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("t3 is running");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        t3.start();
//
//        //创建匿名类实例，直接启动线程
//        new Thread(){
//            public void run(){
//                System.out.println("创建匿名类实例，直接启动线程成功");
//                while (true){
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("t4 is running");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
//
//        //创建匿名类实例，实现runnable接口，直接启动线程
//        new Thread(new Runnable(){
//            public void run(){
//                System.out.println("创建匿名类实例，实现runnable接口，直接启动线程");
//                while (true){
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println("t5 is running");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//    }
}
