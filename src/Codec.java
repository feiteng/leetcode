import java.util.ArrayList;
import java.util.List;

public class Codec
{

	// Encodes a list of strings to a single string.
	public String encode( List<String> strs )
	{
		StringBuilder sb = new StringBuilder();
		for ( String s : strs )
			sb.append( "" + s.length() + "." + s );
		return sb.toString();

	}

	// Decodes a single string to a list of strings.
	public List<String> decode( String s )
	{
		List<String> list = new ArrayList<>();
		int i = 0;
		while ( i < s.length() )
		{
			int p = s.indexOf( ".", i );
			int len = Integer.valueOf( s.substring( i, p ) );
			list.add( s.substring( p + 1, len + p + 1 ) );
			i = len + p + 1;
		}

		return list;
	}

	public static void main( String[] args )
	{
		Codec c = new Codec();
		List<String> list = new ArrayList<>();
		String[] strings = { "C#", "&", "~Xp|F", "R4QBf9g=_" };
		for ( String string : strings )
			list.add( string );
		System.out.println( c.decode( c.encode( list ) ) );
	}
}
