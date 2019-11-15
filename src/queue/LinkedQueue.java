package queue;

public class LinkedQueue<E> implements Queue<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }

    }

    private Node front;
    private Node tail;
    private int size;
    public LinkedQueue(){
        front=tail=new Node();
        size=0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void enQueue(E e) {
        if (isEmpty()){
            front = tail = new Node(e);
        }else{
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E deQueue() {
        if(isEmpty()){
            throw new IllegalArgumentException("队列为空");
        }
        Node retNode = front;
        front = front.next;
        retNode.next = null;
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        return front.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue : front ");
        Node cur = front;
        while(cur!=null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args) {

        LinkedQueue<Integer> arr = new LinkedQueue<>();
        arr.enQueue(1);
        arr.enQueue(2);
        arr.enQueue(3);
        arr.enQueue(4);
        System.out.println(arr);
        System.out.println(arr.getFront());
        arr.deQueue();
        arr.deQueue();
        arr.deQueue();
        arr.deQueue();
        System.out.println(arr);


    }
}
