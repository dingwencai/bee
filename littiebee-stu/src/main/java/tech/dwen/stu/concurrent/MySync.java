package tech.dwen.stu.concurrent;

/**
 * Created by MrCai on 2020/1/4.
 */
public class MySync {

    public int i;

    public void syncTask(){
        synchronized (this) {
            i++;
        }
    }

}
