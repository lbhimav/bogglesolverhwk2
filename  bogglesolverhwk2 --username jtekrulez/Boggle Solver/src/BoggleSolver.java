/**
 *BoggleSolver class
 *	This class holds the main execution code,  
 **/
public class BoggleSolver {
	private Dictionary dictionary;
	public BoggleSolver(String fileDir, String letters){
		try {
			dictionary = new Dictionary(fileDir);
			BoggleBoard bBoard = new BoggleBoard(letters);
			Word wordSoFar = new Word();
			bBoard.printBoard();
			for(int i = 0; i<bBoard.dimensions(); i++){
				for(int j=0; j<bBoard.dimensions(); j++){
					wordSoFar.addLetter(bBoard.letterAtLocation(new BoardLocation(i,j)));			
				}
			}
		} catch (Exception e) {
			System.out.println("Error: "+e);
			System.exit(0);
		}
	}
	
	public int recurseWords(Word wordSoFar, BoggleBoard bBoard, BoardLocation loc){
		if(wordSoFar.length() >= 3 && dictionary.wordsExistThatStartWith(wordSoFar.toString())){
			return 0;
		}
		if(wordSoFar.length() >= 3 && dictionary.containsWord(wordSoFar.toString())){
			System.out.println("Word: "+wordSoFar.toString());
		}
		BoardLocation[] adjLocations = bBoard.getAdjacentUntakens(loc);
		for(int i=0; i<adjLocations.length; i++){
			recurseWords(wordSoFar.addLetter(bBoard.letterAtLocation(adjLocations[i])), bBoard.makeCopy(), adjLocations[i]);
		}
		return 0;
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
			System.out.println("Please rerun program");
			System.exit(0);
		}
	}
}
