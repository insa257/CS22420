package a03;

import java.util.Comparator;

/**
 * BinarySearchDeluxe uses binary search to find the first or last entry of a key in an array.
 * The input array must be sorted and none of the input arguments can be null.
 * 
 * @author insanorcross, manyanair
 *
 */
public class BinarySearchDeluxe 
{
	/**
	 *  Return the index of the first key in a[] that equals the search key, 
	 *  or -1 if no such key.
	 * 
	 * @param a - sorted array
	 * @param key - key to find in array
	 * @param comparator
	 * @return - first index of key, or -1 when key is not found 
	 */
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator)
	{
		// Edge cases
		if (a == null || key == null || comparator == null)
			throw new NullPointerException("Argument cannot be null");
		
		// Binary search
		int low = 0, high = a.length -1, middle;		
		while (low <= high)
		{
			middle = (low + high) / 2;
			// key is before the middle
			if (comparator.compare(key, a[middle]) < 0) 
			{
				high = middle - 1;
			}
			// key is after the middle
			else if (comparator.compare(key, a[middle]) > 0) 
			{
				low = middle + 1;
			}
			// key is the middle
			else  
			{
				// Check if key is first element
				if (middle == 0)
					return middle;
				// Check if entry before middle is also a key
				else if (comparator.compare(key,  a[middle - 1]) == 0)
					high = middle - 1;
				// middle is the first key
				else
					return middle;
			}
		}
		
		// Key not found
		return -1;
		
	}
	
	/**
	 *  Return the index of the last key in a[] that equals the search key, 
	 *  or -1 if no such key.
	 * 
	 * @param a - sorted array
	 * @param key - key to find in array
	 * @param comparator
	 * @return - last index of key, or -1 when key is not found 
	 */	
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator)
	{
		// Edge cases
		if (a == null || key == null || comparator == null)
			throw new NullPointerException("Argument cannot be null");
		// Binary search
		int low = 0, high = a.length -1, middle;		
		while (low <= high)
		{
			middle = (low + high) / 2;
			// key is before the middle
			if (comparator.compare(key, a[middle]) < 0) 
			{
				high = middle - 1;
			}
			// key is after the middle
			else if (comparator.compare(key, a[middle]) > 0) 
			{
				low = middle + 1;
			}
			// key is the middle
			else  
			{
				// Check if key is last element
				if (middle == a.length - 1)
					return middle;
				// Check if entry after middle is also a key
				if (comparator.compare(key,  a[middle + 1]) == 0)
					low = middle + 1;
				// middle is last key
				else
					return middle;
			}
		}
		
		// Key not found
		return -1;
	}
}
