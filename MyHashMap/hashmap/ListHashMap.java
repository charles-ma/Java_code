/**
 * 
 */
package hashmap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * This class implements hashmap using bucket hashing and using linked lists as elements in the bucket
 * @author machao
 * @version 1.0
 */
public class ListHashMap<K, V> implements Map<K, V> {

	@Override
	public int size() {
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public V get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		
		return null;
	}

	/**
	 * Inner class to store the key-value pairs
	 * @author machao
	 *
	 * @param <M> The type of the key
	 * @param <N> The type of the value
	 */
	private class Pair<M, N> {
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
	}
}
