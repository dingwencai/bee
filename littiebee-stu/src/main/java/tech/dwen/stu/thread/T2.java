package tech.dwen.stu.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class T2 {


    /**
     * 两个线程分别输入: 123456  和 ABCDEF ，需要输出: 1A2B3C4D5E6F
     * @param args
     */
    public static void main(String[] args) {

        char[] a1 = "123456".toCharArray();
        char[] a2 = "ABCDEF".toCharArray();

        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();//队列
        Condition condition2 = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c:a1) {
                    System.out.print(c);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() ->{
            try {
                lock.lock();
                for (char c:a2) {
                    System.out.println(c);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        },"t2").start();

    }


}
