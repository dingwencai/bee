package tech.dwen.stu.thread;

public class T3 {

    public static void main(String[] args) {
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("haha");
    }

}
