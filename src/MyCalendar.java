import java.util.ArrayList;
import java.util.List;

class MyCalendar
{
	class Interval
	{
		int _start, _end;

		public Interval( int start, int end )
		{
			_start = start;
			_end = end;
		}
	}

	List<Interval> _list = new ArrayList<>();

	public MyCalendar()
	{

	}

	boolean intersect( Interval i1, Interval i2 )
	{
		if ( i1._start >= i2._end || i1._end <= i2._start )
			return false;
		return true;
	}

	public boolean book( int start, int end )
	{
		Interval n = new Interval( start, end );
		for ( Interval i : _list )
			if ( intersect( i, n ) )
				return false;
		_list.add( n );
		return true;

	}

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