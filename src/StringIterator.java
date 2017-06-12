import java.util.ArrayList;
import java.util.List;

public class StringIterator
{
	String expandedString = "";
	List<Integer> list = new ArrayList<>();
	int pos = 0;

	public StringIterator( String compressedString )
	{

		int i = 0, j = 0;
		StringBuilder sb = new StringBuilder();
		while ( i < compressedString.length() )
		{
			while ( j < compressedString.length() && !( compressedString.charAt( j ) <= '9' && compressedString.charAt( j ) >= '0' ) )
				j++;

			String s = compressedString.substring( i, j );
			expandedString += s;
			i = j;
			while ( j < compressedString.length() && compressedString.charAt( j ) <= '9' && compressedString.charAt( j ) >= '0' )
				j++;
			int val = Integer.valueOf( compressedString.substring( i, j ) );
			i = j;
			list.add( val );
		}

	}

	public char next()
	{
		if ( hasNext() )
		{
			list.set( pos, list.get( pos ) - 1 );
			char c = expandedString.charAt( pos );
			if ( list.get( pos ) <= 0 )
				pos++;
			return c;
		}
		else
			return ' ';
	}

	public boolean hasNext()
	{
		if ( pos < list.size() && list.get( pos ) > 0 )
			return true;
		return false;
	}

	public static void main( String[] args )
	{
		StringIterator s = new StringIterator( "L1e2t1C1o1d1e1" );

		String[] op = { "StringIterator", "next", "next", "next", "next", "next", "next", "hasNext", "next", "hasNext" };
		// {{"a1234567890b1234567890"},{},{},{},{},{},{},{},{},{}};
		for ( int i = 1; i < op.length; i++ )
		{
			if ( op[i].equals( "next" ) )
				System.out.println( s.next() );
			else
				System.out.println( s.hasNext() );
		}
		// {{"L1e2t1C1o1d1e1"},{},{},{},{},{},{},{},{},{}};
	}
}
