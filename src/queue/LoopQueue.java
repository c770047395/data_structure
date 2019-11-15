package queue;



public class LoopQueue<E> implements Queue<E> {
    private E[] data;
     int front;
     int tail;
    private int size;
    public LoopQueue(int initialCapacity) {
        data =(E[]) new Object[initialCapacity];
        front=tail=size=0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front==tail;
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for(int i = 0; i < size; i ++ ){
            newData[i] = data[(front+i)%data.length];
        }
        front=0;
        tail=size;
        data=newData;
    }

    @Override
    public void enQueue(E e) {
        if((tail+1)%data.length == front){
            resize(data.length*2);
        }

        data[tail] = e;
        tail = (tail+1)%data.length;
        size++;
    }

    @Override
    public E deQueue() {
        if(size==0){
            throw new IllegalArgumentException("队列为空");
        }
        if(size==data.length/4 && data.length/2 != 0 ){
            resize(data.length/2);
        }
        E ret = data[front];
        front = (front+1)%data.length;
        size--;
        return ret;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue : size = %d , capacity = %d \n",size,data.length));
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
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0 ;i < 100; i++){
            queue.enQueue(i);
        }
        System.out.println(queue);
        for (int i = 0 ; i < 100 ; i++){
            System.out.println(queue.deQueue());

        }
        System.out.println(queue);



    }
}
