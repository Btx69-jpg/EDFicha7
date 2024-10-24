package Interfaces;

public interface OrderedListADT<T> extends ListADT<T> {
    /**
     * Metodo para adicionar um elemento a lista ordenada, recebe um elemento do tipo comparavel para poder ordenar o mesmo na lista com base na caracteristica do elemento.
     * @param element elemento a ser adicionado
     * */
    public void add(T element);
}
