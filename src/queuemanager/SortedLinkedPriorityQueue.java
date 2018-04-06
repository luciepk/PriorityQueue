/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queuemanager;

/**
 *@param <T> The type of things being stored.
 * @author LUCIE
 */
public class SortedLinkedPriorityQueue<T> implements PriorityQueue<T> {
    
// create the node to use in the list    
    class Node<T>
    {
       T data;
       int priority;
       Node<T> next;

       public Node(T data,int priority, Node<T> next)
       {
          this.data = data;
          this.priority = priority;
          this.next = next;
       }
    }

    Node head;
    //constructor
    public SortedLinkedPriorityQueue() {
    }

    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            return ((PriorityItem<T>) head.data).getItem();//return the item witch is in the node head
        }
    }

    @Override
    public void add(T item, int priority){
       //creation of a new node we are going to add
       Node addnode = new Node<>(item,priority, head);
       //we put this node at the beginning
       Node current = head;
       //we move the node we created inside the list
       while ( current.next.priority < priority && current.next != null ) {
        current = current.next;  
        }
       //we add the new node in the middle
       addnode.next = current.next;
       current.next = addnode;
          
    }
 

    @Override
    public void remove() throws QueueUnderflowException {
        if (head == null) {
            throw new QueueUnderflowException();
        } 
        else {
            //the head take the value of the segond node so the first one is not in the list
            head= head.next;
        }
     }
 
    

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        String result ="[";
         Node temp = head;
         while (temp != null)
         {
             result = result + temp.next +",";
            //System.out.print(temp.data+" ");
            temp = temp.next;
         }
         result = result + "]";
         return result ;
    }   
}
