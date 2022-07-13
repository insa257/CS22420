package a04;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.StdOut;

// Models a board in the 8-puzzle game or its generalization.
public class Board {
    private int[][] blocks;
    private int N;
    private int hamming;
    private int manhattan;
    
    
    // Construct a board from an N-by-N array of tiles, where 
    // tiles[i][j] = tile at row i and column j, and 0 represents the blank 

    public Board(int[][] blocks) {
        this.N = blocks.length;
        this.blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                this.blocks[i][j] = blocks[i][j]; 
        this.hamming = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] == 0)
                    continue;
                if (this.blocks[i][j] != i * N + j + 1)
                    this.hamming++;
            }
        }
        this.manhattan = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //if not in position
                if (blocks[i][j] != i * N + j + 1 && blocks[i][j] != 0) {
                    int expi = (blocks[i][j] - 1) / N; //row gap
                    int expj = (blocks[i][j] - 1) - (expi * N); //column gap
                    this.manhattan += Math.abs(i - expi) + Math.abs(j - expj);
                }
            }
        }
    }

    // Tile at row i and column j.
    public int tileAt(int i, int j) {
        return blocks[i][j];
    }
    
    // Size of this board.
    public int size() {
        return N * N;
    }

    // Number of tiles out of place.
    public int hamming() {
        return this.hamming;
    }

    // Sum of Manhattan distances between tiles and goal.
    public int manhattan() {
        return this.manhattan;
    }

    // Is this board the goal board?
    public boolean isGoal() {
        return blankTile() == N * N && inversions() == 0;
    }

    // Is this board solvable?
    public boolean isSolvable() {
        if (N % 2 != 0) {
            if (inversions() % 2 == 0) { //if inversions even
                return true;
            }
            return false;
        }
        else {
            int sum = ((blankTile() - 1) / N) + inversions();
            if (sum % 2 == 0) {
                return false;
            }
                return true;
        }
    }

    // Does this board equal that?
    public boolean equals(Board that) {
        if (this.N != that.N)
            return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.blocks[i][j] != that.blocks[i][j])
                    return false; //return false if not equal that
            }
        }
        return true;
    }

    // All neighboring boards.
    public Iterable<Board> neighbors() {
        LinkedQueue<Board> q = new LinkedQueue<Board>();
        int[][] neighbor;
        int i = (blankTile() - 1) / N; //this is the row number
        int j = (blankTile() - 1) % N; //this is the column number
        int tempVal; //holds a temporary value
        if (i - 1 >= 0 && i - 1 < N - 1) { //boundary for checking north
            neighbor = cloneTiles(); //clone into neighbor array
            tempVal = neighbor[i][j]; //swap neighbor[i][j] with neighbor[i - 1][j]
            neighbor[i][j] = neighbor[i - 1][j];
            neighbor[i - 1][j] = tempVal;
            Board board = new Board(neighbor); //board object from clone
            q.enqueue(board); //enqueue board into q
        }
        if (j + 1 > 0 && j + 1 < N) { //boundary for checking east
            neighbor = cloneTiles(); //clone into neighbor array
            tempVal = neighbor[i][j]; //swap neighbor[i][j] with neighbor[i][j + 1]
            neighbor[i][j] = neighbor[i][j + 1];
            neighbor[i][j + 1] = tempVal;
            Board board = new Board(neighbor);
            q.enqueue(board);
        }
        if (i + 1 > 0 && i + 1 < N) { //boundary for checking south
            neighbor = cloneTiles(); //clone into neighbor array
            tempVal = neighbor[i][j]; //swap neighbor[i][j] with neighbor[i + 1][j]
            neighbor[i][j] = neighbor[i + 1][j];
            neighbor[i + 1][j] = tempVal;
            Board board = new Board(neighbor); //board from object clone
            q.enqueue(board); //enqueue board into q
        }
        
        if (j - 1 >= 0 && j - 1 < N - 1) { //boundary for checking west
            neighbor = cloneTiles(); //clone into neighbor array
            tempVal = neighbor[i][j]; //swap neighbor[i][j] with neighbor[i][j - 1]
            neighbor[i][j] = neighbor[i][j - 1];
            neighbor[i][j - 1] = tempVal;
            Board board = new Board(neighbor);
            q.enqueue(board);
        }
        return q;
    }

    // String representation of this board.
    public String toString() {
        //String s = "[" + N + "\n";
    	String s = N + "\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s += String.format("%2d", blocks[i][j]);
                if (j < N - 1) {
                    s += " ";
                }
            }
            if (i < N - 1) {
                s += "\n";
            }
        }
        //s += "]";
        return s;
    }

    // Helper method that returns the position (in row-major order) of the 
    // blank (zero) tile.
    private int blankTile() {
        int k = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                k++;
                if (blocks[i][j] == 0) {
                    return N * i + j + 1;
                }
            }
        }
        return -1;
    }

    // Helper method that returns the number of inversions.
    private int inversions() {
        int count = 0;
        int p1 = 0;
        int p2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                p1++;
                for (int k = 0; k < N; k++) {
                    for (int l = 0; l < N; l++) {
                        p2++;
                        if (blocks[i][j] == 0 || blocks[k][l] == 0)
                            continue;
                        else if (p1 < p2 && blocks[i][j] > blocks[k][l]) {
                            count++;
                        }
                    }
                }
                p2 = 0;
            }
        }
        return count;
    }

    // Helper method that clones the tiles[][] array in this board and 
    // returns it.
    private int[][] cloneTiles() {
        int[][] s = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                s[i][j] = blocks[i][j];
        return s;
    }
   public static void main(String[] args) { 
	   
   }
}