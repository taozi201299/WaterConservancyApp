package com.example.testmodule.thread;

/**
 * Created by BZB on 2018/8/7.
 * Project: Syberos.
 * Package：com.example.testmodule.thread.
 */
public class StartThreadActivity {
    public static void main(String[] args) {
        startThread();
    }

    private static void startThread() {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());

            }
        };
        t1.start();
        Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName()), "t2");
        t2.start();



    }
}
