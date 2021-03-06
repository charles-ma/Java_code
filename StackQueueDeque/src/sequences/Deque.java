package sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> extends AbstractSequence<T> {

    private int count = 0;
    private Node front = null;
    private Node rear = null;
    //private Node curr = null;
    
    @Override
    /**
     * Get the iterator of the deque
     * @return the iterator
     */
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new MyIterator(true);
    }

    @Override
    /**
     * Check if the deque is empty
     * @return a boolean value
     */
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        if(this.count == 0) return true;
        return false;
    }

    @Override
    /**
     * Remove all the elements from the deque
     */
    public void clear() {
        // TODO Auto-generated method stub
        this.count = 0;
        this.front = null;
        this.rear = null;
        //this.curr = null;
    }

    @Override
    /**
     * Get the size of the deque
     * @return the size
     */
    public int size() {
        // TODO Auto-generated method stub
        return this.count;
    }

    @Override
    /**
     * Add an element to the rear of the deque
     * @param value the element
     */
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
    /**
     * Remove the element from the front of the deque
     * @return the element
     */
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
    /**
     * Peek the element at the front of the deque
     * @return the element
     */
    public T peek() throws NoSuchElementException {
        // TODO Auto-generated method stub
        if(this.count == 0) throw new NoSuchElementException();
        return this.front.data;
    }

    /**
     * Add one element to the front of the deque
     * @param value the element
     */
    public void addFront(T value) {
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
    
    /**
     * Peek the element at the rear of the deque
     * @return the element
     * @throws NoSuchElementException
     */
    public T peekRear() throws NoSuchElementException {
        if(this.count == 0) throw new NoSuchElementException();
        return this.rear.data;
    }

    /**
     * Remove the element at the rear of the deque
     * @return the element
     * @throws NoSuchElementException
     */
    public T removeRear() throws NoSuchElementException{
        if(this.count == 0) throw new NoSuchElementException();
        T tmp = this.rear.data;
        this.rear = this.rear.previous;
        if(this.rear != null) this.rear.next = null;
        if(this.count == 1) this.front = null;
        this.count--;
        return tmp;
    }
    
    /**
     * Get the reverse iterator
     * @return the reverse iterator
     */
    public Iterator<T> reverseIterator(){
        return new MyIterator(false);
    }
    
    /**
     * The inner data structure to implement the linked list
     * @author machao
     *
     */
    private class Node {
        public T data;
        public Node previous;
        public Node next;
    }
    
    /**
     * The inner iterator
     * @author machao
     *
     * @param <T> the Object you want to put into the deque
     */
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
