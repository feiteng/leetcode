
import java.util.ArrayList;
import java.util.List;

/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator
{

	List<TreeNode> visit = new ArrayList<>();
	int _count = 0;
	int _step = 0;

	public BSTIterator( TreeNode root )
	{
		inOrder( root );
		_count = visit.size();

	}

	public void inOrder( TreeNode root )
	{
		if ( root == null )
			return;
		inOrder( root.left );
		visit.add( root );
		inOrder( root.right );

	}

	/** @return whether we have a next smallest number */
	public boolean hasNext()
	{
		return _count - _step == 0 ? false : true;
	}

	/** @return the next smallest number */
	public int next()
	{
		int next = visit.get( _step ).val;
		_step++;
		return next;

	}
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */