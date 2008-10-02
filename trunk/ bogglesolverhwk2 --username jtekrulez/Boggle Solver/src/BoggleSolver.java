/**
 *BoggleSolver class
 *	This class holds the main execution code,  
 **/
public class BoggleSolver {
	private Dictionary dictionary;
	private int validPaths = 0;
	public BoggleSolver(String fileDir, String letters){
		try {
			dictionary = new Dictionary(fileDir);			
		} catch (Exception e) {
			System.out.println("Error: "+e);
			System.exit(0);
		}
		BoggleBoard bBoard = new BoggleBoard(letters);
		bBoard.printBoard();
		System.out.println("\n================================================================");
		System.out.println("|                Searching for possible words                  |");
		System.out.println("================================================================\n");
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
