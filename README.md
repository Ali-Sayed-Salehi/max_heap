# max_heap
Implementation of max-heap data structure in Java

## How it is done
In a min-heap, the element of the heap with the smallest key is the root of a binary tree. A max-heap always has as root the element with the biggest key and the relationship between the keys of a node and its parent is less than or equal (≤). For a max-heap we will have the operation removeMax( ) (instead of removeMin( )).

In this program, we develop our own binary tree ADT and our own max-heap ADT. The max-heap ADT is implemented using our binary tree ADT. The max-heap ADT supports two additional operations:
* **increaseKey(v,k)** : assigns to vertex v in the max-heap the new key k which cannot be smaller than the old key value. It is worth noting that after this operation the max-heap order will be restored (if needed).
* **heapSort(heap1)** : sorts the heap in non-decreasing order and stores the sorted elements in a one-dimension array. The array can later be used by the calling code to print the sorted values.

For novelty, binary tree is implemented with a dynamic, linked data structure (aas apposed to an array).
