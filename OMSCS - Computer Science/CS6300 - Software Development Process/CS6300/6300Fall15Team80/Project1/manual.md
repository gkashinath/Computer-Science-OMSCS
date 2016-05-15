#NAME

**WC** - average sentence length calculator

#SYNOPSIS

`java -jar WC.jar [-d delimiters] [-l min-length] file`

`java -jar WC.jar [-d delimiters] file [-l min-length]`

`java -jar WC.jar file [-d delimiters] [-l min-length]`

#DESCRIPTION

**WC** computes the average sentence length of a given text file. Each sentence is separated by a delimiter in the specified delimiter set. A word must have the minimum length to be counted, and a sentence must have at least one word to be counted. The average sentence length is rounded down to a whole number.

#OPTIONS

`-d delimiters` : *delimiters* Is a string representing the set of delimiters to use. If this option is not used the program behaves as though `-d .?!:;` has been passed.

`-l min-length` : *min-length* is the minimum length that the words must be. If this option is not used the program behaves as though `-l 4` has been passed.

#REQUIREMENTS

This program requires that the user have JRE 7 or higher. It also requires the that the user have access to a command line terminal and a file system.

#EXAMPLES
    
`java -jar WC.jar myfile1.txt` : Computes the average sentence length of `myfile.txt` using default options.

`java -jar WC.jar -d ,.!? path/to/file.txt` : Computes the average sentence length of `path/to/file.txt` using `,`, `.`, `!` and `?` to separate sentences.

`java -jar WC.jar -l 9 article.txt -d ,` : Computes the average sentence length of `article.txt` counting only words that are at least 9 characters long and where sentences are separated by a `,`.

#ERRORS

`Error: Ill-formed command` : The program and arguments on the command-line are not correctly specified.

`Error: Unable to read input file` : The file exists but is not readable.

`Error: Unable to process input file` : The file doesn't exist.

`Error: Program terminated unexpectedly` : Some unexpected error happened.
