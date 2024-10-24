
package DoublyLinkedList;

public class DoubleNode<T> {
    private T element;         // Dado armazenado no nó
    private DoubleNode<T> next; // Referência para o próximo nó
    // O ANTES É UMA CARACTERISTICA DA LISTA DUPLAMENTE LIGADA, O NO SENTINELA É EXATAMENTE IGUAL AO NO LINEAR
    // MAS COM O HEAD E O TAIL NULOS
    private DoubleNode<T> previous; // Referência para o nó anterior

    // Construtor padrão que inicializa um nó vazio
    public DoubleNode() {
        this.element = null;
        this.next = this.previous = null;
    }

    // Construtor que inicializa o nó com um elemento
    public DoubleNode(T elem) {
        this.element = elem;
        this.next  = this.previous = null;
    }

    // Retorna o elemento armazenado no nó
    public T getElement() {
        return element;
    }

    // Define o elemento do nó
    public void setElement(T elem) {
        this.element = elem;
    }

    // Retorna o nó anterior
    public DoubleNode<T> getPrevious() {
        return previous;
    }

    // Define o nó anterior
    public void setPrevious(DoubleNode<T> previousNode) {
        this.previous = previousNode;
    }
    // Retorna o próximo nó
    public DoubleNode<T> getNext() {
        return next;
    }

    // Define o próximo nó
    public void setNext(DoubleNode<T> nextNode) {
        this.next = nextNode;
    }

}
