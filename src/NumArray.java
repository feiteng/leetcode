class NumArray
{
	int[] vals, BIT;
	int n;

	public NumArray( int[] nums )
	{
		vals = nums;
		BIT = new int[n + 1];
		n = vals.length;
		for ( int i = 0; i < n; i++ )
		{
			constructBIT( i, vals[i] );
		}
	}

	void constructBIT( int i, int val )
	{
		i++;
		while ( i <= n )
		{
			BIT[i] += val;
			i += ( i & -i );
		}

	}

	public void update( int i, int val )
	{
		constructBIT( i, val - vals[i] );
		vals[i] = val;
	}

	public int sumRange( int i, int j )
	{
		return sumBIT( j ) - sumBIT( i );
	}

	int sumBIT( int k )
	{
		k++;
		int re = 0;
		while ( k > 0 )
		{
			re += BIT[k];
			k -= ( k & -k );
		}
		return re;
	}

	/**
	 * Your NumArray object will be instantiated and called as such:
	 * NumArray obj = new NumArray(nums);
	 * obj.update(i,val);
	 * int param_2 = obj.sumRange(i,j);
	 */
	// class NumArray
	// {
	// SegmentTree _root;
	// int n;
	//
	// class SegmentTree
	// {
	// int _lBound = 0, _rBound = 0, _sum = 0;
	// SegmentTree _left = null, _right = null;
	// }
	//
	// void printSegTree( SegmentTree t )
	// {
	// if ( t == null )
	// return;
	// if ( t._lBound == t._rBound )
	// System.out.printf( " %d", t._sum );
	// printSegTree( t._left );
	// printSegTree( t._right );
	// }
	//
	// SegmentTree buidSegTree( int[] vals, int left, int right )
	// {
	// SegmentTree re = new SegmentTree();
	// if ( left == right )
	// {
	// re._lBound = re._rBound = left;
	// re._sum = vals[left];
	// return re;
	// }
	// if ( left > right )
	// return null;
	// int mid = ( left + right ) / 2;
	// re._lBound = left;
	// re._rBound = right;
	// re._left = buidSegTree( vals, left, mid );
	// re._right = buidSegTree( vals, mid + 1, right );
	// if ( left == right )
	// re._sum = vals[left];
	// else
	// re._sum = re._left._sum + re._right._sum;
	// return re;
	// }
	//
	// public NumArray( int[] nums )
	// {
	// n = nums.length;
	// _root = buidSegTree( nums, 0, n - 1 );
	// }
	//
	// public void update( int i, int val )
	// {
	// updateSegTree( i, val, _root );
	// }
	//
	// void updateSegTree( int i, int val, SegmentTree t )
	// {
	// if ( t._lBound == i && t._rBound == i )
	// {
	// t._sum = val;
	// return;
	// }
	// int m = ( t._lBound + t._rBound ) / 2;
	// if ( i <= m )
	// updateSegTree( i, val, t._left );
	// else
	// updateSegTree( i, val, t._right );
	// t._sum = t._left._sum + t._right._sum;
	// }
	//
	// public int sumRange( int i, int j )
	// {
	// return sumHelper( 0, n - 1, i, j, _root );
	// }
	//
	// int sumHelper( int L, int R, int i, int j, SegmentTree p )
	// {
	// if ( j < L || R < i )
	// return 0;
	// if ( i <= L && R <= j )
	// return p._sum;
	// int M = ( L + R ) / 2;
	// return sumHelper( L, M, i, j, p._left ) +
	// sumHelper( M + 1, R, i, j, p._right );
	// }
	//
	public static void main( String[] args )
	{

		String[] ops = { "NumArray", "sumRange", "update", "sumRange", "sumRange",
				"update", "update", "sumRange", "sumRange", "update", "update" };
		int[][] n = { {}, { 5, 6 }, { 9, 27 }, { 2, 3 }, { 6, 7 },
				{ 1, -82 }, { 3, -72 }, { 3, 7 }, { 1, 8 }, { 5, 13 }, { 4, -67 } };
		int[] v = { -28, -39, 53, 65, 11, -56, -65, -39, -43, 97 };
		NumArray na = new NumArray( v );
		for ( int i = 1; i < ops.length; i++ )
		{
			if ( ops[i].equals( "sumRange" ) )
				System.out.println( na.sumRange( n[i][0], n[i][1] ) );
			else
			{
				na.update( n[i][0], n[i][1] );
				System.out.println( "null" );
			}
			// na.printSegTree( na._root );
			System.out.println();
		}
	}

}

/// **
// * Your NumArray object will be instantiated and called as such:
// * NumArray obj = new NumArray(nums);
// * obj.update(i,val);
// * int param_2 = obj.sumRange(i,j);
// */
