package org.example;

import DoublyLinkedList.DoublyLinkedList;

public class TestEx3 {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

        list.add(1);
        System.out.println(list.toString());
        list.add(2);
        System.out.println(list.toString());
        list.add(3);
        System.out.println(list.toString());
        list.add(4);

        System.out.println(list.toString());
        System.out.println(list.reverseList().toString());

    }
}
