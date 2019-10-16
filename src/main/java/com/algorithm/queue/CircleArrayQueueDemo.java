package com.algorithm.queue;

import java.util.Scanner;

/**
 * 数组实现队列
 *
 * @author yusong
 * @version 1.0
 * @date 2019/10/8 19:38
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        char key;
        while (loop) {
            System.out.println("s: 打印队列");
            System.out.println("q: 退出");
            System.out.println("a: 入队列");
            System.out.println("p: 出队列");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    try {
                        arrayQueue.printQueue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'q':
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    arrayQueue.add(scanner.nextInt());
                    break;
                case 'p':
                    try {
                        int value = arrayQueue.poll();
                        System.out.println("出队列的元素: " + value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
            }
        }
        scanner.close();
        System.out.println("程序退出~");
    }


}

class CircleArrayQueue {

    /**
     * 首元素位置
     */
    private int front = 0;

    /**
     * 尾元素位置
     */
    private int rear = -1;

    private int maxSize;

    private int[] data;

    private int length;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
        length = 0;
    }

    public void add(int value) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        rear = (rear + 1) % maxSize;
        data[rear] = value;
        length++;
    }

    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        length--;
        int result = data[front];
        front = (front + 1) % maxSize;
        return result;
    }

    public boolean isFull() {
        return length >= maxSize;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void printQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        for (int i = front; i < front + length; i++) {
            System.out.printf("arr[%d] = %d\r\n", i, data[i % maxSize]);
        }
    }
}