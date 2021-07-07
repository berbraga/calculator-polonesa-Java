package univali.br.estrururasDeDados;

public class Node<T> {
    private Node<T> last ;
    private T value;
    private Node<T> next;

    public Node(Node<T> last, T value, Node<T> next){
        this.last = last;
        this.value = value;
        this.next = next;
    }

    public Node<T> getLast() {
        return last;
    }

    public void setLast(Node<T> anterior) {
        this.last = anterior;
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
