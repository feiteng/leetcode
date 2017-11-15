import java.util.ArrayList;
import java.util.List;

class RangeModule
{

	class Interval
	{
		int _start, _end;

		public Interval( int start, int end )
		{
			_start = start;
			_end = end;
		}

		boolean intersect( Interval v )
		{
			if ( v._start >= this._end || v._end <= this._start )
				return false;
			return true;
		}

		Interval merge( Interval v )
		{
			Interval n = new Interval( _start, _end );
			n._start = Math.min( n._start, v._start );
			n._end = Math.min( n._end, v._end );
			return n;
		}

	}

	// 0 to 10^9
	List<Interval> list = new ArrayList<>();

	public RangeModule()
	{

	}

	public void addRange( int left, int right )
	{
		Interval v = new Interval( left, right );
		List<Interval> newList = new ArrayList<>();
		for ( Interval k : list )
		{
			if ( v.intersect( k ) )
				newList.add( v.merge( k ) );
		}
	}

	public boolean queryRange( int left, int right )
	{

	}

	public void removeRange( int left, int right )
	{

	}
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */