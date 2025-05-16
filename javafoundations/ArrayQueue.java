package javafoundations;

import javafoundations.exceptions.*;

/**
 * Represents an array implementation of the queue Interface. 
 * The front of the queue is kept at array index 0.
 * 
 * */
public class ArrayQueue<T> implements Queue<T>
{
    private final int DEFAULT_CAPACITY = 10;
    private int count;
    private T[] queue;

    /**
     * Constructor
     * Creates an empty queue.
     */
    public ArrayQueue()
    {
        count = 0;
        queue = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return the element removed from the front of the queue.
     * @throws EmptyCollectionException if this queue is empty
     */
    public T dequeue() throws EmptyCollectionException
    {
        if (count == 0)
            throw new EmptyCollectionException ("Dequeue operation failed. "
                + "The queue is empty.");

        T result = queue[0];

        count--;

        // shift the elements to keep the front element at index 0
        for (int index=0; index < count; index++)
            queue[index] = queue[index+1];

        queue[count] = null;

        return result;
    }

    /**
     * Adds the specified element to the rear of the queue,
     * expanding the underlying array if needed.
     * 
     * @param The element to be enqueued into the queue
     */public void enqueue (T element) 
    {
        if(count == queue.length)
            expandCapacity();

        queue[count] = element;
        count++;
    }

    /**
     *  Creates a new array, twice the capacity of the old one,
     *  to store the contents of this queue.
     **/
    private void expandCapacity()
    {
        T[] larger = (T[])(new Object[queue.length * 2]);

        for(int index = 0; index < queue.length; index++)
            larger[index] = queue[index];

        queue = larger;
    }

    /**
     * Returns the element at the front of the queue
     * without removing it.
     * @return the element at the front of the queue
     * @throws EmptyCollectionException if this queue is empty
     */
    public T first () throws EmptyCollectionException 
    {
        if(count == 0)
            throw new EmptyCollectionException ("First operation failed. Queue is empty.");

        return queue[0];
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

        for(int index = 0; index < count; index++)
            result += queue[index] + "\n";

        return result;
    }
}
