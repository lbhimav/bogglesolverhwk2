/**
 *BoggleSolver class
 *	This class holds the main execution code, and the recursive method used to find all of the words. 
 *	The main method takes in the parameters of the programs and check to make sure that they are valid
 *	only. All other code is executed by the constructor and the recursive method that is called by the constructor.
 *
 *Jack Yuan -- jly2104 
 *
 *This code can be freely distributed and copied and modified for all uses with the exception of assignments for class. 
 **/
public class BoggleSolver {
	private Dictionary dictionary; // Dictionary file that is a variable open to the class
	private int validPaths = 0; // Checks how many total valid paths there are in the program
	
	/**
	 *	BoggleSolver Constructor
	 *	The constructor takes in two parameters, the directory of the dictionary file and the string
	 *of letters used to make the boggle board. The basic board setup is done in this method and is
	 *then handed down to the recursive method to look for all available words on the board. 
	 **/	
	public BoggleSolver(String fileDir, String letters){
		/**
		 *This try catch is only used to make sure that the dictionary class can read the file directory 
		 **/		
		try {
			dictionary = new Dictionary(fileDir);			
		} catch (Exception e) {
			System.out.println("Error: "+e);
			System.exit(0);
		}
		System.out.println("\n\nCreating Boggle board.....\n\n");
		BoggleBoard bBoard = new BoggleBoard(letters); // creating boggle board
		bBoard.printBoard();
		System.out.println("\n================================================================");
		System.out.println("|                Searching for possible words                  |");
		System.out.println("================================================================\n");
		/**
		 *This nested for loop loops through all of the letters on the Boggle board and running the recurse on all of them 
		 **/		
		for(int i = 0; i<bBoard.dimensions(); i++){
			for(int j=0; j<bBoard.dimensions(); j++){
				Word wordSoFar = new Word();
				BoardLocation curLoc = new BoardLocation(i,j);
				wordSoFar.addLetter(bBoard.letterAtLocation(curLoc));
				Word newWord = wordSoFar.makeCopy();
				BoggleBoard newBoard = bBoard.makeCopy();
				newBoard.markAsTaken(curLoc);
				recurseWords(newWord, newBoard, curLoc);
			}
		}
		System.out.println("\n================================================================");
		System.out.println("                    There are "+validPaths+" valid paths.            ");
		System.out.println("================================================================\n");
	}
	/**
	 *The recursive method takes in three parameters, the word so far (passed by value),
	 * the current state of the boggle board (passed by value), and the board location. The 
	 * method first takes a string and set that equal to the toString of wordSoFar. Then marks
	 * the current location as taken and creates an array of adjacent locations. Then the program
	 * checks through to make sure that the current wordSoFar still exists in the dictionary, if not 
	 * then break out of the method. Otherwise first check if the word length is 3 or greater and if it 
	 * exists in the dictionary. If it does then print it to the screen and increment valid paths. Then using
	 * the array of adjacent locations, run it through a loop to recurse through all of those locations to check 
	 * for more words. 
	 **/
	public void recurseWords(Word wordSoFar, BoggleBoard bBoard, BoardLocation loc){
		String str = wordSoFar.toString();
		bBoard.markAsTaken(loc);
		BoardLocation[] adjLoc = bBoard.getAdjacentUntakens(loc);
		if(wordSoFar.length() > 2 && dictionary.containsWord(str)){
			System.out.println("Word: "+str);
			validPaths++;
		}
		if(!dictionary.wordsExistThatStartWith(str)){
			return;
		}else{
			for(int i =0; i< adjLoc.length; i++){
				Word newWord = wordSoFar.makeCopy();
				newWord.addLetter(bBoard.letterAtLocation(adjLoc[i]));
				recurseWords(newWord, bBoard.makeCopy(), adjLoc[i]);
			}
		}		
	}
	/**
	 *The main method
	 *This main method is used to only check if the input is correct before passing the parameters to
	 *the constructor and generally letting the constructor take care of everything else. First thing to 
	 *check was to make sure that there were actually two parameters, then check to make sure that the first
	 *parameter was a filepath that ended in .txt to make sure that dictionary class could use it. Then check to
	 *make sure that the parameter of strings was a perfect square.
	 **/
	public static void main(String[] args) {
		try{
			if(args.length != 2){
				System.out.println("You did not enter enough parameters, please rerun the program.");
				System.exit(0);
			}
			if(!args[0].endsWith(".txt") || args[1].endsWith(".txt")){
				System.out.println("Please re-execute program with dictionary file as first parameter and string of letters as second parameter sperated by a space. ");
				System.exit(0);
			}
			if((double)(int)Math.sqrt(args[1].length()) == Math.sqrt(args[1].length())){
				new BoggleSolver(args[0], args[1]);
			}
		}catch(Exception e){
			System.out.println("Error: "+e);
			e.printStackTrace();
			System.out.println("Please rerun program");
			System.exit(0);
		}
	}
}
