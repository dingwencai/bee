package tech.dwen.stu.design;

public class Singleton1 {

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
