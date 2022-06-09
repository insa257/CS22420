package a02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

public class RandomizedQueue<Item> implements Iterable<Item> //item is a generic. Allows us to make randomized queues of any data type
{
	//private static final Item[] randomQueue = null;
	//private static final Item[] randomQueue = null;
	// Fields
	//Queue<Item> randomQueue = new Queue<>();
	//Item[] randomQueue;
	private ArrayList<Item> randomQueue = new ArrayList<Item>();
	int size;
	
	// Constructor
//	public RandomizedQueue<Item>
//	{
//		size = 0;
//	}
	 public RandomizedQueue() // construct an empty randomized queue
	 {
		  ArrayList<Item> randomQueue = new ArrayList<Item>();
	 }
	   public boolean isEmpty()                 // is the queue empty?
	   { 
		   return (size == 0);
	   }
	   public int size()                        // return the number of items on the queue
	   {
		   return size;
	   }
	   public void enqueue(Item item)           // add the item
	   {
		   if (item == null) {
	            throw new NullPointerException("Can't enqueue null item");
	        }
	        this.randomQueue[size++] = item;
	        if (size == this.randomQueue.length) {
	            resize(2 * this.randomQueue.length);
	        }
	        
	        //swapping items in queue
	        int i = StdRandom.uniform(size);
	        Item temp = randomQueue[i];
	        randomQueue[i] = randomQueue[size - 1];
	        randomQueue[size - 1] = temp;
	    }
	   
	public Item dequeue()                    // delete and return a random item
	   {
		   if (size == 0) 
		   {
	            throw new NoSuchElementException("Can't dequeue, queue is empty");
	        }
	        Item item = this.randomQueue[--size];
	        if (size > 0 && size == this.randomQueue.length / 4) {
	            resize(this.randomQueue.length / 2);
	        }
	        this.randomQueue[size] = null;
	        return item;
	   }
	
	   //create larger queue through copying contents
	   private void resize(int qSize)
	   {
		   Item[] copy = (Item[]) new Object[qSize];
	        for (int i = 0; i < size; i++)
	            copy[i] = randomQueue[i];
	        randomQueue = copy;
	   }
	public Item sample()                  // return (but do not delete) a random item
	   {
		   if (size == 0) 
		   {
	            throw new NoSuchElementException("Can't dequeue, queue is empty");
	       }
		   
	        int i = StdRandom.uniform(size);
	        return this.randomQueue[i];
	   }
	   
	   public Iterator<Item> iterator() {
	        return new randomIterator();
	    }

	    private class randomIterator implements Iterator<Item> 
	    {
	        private int i;

	        public boolean hasNext() 
	        {
	            return randomQueue[i] != null;
	        }

	        public void remove() 
	        {
	            throw new UnsupportedOperationException();
	        }

	        public Item next() 
	        {
	            if (!hasNext()) {
	                throw new NoSuchElementException();
	            } else {
	                Item item = randomQueue[i++];
	                return item;
	            }
	        }
	    }
	   public static void main(String[] args)   // unit testing
	   {
		   RandomizedQueue<String> rq = new RandomizedQueue<>();
	        while (!StdIn.isEmpty()) {
	            String s = StdIn.readString();
	            if (s.equals("-"))
	                StdOut.print(rq.dequeue());
	            else
	                rq.enqueue(s);
	        }
	   }
}