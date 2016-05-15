# **Requirements Document -- Team 80**

##1 User Requirements

###1.1 Software Interfaces

1. System interacts with an invoking shell/command prompt for I/O.
2. System interacts with file system to read file contents.
3. System interacts with the Java Runtime Environment (JRE) to provide functionality.

###1.2 User Interfaces

The system is a command-line tool that is executed by the user with a file name as an argument and optional flags to specify different parameters. The system will then open the specified file and read it, compute the average number of words per sentence and print that value in the invoking command prompt, which the user can then read on the command-line interface.

###1.3 User Characteristics

The users of the system are university students with a wide range of experience with computers and technical expertise. Some students will not understand more than the basics of computers while others may be programmers.

##2 System Requirements

###2.1 Terms and Definitions

- The **input** is a raw ASCII text file.
- The input is composed of **sentences**.
- A sentence is composed of a sequence of 1 or more **words** separated from one another by white space characters.
- Sentences are terminated by **delimiters**.
- A delimiter is, by default, one of the following characters: `.` (period), `?` (question mark), `!` (exclamation mark), `:` (colon) or `;` (semicolon).
- EOF is always a delimiter.
- A word is a string of any characters that are not white space characters or delimiters that has at least the **minimum word length**.
- The minimum word length is, by default, 4.
- The **output** is the average number of words per sentence of the input rounded down to the nearest integer.
- If the input has no sentences the output is 0.
 
###2.2 Functional Requirements

1. The system shall run on a command line terminal.
  1. The system shall take the path to the input via as a mandatory first argument in command-line argument passing.
    1. For example, the following command runs the system with input located in path `from/path/hello.txt`: `WC from/path/hello.txt`
  2. The system shall print the output to the command-line terminal.
    1. The output should consists only of a number with no other text.
  3. The system shall terminate after printing out an error or the output.
2. The system must allow optional additional flags to be passed in the command line.
  1. If specified, the `-d` flag allows the user to specify which characters should be delimiter.
    1. The `-d` flag is followed by white space and then a set of the delimiter characters without white space between them.
	2. For example, the following  command runs the system on `examplefile.txt`, making `a`, `b` and space (assuming that the shell treats `\` as an escape character) a delimiter: `WC examplefile.txt -d a\ b`
	3. If a delimiter is a white space character as well it behaves as a delimiter.
  2. If specified, the `-l` flag allows the user to specify the minimum word length.
    1. The `-l` flag is followed by by white space and then a number denoting world length. 
	2. For example, the following command runs the system on `examplefile.txt` with a minimum word length of 2: `WC examplefile.txt -l 2`
  3. The combined arguments (mandatory and optional) can be passed in any order.
  4. If a flag is repeated more than once the behaviour is undefined.
3. The system shall print the follow error messages to the standard error pipe:
	1. `Error: Unable to read input file` - if the file exists but is not readable.
	2. `Error: Unable to process input file` - if the file doesn't exist.
	3. `Error: Ill-formed command` - if the program and arguments on the command-line are not correctly specified
	4. `Error: Program terminated unexpectedly` - any other error.

###2.3 Non-Functional Requirements

1. The system shall be named `WC`.
2. The program shall be able to process at least 5000 characters in a second.
3. The system shall be compilable using only the `javac` command with no options.
4. The system shall be runnable with a vanilla Java installation (JDK 1.6, 1.7 or 1.8)
6. The system shall not make any additional unstated assumptions about the user's particular operating environment.
