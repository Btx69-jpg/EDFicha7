package org.example;


import DoublyLinkedList.*;

public class TesteEx2 {
    public static void main(String[] args) {
        DoublyOrderedList<String> list1 =  new DoublyOrderedList<String>();

        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add("5");
        list1.add("6");

        System.out.println(list1.printListFromLast());
        System.out.println(list1.printListFromFirst());
    }
}
