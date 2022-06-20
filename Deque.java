/**
 * Deque creates an iterable double-ended queue.
 * Items can be added and removed to both the front and the end.
 * 
 * @author manyanair, insanorcross
 * @version Summer 2022
 */

package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;


public class Deque<Item> implements Iterable<Item>
{
	private Node first = null;
	private Node last = null;
	private int size = 0;

	private class Node 
	{
		private Item item;
	    private Node next;
	    private Node prev;
	}
	
	// Construct an empty deque
    public Deque() 
    { }

    /**
     * Checks whether the Deque is empty.
     * 
     * @return if the deque is empty or not.
     */
    public boolean isEmpty()
    {
    	return (size == 0);
    }

    /**
     * Size of Deque.
     * 
     * @return number of items in Deque
     */
    public int size()
    {
		return size;
    }

    /**
     * Adds an item to the front of the queue.
     * 
     * @param item - to be added to Deque
     */
    public void addFirst(Item item)
    {
    	if (item == null)
    		throw new NullPointerException("Cannot add a null item");
    	
    	Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        
        if (isEmpty()) 
        {
            last = first;
        } 
        else 
        {
            oldFirst.prev = first;
        }
        
        size++;
    }

    /**
     * Adds an item to the front of the queue.
     * 
     * @param item - to be added to Deque
     */
    public void addLast(Item item)
    {
    	if (item == null)
    		throw new NullPointerException("Cannot add a null item");
    	
    	Node oldLast = last;
    	last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        
        if (isEmpty()) 
        {
            first = last;
        } 
        else 
        {
            oldLast.next = last;
        }
        
        size++;
    }
    
    /**
     * Removes an item from the front of the queue.
     * 
     * @return item - that was removed from Deque
     */
    public Item removeFirst()
    {
    	if (size == 0)
    		throw new NoSuchElementException("Cannot remove an item from an empty Deque");
    	
    	 Item item = first.item;
         size--;
         if (isEmpty()) 
         {
             first = null;
             last = null;
         } 
         else 
         {
             first = first.next;
             first.prev = null;
         }
         return item;
    }

    /**
     * Removes an item from the end of the queue.
     * 
     * @return item - that was removed from Deque
     */
    public Item removeLast()
    {
    	if (size == 0)
    		throw new NoSuchElementException("Cannot remove an item from an empty Deque");
    	
    	Item item = last.item;
        size--;
        if (isEmpty()) 
        {
            first = null;
            last = null;
        } 
        else 
        {
            last = last.prev;
            last.next = null;
        }
        return item;
    }

    /**
     * Creates iterator
     */
    public Iterator<Item> iterator() 
    {
        return new dequeIterator();
    }
    
    /**
     * Implements the methods for the Iterable interface.
     */
    private class dequeIterator implements Iterator<Item>
    {
    	  private Node current = first;

          public boolean hasNext() 
          {
              return current != null;
          }

          public void remove() 
          {
              throw new UnsupportedOperationException();
          }

          public Item next() 
          {
              if (!hasNext()) 
              {
                  throw new NoSuchElementException("No more items");
              } 
              else 
              {
                  Item item = current.item;
                  current = current.next;
                  return item;
              }
          }
    }

    // For testing purposes.
    public static void main(String[] args)
    {
    	Deque<String> deque = new Deque<String>();
    	
    	// Check if empty deque was created
    	StdOut.println(deque.isEmpty());
    	
    	// Test for edge case
    	//deque.removeFirst();
    	//deque.removeLast();
    	//deque.addFirst(null);
    	//deque.addLast(null);
    	
    	// Add strings to deque
    	deque.addFirst("One");
    	deque.addLast("Two");
    	deque.addFirst("Three");
    	deque.addLast("Four");
    	
    	// Print resulting deque
    	StdOut.println(deque.size());
    	for (String d : deque)
		{
    		 StdOut.print(d + ' ');
		}
    	
    	// Remove items from deque
    	deque.removeFirst();
    	deque.removeLast();
    	
    	// Print resulting deque
    	StdOut.println("\n" + deque.size());
    	for (String d : deque)
		{
    		 StdOut.print(d + ' ');
		}  	
    	
    	
    }
}