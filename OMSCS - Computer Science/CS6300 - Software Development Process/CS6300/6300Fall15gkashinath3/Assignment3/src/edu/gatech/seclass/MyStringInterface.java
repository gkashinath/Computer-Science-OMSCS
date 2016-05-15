package edu.gatech.seclass;

/**
 * This is an interface for a simple class that represents a string, defined
 * as a sequence of characters.
 * 
 * @author Rufus
 * 
 */
public interface MyStringInterface {

	/**
	 * Sets the value of the current string.
	 * 
	 * @param string
	 *            The value to be set
	 */
	void setString(String string);

	/**
	 * Returns the current string
	 * 
	 * @return Current string
	 */
	String getString();

	/**
	 * Returns a string that consists of all the consonants in the current string,
	 * in the same order and with the same case.
	 * ("y" is considered a consonant)
	 * 
	 * @return Consonants in the current string
	 */
	String getConsonants();

	/**
	 * Returns the number of consonants in the current string
	 * ("y" is considered a consonant)
	 * 
	 * @return Number of consonants in the current string
	 */
	int numberOfConsonants();

	/**
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
	char getCharacter(int position)
			throws IllegalArgumentException, IllegalIndexException;

	/**
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
	void flipCaseInSubstring(int startPosition, int endPosition)
			throws IllegalArgumentException, IllegalIndexException;
}
