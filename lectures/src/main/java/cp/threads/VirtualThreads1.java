package cp.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class VirtualThreads1
{
	public static void main()
	{
		// word -> number of times it appears over all files
		Map< String, Integer > occurrences = new ConcurrentHashMap<>();
		
		try {
			// Stream.of( "text1.txt", "text2.txt", "text3.txt", "text4.txt", "text5.txt", "text6.txt", "text7.txt", "text8.txt", "text9.txt", "text10.txt", "text1.txt", "text2.txt", "text3.txt", "text4.txt", "text5.txt", "text6.txt", "text7.txt", "text8.txt", "text9.txt", "text10.txt" )
			Files.walk( Paths.get( "data" ) )
				.filter( Files::isRegularFile )
				// .map( Paths::get )
				.parallel()
				.flatMap( textFile -> {
					try {
						return Files.lines( textFile );
					} catch( IOException e ) {
						return Stream.empty();
					}
				} )
				.map( line -> {
					return Thread.ofVirtual().start( () -> {
						Words.extractWords( line )
							.map( String::toLowerCase )
							.forEach( s -> occurrences.merge( s, 1, Integer::sum ) );
					} );
					// System.out.println( thread );
				} )
				.forEach( t -> {
					try {
						t.join();
					} catch ( InterruptedException e ) {
						e.printStackTrace();
					}
				} );
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
		occurrences.forEach( (word, n) -> System.out.println( word + ": " + n ) );
	}
}
