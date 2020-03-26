package tech.dwen.stu.thread;

import java.util.concurrent.*;

/**
 * 线程创建的方式
 * Created by MrCai on 2019/12/30.
 */
public class Th1 {

    static class MyCall implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("hello mycall");
            return "hello";
        }
    }

    static class MyRun implements Runnable{

        @Override
        public void run() {
            System.out.println("MyRun");
        }
    }

    static class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println("MyThread");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(new FutureTask<String>(new MyCall())).start();

        new Thread(()->{
            System.out.println("lambda");
        },"name").start();

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(()->{
            System.out.println("thread pool");
        });
        service.shutdown();
    }

}
