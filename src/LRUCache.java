

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache
{
	Map<Integer, Integer> _map;

	int _count, _capacity;

	public LRUCache( int capacity )
	{
		_count = 0;
		_capacity = capacity;
		_map = new LinkedHashMap<>();
	}

	void update( int key )
	{
		int value = _map.get( key );
		_map.remove( key );
		_map.put( key, value );
	}

	public int get( int key )
	{
		if ( _map.containsKey( key ) )
		{
			update( key );
			return _map.get( key );
		}
		return -1;
	}

	public void put( int key, int value )
	{
		if ( _capacity == 0 )
			return;
		if ( _map.containsKey( key ) )
		{
			update( key );
			_map.put( key, value );
		}
		else
		{
			if ( _count == _capacity )
			{
				for ( int k : _map.keySet() )
				{
					_map.remove( k );
					break;
				}
				_count--;
			}
			_map.put( key, value );
			_count++;
		}
	}
	// class DoubleList
	// {
	// int _key, _value;
	// DoubleList _p, _n;
	//
	// public DoubleList()
	// {
	// _p = null;
	// _n = null;
	// }
	//
	// public DoubleList( int key, int value )
	// {
	// _key = key;
	// _value = value;
	// _p = null;
	// _n = null;
	// }
	//
	// }
	//
	// static DoubleList _Head, _Tail;
	//
	// Map<Integer, DoubleList> _map;
	// int _count, _capacity;
	//
	// void insert( DoubleList node )
	// {
	// DoubleList next = _Head._n;
	// node._p = _Head;
	// _Head._n = node;
	// node._n = next;
	// next._p = node;
	// }
	//
	// void delete( DoubleList node )
	// {
	// DoubleList prev = node._p, next = node._n;
	// prev._n = next;
	// next._p = prev;
	// }
	//
	// DoubleList pop()
	// {
	// DoubleList node = _Tail._p, prev = _Tail._p._p;
	// prev._n = _Tail;
	// _Tail._p = prev;
	// return node;
	// }
	//
	// public LRUCache( int capacity )
	// {
	// _Head = new DoubleList();
	// _Tail = new DoubleList();
	// _Head._n = _Tail;
	// _Tail._p = _Head;
	// _map = new HashMap<>();
	// _count = 0;
	// _capacity = capacity;
	// }
	//
	// public int get( int key )
	// {
	// if ( _map.containsKey( key ) )
	// {
	// DoubleList node = _map.get( key );
	// delete( node );
	// insert( node );
	// return node._value;
	// }
	// return -1;
	// }
	//
	// public void set( int key, int value )
	// {
	// if ( _map.containsKey( key ) )
	// {
	// DoubleList node = _map.get( key );
	// delete( node );
	// insert( node );
	// node._value = value;
	//
	// }
	// else
	// {
	// DoubleList node = new DoubleList( key, value );
	// _map.put( key, node );
	// insert( node );
	//
	// if ( _count < _capacity )
	// _count++;
	// else
	// {
	// DoubleList removeNode = pop();
	// _map.remove( removeNode._key );
	// }
	// }
	// }

	public static void main( String[] args )
	{
		LRUCache l = new LRUCache( 2 );
		l.f1( l );

	}

	void f2( LRUCache l )
	{
		boolean flag = true;
		int k = 0;
		for ( int i = 0; i < 10; i++ )
		{
			l.put( k++, i );

		}

		System.out.println( l.get( 0 ) );
		System.out.println( l.get( 1 ) );

	}

	static void f1( LRUCache l )
	{
		String actions = "set(2,1),set(1,1),set(2,3),set(4,1),get(1),get(2)";
		char[] chars = actions.toCharArray();
		for ( int i = 0; i < chars.length - 1; i++ )
		{
			if ( chars[i] == ')' && chars[i + 1] == ',' )
				chars[i + 1] = ':';
		}
		actions = String.valueOf( chars );
		String[] split = actions.split( ":" );
		for ( String a : split )
		{
			if ( a.substring( 0, 3 ).equals( "set" ) )
			{
				String[] vals = a.substring( 4, a.length() - 1 ).split( "," );
				l.put( Integer.valueOf( vals[0] ), Integer.valueOf( vals[1] ) );
				/*System.out.print( "set: " + vals[0] + "-" + vals[1] + "\t" );
				/*Iterator<Entry<Integer, Integer>> iterator = l._map.entrySet().iterator();
				for ( ; iterator.hasNext(); )
				{
					Entry<Integer, Integer> entry = iterator.next();
					System.out.print( entry.getKey() + "-" + entry.getValue() + "|" );
				}
				/*
				for ( int i = 0; i < l._list.size(); i++ )
					System.out.print( l._list.get( i )._key + "-" + l._list.get( i )._value + "|" );
				
				System.out.println();
				*/
			}
			else
			{
				String vals = a.substring( 4, a.length() - 1 );
				/*
				for ( int i = 0; i < l._list.size(); i++ )
					System.out.print( l._list.get( i )._key + "-" + l._list.get( i )._value + "|" );
				*/
				System.out.println( "get: " + vals + "\t" + l.get( Integer.valueOf( vals ) ) );
			}
			// System.out.printf( "%s\n", l._list.toString() );
		}
	}

}