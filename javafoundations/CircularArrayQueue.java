//********************************************************************
//  CircularArrayQueue.java       Java Foundations
package javafoundations;

import javafoundations.exceptions.*;

/**
 * Represents an array implementation of a queue in which neither
 *  end of the queue is fixed in the array. The variables storing the
 *  indexes of the front and rear of the queue continually increment
 *  as elements are removed and added, cycling back to 0 when they
 *  reach the end of the array.
 * 
 * @author Esther Luo & Anushri 
 * @version 2019.10.30
 * */
public class CircularArrayQueue<T> implements Queue<T>
{
  private final int DEFAULT_CAPACITY = 3;
  private int front, rear, count;
  private T[] queue;
  
  /**
     * Constructor
     * Creates an empty queue.
     */
  public CircularArrayQueue()
  {
    front = rear = count = 0;
    queue = (T[]) (new Object[DEFAULT_CAPACITY]);
  }
  
  /**
     * Adds the specified element to the rear of the queue,
     * expanding the underlying array if needed.
     * 
     * @param The element to be enqueued into the queue
     */
  public void enqueue (T element)
  {
    if (count == queue.length)
      expandCapacity();
    
    queue[rear] = element;
    rear = (rear+1) % queue.length;
    count++;
  }
  
  /**
     *  Creates a new array, twice the capacity of the old one,
     *  to store the contents of this queue.
     **/
  public void expandCapacity()
  {
    T[] larger = (T[])(new Object[queue.length*2]);
    
    for (int index=0; index < count; index++)
      larger[index] = queue[(front+index) % queue.length];
    
    front = 0;
    rear = count;
    queue = larger;
  }
  
  /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return the element removed from the front of the queue.
     * @throws EmptyCollectionException if this queue is empty
     */
   public T dequeue () throws EmptyCollectionException { 
       if (count == 0)
            throw new EmptyCollectionException ("Dequeue operation failed. "
                + "The queue is empty.");
                
       T result = queue[front];
       
       queue[front] = null;
       
       front = (front + 1) % queue.length;
       
       count--;
     
       return result;
    }
    
  /**
     * Returns the element at the front of the queue
     * without removing it.
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if this queue is empty
    */    
   public T first () throws EmptyCollectionException { 
       if (count == 0)
            throw new EmptyCollectionException ("First failed. Empty q.");
            
        return queue[front];
    }
   
   /**
     * Returns the number of elements in the queue.
     * 
     * the number of elements in the queue
     */     
   public int size() { 
       return count;
    }
   
   /**
     * Returns true if the queue contains no elements,
     *  false otherwise.
     * 
     * @return true if the queue is empty, false otherwise.
   */
   public boolean isEmpty() { 
       return count == 0;
    }
    
    /**
     * Returns a string representation of the queue.
     * 
     * a string representation of the queue
     */   
   public String toString() { 
       String result = "<front of queue>\n";
       int currentRear = front + count;
       for(int index = front; index < currentRear; index++)
           result += queue[index] + "\n";
       
        return result + "<rear of queue>";
    }
  
   public static void main(String[] args){
       //Testing CircularArrayQueue 
       //System.out.println("Testing CircularArrayQueue");
       
       
       // //Testing empty CircularArrayQueue 
        // //System.out.println("***Test Case 0: Testing empty circular Queue***");
        // CircularArrayQueue test0 = new CircularArrayQueue();
        // //System.out.println("\tEnqueue Method => Throw Empty Collection");
        // //System.out.println(test0);
        // //test0.dequeue();
        // System.out.println("\tSize => 0");
        // System.out.println(test0.size());
        // System.out.println("\tisEmpy Method => True");
        // System.out.println(test0.isEmpty());
        // System.out.println("\tfirst Method => Throw Empty Collection");
        // System.out.println(test0.first());
        
        //Testing a singleton Queue
        
        System.out.println("***Test Case 1: Testing a singleton Queue***");
        CircularArrayQueue test1 = new CircularArrayQueue();
        System.out.println("\tEnqueue Method => adds one element");
        test1.enqueue(1);
        System.out.println(test1);
        System.out.println("\tSize => 1");
        System.out.println(test1.size());
        System.out.println("\tisEmpy Method => False");
        System.out.println(test1.isEmpty());
        System.out.println("\tfirst Method => returns first element");
        System.out.println(test1.first());
        System.out.println("\tDequeue Method => removes element");
        test1.dequeue();
        System.out.println(test1);
        
        //Testing a CircularArrayQueue with elements where the rear of the
        //queue does not reach the end of the array
        System.out.println("***Test Case 3: Testing multiple elements in a Queue where" + 
         " the rear of the queue does not reach the end of the array***");
        CircularArrayQueue test3 = new CircularArrayQueue();
        System.out.println("\tEnqueue Method => adds multiple elements");
        test3.enqueue(1);
        test3.enqueue(2);
        System.out.println(test3);
        System.out.println("\tSize = 2");
        System.out.println(test3.size());
        System.out.println("\tisEmpty Method => False");
        System.out.println(test3.isEmpty());
        System.out.println("\tfirst Method => returns first element");
        System.out.println(test3.first());
        System.out.println("\tDequeue Method => removes first element");
        test3.dequeue();
        System.out.println(test3);
        
        //Testing a CircularArrayQueue with elements where the rear of the
        //queue does reach the end of the array and expand capacity
        System.out.println("***Test Case 4: Testing multiple elements in a Queue where" + 
        " the rear of the queue does reach the end of the array and expand capacity***");
        CircularArrayQueue test4 = new CircularArrayQueue();
        System.out.println("\tEnqueue Method => adds multiple elements");
        test4.enqueue(1);
        test4.enqueue(2);
        test4.enqueue(3);
        test4.enqueue(4);
        System.out.println(test4);
        System.out.println("\tSize = 4");
        System.out.println(test4.size());
        System.out.println("\tisEmpty Method => False");
        System.out.println(test4.isEmpty());
        System.out.println("\tfirst Method => returns first element");
        System.out.println(test4.first());
        System.out.println("\tDequeue Method => removes first element");
        test4.dequeue();
        System.out.println(test4);
        
        //Testing a CircularArrayQueue with elements where the rear of the
        //queue does reach the end of the array
        System.out.println("***Test Case 5: Testing multiple elements in a Queue with" 
        + " elements where the rear of the queue does reach the end of the array***");
        CircularArrayQueue test5 = new CircularArrayQueue();
        System.out.println("\tEnqueue Method => adds multiple elements");
        test5.enqueue(1);
        test5.enqueue(2);
        test5.enqueue(3);
        System.out.println(test5);
        System.out.println("\tSize = 3");
        System.out.println(test5.size());
        System.out.println("\tisEmpty Method => False");
        System.out.println(test5.isEmpty());
        System.out.println("\tfirst Method => returns first element");
        System.out.println(test5.first());
        System.out.println("\tDequeue Method => removes first element");
        test5.dequeue();
        System.out.println(test5);
    }
   
}