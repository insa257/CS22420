package KevinBaconGame;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * Creates a BaconGraph and runs the breadth first search to find the shortest path between
 * a seed and a destination. It also determines the number of vertices between the seed and the destination,
 * known as the Bacon number.
 * 
 * @author insanorcross
 *
 */
public class BaconGame 
{
	// Fields 
	private BaconGraph graph = new BaconGraph();
	private String seed, destination;
	
	// Constructor
	public BaconGame(String seed, String destination)
	{
		this.seed = seed;
		this.destination = destination;
		
	}
	
	/**
	 *  Conducts a breadth first search to find the shortest path from the seed to the destination. The path is returned 
	 *  as a queue in seed - movie - actor - ... - destination format.
	 *  
	 * @param seed - starting point of the bfs search
	 * @param destination - end point of the bfs search
	 * @return A queue of strings 
	 */
	public Queue<String> getPath(String seed, String destination)
	{
		BreadthFirstPaths bfp = new BreadthFirstPaths(graph.graph(), graph.indexOfActor(seed));
		Queue<String> actors = new Queue<String>();
		Queue<String> path = new Queue<String>();
		
    	if (bfp.hasPathTo(graph.indexOfActor(destination)))
    	{
	        for (Integer s : bfp.pathTo(graph.indexOfActor(destination))) 
	        {	
	            actors.enqueue(graph.getActor(s));
	        }
	        	        
	        while(actors.size() > 1)
	        {
	        	String current = actors.dequeue();
				String next = actors.peek();
				path.enqueue(current);
				path.enqueue(this.graph().getEdge(this.graph().indexOfActor(current), this.graph().indexOfActor(next)));
	        }
	        path.enqueue(actors.dequeue());
    	}
    	else
    		path = null;
		
		return path;
	}
	
	/**
	 * Finds and returns the distance between the seed and destination along the shortest path.
	 * 
	 * @return Bacon number
	 */
	public int getBaconNumber()
	{
		BreadthFirstPaths bfp = new BreadthFirstPaths(graph.graph(), graph.indexOfActor(seed));
		return bfp.distTo(graph.indexOfActor(destination));
	}
	
	/** 
	 * Returns the graph built from the input files.
	 * 
	 * @return BaconGraph
	 */
	public BaconGraph graph()
	{
		return graph;
	}

	// For debugging
	public static void main(String[] args) 
	{
		
		String source = "Kevin Bacon"; // Change to get from user later
		String destination = "Lisa Kudrow"; // Change to get from user later
		BaconGraph graph = new BaconGraph();
		BaconGame game = new BaconGame(graph, source, destination);
		
		// Find path
		Queue<String> path = game.getPath(source, destination);
		StdOut.println("This is the path from " + source + " to " + destination + ":");
		while (!path.isEmpty())
		{
			StdOut.print(path.dequeue() + " -> ");
		}
		StdOut.println();
		
		// Find Bacon number
		StdOut.println("Bacon number: " + game.getBaconNumber());
	}

}
