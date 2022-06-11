package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
	private Item[] queue;
	private int last;
	private int size;

	@SuppressWarnings("unchecked")
	public RandomizedQueue()
	{
		queue = (Item[]) new Object[2];
		size = 0;
		last = 0;
	}

	public boolean isEmpty()
	{
		return (size == 0);
	}

	public int size()
	{
		return size;
	}

	private void resize(int capacity)
	{
		assert capacity >= size;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++)
		{
			temp[i] = queue[i];
		}
		queue = temp;
	}

	public void enqueue(Item item)
	{
		if (item == null)
			throw new NullPointerException("The entry is null");
		else
		{
			if (size == queue.length)
				resize(2 * queue.length);
			last = size;
			queue[size++] = item;
		}
	}

	public Item dequeue()
	{
		if (isEmpty())
			throw new NoSuchElementException("The queue is empty");
		int randomInt = StdRandom.uniform(size);
		Item item = queue[randomInt];
		if (randomInt == last)
		{
			queue[last] = null;
			last--;
			size--;
		} 
		else
		{
			queue[randomInt] = queue[last];
			queue[last] = null;
			last--;
			size--;
		}

		if (size > 0 && size == queue.length / 4)
			resize(queue.length / 2);
		return item;

	}

	public Item sample()
	{
		if (isEmpty())
			throw new NoSuchElementException("The queue is empty");
		int randomInt = StdRandom.uniform(size);
		return queue[randomInt];
	}

	public Iterator<Item> iterator()
	{
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<Item>
	{
		private int i;

		public QueueIterator()
		{
			i = size - 1;
		}

		@Override
		public boolean hasNext()
		{
			return i >= 0;
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			return queue[i--];
		}

	}

	public static void main(String[] args)
	{
		RandomizedQueue randomQ = new RandomizedQueue();
		// for testing purposes

		for (int i = 0; i < 20; i++)
		{
			randomQ.enqueue(i);
		}
		StdOut.println("Testing if output is random: ");
		for (int j = 0; j < 20; j++)
		{
			int test = (int) randomQ.dequeue();
			StdOut.print(test + " ");
		}
		
	}
}