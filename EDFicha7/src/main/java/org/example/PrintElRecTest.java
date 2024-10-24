package org.example;

import LinkedList.LinkedList;

public class PrintElRecTest {
    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<Integer>();

        list.add(5);
        list.add(7);
        list.add(1);
        list.add(2);

        System.out.println(list.printList());
    }

}
