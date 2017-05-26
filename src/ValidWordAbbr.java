

import java.util.HashSet;
import java.util.Set;

public class ValidWordAbbr
{
	Set<String> _set;

	public ValidWordAbbr( String[] dictionary )
	{
		_set = new HashSet<>();
		for ( String d : dictionary )
			_set.add( abbreviate( d ) );
	}

	public boolean isUnique( String word )
	{
		return _set.contains( abbreviate( word ) );
	}

	String abbreviate( String string )
	{
		return string.charAt( 0 ) + String.valueOf( string.length() - 2 >= 0 ? string.length() - 2 : "" ) + string.charAt( string.length() - 1 );
	}
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */
