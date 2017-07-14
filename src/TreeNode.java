
// Definition for binary tree
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TreeNode class from leetcode, with extra constructors and print functions
 * 
 * @author Rutter
 *
 */

public class TreeNode
{
	int val;
	TreeNode left;
	TreeNode right;

	static String _seprator = ",";
	String chara = "null";

	public TreeNode()
	{
		// TODO Auto-generated constructor stub
	}

	TreeNode( int x )
	{
		val = x;
	}

	/**
	 * Constructs Tree with input String in level order
	 * A "null" means no node added for root, and will be ignored in next level
	 * 
	 * @param c
	 */
	TreeNode( String[] c )
	{
		// List<TreeNode> l = new ArrayList<TreeNode>();
		Queue<TreeNode> queue = new LinkedList<>();

		TreeNode tmp;
		this.val = Integer.parseInt( c[0] );
		queue.add( this );
		int k = 1;
		while ( k < c.length )
		{
			tmp = queue.poll();

			if ( c[k] != chara )
			{
				tmp.left = new TreeNode( Integer.parseInt( c[k] ) );
				queue.add( tmp.left );
			}
			k++;
			if ( k < c.length && c[k] != chara )
			{
				tmp.right = new TreeNode( Integer.parseInt( c[k] ) );
				queue.add( tmp.right );
			}
			k++;
		}
	}

	/**
	 * Constructs Tree with input String in level order
	 * Constructs full tree
	 * 
	 * @param c
	 */

	TreeNode( char[] c )
	{
		ArrayList<TreeNode> l = new ArrayList<TreeNode>();
		TreeNode tmp;
		this.val = c[0] - '0';
		l.add( this );
		for ( int i = 0; i < c.length / 2; i++ )
		{
			tmp = l.get( 0 );
			l.remove( 0 );
			if ( c[2 * i + 1] != '#' )
			{
				tmp.left = new TreeNode( c[2 * i + 1] - '0' );
				l.add( tmp.left );
			}
			if ( 2 * i + 2 < c.length )
			{
				if ( c[2 * i + 2] != '#' )
				{
					tmp.right = new TreeNode( c[2 * i + 2] - '0' );
					l.add( tmp.right );
				}
			}
		}
	}

	TreeNode( int[] c )
	{
		ArrayList<TreeNode> l = new ArrayList<TreeNode>();

		TreeNode tmp;
		this.val = c[0];
		l.add( this );
		for ( int i = 0; i < c.length / 2; i++ )
		{
			tmp = l.get( 0 );
			l.remove( 0 );
			if ( c[2 * i + 1] != -999 )
			{
				tmp.left = new TreeNode( c[2 * i + 1] );
				l.add( tmp.left );
			}
			if ( 2 * i + 2 < c.length )
			{
				if ( c[2 * i + 2] != -999 )
				{
					tmp.right = new TreeNode( c[2 * i + 2] );
					l.add( tmp.right );
				}
			}
		}
	}

	/**
	 * prints tree in level order
	 */
	public void print()
	{
		TreeNode tmp;

		ArrayList<TreeNode> q = new ArrayList<TreeNode>();
		TreeNode that = new TreeNode( 0 );
		q.add( this );
		q.add( that );
		System.out.println( "\nLevel Order Tree:\n Root->" + this.val );
		while ( !q.isEmpty() )
		{
			tmp = q.get( 0 );
			if ( tmp == that )
			{
				System.out.println();
				q.remove( 0 );
				if ( !q.isEmpty() )
				{
					q.add( that );
					continue;
				}
				else
					break;
			}
			q.remove( 0 );
			if ( tmp == null )
				continue;
			if ( tmp.left == null && tmp.right == null )
			{
				System.out.print( " # " );
				System.out.print( " # " );
				continue;
			}
			if ( tmp.left != null )
			{
				q.add( tmp.left );
				System.out.print( tmp.val + "->" + tmp.left.val + " " );
			}
			else
				System.out.print( " # " );
			if ( tmp.right != null )
			{
				q.add( tmp.right );
				System.out.print( tmp.val + "->" + tmp.right.val + " " );
			}
			else
				System.out.print( " # " );

		}
	}

	/**
	 * print tree in pre-order traversal
	 * 
	 * @param root
	 */
	public static void printPreOrder( TreeNode root )
	{
		System.out.println( "\npre order: " );
		preOrder( root );
	}

	/**
	 * print tree in in-order traversal
	 * 
	 * @param root
	 */
	public static void printInOrder( TreeNode root )
	{
		System.out.println( "\nin order: " );
		inOrder( root );
	}

	/**
	 * print tree in post-order traversal
	 * 
	 * @param root
	 */
	public static void printPostOrder( TreeNode root )
	{
		System.out.println( "\npost order: " );
		postOrder( root );
	}

	private static void preOrder( TreeNode root )
	{
		if ( root == null )
			return;
		System.out.print( root.val + _seprator );
		preOrder( root.left );
		preOrder( root.right );
	}

	private static void inOrder( TreeNode root )
	{
		if ( root == null )
			return;
		inOrder( root.left );
		System.out.print( root.val + _seprator );
		inOrder( root.right );
	}

	private static void postOrder( TreeNode root )
	{
		if ( root == null )
			return;
		postOrder( root.left );
		postOrder( root.right );
		System.out.print( root.val + _seprator );

	}
}
