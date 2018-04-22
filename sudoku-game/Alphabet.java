package homework3;

import java.io.Serializable;
import java.util.*;

public class Alphabet implements Serializable
{

	private static final long serialVersionUID = -6906385698060963797L;
	Scanner scan = new Scanner(System.in);
	public String alphabet;
	
	/**
	 * Initialize the alphabet with the default values
	 */
	
	//constructors
	public Alphabet()
	{
		alphabet="123456789";//the default value
	}
	
	public Alphabet(char [] input) throws InvalidAlphabetException 
	{
		//error handling 1: duplicate input
		if(isDuplicate(input)) {
			throw new InvalidAlphabetException();
		}
			
		//error handling 2: length<9
		if(input.length!=(Board.DIMENSION*Board.DIMENSION)) {
			throw new InvalidAlphabetException();
		}
		
		//construct new alphabet
		alphabet =new String(input);
	}
	
	//methods
	
	//get the position of a character in the alphabet
	//return -1 if not included
	public int getPosition(char c)
	{
		return alphabet.indexOf(c);
	}
	
	//check if the character is inside the alphabet
	public boolean isValidChar(char c)
	{
		return alphabet.contains(" "+c);
		//since contains(CharSequence), add whitespace to be legal
	}
	
	//converts an integer into a character for display
	public Character get(int pos) 
	{
		return alphabet.charAt(pos);
	}
	
	//copy the content from another alphabet
	public void copy(Alphabet alphabet2) 
	{
		alphabet=alphabet2.alphabet;
	}
	
	//check if duplicate
	public boolean isDuplicate(char[] input) 
	{
		for(int i=0;i<input.length;i++) {
			for(int j=i+1;j<input.length;j++) {
				if(i!=j&&input[i]==input[j]) {
					return true;
				}
			}
		}
		return false;
	}
}
