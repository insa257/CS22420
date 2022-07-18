package a05;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

/**
 * Creates a k-dimensional binary tree of 2D points (keys) 
 * and their values (values).
 * 
 * @author insanorcross, manyanair
 * @param <Value> - value associated with the key
 */
public class KdTreeST<Value> 
{
	// Fields:
	private Node root;
	private int size;
	
	/**
	 * Creates a <@code Node> that contains the key and value pair.
	 * It also keeps references to the left and right nodes of
	 * this node and whether the plane of the node is vertical 
	 * or horizontal.
	 */

	private class Node
	{
		// Fields:
		private Point2D key;
		private Value val;
		private Node left, right;
		private boolean vertical = true; // Plane: vertical if true, horizontal if false
		
		// Constructor:
		public Node(Point2D key, Value val)
		{
			this.key = key;
			this.val = val;
		}
	}
	// Constructor creates empty kd tree 
	public KdTreeST()
	{
		root = null;
		size = 0;		
	}

	/**
	 *  Returns whether the symbol table is empty or not.
	 *  
	 * @return true if symbol table is empty, false otherwise.
	 */                            
    public boolean isEmpty() 
    {
        return root == null;
    } 

    /**
	 *  Returns how many points are in the symbol table
	 *  
	 * @return number of points
	 */                      
    public int size() 
    {
        return size;
    } 

    /**
	 *  Associates the value <@code val> with point <@code p> by
	 *  adding it to the kd tree.
	 *  
	 * @param p - 2D point
	 * @param val - value associated with <@code p>
	 * @throws NullPointerException if <@code p> or <@code val> is null
	 */                      
    public void put(Point2D p, Value val)
    {
    	// Corner case
    	if (p == null|| val == null)
	    	throw new NullPointerException("Argument(s) to put() is (are) null");
    	
    	// Override value if key already exists
    	if (this.contains(p))
    		this.get(p);
    	
    	// Start comparison at root
    	root = put(root, p, val, true);
    }
    
    /**
     * Private method to implement recursive method 
     * of finding the right place to insert the new node
     * or override the value of an existing point (key).
     * Accesses whether to go left or right down the kd tree based on 
     * if the current plane is vertical or horizontal.
     * 
     * @param n - Current node
     * @param p - new Point2D to be inserted
     * @param val - new value to be inserted
     * @param vertical - boolean whether the plane is vertical or horizontal
     * @return Node on lower level
     */
    private Node put(Node n, Point2D p, Value val, boolean vertical)
    {
    	// Create new node if at bottom of tree
    	if (n == null)
    	{
    		Node temp = new Node(p, val);
    		temp.vertical = vertical;
    		size++;
    		return temp;
    	}
    	// Compare Node to add to current node
    	// Coordinates of node to insert
    	double x = p.x();
        double y = p.y();
        // Coordinates of current node
        double nx = n.key.x();
        double ny = n.key.y();
        // Compare coordinates of points based on whether the plane is vertical or horizontal
        if (n.vertical == true) 
        {   
        	// Vertical plane -> compare x coordinates   	
            if (x > nx) 
            	n.right = put(n.right, p, val, !n.vertical);
            else if (x < nx) 
            	n.left = put(n.left, p, val, !n.vertical); 
            // x coordinates are the same, go right if y does not equal ny and override val if y equals ny
            else if (y != ny) 
            	n.right = put(n.right, p, val, !n.vertical);
            else
            	n.val = val;
        }
        else 
        {
        	// Horizontal plane -> compare y coordinates
            if (y > ny) 
            	n.right = put(n.right, p, val, !n.vertical);
            else if (y < ny) 
            	n.left = put(n.left, p, val, !n.vertical); 
            // y coordinates are the same, go top (right) if x does not equal nx and override val if x equals nx
            else if (x != nx) 
            	n.right = put(n.right, p, val, !n.vertical);
            else
            	n.val = val;
        }

        return n;
    }

    /**
     * Gets the value associates with point <@code p>.
     * 
     * @param p - 2D point
     * @return - value associated with p
     */
    public Value get(Point2D p)
    {
    	// Corner case
    	if (p == null)
	    	throw new NullPointerException("Argument to get() is null");
    	
    	// Search tree for point
    	Node n = root;
    	while (n != null) 
    	{
            if (n.vertical == true) 
            {
            	// Vertical plane -> compare x coordinates 
                if (p.x() > n.key.x())  
                	n = n.right;
                else if (p.x() < n.key.x())
                	n = n.left;
             // x coordinates are the same, check y coordinates
                else if (p.y() != n.key.y()) 
                	n = n.right;
                else return n.val; // Node found
            }
            else // horizontal plane -> compare y values
            {
                if (p.y() > n.key.y()) 
                	n = n.right;
                else if (p.y() < n.key.y()) n = n.left;
                // y coordinates are the same, check x coordinates
                else if (p.x() != n.key.x()) n = n.right;
                else return n.val; // Node found
            }
        }
    	// Point is not in tree
    	return null;
    }
    
    /**
     * Returns whether the point <@code p> is in the symbol table.
     *  
     * @param p - 2D point
     * @return true if p is the the symbol table, false otherwise
     */
    public boolean contains(Point2D p) 
    {
    	// Corner case
    	if (p == null) 
    	{
            throw new NullPointerException("Argument to contains() is null\n");
        }
        
    	// Go through kd tree to find a match
    	Node n = root;
    	while (n != null) 
    	{
            if (n.vertical == true) 
            {
            	// Vertical plane -> compare x coordinates 
                if (p.x() > n.key.x())  
                	n = n.right;
                else if (p.x() < n.key.x())
                	n = n.left;
             // x coordinates are the same, check y coordinates
                else if (p.y() != n.key.y()) 
                	n = n.right;
                else return true; // Node found
            }
            else // horizontal plane -> compare y values
            {
                if (p.y() > n.key.y()) 
                	n = n.right;
                else if (p.y() < n.key.y()) n = n.left;
                // y coordinates are the same, check x coordinates
                else if (p.x() != n.key.x()) n = n.right;
                else return true; // Node found
            }
        }
    	// Node is not in tree
        return false;
    }

    /**
     *  Iterates through points in tree in level-order.
     *  
     *  @return Iterable of points in level-order
     */
    public Iterable<Point2D> points()
    {
    	Queue<Point2D> keys = new Queue<Point2D>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) 
        {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
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
            throw new NullPointerException("the argument to range() is null\n");

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
        if (this.isEmpty()) 
            return null;
           
        // Assume first point is closest
        Point2D nearestP = root.key;
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
 
    // unit testing of the methods (optional)
    public static void main(String[] args) 
    { }                   
}
