import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

class RangeModule
{

	class Interval implements Comparable<Interval>
	{

		int _left, _right;

		public Interval( int left, int right )
		{
			_left = left;
			_right = right;
		}

		@Override
		public int compareTo( Interval o )
		{
			return this._right == o._right ? this._left - o._left : this._right - o._right;
		}

	}

	// keys are starting positions
	// values are ending positions
	TreeSet<Interval> ranges = new TreeSet<>();

	public RangeModule()
	{

	}

	public void addRange( int left, int right )
	{
		if ( right <= left )
			return;
		// iterator that are greater than left
		Iterator<Interval> itr = ranges.tailSet( new Interval( 0, left - 1 ) ).iterator();
		// merges all intervals that intersect new interval
		while ( itr.hasNext() )
		{
			Interval iv = itr.next();
			if ( right < iv._left )
				break;
			left = Math.min( left, iv._left );
			right = Math.max( right, iv._right );
			itr.remove();
		}
		ranges.add( new Interval( left, right ) );
	}

	public boolean queryRange( int left, int right )
	{

		// the greatest element that has least left
		Interval iv = ranges.higher( new Interval( 0, left ) );

		return ( iv != null && iv._left <= left && right <= iv._right );
	}

	public void removeRange( int left, int right )
	{
		Iterator<Interval> itr = ranges.tailSet( new Interval( 0, left ) ).iterator();
		List<Interval> todo = new ArrayList<>();
		while ( itr.hasNext() )
		{
			Interval iv = itr.next();
			if ( right < iv._left )
				break;
			if ( iv._left < left )
				todo.add( new Interval( iv._left, left ) );
			if ( right < iv._right )
				todo.add( new Interval( right, iv._right ) );
			itr.remove();
		}
		ranges.addAll( todo );
	}

	public static void main( String[] args )
	{
		String[] op = { "RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange" };
		int[][] v = { {}, { 10, 20 }, { 14, 16 }, { 10, 14 }, { 13, 15 }, { 16, 17 } };
		RangeModule r = new RangeModule();
		for ( int i = 1; i < op.length; i++ )
		{
			if ( op[i].equals( "addRange" ) )
			{
				r.addRange( v[i][0], v[i][1] );
				System.out.printf( "Add Range %d, %d\n", v[i][0], v[i][1] );
			}
			if ( op[i].equals( "removeRange" ) )
			{
				r.removeRange( v[i][0], v[i][1] );
				System.out.printf( "Removed Range %d, %d\n", v[i][0], v[i][1] );
			}
			if ( op[i].equals( "queryRange" ) )
			{

				System.out.printf( "Query Range %d, %d %b\n", v[i][0], v[i][1], r.queryRange( v[i][0], v[i][1] ) );
			}
			for ( Interval k : r.ranges )
				System.out.printf( "%d_%d ", k._left, k._right );
			System.out.println();
		}

	}
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */