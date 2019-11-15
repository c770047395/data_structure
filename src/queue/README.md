# 2.队列（Queue）
队列是一种先进先出（First In Fist Out）的数据结构，平时生活中的排队基本上都是可以看成是一种队列的结构，例如去银行取钱，大家从依次排好顺序，先来的人站前，后来的人只能站后面，先来的人先办完事，就可以先走了，这就是一种队列的结构，同样的，对队列的操作我也定义了一个接口

```java
public interface Queue<E> {
    void enqueue(E e);//入队
    E dequeue();//出队
    E getFront();//获取队首元素
    int getSize();//获取队列长度
    boolean isEmpty();//判断队列是否为空
}

```

## 2.1数组队列（ArrayQueue）
数组队列与数组栈基本相同，都是底层通过一个动态数组实现，只不过数组队列是从数组的一头进，另一头出，而数组栈都是同一头进出。

```java
public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;
    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }
    public ArrayQueue(){
        array = new Array<>();
    }

    @Override
    public void enqueue(E e){
        array.addLast(e);
    }

    @Override
    public E dequeue(){
        return array.removeFirst();
    }

    @Override
    public E getFront(){
        return array.getFirst();
    }

    @Override
    public int getSize(){
        return array.getSize();
    }

    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for(int i = 0 ; i < array.getSize() ; i++){
            res.append(array.get(i));
            if(i != array.getSize() - 1){
                res.append(",");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}

```
看了代码，不难发现，入队的操作时间复杂度是O（1），但是出队操作由于要从数组头取出一个元素，就要将数组中后面的每一个元素都向前移动一位，这样做的时间复杂度是O（n），那有没有什么办法能解决这一问题呢？所以我们对这个底层的数组进行了改进，加上一个**记录队首索引**的成员变量和**记录队尾索引**的成员变量，将数组进行改进，成为一个循环数组

## 2.2循环队列（LoopQueue）
具体操作其实不难，这时的底层就是我们要新修改的数组，不用原来的数组

```java
    private E[] data;
    private int front;
    private int tail;
    private int size;
```
定义了以上变量，front用来记录队首索引，tail用来记录队尾索引，当front = tail时，队列为空，当tail + 1 = front 时，队列为满，也就是说，队列满的时候，我们其实有一个空间是浪费的，即tail索引指向的空间是浪费的，所以在声明的容量的时候，我们要将队列实际的容量+1才能得到这么大的容量。

有了队首和队尾之后，我们对队列的操作就简单了许多，当入队时，我们只要将元素插入tail所指向的索引处，然后维护一下tail 将tail+1即可（实际上tail+1不准确，由于front会进行移动，所以队列可能会出现数组前几个索引是空的情况，这时要将tail重新指向这些地方，所以入队后tail应该是等于（tail+1）%data.length）。出队操作也十分容易，将front索引处的元素释放，然后将front+1(实际上与tail相同，都要（front+1）%data.length)，就完成了一次出队操作。

这样一来，入队和出队都只需要对front、tail的索引处进行操作，有时会涉及到扩容的操作，所以均摊复杂度为O（1），就改善了普通数组队列出队时的时间复杂度是O（n）的情况。
```java
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }
    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public boolean isEmpty(){
        return front==tail;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public void enqueue(E e){
        if((tail + 1) % data.length == front){
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;

    }

    @Override
    public E dequeue(){

        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if(size == getCapacity() /4 && getCapacity()/2 != 0){
            resize(getCapacity()/2);
        }
        return ret;

    }

    @Override
    public E getFront(){
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot getFront from an empty queue");
        }
        return data[front];
    }
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity+1];
        for(int i = 0 ; i < size ; i ++ ){
            newData[i] = data[(front+i) % data.length];
        }
        data = newData;
        front = 0 ;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue : size = %d , capacity = %d \n",size,getCapacity()));
        res.append("front [");
        for(int i = front ; i != tail   ; i=(i+1) % data.length){
            res.append(data[i]);
            if((i+1) % data.length != tail){
                res.append(",");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {

        LoopQueue<Integer> arr = new LoopQueue<>();
        for(int i = 0 ; i<100000 ; i++){
            arr.enqueue(i);
            System.out.println(arr);
            if(i % 3 == 2){
                arr.dequeue();
                System.out.println(arr);
            }
        }

    }
}

```
