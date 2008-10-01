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
	private int length;
    /**
     * Constructor.
     */
    public Word() {
    	word = new LinkedList<String>();
    	iterator = word.iterator();
    	length = 0;
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
    	length++;
    	return makeCopy();
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
    	StringBuffer str = new StringBuffer();
    	while(iterator.hasNext()){
    		str.append(iterator.next());
    	}
    	return str.toString();
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

