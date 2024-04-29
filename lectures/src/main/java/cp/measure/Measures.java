package cp.measure;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

class MeasureUtils {
	public static void doAndMeasure( Function<Integer,List<Integer>> runnable ) {
		final var startTime = System.currentTimeMillis();
		List<Integer> l = runnable.apply( 10 );
		final var elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println( "Elapsed time " + elapsedTime + "ms" );
		System.out.println( "Size: " + l.size() );
	}
}

class MyCode {
	public static List<Integer> run( int first ) {
		final List<Integer> list = new LinkedList<>();
		for (int i = first; i < 100_000 + first; i++) {
			list.add( i );
		}
		return list;
	}
}
public class Measures {
	public static void main() {
		MeasureUtils.doAndMeasure( MyCode::run );
	}
}
