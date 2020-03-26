package tech.dwen.stu.leetcode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Created by MrCai on 2020/1/12.
 */
public class T1116 {
    private int n;

    Semaphore zero = new Semaphore(1);
    Semaphore even = new Semaphore(0);
    Semaphore odd = new Semaphore(0);

    public T1116(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 0; i < n ; i++){
            zero.acquire();
            printNumber.accept(0);
            if( (i&1) == 0){
                odd.release();
            } else {
                even.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2; i <= n ; i += 2){
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n ; i += 2){
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }
    }
}
