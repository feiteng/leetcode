import java.util.TreeSet;

class MyCalendar
{

	public MyCalendar()
	{

	}

	TreeSet<Integer> _set = new TreeSet<>();

	public boolean book( int start, int end )
	{

		_set.add( start );
		_set.add( -end );
		int k = 0, run = 0;
		for ( int m : _set )
		{
			if ( m > 0 )
				run++;
			else
				run--;
			if ( run > 1 )
			{
				_set.remove( start );
				_set.remove( -end );
				return false;
			}
		}

		return true;
	}

	/**
	 * Your MyCalendar object will be instantiated and called as such:
	 * MyCalendar obj = new MyCalendar();
	 * boolean param_1 = obj.book(start,end);
	 */

	public static void main( String[] args )
	{
		String[] op = { "MyCalendar", "book", "book", "book" };
		int[][] v = { {}, { 10, 20 }, { 15, 25 }, { 20, 30 } };
		MyCalendar myCalendar = new MyCalendar();
		for ( int i = 1; i < op.length; i++ )
			System.out.println( myCalendar.book( v[i][0], v[i][1] ) );

	}
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */