package array;




import java.util.Arrays;

public class MyArray<E> {
    private E[] data;
    int size;

    public MyArray(int initialCapacity) {
        data = (E[]) new Object[initialCapacity];
        size = 0;
    }

    public MyArray() {
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
            throw new IllegalArgumentException("数组已满");
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
        size -- ;
        return ret;
    }


    @Override
    public String toString() {
        return "MyArray{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        MyArray myArray = new MyArray<String>(10);
        myArray.add(0,"2");
        myArray.add(0,"22");
        myArray.add(0,"222");
        System.out.println(myArray.remove(0));
        System.out.println(myArray);
    }
}
