import java.util.Arrays;

class NumMatrix
{
	int[][] mat, rowSum;
	int m, n;

	public NumMatrix( int[][] matrix )
	{
		if ( matrix == null || matrix.length == 0 || matrix[0].length == 0 )
			return;
		mat = matrix;
		m = matrix.length;
		n = matrix[0].length;
		rowSum = new int[m][n + 1];
		for ( int i = 0; i < m; i++ )
		{
			for ( int j = 0; j < n; j++ )
			{
				rowSum[i][j + 1] = rowSum[i][j] + mat[i][j];
			}
		}

	}

	public void update( int row, int col, int val )
	{
		for ( int j = col; j < n; j++ )
		{
			rowSum[row][j + 1] += val - mat[row][col];
		}

		mat[row][col] = val;
	}

	public int sumRegion( int row1, int col1, int row2, int col2 )
	{
		int re = 0;
		for ( int i = row1; i <= row2; i++ )
		{
			re += rowSum[i][col2 + 1] - rowSum[i][col1];
		}
		return re;
	}

	public static void main( String[] args )
	{
		String[] ops = { "NumMatrix", "sumRegion", "update", "sumRegion" };

		int[][] n = { {}, { 2, 1, 4, 3 }, { 3, 2, 2 }, { 2, 1, 4, 3 } },
				v = { { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 }, { 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 }, { 1, 0, 3, 0, 5 } };
		NumMatrix na = new NumMatrix( v );
		for ( int i = 1; i < ops.length; i++ )
		{
			if ( ops[i].equals( "sumRegion" ) )
				System.out.println( na.sumRegion( n[i][0], n[i][1], n[i][2], n[i][3] ) );
			else
			{
				na.update( n[i][0], n[i][1], n[i][2] );
				System.out.println( "null" );
			}
			for ( int[] tm : na.rowSum )
				System.out.println( Arrays.toString( tm ) );
		}

	}
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */