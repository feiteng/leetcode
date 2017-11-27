import java.util.ArrayList;
import java.util.List;

class MyCalendarTwo
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

	// 1 for 1st insert

	List<Interval> _list1 = new ArrayList<>(),
			// 2 for double counting
			_list2 = new ArrayList<>();

	boolean intersect( Interval i1, Interval i2 )
	{
		if ( i1._start >= i2._end || i1._end <= i2._start )
			// do NOT intersect
			return false;
		// do intersect
		return true;
	}

	Interval intersection( Interval i1, Interval i2 )
	{
		Interval shrink = new Interval( i1._start, i1._end );
		shrink._start = Math.max( i1._start, i2._start );
		shrink._end = Math.min( i1._end, i2._end );
		return shrink;
	}

	public MyCalendarTwo()
	{

	}

	Interval[] separate( Interval i1, Interval i2 )
	{
		// will result in 1 interval if start / end equals

		// else 2 intervals

		Interval[] re = new Interval[2];
		if ( i1._start >= i2._start )
		{
			Interval i = i2;
			i2 = i1;
			i1 = i;
		}

		re[0] = i1._start == i2._start ? null : new Interval( i1._start, i2._start );

		re[1] = i1._end == i2._end ? null : new Interval( Math.min( i1._end, i2._end ), Math.max( i1._end, i2._end ) );

		return re;
	}

	public boolean book( int start, int end )
	{
		Interval n = new Interval( start, end );
		for ( Interval i : _list2 )
		{
			// simply can't add due to third counting
			if ( intersect( i, n ) )
				return false;
		}
		// check if new interval intersects with any current interval
		boolean flag = false;
		List<Interval> nList = new ArrayList<>();
		for ( Interval i : _list1 )
		{
			// updates intersecting intervals
			if ( intersect( i, n ) )
			{
				flag = true;
				// intersection part goes into list 2,

				_list2.add( intersection( i, n ) );
				// rest goes into 1
				for ( Interval sep : separate( i, n ) )
				{
					if ( sep != null )
						nList.add( sep );
				}

			}
			else
				nList.add( i );
		}
		if ( !flag )
			nList.add( n );
		_list1 = new ArrayList<>( nList );
		return true;
	}

	public static void main( String[] args )
	{
		String[] op = { "MyCalendarTwo", "book", "book", "book", "book", "book", "book" };
		int[][] v = { {}, { 10, 20 }, { 50, 60 }, { 10, 40 }, { 5, 15 }, { 5, 10 }, { 25, 55 } };
		MyCalendarTwo myCalendar = new MyCalendarTwo();
		for ( int i = 1; i < op.length; i++ )
			System.out.println( myCalendar.book( v[i][0], v[i][1] ) );

	}
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */