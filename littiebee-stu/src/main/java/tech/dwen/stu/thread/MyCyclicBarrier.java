package tech.dwen.stu.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by MrCai on 2020/1/12.
 */
public class MyCyclicBarrier {



    public static void main(String[] args) {
        int count = 3;
        CyclicBarrier cyclicbarrier = new CyclicBarrier(count);

        for(int i = 0 ; i < count; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"begin,并等待其他线程");
                    cyclicbarrier.await();
                    System.out.println(Thread.currentThread().getName()+"开始工作");
                    Thread.sleep(finalI * 1000);
                    System.out.println(Thread.currentThread().getName()+"完成工作");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"T"+i).start();
        }
    }
}
