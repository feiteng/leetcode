


import java.util.concurrent.atomic.AtomicInteger;

public class NoVisibility
{
	private static boolean ready;
	private static AtomicInteger number;

	private static class ReaderThread extends Thread
	{
		public void run()
		{
			while ( !ready )
				Thread.yield();
			System.out.println( number );
		}
	}

	public static void main( String[] args )
	{
		new ReaderThread().start();
		number = new AtomicInteger( 42 );
		ready = true;
	}
}