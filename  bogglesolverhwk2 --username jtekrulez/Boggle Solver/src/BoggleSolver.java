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
		System.out.println("Creating boggle board...");
		BoggleBoard bBoard = new BoggleBoard(letters);
		bBoard.printBoard();
		//for(int i = 0; i<bBoard.dimensions(); i++){
			//for(int j=0; j<bBoard.dimensions(); j++){
				Word wordSoFar = new Word();
				BoardLocation curLoc = new BoardLocation(0,0);
				Word newWord = wordSoFar.makeCopy().addLetter(bBoard.letterAtLocation(curLoc));
				bBoard.markAsTaken(curLoc);
				recurseWords(newWord, bBoard.makeCopy(), curLoc);
			//}
		//}
		System.out.println("There are "+validPaths+" valid paths.");
	}
	
	public void recurseWords(Word wordSoFar, BoggleBoard bBoard, BoardLocation loc){
		String str = wordSoFar.toString();
		System.out.println(str);		
		
		if(!dictionary.wordsExistThatStartWith(str)){
			return;
		}
		
		if(wordSoFar.length() > 2 && dictionary.containsWord(str)){
			System.out.println("Word: "+str);
			++validPaths;
		}
		
		BoardLocation[] adjLocations = bBoard.getAdjacentUntakens(loc);
		
		for(int i=0; i<adjLocations.length; i++){
			wordSoFar.addLetter(bBoard.letterAtLocation(adjLocations[i]));
			bBoard.markAsTaken(adjLocations[i]);
			recurseWords(wordSoFar.makeCopy(), bBoard.makeCopy(), adjLocations[i]);
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
