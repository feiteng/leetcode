import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

// public class Codec
// {
// Map<String, String> _longUrl = new HashMap<>(), _shortUrl = new HashMap<>();
// String string = "http://tinyurl.com/", code = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
// int len = code.length();
// Random r = new Random();
//
// // Encodes a URL to a shortened URL.
// public String encode( String longUrl )
// {
// _longUrl.put( null, null );
// if ( _longUrl.containsKey( longUrl ) )
// return _longUrl.get( longUrl );
// String key = null;
// StringBuilder sb = new StringBuilder();
// while ( _longUrl.containsKey( key ) )
// {
// for ( int i = 0; i < 10; i++ ) // length of 10
// sb.append( code.charAt( r.nextInt( len ) ) );
// key = sb.toString();
// }
// _longUrl.put( longUrl, key );
// _shortUrl.put( key, longUrl );
// return string.concat( key );
// }
//
// // Decodes a shortened URL to its original URL.
// public String decode( String shortUrl )
// {
// return _shortUrl.get( shortUrl.substring( 19, shortUrl.length() ) );
// }
//
// public static void main( String[] args )
// {
// Codec c = new Codec();
// System.out.println( c.decode( c.encode( "https://leetcode.com/problems/design-tinyurl" ) ) );
// }
// }

// public class Codec
// {
//
// // Encodes a tree to a single string.
// public String serialize( TreeNode root )
// {
// list.clear();
// preOrder( root );
// return String.join( ",", list );
// }
//
// // Decodes your encoded data to tree.
// public TreeNode deserialize( String data )
// {
// if ( data.length() < 1 )
// return null;
// String[] sVals = data.split( "," );
// int[] vals = new int[sVals.length];
// for ( int i = 0; i < sVals.length; i++ )
// vals[i] = Integer.parseInt( sVals[i] );
// return construct( vals );
// }
//
// TreeNode construct( int[] val )
// {
// if ( val.length < 1 )
// return null;
// int i = 1;
// TreeNode root = new TreeNode( val[0] );
// while ( i < val.length && val[i] < val[0] )
// i++;
// int[] left = Arrays.copyOfRange( val, 1, i ), right = Arrays.copyOfRange( val, i, val.length );
// root.left = construct( left );
// root.right = construct( right );
// return root;
// }
//
// List<String> list = new ArrayList<>();
//
// void preOrder( TreeNode root )
// {
// if ( root == null )
// return;
// list.add( String.valueOf( root.val ) );
// preOrder( root.left );
// preOrder( root.right );
// }
// }
public class Codec
{

	// Encodes a tree to a single string.
	// level order representation
	public String serialize( TreeNode root )
	{
		if ( root == null )
			return "";
		Queue<TreeNode> queue = new LinkedList<>(), tQueue = new LinkedList<>();
		List<String> list = new ArrayList<>();
		queue.add( root );
		while ( !queue.isEmpty() )
		{
			tQueue.clear();
			while ( !queue.isEmpty() )
			{
				TreeNode p = queue.poll();
				if ( p != null )
				{
					tQueue.add( p.left );
					tQueue.add( p.right );
					list.add( String.valueOf( p.val ) );
				}
				else
					list.add( "null" );
			}
			queue.addAll( tQueue );
		}
		return String.join( ",", list );
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize( String data )
	{
		if ( data.equals( "" ) )
			return null;
		String[] sv = data.split( "," );
		TreeNode root = new TreeNode( Integer.valueOf( sv[0] ) );
		Queue<TreeNode> queue = new LinkedList<>();

		queue.add( root );
		int k = 1;
		while ( k < sv.length )
		{
			TreeNode tmp = queue.poll();

			if ( !sv[k].equals( "null" ) )
			{
				tmp.left = new TreeNode( Integer.parseInt( sv[k] ) );
				queue.add( tmp.left );
			}
			k++;
			if ( k < sv.length && !sv[k].equals( "null" ) )
			{
				tmp.right = new TreeNode( Integer.parseInt( sv[k] ) );
				queue.add( tmp.right );
			}
			k++;
		}
		return root;
	}

	public static void main( String[] args )
	{
		Codec cd = new Codec();
		TreeNode r = new TreeNode( 1 );
		System.out.println( cd.serialize( r ) );
		cd.deserialize( "1" ).print();
	}
}