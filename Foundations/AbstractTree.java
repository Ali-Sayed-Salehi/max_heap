package Foundations;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** An abstract base class providing some
 *  functionality of the Tree interface. */
public abstract class AbstractTree<E> implements Tree<E> {
    public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }
    public boolean isExternal(Position<E> p) { return numChildren(p) == 0; }
    public boolean isRoot(Position<E> p) { return p == root( ); }
    public boolean isEmpty( ) { return size( ) == 0; }

    /** Returns an iterable collection of positions
     *  of the tree in breadth-first order. */
    public Iterable<Position<E>> breadthfirst( ) {
        List<Position<E>> snapshot = new ArrayList<>( );
        if (!isEmpty( )) {
            NewQueue<Position<E>> fringe = new LinkedQueue<>( );
            fringe.enqueue(root( )); // start with the root
        while (!fringe.isEmpty( )) {
            Position<E> p = fringe.dequeue( ); // remove from front of the queue
            snapshot.add(p); // report this position
            for (Position<E> c : children(p))
                fringe.enqueue(c); // add children to back of queue
        }
    }
        return snapshot;
    }
    
    public Iterable<Position<E>> positions( ) { return breadthfirst( ); }

    //---------------- nested ElementIterator class ----------------
    /* This class adapts the iteration produced by positions() to return elements. */
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions( ).iterator( );
        public boolean hasNext( ) { return posIterator.hasNext( ); }
        public E next( ) { return posIterator.next( ).getElement( ); } // return element!
        public void remove( ) { posIterator.remove( ); }
    }
    /** Returns an iterator of the elements stored in the tree. */
    public Iterator<E> iterator( ) { return new ElementIterator( ); }
}
