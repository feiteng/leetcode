

import java.util.ArrayList;
import java.util.List;

public class NestedIntegerImplement implements NestedInteger
{
	Integer _integer;
	List<NestedInteger> _list;
	boolean _integerFlag = false;

	public NestedIntegerImplement()
	{

	}

	public NestedIntegerImplement( Integer integer )
	{
		_integer = integer;
		_integerFlag = true;
	}

	public NestedIntegerImplement( List<Integer> list )
	{
		_list = new ArrayList<>();
		for ( Integer k : list )
			_list.add( new NestedIntegerImplement( k ) );
		_integerFlag = false;
	}

	@Override
	public boolean isInteger()
	{
		return _integerFlag;
	}

	@Override
	public Integer getInteger()
	{
		return _integer;
	}

	@Override
	public List<NestedInteger> getList()
	{
		return _list;
	}

	@Override
	public void add( NestedInteger tmp )
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void add( NestedIntegerImplement tmp )
	{
		// TODO Auto-generated method stub

	}

}
