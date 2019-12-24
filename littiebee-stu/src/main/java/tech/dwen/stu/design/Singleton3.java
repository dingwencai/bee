package tech.dwen.stu.design;

public class Singleton3 {
    private static Singleton3 ourInstance = new Singleton3();

    public static Singleton3 getInstance() {
        return ourInstance;
    }

    private Singleton3() {
    }
}
