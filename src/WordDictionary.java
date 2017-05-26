

class WordNode
{
	boolean _wordEnd = false;
	WordNode[] child = new WordNode[26];

}

public class WordDictionary
{
	WordNode _root;

	public WordDictionary()
	{
		_root = new WordNode();
	}

	// Adds a word into the data structure.
	public void addWord( String word )
	{
		WordNode root = _root;
		for ( char c : word.toCharArray() )
		{
			if ( root.child[c - 'a'] == null )
				root.child[c - 'a'] = new WordNode();
			root = root.child[c - 'a'];
		}
		root._wordEnd = true;
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search( String word )
	{
		return wordSearch( _root, word, 0 );
	}

	boolean wordSearch( WordNode root, String word, int pos )
	{
		if ( word.length() == pos && root._wordEnd )
			return true;
		if ( word.length() == pos )
			return false;
		char c = word.charAt( pos );

		if ( c != '.' )
		{
			if ( root.child[c - 'a'] == null )
				return false;
			return wordSearch( root.child[c - 'a'], word, pos + 1 );
		}
		else
		{
			for ( WordNode newRoot : root.child )
			{
				if ( newRoot == null )
					continue;
				if ( wordSearch( newRoot, word, pos + 1 ) )
					return true;
			}
		}

		return false;
	}

	public static void main( String[] args )
	{
		WordDictionary w = new WordDictionary();
		String[] ops = { "WordDictionary", "addWord", "addWord", "addWord", "search", "search", "search", "search" };
		String[][] words = { {}, { "bad" }, { "dad" }, { "mad" }, { "pad" }, { "bad" }, { ".ad" }, { "b.." } };
		for ( int i = 1; i < ops.length; i++ )
		{
			if ( ops[i].equals( "addWord" ) )
			{
				w.addWord( words[i][0] );
			}
			else
				System.out.println( w.search( words[i][0] ) );
		}

	}
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");