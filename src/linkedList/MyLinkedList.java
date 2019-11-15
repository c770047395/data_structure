package linkedList;

public class MyLinkedList<E> {
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
        public String toString() {
            return e.toString();
        }
    }
    private Node dummyNode;
    private int size;
    public MyLinkedList(){
        dummyNode = new Node();
        size = 0;
    }
    private Node findPre(int index){
        Node prev = dummyNode;
        for (int i = 0 ; i < index; i ++){
            prev = prev.next;
        }
        return prev;
    }
    public void add(int index,E e){
        if (index<0||index>size){
            throw new IllegalArgumentException("索引越界");
        }
        Node prev = findPre(index);
        prev.next = new Node(e,prev.next);
        size++;
    }
    public E remove(int index){
        if (index<0||index>size-1){
            throw new IllegalArgumentException("索引越界");
        }
        Node pre = findPre(index);
        Node ret = pre.next;
        pre.next = pre.next.next;
        ret.next = null;
        size --;
        return ret.e;
    }

    public void addFirst(E e){
        add(0,e);
    }
    public void addLast(E e){
        add(size, e);
    }
    public E removeFirst(){
        return remove(0);
    }
    public E removeLast(){
        return remove(size-1);
    }

    public E get(int index){
        if (index<0||index>size-1){
            throw new IllegalArgumentException("索引越界");
        }
        Node pre = findPre(index);
        return pre.next.e;
    }
    public E getFirst(){
        return get(0);
    }
    public E getLast(){
        return get(size-1);
    }
    public void set(int index,E e){
        if (index<0||index>size-1){
            throw new IllegalArgumentException("索引越界");
        }
        Node pre = findPre(index);
        pre.next.e = e;
    }

    public boolean contain(E e){
        Node cur = dummyNode.next;
        while(cur!=null){
            if (cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyNode.next;
        while(cur!=null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.remove(1);
        list.removeFirst();
        list.removeLast();
        System.out.println(list);
    }



}
