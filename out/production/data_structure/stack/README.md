
# 1.栈（Stack）
栈是一种后进先出（Last In First Out）的数据结构，只能从同一端放入数据和取出数据
具体应用有：
 1. 文档编辑器的Undo（撤销）操作：每次对文档进行操作，就将操作压栈，撤销时就将栈顶操作弹出，就完成了撤销操作
2. 代码运行时的方法调用：当一个方法的执行需要调用另一个方法时，调用前会将本次方法以及执行的位置入栈，执行被调用的方法结束后，在将栈顶的方法弹出，继续执行
首先定义了一个栈的接口，声明了栈所需要的操作

```
public interface Stack<E> {
    int getSize();//获取当前栈的大小
    boolean isEmpty();//判断栈是否为空
    void push(E e);//元素入栈操作
    E pop();//元素出栈操作
    E peek();//取出栈顶元素

}

```


## 1.1数组栈（ArrayStack）
数组栈的底层是通过昨天学习的动态数组实现的，实现比较容易，在新建栈对象的时候新建一个数组对象，并作为栈的成员变量，之后对栈进行的操作都是对这个数组进行的操作，本质是调用一下这个数组已定义的一些方法（入栈addLast()、出栈removeLast()、取顶getLast()）。判断是栈的大小和是否为空栈就可以调用数组中的getSize()和isEmpty()方法。
在这些方法中，由于都是对栈顶，即数组的尾部进行操作，所以均摊复杂度为O（1）

```
public class ArrayStack<E> implements Stack<E> {
    private Array<E> array;
    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }
    public ArrayStack(){
        array = new Array<>();
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
    public void push(E e){
        array.addLast(e);
    }

    @Override
    public E pop(){
        return array.removeLast();
    }

    @Override
    public E peek(){
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack:");
        res.append("[");
        for(int i = 0 ; i < array.getSize() ; i++){
            res.append(array.get(i));
            if(i != array.getSize() - 1 ){
                res.append(",");
            }
        }
        res.append("]top");
        return res.toString();
    }
}

```

## 1.2链表栈（LinkedListStack）
链表栈的底层是通过一个链表实现，由于链表本身的结构，对链表头进行操作的复杂度都为O（1），刚好栈只需要对一端进行增减操作，所以链表非常适合来作为栈的底层结构，通过链表中的头操作（addFirst(),removeFirst(),getFirst()）即可轻松实现栈的结构

```
public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> list;

    public LinkedListStack(){
        list = new LinkedList<>();
    }

    @Override
    public int getSize(){
        return list.getSize();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public void push(E e){
        list.addFirst(e);
    }

    @Override
    public E pop(){
        return list.removeFirst();
    }

    @Override
    public E peek(){
        return list.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack : top ");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args) {

        LinkedListStack<Integer> stack = new LinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }


}

```