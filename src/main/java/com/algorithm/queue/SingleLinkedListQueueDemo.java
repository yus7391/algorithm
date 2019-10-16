package com.algorithm.queue;

/**
 * 单向链表
 *
 * @author yusong
 * @version 1.0
 * @date 2019/10/11 19:02
 */
public class SingleLinkedListQueueDemo {

    public static void main(String[] args){
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(3);
        singleLinkedList.add(4);
        singleLinkedList.add(5);
        System.out.println(singleLinkedList);
        Integer remove = singleLinkedList.remove(1);
        System.out.println("remove:  " + remove);
        System.out.println(singleLinkedList);
        Integer pop = singleLinkedList.pop();
        System.out.println("pop:  " + pop);
        System.out.println(singleLinkedList);
        singleLinkedList.reverse();
        System.out.println(singleLinkedList);
    }
}

class SingleLinkedList<T> {

    /**
     * 指向头结点的前一个位置
     */
    private Node<T> front;

    private int length;

    public SingleLinkedList() {
        front = new Node(null, null);
    }

    @Override
    public String toString() {
        return "SingleLinkedList{" +
                "front=" + front +
                ", length=" + length +
                '}';
    }

    public int size() {
        return length;
    }

    public void add(T data) {
        Node temp = front;
        Node node = new Node(null, data);
        for(;;) {
            if (temp.next == null) {
                temp.next = node;
                length ++;
                break;
            }
            temp = temp.next;
        }
    }

    public T remove(int index) {
        if (index > length - 1) {
            throw new RuntimeException("index = " + index  + "isn`t exist");
        }
        Node<T> temp = front;
        for (int i = 0; i < length; i++) {
            if (index == i) {
                Node<T> cur = temp.next;
                temp.next = cur.next;
                length --;
                return cur.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public T pop() {
        if (size() == 0) {
            throw new RuntimeException("队列为空");
        }
        return remove(length - 1);
    }

    /**
     * 反转可以看做是先删除该节点再在首部插入该节点
     */
    public void reverse() {
        if (size() == 0) {
            throw new RuntimeException("队列为空");
        }
        if (size() == 1) {
            return;
        }
        // node1 是node2的前一个节点
        Node<T> node1 = front.next;
        // node2 看作是先删除再插入的节点
        Node<T> node2 = node1.next;
        for (int i = 1; i < length; i++) {
            node1.next = node2.next;
            node2.next = front.next;
            front.next = node2;
            node2 = node1.next;
        }
    }


    private static class Node<T> {

        Node<T> next;

        T data;

        public Node(Node<T> next, T data) {
            this.next = next;
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }
}
