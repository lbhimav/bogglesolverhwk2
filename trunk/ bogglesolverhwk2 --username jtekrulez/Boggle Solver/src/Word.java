import java.util.Iterator;
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
	private Iterator<String> iterator;
    /**
     * Constructor.
     */
    public Word() {
    	word = new LinkedList<String>();
    	iterator = word.iterator();
    }
    private Word(LinkedList<String> w){
    	word = w;
    	iterator = w.iterator();
    }
    /**
     * Add a letter to the Word.
     */
    public Word addLetter (String letter) {
    	word.add(letter);
    	return makeCopy();
    }
    /**
     *Returns length of word 
     **/
    public int length(){
    	return toString().length();
    }
    /**
     * "Flatten" the word back into a String.
     */
    public String toString() {
    	String str = "";
    	while(iterator.hasNext()){
    		str += iterator.next();
    	}
    	return str;
    }

    /**
     * Make a copy of the word.  This is important for getting your
     * recursion to work correctly (pass by value instead of by
     * reference).
     */
    public Word makeCopy() {
    	return new Word(word);
    }

}

