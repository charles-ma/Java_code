package sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> extends AbstractSequence<T> {

    private int count = 0;
    private Node front = null;
    private Node rear = null;
    //private Node curr = null;
    
    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new MyIterator(true);
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        if(this.count == 0) return true;
        return false;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        this.count = 0;
        this.front = null;
        this.rear = null;
        //this.curr = null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return this.count;
    }

    @Override
    public void add(T value) {
        // TODO Auto-generated method stub
        Node newNode = new Node();
        newNode.data = value;
        if(this.count == 0) {
            this.rear = newNode;
            this.front = newNode;
        }else {
            this.rear.next = newNode;
            newNode.previous = this.rear;
            this.rear = newNode;
        }
        this.count++;
    }

    @Override
    public T remove() throws NoSuchElementException {
        // TODO Auto-generated method stub
        if(this.count == 0) throw new NoSuchElementException();
        T tmp = this.front.data;
        this.front = this.front.next;
        if(this.front != null) this.front.previous = null;
        if(this.count == 1) this.rear = null;
        this.count--;
        return tmp;
    }

    @Override
    public T peek() throws NoSuchElementException {
        // TODO Auto-generated method stub
        if(this.count == 0) throw new NoSuchElementException();
        return this.front.data;
    }

    void addFront(T value) {
        Node newNode = new Node();
        newNode.data = value;
        if(this.count == 0) {
            this.front = newNode;
            this.rear = newNode;
        }else {
            newNode.next = this.front;
            this.front.previous = newNode;
            this.front = newNode;
        }
        this.count++;
    }
    
    T peekRear() throws NoSuchElementException {
        if(this.count == 0) throw new NoSuchElementException();
        return this.rear.data;
    }
    
    T removeRear() throws NoSuchElementException{
        if(this.count == 0) throw new NoSuchElementException();
        T tmp = this.rear.data;
        this.rear = this.rear.previous;
        if(this.rear != null) this.rear.next = null;
        if(this.count == 1) this.front = null;
        this.count--;
        return tmp;
    }
    
    Iterator<T> reverseIterator(){
        return new MyIterator(false);
    }
    
    private class Node {
        public T data;
        public Node previous;
        public Node next;
    }
    
    private class MyIterator<T> implements Iterator {

        private boolean flag;
        private Node curr;
        
        public MyIterator(boolean flag) {
            if(flag) curr = front;
            else curr = rear;
            this.flag = flag;
        }
        
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            if(curr != null) return true;
            return false;
        }

        @Override
        public Object next() {
            // TODO Auto-generated method stub
            if(curr != null) {
                Object tmp = curr.data;
                if(this.flag) curr = curr.next;
                else curr = curr.previous;
                return tmp;
            }
            return null;
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            
        }
        
    }
}
