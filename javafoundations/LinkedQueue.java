package javafoundations;

import javafoundations.exceptions.*;
/**
 * Represents a linked implementation of a queue
 *
 * @author Esther Luo & Anushri 
 * @version 2019.10.30
 */
public class LinkedQueue<T> implements Queue<T>
{
   private int count;
   private LinearNode<T> front, rear;

   /**
   * A linked implementation of the Queue Interface.
   * */
   public LinkedQueue()
   {
      count = 0;
      front = rear = null;
   }

   /**
     * Adds the specified element to the rear of the queue.
     * 
     * @param The element to be enqueued into the queue
     */ 
   public void enqueue (T element)
   {
      LinearNode<T> node = new LinearNode<T>(element);

      if (count == 0)
         front = node;
      else
         rear.setNext(node);

      rear = node;
      count++;
   }

   /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return the element removed from the front of the queue.
     * @throws EmptyCollectionException if this queue is empty
     */
   public T dequeue () throws EmptyCollectionException { 
    if (count == 0)
            throw new EmptyCollectionException ("Dequeue failed. Empty q.");

        T temp = front.getElement();
        front = front.getNext();
        count--;
        return temp;
    }
    
   /**
     * Returns the element at the front of the queue
     * without removing it.
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if this queue is empty
     */
    public T first () throws EmptyCollectionException 
    {
        if (count == 0)
            throw new EmptyCollectionException ("First failed. Empty q.");
            
        return front.getElement();
    }

    /**
     * Returns true if the queue contains no elements,
     *  false otherwise.
     * 
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty() 
    {
        return count == 0;
    }

    /**
     * Returns the number of elements in the queue.
     * 
     * the number of elements in the queue
     */
    public int size() 
    {
        return count;
    }

    /**
     * Returns a string representation of the queue.
     * 
     * a string representation of the queue
     */
    public String toString() 
    {
        String result = "<front of queue>\n";
        LinearNode<T> current = front;
        while(current != null)
        {
            result += current.getElement() + "\n";
            current = current.getNext();
        }

        return result + "<rear of queue>";
    }
    
    public static void main(String[] args){
        //System.out.println("Testing LinkedQueue");
        
        // //Testing empty Queue
        // //System.out.println("***Test Case 0: Testing Empty Queue***");
        // LinkedQueue test = new LinkedQueue();
        // //System.out.println("\tDequeue Method => Throw Empty Collection");
        // //test.dequeue();
        // System.out.println("\tSize => 0");
        // System.out.println(test.size());
        // System.out.println("\tisEmpty Method => True");
        // System.out.println(test.isEmpty());
        // System.out.println("\tfirst Method => Throw Empty Collection");
        // System.out.println(test.first());
        
        //Testing a singleton Queue
        
        System.out.println("***Test Case 1: Testing a singleton Queue***");
        LinkedQueue test1 = new LinkedQueue();
        System.out.println("\tEnqueue Method => adds one element");
        test1.enqueue(1);
        System.out.println(test1);
        System.out.println("\tSize => 1");
        System.out.println(test1.size());
        System.out.println("\tisEmpty Method => False");
        System.out.println(test1.isEmpty());
        System.out.println("\tfirst Method => returns first element");
        System.out.println(test1.first());
        System.out.println("\tDequeue Method => removes element");
        test1.dequeue();
        System.out.println(test1);
        
        //Testing multiple elements in a Queue
        System.out.println("***Test Case 2: Testing multiple elements in a Queue***");
        LinkedQueue test2 = new LinkedQueue();
        System.out.println("\tEnqueue Method => adds multiple elements");
        test2.enqueue(1);
        test2.enqueue(2);
        test2.enqueue(3);
        System.out.println(test2);
        System.out.println("\tSize = 3");
        System.out.println(test2.size());
        System.out.println("\tisEmpty Method => False");
        System.out.println(test2.isEmpty());
        System.out.println("\tfirst Method => returns first element");
        System.out.println(test2.first());
        System.out.println("\tDequeue Method => removes first element");
        test2.dequeue();
        System.out.println(test2);
    }
}
