package com.algorithm.queue;

import java.util.Scanner;

/**
 * 数组实现队列(只能用一次)
 *
 * @author yusong
 * @date 2019/10/3 0:36
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
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
                    arrayQueue.printQueue();
                    break;
                case 'q':
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    arrayQueue.add(scanner.nextInt());
                    break;
                case 'p':
                    int value = arrayQueue.poll();
                    System.out.println("出队列的元素: " + value);
                    break;
                default:
            }
        }
        scanner.close();
    }
}

class ArrayQueue {

    /**
     * 指向首元素的前一个位置
     */
    private int front = -1;

    /**
     * 指向尾元素
     */
    private int real = -1;

    private int maxSize;

    private int[] data;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
    }

    public boolean isEmpty() {
        return front == real;
    }

    public boolean isFull() {
        return real == maxSize - 1;
    }

    public void add(int value) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        data[++real] = value;
    }

    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return data[++front];
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
        }
        for (int i = front + 1; i <= real; i++) {
            System.out.println(data[i]);
        }
    }
}