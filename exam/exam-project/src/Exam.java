import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/*
This is the exam for DM584 - Concurrent Programming, Spring 2024.

Your task is to implement the following methods of class Exam:
- findWordsUniqueToALine;
- lineWithMostA;
- wordWithConsonants;
- wordsWithSubstring.

These methods search text files for particular words.
You must use a BreakIterator to identify words in a text file,
which you can obtain by calling BreakIterator.getWordInstance().
For more details on the usage of BreakIterator, please see the corresponding video lecture in the
course.

The implementations of these methods must exploit concurrency to achieve improved performance.

The only code that you can change is the implementation of these methods.
In particular, you cannot change the signatures (return type, name, parameters) of any method, and
you cannot edit method main.
The current code of these methods throws an UnsupportedOperationException: remove that line before
proceeding on to the implementation.

You can find a complete explanation of the exam rules at the following webpage.

https://github.com/fmontesi/cp-2024/tree/main/exam
*/
public class Exam {
	// Do not change this method
	public static void main(String[] args) {
		checkArguments(args.length > 0,
				"You must choose a command: help, uniqueWords, lineWithMostA, consonants, or substring.");
		switch (args[0]) {
			case "help":
				System.out.println(
						"Available commands: help, uniqueWords, lineWithMostA, consonants, or substring.\nFor example, try:\n\tjava Exam uniqueWords data");
				break;
			case "uniqueWords":
				checkArguments(args.length == 2, "Usage: java Exam.java uniqueWords <directory>");
				List<LocatedWord> uniqueWords = findWordsUniqueToALine(Paths.get(args[1]));
				System.out.println("Found " + uniqueWords.size() + " words");
				uniqueWords.forEach( locatedWord ->
                             System.out.println( locatedWord.word + ":" + locatedWord.filepath + ":" + locatedWord.line) );
				break;
			case "lineWithMostA":
				checkArguments(args.length == 2, "Usage: java Exam.java lineWithMostA <directory>");
				Location location = lineWithMostA(Paths.get(args[1]));
				System.out.println("Line with most occurrences of A found at " + location.filepath + ":" + location.line );
				break;
			case "consonants":
				checkArguments(args.length == 3, "Usage: java Exam.java consonants <directory> <consonants>");
				int consonants = Integer.parseInt(args[2]);
				Optional<LocatedWord> word = wordWithConsonants(Paths.get(args[1]), consonants);
				word.ifPresentOrElse(
                             locatedWord -> System.out.println("Found " + locatedWord.word + " in " + locatedWord.filepath + ":" + locatedWord.line),
						() -> System.out.println("No word found with " + args[2] + " consonants." ) );
				break;
			case "substring":
				checkArguments(args.length == 4, "Usage: java Exam.java substring <directory> <substring> <length>");
				int length = Integer.parseInt(args[3]);
				List<LocatedWord> words = wordsWithSubstring(Paths.get(args[1]), args[2], length);
				if( words.size() > length ) {
					System.out.println( "WARNING: Implementation of wordsWithSubstring computes more than " + args[3] + " words!" );
				}
				words.forEach(loc -> System.out.println(loc.word + ":" + loc.filepath + ":" + loc.line));
				break;
			default:
				System.out.println("Unrecognised command: " + args[0] + ". Try java Exam.java help.");
				break;
		}
	}

	// Do not change this method
	private static void checkArguments(Boolean check, String message) {
		if (!check) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Return the unique words for each file contained in the given directory: the unique words of a file are those that appear only once in that file. This means that a unique word appears only in one of the lines of the file, and it is not repeated in that line.
	 *
	 * This method recursively visits a directory to find text files contained in it
	 * and its subdirectories (and the subdirectories of these subdirectories,
	 * etc.).
	 *
	 * You must consider only files ending with a ".txt" suffix. You are guaranteed
	 * that they will be text files.
	 *
	 * The method should return a list of LocatedWord objects (defined by the class
	 * at the end of this file), where each LocatedWord object should consist of:
	 * - a word appearing only on one line of the file;
	 * - the line containing such word;
	 * - the path to the file containing such line.
	 *
	 * All words appearing only on a single line of some file must appear in the list: words
	 * that can be in the list must be in the list.
	 * 
	 * Note that a word does not need to be unique to *all* files, just to a file. So if a word, say "Hello", appears exactly once in a file "f1.txt" and also in another file "f2.txt", then the list should contain two entries (one for each occurrence of Hello in the two files).
	 *
	 * Words must be considered equal without considering differences between
	 * uppercase and lowercase letters. (Case insensitive.) For example, the words "Hello", "hEllo" and
	 * "HELLo" must be considered equal to the word "hello".
	 * 
	 * 
	 * Allowed concurrency strategies: all.
	 *
	 * @param dir the directory to search
	 * @return a list of words that, within a file inside dir, appear on only one line
	 */
	private static List<LocatedWord> findWordsUniqueToALine(Path dir) {
		throw new UnsupportedOperationException(); // Remove this once you implement the method
	}

	/** Returns the line with the highest number of occurrences of the letter 'a' among all the lines
	 * present in the text files contained in a directory.
	 *
	 * This method recursively visits a directory to find all the text files
	 * contained in it and its subdirectories (and the subdirectories of these
	 * subdirectories, etc.).
	 *
	 * You must consider only files ending with a ".txt" suffix. You are
	 * guaranteed that they will be text files.
	 *
	 * The method should return the line which counts the highest number of occurrences of the letter 'a' (counting also 'A') found among all text files.
	 * If multiple lines are identified as having the highest number in the same file, just return the line that comes first. If two files have lines with the same (highest) number, then return the file whose filepath comes first lexicographically. (Use the full path, or 'absolute path' in Java, or you will not compare correctly files with the same name in different directories.)
	 * To compare strings lexicographically, you can use String::compareTo.
	 * See also https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#compareTo(java.lang.String)
	 * 
	 * Allowed concurrency strategy: parallel streams.
	 *
	 * @param dir the directory to search
	 * @return the line with the highest number of occurrences of 'a' found among all text files inside of dir
	 */
	private static Location lineWithMostA(Path dir) {
		throw new UnsupportedOperationException(); // Remove this once you implement the method
	}

	/**
	 * Returns an Optional<LocatedWord> (see below) about a word found in the files
	 * of the given directory containing the given number of consonants.
	 *
	 * This method recursively visits a directory to find text files contained in it
	 * and its subdirectories (and the subdirectories of these subdirectories,
	 * etc.).
	 *
	 * You must consider only files ending with a ".txt" suffix. You are guaranteed
	 * that they will be text files.
	 *
	 * The method should return an (optional) LocatedWord object (defined by the
	 * class at the end of this file), consisting of:
	 * - the word found that contains as many consonants as specified by the parameter numberOfConsonants (and no more);
	 * - the line containing such word;
	 * - the path to the file containing such line.
	 *
	 * You can consider a letter to be a consonant according to the English alphabet.
	 *
	 * If a word satisfying the description above can be found, then the method
	 * should return an Optional containing the desired LocatedWord. Otherwise, if
	 * such a word cannot be found, the method should return Optional.empty().
	 *
	 * This method should return *as soon as possible*: as soon as a satisfactory
	 * word is found, the method should return a result without waiting for the
	 * processing of remaining files and/or other data.
	 *
	 * 
	 * Allowed concurrency strategies: all.
	 * 
	 * @param dir the directory to search
	 * @param numberOfConsonants the number of consonants the word must contain
	 * @return an optional LocatedWord about a word containing exactly n consonants
	 */
	private static Optional<LocatedWord> wordWithConsonants(Path dir, int numberOfConsonants) {
		throw new UnsupportedOperationException(); // Remove this once you implement the method
	}

	/** Returns a list of words found in the given directory having the given string as a substring.
	 *
	 * This method recursively visits a directory to find text files
	 * contained in it and its subdirectories (and the subdirectories of these
	 * subdirectories, etc.).
	 *
	 * You must consider only files ending with a ".txt" suffix. You are
	 * guaranteed that they will be text files.
	 *
	 * The method should return a list of LocatedWord objects (defined by the
	 * class at the end of this file), consisting of:
	 * - the word that contains the given substring;
	 * - the line containing such word;
	 * - the path to the file containing such line.
	 *
	 * The size of the returned list must not exceed the given limit.
	 * Therefore, this method should return *as soon as possible*: if the list
	 * reaches the given limit at any point during the computation, no more
	 * elements should be added to the list and remaining files and/or other lines
	 * should not be analysed.
	 * 
	 * 
	 * Allowed concurrency strategy: virtual threads.
	 *
	 * @param dir the directory to search
	 * @param substring the substring to be searched for
	 * @param limit the size limit for the returned list
	 * @return a list of words containing the given substring
	 */
	private static List<LocatedWord> wordsWithSubstring(Path dir, String substring, int limit) {
		throw new UnsupportedOperationException(); // Remove this once you implement the method
	}

	// Do not change this class
	private record LocatedWord(
		String word, // the word
		int line, // the line where the word has been found
		Path filepath // the file where the word has been found
	) {}

	// Do not change this class
	private record Location(
		Path filepath, // the file where the word has been found
		int line // the line number at which the word has been found
	) {}

	// Do not change this class
	private final static class InternalException extends RuntimeException {
		private InternalException(String message) {
			super(message);
		}
	}
}
