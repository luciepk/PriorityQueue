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
public class SortedLinkedPriorityQueue<T> implements PriorityQueue<T> {

    private ListNode<T> head;

    //constructor
    public SortedLinkedPriorityQueue() {
    }

    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            return head.getItem().getItem();//return the item witch is in the node head
        }
    }

    @Override
    public void add(T item, int priority) {
        //creation of a new item we want
        PriorityItem<T> newItem = new PriorityItem<>(item, priority);

        // if queue is empty or new item has larger priority than the head
        
        if (isEmpty() || head.getItem().getPriority() < priority) {
            head = new ListNode<>(newItem, head);
            return;
        }
        
        ListNode<T> current = head;

        // while current node is not the last item in the queue
        // and the next node has larger priority than the node that is being added
        while (current.getNext() != null
                && priority < current.getNext().getItem().getPriority()) {
            current = current.getNext();
        }

        // create a new node and add it to the current node next position
        ListNode<T> newNode = new ListNode<>(newItem, current.getNext());
        current.setNext(newNode);
    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (head == null) {
            throw new QueueUnderflowException();
        } else {
            //the head take the value of the segond node so the first one is not in the list
            head = head.getNext();
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        String result = "[";
        ListNode<T> temp = head;
        while (temp != null) {
            result = result + temp.getItem() + ",";
            temp = temp.getNext();
        }
        result = result + "]";
        return result;
    }
}