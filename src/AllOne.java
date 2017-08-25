import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class AllOne
{
	Map<String, Integer> map = new HashMap<>();
	Map<String, oneMap> position = new HashMap<>();
	oneMap _HEAD, _END;

	/** Initialize your data structure here. */
	public AllOne()
	{
		_HEAD = new oneMap();
		_HEAD.val = 0;
		_END = new oneMap();
		_HEAD.next = _END;
		_END.prev = _HEAD;
	}

	/** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
	public void inc( String key )
	{
		if ( !map.containsKey( key ) )
		{
			map.put( key, 1 );
			_HEAD.insert( key );
			position.put( key, _HEAD.next );
		}
		else
		{
			map.put( key, map.get( key ) + 1 );
			oneMap curr = position.get( key ), next = curr.insert( key );
			position.put( key, next );
		}
	}

	/** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
	public void dec( String key )
	{
		if ( !map.containsKey( key ) )
			return;
		if ( map.get( key ) == 1 )
			map.remove( key );
		else
			map.put( key, map.get( key ) - 1 );

		oneMap del = position.get( key ).delete( key );
		if ( del == null )
			return;
		if ( del.val == 0 ) // _HEAD
		{
			del.set.remove( key );
			position.remove( key );
		}
		else
			position.put( key, del );

	}

	/** Returns one of the keys with maximal value. */
	public String getMaxKey()
	{
		return getSet( _END.prev.set );
	}

	/** Returns one of the keys with Minimal value. */
	public String getMinKey()
	{
		return getSet( _HEAD.next.set );
	}

	String getSet( Set<String> set )
	{
		String r = "";
		for ( Iterator<String> i = set.iterator(); i.hasNext(); )
		{
			r = i.next();
			break;
		}
		return r;
	}

	public static void main( String[] args )
	{
		AllOne a = new AllOne();
		String[] ops = { "AllOne", "inc", "inc", "inc", "dec", "inc", "inc", "getMaxKey", "dec", "dec", "dec", "getMaxKey" };

		String[][] vs = { {}, { "hello" }, { "world" }, { "hello" }, { "world" }, { "hello" }, { "leet" }, {}, { "hello" }, { "hello" }, { "hello" },
				{} };

		for ( int i = 1; i < ops.length; i++ )
		{

			a.print( a );
			System.out.println();
			if ( ops[i].equals( "inc" ) )
				a.inc( vs[i][0] );
			if ( ops[i].equals( "dec" ) )
				a.dec( vs[i][0] );
			if ( ops[i].equals( "getMaxKey" ) )
				System.out.println( a.getMaxKey() );
			if ( ops[i].equals( "getMinKey" ) )
				System.out.println( a.getMinKey() );
		}

	}

	void print( AllOne a )
	{
		oneMap h = a._HEAD;
		while ( h.next != null )
		{
			System.out.print( "" + h.val + h.set );
			System.out.println();
			h = h.next;
		}
	}
}

class oneMap
{
	int val = -1;
	Set<String> set = new HashSet<>();

	Set<String> getSet()
	{
		return set;
	}

	oneMap prev = null, next = null;

	oneMap insert( String key )
	{
		set.remove( key );
		oneMap curr = this, next = curr.next;
		if ( set.size() == 0 && this.prev != null ) // current node deleted
		{
			this.prev.next = next;
			next.prev = this.prev;
			curr = this.prev;
		}
		if ( next.val - val == 1 )
			next.set.add( key );
		else
		{
			oneMap newMap = new oneMap();
			newMap.set.add( key );
			newMap.val = val + 1;
			curr.next = newMap;
			newMap.prev = curr;
			newMap.next = next;
			next.prev = newMap;
		}
		return curr.next;
	}

	oneMap delete( String key )
	{
		oneMap curr = this, prev = curr.prev;
		set.remove( key );

		if ( curr.val - prev.val == 1 )
		{
			prev.set.add( key );
		}
		else
		{
			oneMap newMap = new oneMap();
			newMap.set.add( key );
			newMap.val = val - 1;
			curr.prev = newMap;
			newMap.next = curr;
			newMap.prev = prev;
			prev.next = newMap;
		}
		if ( set.size() == 0 && curr.next != null )
		{
			curr.prev.next = curr.next;
			curr.next.prev = curr.prev;
			curr = curr.next;
		}
		return curr.prev;

	}
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */