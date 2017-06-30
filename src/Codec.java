import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// public class Codec
// {
//
// // Encodes a list of strings to a single string.
// public String encode( List<String> strs )
// {
// StringBuilder sb = new StringBuilder();
// for ( String s : strs )
// sb.append( "" + s.length() + "." + s );
// return sb.toString();
//
// }
//
// // Decodes a single string to a list of strings.
// public List<String> decode( String s )
// {
// List<String> list = new ArrayList<>();
// int i = 0;
// while ( i < s.length() )
// {
// int p = s.indexOf( ".", i );
// int len = Integer.valueOf( s.substring( i, p ) );
// list.add( s.substring( p + 1, len + p + 1 ) );
// i = len + p + 1;
// }
//
// return list;
// }
//
// public static void main( String[] args )
// {
// Codec c = new Codec();
// List<String> list = new ArrayList<>();
// String[] strings = { "C#", "&", "~Xp|F", "R4QBf9g=_" };
// for ( String string : strings )
// list.add( string );
// System.out.println( c.decode( c.encode( list ) ) );
// }
// }

public class Codec
{
	Map<String, String> _longUrl = new HashMap<>(), _shortUrl = new HashMap<>();
	String string = "http://tinyurl.com/", code = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	int len = code.length();
	Random r = new Random();

	// Encodes a URL to a shortened URL.
	public String encode( String longUrl )
	{
		_longUrl.put( null, null );
		if ( _longUrl.containsKey( longUrl ) )
			return _longUrl.get( longUrl );
		String key = null;
		StringBuilder sb = new StringBuilder();
		while ( _longUrl.containsKey( key ) )
		{
			for ( int i = 0; i < 10; i++ ) // length of 10
				sb.append( code.charAt( r.nextInt( len ) ) );
			key = sb.toString();
		}
		_longUrl.put( longUrl, key );
		_shortUrl.put( key, longUrl );
		return string.concat( key );
	}

	// Decodes a shortened URL to its original URL.
	public String decode( String shortUrl )
	{
		return _shortUrl.get( shortUrl.substring( 19, shortUrl.length() ) );
	}

	public static void main( String[] args )
	{
		Codec c = new Codec();
		System.out.println( c.decode( c.encode( "https://leetcode.com/problems/design-tinyurl" ) ) );
	}
}
