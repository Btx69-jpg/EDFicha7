package LinkedList;

public class LinearNode<T> {

    private T element;

    private LinearNode<T> next_ele;

    public LinearNode() {
        this.next_ele = null;
        this.element = null;
    }

    public LinearNode(T next) {
        this.next_ele = null;
        this.element = next;
    }

    public LinearNode<T> getNext() {
        return this.next_ele;
    }

    public void setNext(LinearNode<T> next_ele) {
        this.next_ele = next_ele;
    }

    public T getElement() {
        return this.element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    //Ver se posso ter este toString
    @Override
    public String toString() {
        return this.element.toString();
    }
}