import java.util.HashMap;
import java.util.Map;

public class Logger
{

	Map<String, Integer> msgCount = new HashMap<>();

	/** Initialize your data structure here. */
	public Logger()
	{

	}

	/**
	 * Returns true if the message should be printed in the given timestamp, otherwise returns false.
	 * If this method returns false, the message will not be printed.
	 * The timestamp is in seconds granularity.
	 */
	public boolean shouldPrintMessage( int timestamp, String message )
	{
		if ( !msgCount.containsKey( message ) ||
				msgCount.get( message ) < timestamp - 10 )
		{
			// update msg
			msgCount.put( message, timestamp );
			return true;
		}
		return false;
	}
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */