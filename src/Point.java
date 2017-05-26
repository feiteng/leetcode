

public class Point
{
	int x;
	int y;

	Point()
	{
		x = 0;
		y = 0;
	}

	Point( int a, int b )
	{
		x = a;
		y = b;
	}

	static Point[] buildPointArray( int[][] a )
	{
		Point[] point = new Point[a.length];
		for ( int i = 0; i < a.length; i++ )
		{
			point[i] = new Point( a[i][0], a[i][1] );
		}
		return point;
	}
}
