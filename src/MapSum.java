class MapSum
{
	TrieMapSum _root = new TrieMapSum();

	/** Initialize your data structure here. */
	public MapSum()
	{

	}

	public void insert( String key, int val )
	{
		_root.insert( key, val );
	}

	public int sum( String prefix )
	{
		return _root.lookup( prefix );
	}
}

class TrieMapSum
{

	int _val = 0, _sum = 0;

	TrieMapSum[] _ch = new TrieMapSum[26];

	void insert( String v, int val )
	{
		int oriVal = find( v );

		TrieMapSum root = this;
		root._sum += val - oriVal;
		for ( char k : v.toCharArray() )
		{
			if ( root._ch[k - 'a'] == null )
				root._ch[k - 'a'] = new TrieMapSum();
			root = root._ch[k - 'a'];
			root._sum += val - oriVal;
		}
		root._val = val;
	}

	int find( String v )
	{
		TrieMapSum root = this;
		for ( char k : v.toCharArray() )
		{
			if ( root._ch[k - 'a'] == null )
				return 0;
			root = root._ch[k - 'a'];
		}
		return root._val;
	}

	int lookup( String v )
	{
		TrieMapSum root = this;
		for ( char k : v.toCharArray() )
		{
			if ( root._ch[k - 'a'] == null )
				return 0;
			root = root._ch[k - 'a'];
		}
		return root._sum;
	}
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */