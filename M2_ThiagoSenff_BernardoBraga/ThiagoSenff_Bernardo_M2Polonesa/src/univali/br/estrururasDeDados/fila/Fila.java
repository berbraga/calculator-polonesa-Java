package univali.br.estrururasDeDados.fila;

import univali.br.estrururasDeDados.*;

import java.text.DecimalFormat;

public class Fila<T>{
    private Node<T> first;
    private Node<T> last;
    private int size;


    public Fila(){
        this.first = null;
        this.last = null;
        this.size = 0;

    }

    public T get(int position){
        if (position<1 || position > this.size){
            throw new IllegalArgumentException("get() recebeu um index fora do valor valido");
        }
        Node<T> current = this.first;
        for (int cont = 1; cont < position; cont++ ){
            current=current.getNext();
        }
        return current.getValue();
    }

    private Node<T> getNode(int position){
        if (position<0 || position >= this.size){
            throw new IllegalArgumentException("getNode() recebeu um index fora do valor valido");
        }
        Node<T> current = this.first;
        for (int cont = 0; cont < position; cont++ ){
            current=current.getNext();
        }
        return current;
    }

    public void add(T toAdd){


        if (isEmpty()){
            Node<T> added = new Node<T>(null,toAdd,null);
            this.first = added;
            this.last = added;
        }
        else {
            Node<T> added = new Node<T>(this.last,toAdd,null);
            this.last.setNext(added);
            this.last = added;
        }

        this.size++;
    }

    public T remove(){
        if (isEmpty()){
            throw new IllegalStateException("Caller is empty ");
        }
        T removed = first.getValue();
        if (this.size == 1){
            this.first = null;
            this.last = null;
        }
        else{
            this.first.getNext().setLast(null);
            this.first = this.first.getNext();
        }
        this.size--;
        return removed;
    }

    /**
     * Pega o tamanho da lista
     * @return tamanho
     */
    public int getSize() {
        return size;
    }

    /**
     * @return true se vazio, false se nao
     */
    public boolean isEmpty(){
        return size == 0;
    }



    @Override
    public String toString() {

        Node<? extends T> current = this.first;
        String toPrint = "";
        while(current != null) {
            toPrint = toPrint+current.getValue()+" ";
            current = current.getNext();
        }
        return toPrint;
    }
}



