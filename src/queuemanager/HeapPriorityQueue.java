/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuemanager;

/**
 *
 * @author LUCIE
 */
public class HeapPriorityQueue<T> implements PriorityQueue<T> {

    /**
     * Where the data is actually stored.
     */
    private final Object[] storage;

    /**
     * The size of the storage array.
     */
    private final int capacity;

    /**
     * The index of the last item stored. This is equal to the item count minus
     * one.
     */
    private int tailIndex;

    /**
     * Create a new empty queue of the given size.
     *
     * @param size Size of the queue.
     */
    public HeapPriorityQueue(int size) {
        storage = new Object[size];
        storage[0] = 0;
        capacity = size;
        tailIndex = 0;
    }

    //Returns the head of the queue, which is the second item in the array so in the place 1
    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        return ((PriorityItem<T>) storage[1]).getItem();
    }

    @Override
    public void add(T item, int priority) throws QueueOverflowException {
        if (tailIndex >= capacity - 1) {
            throw new QueueOverflowException();
        }

        tailIndex++; // keep the size update
        storage[tailIndex] = new PriorityItem<>(item, priority);
        upRightPosition(tailIndex);// use the right possition function
    }

    // Method that place new  node in the correct place 
    private void upRightPosition(int tailIndex) {
        // return when there is only one item in the queue
        if (tailIndex == 1) {
            return;
        }

        int parentIndex = tailIndex / 2; // get you into the middle 
        PriorityItem<T> parent = (PriorityItem<T>) storage[parentIndex];
        PriorityItem<T> node = (PriorityItem<T>) storage[tailIndex];

        // return when parent node is already larger than the node
        if (parent.getPriority() > node.getPriority()) {
            return;
        }

        // swap child and parent
        Object temporaryNode = storage[parentIndex];// to help you swap to value
        storage[parentIndex] = storage[tailIndex];
        storage[tailIndex] = temporaryNode;

        upRightPosition(parentIndex);
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        // replaces the head of the queue with the last item in the queue
        storage[1] = storage[tailIndex];

        // removes the last item from the queue
        storage[tailIndex] = null;
        tailIndex--;// keep the size update

        downRightPosition(tailIndex);

    }

    /**
     * Recursive method that places node from the root of the heap in the
     * correct place so it maintains the max heap property.
     *
     * @param nodeIndex Index of the node that is moved.
     */
    private void downRightPosition(int tailIndex) {
        int i = 1;

        PriorityItem<T> parent = (PriorityItem<T>) storage[tailIndex];
        PriorityItem<T> node = (PriorityItem<T>) storage[i];

        // swap child and parent
        Object temporaryNode;

        while (i != tailIndex) {
            temporaryNode = storage[tailIndex];// to help you swap to value
            storage[tailIndex] = storage[i];
            storage[i] = temporaryNode;
            i--;
            temporaryNode = storage[tailIndex];// to help you swap to value
            storage[tailIndex] = storage[i];
            storage[i] = temporaryNode;
            tailIndex--;

        }
                

    }

    @Override
    public boolean isEmpty() {
        return tailIndex == 0;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 1; i <= tailIndex; i++) {
            if (i > 1) {
                result = result + ", ";
            }
            result = result + storage[i];
        }
        result = result + "]";
        return result;
    }
}
