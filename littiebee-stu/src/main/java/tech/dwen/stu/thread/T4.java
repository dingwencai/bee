package tech.dwen.stu.thread;

/**
 * Thread 的 join 方法
 * t.join(); 当前线程阻塞，等待 t 线程执行完成
 */
public class T4 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "-开始");
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "-开始");
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000L);
                    System.out.println(Thread.currentThread().getName() + "-" +i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "-结束");
        },"T1");

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "-开始");
            try {
                // t2 线程等待 t1 线程执行完成
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-结束");
        },"T2");

        t1.start();
        t2.start();

        // main 线程等待 t2 线程执行完成
        t2.join();
        System.out.println(Thread.currentThread().getName() + "-结束");
    }

}
