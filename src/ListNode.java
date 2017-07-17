
public class ListNode
{
	int val;
	ListNode next;

	ListNode( int x )

	{
		val = x;
		next = null;
	}

	ListNode( int[] v )
	{
		ListNode t = this;
		this.val = v[0];
		for ( int i = 1; i < v.length; i++ )
		{
			t.next = new ListNode( v[i] );
			t = t.next;
		}

	}

	public int[] toarray()
	{
		ListNode tmp = this;
		int count = 0;
		while ( tmp != null )
		{
			tmp = tmp.next;
			count++;

		}
		int[] ret = new int[count];
		tmp = this;
		for ( int i = 0; i < count; i++ )
		{
			ret[i] = tmp.val;
			tmp = tmp.next;
		}
		return ret;
	}

	public void print()
	{

		ListNode t = this;
		System.out.print( "Listnode: " );
		while ( t.next != null )
		{
			System.out.print( "->" + t.val );
			t = t.next;
		}
		System.out.print( "->" + t.val + "->NULL" );
		System.out.println();
	}

	public ListNode reverse()
	{
		ListNode ret = new ListNode( 0 );
		ListNode tmp = ret, n;
		ret.next = this;
		while ( tmp.next != null )
		{
			n = ret.next;
			ret.next = new ListNode( tmp.next.val );
			ret.next.next = n;
			tmp = tmp.next;
		}
		return ret;
	}

}