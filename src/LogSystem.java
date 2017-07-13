import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogSystem
{

	List<String> unit = Arrays.asList( new String[] { "Year", "Month", "Day", "Hour", "Minute", "Second" } );
	int[] pos = { 4, 7, 10, 13, 16, 19 };
	List<String[]> timeStamps = new ArrayList<>();

	int[] _monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	Map<Double, Integer> days = new HashMap<>();

	public LogSystem()
	{

	}

	public void put( int id, String timestamp )
	{
		timeStamps.add( new String[] { String.valueOf( id ), timestamp } );
	}

	public List<Integer> retrieve( String s, String e, String gra )
	{
		int p = pos[unit.indexOf( gra )];
		List<Integer> list = new ArrayList<>();
		for ( String[] t : timeStamps )
		{
			if ( t[1].substring( 0, p ).compareTo( s.substring( 0, p ) ) >= 0 && t[1].substring( 0, p ).compareTo( e.substring( 0, p ) ) <= 0 )
				list.add( Integer.valueOf( t[0] ) );

		}
		return list;

	}

	String fillS = "2000:1:1:0:0:0", fillE = "2017:12:31:23:59:59";

	public static void main( String[] args )
	{
		System.out.println( "2017:01:02:23:59:59".compareTo( "2017:01:02:23:59:58" ) );

		String[] op = { "LogSystem", "put", "put", "put", "retrieve", "retrieve" };
		String[][] ret = { { "2017:01:01:23:59:58", "2017:01:02:23:59:58", "Second" }, { "2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour" } },
				t = { { "1", "2017:01:01:23:59:59" }, { "2", "2017:01:02:23:59:59" }, { "3", "2016:01:01:00:00:00" } };
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