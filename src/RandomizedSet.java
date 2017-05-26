


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet
{
	Map<Integer, Integer> _set;
	List<Integer> _list;
	int _index;
	Random _random;

	/** Initialize your data structure here. */
	public RandomizedSet()
	{
		_set = new HashMap<>();
		_list = new ArrayList<>();
		_index = 0;
		_random = new Random();

	}

	/** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
	public boolean insert( int val )
	{
		if ( _set.containsKey( val ) )
			return false;
		_list.add( val );
		_set.put( val, _index++ );
		return true;
	}

	/** Removes a value from the set. Returns true if the set contained the specified element. */
	public boolean remove( int val )
	{
		if ( !_set.containsKey( val ) )
			return false;
		int pos = _set.get( val );
		if ( pos != _index )
		{
			_list.set( pos, _list.get( _index - 1 ) );
			_set.put( _list.get( pos ), pos );
		}
		_list.remove( _index - 1 );
		_index--;
		_set.remove( val );
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom()
	{
		// System.out.println( Arrays.toString( _list.toArray( new Integer[0] ) ) );
		int n = _random.nextInt( _index );
		return _list.get( n );
	}

	public static void main( String[] args )
	{
		RandomizedSet r = new RandomizedSet();
		String[] action = { "insert", "insert", "remove", "insert", "insert", "insert", "remove", "remove", "insert", "remove",
				"insert", "insert", "insert", "insert", "insert", "getRandom", "insert", "remove", "insert", "insert" };
		int[] vals = { 3, -2, 2, 1, -3, -2, -2, 3, -1, -3, 1, -2, -2, -2, 1, 0, -2, 0, -3, 1 };
		for ( int i = 0; i < action.length; i++ )
			run( r, action[i], vals[i] );
	}

	static void run( RandomizedSet r, String action, int val )
	{
		if ( action.equals( "insert" ) )
			System.out.println( "insert\t" + val + " " + r.insert( val ) );
		if ( action.equals( "remove" ) )
			System.out.println( "remove\t " + val + " " + r.remove( val ) );
		if ( action.equals( "getRandom" ) )
			System.out.println( r.getRandom() );
	}
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */