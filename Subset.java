/**
 * Subset reads in an integer k and N strings from the keyboard. 
 * It then prints out k random strings from the input strings.
 */
package a02;

import edu.princeton.cs.algs4.*;

public class Subset
{
	public static void main(String[] args)
	{
		// Use RandomizedQueue to get a random output:
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		
		StdOut.print("Provide the number of items you would like to output: ");
		int k = StdIn.readInt();
		
		StdOut.println();
		StdOut.print("Input the strings you would like to add to the queue: ");
		while(!StdIn.isEmpty())
		{
			queue.enqueue(StdIn.readString());
		}
		
		StdOut.println();
		StdOut.println("Here is the output: ");
		for (int i = 0; i < k; i++)
		{
			StdOut.println(queue.dequeue());
		}
	}
}
 
