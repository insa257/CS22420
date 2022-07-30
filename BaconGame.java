package KevinBaconGame;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class BaconGame 
{
	// Fields 
	int baconNumber;
	BaconGraph graph = new BaconGraph();
	String seed, destination;
	
	// Constructor
	public BaconGame(String seed, String destination)
	{
		this.seed = seed;
		this.destination = destination;
		
	}
	
	// Auxiliary functions
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
	
	public Queue<String> getMoviePath(Queue<String> actors)
	{
		//Queue<String> temp = actors
		if (actors == null)
			return null;
		
		Queue<String> movies = new Queue<String>();		
		while (actors.size() > 1)
		{
			String current = actors.dequeue();
			String next = actors.peek();
			movies.enqueue(this.graph().getEdge(this.graph().indexOfActor(current), this.graph().indexOfActor(next)));			
		}
		
		return movies;
	}
	
	public int getBaconNumber()
	{
		BreadthFirstPaths bfp = new BreadthFirstPaths(graph.graph(), graph.indexOfActor(seed));
		return bfp.distTo(graph.indexOfActor(destination));
	}
	
	public BaconGraph graph()
	{
		return graph;
	}

	// For debugging
	public static void main(String[] args) 
	{
		
		String source = "Kevin Bacon"; // Change to get from user later
		String destination = "Lisa Kudrow"; // Change to get from user later
		BaconGame game = new BaconGame(source, destination);
		
		// Find path
		Queue<String> path = game.getPath(source, destination);
		StdOut.println("This is the path from " + source + " to " + destination + ":");
		while (!path.isEmpty())
		{
			StdOut.print(path.dequeue() + " -> ");
		}
		StdOut.println();
		
		StdOut.println("Bacon number: " + game.getBaconNumber());
	}

}
