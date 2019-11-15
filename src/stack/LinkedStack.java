package stack;

import java.util.LinkedList;

public class LinkedStack<E> implements Stack<E> {
    private LinkedList<E> data;

    public LinkedStack() {
        data = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void push(E e) {
        data.addLast(e);
    }

    @Override
    public E pop() {
        return data.removeLast();
    }

    @Override
    public E peek() {
        return data.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack:");
        res.append("[");
        for(int i = 0 ; i < data.size() ; i++){
            res.append(data.get(i));
            if(i != data.size() - 1 ){
                res.append(",");
            }
        }
        res.append("]top");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        System.out.println(stack.isEmpty());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(3);
        stack.push(3);
        stack.push(3);
        stack.push(3);
        stack.push(3);
        stack.push(3);
        stack.push(3);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println(stack);
        stack.push(5);
        System.out.println(stack);

    }
}
