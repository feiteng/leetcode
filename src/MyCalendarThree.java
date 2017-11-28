import java.util.TreeMap;

class MyCalendarThree
{

	TreeMap<Integer, Integer> _tMap = new TreeMap<>();

	public MyCalendarThree()
	{

	}

	int maxCount = 0;

	public int book( int start, int end )
	{
		_tMap.put( start, _tMap.getOrDefault( start, 0 ) + 1 );
		_tMap.put( end, _tMap.getOrDefault( end, 0 ) - 1 );
		int count = 0;
		for ( int k : _tMap.keySet() )
		{
			maxCount = Math.max( maxCount, count + _tMap.get( k ) );
			count += _tMap.get( k );
		}

		return maxCount;

	}

	public static void main( String[] args )
	{
		String[] op = { "MyCalendarThree", "book", "book", "book", "book", "book", "book", "book", "book", "book", "book" };
		int[][] v = { {}, { 26, 35 }, { 26, 32 }, { 25, 32 }, { 18, 26 }, { 40, 45 }, { 19, 26 }, { 48, 50 }, { 1, 6 }, { 46, 50 }, { 11, 18 } };
		MyCalendarThree myCalendar = new MyCalendarThree();
		for ( int i = 1; i < op.length; i++ )
			System.out.println( myCalendar.book( v[i][0], v[i][1] ) );

	}
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */