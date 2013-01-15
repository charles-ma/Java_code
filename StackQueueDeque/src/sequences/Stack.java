package sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> extends AbstractSequence<T> {

    private Node first = null;
    //private Node curr = this.first;
    private int count = 0;

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new MyIterator();
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
	this.first = null;
	//this.curr = null;
	this.count = 0;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return this.count;
    }

    @Override
    public void add(T value) {
        // TODO Auto-generated method stub
	if(value == null) ;
	else {
	    Node newNode = new Node();
	    newNode.data = value;
	    newNode.next = this.first;
	    this.first = newNode;
	    this.count++;
	}
    }

    @Override
    public T remove() throws NoSuchElementException {
        // TODO Auto-generated method stub
        if(this.count == 0) throw new NoSuchElementException();
	Node result = this.first;
	this.first = this.first.next;
	this.count--;
	return result.data;
    }

    @Override
    public T peek() throws NoSuchElementException {
        // TODO Auto-generated method stub
        if(this.count == 0) throw new NoSuchElementException();
        return this.first.data;
    }

    private class Node {
        public T data = null;
        public Node next = null;
    }

    private class MyIterator<T> implements Iterator {

        private Node curr;
        
        public MyIterator() {
            curr = first;
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
