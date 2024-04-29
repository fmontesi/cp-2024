package cp.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class VirtualThreads3
{
	public static void main()
	{
		// var pool = new ForkJoinPool( 6 );
		// pool.execute( () -> {
		// 	IntStream
		// 		.range( 0, 5 )
		// 		.parallel()
		// 		.forEach( n -> {
		// 			try {
		// 				System.out.println( "Sleeping" );
		// 				Thread.sleep( 1000 );
		// 			} catch( InterruptedException e ) {} 
		// 		} );
		// } );
		// try {
		// 	pool.shutdown();
		// 	pool.awaitTermination(1, TimeUnit.DAYS);
		// } catch( InterruptedException e ) {}

		List< Thread > threads = new ArrayList<>();

		IntStream
			.range( 0, 5 )
			.forEach( n -> threads.add( Thread.ofVirtual().unstarted( () -> {
				try {
					System.out.println( "Sleeping" );
					Thread.sleep( 1000 );
				} catch( InterruptedException e ) {} 
			} ) ) );
			// .forEach( t -> {
			// 	try {
			// 		t.join();
			// 	} catch( InterruptedException e ) {}
			// } );
		for( var t : threads ) {
			t.start();
		}

		for( var t : threads ) {
			try {
				t.join();
			} catch( InterruptedException e ) {}
		}
	}
}
