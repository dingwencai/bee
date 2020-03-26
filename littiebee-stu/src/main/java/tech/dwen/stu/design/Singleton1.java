package tech.dwen.stu.design;

public class Singleton1 {

    /**
     * volatile(1.可见性；2.禁止指令重排):
     * 1.对象的创建过程
     *   1.1 分配内存(成员变量赋值默认值。int 变量赋值为 0；对象赋值为 null)
     *   1.2 调用构造方法(设置初始值)
     *   1.3 引用指向分配的内存地址
     * 2.volatile 禁止指令重新排列(JVM层面增加了内存屏障)
     *   new Singleton1(); 分为3个指令。
     *
     * 线程1：指令重排后，执行了 1.1；1.3，让出CPU
     * 线程2：instance != null 成立，然后使用的是一个半初始化对象。
     */
    private static volatile Singleton1 instance = null;

    private Singleton1(){}

    public Singleton1 getInstance(){
        if (instance == null) {

            synchronized (Singleton1.class) {
                if (instance == null) {
                    instance = new Singleton1();
                }
            }
        }
        return instance;
    }

}
