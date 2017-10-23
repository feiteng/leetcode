class NumArray
{
	int[] vals;
	int n;

	public NumArray( int[] nums )
	{
		n = nums.length;
		vals = new int[2 * n];
		for ( int i = 0; i < n; i++ )
			vals[i + n] = nums[i];
		for ( int i = n - 1; i > 0; i-- )
		{
			int a = i * 2 >= 2 * n ? 0 : vals[i * 2], b = i * 2 + 1 >= 2 * n ? 0 : vals[i * 2 + 1];
			vals[i] = a + b;
		}

	}

	public void update( int i, int val )
	{
		int oldval = vals[i + n];
		int k = i + n;
		while ( k > 0 )
		{
			vals[k] += val - oldval;
			k = k / 2;
		}
	}

	public int sumRange( int i, int j )
	{
		return sumHelper( 0, n - 1, i, j, 1 );
	}

	int sumHelper( int L, int R, int i, int j, int p )
	{
		if ( j < L || R < i || p >= 2 * n )
			return 0;
		if ( i <= L && R <= j )
			return vals[p];
		int left = p * 2, right = p * 2 + 1, M = ( L + R ) / 2;
		return sumHelper( L, M, i, j, left ) +
				sumHelper( M + 1, R, i, j, right );
	}

	public static void main( String[] args )
	{

		String[] ops = { "NumArray", "sumRange", "update", "sumRange", "sumRange", "update", "update", "sumRange", "sumRange", "update", "update" };
		int[][] n = { {}, { 5, 6 }, { 9, 27 }, { 2, 3 }, { 6, 7 }, { 1, -82 }, { 3, -72 }, { 3, 7 },
				{ 1, 8 }, { 5, 13 }, { 4, -67 } };
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
		}
	}

}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
