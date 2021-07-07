package univali.br.estrururasDeDados.pilha;

import univali.br.estrururasDeDados.Node;
import univali.br.estrururasDeDados.fila.Fila;

/**
 * Estrutura de Dados Pilha implementada de forma dinamica
 * Inicia na posiçao 0
 * @param <T> Type dos elementos que serão guardados
 */
public class Pilha<T> {
    private int size;
    private Node<T> top;

    /**
     * Construtor
     */
    public Pilha(){
        this.size = 0;
        this.top = null;
    }

    /**
     * Adiciona um elemento ao topo da Pilha
     * @param element elemento a ser adicionado
     */
    public void add(T element){
        Node<T> toAdd = new Node<>(null,element,this.top);
        setTop(toAdd);
        this.size++;
    }

    /**
     * Remove o elemento no topo da Pilha
     * @return Elemento removido
     */
    public T remove(){
        if (isEmpty())throw new IllegalStateException("Pilha is empty");
        T removed = top.getValue();
        if (this.size == 1){
            setTop(null);
        }
        else {
            setTop(this.top.getNext());
            top.setLast(null);
        }
        this.size--;
        return removed;
    }

    /**
     * Pega um elemento em determinada posição
     * @param index posiçao do elemento
     * @return elemento
     */
    public T get(int index){
        if (isEmpty())throw new IllegalStateException("Pilha is empty");
        if (!validPosition(index)) throw new IllegalArgumentException("Index is out of range");
        Node<T> current = top;
        for (int i = size-1; i > index ; i--) {
            current = this.top.getNext();
        }
        return  current.getValue();
    }

    /**
     * Pega o topo da pilha
     * @return
     */
    public T getTop(){
        if (isEmpty())return null;
        return this.top.getValue();
    }

    /**
     * Verifica se a Pilha possui um elemento especifico
     * @param element elemento a ser procurado
     * @return a posiçao da primeira ocorrencia do elemento, ou -1 caso nao haja nenhuma ocuorrencia
     */
    public int find(T element){
        Node<T> current = top;
        for (int index = 0; index <= getSize() ; index++) {
            if (current.getValue() == element)return index;
            current = this.top.getNext();
        }
        return -1;
    }

    /**
     * Coloca um Node como o topo da pilha
     * @param top Node que sera o novo topo
     */
    private void setTop(Node<T> top) {
        this.top = top;
    }

    /**
     * @return quantidade de elementos da pilha
     */
    public int getSize() {
        return size;
    }

    /**
     * @return true se a pilha esta vazia, false se não
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Verifica se uma posiçao é valida para a pilha
     * @param index posição a ser testada
     * @return true se valida, false se nao
     */
    public boolean validPosition(int index){
        return size>index && index > -1;
    }

    /**
     * Inverte a ordem de uma pilha
     */
    public void invertOrder(){
        Fila<T> temp = new Fila<>();
        int cont = this.size;
        for (int i = 0; i < cont; i++) {
            temp.add(remove());
        }
        cont = temp.getSize();
        for (int i = 0; i < cont; i++) {
            this.add(temp.remove());
        }
    }
    /**
     * Coloca os elementos da Pilha em uma fila, o Topo da Pilha sendo o primeiro elemento
     * @return Fila com os elementos de Pilha
     */
    public Fila<T> toFila(){
        Fila<T> transformed = new Fila<>();
        Pilha<T> temp = new Pilha<>();
        int cont =  this.size;
        for (int i = 0; i < cont; i++) {
            temp.add(this.remove());
        }
        for (int i = 0; i < cont; i++) {
            transformed.add(temp.remove());
        }
        return transformed;
    }

    @Override
    public String toString() {

        Node<T> current = this.top;
        String toPrint = "";
        while(current != null) {
            toPrint = toPrint+current.getValue()+" ";
            current = current.getNext();


        }
        return toPrint;
    }


}
