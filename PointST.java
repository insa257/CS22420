package a05;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.Stack;

/**
 * Creates an iterable BlackRedST of 2D points (keys) 
 * and Values (values).
 * 
 * @author insanorcross, manyanair
 *
 * @param <Value> - value associated with point
 */
public class PointST<Value>
{
	// Fields
	private RedBlackBST<Point2D, Value> points;
	
	/**
	 * Constructs an empty symbol table of points.
	 */
	public PointST() 
	{
		this.points = new RedBlackBST<Point2D, Value>();
	}
	
	/**
	 *  Returns whether the symbol table is empty or not.
	 *  
	 * @return true if symbol table is empty, false otherwise.
	 */
	public boolean isEmpty() 
	{
		return (points.size() == 0);
	}
	
	/**
	 *  Returns how many points are in the symbol table
	 *  
	 * @return number of points
	 */
	public int size()
	{
		return points.size();
	}

	
	/**
	 *  Associates the value val with point <@code p> by
	 *  adding it to the symbol table <@code points>.
	 *  
	 * @param p - 2D point
	 * @param val - value associated with <@code p>
	 * @throws NullPointerException if <@code p> or <@code val> is null
	 */
	public void put(Point2D p, Value val) 
	{
		// Corner cases
		if (p.equals(null) || val.equals(null))
			throw new NullPointerException("Argument cannot be null");
		
		// Associate the value val with point p 
		points.put(p, val);
	}
	   
	/**
	 *  Returns the value associated with point <@code p>.
	 *  
	 * @param p - 2D point
	 * @return value associated with <@code p>
	 * @throws NullPointerException if <@code p> is null
	 */
	public Value get(Point2D p) 
	{
		// Corner cases
		if (p.equals(null))
			throw new NullPointerException("Argument cannot be null");
		
		// Get value associated with point p 
		return points.get(p);
	}
	   
	/**
	 *  Returns whether the point <@code p> is in the symbol table or not.
	 *  
	 * @param p - 2D point
	 * @return true if <@code p> is in symbol table, false otherwise
	 * @throws NullPointerException if <@code p> is null
	 */
	public boolean contains(Point2D p) 
	{
		// Corner cases
		if (p.equals(null))
			throw new NullPointerException("Argument cannot be null");
		
		// Get value associated with point p 
		return points.contains(p);
	}
	   
	/**
	 * Iterates through all points in the symbol table.
	 * 
	 * @return Iterable of 2D points
	 */
	public Iterable<Point2D> points() 
	{
		return points.keys();
	}
	   
	/**
	 * Returns all points that are inside the rectangle <@code rect>.
	 * 
	 * @param rect - rectangle to find all points in
	 * @return Iterable of all points inside <@code rect>
	 */	
	public Iterable<Point2D> range(RectHV rect)
	{
		// Corner case
		if (rect == null) 
            throw new NullPointerException("Argument to range() is null\n");

		// Find boundaries of rectangle
        double xmin = rect.xmin();
        double ymin = rect.ymin();
        double xmax = rect.xmax();
        double ymax = rect.ymax();
        
        // Create stack to store points in the rectangle
        Stack<Point2D> pointsInRect = new Stack<Point2D>();
        
        // Iterate through all points in points and check if they are 
        // in the rectangle, add to stack if they are
        for (Point2D p : points()) 
        {
            double x = p.x();
            double y = p.y();
            if (x >= xmin && x <= xmax && y >= ymin && y <= ymax)
                pointsInRect.push(p);
        }
        
        // Return Stack of points that are in rectangle
        return pointsInRect;
	}
	   
	/**
	 *  Returns nearest neighbor to point p; null if the symbol table is empty.
	 *  
	 * @param p - Point2D 
	 * @return point closest to <@code p>
	 */
	public Point2D nearest(Point2D p) 
	{
		// Corner case
        if (p == null) 
            throw new NullPointerException("Argument to nearest() is null");

        // Return null if symbol table is empty
        if (points.isEmpty()) 
            return null;
           
        // Assume first point is closest
        Point2D nearestP = points.select(0);
        double dist = nearestP.distanceSquaredTo(p);
        
        // Check if any other point is closer
        for (Point2D temp : points()) 
        {
            if (temp.distanceSquaredTo(p) < dist) 
            {
                dist = temp.distanceSquaredTo(p);
                nearestP = temp;
            }
        }
        
        // Return nearest point
        return nearestP;
	}
	   
	// Unit testing of the methods (not graded)
	public static void main(String[] args) 
	{ }
}
