package tech.dwen.stu.m;

/**
 * Created by MrCai on 2019/12/24.
 */
public class T1 {

    private volatile int n = 0;

    public void add() {
      n++;
    }

    public static void main(String[] args) {
        T1 o = new T1();

        for(int i = 0; i < 10 ; i++) {
            new Thread(()->{
                o.add();
            },"").start();
        }
    }

}
