package KevinBaconGame;

import java.io.File;
import java.util.ArrayList;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

/**
 * Reads in actors and their IDs, movies and their IDs, and movie-actor pairings from three different files.
 * Creates an undirected graph where the actors are the vertices and the movies are the edges.
 * 
 * @author insanorcross
 */

public class BaconGraph 
{
	// Fields
	private Graph universe; //initialize the graph
	private int size = 0; // size of the graph (number of vertices)
	private ST<Integer, String> a = new ST<Integer, String>(); //id-actor ST
	private ST<Integer, String> m = new ST<Integer, String>(); //id-movie ST
	private ST<String, ArrayList<String>> ma = new ST<String, ArrayList<String>>(); //movie-actors ST
	private ST<Edge, String> edges = new ST<Edge, String>(); // movie names for all edges
	
	// Constructor
	public BaconGraph()
	{		
		//Read the actors into a id-name map
		In in = new In(new File("/Users/insanorcross/eclipse-workspace/2420_ProgrammingEnvironment/src/BaconGame/actorsTest.txt"));
		while(in.hasNextLine()) 
		{ 
			String line = in.readLine();
			Integer actorId = Integer.parseInt(line.split("\\|")[0]);
			String actorName = line.split("\\|")[1];
			a.put(actorId, actorName);
			if (actorId > size)
				size = actorId;		
		}
		size++; // Add one because the actor IDs start out at 1 not 0
		
		//Read the movies into an id-name map and set up a movie-list of actors map
		in = new In(new File("/Users/insanorcross/eclipse-workspace/2420_ProgrammingEnvironment/src/BaconGame/moviesTest.txt"));
		while(in.hasNextLine()) 
		{ 
			String line = in.readLine();
			Integer movieId = Integer.parseInt(line.split("\\|")[0]);
			String movieName = line.split("\\|")[1];
			m.put(movieId, movieName);
			ma.put(movieName, new ArrayList<String>()); //initialize array lists in the movie-actor map
		}
		
		//Put the actors into the movies they play in
		in = new In(new File("/Users/insanorcross/eclipse-workspace/2420_ProgrammingEnvironment/src/BaconGame/movie-actorsTest.txt"));
		while(in.hasNextLine()) { 
			String line = in.readLine();
			String movieName = m.get(Integer.parseInt(line.split("\\|")[0])); //get the movie name from the key
			String actorName = a.get(Integer.parseInt(line.split("\\|")[1])); //get the actor name from the key
			ma.get(movieName).add(actorName); //add the actor name to the movie's list
		}
		
		// Create the graph
		universe = new Graph(size);	
		// Go through all the movies and make connections between actors in the movies
		for(String movie: ma.keys()) 
		{ 
			/*StdOut.println(movie);
			for (String m : ma.get(movie))
				StdOut.print(m + " ");*/
			//go through every possible pair in the movie's actors
			for(int i = 0; i <= ma.get(movie).size(); i ++) 
			{ 
				for(int j = i + 1; j < ma.get(movie).size(); j ++) 
				{
					Edge e = new Edge(this.indexOfActor(ma.get(movie).get(i)), this.indexOfActor(ma.get(movie).get(j)), (this.indexOfActor(ma.get(movie).get(i)) + this.indexOfActor(ma.get(movie).get(j))));
					edges.put(e, movie);
					universe.addEdge(this.indexOfActor(ma.get(movie).get(i)), this.indexOfActor(ma.get(movie).get(j)));					
				}
			}
		}
	}
	
	/**
	 * Returns the underlying graph used to build the Bacon Graph.
	 * 
	 * @return graph
	 */
	public Graph graph()
	{
		return universe;
	}
	
	/**
	 * Checks whether the actor is in the BaconGraph and returns result.
	 * 
	 * @param s - name of actor
	 * @return true is actor is in graph, false otherwise
	 */
	public Boolean containsActor(String s)
	{
		for (int i : a.keys())
			if (s.equals(a.get(i))) return true;
		return false;
	}
	
	/**
	 * Checks whether the movie is in the BaconGraph and returns result.
	 * 
	 * @param s - name of movie
	 * @return true is movie is in graph, false otherwise
	 */
	public Boolean containsMovie(String s)
	{
		for (int i : m.keys())
			if (s.equals(m.get(i))) return true;
		return false;
	}
	
	/**
	 * Finds and returns the ID of the actor. Returns -1 if actor is not in graph.
	 * 
	 * @param s - name of actor
	 * @return actor ID
	 */
	public int indexOfActor(String s)
	{
		for (int i :a.keys())
			if (s.equals(a.get(i))) return i;
		return -1;
	}
	
	/**
	 * Finds and returns the ID of the movie. Returns -1 if movie is not in graph.
	 * 
	 * @param s - name of movie
	 * @return movie ID
	 */
	public int indexOfMovie(String s)
	{
		for (int i : m.keys())
			if (s.equals(m.get(i))) return i;
		return -1;
	}
	
	/**
	 * Finds and returns the name of the actor given their ID.
	 * 
	 * @param i - actor ID
	 * @return name of actor
	 */
	public String getActor(int i)
	{
		return a.get(i);
	}
	
	/**
	 * Finds and returns the name of the movie given its ID.
	 * 
	 * @param i - movie ID
	 * @return name of movie
	 */
	public String getMovie(int i)
	{
		return m.get(i);
	}
	
	/**
	 * Finds and returns the name of the movie connecting vertices v and w.
	 * They are the IDs of actors.
	 * 
	 * @param v - vertex
	 * @param w - vertex
	 * @return name of movie connecting v and w
	 */
	public String getEdge(int v, int w)	
	{
		for (Edge e : edges.keys())
		{
			if (e.either() == v && e.other(v) == w)
				return edges.get(e);
			if (e.either() == w && e.other(w) == v)
				return edges.get(e);				
		}
		return "Not found.";
	}
	
	// for testing/ debugging only
	public static void main(String[] args)
	{
		BaconGraph bg = new BaconGraph();
		// Test symbol tables created from files
		StdOut.println("Size of a: " + bg.a.size());
		StdOut.println("Contains actor 1534: " + bg.a.contains(1534));
		StdOut.println("Actor 1534: " + bg.a.get(1534));
		StdOut.println("Index of Lisa Kudrow: " + bg.indexOfActor("Lisa Kudrow"));
		StdOut.println("Size of m: " + bg.m.size());
		StdOut.println("Contains movie 1875: " + bg.m.contains(1875));
		StdOut.println("Movie 1875: " + bg.m.get(1875));
		StdOut.println("Index of Apollo 13 (1995): " + bg.indexOfMovie("Apollo 13 (1995)"));
		StdOut.println("Size of ma: " + bg.ma.size());
		StdOut.println("Contains Clockwatchers (1997): " + bg.ma.contains("Clockwatchers (1997)"));
		StdOut.println("Actors in Apollo 13 (1995): " + bg.ma.get("Apollo 13 (1995)"));
		// Test graph
		StdOut.println("V: " + bg.graph().V());
		StdOut.println("E: " + bg.graph().E());
		StdOut.print("Actors connected to " + bg.getActor(1) + ": ");
		for (int i : bg.graph().adj(1))
			StdOut.print(bg.getActor(i) + ", ");
		StdOut.println();
		// Test edges
		StdOut.println("Size of edges: " + bg.edges.size());
		for (Edge e : bg.edges)
			StdOut.println(e + " " + bg.edges.get(e));
		StdOut.println("Edge connecting " + bg.getActor(1) + " and " + bg.getActor(841) + " is: " + bg.getEdge(1, 841));
		StdOut.println("Edge connecting " + bg.getActor(841) + " and " + bg.getActor(3823) + " is: " + bg.getEdge(841, 3823));
		StdOut.println("Edge connecting " + bg.getActor(1) + " and " + bg.getActor(1534) + " is: " + bg.getEdge(1, 1534));
	}
}
