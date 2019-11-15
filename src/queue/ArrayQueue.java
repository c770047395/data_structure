package queue;

import array.MyDynamicArray;

public class ArrayQueue<E> implements Queue<E> {
    private MyDynamicArray<E> array;

    public ArrayQueue() {
        array = new MyDynamicArray();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void enQueue(E e) {
        array.addLast(e);
    }

    @Override
    public E deQueue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }


}
