package tech.dwen.stu.thread;

import java.util.Random;

/**
 * Created by MrCai on 2020/1/7.
 */
public class Th2 {

    private static Object o = new Object();

    private static volatile Integer i = 0;

    public static void main(String[] args) {
        new Thread(()->{
            Random r = new Random();

            synchronized (o) {
                while (true) {
                    int i = r.nextInt(10);
                    try {
                        Thread.sleep(100);
                        if (i == 5) {
                            System.out.println(Thread.currentThread().getName()+"-"+i);
                            o.notify();
                            o.wait();
                        } else {
                            System.out.println(i);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        },"T1").start();

        new Thread(() -> {

            synchronized (o) {
                try {
                    while (true) {
                        System.out.println("T1现在输出的是:5");
                        o.notify();
                        o.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "T2").start();
    }

}
