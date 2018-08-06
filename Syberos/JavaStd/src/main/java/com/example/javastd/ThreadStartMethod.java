package com.example.javastd;

/**
 * Created by Administrator on 2018/8/6.
 * 启动线程 5 中方式
 */

public class ThreadStartMethod {
    public static void startThread(){
        //使用匿名内部类创建线程
        Thread t1 = new Thread(){
            public void run(){
                System.out.println("匿名内部类创建线程成功");
            }
        };
        t1.start();

        //使用runnable接口创建线程
        Runnable r = new Runnable(){
            public void run(){
                System.out.println("runnable接口创建线程成功");
            }
        };
        Thread t2 = new Thread(r);
        t2.start();

        //使用runnable接口创建创建匿名类，创建线程实例
        Thread t3 = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("使用runnable接口创建创建匿名类，创建线程实例成功");
            }
        });
        t3.start();

        //创建匿名类实例，直接启动线程
        new Thread(){
            public void run(){
                System.out.println("创建匿名类实例，直接启动线程成功");
            }
        }.start();

        //创建匿名类实例，实现runnable接口，直接启动线程
        new Thread(new Runnable(){
            public void run(){
                System.out.println("创建匿名类实例，实现runnable接口，直接启动线程");
            }
        }).start();
    }
}
