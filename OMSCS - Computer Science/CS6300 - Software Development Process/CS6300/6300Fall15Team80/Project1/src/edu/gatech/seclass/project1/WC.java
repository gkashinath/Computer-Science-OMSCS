package edu.gatech.seclass.project1;
import java.io.File;
import edu.gatech.seclass.project1.ASL;

public class WC {// extends ASL {

	public WC() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		Boolean debugFlag=false;
		ASL myASL = new ASL();
		int wordLength = -1;
		int averageSentence = 0;
		String delimiter = "";
		String filename = "";
		
		/* Make sure argument list is not empty */
		if (args.length <= 0){
			System.err.println("Error: Ill-formed command!");
			throw new IllFormedCommandException();
		} else if (args.length >= 6) {
			System.err.println("Error: Program terminated unexpectedly");
			throw new UnexpectedErrorException();			
		}
		
		/* Loop through input arguments to process them.
		 * NOTE: The arguments can be in any order. */
		int i=0;
		while (i < args.length) {
			String curI = args[i];
			
			if (curI.startsWith("-")) {
				String nextI = i+1 < args.length ? args[i+1] : null;
				if (curI.equals("-l")) {
					// Process the word count flag and throw exception if errors detected.
					if (nextI == null || nextI.startsWith("-")) {
						System.err.println("Error: Ill-formed command!");
						throw new IllFormedCommandException();
						} else {
							try {
								wordLength = Integer.parseInt(nextI);
								i++;
								} catch (NumberFormatException e) {
									System.err.println("Error: Ill-formed command!");
									throw new IllFormedCommandException();
									}
							}
					} else if (curI.equals("-d")) {
						// Process the word count flag and throw exception if errors detected.
						// Check if any of the delimiters are alphabets.
						char[] chars = args[i+1].toCharArray();
						boolean isAlpha=false;
						for (char c:chars) {
							if(Character.isLetter(c)) {
					            isAlpha=true;
							}
						}
						if (nextI == null || nextI.startsWith("-") || isAlpha) {
							System.err.println("Error: Ill-formed command!");
							throw new IllFormedCommandException();
							} else {
								delimiter = nextI;
								i++;
								}
						}
				} else {
					// Process the input file name and throw exceptions if errors are detected.
					filename = curI;
					File file = new File(filename);
					if (!file.exists()) {
						System.err.println("Error: Unable to process input file!");
						throw new FileDoesNotExistException();
						}
					if (!file.isFile() || !file.canRead()){
						System.err.println("Error: Unable to read input file!");
						throw new FileNotReadableException();
						}
					} 
			i++;
			}
		/* Set defaults if entries of arguments are missing*/
		if(delimiter == ""){
			delimiter = ".?!:;";
		}
		if(wordLength <= -1){
			wordLength = 4; 
		}
		
		/*Debug prints*/
		if (debugFlag) {System.out.println("Delimiters: " + delimiter);};
		if (debugFlag) {System.out.println("Min. word length = " + wordLength);};
		if (debugFlag) {System.out.println("The file name is: " + filename);};
		
		/* Calculate and print average number of words per sentence*/
		averageSentence = myASL.averageSentenceLength(filename, delimiter, wordLength);
		if (debugFlag) {System.out.println("The average number of words per sentence is: " + averageSentence);};
		
		System.out.println(averageSentence);
	}
}