


import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class LFUCache
{
	class FrequencyList
	{
		int _freq;
		// linked hash set, thus keeping adding order (within same freq)
		// first element is always the lru
		Set<Integer> _list;
		FrequencyList _p, _n;

		public FrequencyList()
		{
			this( 1 );
		}

		FrequencyList( int freq )
		{
			_list = new LinkedHashSet<>();
			_freq = freq;
			_p = null;
			_n = null;
		}

	}

	int _count, _capacity;
	// maps key to it's frequency list
	Map<Integer, FrequencyList> _freqMap;
	// maps key to it's value
	Map<Integer, Integer> _valueMap;
	FrequencyList _HEAD, _TAIL;

	public LFUCache( int capacity )
	{
		_capacity = capacity;
		_count = 0;
		_valueMap = new HashMap<>();
		_freqMap = new HashMap<>();
		_HEAD = new FrequencyList();
		_TAIL = new FrequencyList( -1 );
		_HEAD._n = _TAIL;
		_TAIL._p = _HEAD;
	}

	void update( int key )
	{
		FrequencyList fList = _freqMap.get( key );
		int freq = fList._freq;
		fList._list.remove( key );
		if ( fList._n._freq == freq + 1 )
		{
			fList._n._list.add( key );

		}
		else // insert a new freq list
		{
			FrequencyList newList = new FrequencyList( freq + 1 ), nextList = fList._n;
			newList._list.add( key );
			newList._p = fList;
			newList._n = nextList;
			fList._n = newList;
			nextList._p = newList;
		}
		if ( fList._list.size() == 0 )
		{
			fList._p._n = fList._n;
			fList._n._p = fList._p;
		}
		_freqMap.put( key, fList._n );
	}

	public int get( int key )
	{
		if ( _valueMap.containsKey( key ) )
		{
			update( key );
			return _valueMap.get( key );
		}
		return -1;
	}

	void removeOld()
	{
		for ( int k : _HEAD._n._list ) // in order
		{
			_HEAD._n._list.remove( k );
			if ( _HEAD._n._list.size() == 0 )
			{
				_HEAD._n = _HEAD._n._n;
				_HEAD._n._p = _HEAD;
			}
			_valueMap.remove( k );
			_freqMap.remove( k );
			_count--;
			return;
		}
	}

	public void put( int key, int value )
	{
		if ( _capacity == 0 )
			return;
		if ( _valueMap.containsKey( key ) )
		{
			update( key );
			_valueMap.put( key, value );
		}
		else
		{
			_valueMap.put( key, value );
			// new element - remove before add
			if ( _count == _capacity )
				removeOld();
			if ( _HEAD._n._freq == 1 )
				_HEAD._n._list.add( key );
			else
			{
				FrequencyList One = new FrequencyList(), nextList = _HEAD._n;
				One._list.add( key );
				One._p = _HEAD;
				One._n = nextList;
				_HEAD._n = One;
				nextList._p = One;
			}
			_freqMap.put( key, _HEAD._n );
			_count++;
		}
	}

	public static void main( String[] args )
	{
		LFUCache l = new LFUCache( 10 );
		l.f1( l );
	}

	void f1( LFUCache l )
	{
		String[] op = { "put", "put", "put", "put", "put", "get", "put", "get", "get", "put", "get", "put", "put", "put", "get", "put",
				"get", "get", "get", "get", "put", "put", "get", "get", "get", "put", "put", "get", "put", "get", "put", "get", "get", "get", "put",
				"put", "put", "get", "put", "get", "get", "put", "put", "get", "put", "put", "put", "put", "get", "put", "put", "get", "put", "put",
				"get", "put", "put", "put", "put", "put", "get", "put", "put", "get", "put", "get", "get", "get", "put", "get", "get", "put", "put",
				"put", "put", "get", "put", "put", "put", "put", "get", "get", "get", "put", "put", "put", "get", "put", "put", "put", "get", "put",
				"put", "put", "get", "get", "get", "put", "put", "put", "put", "get", "put", "put", "put", "put", "put", "put", "put" };
		int[][] opn = { { 10, 13 }, { 3, 17 }, { 6, 11 }, { 10, 5 }, { 9, 10 }, { 13 }, { 2, 19 }, { 2 }, { 3 }, { 5, 25 }, { 8 }, { 9, 22 },
				{ 5, 5 }, { 1, 30 }, { 11 }, { 9, 12 }, { 7 }, { 5 }, { 8 }, { 9 }, { 4, 30 }, { 9, 3 }, { 9 }, { 10 }, { 10 }, { 6, 14 }, { 3, 1 },
				{ 3 }, { 10, 11 }, { 8 }, { 2, 14 }, { 1 }, { 5 }, { 4 }, { 11, 4 }, { 12, 24 }, { 5, 18 }, { 13 }, { 7, 23 }, { 8 }, { 12 },
				{ 3, 27 }, { 2, 12 }, { 5 }, { 2, 9 }, { 13, 4 }, { 8, 18 }, { 1, 7 }, { 6 }, { 9, 29 }, { 8, 21 }, { 5 }, { 6, 30 }, { 1, 12 },
				{ 10 }, { 4, 15 }, { 7, 22 }, { 11, 26 }, { 8, 17 }, { 9, 29 }, { 5 }, { 3, 4 }, { 11, 30 }, { 12 }, { 4, 29 }, { 3 }, { 9 }, { 6 },
				{ 3, 4 }, { 1 }, { 10 }, { 3, 29 }, { 10, 28 }, { 1, 20 }, { 11, 13 }, { 3 }, { 3, 12 }, { 3, 8 }, { 10, 9 }, { 3, 26 }, { 8 }, { 7 },
				{ 5 }, { 13, 17 }, { 2, 27 }, { 11, 15 }, { 12 }, { 9, 19 }, { 2, 15 }, { 3, 16 }, { 1 }, { 12, 17 }, { 9, 1 }, { 6, 19 }, { 4 },
				{ 5 }, { 5 }, { 8, 1 }, { 11, 7 }, { 5, 2 }, { 9, 28 }, { 1 }, { 2, 2 }, { 7, 4 }, { 4, 22 }, { 7, 24 }, { 9, 26 }, { 13, 28 },
				{ 11, 26 } };

		if ( op.length != opn.length )
		{
			throw new IllegalArgumentException( "Different lengths!" );
		}
		for ( int i = 0; i < op.length; i++ )
		{
			if ( op[i].equals( "put" ) )
			{
				l.put( opn[i][0], opn[i][1] );
			}
			if ( op[i].equals( "get" ) )
			{
				// l.get( opn[i][0] );
				System.out.printf( "%d\t%d\n", opn[i][0], l.get( opn[i][0] ) );
			}

			boolean p = false;
			if ( p )
			{
				int f = 0;
				FrequencyList t = _HEAD._n;
				while ( t != null )
				{

					f = t._freq;
					System.out.printf( "%d\t%s\n", f, t._list.toString() );
					t = t._n;
				}
				System.out.println();
				// LFUList hList = _HEAD._n;
				// while ( hList != null )
				// {
				// System.out.printf( "[%d,%d] ", hList._key, hList._value );
				// hList = hList._n;
				// }
				// System.out.println();
			}
		}

	}
}