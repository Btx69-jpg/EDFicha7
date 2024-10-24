package DoublyLinkedList;


import Exceptions.ConcorrentModificationException;
import Exceptions.ElementNotFoundException;
import Exceptions.EmptyCollectionException;
import Exceptions.WrongMethodException;
import Interfaces.ListADT;
import LinkedList.LinearNode;

import java.util.Iterator;

public class DoublyLinkedList<T> implements ListADT<T> {

    /**
     * The first node of the list.
     * */
    protected DoubleNode<T> first;

    /**
     * The last node of the list.
     * */
    protected DoubleNode<T> last;

    /**
     * The number of elements in the list.
     * */
    protected int count;

    /**
     * The number of modifications on the list.
     * */
    protected int modCount;

    public DoublyLinkedList() {
        this.first = null;
        this.last = null;
        this.count = 0;
        this.modCount = 0;
    }

    /**
     * Remove the first element of the list
     */
    @Override
    public T removeFirst() throws EmptyCollectionException {
        if (this.first == null){
            throw new EmptyCollectionException("A lista esta vazia");
        }
        DoubleNode<T> removed = this.first;
        this.first = removed.getNext();
        this.count--;
        this.modCount++;
        return removed.getElement();
    }

    /*
    * Metodo do artur, somente para poder fazer o ex3 parte dois, que não é possivel sem um metodo add, logo não é possivel ter direto nesta classe considerando
    * listas duplamente ligadas ordenadas e nao ordenadas
    * */
    public void add(T element) throws NullPointerException {
        if(element == null) {
            throw new NullPointerException("O elento que introduziu está a null");
        }

        DoubleNode<T> new_node = new DoubleNode<T>(element);

        if(this.last == null) {
            this.first = new_node;
        } else {
            new_node.setPrevious(this.last); /*Estou a indicar ao novo nó (ao novo elemento), que o próximo a ele é o que está no head*/
            this.last.setNext(new_node); /*Estou a dizer á head que ela agora vai estar atrás do nó*/
        }

        this.last = new_node;
        this.count++;
    }

    /**
     * Remove the last element of the list
     */
    @Override
    public T removeLast() throws EmptyCollectionException {
        if (this.count == 0){
            throw new EmptyCollectionException("A lista esta vazia");
        }

        DoubleNode<T> removed = this.last;
        this.last = removed.getPrevious();
        this.count--;
        this.modCount++;
        return removed.getElement();
    }

    /**
     * Remove a determined element of the list
     *
     * @param element element to remove
     */
    @Override
    public T remove(T element) throws EmptyCollectionException, ElementNotFoundException {
        T removed = null;
        boolean exists = false;
        if (this.count == 0){
            throw new EmptyCollectionException("A lista esta vazia");
        }

        DoubleNode<T> current = this.first;

        if (element == first.getElement()){
            removed = first.getElement();
            this.first = first.getNext();
            this.count--;
            this.modCount++;
            return removed;
        }

        while (current != null){
            if (current.getElement().equals(element)){
                //confirmar se é necessario estas exceptions por segurança ou se é preferivel deixar livre (utilizar estes metodos comprometera o desempenho quando utilizado em larga escala)
                if (current.getNext() == null){
                    throw new WrongMethodException("Para remover o ultimo elemento Utilize o metodo para remover o ultimo elemento");
                }
                if (current.getPrevious() == null){
                    throw new WrongMethodException("Para remover o primeiro elemento Utilize o metodo para remover o primeiro elemento");
                }
                removed = current.getElement();
                DoubleNode<T> previous = current.getPrevious();
                DoubleNode<T> next = current.getNext();
                previous.setNext(next);
                next.setPrevious(previous);

                exists = true;
                break;
            }

            current = current.getNext();
        }

        if (!exists){
            throw new ElementNotFoundException("O elemento nao existe na lista");
        }

        this.count--;
        this.modCount++;
        return removed;
    }

    /**
     * @return the first object of the list
     */
    @Override
    public T first() {
        return this.first.getElement();
    }

    /**
     * @return the last object of the list
     */
    @Override
    public T last() {
        return this.last.getElement();
    }

    /**
     * Returns true if this list contains the specified target
     * element.
     *
     * @param target the target that is being sought in the list
     * @return true if the list contains this element
     */
    @Override
    public boolean contains(T target) {
        DoubleNode<T> current = this.first;
        while (current != null){
            if (current.getElement().equals(target)){
                return true;
            }
            current = current.getNext();
        }

        return false;
    }

    /**
     * @return true if is empty and false if it isn't
     */
    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    /**
     * @return the size of the list
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Implementação do método iterator, que retorna uma instância de MyIterator.
     * @return Iterator<T> para percorrer a lista.
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    /**
     * Classe interna MyIterator que implementa o iterador para ArrayList.
     */
    private class MyIterator implements Iterator<T> {

        /**
         *
         * */
        private DoubleNode<T> currentNode; // Posição atual do iterador

        /**
         *
         * */
        private int currentPosition; // Posição atual do iterador

        /**
         *Boolean variable
         * */
        //verificar necessidade
        private boolean hasNext = false;

        private int expectedModCount = 0;

        private MyIterator(){
            currentPosition = 0;
            currentNode = first;
            expectedModCount = modCount;


        }
        @Override
        public boolean hasNext() {
            // Verifica se ainda há elementos na lista
            return currentPosition < count && currentNode.getNext() != null;
        }

        @Override
        public T next() throws ConcorrentModificationException {
            T element = null;
            if (this.expectedModCount != modCount){
                throw new ConcorrentModificationException("A lista foi modificada");
            }
            if (!hasNext()){
                throw new ElementNotFoundException("Não há mais elementos para iterar.");
            }
            // Retorna o próximo elemento da lista e incrementa a posição
            element = currentNode.getElement();

            currentPosition++;
            currentNode = currentNode.getNext();
            return element;
        }

        @Override
        public void remove() {
            // Remove o elemento atual
            throw new UnsupportedOperationException("Operação de remoção não suportada.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        DoubleNode<T> current = this.first;

        while (current != null) {
            sb.append(current.getElement());
            current = current.getNext();
            if (current != null) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    // Método recursivo para imprimir os elementos da lista
    public String printListRecursiveFromFirst(DoubleNode<T> node) {
        if (node == null) {  // Caso base: se o nó for null, fim da lista
            return "";
        }
        return node.getElement() + ", " + printListRecursiveFromFirst(node.getNext());     // Chama recursivamente para o próximo nó
    }

    public String printListRecursiveFLast(DoubleNode<T> node) {

        if (node == null) {  // Caso base: se o nó for null, fim da lista
            return  "";
        }
        return  printListRecursiveFLast(node.getNext()) + ", " + node.getElement() ;     // Chama recursivamente para o próximo nó
    }

    // Método auxiliar para iniciar a chamada recursiva a partir do primeiro nó
    public String printListFromFirst() {
        return printListRecursiveFromFirst(this.first);  // Chama o método recursivo começando do head
    }

    // Método auxiliar para iniciar a chamada recursiva a partir do ultimo nó
    public String printListFromLast() {
        return printListRecursiveFLast(this.first);  // Chama o método recursivo começando do head
    }

    /*
    public DoublyLinkedList<T> inverseListRecursive(DoublyLinkedList<T>invList ,DoubleNode<T> current) {

        if (invList.last == this.first) {  // Caso base: se o nó for null, fim da lista
            return  null;
        }
        invList.add(node.getElement());
        return invList.add();
                //inverseListRecursive(invList, node.getPrevious());
    }

    public DoublyLinkedList<T> invertList(){
        DoublyLinkedList<T> invList = this;
        return inverseListRecursive(invList, this.last);
    }

     */

    // Método recursivo para preencher uma nova lista com os elementos em ordem inversa
    private void fillReverse(DoubleNode<T> node, DoublyLinkedList<T> reversedList) {
        if (node == null) { // Caso base: quando chegamos ao início da lista
            return;
        }
        reversedList.add(node.getElement()); // Adiciona o elemento atual à nova lista
        fillReverse(node.getPrevious(), reversedList); // Chama recursivamente para o nó anterior
    }

    // Método público para retornar uma nova lista com os elementos em ordem inversa
    public DoublyLinkedList<T> reverseList() {
        DoublyLinkedList<T> reversedList = new DoublyLinkedList<T>(); // Cria nova lista
        fillReverse(this.last, reversedList); // Chama o método recursivo para preencher a nova lista
        return reversedList; // Retorna a lista invertida
    }

    public void ReplaceRecursiv(T existEl, T newEl, DoubleNode<T> first){
        if (first == null){
            return;
        }
        if (existEl.equals(first.getElement())){
            first.setElement(newEl);
        }
        first = first.getNext();
        ReplaceRecursiv(existEl, newEl, first);
    }
    public void Replace(T ExistingElement, T newEl){
        ReplaceRecursiv(ExistingElement, newEl, this.first);
    }

}
