package a02;

import edu.princeton.cs.algs4.*;

public class Subset
{
	public static void main(String[] args)
	{
		  RandomizedQueue<String> queue = new RandomizedQueue<String>();
	        int size = 3;
	        while (!StdIn.isEmpty()) {
	            queue.enqueue(StdIn.readString());
	        }
	        while (size > 0) {
	            System.out.println(queue.dequeue());
	            size--;
	        }
	}
}
 