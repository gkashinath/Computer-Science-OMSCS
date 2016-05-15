/**
 * 
 */
package edu.gatech.seclass;

/**
 * @author Gandharv Kashinath
 *
 */
public class MyString implements MyStringInterface {
	
	//Exceptions
	public static final String INDEX_LESS_THAN_1 = "Input index is < 1";
	public static final String START_GREATER_THAN_END_INDEX = "Input start index is > end index";
		
	private String curString;
	public MyString() {}
	/*
	 * Sets the value of the current string.
	 * 
	 * @param string
	 *            The value to be set
	 */
	public void setString(String string) {
		curString = string.trim();
	}

	/*
	 * Returns the current string
	 * 
	 * @return Current string
	 */
	public String getString() {
		return curString;
	}

	/*
	 * Returns a string that consists of all the consonants in the current string,
	 * in the same order and with the same case.
	 * ("y" is considered a consonant)
	 * 
	 * @return Consonants in the current string
	 */
	public String getConsonants() {
		char c[]=curString.toCharArray();
		StringBuilder conso = new StringBuilder();
		String consostr="";
		for (int i=0;i<c.length;i++)
		{
			if(Character.isLetter(c[i]))
			{
				if(c[i]!='a' && c[i]!='e' && c[i]!='i' && c[i]!='o' && c[i]!='u' &&
						c[i]!='A' && c[i]!='E' && c[i]!='I' && c[i]!='O' && c[i]!='U'){
					consostr=Character.toString(c[i]);
					conso.append(consostr);
				}
					
			}
		}
		System.out.println( "All the consonants in the above string is: "+ conso);
		return conso.toString();
	}

	/*
	 * Returns the number of consonants in the current string
	 * ("y" is considered a consonant)
	 * 
	 * @return Number of consonants in the current string
	 */
	@Override
	public int numberOfConsonants() {
		char c[]=curString.toCharArray();
		int nconso;
		nconso=0;
		for (int i=0;i<c.length;i++)
		{
			if(Character.isLetter(c[i]))
			{
				if(c[i]!='a' && c[i]!='e' && c[i]!='i' && c[i]!='o' && c[i]!='u' &&
						c[i]!='A' && c[i]!='E' && c[i]!='I' && c[i]!='O' && c[i]!='U'){
					nconso++;
				}
					
			}
		}
		System.out.println( "The number of consonants in the above string is: "+ nconso);
		return nconso;
	}

	/*
	 * Returns the character at position "position" in the string, with 1 being the
	 * first position
	 * 
	 * @param position
	 *            Position of the character to return
	 * @return The character at position "position"
	 * @throws IllegalArgumentException
	 *             If "position" is invalid (e.g., "position" <= 0)
	 * @throws IllegalIndexException
	 *             If the string has less than "position" characters in it
	 */

	public char getCharacter(int position) throws IllegalArgumentException, IllegalIndexException {
		char c[]=curString.toCharArray();
		char charpos;
		int strlen=curString.length();
		
		if(position<=0){
			System.out.println( "Invalid position request (must be > 0): " + position);
			throw new IllegalArgumentException(INDEX_LESS_THAN_1);
		}
		if(strlen<position){
			System.out.println( "The length of the string is " + strlen + ", which is less than the position requested, "+ position);
			throw new IllegalIndexException();
		}
		
        charpos=c[position-1];
		System.out.println( "The character at position, " + position + ", is: "+ charpos);
		return charpos;
	}

	/*
	 * Changes the case of the alphabetic characters in the current string,
	 * between startPosition and endPosition (included), with 1 being the first
	 * position:
	 * - lower case characters are replaced with the corresponding upper case characters
	 * - upper case characters are replaced with the corresponding lower case characters
	 * 
	 * @param startPosition
	 *            Position of the first character to consider
	 * @param endPosition
	 *            Position of the last character to consider
	 * @return
	 * @throws IllegalArgumentException
	 *             If either "startPosition" or "endPosition" are invalid (e.g.,
	 *             "startPosition" <= 0, "endPosition" <= 0, "startPosition" > "endPosition")
	 * @throws IllegalIndexException
	 *             If the string has less than "endPosition" characters in it
	 */
	public void flipCaseInSubstring(int startPosition, int endPosition)
			throws IllegalArgumentException, IllegalIndexException {
		char c[]=curString.toCharArray();
		int strlen=curString.length();
		
		if(startPosition<=0){
			System.out.println( "Invalid start position request (must be > 0): " + startPosition);
			throw new IllegalArgumentException(INDEX_LESS_THAN_1);
		}else if(endPosition<=0){
			System.out.println( "Invalid end position request (must be > 0): " + endPosition);
			throw new IllegalArgumentException(INDEX_LESS_THAN_1);
		}else if(startPosition>endPosition){
			System.out.println( "Invalid end position request (start > end position):");
			throw new IllegalArgumentException(START_GREATER_THAN_END_INDEX);
		}
		if(strlen<endPosition){
			System.out.println( "The length of the string is " + strlen + ", which is less than the end position requested, "+ endPosition);
			throw new IllegalIndexException();
		}
		
		for (int i = startPosition-1; i <=endPosition-1;i++)
		{
			if(Character.isLetter(c[i]))
			{
				if(Character.isUpperCase(c[i]))
				{
					c[i] = Character.toLowerCase(c[i]);
				}
				else if (Character.isLowerCase(c[i]))
				{
					c[i] = Character.toUpperCase(c[i]);
				}
			}
		}
		curString=String.valueOf(c);
		System.out.println( "Case flipped string: " + curString);
	}

}
