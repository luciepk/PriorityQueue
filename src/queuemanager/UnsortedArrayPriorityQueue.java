
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuemanager;

/**
 * @param <T> The type of things being stored.
 * @author LUCIE
 */
public class UnsortedArrayPriorityQueue<T> implements PriorityQueue<T> {
  /**
     * Where the data is actually stored.
     */
    private final Object[] storage;

    /**
     * The size of the storage array.
     */
    private final int capacity;

    /**
     * The index of the last item stored.
     *
     * This is equal to the item count minus one.
     */
    private int tailIndex;

    /**
     * Create a new empty queue of the given size.
     *
     * @param size
     */
    public UnsortedArrayPriorityQueue(int size) {
        storage = new Object[size];
        capacity = size;
        tailIndex = -1;
    }

    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } 
        else {
         int i;
         int p = 0; //to stock the highest priority position
         int priority = 0;// use to compare the priority of each item
            for (i=0 ; i <= tailIndex; i++) {
                if (priority <((PriorityItem<T>) storage[i]).getPriority()) {
                    priority = ((PriorityItem<T>) storage[i]).getPriority();
                    p = i;
                }
        }   
         return ((PriorityItem<T>) storage[p]).getItem();
        }
    }

    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        tailIndex = tailIndex + 1;
        if (tailIndex >= capacity) {
            /* No resizing implemented, but that would be a good enhancement. */
            tailIndex = tailIndex - 1;
            throw new QueueOverflowException();
        } else {
            /* insertion of the end at the beginning of the tab */
            storage[tailIndex] = new PriorityItem<>(item, priority);
        }
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } 
        else {
            int i;
            int p = 0; //to stock the highest priority position
            int priority = 0;// use to compare the priority of each item
            for (i=0 ; i <= tailIndex; i++) {
                if (priority <((PriorityItem<T>) storage[i]).getPriority()) {
                    priority = ((PriorityItem<T>) storage[i]).getPriority();
                    p = i;
                    } 
                }
            for (i = p; i < tailIndex; i++) {
               storage[i] = storage[i + 1];
               }
            tailIndex = tailIndex - 1;
           }
    }

    @Override
    public boolean isEmpty() {
        return tailIndex < 0;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i <= tailIndex; i++) {
            if (i > 0) {
                result = result + ", ";
            }
            result = result + storage[i];
        }
        result = result + "]";
        return result;
    }   
}
