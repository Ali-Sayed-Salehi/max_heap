package Heap;
import java.util.ArrayList;
import java.util.Comparator;

import Foundations.*;

/** An implementation of a priority queue using a linked-structure-based heap. */
public class MaxHeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {

    /** primary collection of priority queue entries */
    protected LinkedBinaryTree<Entry<K,V>> heap = new LinkedBinaryTree<>( );

    /** Creates an empty priority queue based on the natural ordering of its keys. */
    public MaxHeapPriorityQueue( ) { super( ); }

    /** Creates an empty priority queue using the given comparator to order keys. */
    public MaxHeapPriorityQueue(Comparator<K> comp) { super(comp); }

    /** Returns the number of items in the priority queue. */
    @Override
    public int size() {
        return heap.size();
    }

    protected Position<Entry<K, V>> lastNode() {
        ArrayList<Position<Entry<K, V>>> positionsList = new ArrayList<>();
        for(Position<Entry<K, V>> p : heap.positions()){
            positionsList.add(p);
        }

        Position<Entry<K, V>> lastNodePosition = 
            positionsList.get(positionsList.size() - 1);

        return lastNodePosition;
    }

    protected boolean hasLeft(Position<Entry<K, V>> a) { 
        return  (heap.left(a) != null); 
    }
    protected boolean hasRight(Position<Entry<K, V>> a) { 
        return  (heap.right(a) != null); 
    }


    /** Exchanges the entries at indices i and j of the array list. */
    protected void swap(Position<Entry<K, V>> a, Position<Entry<K, V>> b) {
        Entry<K, V> temp = a.getElement();
        heap.set(a, b.getElement());
        heap.set(b, temp);
    }


    /** Moves the entry at index a higher, if necessary,
     *  to restore the heap property. */
    protected void upheap(Position<Entry<K, V>> a) {

        while (!heap.isRoot(a)) { // continue until reaching root (or break statement)
            Position<Entry<K, V>> p = heap.parent(a);
            Entry<K, V> parentEntry = p.getElement();
            Entry<K, V> Entry = a.getElement();

            if (compare(Entry, parentEntry) <= 0) break; // heap property verified
            swap(a, p);
            a = p; // continue from the parent's location
        }
    }



    /** Moves the entry at index j lower, if necessary,
     *  to restore the heap property. */
    protected void downheap(Position<Entry<K, V>> a){

        while (hasLeft(a)) { // continue to bottom (or break statement)

            Position<Entry<K, V>> leftPosition = heap.left(a);
            Position<Entry<K, V>> bigChildPosition = leftPosition; // although right may be smaller
            
            if (hasRight(a)) {
                Position<Entry<K, V>> rightPosition = heap.right(a);

                if (compare(heap.left(a).getElement(), 
                heap.right(a).getElement()) < 0)

                bigChildPosition = rightPosition; // right child is smaller
            }

            if (compare(bigChildPosition.getElement(), a.getElement()) <= 0)
                break; // heap property has been restored

            swap(a, bigChildPosition);
            a = bigChildPosition; // continue at position of the child
        }
    }


    /** Inserts a key-value pair and returns the entry created. */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key); // auxiliary key-checking method (could throw exception)

        Entry<K,V> newest = new PQEntry<>(key, value);

        for(Position<Entry<K, V>> p : heap.positions()){
            if (heap.left(p) != null){
                heap.addLeft(p, newest);
                break;
            } else if (heap.right(p) != null){
                heap.addRight(p, newest);
                break;
            }
        }
        upheap(lastNode());
        return newest;
    }

    /** Returns (but does not remove) an entry with maximum key (if any). */
    @Override
    public Entry<K, V> max() {
        if (heap.isEmpty( )) return null;
        Position<Entry<K,V>> rootPosition = heap.root();
        return rootPosition.getElement();
    }

    @Override
    public Entry<K, V> removeMmax() {
        if (heap.isEmpty( )) return null;
        Entry<K,V> answer = heap.root().getElement();

        swap(heap.root(), lastNode());
        heap.remove(lastNode());
        downheap(heap.root());

        return answer;
    }

    /**assigns to vertex v in the max-heap the
     *  new key value k which cannot be smaller than the old key value. */
    @Override
    public Entry<K, V> increaseKey(K key, V value) {
        for(Position<Entry<K, V>> p : heap.positions()){
            Entry<K, V> e = p.getElement();
            V entryValue = e.getValue();
            Entry<K,V> newEntry = new PQEntry<K,V>(key, value);

                if (entryValue == value && 
                    compare(e, newEntry) > 0){
                    e.setKey(key);
                    upheap(p);
                    return e;
                }
            }
        return null;
    }

    /**sorts the heap in non-decreasing order and
     *  stores the sorted elements in a one-dimension array. */
    @Override
    public ArrayList<Entry<K, V>> heapSort() {

        ArrayList<Entry<K, V>> positionsList = new ArrayList<>();
        ArrayList<Entry<K, V>> reversedList = new ArrayList<>();
        for(Position<Entry<K, V>> p : heap.positions()){
            positionsList.add(p.getElement());
        }

        while(!positionsList.isEmpty()){
            reversedList.add(positionsList.get(positionsList.size() - 1));
        }
        return reversedList;
    }


    public void printHeap(){
        ArrayList<Entry<K, V>> positionsList = new ArrayList<>();
        for(Position<Entry<K, V>> p : heap.positions()){
            positionsList.add(p.getElement());
        K key = p.getElement().getKey();
        V value = p.getElement().getValue();
        System.out.println("key: " + key + "value: " + value + "\n");
        }
    
    }

    public String heapToString(){
        ArrayList<Entry<K, V>> positionsList = new ArrayList<>();
        for(Position<Entry<K, V>> p : heap.positions()){
            positionsList.add(p.getElement());
        }
        return positionsList.toString();
    }
}
