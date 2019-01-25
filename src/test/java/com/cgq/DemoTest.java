package com.cgq;

/**
 * Created by 1 on 2018/9/19.
 */
public class DemoTest {
    public static void main(String[] args) {
        final Business business = new Business();

        // 子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    business.sub(i);
                }
            }
        }).start();

        // main方法主线程
        for (int i = 0; i < 5; i++) {
            business.main(i);
        }
    }

}


class Business{
    // 子线程是否可以调用
    private boolean subShould = true;

    // 子线程业务方法
    public synchronized void sub(int i){
        while(!subShould){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 4; j++) {
            System.out.println("sub thread sequence of " + j + " ,loop of " + i);
        }
        subShould = false;
        this.notify();
    }

    // 主线程业务方法
    public synchronized void main(int i){
        while(subShould){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 3; j++) {
            System.out.println("main thread sequence of " + j + " ,loop of " + i);
        }
        subShould = true;
        this.notify();
    }
}