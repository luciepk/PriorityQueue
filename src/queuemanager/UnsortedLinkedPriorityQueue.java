/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuemanager;

/**
 **@param <T> The type of things being stored.
 * @author LUCIE
 */
public class UnsortedLinkedPriorityQueue<T> implements PriorityQueue<T> {

    // create the first node that lead to all the list
    private ListNode<T> head;

    //constructor
    public UnsortedLinkedPriorityQueue() {
    }

    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } 
        else {

            ListNode<T> current = head;// stock the currnt node
            int p = current.getItem().getPriority();// to stock the highest priority
            ListNode<T> Big = head; //to stock the highest priority node
            //we move the node we created inside the list
            while (current != null) {

                if (p < current.getItem().getPriority()) {
                    p = current.getItem().getPriority();
                    Big = current;
                }
                current = current.getNext();
            }

            return Big.getItem().getItem();// return the item of the node
        }
    }

    @Override
    public void add(T item, int priority) {
        PriorityItem<T> newItem = new PriorityItem<>(item, priority);
        head = new ListNode<>(newItem, head);

    }

    @Override
    public void remove() throws QueueUnderflowException {
        if (head == null) {
            throw new QueueUnderflowException();
        } 
        else {
            T highest = head();// is the highest priority node
            ListNode<T> current = head;//is the first node of the list
            ListNode<T> previous = null;// stock the previous node
            
            while (current != null && current.getItem().getItem() != head) {
            previous = current;
            current = current.getNext();
            }

        // delete the head node by taking the value of the next to the current node
        previous.setNext(current.getNext());

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
