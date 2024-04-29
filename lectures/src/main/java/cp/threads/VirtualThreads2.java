package cp.threads;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VirtualThreads2
{
	public static void main()
	{
		// word -> number of times it appears over all files
		Map< String, Integer > occurrences = new ConcurrentHashMap<>();
		var uriStream = Stream.of( "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/", "https://www.fabriziomontesi.com/", "https://seg.inf.unibe.ch/people/sandra/", "https://www.marcoperessotti.com/", "https://functorial.org/", "https://jacopomauro.com/", "http://www.polycephaly.org/", "https://www.unibo.it/sitoweb/marco.prandini/en", "https://www.imada.sdu.dk/u/kslarsen/", "https://imada.sdu.dk/u/lenem/", "https://imada.sdu.dk/u/zimek/", "https://imada.sdu.dk/u/joan/", "https://acp.sdu.dk/" ).map( URI::create );

		// var latch = new CountDownLatch( 240 );

		// var pool = new ForkJoinPool( 4 );
		// try {
		// 	pool.submit( () -> 
		// 		uriStream
		// 			.parallel()
		// 			.forEach( uri -> {
		// 				System.out.println( uri.toString() );
		// 				HttpRequest request = HttpRequest.newBuilder().GET().uri( uri ).build();
		// 				try( HttpClient client = HttpClient.newHttpClient() ) {
		// 					HttpResponse< Stream< String > > response = client.send( request, BodyHandlers.ofLines() );
		// 					response.body()
		// 						.flatMap( Words::extractWords )
		// 						.map( String::toLowerCase )
		// 						.forEach( s -> occurrences.merge( s, 1, Integer::sum ) );
		// 					latch.countDown();
		// 					System.out.println( "Done " + uri.toString() );
		// 				} catch( IOException | InterruptedException e ) {
		// 					e.printStackTrace();
		// 				}
		// 			} )
		// 	).get();
		// } catch( InterruptedException | ExecutionException e ) {
		// 	e.printStackTrace();
		// }

		// // pool.shutdown();
		// // try {
		// // 	pool.awaitTermination(1, TimeUnit.DAYS);
		// // } catch( InterruptedException e ) {}
		// try {
		// 	latch.await();	
		// } catch (Exception e) {
		// }

		List< Thread > threads = new ArrayList<>();

		uriStream
			.collect( Collectors.toList() )
			// .parallel()
			.forEach( uri ->
				threads.add( Thread.ofVirtual().unstarted( () -> {
					System.out.println( uri.toString() );
					HttpRequest request = HttpRequest.newBuilder().GET().uri( uri ).build();
					try {
						HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
						HttpResponse< Stream< String > > response = client.send( request, BodyHandlers.ofLines() );
						response.body()
							.flatMap( Words::extractWords )
							.map( String::toLowerCase )
							.forEach( s -> occurrences.merge( s, 1, Integer::sum ) );
					} catch( IOException | InterruptedException e ) {
						e.printStackTrace();
					}
				} ) ) );
			
			for( var t : threads ) {
				t.start();
			}

			for( var t : threads ) {
				try {
					t.join();
				} catch( InterruptedException e ) {}
			}
			// .forEach( t -> {
			// 	try {
			// 		t.join();
			// 	} catch( InterruptedException e ) {
			// 		e.printStackTrace();
			// 	}
			// } );
		
		occurrences.entrySet().stream().sorted(
			(entry1, entry2) -> entry1.getValue().compareTo( entry2.getValue() )
		).forEach( entry -> System.out.println( entry.getKey() + ": " + entry.getValue() ) );
	}
}
