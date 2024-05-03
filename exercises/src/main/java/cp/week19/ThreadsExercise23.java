package cp.week19;

/**
 *
 * @author Fabrizio Montesi <fmontesi@imada.sdu.dk>
 */
public class ThreadsExercise23
{
	/*
	* Virtual threads can be managed with executors as well.
	* Read the documentation at https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/Executors.html#newVirtualThreadPerTaskExecutor()
	*
	* Then, modify threads/WalkExecutor to use Executors.newVirtualThreadPerTaskExecutor() instead of a fixed-size thread pool.
	* To manage shutdown of the executor, just use try-with-resources, e.g., try( var executor = Executors.newVirtualThreadPerTaskExecutor() ) { ... }
	* (This ensures that the shutdown method is called.)
	*/
}
