/**
 * 
 */
package hashmap;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import tree.Tree;

/**
 * This class implements hashmap using bucket hashing and using trees as elements in the bucket
 * @author machao
 * @version 1.0
 */
public class TreeHashMap<K extends Comparable, V> implements Map<K, V> {

	private SortedBinaryTree<Pair<K, V>>[] buckets = null; 
	
	/**
	 * Constructor of the TreeHashMap
	 * @param size the number of the hashing buckets
	 */
	public TreeHashMap(int size) {
		this.buckets = new SortedBinaryTree[size];
		for(int i = 0; i < size; i++) {
			SortedBinaryTree<Pair<K, V>> bucket = new SortedBinaryTree<Pair<K, V>>(null);
			this.buckets[i] = bucket;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		int size = 0;
		for(SortedBinaryTree bucket : this.buckets) {
			if(bucket.size() != 0) size++;
		}
		return size;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		K formalKey = (K) key;
		int hashCode = Math.abs(formalKey.hashCode()) % (this.buckets.length);
		SortedBinaryTree<Pair<K, V>> bucket = this.buckets[hashCode];
		Iterator i = bucket.iterator();
		while(i.hasNext()) {
			Pair<K, V> pair = (Pair<K, V>) i.next();
			if(pair.getKey().equals(formalKey)) return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		V formalVal = (V) value;
		for(SortedBinaryTree<Pair<K, V>> bucket : this.buckets) {
			Iterator i = bucket.iterator();
			while(i.hasNext()) {
				Pair<K, V> pair = (Pair<K, V>) i.next();
				if(pair.getValue().equals(formalVal)) return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public V get(Object key) {
		if(!containsKey(key)) return null;
		K formalKey = (K) key;
		int hashCode = Math.abs(key.hashCode()) % this.buckets.length;
		SortedBinaryTree<Pair<K, V>> bucket = this.buckets[hashCode];
		Iterator i = bucket.iterator();
		while(i.hasNext()) {
			Pair<K, V> pair = (Pair<K, V>) i.next();
			if(pair.getKey().equals(formalKey)) return pair.getValue();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V put(K key, V value) {
		if(key == null) return null;
		int hashCode = Math.abs(key.hashCode()) % this.buckets.length;
		SortedBinaryTree<Pair<K, V>> bucket = this.buckets[hashCode];
		if(containsKey(key)) {
			Iterator i = bucket.iterator();
			while(i.hasNext()) {
				Pair<K, V> pair = (Pair<K, V>) i.next();
				if(pair.getKey().equals(key)) {
					pair.setValue(value);
					break;
				}
			}
		} else {
			bucket.add(new Pair<K, V>(key, value));
		}
		return value;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public V remove(Object key) {
		/*if(!containsKey(key)) return null;
		int hashCode = key.hashCode() % this.buckets.length;
		LinkedList<Pair<K, V>> bucket = this.buckets[hashCode];
		for(int i = 0; i < bucket.size(); i++) {
			if(bucket.get(i).getKey().equals(key)) {
				V returnV = bucket.get(i).getValue();
				bucket.remove(i);
				return returnV;
			}
		}*/
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
		for(SortedBinaryTree<Pair<K, V>> bucket : this.buckets) bucket.clear(); 
	}

	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<K> keySet() {
		Set<Object> keySet = new HashSet<Object>();
		for(SortedBinaryTree<Pair<K, V>> bucket : this.buckets) {
			Iterator i = bucket.iterator();
			while(i.hasNext()) {
				Pair<K, V> pair = (Pair<K, V>) i.next();
				keySet.add(pair.getKey());
			}
		} 
		return (Set<K>)keySet;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<V> values() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null) return false;
		if(!(other instanceof ListHashMap<?, ?>)) return false;
		ListHashMap<K, V> otherMap = (ListHashMap<K, V>) other;
		if(!keySet().equals(otherMap.keySet())) return false;
		for(K key : keySet()) {
			if(!otherMap.get(key).equals(get(key))) return false;
		}
		return true;
	}

	/**
	 * Inner class to store the key-value pairs
	 * @author machao
	 *
	 * @param <M> The type of the key
	 * @param <N> The type of the value
	 */
	private class Pair<M extends Comparable, N> implements Comparable{
		private M k;
		private N v;
		
		/**
		 * Constructor for this class
		 * @param k The key
		 * @param v The value
		 */
		public Pair(M k, N v) {
			this.k = k;
			this.v = v;
		}
		
		/**
		 * Gets the key
		 * @return The key
		 */
		public M getKey() {
			return this.k;
		}
		
		/**
		 * Gets the value
		 * @return The value
		 */
		public N getValue() {
			return this.v;
		}
		
		/**
		 * Sets the key
		 * @param k The wanted key
		 */
		public void setKey(M k) {
			this.k = k;
		}
		
		/**
		 * Sets the value
		 * @param v The wanted value
		 */
		public void setValue(N v) {
			this.v = v;
		}

		@Override
		public int compareTo(Object o) {
			Pair<K, V> other = (Pair<K, V>) o;
			return this.k.compareTo(other.k);
		}
		
	}
	
	public class SortedBinaryTree<T extends Comparable> extends Tree<T>{
		
		private int size = 0;
		
		/**
		 * Constructor of the SortedBinaryTree
		 * @param value the value stored in the root node
		 * @param children the children of the root node
		 * @throws IllegalArgumentException
		 */
		public SortedBinaryTree(T value, SortedBinaryTree<T>... children) throws IllegalArgumentException{
			super(value, children);
			if(value == null) this.size = 0;
			else {
				for(int i = 0; i < children.length; i++) {
					this.size += children[i].size;
				}
			}
		}
		
		/**
		 * Gets the size of the tree
		 * @return the size
		 */
		public int size() {
			return this.size;
		}
		
		/**
		 * Adds new value to the tree
		 * @param value the value to be added
		 */
		public void add(T value) {
			if(this.value == null) this.value = value;
			else if(this.value.compareTo(value) == -1) {
				if(this.getChild(1) == null) this.addChild(1, new SortedBinaryTree(value));
				else {
					SortedBinaryTree rightChi = (SortedBinaryTree) this.getChild(1);
					rightChi.add(value);
				}
			} else if(this.value.compareTo(value) >= 0) {
				if(this.getChild(0) == null) this.addChild(0, new SortedBinaryTree(value));
				else {
					SortedBinaryTree leftChi = (SortedBinaryTree) this.getChild(0);
					leftChi.add(value);
				}
			}
			this.size++;
		}
		
		/**
		 * Removes all the elements from the tree
		 */
		public void clear() {
			this.children.clear();
			this.value = null;
			this.size = 0;
		}
	}
}
