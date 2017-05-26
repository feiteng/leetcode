


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer>
{
	List<Integer> list = new ArrayList<>();
	int _step, _count;

	public PeekingIterator( Iterator<Integer> iterator )
	{
		// initialize any member here.
		while ( iterator.hasNext() )
			list.add( iterator.next() );
		_count = list.size();
		_step = 0;
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek()
	{
		return list.get( _step );
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next()
	{
		int step = _step;
		_step++;
		return list.get( step );

	}

	@Override
	public boolean hasNext()
	{
		return _count - _step == 0 ? false : true;

	}
}