


public class NumMatrix
{
	int[][] sum;

	public NumMatrix( int[][] matrix )
	{
		sum = new int[matrix.length + 1][matrix[0].length + 1];
		for ( int i = 0; i < matrix.length; i++ )
			for ( int j = 0; j < matrix[0].length; j++ )
				sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + matrix[i][j];

	}

	public int sumRegion( int row1, int col1, int row2, int col2 )
	{
		return sum[row2 + 1][col2 + 1] - sum[row2 + 1][col1] - sum[row1][col2 + 1] + sum[row1][col1];
	}

	void print()
	{
		for ( int i = 0; i < sum.length; i++ )
		{
			for ( int j = 0; j < sum[0].length; j++ )
				System.out.printf( "%d ", sum[i][j] );
			System.out.println();
		}

	}

	public static void main( String[] args )
	{
		int[][] n = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		NumMatrix m = new NumMatrix( n );
		m.print();
	}
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */