package org.example;

import DoublyLinkedList.DoublyLinkedList;

public class ReplaceDemo {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        System.out.println("elements:" + list);

        list.Replace(1,8);

        System.out.println("elements after replace:" + list);
    }
}
