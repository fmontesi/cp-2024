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

public class WebsiteReader
{
	public static void main()
	{
		// word -> number of times it appears over all files
		Map< String, Integer > occurrences = new ConcurrentHashMap<>();
		
		URI uri = URI.create( "https://www.fabriziomontesi.com/" );

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
		
		occurrences.entrySet().stream().sorted(
			(entry1, entry2) -> entry1.getValue().compareTo( entry2.getValue() )
		).forEach( entry -> System.out.println( entry.getKey() + ": " + entry.getValue() ) );
	}
}
