package sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> extends AbstractSequence<T> {

    private Node front = null;
    private Node rear = null;
    //private Node curr = this.front;
    private int count = 0;
    
    @Override
    /**
     * Get the Iterator of the queue
     * @return the iterator
     */
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new MyIterator();
    }

    @Override
    /**
     * Check if the queue is empty
     * @return a boolean value
     */
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        if(count == 0) return true;
        return false;
    }

    @Override
    /**
     * Remove all the elements from the queue
     */
    public void clear() {
        // TODO Auto-generated method stub
        this.front = null;
        this.rear = null;
        //this.curr = null;
        this.count = 0;
    }

    @Override
    /**
     * Get the size of the queue
     * @return the size
     */
    public int size() {
        // TODO Auto-generated method stub
        return this.count;
    }

    @Override
    /**
     * Add one element to the rear of the queue
     * @param the element
     */
    public void add(T value) {
        // TODO Auto-generated method stub
        Node newNode = new Node();
        newNode.data = value;
        newNode.next = null;
        if(this.count != 0)
            this.rear.next = newNode;
        this.rear = newNode;
        if(this.count == 0)
            this.front = newNode;
        this.count++;
    }

    @Override
    /**
     * Remove the element from the front of the queue
     * @return the element
     */
    public T remove() throws NoSuchElementException {
        // TODO Auto-generated method stub
        if(this.count == 0) throw new NoSuchElementException();
        T tmp = this.front.data;
        this.front = this.front.next;
        if(this.count == 1) this.rear = null;
        this.count--;
        return tmp;
    }

    @Override
    /**
     * Peek the element at the front of the queue
     * @return the element
     */
    public T peek() throws NoSuchElementException {
        // TODO Auto-generated method stub
        if(this.count == 0) return null;
        return this.front.data;
    }

    /**
     * Inner date structure to implement the linked list
     * @author machao
     *
     */
    private class Node {
        public T data = null;
        public Node next = null;
    }

    /**
     * Inner iterator
     * @author machao
     *
     * @param <T> the Object you want to put into the queue
     */
    private class MyIterator<T> implements Iterator {

        private Node curr;
        
        public MyIterator() {
            curr = front;
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
                curr = curr.next;
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
