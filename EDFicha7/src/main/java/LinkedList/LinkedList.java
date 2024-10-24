package LinkedList;

import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;

public class LinkedList<T> {

    /**
     * O count indica o numero de elementos já guardados
     */
    private int count;
    /**
     * O head é o array,
     * o tail serve para dizer onde começa o array
     */
    private LinearNode<T> head, tail;

    public LinkedList() {
        this.count = 0;
        this.head = this.tail = null;
    }

    public boolean add(T element) {
        LinearNode<T> new_node = new LinearNode<T>(element);

        if(this.head == null) {
            this.tail = new_node;
        } else {
            new_node.setNext(this.head);
        }

        this.head = new_node;
        this.count++;
        return true;
    }

    public boolean remove(T element) throws EmptyCollectionException {
        if(this.count == 0) {
            throw new EmptyCollectionException("A lista está vazia");
        }

        LinearNode<T> current = head;
        LinearNode<T> previous = null;

        boolean find = false;

        while(current != null && find == false) {
            if(current.getElement().equals(element)) {
                find = true;
            } else {
                previous = current;
                current = current.getNext();
            }
        }

        if(find == false) {
            throw new ElementNotFoundException("O elemento a eliminar não existe!");
        }

        if(count == 1) {
            this.head = null;
            this.tail = null;
        } else if (current.getElement().equals(this.head.getElement())) {
            this.head = current.getNext();
        } else if (current.getElement().equals(this.tail.getElement())) {
            previous.setNext(null);
            this.tail = previous;
        } else {
            previous.setNext(current.getNext());
        }

        this.count--;
        return true;
    }

    public LinearNode<T> getFirst() {
        return this.tail;
    }

    public int size() {
        return this.count;
    }

    /**
     * Faz de forma descrecente
     */
    public void imprimeValores() {
        if (this.count == 0) {
            System.err.println("A lista está vazia!");
        } else {
            LinearNode<T> current = this.head;
            int i = this.count;

            while(current != null) {
                System.out.println("Valor nº" + i-- + ": " + current.getElement());
                current = current.getNext();
            }

            System.out.println("Fim da lista");
        }
    }

    public String value(LinkedList<T> next) throws EmptyCollectionException {
        if(next.count == 0) {
            System.out.println("Fim da lista");
        } else {
            LinearNode<T> current = next.head;
            current.getNext();
            next.remove(current.getElement());
            System.out.println(current.toString() + " " + value(next));
        }

        return "";
    }

    // Método recursivo para imprimir os elementos da lista
    public String printListRecursive(LinearNode<T> node) {
        if (node == null) {  // Caso base: se o nó for null, fim da lista
            return "";
        }
        return node.getElement() + ", " +printListRecursive(node.getNext());     // Chama recursivamente para o próximo nó
    }

    // Método auxiliar para iniciar a chamada recursiva a partir do primeiro nó
    public String printList() {
        return printListRecursive(this.head);  // Chama o método recursivo começando do head
    }
}