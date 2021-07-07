package univali.br.estrururasDeDados.lista;

import univali.br.estrururasDeDados.Node;

public class Lista<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public Lista(){
        this.first = null;
        this.last = null;
        this.size = 0;

    }

    /*
    Implementaçao da Prova
     */

    /**
     * Entao Prof eu ja tinha essa feita na minha implementação da lista
     * Ela nao estava completa, eu so adicionei o teste de estar vazia
     *
     *  Ex 1
     * Verifica se uma lista contem os mesmos objetos armazenados dentro delas
     * @param another list para comparação
     * @return se ambas guardam os objects com valores iguais
     */
    public boolean equals(Lista<T> another) {
        if (this.isEmpty() && another.isEmpty()) return true;
        if (this.size  != another.getSize())  return false;
        if (this.first.getValue() != another.getFirst().getValue()) return false;
        if (this.last.getValue()  != another.getLast().getValue())  return false;
        for (int i = 1; i < this.size-1; i++ ){
            if (get(i) != another.get(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * Ex 2
     * A lista começa por 0, mas para seguir o exemplo eu contei a posição 0 como 1 e assim por diante
     **/
    public Lista<T> makeSequence(){
        Lista<T> L2 = new Lista<>();
        for (int i = 0; i < getSize(); i++){
            for (int j = 0; j <= i;j++){
                L2.addLast(this.get(i));
            }
        }
        return L2;
    }

    /**
     * Ex 3
     */
    public void removeLastOcurrence(T toRemove){
        Node<T> current = this.last;
        int cont = this.size-1;

        while (current != null){
            if (current.getValue() == toRemove){
                remove(cont);
                return;
            }
            cont--;
            current = current.getLast();
        }
    }

    /*
        Implementação antiga
     */


    /**
     * Pega o objeto armazenado dentro de um node em uma determinada posição
     * @param position posição do node
     * @return objeto armazenado
     */
    public T get(int position){
        if (position<0 || position >= this.size){
            throw new IllegalArgumentException("get() recebeu um index fora do valor valido");
        }
        Node<T> current = this.first;
        for (int cont = 0; cont < position; cont++ ){
            current=current.getNext();
        }
        return current.getValue();
    }

    /**
     * Pega o node em um posição especifica da lista
     * @param position posição do node
     * @return node da posição
     */
    private Node<T>  getNode(int position){
        if (position<0 || position >= this.size){
            throw new IllegalArgumentException("getNode() recebeu um index fora do valor valido");
        }
        Node<T> current = this.first;
        for (int cont = 0; cont < position; cont++ ){
            current=current.getNext();
        }
        return current;
    }

    /**
     *Adiciona um novo node no final da lista
     * @param toAdd objeto que sera armazenado no node
     */
    public void addLast(T toAdd){
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

    /**
     * Adiciona um novo node no inicio da lista
     * @param toAdd objeto que sera armazenado no node
     */
    public void addFirst(T toAdd){
        if (isEmpty()){
            Node<T> added = new Node<T>(null,toAdd,null);
            this.first = added;
            this.last = added;
        }
        else {
            Node<T> added = new Node<T>(null,toAdd,this.first);
            this.first.setLast(added);
            setFirst(added);
        }
        this.size++;
    }

    /**
     * Pega o ultimo node da lista
     * @return ultimo node
     */
    public Node getLast() {
        return this.last;
    }

    /**
     * Adiciona um node em um posição especifica
     * @param toAdd objeto armazenado no node
     * @param position posição do node
     */
    public void add(T toAdd, int position){
        if (position == 0){
            addFirst(toAdd);
            return;
        }
        else{
            if (position > getSize()||position<0){
                throw new IllegalArgumentException("add() recebeu um index fora do valor valido");
            }
            else {
                if (position == this.size){
                    addLast(toAdd);
                }
                else {
                    Node nodePos= getNode(position);
                    Node<T> added = new Node<>(nodePos.getLast(),toAdd,nodePos);

                    nodePos.getLast().setNext(added);
                    nodePos.setLast(added);
                }
            }
        }

        this.size++;
    }

    /**
     * Remove o primeiro node da Lista
     */
    public T removeFirst(){
        if (isEmpty()){
            throw new IllegalStateException("Lista is empty");
        }
        T removed = this.first.getValue();
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
     * Remove o ultimo node da Lista
     */
    public void removeLast(){
        if (isEmpty()){
            //error
        }
        if (this.size == 1){
            this.first = null;
            this.last = null;
        }
        else {
            this.last.getLast().setNext(null);
            this.last = this.last.getLast();
        }
        this.size--;
    }

    /**
     * Remove um node especifico da Lista
     * @param position posição do node
     */
    public void remove(int position){

        if (position >= getSize()||position<0){
            throw new IllegalArgumentException("add() recebeu um index fora do valor valido");
        }

        if (position == 0){
            removeFirst();
        }
        else {
            if (position== this.size-1){
                removeLast();
            }
            else {
                Node<T> nodePos = getNode(position);


                Node<T> proxNode = nodePos.getNext();
                Node<T> antNode = nodePos.getLast();

                antNode.setNext(proxNode);
                proxNode.setLast(antNode);
                this.size--;

            }
        }


    }

    /**
     * Verifica se existe um objeto especifico na lista
     * @param toCompare objeto para comparar
     * @return true se existe um objeto  igual a toCompare, false caso não exista
     */
    public boolean contains(T toCompare) {
        Node<T> current = this.first;
        do {
            if (current.getValue() == toCompare)return true;
            current = current.getNext();

        }while(current != null);
        return false;
    }



    /**
     * Pega o primeiro node da lista
     * @return primeiro node
     */
    public Node<T> getFirst() {
        return first;
    }

    /**
     * Seta o primeiro node da Lista
     * @param first novo primeiro node
     */
    private void setFirst(Node<T> first) {
        this.first = first;
    }

    /**
     * Pega o tamanho da lista
     * @return tamanho
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @return true se vazio, false se nao
     */
    public boolean isEmpty(){
        return size == 0;
    }


    @Override
    public String toString() {

        Node current = this.first;
        String toPrint = "";
        while(current != null) {
            toPrint = toPrint+current.getValue()+" ";
            current = current.getNext();


        }
        return toPrint;
    }
}
