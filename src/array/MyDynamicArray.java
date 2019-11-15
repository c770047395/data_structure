package array;


import java.util.Arrays;

public class MyDynamicArray<E> {
    private E[] data;
    private int size;

    public MyDynamicArray(int initialCapacity) {
        data = (E[]) new Object[initialCapacity];
        size = 0;
    }

    public MyDynamicArray() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("索引小于0或者大于数组长度");
        }
        if (size == data.length) {
            resize(data.length * 2);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException("索引不在数组长度内");
        }
        return data[index];
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public void set(int index, E e) {
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException("索引不在数组长度内");
        }
        data[index] = e;
    }

    public boolean contain(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException("索引不在数组长度内");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        if (size < getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    public void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];

        }
        data = newData;
    }

    @Override
    public String toString() {
        return "MyArray{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        MyDynamicArray myArray = new MyDynamicArray<String>(10);
        myArray.add(0, "2");
        System.out.println(myArray.remove(0));
        myArray.add(0, "2");
        System.out.println(myArray.remove(0));
        myArray.add(0, "2");
        myArray.add(0, "2");
        myArray.add(0, "2");
        myArray.add(0, "2");
        myArray.add(0, "2");
        System.out.println(myArray.remove(0));
        System.out.println(myArray.remove(0));

        System.out.println(myArray.remove(0));
        System.out.println(myArray.remove(0));
        System.out.println(myArray.remove(0));

        System.out.println(myArray);
        System.out.println(myArray.find("322"));
    }
}
