
import java.util.HashMap;
import java.util.Map;

public class TwoSum
{

	Map<Integer, Integer> _map = new HashMap();

	/** Initialize your data structure here. */
	public TwoSum()
	{
	}

	/** Add the number to an internal data structure.. */
	public void add( int number )
	{
		if ( _map.containsKey( number ) )
			_map.put( number, _map.get( number ) + 1 );
		else
			_map.put( number, 1 );

	}

	/** Find if there exists any pair of numbers which sum is equal to the value. */
	public boolean find( int value )
	{
		for ( int k : _map.keySet() )
		{
			if ( _map.containsKey( value - k ) )
			{
				if ( k * 2 == value && _map.get( k ) > 1 )
					return true;
				else if ( k * 2 != value )
					return true;
			}
		}
		return false;
	}
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */