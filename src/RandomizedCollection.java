

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class RandomizedCollection
{
	Map<Integer, List<Integer>> _map;
	List<Integer> _position;
	int _total;
	Random _random;

	/** Initialize your data structure here. */
	public RandomizedCollection()
	{
		_map = new HashMap<>();
		_position = new ArrayList<>();
		_total = 0;
		_random = new Random();

	}

	/** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
	public boolean insert( int val )
	{
		boolean flag = false;
		if ( !_map.containsKey( val ) )
		{
			List<Integer> list = new ArrayList<>();
			list.add( _total++ );
			_map.put( val, list );
			flag = true;
		}
		else
		{
			List<Integer> list = _map.get( val );
			list.add( _total++ );
		}
		_position.add( val );
		return flag;

	}

	/** Removes a value from the collection. Returns true if the collection contained the specified element. */
	public boolean remove( int val )
	{

		if ( !_map.containsKey( val ) )// || _map.get( val ).size() > 0 )

			return false;
		int lastItem = _total - 1;
		List<Integer> removeList = _map.get( val );
		List<Integer> replaceList = _map.get( _position.get( lastItem ) );

		if ( val == _position.get( lastItem ) )
		{
			// last item is the val to be removed
			// e.g. [1,1,1,2,2] , remove 2
			removeList.remove( removeList.size() - 1 );
		}
		else
		{
			// general case
			// e.g. [1,1,1,2,2] remove 1
			int removeIndex = removeList.get( removeList.size() - 1 );
			removeList.remove( removeList.size() - 1 );
			replaceList.add( 0, removeIndex );
			replaceList.remove( replaceList.size() - 1 );
			_position.set( removeIndex, _position.get( lastItem ) );
		}
		if ( removeList.size() == 0 )
			_map.remove( val );

		_position.remove( lastItem );
		_total--;
		return true;
	}

	/** Get a random element from the collection. */
	public int getRandom()
	{
		int random = _random.nextInt( _total );
		return _position.get( random );
	}

	public static void main( String[] args )
	{

		String[] actions = { "insert", "remove", "insert", "remove", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom",
				"getRandom", "getRandom", "getRandom", "getRandom"
		};
		int[] vals = { 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		RandomizedCollection r = new RandomizedCollection();
		for ( int i = 0; i < actions.length; i++ )
			r.perform( r, actions[i], vals[i] );
	}

	void perform( RandomizedCollection r, String s, int val )
	{
		if ( s.equals( "insert" ) )
			r.insert( val );
		if ( s.equals( "remove" ) )
			r.remove( val );
		if ( s.equals( "getRandom" ) )
		{
		} // r.getRandom();
		for ( int i = 0; i < r._position.size(); i++ )
			System.out.print( r._position.get( i ) + " " );
		Iterator<Entry<Integer, List<Integer>>> iterator = r._map.entrySet().iterator();
		System.out.print( "|\t" );
		while ( iterator.hasNext() )
		{
			Entry<Integer, List<Integer>> e = iterator.next();
			System.out.print( e.getKey() + ":" + e.getValue() + " " );
		}
		System.out.println();

	}
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */