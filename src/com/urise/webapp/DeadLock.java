package com.urise.webapp;

public class DeadLock {
    public static void main(String[] args) {
        final String lock1 = "lock1";
        final String lock2 = "lock2";
        runDeadlock(lock1, lock2);
        runDeadlock(lock2, lock1);
    }

    static void runDeadlock(String firstLock, String secondLock) {
        new Thread(() -> {
            System.out.println(firstLock + " поток ожидает");
            synchronized (firstLock) {
                System.out.println(firstLock + " поток удерживает");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(secondLock + " поток ожидает");
                synchronized (secondLock) {
                    System.out.println(secondLock + " поток удерживает");
                }
            }
        }).start();
    }
}
