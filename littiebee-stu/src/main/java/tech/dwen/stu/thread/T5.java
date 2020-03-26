package tech.dwen.stu.thread;

/**
 * Object 的 await() notify() notifyAll() 方法，需要在 synchronized 同步方法/代码块中使用（调用obj的wait(), notify()方法前，必须获得obj锁）
 */
public class T5 {

    public static void main(String[] args) throws InterruptedException {

        // 每个锁对象都有两个队列，就绪队列和等待队列
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "-开始");
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + "-before wait");
                    // wait 方法释放对象锁
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + "-after wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "-结束");
        }, "T1");

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "-开始");
            synchronized (lock) {
                for (int i = 0; i < 8; i++) {
                    System.out.println(Thread.currentThread().getName() + "-" + i);
                    if (i == 5) {
                        // 唤醒（lock）对象监视器上等待的单个线程，选择是任意性的。notifyAll()唤醒在此对象监视器上等待的所有线程。
                        lock.notify();
                        System.out.println(Thread.currentThread().getName() + "notify");
                    }
                }
            }
            for (int i = 0; i < 1000000000; i++) {

            }
            System.out.println(Thread.currentThread().getName() + "-结束");
        }, "T2");

        t1.start();
        Thread.sleep(1000);
        t2.start();
    }
}
