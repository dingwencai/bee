package tech.dwen.stu.thread;

import java.util.concurrent.Semaphore;

/**
 * 信号量
 * Created by MrCai on 2020/1/12.
 */
public class MySemaphore {

    public static void main(String[] args) {
        Semaphore z = new Semaphore(1);
        try {
            z.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
