package Heap;
import java.util.ArrayList;

/** Interface for the max-heap priority queue ADT. */
public interface PriorityQueue<K,V> {
    int size( );
    boolean isEmpty( );
    Entry<K,V> insert(K key, V value) throws IllegalArgumentException;
    Entry<K,V> max( );
    Entry<K,V> removeMmax( );
    Entry<K,V> increaseKey(K key, V value);
    ArrayList<Entry<K,V>> heapSort();
}
