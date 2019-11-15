# 3.链表
链表是一种真正的，最基础的动态数据结构，基础的链表头节点指向索引为0的地方，但是链表有一个特性，就是对当前节点操作麻烦，对当前节点的下一节点，即next节点操作简单，所以我们定义了一个虚拟头节点dummyHead，里面啥也不存，就用来指向真正链表的头节点。

## 3.1链表的基础结构
首先，我们在链表类中定义了一个内部类，用来存放节点的结构

```
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
```

然后为链表类添加两个成员变量，分别是dummyHead和size，用来记录虚拟头节点的位置和当前链表的长度

```
    private Node dummyHead;
    private int size;
```

而后就是定义一些关于链表的基本操作

## 3.2链表的初始化
链表的初始化就是生成一个链表的虚拟头节点以及设置一下链表的size=0 就完成了链表的初始化

```
    public LinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }
```

## 3.2链表的插入与删除操作
在实际工程中，对链表的插入与删除基本上只要对链表的头进行操作，没有索引的概念，索引的概念只是在起一个练习的作用
**新增操作**（add（））：

```
    public void add(int index,E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("add failed.Illegal index.");
        }
        Node prev = dummyHead;
        for(int i = 0 ; i<index ; i ++){
                prev = prev.next;
        }
//       Node node = new Node(e);
//       node.next = prev.next;
//       prev.next = node;
        prev.next = new Node(e,prev.next);
        size ++;

    }
```
首先检查一下索引的正确性，然后将前驱节点prev从虚拟头节点出发，不断下移，直到指向index处的前一个节点。最后将新生成节点插入前驱节点的下一个节点并连接上后面的节点就完成了插入操作。当然最后还得维护一下size变量，将他进行加一操作。

**删除操作**（remove（））：

```
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("set failed.Illegal index.");
        }
        Node prev = dummyHead;
        for(int i = 0 ; i < index; i++){
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size -- ;
        return delNode.e;
    }
```

与新增操作大同小异，都是通过前驱节点找到所要删除的节点，然后继续操作。

链表的总代码如下

```
public class LinkedList<E> {

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

    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void addFirst(E e){
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
//        dummyHead.next = new Node(e, dummyHead.next);
//        size ++;
        add(0,e);
    }

    public void add(int index,E e){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("add failed.Illegal index.");
        }
        Node prev = dummyHead;
        for(int i = 0 ; i<index ; i ++){
                prev = prev.next;
        }
//       Node node = new Node(e);
//       node.next = prev.next;
//       prev.next = node;
        prev.next = new Node(e,prev.next);
        size ++;

    }

    public void addLast(E e){
        add(size,e);
    }

    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("get failed.Illegal index.");
        }

        Node cur = dummyHead.next;
        for(int i = 0 ;i < index ; i ++){
            cur=cur.next;
        }
        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    public void set(int index , E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("set failed.Illegal index.");
        }
        Node cur = dummyHead.next;
        for(int i = 0; i < index ; i++){
            cur = cur.next;
        }
        cur.e = e ;
    }

    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("set failed.Illegal index.");
        }
        Node prev = dummyHead;
        for(int i = 0 ; i < index; i++){
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size -- ;
        return delNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while(cur!=null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }

}

```
# 4.总结

 - 栈是后进先出的数据结构，具体实验了两种实现方法，数组栈和链表栈。**数组栈**新增与删除操作都是对数组尾部进行操作，由于有size的存在，知道尾部在哪，不过要不断扩容，所以均摊复杂度为O（1）。**链表栈**由于都只对头节点进行操作，时间复杂度也是一样都为O（1），但是链表所实现的是真正动态。
 - 队列是先进先出的数据结构，首先用了**数组队列**，发现出队时由于对队首进行操作需要进行后续元素的迁移，导致时间复杂度为O（n），为解决此方法，我们改进了队列成为**循环队列**，在数组中加入了一个front变量和tail变量，分别存储头尾的索引。这样入队和出队操作都变成了均摊复杂度为O（1）的操作了。最后我们也能用改进的链表实现**链表队列**这个结构，在链表中加入一个记录尾部的tail节点，就可以方便尾部数据插入，而头部数据插入和删除都是容易的，所以用链表实现的这个队列结构也是时间复杂度都为O（1）的。
 - 链表是一种真正的动态的数据结构。对头部的新增删除查询操作容易，对搜索和其他位置的新增删除操作不容易。