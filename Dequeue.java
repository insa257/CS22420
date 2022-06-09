package a02;

import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;


public class Dequeue<Item> implements Iterable<Item>
{
	 private Node first = null;
	 private Node last = null;
	 private int size = 0;

	    private class Node {
	        private Item item;
	        private Node next;
	        private Node prev;
	    }
	 // construct an empty dequeue
    public Dequeue()
    {
    	
    }

    // is the dequeue empty?
    public boolean isEmpty()
    {
    	return (size == 0);
    }

    // return the number of items on the dequeue
    public int size()
    {
		return size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
    	  Node oldFirst = first;
          first = new Node();
          first.item = item;
          first.next = oldFirst;
          first.prev = null;
          if (isEmpty()) {
              last = first;
          } else {
              oldFirst.prev = first;
          }
          size++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
    	 Node oldLast = last;
    	 last = new Node();
         last.item = item;
         last.next = null;
         last.prev = oldLast;
         if (isEmpty()) {
             first = last;
         } else {
             oldLast.next = last;
         }
         size++;
    }
    // remove and return the item from the front
    public Item removeFirst()
    {
    	 Item item = first.item;
         size--;
         if (isEmpty()) {
             first = null;
             last = null;
         } else {
             first = first.next;
             first.prev = null;
         }
         return item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
    	 Item item = last.item;
         size--;
         if (isEmpty()) {
             first = null;
             last = null;
         } else {
             last = last.prev;
             last.next = null;
         }
         return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new dequeIterator();
    }
    

    private class dequeIterator implements Iterator<Item>
    {
    	  private Node current = first;

          public boolean hasNext() {
              return current != null;
          }

          public void remove() {
              throw new UnsupportedOperationException();
          }

          public Item next() {
              if (!hasNext()) {
                  throw new NoSuchElementException();
              } else {
                  Item item = current.item;
                  current = current.next;
                  return item;
              }
          }
    }

    // unit testing (required)
    public static void main(String[] args)
    {
    	 Deque<String> deque = new Deque<>();
         while (!StdIn.isEmpty()) 
         {
             String s = StdIn.readString();
             if (s.equals("-"))
                 StdOut.print(deque.removeLast());
             else
                 deque.addFirst(s);
         }
    }
}