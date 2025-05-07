import java.util.LinkedList;

public class Stack<T> {
    private LinkedList<T> list = new LinkedList<>();

    public void push(T item) {
        list.addLast(item);
    }

    public T pop() {
        return list.pollLast();
    }

    public T peek() {
        return list.peekLast();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
