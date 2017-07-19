import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutocompleteSystem
{
	TrieAS root = new TrieAS();

	public AutocompleteSystem( String[] sentences, int[] times )
	{
		for ( int i = 0; i < sentences.length; i++ )
		{
			root.update( sentences[i], times[i] );
		}
	}

	StringBuilder find = new StringBuilder();

	public List<String> input( char c )
	{
		if ( c == '#' )
		{
			root.update( find.toString(), 1 );
			find.delete( 0, find.length() );
			return new ArrayList<>();
		}
		find.append( c );
		List<String[]> list = new ArrayList<>();
		findStr( find.toString(), list );
		Collections.sort( list, new Comparator<String[]>()
		{

			@Override
			public int compare( String[] o1, String[] o2 )
			{
				int v1 = Integer.valueOf( o1[1] ), v2 = Integer.valueOf( o2[1] );
				return v1 != v2 ? v2 - v1 : o1[0].compareTo( o2[0] );
			}

		} );
		List<String> ret = new ArrayList<>();

		for ( int i = 0, n = list.size(); i < Math.min( 3, n ); i++ )
		{
			ret.add( list.get( i )[0] );
		}
		return ret;
	}

	void findStr( String string, List<String[]> list )
	{
		TrieAS r = root;
		for ( char k : string.toCharArray() )
		{
			int pos = k == ' ' ? 26 : k - 'a';
			if ( r._child[pos] == null )
				return;
			r = r._child[pos];
		}
		for ( String s : r.map.keySet() )
			list.add( new String[] { s, String.valueOf( r.map.get( s ) ) } );

	}

	public static void main( String[] args )
	{
		String[] s = { "i love you", "island", "iroman", "i love leetcode" };
		int[] t = { 5, 3, 2, 2 };
		AutocompleteSystem ats = new AutocompleteSystem( s, t );
		String[][] inp = { { "i" }, { " " }, { "a" }, { "#" }, { "i" }, { " " }, { "a" }, { "#" }, { "i" }, { " " }, { "a" }, { "#" } };
		for ( int i = 0; i < inp.length; i++ )
			System.out.println( ats.input( inp[i][0].toCharArray()[0] ) );
	}
}

class TrieAS
{
	TrieAS[] _child = new TrieAS[27];
	Map<String, Integer> map = new HashMap<>();
	List<String> _str = new ArrayList<>();
	List<Integer> _hotness = new ArrayList<>();

	void update( String string, int hotness )
	{
		TrieAS root = this;
		for ( char k : string.toCharArray() )
		{
			int pos = k == ' ' ? 26 : k - 'a';
			if ( root._child[pos] == null )
				root._child[pos] = new TrieAS();
			root.map.put( string, root.map.getOrDefault( string, 0 ) + hotness );
			root = root._child[pos];
		}
		root.map.put( string, root.map.getOrDefault( string, 0 ) + hotness );
	}

}
