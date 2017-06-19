import java.util.LinkedList;
import java.util.Queue;

public class MovingAverage
{
	Queue<Integer> queue = new LinkedList<>();
	int _size, _sum = 0;

	/** Initialize your data structure here. */
	public MovingAverage( int size )
	{
		_size = size;
	}

	public double next( int val )
	{
		if ( queue.size() == _size )
		{
			_sum -= queue.poll();
			queue.add( val );
			_sum += val;
		}
		else
		{ // add items in
			queue.add( val );
			_sum += val;
		}
		return ( (double) _sum / queue.size() );

	}
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */