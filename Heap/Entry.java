package Heap;
/** Interface for a key-value pair. */
public interface Entry<K,V> {
    K getKey( ); // returns the key stored in this entry
    V getValue( ); // returns the value stored in this entry
    void setKey(K key);
    void setValue(V value);
}
