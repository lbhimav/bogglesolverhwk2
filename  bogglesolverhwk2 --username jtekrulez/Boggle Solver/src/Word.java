import java.util.LinkedList;

/**
 * Word
 * For CS3134 F08 HW2
 *
 * This class will represent a Word under construction.  Hint: Use a
 * LinkedList of Strings to represent the word.  (Each string will be
 * 1 letter long, of course.)
 */
public class Word {
	private LinkedList<String> word;
	private int length;
    /**
     * Constructor.
     */
    public Word() {
    	word = new LinkedList<String>();
    	length = 0;
    }
    /**
     * Add a letter to the Word.
     */
    public void addLetter (String letter) {
    	word.add(letter);
    	length++;
    }
    /**
     *Returns length of word 
     **/
    public int length(){
    	return length;
    }
    /**
     * "Flatten" the word back into a String.
     */
    public String toString() {
    	String tempStr = "";
    	for(int i=0; i< word.size(); i++){
    		tempStr += word.get(i);
    	}
    	return tempStr;
    }

    /**
     * Make a copy of the word.  This is important for getting your
     * recursion to work correctly (pass by value instead of by
     * reference).
     */
    public Word makeCopy() {
    	String str = this.toString();
    	Word newWord = new Word();
    	for(int i = 0; i< str.length(); i++){
    		newWord.addLetter(""+str.charAt(i));
    	}
    	return newWord;
    }

}

