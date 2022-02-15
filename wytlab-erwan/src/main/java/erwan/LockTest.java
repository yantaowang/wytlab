package erwan;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static ReentrantLock reetrantLock = new ReentrantLock(true);
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock();
                try {
                    Thread.sleep(3 * 60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reetrantLock.unlock();
            }
        }).start();;

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock();
                reetrantLock.unlock();
            }
        }).start();
        while (true) {
            Thread.sleep(1000);
        }
    }

    public static void lock() {
        reetrantLock.lock();
    }
}
