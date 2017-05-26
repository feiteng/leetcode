

public class NumArray
{
	int[] _nums, _sums;

	public NumArray( int[] nums )
	{
		_nums = nums;
		_sums = new int[nums.length + 1];
		_sums[0] = 0;
		updateSums();
	}

	void updateSums()
	{
		for ( int i = 0; i < _nums.length; i++ )
			_sums[i + 1] = _sums[i] + _nums[i];
	}

	public void update( int i, int val )
	{
		_nums[i] = val;
		updateSums();
	}

	public int sumRange( int i, int j )
	{
		return _sums[j + 1] - _sums[i];

	}

	public static void main( String[] args )
	{

		int[] n = { -2, 0, 3, -5, 2, -1 };
		NumArray na = new NumArray( n );
		System.out.println( na.sumRange( 0, 2 ) );
		// na.sumRange( 0, 2 );
		System.out.println( na.sumRange( 2, 5 ) );
		System.out.println( na.sumRange( 0, 5 ) );
	}

	String[] ops = {};
	int[][] vals = { { 1, 3, 5 }, { 0, 2 }, { 1, 2 }, { 0, 2 } };

}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
