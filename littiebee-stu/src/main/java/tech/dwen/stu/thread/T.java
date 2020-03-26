package tech.dwen.stu.thread;

public class T {

    private static Object o = new Object();

    private static long count = 0;

    public void test() {
        //1
        synchronized (o) {

            for (int j = 0; j < 1000_0000; j++) {
                count = count + 1;
            }
        }
    }

    public static void main(String[] args) {

    }
}
