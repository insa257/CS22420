* RandomizedQueue creates a random queue of iterable items.
 * 
 * @author manyanair insanorcross
 * @version Summer 2022
 */

package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
	private Item[] queue;
	private int size;

	@SuppressWarnings("unchecked")
	public RandomizedQueue()
	{
		queue = (Item[]) new Object[1];
		size = 0;
	}
	
	/**
	 * Checks the size to see if the queue is empty
	 * 
	 * @return if the queue is empty or not
	 */
	public boolean isEmpty()
	{
		return (size == 0);
	}

	/**
	 * Returns the number of items in the queue.
	 * 
	 * @return size of queue
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Changes the number of total slots in the queue to 'capacity.'
	 * 
	 * @param capacity - number of total slots in new queue
	 */
	private void resize(int capacity)
	{
		@SuppressWarnings("unchecked")
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++)
		{
			temp[i] = queue[i];
		}
		queue = temp; 
	}

	/**
	 * Adds item to the queue.
	 * 
	 * @param item - to be added to queue
	 */
	public void enqueue(Item item)
	{
		// Handle edge case
		if (item == null)
			throw new NullPointerException("The entry is null");

		// Double queue if full
		if (size == queue.length)
		{
			resize(2 * queue.length);
		}
			
		// Add item to queue
		queue[size++] = item;
	}

	/**
	 * Randomly selects an item from the queue to be removed.
	 * 
	 * @return item - that was removed
	 */
	public Item dequeue()
	{
		// Handle edge case
		if (isEmpty())
			throw new NoSuchElementException("The queue is empty");
		
		// Randomly remove an item in queue 
		// and move last item into vacant spot
		int randomInt = StdRandom.uniform(0, size);
		Item item = queue[randomInt];
		size--;
		queue[randomInt] = queue[size];
		queue[size] = null; // Avoid loitering
		
		// Decrease queue size if 1/4 full
		if (size <= queue.length / 4)//queue.length > 4 && 
		{
			resize(queue.length / 2);	
		}
		return item;
	}

	/**
	 * Randomly returns an item from the queue, 
	 * but does not remove it from the queue.
	 * 
	 * @return item - randomly chosen from queue
	 */
	public Item sample()
	{
		if (isEmpty())
			throw new NoSuchElementException("The queue is empty");
		return queue[StdRandom.uniform(0, size)];
	}

	/** 
	 * Creates new Iterator
	 */
	public Iterator<Item> iterator()
	{
		return new QueueIterator(queue, size);
	}

	/**
	 * Implements classes for Iterator interface
	 * by randomly iterating through the queue.
	 */
	private class QueueIterator implements Iterator<Item>
	{ 
		private Item[] iteratorQueue;
		private int iteratorIndex = 0;

		@SuppressWarnings("unchecked")
		public QueueIterator(Item[] queue, int size)
		{
			iteratorQueue = (Item[]) new Object[size];
			
			// Copy queue into private interatorQueue
			for (int i = 0; i < iteratorQueue.length; i++) 
			{
                iteratorQueue[i] = queue[i];
            }
			
			// Randomize copied queue so that the iteration is random
			for (int j = 1; j < iteratorQueue.length; j++) 
			{
                int swapIndex = StdRandom.uniform(j + 1);

                Item temp = iteratorQueue[j];
                iteratorQueue[j] = iteratorQueue[swapIndex];
                iteratorQueue[swapIndex] = temp;
            }
		}

		@Override
		public boolean hasNext()
		{
			return (iteratorIndex < iteratorQueue.length);
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next()
		{
			if (!hasNext()) 
			{
                throw new NoSuchElementException("Queue is empty");
            }

            Item item = iteratorQueue[iteratorIndex];
            iteratorIndex++;
            return item;
        }
	}

	// Testing RandomizedQueue:
	public static void main(String[] args)
	{
		// Test if queue is empty after adding one item and removing it
		RandomizedQueue<Integer> randomQ1 = new RandomizedQueue<Integer>();
		randomQ1.enqueue(1);
		for (int r : randomQ1)
		{
			StdOut.print(r);
		}
		StdOut.println();
		
		randomQ1.dequeue();
		
		for (int r : randomQ1)
		{
			StdOut.print(r);
		}
		StdOut.println();
		StdOut.println(randomQ1.size());
		StdOut.println(randomQ1.isEmpty());
		StdOut.println();
		
		// Test if output is random
		RandomizedQueue<Integer> randomQ2 = new RandomizedQueue<Integer>();
		for (int i = 0; i < 10; i++)
		{
			randomQ2.enqueue(i);
		}
		
		for (Integer j : randomQ2)
		{
			StdOut.print(j + " ");
		}
		StdOut.println();
		
		// Test if all items get removed
		for (int j = 0; j < 10; j++)
		{
			int test = (int) randomQ2.dequeue();
			StdOut.print(test + " ");
		}
		StdOut.println();
		StdOut.println(randomQ2.size());
		StdOut.println(randomQ2.isEmpty());
		StdOut.println();
	}
}
