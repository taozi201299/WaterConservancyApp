package com.example.testmodule.thread; //cmd ���� ��Ҫ�� package ȥ��

/**
 * Created by Administrator on 2018/8/6.
 * �����߳� 5 �з�ʽ
 */

public class ThreadStartMethod {
    public static void main(String[] args) {
//        startThread();
        System.out.println("Test ");
    }
//    public static void startThread(){
//        //ʹ�������ڲ��ഴ���߳�
//        Thread t1 = new Thread(){
//            public void run(){
//                System.out.println("�����ڲ��ഴ���̳߳ɹ�");
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
//        //ʹ��runnable�ӿڴ����߳�
//        Runnable r = new Runnable(){
//            public void run(){
//                System.out.println("runnable�ӿڴ����̳߳ɹ�");
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
//        //ʹ��runnable�ӿڴ������������࣬�����߳�ʵ��
//        Thread t3 = new Thread(new Runnable(){
//            @Override
//            public void run() {
//                System.out.println("ʹ��runnable�ӿڴ������������࣬�����߳�ʵ���ɹ�");
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
//        //����������ʵ����ֱ�������߳�
//        new Thread(){
//            public void run(){
//                System.out.println("����������ʵ����ֱ�������̳߳ɹ�");
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
//        //����������ʵ����ʵ��runnable�ӿڣ�ֱ�������߳�
//        new Thread(new Runnable(){
//            public void run(){
//                System.out.println("����������ʵ����ʵ��runnable�ӿڣ�ֱ�������߳�");
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
