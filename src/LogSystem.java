import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogSystem
{
	int[] _monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	Map<Double, Integer> days = new HashMap<>();

	public LogSystem()
	{

	}

	public void put( int id, String timestamp )
	{
		days.put( parse( timestamp ), id );
	}

	public List<Integer> retrieve( String s, String e, String gra )
	{
		List<Integer> list = new ArrayList<>();

		double start = parse( fillString( s, gra, 0 ) ), sEnd = parse( fillString( e, gra, 1 ) );

		for ( double d : days.keySet() )
		{
			if ( d >= start && d <= sEnd )
				list.add( days.get( d ) );
		}
		return list;

	}

	String fillS = "2000:1:1:0:0:0", fillE = "2017:12:31:23:59:59";

	String fillString( String s, String gra, int st )
	{
		int g = getGr( gra );
		String fill = st == 0 ? fillS : fillE;
		String[] sp = s.split( ":" ), f = fill.split( ":" );
		for ( int i = g + 1; i < 6; i++ )
		{
			sp[i] = f[i];
		}
		return String.join( ":", sp );
	}

	int getGr( String gra )
	{
		if ( gra.charAt( 0 ) == 'Y' )
			return 0;
		if ( gra.charAt( 0 ) == 'M' && gra.charAt( 1 ) == 'o' )
			return 1;
		if ( gra.charAt( 0 ) == 'D' )
			return 2;
		if ( gra.charAt( 0 ) == 'H' )
			return 3;
		if ( gra.charAt( 0 ) == 'M' && gra.charAt( 1 ) == 'i' )
			return 4;
		return 5;
	}

	double parse( String timeStamp )
	{
		String[] split = timeStamp.split( ":" );
		double day = 0;

		int year = Integer.valueOf( split[0] ) - 2000, leap = 0;
		if ( year % 4 == 0 )
			leap += year / 4;
		if ( year == 0 )
			leap += 1;
		day += year * 365;
		for ( int i = 0; i < Integer.valueOf( split[1] ) - 1; i++ )
			day += _monthDays[i];
		day += Integer.valueOf( split[2] ) + leap;
		double sec = Integer.valueOf( split[3] ) * 3600 + Integer.valueOf( split[4] ) * 60 + Integer.valueOf( split[5] );
		sec /= ( 24 * 3600.0 );
		return day + sec;
	}

	public static void main( String[] args )
	{
		String[] op = { "LogSystem", "put", "put", "put", "retrieve", "retrieve" };
		String[][] ret = { { "2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year" }, { "2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour" } },
				t = { { "1", "2017:01:01:23:59:59" }, { "2", "2017:01:01:22:59:59" }, { "3", "2016:01:01:00:00:00" } };
		LogSystem s = new LogSystem();
		int k = 0, v = 0;
		for ( int i = 1; i < op.length; i++ )
		{
			if ( op[i].equals( "put" ) )
			{
				s.put( Integer.valueOf( t[v][0] ), t[v][1] );
				v++;
			}
			else
			{
				System.out.println( s.retrieve( ret[k][0], ret[k][1], ret[k][2] ) );
				k++;
			}
		}
	}
}
/**
 * Your LogSystem object will be instantiated and called as such:
 * LogSystem obj = new LogSystem();
 * obj.put(id,timestamp);
 * List<Integer> param_2 = obj.retrieve(s,e,gra);
 */