package edu.gatech.seclass.project1;

import java.io.FileInputStream;
import java.io.InputStream;

public class ASL {
		
	public int averageSentenceLength (String text_file_name, String delimiters, int minLetters) throws Exception{
		
		Boolean debugFlag=false;
		//delimiters = ".?!:; and EOF
		//minimum word length = 4 and above
		//round average down
		//do not include sentences with 0 words
		
		//Process
		//obtain filename
		//obtain delimiter value if present
		//obtain minimum word length is present
		//loop through each character in the file
		
		//if character is not a delimiter, whitespace, or EOF, it is an acceptable character
		//the system is reading a word and starts to count the number of chracters
		
		//if the charafter is a whitespace.  The word is formed.  If the word 
		//length is >= minword lenght the number of total words is increased by one
		
		//if the character is a delimiter or EOF.  The word is formed and the sentence is formed.
		//if the word length is >= minword length, the number of words in increased by one.
		//The number of sentences is increased by one
		
		//if the character is EOF, the average is calculated and rounded to the nearest integer
		//and returned		
		
		// This prints out the given test file assuming that it is the first argument
		//System.out.println("Text File: " + text_file_name);
		
		//This opens an input stream for the given text file
		InputStream textFile = new FileInputStream(text_file_name);
		int c;
		int numberOfLetters = 0;
		int numberOfWords = 0;
		int numberOfCharacters = 0;
		int numberOfWordsInCurrentSentence = 0;
		
		int numberOfSentences = 0;
		char currentChar;
		String currentWord = "";
		int averageWordsPerSentence = 0;
		Boolean inFile = true;
		
		
		while(inFile){
			c = textFile.read();
			currentChar = (char)c;
			numberOfCharacters++;
			
			//if (debugFlag) {System.out.println(currentChar);};
			
			//DETECT IN WORD
			if ((delimiters.indexOf(currentChar)==-1) && (currentChar != ' ') && (delimiters.indexOf(currentChar)==-1) && (c !='\r') && (c !='\t') && (c !='\n')) {
				numberOfLetters++;			
				currentWord = currentWord+currentChar;
				//if (debugFlag) {System.out.println("character detected");};
			}
			
			//DETECT END OF WORD - space found 
			if ((currentChar == ' ') || (c =='\r') || (c =='\t') || (c =='\n')) {
				if (debugFlag) {System.out.print(currentWord);};
				if (numberOfLetters >= minLetters) {
					numberOfWords++;
					numberOfWordsInCurrentSentence++;
					numberOfLetters =0;
					//if (debugFlag) {System.out.println("acceptable word found");};
					//if (debugFlag) {System.out.print("total words = ");};
					//if (debugFlag) {System.out.println(numberOfWords);};
				}
				if (debugFlag) {System.out.print(" ");};
				if (debugFlag) {System.out.println(numberOfWords);};
				numberOfLetters =0;
				currentWord="";
				//if (debugFlag) {System.out.println("end of word");};
			}
						
			//DETECT END OF SENTENCE - delimiter found
			if ((delimiters.indexOf(currentChar)>=0) || (c == -1)){
				if (debugFlag) {System.out.print(currentWord);};
				if (numberOfLetters >= minLetters) {
					numberOfWords++;
					numberOfWordsInCurrentSentence++;
					numberOfLetters =0;
					//if (debugFlag) {System.out.println("acceptable word found");};			
				}
				if (debugFlag) {System.out.print(" ");};
				if (debugFlag) {System.out.println(numberOfWords);};
				if (numberOfWordsInCurrentSentence>0){numberOfSentences++;};
				numberOfWordsInCurrentSentence=0;
				numberOfLetters =0;
				currentWord="";
				if (debugFlag) {System.out.println("end of sentence");};
				if (debugFlag) {System.out.print("total sentences = ");};
				if (debugFlag) {System.out.println(numberOfSentences);};	
			}
			
			if (c == -1) {inFile = false;};
			
			if ((c==-1) && (numberOfCharacters == 1)) {inFile = false; numberOfWords = 0; numberOfSentences = 0;};

		}
		
		if (debugFlag) {System.out.println("The number of words is:");};
		if (debugFlag) {System.out.println(numberOfWords);};
		if (debugFlag) {System.out.println("The number of sentences is:");};
		if (debugFlag) {System.out.println(numberOfSentences);};
		
		//Check if no words or no sentences are found - protect against div-by-zero
		if ((numberOfWords == 0) || (numberOfSentences == 0)){
			averageWordsPerSentence = 0;
		}
		else {
			averageWordsPerSentence = numberOfWords/numberOfSentences;
		}
		
		if (debugFlag) {System.out.println("The average number of words is:");};
		if (debugFlag) {System.out.println(averageWordsPerSentence);};
						
		textFile.close();
		
		return averageWordsPerSentence;
	}

}
