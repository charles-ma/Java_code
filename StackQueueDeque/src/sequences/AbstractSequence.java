package sequences;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Specifies methods that the Stack, Queue, and Deque classes must implement.
 * @author David Matuszek
 * @param <T> The type of the elements that can be held in this sequence.
 */
public abstract class AbstractSequence<T> implements Iterable<T> {
    
    /**
     * Tests whether this sequence is empty.
     * @return <code>true</code> if this sequence is empty.
     */
    
    public abstract boolean isEmpty();
    /**
     * Removes all elements from this sequence.
     */
    public abstract void clear();
    
    /**
     * Counts the number of elements in this sequence.
     * @return The number of elements contained.
     */
    public abstract int size();
    
    /**
     * Adds the given value as the new first value in the sequence.
     * @param value The value to be added.
     */
    public abstract void add(T value);
    
    /**
     * Removes and returns the first element of this sequence.
     * @return The value in the first element.
     * @throws NoSuchElementException If the sequence is empty.
     */
    public abstract T remove() throws NoSuchElementException;
    
    /**
     * Returns the first element of this sequence.
     * @return The value in the first element.
     * @throws NoSuchElementException If the sequence is empty.
     */
    public abstract T peek() throws NoSuchElementException;

    /**
     * Returns a string representation of this sequence, of the form
     * [value1 value2 ... valueN].
     * 
     * @return A string representation of this sequence.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        Iterator<T> iter = iterator();
        String s = "[";
        if (iter.hasNext()) {
            s += iter.next();
        }
        while (iter.hasNext()) {
            s += " " + iter.next();
        }
        return s + "]";
    }
}
