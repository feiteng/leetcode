


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer>
{
	int _count, _step;
	List<NestedInteger> _nestedList;

	public NestedIterator( List<NestedInteger> nestedList )
	{
		_nestedList = new ArrayList<>();
		Integerflat( nestedList );
		_count = _nestedList.size();
		_step = 0;

	}

	public void Integerflat( List<NestedInteger> nestedList )
	{
		for ( NestedInteger n : nestedList )
		{
			if ( n.isInteger() )
				_nestedList.add( n );
			else
			{
				Integerflat( n.getList() );
			}
		}
	}

	@Override
	public Integer next()
	{
		Integer ret = _nestedList.get( _step ).getInteger();
		_step++;

		return ret;
	}

	@Override
	public boolean hasNext()
	{
		return _count > _step;
	}
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */