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
    public UnsortedLinkedPriorityQueue() {
    }

    @Override
    public T head() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        } 
        else {
            
            Node current = head;
            int p = current.priority;// to stock the highest priority
            Node Big = head ; //to stock the data of the hghest priority
            //we move the node we created inside the list
            while(  current.next != null) { 
                if (p < current.next.priority){
                    p = current.next.priority;
                    Big.data= current.next.data;
                }
                current = current.next;
             }

            return ((PriorityItem<T>) Big.data).getItem();
        }
    }

    @Override
    public void add(T item, int priority){
        Node current;
        current = new Node<>(item,priority, head);
        current.next = head;
        head= current ;
        
    }


    @Override
    public void remove() throws QueueUnderflowException {
        if (head == null) {
            throw new QueueUnderflowException();
        } 
        else {
            Node current = head;
            Node before = head; //use to stock the node before cuurent
            int p = current.priority;// to stock the highest priority
            Node Big = head ; //to stock the data of the hghest priority
            //we move the node we created inside the list
            while(  current.next != null) { 
                 if (p < current.next.priority){
                     p = current.next.priority;
                     Big.data= current.next.data;
                  }
                     current = current.next;
            }
             current = head ; // we come back at the beginning of the list    
             while(current.data != Big.data){
                 before = current;
                 current = current.next;
             }
            before = current.next ; 
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
