

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Interval
{
	int start;
	int end;

	Interval()
	{
		start = 0;
		end = 0;
	}

	Interval( int s, int e )
	{
		start = s;
		end = e;
	}
}

/**
 * Definition for an interval.
 * public class Interval {
 * int start;
 * int end;
 * Interval() { start = 0; end = 0; }
 * Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class SummaryRanges
{
	Map<Integer, Integer> map = new HashMap<>(), range = new HashMap<>();

	/** Initialize your data structure here. */
	public SummaryRanges()
	{

	}

	public void addNum( int val )
	{
		for ( int k : range.keySet() )
		{
			if ( k <= val && val <= range.get( k ) )
				return;
		}
		int head = map.containsKey( val - 1 ) ? map.get( val - 1 ) : 0,
				tail = map.containsKey( val + 1 ) ? map.get( val + 1 ) : 0,
				sum = head + tail + 1;
		map.put( val, sum );
		if ( head > 0 )
		{
			map.put( val - head, sum );
			map.remove( val - 1 );
			range.put( val - head, sum );
		}
		map.put( val + tail, sum );
	}

	public List<Interval> getIntervals()
	{
		return null;

	}
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */