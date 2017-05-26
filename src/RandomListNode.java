


import java.util.HashMap;
import java.util.Map;

// Definition for singly-linked list with a random pointer.
class RandomListNode
{
	int label;
	RandomListNode next, random;

	RandomListNode( int x )
	{
		this.label = x;

	}

	RandomListNode( int[] a )
	{
		Map<Integer, RandomListNode> _map = new HashMap<>();
		// assuming all int values are distinct
		RandomListNode tmp = this;
		tmp.label = a[0];
		_map.put( a[0], tmp );

		for ( int i = 2; i < a.length; i += 2 )
		{
			RandomListNode k = new RandomListNode( a[i] );
			tmp.next = k;
			_map.put( a[i], k );
			tmp = tmp.next; // puts all values into list
		}
		tmp = this;
		for ( int i = 1; i < a.length; i += 2 )
		{
			tmp.random = _map.get( a[i] );
			tmp = tmp.next;

		}

	}

	public void print()
	{
		RandomListNode t = this;
		System.out.print( "Listnode: " );
		while ( t != null )
		{
			System.out.print( "->" + t );
			t = t.next;
		}
		System.out.print( "->NULL" );
		t = this;
		System.out.print( "\nRandomNode: " );
		while ( t != null )
		{
			System.out.print( "->" + t.random );
			t = t.next;
		}
		System.out.print( "->NULL" );
		System.out.println();
	}
};