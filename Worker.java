class Worker {
    private int count = 0;
    public synchronized void increment() {
        count++;
    }
    public synchronized void decrement() {
        count--;
    }
    public synchronized int getCount() {
        return count;
    }
}

class MyThread extends Thread {
    private Worker worker;
    private boolean increment;

    public MyThread(Worker worker, boolean increment) {
        this.worker = worker;
        this.increment = increment;
    }

    public void run() {
        if (increment) {
            worker.increment();
        } else {
            worker.decrement();
        }
    }
}

public class SynchronizationExample {
    public static void main(String[] args) {
        Worker worker = new Worker();
        MyThread incrementThread1 = new MyThread(worker, true);
        MyThread incrementThread2 = new MyThread(worker, true);
        MyThread decrementThread1 = new MyThread(worker, false);
        MyThread decrementThread2 = new MyThread(worker, false);
        incrementThread1.start();
        incrementThread2.start();
        decrementThread1.start();
        decrementThread2.start();
        try {
            incrementThread1.join();
            incrementThread2.join();
            decrementThread1.join();
            decrementThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final count: " + worker.getCount());
    }
}
