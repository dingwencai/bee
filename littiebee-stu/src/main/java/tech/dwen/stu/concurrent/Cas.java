package tech.dwen.stu.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by MrCai on 2019/12/30.
 */
public class Cas {

    private static AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) {

        new Thread(()->{
            i.getAndIncrement();
        },"").start();


    }

}
