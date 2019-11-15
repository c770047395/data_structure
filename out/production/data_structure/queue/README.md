# 2.队列（Queue）
队列是一种先进先出（First In Fist Out）的数据结构，平时生活中的排队基本上都是可以看成是一种队列的结构，例如去银行取钱，大家从依次排好顺序，先来的人站前，后来的人只能站后面，先来的人先办完事，就可以先走了，这就是一种队列的结构，同样的，对队列的操作我也定义了一个接口

```
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

```
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