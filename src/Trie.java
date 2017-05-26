

class TrieNode
{
	char _c;
	boolean _worEnd = false;
	TrieNode[] _child = new TrieNode[26];

	public TrieNode()
	{
	}

	public TrieNode( char c )
	{
		_c = c;
	}

}

public class Trie
{
	TrieNode _root;

	public Trie()
	{
		_root = new TrieNode();
	}

	// Inserts a word into the trie
	public void insert( String word )
	{
		TrieNode traverse = _root;
		for ( char c : word.toCharArray() )
		{
			if ( traverse._child[c - 'a'] == null )
				traverse._child[c - 'a'] = new TrieNode( c );
			traverse = traverse._child[c - 'a'];
		}
		traverse._worEnd = true;
	}

	// Returns if the word is in the trie.
	public boolean search( String word )
	{
		TrieNode traverse = _root;
		for ( char c : word.toCharArray() )
		{
			if ( traverse._child[c - 'a'] == null )
				return false;
			traverse = traverse._child[c - 'a'];
		}
		if ( traverse._worEnd )
			return true;
		return false;
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith( String prefix )
	{
		TrieNode traverse = _root;
		for ( char c : prefix.toCharArray() )
		{
			if ( traverse._child[c - 'a'] == null )
				return false;
			traverse = traverse._child[c - 'a'];
		}
		return true;
	}

	void rootPrint( String prefix, TrieNode root )
	{
		TrieNode traverse = root;
		for ( TrieNode t : traverse._child )
		{
			if ( t == null )
				continue;
			String nextPrefix = prefix + String.valueOf( t._c );
			if ( t._worEnd )
				System.out.println( nextPrefix );
			rootPrint( nextPrefix, t );
		}

	}

	public static void main( String[] args )
	{
		String[] ops = { "Trie", "insert", "insert", "insert", "insert", "insert", "insert", "search", "search", "search", "search", "search",
				"search", "search", "search", "search", "startsWith", "startsWith", "startsWith", "startsWith", "startsWith", "startsWith",
				"startsWith", "startsWith", "startsWith" };
		String[][] vals = { {}, { "app" }, { "apple" }, { "beer" }, { "add" }, { "jam" }, { "rental" }, { "apps" }, { "app" }, { "ad" },
				{ "applepie" }, { "rest" }, { "jan" }, { "rent" }, { "beer" }, { "jam" }, { "apps" }, { "app" }, { "ad" }, { "applepie" }, { "rest" },
				{ "jan" }, { "rent" }, { "beer" }, { "jam" } };

		Trie t = new Trie();
		System.out.println( "null" );
		for ( int i = 1; i < ops.length; i++ )
		{
			if ( ops[i].equals( "insert" ) )
			{
				t.insert( vals[i][0] );
				System.out.println( "null" );
			}
			if ( ops[i].equals( "search" ) )
				System.out.println( t.search( vals[i][0] ) );
			if ( ops[i].equals( "startsWith" ) )

				System.out.println( t.startsWith( vals[i][0] ) );
		}

	}
}