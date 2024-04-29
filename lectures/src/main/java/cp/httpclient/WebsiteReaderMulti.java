package cp.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import cp.threads.Words;

public class WebsiteReaderMulti
{
	public static void main()
	{
		// word -> number of times it appears over all files
		Map< String, Integer > occurrences = new ConcurrentHashMap<>();
		
		Stream.of( "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en" )
			.map( URI::create )
			.parallel()
			.forEach( uri -> {
				System.out.println( uri.toString() );
				HttpRequest request = HttpRequest.newBuilder().GET().uri( uri ).build();
				try( HttpClient client = HttpClient.newHttpClient() ) {
					HttpResponse< Stream< String > > response = client.send( request, BodyHandlers.ofLines() );
					response.body()
						.flatMap( Words::extractWords )
						.map( String::toLowerCase )
						.forEach( s -> occurrences.merge( s, 1, Integer::sum ) );
				} catch( IOException | InterruptedException e ) {
					e.printStackTrace();
				}
			} );
		
		occurrences.entrySet().stream().sorted(
			(entry1, entry2) -> entry1.getValue().compareTo( entry2.getValue() )
		).forEach( entry -> System.out.println( entry.getKey() + ": " + entry.getValue() ) );
	}
}
