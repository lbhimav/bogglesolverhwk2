import java.util.LinkedList;

/**
 * BoggleBoard
 * For CS3134 F08 HW2
 *
 * This is the class that will represent a Boggle board at a certain
 * state during "play".  It keeps track of where the letters are on
 * the board, as well as which are taken/untaken.
 */
public class BoggleBoard {

    /**
     * Constructor.  It takes a string of letters.  You should make
     * sure the string has a square number of letters, and then
     * construct your board from the string by putting one letter in
     * each square.  
     */
    public BoggleBoard (String letters) throws Exception {
	// ...
    }

    /**
     * Return the dimensionality of the board.
     */
    public int dimensions(){
	// ...
    }

    /**
     * Print out the board.
     */
    public void printBoard () {
	// ...
    }

    /**
     * Make a copy of the board.  This is important for getting your
     * recursion to work correctly (pass by value instead of by
     * reference).
     */
    public BoggleBoard makeCopy() throws Exception {
	// ...
    }

    /**
     * Return the letter at a particular location.
     */
    public String letterAtLocation(BoardLocation location) {
	// ...
    }

    /**
     * Mark a particular letter on the board as being taken.
     */
    public void markAsTaken(BoardLocation takenLocation) {
	// ...

    }

    /**
     * Return a list of board locations that are (1) on the board, (2)
     * adjacent to a given location, and (3) not already taken.
     */
    public LinkedList<BoardLocation> getAdjacentUntakens(BoardLocation location)  {
	// ...
    }
		

}