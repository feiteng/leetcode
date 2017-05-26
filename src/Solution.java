

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Class used to run leetcode
 * 
 * @author Rutter
 *
 */

public class Solution
{
	int sizex;
	int sizey;
	int index;
	int len;
	public char[] wordc;
	String _endWord;
	int[] px, py, b, m;
	List<TreeNode> preOrderList, inOrderList, postOrderList;
	int _buildTree_pos;
	int[] _buildTree_preorder, _buildTree_postorder;
	int[][] _spiralOrder_matrix;
	int _spiralOrder_i, _spiralOrder_j;
	// List<Integer> preOrderList, inOrderList;.
	Set<Integer> set;

	public static void main( String[] args ) throws IOException
	{
		Solution s = new Solution();

		long time;

		time = System.currentTimeMillis();

		String[] string = { "10111", "01010", "11011", "11011", "01111" };

		char[][] m = new char[string.length][];
		int k = 0;
		for ( String str : string )
			m[k++] = str.toCharArray();

		System.out.println( s.maximalSquare( m ) );

		System.out.printf( "Run time... %s ms", System.currentTimeMillis() - time );

	}

	public int maximalSquare( char[][] matrix )
	{
		if ( matrix.length == 0 || matrix[0].length == 0 )
			return 0;
		int maxlen = 0, m = matrix.length, n = matrix[0].length;
		int[][] w = new int[m][n];
		w[0][0] = matrix[0][0] == '1' ? 1 : 0;
		for ( int i = 1; i < m; i++ )
		{
			w[i][0] = matrix[i][0] == '1' ? 1 : 0;// + w[i - 1][0] : 0;
			maxlen = Math.max( maxlen, w[i][0] );
		}
		for ( int j = 1; j < n; j++ )
		{
			w[0][j] = matrix[0][j] == '1' ? 1 : 0;// + w[0][j - 1] : 0;
			maxlen = Math.max( maxlen, w[0][j] );
		}
		for ( int i = 1; i < m; i++ )
		{
			for ( int j = 1; j < n; j++ )
			{
				if ( matrix[i][j] == '0' )
					continue;
				w[i][j] = Math.min( Math.min( w[i - 1][j], w[i][j - 1] ), w[i - 1][j - 1] ) + 1;
				maxlen = Math.max( maxlen, w[i][j] );
			}
		}
		for ( int[] r : w )
			System.out.println( Arrays.toString( r ) );
		return maxlen * maxlen;
	}

	public int calculateMinimumHP( int[][] dungeon )
	{
		int m = dungeon.length, n = dungeon[0].length;
		int[][] hp = new int[m][n];
		hp[m - 1][n - 1] = dungeon[m - 1][n - 1];
		for ( int i = m - 2; i >= 0; i-- )
			hp[i][n - 1] = dungeon[i][n - 1] - hp[i + 1][n - 1];
		for ( int j = n - 2; j >= 0; j-- )
			hp[m - 1][j] = dungeon[m - 1][j] - hp[m - 1][j + 1];

		for ( int i = m - 2; i >= 0; i-- )
		{
			for ( int j = n - 2; j >= 0; j-- )
			{
				hp[i][j] = dungeon[i][j] - Math.min( hp[i + 1][j], hp[i][j + 1] );
			}
		}
		for ( int[] h : hp )
			System.out.println( Arrays.toString( h ) );
		return hp[0][0] < 0 ? -hp[0][0] + 1 : 1;
	}

	public int singleNonDuplicate( int[] nums )
	{
		int l = 0, r = nums.length - 1, m = r / 2;
		while ( l + 2 < r )
		{
			m = l + ( r - l ) / 2;
			if ( nums[m] != nums[m - 1] && nums[m] != nums[m + 1] )
				return nums[m];
			if ( nums[m] == nums[m - 1] )
				r = m;
			else
				l = m;
		}
		if ( nums[l] != nums[l + 1] && nums[l + 1] != nums[l + 2] )
			return nums[l + 1];
		if ( nums[l] != nums[l + 1] )
			return nums[l];
		return nums[l + 2];
	}

	public int closestValue( TreeNode root, double target )
	{
		if ( root == null )
			return 0;
		TreeNode r = root;
		int k = r.val;
		double diff = Long.MAX_VALUE;
		while ( r != null )

		{
			if ( Math.abs( (double) r.val - target ) <= diff )
			{
				k = r.val;
				diff = Math.abs( (double) r.val - target );
			}
			if ( r.val > target )
				r = r.left;
			else // if ( r.val <= target )
				r = r.right;
		}
		return k;
	}

	public int evalRPN( String[] tokens )
	{
		Stack<Integer> stack = new Stack<>();
		for ( String s : tokens )
		{
			if ( s.equals( "+" ) || s.equals( "-" ) || s.equals( "*" ) || s.equals( "/" ) )
			{
				int a = stack.pop(), b = stack.pop();
				stack.push( ops( a, b, s ) );
			}
			else
				stack.push( Integer.valueOf( s ) );
		}
		return stack.pop();
	}

	int ops( int b, int a, String s )
	{
		if ( s.equals( "+" ) )
			return a + b;
		if ( s.equals( "-" ) )
			return a - b;
		if ( s.equals( "*" ) )
			return a * b;
		else
			return a / b;
	}

	public boolean checkInclusion( String s1, String s2 )
	{
		if ( s1.length() > s2.length() )
			return false;
		int[] set = new int[26];
		for ( char c : s1.toCharArray() )
			set[c - 'a']++;
		for ( int i = 0; i < s2.length(); i++ )
			if ( checkInclusionHelper( set, s2.toCharArray(), i ) )
				return true;

		return false;
	}

	boolean checkInclusionHelper( int[] set, char[] c, int pos )
	{
		if ( zeroSum( set ) )
			return true;
		if ( c[pos] == '#' || set[c[pos] - 'a'] <= 0 )
			return false;
		if ( pos < 0 || pos >= c.length )
			return false;
		if ( c[pos] == '#' )
			return false;
		int[] dirs = { -1, 1 };
		set[c[pos] - 'a']--;
		if ( set[c[pos] - 'a'] < 0 )
			return false;
		char original = c[pos];
		c[pos] = '#';
		for ( int i : dirs )
		{
			if ( checkInclusionHelper( set, c, pos + i ) )
				return true;
		}
		c[pos] = original;
		set[c[pos] - 'a']++;
		return false;
	}

	boolean zeroSum( int[] num )
	{
		for ( int n : num )
			if ( n != 0 )
				return false;
		return true;
	}

	boolean negSum( int[] num )
	{
		for ( int n : num )
			if ( n < 0 )
				return true;
		return false;
	}

	public int subarraySum( int[] nums, int k )
	{
		int[] sum = new int[nums.length + 1];
		int count = 0;
		for ( int i = 0; i < nums.length; i++ )
			sum[i + 1] = sum[i] + nums[i];
		for ( int i = 0; i < sum.length; i++ )
		{
			for ( int j = i + 1; j < sum.length; j++ )
			{
				if ( sum[j] - sum[i] == k )
					count++;
			}

		}
		return count;
	}

	public int[][] matrixReshape( int[][] nums, int r, int c )
	{
		if ( nums.length * nums[0].length != r * c )
			return nums;
		int[][] mat = new int[r][c];
		int k = 0;
		for ( int i = 0; i < nums.length; i++ )
		{
			for ( int j = 0; j < nums[0].length; j++ )
			{
				mat[k / c][k % c] = nums[i][j];
				k++;
			}
		}
		for ( int[] m : mat )
			System.out.println( Arrays.toString( m ) );
		return mat;

	}

	class TrieNode
	{
		String endWord = null;
		TrieNode[] child = new TrieNode[26];
	}

	public List<String> findWords( char[][] board, String[] words )
	{
		List<String> list = new ArrayList<>();
		TrieNode root = buildTrie( words );
		for ( int i = 0; i < board.length; i++ )
		{
			for ( int j = 0; j < board[0].length; j++ )
			{
				findWord( board, i, j, root.child[board[i][j] - 'a'], list );
			}
		}
		return list;
	}

	void findWord( char[][] board, int i, int j, TrieNode r, List<String> list )
	{
		if ( r == null )
			return;
		if ( r.endWord != null )
		{
			list.add( r.endWord );
			r.endWord = null;
		}
		char o = board[i][j];
		board[i][j] = '#';
		int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		for ( int[] dir : dirs )
		{
			int x = i + dir[0], y = j + dir[1];
			if ( x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == '#' )
				continue;
			findWord( board, x, y, r.child[board[x][y] - 'a'], list );
		}
		board[i][j] = o;
	}

	TrieNode buildTrie( String[] strings )
	{
		TrieNode root = new TrieNode(), travel;
		for ( String string : strings )
		{
			travel = root;
			for ( char c : string.toCharArray() )
			{
				if ( travel.child[c - 'a'] == null )
					travel.child[c - 'a'] = new TrieNode();
				travel = travel.child[c - 'a'];
			}
			travel.endWord = string;
		}
		return root;
	}

	public int longestValidParentheses( String s )
	{
		boolean[] check = new boolean[s.length()];
		Stack<Integer> stack = new Stack<>();
		int run = 0, tmp = 0;
		for ( int k = 0; k < s.length(); k++ )
		{
			if ( s.charAt( k ) == '(' )
				stack.push( k );
			if ( s.charAt( k ) == ')' && !stack.isEmpty() )
			{
				check[k] = true;
				check[stack.pop()] = true;
			}
		}

		for ( int k = 0; k < check.length; k++ )
		{
			if ( check[k] )
				tmp++;
			else
			{
				run = Math.max( tmp, run );
				tmp = 0;
			}
		}
		return run;
	}

	public int longestIncreasingPath( int[][] matrix )
	{
		Map<String, Integer> map = new HashMap<>();
		if ( matrix.length < 1 || matrix[0].length < 1 )
			return 0;
		int count = 0, r = matrix.length, c = matrix[0].length;
		int[][] visited = new int[r][c], path = new int[r][c];
		// for ( int[] p : path )
		// Arrays.fill( p, -1 );

		for ( int i = 0; i < matrix.length; i++ )
		{
			for ( int j = 0; j < matrix[0].length; j++ )
			{
				increasingPathDFS2( matrix, i, j, path );
				count = Math.max( count, path[i][j] );
			}
		}
		for ( int[] p : path )
			System.out.println( Arrays.toString( p ) );
		return count;
	}

	void increasingPathDFS2( int[][] matrix, int x, int y, int[][] path )
	{
		if ( path[x][y] > 0 )
			return;
		int u = 0, d = 0, l = 0, r = 0;
		if ( x - 1 >= 0 && matrix[x - 1][y] > matrix[x][y] )
		{
			increasingPathDFS2( matrix, x - 1, y, path );
			u = path[x - 1][y];
		}
		if ( x + 1 < matrix.length && matrix[x + 1][y] > matrix[x][y] )
		{
			increasingPathDFS2( matrix, x + 1, y, path );
			d = path[x + 1][y];
		}
		if ( y - 1 >= 0 && matrix[x][y - 1] > matrix[x][y] )
		{
			increasingPathDFS2( matrix, x, y - 1, path );
			l = path[x][y - 1];
		}
		if ( y + 1 < matrix[0].length && matrix[x][y + 1] > matrix[x][y] )
		{
			increasingPathDFS2( matrix, x, y + 1, path );
			r = path[x][y + 1];
		}
		path[x][y] = 1 + Math.max( 0, Math.max( u, Math.max( d, Math.max( l, r ) ) ) );
	}

	String pos( int x, int y )
	{
		return String.valueOf( x ) + "_" + String.valueOf( y );
	}

	void increasingPathDFS( int[][] matrix, int x, int y, Map<String, Integer> map )
	{

		if ( map.containsKey( pos( x, y ) ) )
			return;
		// up down left right
		List<Integer> list = new ArrayList<>();
		if ( x - 1 >= 0 && matrix[x - 1][y] > matrix[x][y] )
		{
			increasingPathDFS( matrix, x - 1, y, map );
			list.add( map.get( pos( x - 1, y ) ) );
		}
		if ( x + 1 < matrix.length && matrix[x + 1][y] > matrix[x][y] )
		{
			increasingPathDFS( matrix, x + 1, y, map );
			list.add( map.get( pos( x + 1, y ) ) );
		}
		if ( y - 1 >= 0 && matrix[x][y - 1] > matrix[x][y] )
		{
			increasingPathDFS( matrix, x, y - 1, map );
			list.add( map.get( pos( x, y - 1 ) ) );
		}
		if ( y + 1 < matrix[0].length && matrix[x][y + 1] > matrix[x][y] )
		{
			increasingPathDFS( matrix, x, y + 1, map );
			list.add( map.get( pos( x, y + 1 ) ) );
		}
		list.add( 0 );
		map.put( pos( x, y ), Collections.max( list ) + 1 );
	}

	public int longestConsecutive( int[] nums )
	{
		Map<Integer, Integer> map = new HashMap<>();
		int count = 0;
		for ( int m : nums )
		{
			if ( map.containsKey( m ) )
				continue;

			int head = map.containsKey( m - 1 ) ? map.get( m - 1 ) : 0,
					tail = map.containsKey( m + 1 ) ? map.get( m + 1 ) : 0,
					sum = head + tail + 1;
			map.put( m, sum );
			map.put( m - head, sum );
			map.put( m + tail, sum );

			count = Math.max( count, map.get( m ) );

		}
		System.out.println( map );
		return count;
	}

	public int threeSumSmaller( int[] nums, int target )
	{
		Arrays.sort( nums );
		int count = 0;
		for ( int i = 0; i < nums.length - 2; i++ )
		{
			int j = i + 1, k = nums.length - 1;
			while ( j < k )
			{
				int sum = nums[i] + nums[j] + nums[k];
				// sum decreases gradually, nums[k] is the sup
				while ( j < k && sum >= target )
				{
					k--;
					sum = nums[i] + nums[j] + nums[k];
				}
				if ( j < k && sum < target )
					count += k - j;
				j++;
			}
		}
		return count;
	}

	public int threeSumSmaller1( int[] nums, int target )
	{
		Arrays.sort( nums );
		sumSmallerHelper( nums, target, 3, 0 );
		return count;
	}

	int count = 0;

	void sumSmallerHelper( int[] n, int target, int k, int s )
	{
		if ( target > 0 && k == 0 )
			count++;
		if ( k < 0 )
			return;
		for ( int i = s; i < n.length; i++ )
		{
			sumSmallerHelper( n, target - n[i], k - 1, i + 1 );
		}
	}

	public int firstMissingPositive2( int[] nums )
	{
		for ( int i = 0; i < nums.length; i++ )
		{
			if ( nums[i] <= 0 || nums[i] > nums.length )
				continue;
			int k = i, t = nums[i];
			while ( nums[i] > 0 && nums[i] < nums.length && nums[i] != i + 1 )
			{
				t = nums[nums[i] - 1];
				nums[nums[i] - 1] = nums[i];
				if ( i == t )
					break;
				nums[i] = t;
			}
			i = k;
		}
		System.out.println( Arrays.toString( nums ) );
		for ( int i = 0; i < nums.length; i++ )
		{
			if ( nums[i] != i + 1 )
				return i + 1;
		}
		return nums.length + 1;
	}

	public int firstMissingPositive( int[] nums )
	{
		boolean[] check = new boolean[nums.length + 1];
		Arrays.fill( check, false );
		for ( int k : nums )
		{
			if ( k > 0 && k < check.length )
				check[k] = true;
		}
		for ( int i = 1; i < check.length; i++ )
		{
			if ( !check[i] )
				return i;
		}
		return check.length;
	}

	public int combinationSum4( int[] nums, int target )
	{
		int[] sum = new int[target + 1];
		for ( int i : nums )
		{
			if ( i < sum.length )
				sum[i] = 1;
		}
		for ( int i = 1; i < sum.length; i++ )
		{
			for ( int k : nums )
			{
				if ( i < k )
					break;
				sum[i] += sum[i - k];
			}
		}
		return sum[target];
	}

	public List<List<Integer>> combinationSum3( int k, int n )
	{
		List<List<Integer>> retList = new ArrayList<>();
		int[] cand = new int[9];
		int i = -1;
		while ( ++i < 9 )
			cand[i] = i + 1;
		getComb3( cand, n, k, 0, retList, new ArrayList<>() );

		return retList;
	}

	void getComb3( int[] n, int target, int k, int s, List<List<Integer>> rList, List<Integer> cList )
	{
		if ( target == 0 && k == 0 )
		{
			rList.add( new ArrayList<>( cList ) );
			return;
		}
		if ( target < 0 || target > 0 && k == 0 )
			return;
		for ( int i = s; i < n.length; i++ )
		{
			cList.add( n[i] );
			getComb3( n, target - n[i], k - 1, i + 1, rList, cList );
			cList.remove( cList.size() - 1 );
		}
	}

	public List<List<Integer>> combinationSum2( int[] candidates, int target )
	{
		List<List<Integer>> retList = new ArrayList<>();
		Arrays.sort( candidates );
		getComb2( candidates, target, 0, retList, new ArrayList<>() );
		return retList;
	}

	void getComb2( int[] n, int target, int s, List<List<Integer>> rList, List<Integer> cList )
	{
		if ( target == 0 )
		{
			rList.add( new ArrayList<>( cList ) );
			return;
		}
		if ( target < 0 )
			return;
		for ( int i = s; i < n.length; i++ )
		{
			// had hard time coming up with solution for duplicate numbers
			// originally used Set to add all temp list
			// this if from top solution
			if ( i > s && n[i] == n[i - 1] )
				continue;
			cList.add( n[i] );
			getComb2( n, target - n[i], i + 1, rList, cList );
			cList.remove( cList.size() - 1 );
		}
	}

	public List<List<Integer>> combinationSum( int[] candidates, int target )
	{
		List<List<Integer>> retList = new ArrayList<>();
		Arrays.sort( candidates );
		if ( target < candidates[0] )
			return retList;
		getComb( candidates, target, 0, retList, new ArrayList<>() );
		return retList;
	}

	void getComb( int[] n, int target, int s, List<List<Integer>> rList, List<Integer> cList )
	{
		if ( target == 0 )
		{
			rList.add( new ArrayList<>( cList ) );
			return;
		}
		if ( target < 0 )
			return;
		for ( int i = s; i < n.length; i++ )
		{
			cList.add( n[i] );
			getComb( n, target - n[i], i, rList, cList );
			cList.remove( cList.size() - 1 );
		}
	}

	public String convertToBase7( int num )
	{
		// return Integer.toString( num, 7 );
		StringBuilder sb = new StringBuilder();
		String neg = "";
		if ( num < 0 )
		{
			neg = "-";
			num = -num;
		}
		while ( num > 0 )
		{
			int remainder = num % 7;
			num /= 7;
			sb.append( remainder );
		}
		sb.append( neg );
		return sb.reverse().toString();
	}

	public boolean checkRecord( String s )
	{
		char[] c = s.toCharArray();
		int countA = 0;
		for ( int i = 0; i < s.length() - 2; i++ )
		{
			if ( c[i] == 'L' )
			{
				if ( c[i + 1] == 'L' && c[i + 2] == 'L' )
					return false;
			}
		}
		for ( int i = 0; i < s.length(); i++ )
			if ( c[i] == 'A' )
				countA++;
		if ( countA > 1 )
			return false;
		return true;

	}

	public int lengthLongestPath( String input )
	{
		int maxLen = 0, level;
		input = "\t" + input;
		List<String> currentList = new ArrayList<>(), maxList = new ArrayList<>();
		int i = 0, j = input.indexOf( "\n" );
		while ( j > 0 )
		{
			String nextFolder = input.substring( i, j );
			updateList( nextFolder, currentList, maxList );
			i = j + 1; // i at next character
			j = input.indexOf( "\n", i );
		}
		// now j < 0, also case no \n at all
		if ( i == 0 && input.indexOf( '.' ) < 0 )
			return 0;
		j = input.length();
		String nextFolder = input.substring( i, j );
		updateList( nextFolder, currentList, maxList );

		return getLen( maxList );
	}

	void updateList( String nextFolder, List<String> currentList, List<String> maxList )
	{
		int level = getLevel( nextFolder );

		if ( level > 0 )
			nextFolder = nextFolder.substring( level );
		if ( level <= currentList.size() )
		{
			level = currentList.size() - level;
			while ( --level >= 0 )
				currentList.remove( currentList.size() - 1 );
		}
		currentList.add( nextFolder );
		if ( getLen( maxList ) < getLen( currentList ) && currentList.get( currentList.size() - 1 ).indexOf( '.' ) > 0 )
		{
			maxList.clear();
			maxList.addAll( currentList );
		}
	}

	int getLen( List<String> list )
	{
		return String.join( "/", list ).length();
	}

	int getLevel( String input )
	{
		int level = 0, startPos = 1;
		startPos = input.indexOf( "\t" );
		while ( input.indexOf( "\t", startPos ) >= 0 )// input.indexOf( "\n" ) >
		{
			level++;
			startPos = input.indexOf( "\t", startPos ) + 1;
		}
		return level;
	}

	public int reversePairs( int[] nums )
	{
		int count = 0;

		for ( int i = nums.length - 1; i >= 0; i-- )
		{

		}

		return count;
	}

	public int findCircleNum( int[][] M )
	{
		List<List<Integer>> set = new ArrayList<>();
		Set<Integer> visited = new HashSet<>();
		for ( int i = 0; i < M.length; i++ )
		{
			if ( visited.contains( i ) )
				continue;
			for ( int j = 0; j < M[0].length; j++ )
			{
				if ( visited.contains( i ) )
					continue;
				if ( M[i][j] > 0 )
				{
					List<Integer> list = new ArrayList<>();
					list.add( j );
					visited.add( j );
					findFriends( set, visited, list, M, i );
					System.out.println( list );
					set.add( new ArrayList<>( list ) );
				}
			}
		}
		return set.size();
	}

	void findFriends( List<List<Integer>> set, Set<Integer> visited, List<Integer> friends, int[][] M, int row )
	{
		for ( int j = 0; j < M[row].length; j++ )
		{
			if ( visited.contains( j ) )
				continue;
			if ( M[row][j] > 0 )
			{
				visited.add( j );
				friends.add( j );
				findFriends( set, visited, friends, M, j );
			}
		}
		// set.add( new ArrayList<>( friends ) );
	}

	public List<List<String>> findLadders( String beginWord, String endWord, List<String> wordList )
	{
		List<List<String>> rList = new ArrayList<>();

		Map<Integer, List<String>> map = new HashMap<>();
		Map<String, List<String>> wordMap = new HashMap<>();
		Set<String> dict = new HashSet<>( wordList );
		if ( !dict.contains( endWord ) )
			return rList;

		long time = System.currentTimeMillis(), t2;
		int print1 = 0;
		if ( print1 > 0 )
		{
			time = System.currentTimeMillis();
			onewayBFS( beginWord, endWord, dict, map, wordMap );

			System.out.printf( "one way BFS Running time : ... %d ms\n", System.currentTimeMillis() - time );

			// printMap( map );
		}
		else
		{
			time = System.currentTimeMillis();
			twowayBFS( beginWord, endWord, dict, wordMap );
			System.out.printf( "two way BFS Running time : ... %d ms\n", System.currentTimeMillis() - time );
		}
		// printMap2( wordMap );

		time = System.currentTimeMillis();
		levelDFS( beginWord, endWord, wordMap, rList, new ArrayList<>( Arrays.asList( new String[] { beginWord } ) ) );

		System.out.printf( "DFS Running time : ... %d ms\n", System.currentTimeMillis() - time );

		System.out.println( rList.size() );

		return rList;
	}

	void levelDFS( String beginWord, String endWord, Map<String, List<String>> wordMap, List<List<String>> rList, List<String> cList )
	{
		if ( !wordMap.containsKey( beginWord ) )
			return;
		if ( wordMap.get( beginWord ).contains( endWord ) )
		{
			cList.add( endWord );
			rList.add( new ArrayList<>( cList ) );
			cList.remove( cList.size() - 1 );
			return;
		}
		// System.out.println( cList );
		for ( String words : wordMap.get( beginWord ) )
		{
			List<String> n = wordMap.get( beginWord );
			// System.out.println( n );
			cList.add( words );
			levelDFS( words, endWord, wordMap, rList, cList );
		}
	}

	public int nextGreaterElement( int n )
	{
		char[] nc = String.valueOf( n ).toCharArray();
		nextPermutation( nc );
		long nextP = Long.valueOf( String.valueOf( nc ) );
		if ( nextP > Integer.MAX_VALUE || nextP <= n )
			return -1;
		return Integer.valueOf( String.valueOf( nc ) );

	}

	public void nextPermutation( char[] nums )
	{
		// 1 find largest nums[k] < nums[k+1]
		// 2 find largest nums[k] < nums[l]
		// 3 swap nums[k],nums[l]
		// 4 inverse nums[k+1]..nums[len]
		if ( nums.length < 2 )
			return;
		int k = -1;
		for ( int i = 0; i < nums.length - 1; i++ )
			if ( nums[i] < nums[i + 1] )
				k = i;
		if ( k == -1 )
		{
			reverseChar( nums, 0 );
			return;
		}
		int l = nums.length - 1;
		for ( int i = k; i < nums.length; i++ )
			if ( nums[i] > nums[k] )
				l = i;
		char t = nums[k];
		nums[k] = nums[l];
		nums[l] = t;
		reverseChar( nums, k + 1 );
		return;
	}

	void reverseChar( char[] nums, int index )
	{
		int i = index, j = nums.length - 1;
		char t;
		while ( i < j )
		{
			t = nums[i];
			nums[i] = nums[j];
			nums[j] = t;
			i++;
			j--;
		}
		return;
	}

	List<List<Integer>> readFile() throws IOException
	{
		String file = "C:/Users/Feiteng/Desktop/java/testfile.txt";
		BufferedReader r = new BufferedReader( new FileReader( file ) );
		String next = r.readLine();
		List<List<Integer>> list = new ArrayList<>();
		while ( next != null )
		{
			List<Integer> l = new ArrayList<>();
			String[] s = next.split( "," );
			l.add( Integer.valueOf( s[0] ) );
			l.add( Integer.valueOf( s[1] ) );
			list.add( l );
			next = r.readLine();
		}
		return list;
	}

	public int leastBricks( List<List<Integer>> wall )
	{
		long sum = 0;
		for ( int k : wall.get( 0 ) )
			sum += k;
		Map<Long, Integer> map = new HashMap<>();
		for ( List<Integer> list : wall )
		{
			long v1 = 0, v2 = 0;
			for ( int k : list )
			{
				v2 += k;
				if ( !map.containsKey( v2 ) )
					map.put( v2, 1 );
				else
					map.put( v2, map.get( v2 ) + 1 );
			}
		}
		map.put( 0L, wall.size() );
		map.put( sum, wall.size() );
		for ( long k : map.keySet() )
			System.out.printf( "%s %s\n", k, map.get( k ) );
		int min = Integer.MAX_VALUE;
		for ( long k : map.keySet() )
		{
			if ( k == 0 || k == sum )
				continue;
			min = Math.min( min, wall.size() - map.get( k ) );
		}
		return min == Integer.MAX_VALUE ? wall.size() : min;
	}

	public String reverseWords( String s )
	{
		if ( s.length() < 2 )
			return s;
		String ret = "";

		int i = 0, j = 0;
		while ( j < s.length() )
		{
			while ( i < s.length() && s.charAt( i ) == ' ' )
				i++; // first character position
			// if ( i > j )
			ret += s.substring( j, i ); // white space
			j = i;
			while ( j < s.length() && s.charAt( j ) != ' ' )
				j++; // next white space position
			ret += new StringBuilder( s.substring( i, j ) ).reverse().toString();
			i = j;
		}

		return ret;
	}

	// public String licenseKeyFormatting( String S, int K )
	// {
	// int dashCount = 0;
	// for ( char c : S.toCharArray() )
	// if ( c == '-' )
	// dashCount++;
	// int stringLen = S.length() - dashCount, newlen = stringLen / K, rem = stringLen % K;
	//
	// }

	// public List<List<String>> findLadders( String beginWord, String endWord, List<String> wordList )
	// {
	// List<List<String>> rList = new ArrayList<>();
	// Set<String> wordSet = new HashSet<>( wordList );
	// int length = twowayBFS( beginWord, endWord, wordSet );
	// System.out.println( length );
	// if ( length > 1 )
	// ladderDFS( rList, new LinkedList<>( Arrays.asList( new String[] { beginWord } ) ), beginWord, endWord, length - 1, wordSet,
	// new HashSet<>( Arrays.asList( new String[] { beginWord } ) ) );
	// return rList;
	// }

	void ladderDFS( List<List<String>> rList, List<String> cList,
			String currentWord, String endWord, int step, Set<String> set, Set<String> visited )
	{
		Set<String> neighbours = wordNeighbours( currentWord, set, visited );
		if ( step == 1 && neighbours.contains( endWord ) )
		{
			cList.add( endWord );
			rList.add( cList );
			return;
		}
		if ( step == 1 )
			return;
		for ( String string : neighbours )
		{
			visited.add( string );
			cList.add( string );
			ladderDFS( rList, new LinkedList<>( cList ), string, endWord, step - 1, set, visited );
			visited.remove( cList.get( cList.size() - 1 ) );

			cList.remove( cList.size() - 1 );
		}
	}

	void twowayBFS( String beginWord, String endWord, Set<String> dict, Map<String, List<String>> wordMap )
	{
		Set<String> q, q1 = new HashSet<>(), q2 = new HashSet<>(), qtmp;// = new LinkedList<>();
		q1.add( beginWord );
		q2.add( endWord );
		boolean notFound = true, use1 = true;
		if ( dict.contains( beginWord ) )
			dict.remove( beginWord );
		// dict.remove( endWord );
		while ( !q1.isEmpty() && notFound )
		{
			if ( q1.size() > q2.size() )
			{
				q = q1;
				q1 = q2;
				q2 = q;
				use1 = !use1;
			}
			// System.out.println( q1 );
			qtmp = new HashSet<>();
			for ( String nextWord : q1 )
			{
				List<String> tList = new ArrayList<>();
				for ( int i = 0; i < nextWord.length(); i++ )
				{
					char[] charWord = nextWord.toCharArray();
					char originalCharacter = charWord[i];
					for ( char c = 'a'; c <= 'z'; c++ )
					{
						charWord[i] = c;
						String newString = String.valueOf( charWord );
						// if ( newString.equals( "gee" ) )
						// System.out.println( "" );
						if ( q2.contains( newString ) )
						{
							if ( use1 )
							{
								if ( wordMap.containsKey( nextWord ) )
									wordMap.get( nextWord ).add( newString );
								else
									wordMap.put( nextWord, new ArrayList<>( Arrays.asList( new String[] { newString } ) ) );
							}
							else
							{
								if ( wordMap.containsKey( newString ) )
									wordMap.get( newString ).add( nextWord );
								else
									wordMap.put( newString, new ArrayList<>( Arrays.asList( new String[] { nextWord } ) ) );
							}
							notFound = false;
						}
						if ( dict.contains( newString ) )
							tList.add( newString );

					}
					charWord[i] = originalCharacter;
				}
				if ( tList.size() > 0 )
				{
					if ( use1 )
					{
						if ( wordMap.containsKey( nextWord ) )
							wordMap.get( nextWord ).addAll( tList );
						else
							wordMap.put( nextWord, tList );
					}

					else
					{
						for ( String twords : tList )
							if ( wordMap.containsKey( twords ) )
								wordMap.get( twords ).add( nextWord );
							else
								wordMap.put( twords, new ArrayList<>( Arrays.asList( new String[] { nextWord } ) ) );
					}
					qtmp.addAll( tList );
				}
			}
			q1 = qtmp;
			dict.removeAll( qtmp );

		}
	}

	void onewayBFS( String beginWord, String endWord, Set<String> dict, Map<Integer, List<String>> map, Map<String, List<String>> wordMap )
	{
		Queue<String> q = new LinkedList<>(), qtmp;// = new LinkedList<>();
		q.add( beginWord );
		int level = 1;
		boolean notFound = true;
		map.put( 0, new ArrayList<>( Arrays.asList( new String[] { beginWord } ) ) );
		if ( dict.contains( beginWord ) )
			dict.remove( beginWord );
		dict.add( endWord );
		while ( !q.isEmpty() && notFound )
		{
			qtmp = new LinkedList<>();
			while ( !q.isEmpty() )
			{
				String nextWord = q.poll();
				List<String> tList = new ArrayList<>();
				for ( int i = 0; i < nextWord.length(); i++ )
				{
					char[] charWord = nextWord.toCharArray();
					char originalCharacter = charWord[i];
					for ( char c = 'a'; c <= 'z'; c++ )
					{
						charWord[i] = c;
						String newString = String.valueOf( charWord );
						// if ( newString.equals( "gee" ) )
						// System.out.println( "" );
						if ( newString.equals( endWord ) )
							notFound = false;
						if ( dict.contains( newString ) )
						{
							tList.add( newString );
						}

					}
					charWord[i] = originalCharacter;
				}
				if ( tList.size() > 0 )
				{
					wordMap.put( nextWord, tList );
					qtmp.addAll( tList );
				}
			}
			map.put( level++, new ArrayList<>( qtmp ) );
			q = qtmp;
			dict.removeAll( qtmp );

		}
		// printMap( map );
		// printMap2( wordMap );
	}

	public boolean hasPathSum( TreeNode root, int sum )
	{
		if ( root == null )
			return false;
		if ( sum == root.val && root.left == null && root.right == null )
			return true;
		return hasPathSum( root.left, sum - root.val ) || hasPathSum( root.right, sum - root.val );
	}

	public List<List<Integer>> subsetsWithDup( int[] nums )
	{
		Arrays.sort( nums );
		List<List<Integer>> retList = new ArrayList<>();
		Map<Integer, List<List<Integer>>> map = new HashMap<>();
		Integer nul = new Integer( Integer.MIN_VALUE );
		List<List<Integer>> nulList = new ArrayList<>();
		nulList.add( new ArrayList<>() );
		map.put( nul, nulList );
		for ( int k : nums )
		{
			List<List<Integer>> tList = new ArrayList<>();
			if ( map.containsKey( k ) )
			{
				for ( List<Integer> l : map.get( k ) )
				{
					List<Integer> tmp = new ArrayList<>( l );
					tmp.add( k );
					tList.add( tmp );
				}
				map.get( nul ).addAll( map.get( k ) );
				map.put( k, tList );
			}
			else
			{
				for ( int keys : map.keySet() )
				{
					for ( List<Integer> l : map.get( keys ) )
					{
						List<Integer> tmp = new ArrayList<>( l );
						tmp.add( k );
						tList.add( tmp );
					}
				}
				map.put( k, tList );
			}
		}
		for ( int keys : map.keySet() )
		{
			for ( List<Integer> l : map.get( keys ) )
				retList.add( l );
		}

		return retList;
	}

	public int wordsTyping( String[] sentence, int rows, int cols )
	{
		int count = 0, pos = 0, k = -1;

		while ( rows > 0 )
		{
			pos = 0;
			while ( cols >= pos + sentence[k + 1].length() )
			{
				pos += sentence[++k].length() + 1;
				if ( k >= sentence.length - 1 )
				{
					k = -1;
					count++;
				}
			}
			rows--;

		}

		return count;
	}

	public void reverseWords( char[] s )
	{
		reverseWord( s, 0, s.length );
		int i = 0;
		for ( int j = 0; j < s.length; j++ )
		{
			if ( s[j] == ' ' )
			{
				reverseWord( s, i, j );
				i = j + 1;
			}
		}
		reverseWord( s, i, s.length );
	}

	void reverseWord( char[] s, int i, int j )
	{
		if ( i >= j )
			return;
		while ( i < j )
		{
			char t = s[i];
			s[i] = s[j - 1];
			s[j - 1] = t;
			i++;
			j--;
		}
	}

	public boolean isAdditiveNumber( String num )
	{
		if ( num.length() < 1 )
			return false;
		if ( num.length() < 2 )
			return true;

		for ( int i = 1; i < num.length(); i++ )
		{
			for ( int j = 1; i + j < num.length(); j++ )
			{
				long a = Long.valueOf( num.substring( 0, i ) ), b = Long.valueOf( num.substring( i, i + j ) );
				if ( additiveConstruct( a, b, num ) )
					return true;
			}
		}

		return false;
	}

	boolean additiveConstruct( long a, long b, String num )
	{
		String s = String.valueOf( a ) + String.valueOf( b );

		while ( s.length() < num.length() )
		{
			long c = a + b;
			a = b;
			b = c;
			s += String.valueOf( c );
		}
		return num.equals( s );
	}

	public List<List<Integer>> pathSum( TreeNode root, int sum )
	{
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		pathSumHelper( ret, new ArrayList<>(), root, sum );
		return ret;
	}

	void pathSumHelper( List<List<Integer>> ret, List<Integer> list, TreeNode root, int target )
	{
		if ( root == null )
			return;

		list.add( root.val );
		if ( target == root.val && root.left == null && root.right == null )
		{
			ret.add( new ArrayList<>( list ) );
			list.remove( list.size() - 1 );
			return;
		}
		else
		{
			pathSumHelper( ret, list, root.left, target - root.val );
			pathSumHelper( ret, list, root.right, target - root.val );
		}
		list.remove( list.size() - 1 );

	}

	public boolean isReflected( int[][] points )
	{
		// todo
		// Map<Integer, Set<Integer>> map = new HashMap<>();
		// for ( int[] point : points )
		// {
		// int x = point[0], y = point[1];
		// if ( !map.containsKey( y ) )
		// map.put( y, new HashSet( ) );
		//
		// }
		return false;
	}

	public int countArrangement( int N )
	{
		// todo
		return 0;
	}

	boolean isPalindrome2( String s )
	{
		for ( int i = 0; i < s.length() / 2; i++ )
			if ( s.charAt( i ) != s.charAt( s.length() - 1 - i ) )
				return false;
		return true;
	}

	public String countAndSay( int n )
	{
		String ret = "1";
		if ( n == 1 )
			return "1";
		for ( int i = 0; i < n - 1; i++ )
		{
			ret = convert( ret + "#" );
			System.out.println( "updated ret is: " + ret );
		}
		return ret;
	}

	public String convert( String str )
	{
		String returnStr = "";
		int i = 0;
		while ( i < str.length() - 1 )
		{
			int counter = 1;
			while ( i < str.length() - 1 && str.charAt( i ) == str.charAt( i + 1 ) )
			{
				counter++;
				i++;
			}
			returnStr += String.valueOf( counter ) + String.valueOf( str.charAt( i ) );
			i++;
		}
		return returnStr;
	}

	public String num2chr( int num )
	{
		char[] c = Character.toChars( 48 + num );
		return String.valueOf( c );
	}

	Map<String, List<List<String>>> palindromePartitionMap = new HashMap();

	public List<List<String>> partition( String s )
	{
		List<List<String>> list = new ArrayList<>();
		// base case
		if ( s.length() == 0 )
		{
			list.add( new ArrayList<>() );
			return list;
		}

		for ( int i = 1; i <= s.length(); i++ )
		{

			String part1 = s.substring( 0, i ), part2 = s.substring( i, s.length() );
			if ( isPalindrome( part1 ) )
			{
				if ( !palindromePartitionMap.containsKey( part2 ) )
					palindromePartitionMap.put( part2, partition( part2 ) );
				List<List<String>> list2 = palindromePartitionMap.get( part2 );
				for ( List<String> l : list2 )
				{
					ArrayList<String> tList = (ArrayList<String>) ( (ArrayList<String>) l ).clone();

					// new ArrayList<>( l );
					tList.add( 0, part1 );
					list.add( tList );
				}
			}
		}
		return list;
	}

	boolean isPalindrome( String s )
	{
		return new StringBuilder( s ).reverse().toString().equals( s );
	}

	public boolean canCross2( int[] stones )
	{
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for ( int s : stones )
			map.put( s, new HashSet<>() );
		map.get( 0 ).add( 1 );
		for ( int stone : stones )
		{
			for ( int jump : map.get( stone ) )
			{
				if ( jump == 0 )
					continue;
				if ( map.containsKey( stone + jump ) )
				{
					map.get( stone + jump ).add( jump );
					map.get( stone + jump ).add( jump - 1 );
					map.get( stone + jump ).add( jump + 1 );
				}
			}
		}
		if ( map.get( stones[stones.length - 1] ).size() > 0 )
			return true;
		return false;
	}

	public boolean canCross( int[] stones )
	{

		// (k,v) = (start pos, list of previous moves, i.e. next possible moves)
		Map<Integer, Set<Integer>> map = new HashMap<>();
		Set<Integer> set = new HashSet<>();
		for ( int s : stones )
			set.add( s );
		int startPos = 0, nextMov = 1, target = stones[stones.length - 1];
		Stack<Integer> posStack = new Stack<>();
		posStack.add( 0 );
		map.put( 0, new HashSet<>( Arrays.asList( new Integer[] { 1 } ) ) );
		while ( !posStack.isEmpty() )
		{
			// System.out.println( posStack );
			if ( map.containsKey( target ) )
				return true;
			int currentPos = posStack.pop();
			if ( map.containsKey( currentPos ) )
			{
				for ( int moves : map.get( currentPos ) )
				{
					if ( moves == 0 )
						continue;
					int nextPos = currentPos + moves;
					if ( map.containsKey( nextPos ) && map.get( nextPos ).contains( moves ) && map.get( nextPos ).contains( moves - 1 ) &&
							map.get( nextPos ).contains( moves + 1 ) )
						continue;
					if ( set.contains( nextPos ) )
					{
						posStack.add( nextPos );
						if ( map.containsKey( nextPos ) )
						{
							map.get( nextPos ).add( moves );
							map.get( nextPos ).add( moves - 1 );
							map.get( nextPos ).add( moves + 1 );
						}
						else
							map.put( nextPos, new HashSet<>( Arrays.asList( new Integer[] { moves, moves - 1, moves + 1 } ) ) );
					}
				}
			}
		}

		return false;

	}

	public int maxCoins( int[] nums )
	{
		if ( nums.length < 1 )
			return 0;
		int[] newNum = new int[nums.length + 2];
		int k = -1, size = newNum.length;
		while ( ++k < nums.length )
			newNum[k + 1] = nums[k];
		newNum[0] = newNum[size - 1] = 1;
		return maxCoinHelper( new int[size][size], newNum, 0, size - 1 );
	}

	int maxCoinHelper( int[][] memo, int[] nums, int left, int right )
	{
		int max = 0;
		if ( memo[left][right] > 0 )
			return memo[left][right];
		if ( left + 1 == right )
			return 0;// nums[left];
		for ( int i = left + 1; i < right; i++ )
		{
			max = Math.max( max, nums[left] * nums[i] * nums[right]
					+ maxCoinHelper( memo, nums, left, i )
					+ maxCoinHelper( memo, nums, i, right ) );
		}
		memo[left][right] = max;
		return max;
	}

	public void wallsAndGates( int[][] rooms )
	{
		int m = rooms.length, n = rooms[0].length;
		for ( int i = 0; i < m; i++ )
		{
			for ( int j = 0; j < n; j++ )
			{
				if ( rooms[i][j] != 0 )
					continue;
				bfsRooms( rooms, i, j ); // [i][j] == 0;
			}
		}
	}

	void bfsRooms( int[][] rooms, int i, int j )
	{
		Queue<Integer> row = new LinkedList<>(), column = new LinkedList<>();
	}

	public int reverseBits( int n )
	{
		int reversedN = 0;
		for ( int i = 0; i < 32; i++ )
		{
			int pos = n >> i & 1;
			if ( pos > 0 )
				reversedN ^= 1 << ( 31 - i );
		}
		return reversedN;
	}

	public String fractionToDecimal( int numeratorO, int denominatorO )
	{
		int flag1 = numeratorO >= 0 ? 1 : -1, flag2 = denominatorO >= 0 ? 1 : -1;
		long numerator = Math.abs( (long) numeratorO ), denominator = Math.abs( (long) denominatorO );
		long quotient = numerator / denominator, frac = numerator % denominator,
				nextQuot = frac * 10 / denominator, nextFrac = frac;
		int count = 0;
		String sign = flag1 * flag2 >= 0 ? "" : "-", denom = String.valueOf( quotient ) + (String) ( ( frac == 0 ) ? "" : "." );
		if ( frac == 0 )
		{
			if ( quotient == 0 )
				return "0";
			return sign + denom;
		}
		List<Long> listFrac = new ArrayList<>();
		Map<Long, Long> map = new HashMap<>();
		while ( !map.containsKey( nextFrac ) && nextFrac != 0 )
		{
			map.put( nextFrac, nextQuot );
			listFrac.add( nextFrac );
			nextFrac = nextFrac * 10 % denominator;
			nextQuot = nextFrac * 10 / denominator;
		}

		int i = 0;
		while ( i < listFrac.size() && listFrac.get( i ) != nextFrac )
		{
			denom += String.valueOf( map.get( listFrac.get( i++ ) ) );
			count++;
		}
		String recuring = "(";
		for ( i = count; i < listFrac.size(); i++ )
			recuring += String.valueOf( map.get( listFrac.get( i ) ) );
		recuring += ")";
		recuring = recuring.equals( "()" ) ? "" : recuring;
		return sign + denom + recuring;
	}

	public int divide( int dividend, int divisor )
	{
		long count = 0, flag1 = dividend > 0 ? 1 : -1, flag2 = divisor > 0 ? 1 : -1;
		long dividendNew = Math.abs( (long) dividend ), divisorNew = Math.abs( (long) divisor );
		while ( dividendNew >= divisorNew )
		{
			long doubleDivisor = divisorNew, tmpCount = 1;
			while ( dividendNew >= doubleDivisor )
			{
				doubleDivisor <<= 1;
				tmpCount *= 2;
			}
			dividendNew -= doubleDivisor >> 1;
			count += tmpCount / 2;
		}
		long result = count * flag1 * flag2;
		return result >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) result;
	}

	public int thirdMax( int[] nums )
	{
		long m1 = Long.MIN_VALUE, m2 = m1, m3 = m1;
		for ( int k : nums )
			m1 = Math.max( m1, k );
		for ( int k : nums )
		{
			if ( k != m1 )
				m2 = Math.max( m2, k );
		}
		for ( int k : nums )
		{
			if ( k != m1 && k != m2 )
				m3 = Math.max( m3, k );
		}

		if ( m3 == Long.MIN_VALUE )
			return (int) m1;
		return (int) m3;
	}

	public int minSubArrayLen( int n, int[] nums )
	{
		int[] s = new int[nums.length + 1];
		s[0] = 0;
		for ( int i = 0; i < nums.length; i++ )
			s[i + 1] = s[i] + nums[i];
		int i = 0, j = 0, size = Integer.MAX_VALUE;
		while ( i < nums.length )
		{
			// System.out.println( i );
			while ( j < nums.length && s[j] - s[i] < n )
				j++;
			if ( s[j] - s[i] >= n )
				size = Math.min( size, j - i );
			i++;
		}
		return size == Integer.MAX_VALUE ? 0 : size;
	}

	Set<String> interLeaveSet = new HashSet<>();

	public boolean isInterleave( String s1, String s2, String s3 )
	{
		if ( interLeaveSet.contains( s1 + "_" + s2 ) )
			return false;
		interLeaveSet.add( s1 + "_" + s2 );
		if ( s1.length() + s2.length() != s3.length() )
			return false;
		if ( s1.length() == 0 )
			return s2.equals( s3 );
		if ( s2.length() == 0 )
			return s1.equals( s3 );
		if ( s1.charAt( 0 ) == s3.charAt( 0 ) && s2.charAt( 0 ) == s3.charAt( 0 ) )
			return isInterleave( s1.substring( 1 ), s2, s3.substring( 1 ) ) || isInterleave( s1, s2.substring( 1 ), s3.substring( 1 ) );
		if ( s1.charAt( 0 ) == s3.charAt( 0 ) )
			return isInterleave( s1.substring( 1 ), s2, s3.substring( 1 ) );
		if ( s2.charAt( 0 ) == s3.charAt( 0 ) )
			return isInterleave( s1, s2.substring( 1 ), s3.substring( 1 ) );
		return false;
	}

	public int candy( int[] ratings )
	{
		int[] candy = new int[ratings.length];
		candy[0] = 1;
		int minCandy = 1, totalCandy = 0;

		for ( int i = 1; i < ratings.length; i++ )
		{
			if ( ratings[i] > ratings[i - 1] )
				candy[i] = candy[i - 1] + 1;
			// if ( ratings[i] == ratings[i - 1] )
			// candy[i] = candy[i - 1];
			if ( ratings[i] <= ratings[i - 1] )
				candy[i] = 1;
		}
		for ( int i = ratings.length - 1; i > 0; i-- )
		{
			if ( ratings[i] >= ratings[i - 1] )
				continue;
			if ( ratings[i] < ratings[i - 1] )
				candy[i - 1] = Math.max( candy[i - 1], candy[i] + 1 );
		}
		for ( int c : candy )
		{
			totalCandy += c;
			// totalCandy += -( 1 - minCandy );
		}
		return totalCandy;
	}

	public int trap( int[] n )
	{
		int s = n.length, maxl = 0, maxr = 0;
		int[] mL = new int[s], mR = new int[s];
		for ( int i = 0; i < s; i++ )
		{
			maxl = Math.max( maxl, n[i] );
			mL[i] = maxl;
			maxr = Math.max( maxr, n[s - 1 - i] );
			mR[s - 1 - i] = maxr;
		}
		System.out.println( Arrays.toString( mL ) );
		System.out.println( Arrays.toString( mR ) );
		int amount = 0;
		for ( int i = 0; i < s; i++ )
			amount += Math.min( mL[i], mR[i] ) - n[i];
		return amount;
	}

	public int findRadius( int[] houses, int[] heaters )
	{
		Arrays.sort( heaters );
		int d = 0, d1, d2;
		for ( int H : houses )
		{
			int pos = Arrays.binarySearch( heaters, H );
			// pos >=0 means it's found in heaters array, covered at all time
			// pos < 0 means pos = - insert - 1, thus insert position = -pos - 1
			if ( pos >= 0 )
				continue;
			System.out.println( pos );
			pos = -pos - 1;
			d1 = pos == 0 ? heaters[pos] - H : H - heaters[pos - 1];
			d2 = pos == heaters.length ? H - heaters[pos - 1] : heaters[pos] - H;
			System.out.printf( "%d\t%d\t%d\n", pos, d1, d2 );
			d = Math.max( d, Math.min( d1, d2 ) );
		}
		return d;
	}

	public int numSquares2( int n )
	{
		int sqrn = (int) Math.sqrt( n );
		int[] sqrs = new int[sqrn + 1], count = new int[n + 1];
		for ( int i = 1; i <= sqrn; i++ )
			sqrs[i] = i * i;
		for ( int i = 0; i <= n; i++ )
		{
			count[i] = Integer.MAX_VALUE;
		}
		for ( int k : sqrs )
			count[k] = 1;
		for ( int i = 0; i <= n; i++ )
		{
			if ( count[i] == 1 )
				continue;
			for ( int j = 1; j <= sqrn; j++ )
			{
				if ( i < sqrs[j] )
					continue;
				count[i] = Math.min( count[i], 1 + count[i - sqrs[j]] );
			}
		}
		return count[n];
	}

	public int numSquares3( int n )
	{
		if ( n == 1 )
			return 1;
		int[] f = new int[n + 1];
		Arrays.fill( f, Integer.MAX_VALUE );
		for ( int i = 1; i <= (int) ( Math.sqrt( n ) ); i++ )
			f[i * i] = 1;
		for ( int i = 2; i <= n; i++ )
		{
			if ( f[i] == 1 )
				continue;
			int sqi = (int) Math.sqrt( i );
			for ( int j = 1; j <= (int) Math.sqrt( i ); j++ )
			{
				f[i] = Math.min( f[i], f[j * j] + f[i - j * j] );
			}
		}
		return f[n];
	}

	// Set<Integer> col = new HashSet<>(), diag = new HashSet<>(), diagr = new HashSet<>();
	public int totalNQueens( int n )
	{
		return solveNQueensCount( n ).size();
	}

	public List<String> solveNQueensCount( int n )
	{
		List<String> list = new ArrayList<>();
		col = new boolean[n];
		diag = new boolean[2 * n];
		diagr = new boolean[2 * n];
		nQueensCount( list, 0, n );

		return list;
	}

	void nQueensCount( List<String> list, int row, int n )
	{
		if ( row == n )
		{
			list.add( "0" );
			return;
		}
		for ( int i = 0; i < n; i++ )
		{
			if ( col[i] || diag[row - i + n] || diagr[row + i] )
				continue;
			col[i] = true;
			diag[row - i + n] = true;
			diagr[row + i] = true;

			nQueensCount( list, row + 1, n );

			col[i] = false;
			diag[row - i + n] = false;
			diagr[row + i] = false;
		}
	}

	boolean[] col, diag, diagr;

	public List<List<String>> solveNQueens3( int n )
	{
		List<List<String>> list = new ArrayList<>();
		col = new boolean[n];
		diag = new boolean[2 * n];
		diagr = new boolean[2 * n];
		nQueen3( list, new ArrayList<>(), 0, n );
		return list;
	}

	void nQueen3( List<List<String>> res, List<String> list, int row, int n )
	{
		if ( row == n )
		{
			res.add( new ArrayList<>( list ) );
			return;
		}
		for ( int i = 0; i < n; i++ )
		{
			if ( col[i] || diag[row - i + n] || diagr[row + i] )
				continue;
			col[i] = true;
			diag[row - i + n] = true;
			diagr[row + i] = true;
			// if ( col.contains( i ) || diag.contains( row - i ) || diagr.contains( row + i ) )
			// continue;
			// col.add( i );
			// diag.add( row - i );
			// diagr.add( row + i );

			char[] sarray = new char[n];
			Arrays.fill( sarray, '.' );
			sarray[i] = 'Q';
			String s = String.valueOf( sarray );

			list.add( s );
			nQueen3( res, list, row + 1, n );
			list.remove( s );

			col[i] = false;
			diag[row - i + n] = false;
			diagr[row + i] = false;
			// col.remove( i );
			// diag.remove( row - i );
			// diagr.remove( row + i );

		}
	}

	public List<List<String>> solveNQueens( int n )
	{
		List<List<String>> list = new ArrayList<>();
		for ( int j = 0; j < n; j++ )
			list.addAll( nQueen( n, n, 0, j, new boolean[n], new boolean[2 * n], new boolean[2 * n] ) );

		return list;
	}

	List<List<String>> nQueen( int n, int k, int x, int y, boolean[] col, boolean[] diag, boolean[] diagr )
	{
		List<List<String>> list = new ArrayList<>();
		char[] st = new char[n];
		Arrays.fill( st, '.' );
		st[y] = 'Q';
		String s = String.valueOf( st );

		if ( k == 1 )
		{
			List<String> sList = new ArrayList<>();
			sList.add( s );
			list.add( sList );
			return list;
		}

		col[y] = true;
		diag[x - y + n] = true;
		diagr[x - ( n - 1 - y ) + n] = true;
		// System.out.printf( "%d\t%d\t%d\n", y, x - y + n, x + 1 + y );
		for ( int i = x + 1; i < n; i++ )
		{

			for ( int j = 0; j < n; j++ )
			{
				if ( col[j] || diag[i - j + n] || diagr[i - ( n - 1 - j ) + n] )
					continue;
				List<List<String>> newList = nQueen( n, k - 1, i, j, col, diag, diagr );

				for ( List<String> l : newList )
				{
					List<String> listk = new ArrayList<>();

					listk.add( s );

					listk.addAll( l );
					list.add( listk );
				}
				// listk.clear();
			}

		}
		col[y] = false;
		diag[x - y + n] = false;
		diagr[x - ( n - 1 - y ) + n] = false;

		return list;
	}

	public List<List<String>> solveNQueens2( int n )
	{
		List<List<String>> list = new ArrayList<>();
		for ( int j = 0; j < n; j++ )
			list.addAll( nQueen2( n, n, 0, j, new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashSet<>() ) );
		// System.out.println( list.toString() );
		return list;
	}

	List<List<String>> nQueen2( int n, int k, int x, int y, Set<Integer> row, Set<Integer> col, Set<Integer> diag, Set<Integer> diagr )
	{
		List<List<String>> list = new ArrayList<>();
		char[] st = new char[n];
		Arrays.fill( st, '.' );
		st[y] = 'Q';
		String s = String.valueOf( st );
		if ( k == 1 )
		{
			list.add( new ArrayList<>( Arrays.asList( new String[] { s } ) ) );
			return list;
		}

		row.add( x );
		col.add( y );
		diag.add( x - y );
		diagr.add( x - ( n - 1 - y ) );
		for ( int i = x + 1; i < n; i++ )
		{
			List<String> listk = new ArrayList<>();

			for ( int j = 0; j < n; j++ )
			{
				if ( col.contains( j ) || diag.contains( i - j ) || diagr.contains( i - ( n - 1 - j ) ) )
					continue;
				List<List<String>> newList = nQueen2( n, k - 1, i, j, row, col, diag, diagr );

				listk.add( s );
				for ( List<String> l : newList )
				{
					listk.addAll( l );
					list.add( listk );
				}
				listk.remove( listk.size() - 1 );
			}
		}
		row.remove( x );
		col.remove( y );
		diag.remove( x - y );
		diagr.remove( x - ( n - 1 - y ) );

		return list;
	}

	public List<Integer> countSmaller( int[] nums )
	{
		List<Integer> list = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		for ( int n : nums )
			set.add( n );
		Map<Integer, Integer> count = new HashMap<>();
		for ( int n : set )
			count.put( n, 0 );
		for ( int n : nums )
			if ( count.containsKey( n ) )
				count.put( n, count.get( n ) + 1 );
		for ( int n : nums )
		{
			int c = 0;
			for ( int s : count.keySet() )
				if ( n > s )
					c += count.get( s );
			count.put( n, count.get( n ) - 1 );
			list.add( c );
		}
		return list;
	}

	public int findDuplicate( int[] nums )
	{
		// [1,3,4,2,2]
		int slow = nums[0], fast = nums[slow];
		while ( slow != fast )
		{
			// System.out.printf( "slow %d\tfast %d\n", slow, fast );
			slow = nums[slow];
			fast = nums[nums[fast]];

		}

		slow = 0;
		while ( slow != fast )
		{
			slow = nums[slow];
			fast = nums[fast];
			// System.out.printf( "slow %d\tfast %d\n", slow, fast );
		}
		return slow;
	}

	// public int countDigitOne( int n )
	// {
	// long k = 1, partial_n = 0, ten = 1, step = 0, remainder = 0;
	// int count = 0;
	// if ( n < 0 )
	// return 0;
	// while ( n > 0 )
	// {
	// remainder = n % 10;
	// count += remainder * step * ten / 10;
	// count += remainder == 1 ? partial_n + 1 : remainder > 1 ? ten : 0;
	// partial_n += remainder * ten;
	// ten *= 10;
	// step++;
	// n /= 10;
	// }
	//
	// return count;
	// }

	// int countDigitOneHelper( int n, long[] f )
	// {
	// if ( n < 10 )
	// return n * f[0];
	// }

	public int countDigitOne2( int n )
	{
		StringBuilder s = new StringBuilder();
		for ( int i = 1; i <= n; i++ )
			s.append( String.valueOf( i ) );
		String str = s.toString().replaceAll( "[0,2-9]*", "" );
		// System.out.println( str );
		return str.length();
	}
	// public int strongPasswordChecker( String s )
	// {
	// int minChange = 0;
	// if(s.length()<6 || s.length()>20)
	// {
	// minChange=
	// }
	//
	// return minChange;
	// }

	public int countSegments( String s )
	{
		s = s.trim();
		if ( s.equals( "" ) )
			return 0;
		// String[] sp = s.split( "\\s+" );
		// System.out.println( Arrays.toString( s.split( "\\s+" ) ) );
		return s.split( "\\s+" ).length;

	}

	int fcount( long a, long b, int n )
	{
		int count = 0;
		while ( a <= n )
		{
			count += Math.min( n + 1, b ) - a;
			a *= 10;
			b *= 10;
		}

		return count;
	}

	int digit;

	public int findKthNumber2( int n, int k )
	{
		List<Integer> list = new ArrayList<>();
		for ( int i = 0; i < n; i++ )
			list.add( i + 1 );
		digit = numDigits( n );
		List<Integer> nlist = lexicoGraphicalSort( list, 0 );
		// System.out.println( nlist );
		return nlist.get( k - 1 );
	}

	int kthDigit( int n, int k )
	{
		char[] c = String.valueOf( n ).toCharArray();
		if ( c.length <= k )
			return -1;
		return c[k] - '0';
	}

	int numDigits( int n )
	{
		int c = 0;
		while ( n != 0 )
		{
			n /= 10;
			c++;
		}
		return c;
	}

	public List<String> summaryRanges2( int[] nums )
	{
		List<String> list = new ArrayList<>();
		if ( nums.length < 1 )
			return list;
		if ( nums.length < 2 )
		{
			list.add( String.valueOf( nums[0] ) );
			return list;
		}

		for ( int i = 0; i < nums.length; )
		{
			int j = 1;
			while ( j < nums.length - i && nums[i + j] - nums[i] == j )
				j++;
			String string;
			if ( j == 1 )
			{
				string = String.valueOf( nums[i] );
				i++;
			}
			else
			{
				string = String.valueOf( nums[i] ) + "->" + String.valueOf( nums[i + j - 1] );
				i = i + j;
			}
			list.add( string );
		}
		return list;

	}

	public int countNodes( TreeNode root )
	{
		if ( root == null )
			return 0;
		if ( root.left == null && root.right == null )
			return 1;
		return 1 + countNodes( root.left ) + countNodes( root.right );

	}

	public int ladderLength( String beginWord, String endWord, Set<String> wordList )
	{
		Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>(), visited = new HashSet<>(), tmp;
		beginSet.add( beginWord );
		endSet.add( endWord );
		int count = 1, index = 26;

		while ( !beginSet.isEmpty() && !endSet.isEmpty() )
		{

			if ( beginSet.size() > endSet.size() )
			{
				tmp = beginSet;
				beginSet = endSet;
				endSet = tmp;
			}
			tmp = new HashSet<>();
			for ( String string : beginSet )
			{
				visited.add( string );
				char[] chr = string.toCharArray();
				for ( int j = 0; j < chr.length; j++ )
				{
					char o = chr[j];
					for ( int i = 0; i < 26; i++ )
					{
						chr[j] = (char) ( 'a' + i );
						String newString = String.valueOf( chr );
						if ( visited.contains( newString ) )
							continue;
						if ( endSet.contains( newString ) )
							return count + 1;
						if ( wordList.contains( newString ) )
							tmp.add( newString );
					}
					chr[j] = o;
				}

			}
			beginSet = tmp;
			count++;
		}

		return 0;
	}

	void putMap( Map<String, Set<String>> map, String s1, String s2 )
	{
		map.get( s1 ).add( s2 );
		map.put( s1, map.get( s1 ) );
	}

	boolean findDiff( String s1, String s2 )
	{
		if ( s1.length() != s2.length() )
			return false;
		int count = 0;
		for ( int i = 0; i < s1.length(); i++ )
			if ( s1.charAt( i ) != s2.charAt( i ) )
				count++;

		return count == 1 ? true : false;
	}

	public ListNode addTwoNumbers( ListNode l1, ListNode l2 )
	{
		int len1 = countListNode( l1 ), len2 = countListNode( l2 ), c = 0, k = Math.abs( len1 - len2 );
		ListNode r = new ListNode( 0 ), n = r, t1 = l1, t2 = l2;
		if ( len1 >= len2 )
		{
			while ( k-- > 0 )
			{
				n.next = new ListNode( t1.val );
				n = n.next;
				t1 = t1.next;
			}
		}
		else
		{
			while ( k-- > 0 )
			{
				n.next = new ListNode( t2.val );
				n = n.next;
				t2 = t2.next;
			}
		}
		while ( t1 != null )
		{
			n.next = new ListNode( ( t1.val + t2.val ) % 10 );
			c = ( t1.val + t2.val ) / 10;
			n.val += c;
			n = n.next;
			t1 = t1.next;
			t2 = t2.next;
		}
		return r.val == 1 ? r : r.next;

	}

	int countListNode( ListNode l )
	{
		int count = 0;
		ListNode tListNode = l;
		while ( tListNode != null )
		{
			tListNode = tListNode.next;
			count++;
		}
		return count;
	}

	public int coinChange3( int[] coins, int amount )
	{
		if ( amount == 0 )
			return 0;
		int[] count = new int[amount + 1];
		for ( int i = 0; i <= amount; i++ )
			count[i] = Integer.MAX_VALUE;
		Arrays.sort( coins );
		for ( int coin : coins )
			if ( coin > amount )
				continue;
			else
				count[coin] = 1;
		for ( int i = 1; i <= amount; i++ )
		{
			if ( count[i] == 1 )
				continue;
			for ( int j = 0; j < coins.length; j++ )
			{
				if ( i < coins[j] || count[i - coins[j]] == Integer.MAX_VALUE )
					continue;
				else
					count[i] = Math.min( count[i], 1 + count[i - coins[j]] );
			}
		}
		// System.out.println( Arrays.toString( count ) );
		return count[amount] == Integer.MAX_VALUE ? -1 : count[amount];

	}

	public int coinChange2( int[] coins, int amount )
	{
		return coinHelper( coins, amount, new ArrayList<>() );
	}

	int coinHelper( int[] coins, int target, List<Integer> list )
	{
		int numCoins = Integer.MAX_VALUE;
		if ( target == 0 )
			return list.size();
		for ( int coin : coins )
		{
			if ( coin > target )
				continue;
			list.add( coin );
			numCoins = Math.min( numCoins, coinHelper( coins, target - coin, list ) );
			list.remove( list.size() - 1 );
		}
		return numCoins == Integer.MAX_VALUE ? -1 : numCoins;
	}

	public int countBattleships( char[][] board )
	{
		int m = board.length, n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		int count = 0;
		for ( int i = 0; i < m; i++ )
		{
			for ( int j = 0; j < n; j++ )
			{
				if ( board[i][j] == '.' || visited[i][j] == true )
					continue;
				int k = checkShip( board, visited, i, j );
				// if ( k != 0 )
				// System.out.println( i + " " + j );
				count += k;
			}
		}
		return count;
	}

	int checkShip( char[][] board, boolean[][] visited, int i, int j )
	{

		visited[i][j] = true;
		// check if single point
		if ( check4( board, i, j ) )
			return 1;
		int[] side = getSide( board, i, j, visited );
		if ( side == null )
			return 0;
		if ( checkSide( board, side, i, j ) )
			return 1;

		return 0;
	}

	int[] getSide( char[][] board, int i, int j, boolean[][] visited )
	{
		int[] res = new int[3];
		int up = i, down = i + 1, left = j, right = j + 1;
		boolean ud = false, lr = false;
		// while ( up >= 0 && board[up][j] == board[up + 1][j] )
		// {
		// visited[up][j] = true;
		// up--;
		// ud = true;
		// }
		// if ( up != i - 1 || up < 0 )
		// up++;
		while ( down < board.length && board[down - 1][j] == board[down][j] )
		{
			visited[down][j] = true;
			down++;
			ud = true;
		}
		// if ( down != i + 1 || down == board.length )
		// down--;
		// while ( left >= 0 && board[i][left] == board[i][left + 1] )
		// {
		// visited[i][left] = true;
		// left--;
		// lr = true;
		// }
		// if ( left != j - 1 || left < 0 )
		// left++;
		while ( right < board[0].length && board[i][right - 1] == board[i][right] )
		{
			visited[i][right] = true;
			right++;
			lr = true;
		}
		// if ( right != j + 1 || right == board[0].length )
		// right--;
		if ( ud )
		{
			res[0] = up;
			res[1] = down - 1;
			res[2] = -1;
		}
		if ( lr )
		{
			res[0] = left;
			res[1] = right - 1;
			res[2] = 1;
		}
		if ( !ud ^ lr )
			return null;
		return res;
	}

	boolean checkSide( char[][] board, int[] info, int i, int j )
	{
		int low = info[0], high = info[1], direction = info[2];
		if ( direction > 0 ) // left right
		{
			for ( int step = low; step <= high; step++ )
			{
				if ( i + 1 < board.length && board[i + 1][step] == 'X' )
					return false;
				if ( i - 1 >= 0 && board[i - 1][step] == 'X' )
					return false;
			}
		}
		if ( direction < 0 ) // up down
		{
			for ( int step = low; step <= high; step++ )
			{
				if ( j + 1 < board[0].length && board[step][j + 1] == 'X' )
					return false;
				if ( j - 1 >= 0 && board[step][j - 1] == 'X' )
					return false;
			}
		}
		return true;
	}

	boolean check4( char[][] board, int i, int j )
	{
		boolean up = false, down = false, left = false, right = false;
		if ( i == 0 )
			up = true;
		else
			up = board[i - 1][j] == '.';
		if ( j == 0 )
			left = true;
		else
			left = board[i][j - 1] == '.';
		if ( i == board.length - 1 )
			down = true;
		else
			down = board[i + 1][j] == '.';
		if ( j == board[0].length - 1 )
			right = true;
		else
			right = board[i][j + 1] == '.';

		return up && left && down && right;
	}

	public List<String> fizzBuzz( int n )
	{
		List<String> list = new ArrayList<>();
		for ( int i = 1; i <= n; i++ )
		{
			if ( i % 3 != 0 && i % 5 != 0 )
			{
				list.add( String.valueOf( i ) );
				continue;
			}
			else if ( i % 3 == 0 && i % 5 == 0 )
			{
				list.add( "FizzBuzz" );
				continue;
			}
			else
			{

				if ( i % 3 == 0 )
					list.add( "Fizz" );
				if ( i % 5 == 0 )
					list.add( "Buzz" );
			}
		}
		return list;
	}

	public List<Integer> findAnagrams( String s, String p )
	{
		List<Integer> list = new ArrayList<>();
		if ( s.length() == 0 || p.length() == 0 )
			return list;
		if ( s.length() < p.length() )
			return list;
		int[] count = new int[26], pcount = new int[26];
		for ( char c : p.toCharArray() )
			pcount[c - 'a']++;
		for ( int i = 0; i < p.length(); i++ )
			count[s.charAt( i ) - 'a']++;
		if ( Arrays.equals( count, pcount ) )
			list.add( 0 );
		for ( int i = p.length(); i < s.length(); i++ )
		{
			count[s.charAt( i - p.length() ) - 'a']--;
			count[s.charAt( i ) - 'a']++;
			if ( Arrays.equals( count, pcount ) )
				list.add( i - p.length() + 1 );
		}
		return list;
	}

	public boolean isSubsequence( String s, String t )
	{
		int m = s.length() - 1, n = t.length() - 1;
		while ( m >= 0 && n >= 0 )
		{
			if ( s.charAt( m ) == t.charAt( n ) )
			{
				m--;
				n--;
			}
			else
				n--;
		}
		return m == -1;
	}

	public String toHex( int num )
	{
		if ( num == 0 )
			return "0";
		long newNum = num > 0 ? num : (long) Math.pow( 2, 32 ) + num;
		String string = "";
		while ( newNum > 0 )
		{
			String c;
			if ( newNum % 16 > 9 )
				c = String.valueOf( (char) ( ( newNum % 16 - 10 ) + 97 ) );
			else
				c = String.valueOf( newNum % 16 );

			string = c + string;
			newNum /= 16;
		}

		return string;

	}

	public int[][] reconstructQueue( int[][] people )
	{
		if ( people == null || people.length == 0 )
			return new int[0][0];
		Arrays.sort( people, ( a, b ) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1] );
		for ( int[] k : people )
			System.out.print( Arrays.toString( k ) + " " );
		List<int[]> list = new ArrayList<>();
		for ( int i = 0; i < people.length; i++ )
		{
			list.add( people[i][1], people[i] );
		}
		return list.toArray( new int[1][] );
	}

	public int longestPalindrome( String s )
	{
		int[] upper = new int[26], lower = new int[26];

		for ( int i = 0; i < s.length(); i++ )
		{
			char c = s.charAt( i );
			if ( c >= 'a' )
				lower[c - 'a']++;
			else
				upper[c - 'A']++;
		}
		int count = 0;
		for ( int i = 0; i < upper.length; i++ )
		{
			if ( upper[i] >= 2 )
				count += upper[i] / 2;
			if ( lower[i] >= 2 )
				count += lower[i] / 2;
		}
		System.out.println( count );
		count *= 2;
		if ( count < s.length() )
			count++;
		return count;
	}

	public int lastRemaining( int n )
	{
		List<Integer> l1 = new ArrayList<>(), l2;
		for ( int i = 1; i <= n; i++ )
			l1.add( i );
		l2 = new ArrayList<>( l1 );
		while ( l1.size() > 1 && l2.size() > 1 )
		{

			l2.clear();

			for ( int i = 1; i < l1.size(); i += 2 )
				l2.add( l1.get( i ) );
			if ( l2.isEmpty() )
				break;
			l1 = new ArrayList<>( l2 );
			l2.clear();
			for ( int i = l1.size() - 2; i >= 0; i -= 2 )
				l2.add( 0, l1.get( i ) );
			if ( l2.isEmpty() )
				break;
			l1 = new ArrayList<>( l2 );
		}
		return l1.get( 0 );
	}

	public int maxRotateFunction( int[] A )
	{
		int sum = 0, f = 0, nextf;
		for ( int i = 0; i < A.length; i++ )
		{
			sum += A[i];
			f += i * A[i];
		}
		nextf = f;
		for ( int i = A.length - 1; i > 0; i-- )
		{
			nextf = nextf + sum - A.length * A[i];
			f = Math.max( nextf, f );
		}
		return f;
	}

	public String addStrings( String num1, String num2 )
	{
		String n1 = new StringBuffer( num1 ).reverse().toString(), n2 = new StringBuffer( num2 ).reverse().toString();
		StringBuffer sb = new StringBuffer();
		int carry = 0;
		int i = 0;
		while ( i < n1.length() && i < n2.length() )
		{
			sb.append( ( carry + n1.charAt( i ) - '0' + n2.charAt( i ) - '0' ) % 10 );
			carry = ( carry + n1.charAt( i ) - '0' + n2.charAt( i ) - '0' ) / 10;
			i++;
		}
		while ( i < n1.length() )
		{
			sb.append( ( carry + n1.charAt( i ) - '0' ) % 10 );
			carry = ( carry + n1.charAt( i ) - '0' ) / 10;
			i++;
		}
		while ( i < n2.length() )
		{
			sb.append( ( carry + n2.charAt( i ) - '0' ) % 10 );
			carry = ( carry + n2.charAt( i ) - '0' ) / 10;
			i++;
		}
		if ( carry != 0 )
			sb.append( 1 );
		return sb.reverse().toString();
	}

	public double[] calcEquation( String[][] equations, double[] values, String[][] queries )
	{
		// equations = [ ["a", "b"], ["b", "c"] ],
		// values = [2.0, 3.0],
		// queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].

		// basically graph theory

		Map<String, Map<String, Double>> map = new HashMap<>();
		for ( int i = 0; i < equations.length; i++ )
		{
			String[] s = equations[i];
			putMap( map, s[0], s[1], values[i] );
			putMap( map, s[1], s[0], 1 / values[i] );

		}
		double[] ret = new double[queries.length];
		for ( int i = 0; i < queries.length; i++ )
		{
			String start = queries[i][0], end = queries[i][1];
			ret[i] = findProd( findMap( map, start, end, new ArrayList<>() ), map );
		}
		return ret;
	}

	void putMap( Map<String, Map<String, Double>> map, String key, String matchKey, double value )
	{
		if ( map.containsKey( key ) )
		{
			map.get( key ).put( matchKey, value );
		}
		else
		{
			Map<String, Double> newMap = new HashMap<>();
			newMap.put( matchKey, value );
			map.put( key, newMap );
		}
	}

	double findProd( List<String> list, Map<String, Map<String, Double>> map )
	{
		if ( list == null )
			return -1;
		double v = 1;
		// System.out.println( list );
		for ( int i = 0; i < list.size() - 1; i++ )
		{
			v *= map.get( list.get( i ) ).get( list.get( i + 1 ) );
		}
		// System.out.println( v );
		return v;
	}

	List<String> findMap( Map<String, Map<String, Double>> map, String start, String end, List<String> path )
	{
		if ( !map.containsKey( start ) )
			return null;
		path.add( start );
		if ( map.get( start ).containsKey( end ) )
		{
			path.add( end );
			return path;
		}
		for ( String nextStart : map.get( start ).keySet() )
		{
			if ( !path.contains( nextStart ) )
			{
				List<String> newPath = findMap( map, nextStart, end, path );
				if ( newPath != null )
					return newPath;
			}
		}
		path.remove( path.size() - 1 );
		return null;
	}

	public int findNthDigit( int n )
	{
		int k = 1;
		while ( n - (int) Math.pow( 10, k ) + (int) Math.pow( 10, k - 1 ) >= 0 )
		{
			n -= (int) Math.pow( 10, k ) - (int) Math.pow( 10, k - 1 );
			k++;

		}
		// if ( n == 0 )
		// {
		// return (int) Math.pow( 10, k - 1 ) - 1;
		// }
		// else
		{
			n -= 1;
			int num = (int) Math.pow( 10, k - 1 ) + n / k, res = n % k;
			return Integer.valueOf( String.valueOf( String.valueOf( num ).charAt( res ) ) );
		}

	}

	int findNthDigit_S( int n )
	{
		StringBuilder stringBuilder = new StringBuilder();
		int i = 0;
		while ( stringBuilder.length() < n )
			stringBuilder.append( String.valueOf( i++ ) );
		System.out.println( stringBuilder.toString() );
		return Integer.valueOf( String.valueOf( stringBuilder.charAt( stringBuilder.length() - 1 ) ) );
	}

	public List<String> readBinaryWatch( int num )
	{
		List<Integer> hr = new ArrayList<>( Arrays.asList( new Integer[] { 1, 2, 4, 8 } ) ),
				min = new ArrayList<>( Arrays.asList( new Integer[] { 1, 2, 4, 8, 16, 32 } ) );
		List<String> list = new ArrayList<>();
		if ( num > 8 )// || num < 1 )
			return list;
		for ( int i = 0; i <= num; i++ )
		{
			List<Integer> hours = comb( hr, i, 11 );
			List<Integer> mins = comb( min, num - i, 59 );
			for ( Integer h : hours )
			{
				for ( Integer m : mins )
				{
					String string = String.format( "%1d:%02d", h, m );
					list.add( string );
				}
			}
		}
		return list;
	}

	List<Integer> comb( List<Integer> time, int n, int threshold )
	{
		// if ( time.size() < n )
		// return null;
		List<Integer> list = new ArrayList<>();
		if ( n == 0 )
		{
			list.add( 0 );
			return list;
		}
		if ( n == 1 )
		{
			for ( int k : time )
				list.add( k );
			return list;
		}
		for ( int i = 0; i < time.size(); i++ )
		{
			List<Integer> tList = comb( time.subList( i + 1, time.size() ), n - 1, threshold );
			for ( int k = 0; k < tList.size(); k++ )
			{
				if ( time.get( i ) + tList.get( k ) < threshold )
					list.add( time.get( i ) + tList.get( k ) );
			}
		}
		return list;
	}

	int longestCommonSubsequence( String s1, String s2 )
	{
		// finding LCS of s2 in s1
		int m = s1.length(), n = s2.length();
		int[] T = new int[n];
		for ( int i = 1; i < n; i++ )
			T[i] = n + 1;
		for ( int i = 0; i < m; i++ )
		{
			for ( int j = n - 1; j >= 0; j-- )
			{
				if ( s1.charAt( i ) == s2.charAt( j ) )
				{
					for ( int k = 1; k < n; k++ )
					{
						if ( T[k - 1] < j && j <= T[k] )
						{
							T[k] = j;
							break;
						}
					}
				}
			}
		}
		System.out.println( Arrays.toString( T ) );

		return 0;
	}

	// Pay attention!
	// the prefix under index i in the table above is
	// is the string from pattern[0] to pattern[i - 1]
	// inclusive, so the last character of the string under
	// index i is pattern[i - 1]

	int[] kmpArray( String s )
	{
		int[] f = new int[s.length()];
		f[0] = 0;
		for ( int i = 1; i < s.length(); i++ )
		{
			if ( f[i - 1] == 0 )
				// no prior match
				// base case, f[i] matches s[0] or not
				f[i] = s.charAt( i ) == s.charAt( 0 ) ? 1 : 0;
			else
			{
				int fi = f[i - 1];
				if ( s.charAt( i ) == s.charAt( fi ) )
				{ // base case, previously matched, next one matched
					f[i] = f[i - 1] + 1;
					continue;
				}
				// find previous match, using f[i-1] = f[f[i-1]]
				// because f[f[i-1]] is the match for current f[i-1]
				while ( fi != 0 && s.charAt( i ) != s.charAt( fi ) )
					fi = f[fi - 1];
				if ( s.charAt( i ) == s.charAt( fi ) )
					f[i] = fi == 0 ? 1 : 0;
			}
		}
		return f;
	}

	int[] build_failure_function( char[] pattern )
	{
		// let m be the length of the pattern
		int[] F = new int[pattern.length + 1];
		int i, j, m = pattern.length;
		F[0] = F[1] = 0; // always true

		for ( i = 2; i <= m; i++ )
		{
			// j is the index of the largest next partial match
			// (the largest suffix/prefix) of the string under
			// index i - 1
			j = F[i - 1];
			for ( ;; )
			{
				// check to see if the last character of string i -
				// - pattern[i - 1] "expands" the current "candidate"
				// best partial match - the prefix under index j
				if ( pattern[j] == pattern[i - 1] )
				{
					F[i] = j + 1;
					break;
				}
				// if we cannot "expand" even the empty string
				if ( j == 0 )
				{
					F[i] = 0;
					break;
				}
				// else go to the next best "candidate" partial match
				j = F[j];
			}
		}
		return F;
	}

	public int strStr_( String haystack, String needle )
	{
		if ( haystack.length() < needle.length() )
			return -1;
		if ( haystack.equals( "" ) || needle.equals( "" ) )
			return 0;
		int[] kmp = kmpArray( needle );
		for ( int i = 0; i <= haystack.length() - needle.length(); i++ )
		{
			if ( haystack.charAt( i ) != needle.charAt( 0 ) )
				continue;
			// now 0 index matches
			int j = 0, k = i;
			while ( k < haystack.length() && j < needle.length()
					&& haystack.charAt( k ) == needle.charAt( j ) )
			{
				k++;
				j++;
			}
			if ( j == needle.length() )
				return i;
			i += kmp[j];
		}
		return -1;
	}

	public ListNode reverseKGroup_( ListNode head, int k )
	{
		if ( head == null || head.next == null )
			return head;
		ListNode ret = new ListNode( 0 ), t = ret;
		ret.next = head;
		while ( t != null )
		{
			ListNode t_ = t;
			// test if at end
			for ( int i = 0; i < k; i++ )
			{
				t_ = t_.next;
				if ( t_ == null )
					return ret.next;

			}
			// now reverse next k
			t_ = t.next; // tail
			ListNode _t = t_; // head
			int k_ = 1;
			while ( k_ < k )
			{
				t.next = t_.next;
				t_.next = t.next.next;
				t.next.next = _t;
				_t = t.next;
				k_++;
				ret.print();
			}
			k_ = 0;
			while ( k_ < k )
			{
				t = t.next;
				k_++;
			}
		}
		return ret.next;
	}

	public List<List<Integer>> fourSum( int[] nums, int target )
	{
		List<List<Integer>> retList = new ArrayList<>();
		if ( nums.length < 4 )
			return retList;
		int tmpTarget, size = nums.length, j, k, sum;
		Arrays.sort( nums );
		Set<Integer> set = new HashSet<>(), seti = new HashSet<>();
		for ( int l = 0; l < size - 3; l++ )
		{
			seti = new HashSet<>();
			tmpTarget = target - nums[l];
			if ( set.contains( tmpTarget ) )
				continue;
			set.add( tmpTarget );
			// this part is 3 sum
			for ( int i = l + 1; i < size - 2; i++ )
			{

				if ( seti.contains( nums[i] ) )
					continue;
				seti.add( nums[i] );
				j = i + 1;
				k = size - 1;
				while ( j < k )
				{
					int a = nums[i], b = nums[j], c = nums[k];
					sum = a + b + c;
					if ( sum == tmpTarget )
						retList.add( Arrays.asList( nums[l], a, b, c ) );
					if ( sum >= tmpTarget )
					{
						k--;
						while ( j < k && nums[k] == nums[k + 1] )
							k--;
					}
					if ( sum <= tmpTarget )
					{
						j++;
						while ( j < k && nums[j] == nums[j - 1] )
							j++;
					}
				}
			}
		}

		return retList;
	}

	public UndirectedGraphNode cloneGraph( UndirectedGraphNode node )
	{
		Map<UndirectedGraphNode, UndirectedGraphNode> oldNewMap = new HashMap<>(),
				newOldMap = new HashMap<>();
		UndirectedGraphNode clone = new UndirectedGraphNode( node.label );
		oldNewMap.put( node, clone );
		newOldMap.put( clone, node );
		// bfs travel
		return clone;
	}

	public RandomListNode copyRandomList_( RandomListNode head )
	{
		RandomListNode ret = new RandomListNode( 0 ), t = ret, tr, h = head, hnext;
		while ( h != null )
		{
			hnext = h.next;
			h.next = new RandomListNode( h.label );
			h.next.next = hnext;
			h = hnext;
		}
		h = head;
		while ( h != null )
		{
			if ( h.random != null )
				h.next.random = h.random.next;
			h = h.next.next;
		}
		h = head;
		// adjust original
		while ( h != null )
		{
			t.next = h.next;
			h.next = h.next.next;
			t = t.next;
			h = h.next;
		}
		return ret.next;
	}

	public RandomListNode copyRandomList( RandomListNode head )
	{
		RandomListNode ret = new RandomListNode( 0 ), t = ret, tr, h = head, hr;
		// t is tmp, tr is tmp random, h is head, hr is head random
		Map<RandomListNode, RandomListNode> oldNewMap = new HashMap<>(), randomMap = new HashMap<>(), newOldMap = new HashMap<>();
		// copy original list
		RandomListNode node;
		while ( h != null )
		{
			node = new RandomListNode( h.label );
			t.next = node;
			oldNewMap.put( node, h );
			newOldMap.put( h, node );
			randomMap.put( h, h.random );
			h = h.next;
			t = t.next;
		}
		t = ret.next;
		h = head;
		// copy original random
		while ( h != null )
		{
			t.random = newOldMap.get( randomMap.get( oldNewMap.get( t ) ) );
			h = h.next;
			t = t.next;
		}

		return ret.next;
	}

	public int[] searchRange( int[] nums, int target )
	{
		int l = 0, size = nums.length - 1, r = size, m = ( l + r ) / 2;
		if ( size == 0 )
			return nums[0] == target ? new int[] { 0, 0 } : new int[] { -1, -1 };
		int[] ret = new int[2];
		// finding left most position
		while ( l < r )
		{
			if ( target <= nums[m] )
				r = m;
			else
				l = m + 1;
			m = ( l + r ) / 2;
		}
		// now r-1 is left pos
		ret[0] = r;
		if ( nums[r] != target )
			return new int[] { -1, -1 };
		l = 0;
		r = size;
		m = ( l + r ) / 2;
		while ( l < r )
		{
			if ( target < nums[m] )
				r = m;
			else
				l = m + 1;
			m = ( l + r ) / 2;
		}
		ret[1] = nums[r] == target ? r : r - 1;
		return ret;
	}

	public List<Integer> lexicalOrder( int n )
	{
		List<Integer> list = new ArrayList<>( n );
		for ( int i = 1; i <= n; i++ )
			list.add( i );
		Collections.sort( list, new Comparator<Integer>()
		{

			public int compare( Integer o1, Integer o2 )
			{
				String s1 = o1.toString(), s2 = o2.toString();
				int i = 0, j = 0;
				while ( i < s1.length() && j < s2.length() )
				{

					if ( s1.charAt( i ) != s2.charAt( j ) )
						return s1.charAt( i ) < s2.charAt( j ) ? -1 : 1;
					i++;
					j++;
				}
				if ( i == s1.length() )
					return -1;
				return 1;

			}
		} );

		System.out.println( list );
		return list;
	}

	public List<List<Integer>> permuteUnique_( int[] nums )
	{
		if ( nums.length == 0 )
			return null;
		Arrays.sort( nums );
		List<Integer> list = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		for ( int n : nums )
			list.add( n );
		// List<List<Integer>> llist = new ArrayList<>( getUniqP( list ) );
		return getUniqP( list );
	}

	List<List<Integer>> getUniqP( List<Integer> list )
	{
		List<List<Integer>> rList = new ArrayList<>(), tmpRList;
		if ( list.size() == 1 )
		{
			rList.add( list );
			return rList;
		}

		int k;
		for ( int i = 0; i < list.size(); i++ )
		{
			while ( i < list.size() - 1 && list.get( i ) == list.get( i + 1 ) )
				i++;
			k = list.remove( i );
			List<Integer> tmpList = new ArrayList<>( list );
			list.add( i, k );
			tmpRList = getUniqP( tmpList );

			for ( Iterator<List<Integer>> iterator = tmpRList.iterator(); iterator.hasNext(); )
			{
				iterator.next().add( 0, k );
			}
			rList.addAll( tmpRList );
		}
		return rList;
	}

	public List<List<Integer>> permute_( int[] nums )
	{
		List<Integer> list = new ArrayList<>();
		for ( int n : nums )
			list.add( n );
		// List<List<Integer>> ret = new ArrayList<>();
		return getP( list );
	}

	List<List<Integer>> getP( List<Integer> list )
	{
		List<List<Integer>> r = new ArrayList<>(), tmpr, tmpr2;
		if ( list.size() == 1 )
		{
			r.add( list );
			return r;
		}
		int k;
		for ( int i = 0; i < list.size(); i++ )
		{
			k = list.remove( i );
			List<Integer> tmplist = new ArrayList<>( list ), tmpList2;
			list.add( i, k );
			tmpr = getP( tmplist ); // permutation of list with 1 element less
			for ( int j = 0; j < tmpr.size(); j++ )
			{
				tmpr.get( j ).add( 0, k );
			}
			r.addAll( tmpr );
		}
		return r;
	}

	public List<List<Integer>> subsets( int[] nums )
	{
		List<List<Integer>> retList = new ArrayList<>(), tmp = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		retList.add( list );
		for ( int i = 0; i < nums.length; i++ )
		{
			tmp.clear();
			ListIterator<List<Integer>> iterator = retList.listIterator();
			while ( iterator.hasNext() )
			{
				List<Integer> tmplist = new ArrayList<>( iterator.next() );
				tmplist.add( nums[i] );
				tmp.add( tmplist );
			}
			retList.addAll( tmp );
		}
		return retList;

	}

	public String getPermutation( int n, int k )
	{
		List<Integer> list = new ArrayList<>();
		int i = 1, a, b, f;
		while ( i <= n )
			list.add( i++ );
		String s = "";
		while ( n != 0 )
		{
			f = factorial( n - 1 );
			a = k / f;
			b = k % f;
			if ( b == 0 )
			{
				s += list.get( a - 1 );
				list.remove( a - 1 );
				for ( i = list.size() - 1; i >= 0; i-- )
					s += list.get( i );
				return s;
			}
			else
			{
				s += list.get( a );
				list.remove( a );
				n--;
				k = b;
			}
		}
		return s;
	}

	int factorial( int n )
	{
		if ( n == 0 || n == 1 )
			return 1;
		return factorial( n - 1 ) * n;
	}

	public boolean increasingTriplet( int[] nums )
	{
		if ( nums.length == 0 )
			return false;
		int[] stack = new int[nums.length];
		stack[0] = nums[0];
		int count = 1, left = 0, right = count, mid = ( left + right ) / 2;
		for ( int i = 1; i < nums.length; i++ )
		{
			left = 0;
			right = count - 1;

			// binary search for position where nums[i] to be inserted
			while ( left < right - 1 )
			{
				if ( nums[i] < stack[mid] )
					right = mid;
				else
					left = mid;
				mid = ( left + right ) / 2;
			}
			// now nums[i] is between left and right
			if ( stack[left] >= nums[i] )
				stack[left] = nums[i];
			else
			{
				if ( stack[right] >= nums[i] )
					stack[right] = nums[i];
				else
				{
					stack[right + 1] = nums[i];

					if ( right + 1 == count )
						count++;
				}
			}
			System.out.println( Arrays.toString( stack ) + "\t" + count );
		}
		return count < 3 ? false : true;
	}

	public int lengthOfLIS( int[] nums )
	{
		if ( nums.length == 0 )
			return 0;
		int[] stack = new int[nums.length];
		stack[0] = nums[0];
		int length = 1, left = 0, right = length - 1, mid = left + ( right - left ) / 2;
		for ( int i = 1; i < nums.length; i++ )
		{
			System.out.println( Arrays.toString( stack ) );
			left = 0;
			right = length - 1;
			int[] tmp = Arrays.copyOfRange( stack, 0, length );
			right = Arrays.binarySearch( tmp, nums[i] );
			if ( right < 0 )
				right = -right - 1;
			if ( right == 0 )
				right = 1;
			if ( stack[right - 1] < nums[i] )
			{
				stack[right] = nums[i];
				if ( right == length )
					length++;
			}
			else
				stack[right - 1] = nums[i];

		}
		return length;
	}

	public int maxEnvelopes( int[][] envelopes )
	{
		List<Envelope> list = new ArrayList<>();
		for ( int i = 0; i < envelopes.length; i++ )
		{
			list.add( new Envelope( envelopes[i][0], envelopes[i][1] ) );
		}
		Collections.sort( list, new EnvelopeSort() );

		for ( int i = 0; i < list.size(); i++ )
			System.out.print( list.get( i ).Member() + " x " );

		int count = 0, i = 0, j = 0;
		for ( i = 0; i < list.size() - 1; i++ )
		{
			if ( list.get( j )._w < list.get( i + 1 )._w &&
					list.get( j )._h < list.get( i + 1 )._h )
			{
				count++;
				j = i;
			}
		}
		return count;

	}

	public int kthSmallest( int[][] matrix, int k )
	{
		int n = matrix.length, count = n * n, step = 0;
		if ( n == 0 || k > count )
			return 0;

		int[] index = new int[matrix.length];
		for ( int i = 0; i < n; i++ )
			index[i] = n - 1;

		while ( count != k - 1 )
		{
			int max = Integer.MIN_VALUE;
			for ( int j = 0; j < n; j++ )
			{
				if ( index[j] == -1 )
					continue;
				if ( max < matrix[j][index[j]] )
				{
					max = matrix[j][index[j]];
					step = j; // step is max row
				}
			}
			index[step]--;
			count--;
		}
		return matrix[step][index[step] + 1];
	}

	public boolean isValidSerialization( String preorder )
	{
		if ( preorder.equalsIgnoreCase( "#" ) )
			return true;
		String[] c = preorder.split( "," );
		if ( c.length < 3 )
			return false;
		System.out.println( find( c, 0 ) );
		if ( find( c, 0 ) == c.length - 1 )
			return true;
		return false;
		/*
		int sum = 1;
		for ( String k : c )
		
			sum += k.equalsIgnoreCase( "#" ) ? 0 : 2;
		
		return sum == c.length ? true : false;
		*/
	}

	int find( String[] a, int index )
	{
		if ( index > a.length - 1 )// || index + 1 > a.length - 1 )
			return a.length;
		if ( a[index].equalsIgnoreCase( "#" ) )
			return index;
		if ( index + 1 > a.length - 1 )
			return a.length;
		if ( !a[index + 1].equalsIgnoreCase( "#" ) )
			// find(a,index+1) finds last index of left tree
			// find ( a, left tree + 1 ) = find ( a, right tree)
			return find( a, find( a, index + 1 ) + 1 );
		// find(a,index+2) finds last index of right tree, as left tree is #
		return find( a, index + 2 );
	}

	public int maxProfit_CD( int[] prices )
	{
		if ( prices.length < 2 )
			return 0;
		if ( prices.length == 2 )
			return Math.max( 0, prices[1] - prices[0] );
		int[] f = new int[prices.length];
		f[0] = 0;
		f[1] = Math.max( prices[1] - prices[0], 0 );
		int sum = 0, minj = Math.max( -prices[0], -prices[1] );
		for ( int i = 2; i < prices.length; i++ )
		{
			minj = Math.max( minj, f[i - 2] - prices[i] );
			f[i] = Math.max( f[i - 1], prices[i] + minj );
		}
		System.err.println( Arrays.toString( f ) );
		return f[prices.length - 1];
	}

	public int[] intersection_2( int[] nums1, int[] nums2 )
	{
		Map<Integer, Integer> map1 = new HashMap<>(), map2 = new HashMap<>(), imap = new HashMap<>();
		for ( int k : nums1 )
		{
			if ( map1.containsKey( k ) )
				map1.put( k, map1.get( k ) + 1 );
			else
				map1.put( k, 1 );
		}
		for ( int k : nums2 )
		{
			if ( map2.containsKey( k ) )
				map2.put( k, map2.get( k ) + 1 );
			else
				map2.put( k, 1 );
		}
		Iterator<Entry<Integer, Integer>> iterator = map1.entrySet().iterator();
		int sum = 0, tmp = 0;
		while ( iterator.hasNext() )
		{
			Entry<Integer, Integer> entry = iterator.next();
			tmp = 0;
			if ( map2.containsKey( entry.getKey() ) )
			{
				tmp = Math.min( entry.getValue(), map2.get( entry.getKey() ) );
				imap.put( entry.getKey(), tmp );
				sum += tmp;

			}
		}
		int[] r = new int[sum];
		int k = 0;
		iterator = imap.entrySet().iterator();
		while ( iterator.hasNext() )
		{
			Entry<Integer, Integer> entry = iterator.next();
			int size = entry.getValue();
			int key = entry.getKey();
			while ( size-- > 0 )
				r[k++] = key;

		}
		return r;

	}

	public int[] intersection( int[] nums1, int[] nums2 )
	{
		Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
		for ( int i : nums1 )
			set1.add( i );
		for ( int i : nums2 )
			set2.add( i );
		set1.retainAll( set2 );
		int[] r = new int[set1.size()];
		Iterator<Integer> iterator = set1.iterator();
		int k = 0;
		for ( ; iterator.hasNext(); )
			r[k++] = iterator.next();
		return r;
	}

	public int superPow_( int a, int[] b )
	{
		int basepow = (int) ( Math.log( 1337 ) / Math.log( a ) ) + 1;
		int modResult = (int) Math.pow( a, basepow ) % 1337;
		long blong = 0;
		for ( int k : b )
			blong += k * 10;
		int left = (int) ( blong % basepow );
		return -1;
	}

	public int superPow_B( int a, int[] b )
	{
		BigInteger bi_a = new BigInteger( String.valueOf( a ) );
		String s_b = "";
		for ( int k : b )
			s_b += k;
		BigInteger bi_b = new BigInteger( s_b );
		BigInteger bi_1337 = new BigInteger( "1337" );
		return bi_a.modPow( bi_b, bi_1337 ).intValue();
	}

	public int rob( TreeNode root )
	{
		if ( root == null )
			return 0;
		if ( root.left == null && root.right == null )
			return root.val;
		int v = root.val;
		if ( root.left != null )
			v += rob( root.left.left ) + rob( root.left.right );
		if ( root.right != null )
			v += rob( root.right.left ) + rob( root.right.right );
		return Math.max( v, rob( root.left ) + rob( root.right ) );
	}

	public int rob2( int[] nums )
	{
		if ( nums.length == 0 )
			return 0;
		if ( nums.length == 1 )
			return nums[0];
		if ( nums.length == 2 )
			return Math.max( nums[0], nums[1] );
		if ( nums.length == 3 )
			return Math.max( nums[0], Math.max( nums[2], nums[1] ) );

		int r0 = nums[0], r1 = Math.max( nums[0], nums[1] ), r2 = 0, maxtmp = 0;
		for ( int i = 2; i < nums.length - 1; i++ )
		{
			r2 = Math.max( r1, r0 + nums[i] );
			r0 = r1;
			r1 = r2;
		}
		maxtmp = r2;
		r0 = nums[1];
		r1 = Math.max( nums[1], nums[2] );
		r2 = 0;
		for ( int i = 3; i < nums.length; i++ )
		{
			r2 = Math.max( r1, r0 + nums[i] );
			r0 = r1;
			r1 = r2;
		}
		return Math.max( r2, maxtmp );
	}

	public int getMoneyAmount( int n )
	{
		if ( n == 1 )
			return 0;
		if ( n == 2 )
			return 1;
		int l = 1, r = n, m = l + ( r - l ) / 2, sum = 0;
		while ( r - l > 1 )
		{
			sum += m;
			l = m;
			m = l + ( r - l ) / 2;
		}
		return sum + l;
	}

	int guess( int num )
	{
		return 2 == num ? 0 : 6 > num ? -1 : 1;

	}

	public int guessNumber( int n )
	{
		int l = 1, r = n, m = l + ( r - l ) / 2;
		int result = guess( m );
		while ( result != 0 && r - l > 1 )
		{
			if ( result == -1 )
				r = m;
			else
				l = m;
			m = l + ( r - l ) / 2;
			result = guess( m );
		}
		if ( guess( m ) == 0 )
			return m;
		return guess( l ) == 0 ? l : r;

	}

	public List<int[]> kSmallestPairs( int[] nums1, int[] nums2, int k )
	{
		int[] adder = new int[nums1.length];
		List<int[]> list = new ArrayList<>();
		if ( nums1.length < 1 || nums2.length < 1 )
			return list;

		int a = 0, b = 0, min = Integer.MAX_VALUE, index = 0, count = 0;
		while ( count < Math.min( nums1.length * nums2.length, k ) )
		{
			min = Integer.MAX_VALUE;
			for ( int i = 0; i < nums1.length; i++ )
			{
				if ( adder[i] == nums2.length )
					continue;
				if ( min > nums1[i] + nums2[adder[i]] )
				{
					min = nums1[i] + nums2[adder[i]];
					index = i;
				}
			}
			int[] tmp = { nums1[index], nums2[adder[index]] };
			list.add( tmp );
			adder[index]++;
			count++;
		}
		return list;
	}

	public int maxProfit_2( int k, int[] prices )
	{
		int[] f = new int[prices.length];
		int maxj = 0, maxProf = 0;
		for ( int i = 1; i <= Math.min( k, prices.length ); i++ )
		{
			maxj = f[0] - prices[0];
			for ( int j = 1; j < prices.length; j++ )
			{
				maxj = Math.max( maxj, f[j] - prices[j] );
				f[j] = Math.max( f[j - 1], maxj + prices[j] );
			}
			// System.out.println( Arrays.toString( f ) );
			if ( maxProf < f[prices.length - 1] )
				maxProf = f[prices.length - 1];
			else
				return maxProf;
		}
		return f[prices.length - 1];
	}

	public int maxProfit( int k, int[] prices )
	{
		int[][] f = new int[k + 1][prices.length];
		int maxj = 0;
		for ( int i = 1; i <= k; i++ )
		{
			maxj = f[i - 1][0] - prices[0];
			for ( int j = 1; j < prices.length; j++ )
			{
				maxj = Math.max( maxj, f[i - 1][j] - prices[j] );
				f[i][j] = Math.max( f[i][j - 1], maxj + prices[j] );
			}

		}
		for ( int i = 0; i <= k; i++ )
		{
			for ( int j = 0; j < prices.length; j++ )
			{
				System.out.print( f[i][j] + " " );
			}
			System.out.println();
		}
		return f[k][prices.length - 1];

	}

	public int maxProfit_3( int[] prices )
	{
		if ( prices.length < 2 )
			return 0;
		if ( prices.length == 2 )
			return Math.max( prices[1] - prices[0], 0 );

		// kadane's algo
		int tmp = 0, change = 0, r = 0;
		List<Integer> total = new ArrayList<>();

		for ( int i = 0; i < prices.length - 1; i++ )
		{
			change = prices[i + 1] - prices[i];

			if ( change < 0 )
			{
				total.add( tmp );
				tmp = 0;
			}
			else
				tmp += change;
		}
		Collections.sort( total );
		Collections.reverse( total );
		if ( total.isEmpty() )
			return tmp;
		if ( total.size() < 2 )
			return total.get( 0 );
		return total.get( 0 ) + total.get( 1 );
	}

	public int maxProfit_2( int[] prices )
	{
		if ( prices.length < 2 )
			return 0;
		if ( prices.length == 2 )
			return Math.max( prices[1] - prices[0], 0 );

		int[] changes = new int[prices.length - 1];
		int maxEndingHere = 0, maxSoFar = 0, maxSoFarTotal = 0;
		for ( int i = 0; i < prices.length - 1; i++ )
		{
			changes[i] = prices[i + 1] - prices[i];
			maxEndingHere += changes[i];
			if ( maxEndingHere <= 0 )
			{
				maxEndingHere = 0;
				maxSoFarTotal += maxSoFar;
				maxSoFar = 0;
			}
			maxSoFar = Math.max( maxSoFar, maxEndingHere );
		}
		return Math.max( maxSoFar, maxSoFarTotal );
	}

	public int maxProfit_1( int[] prices )
	{
		int[] changes = new int[prices.length - 1];
		int maxEndingHere = 0, maxSoFar = 0;
		for ( int i = 0; i < prices.length - 1; i++ )
		{
			changes[i] = prices[i + 1] - prices[i];
			maxEndingHere += changes[i];
			maxEndingHere = Math.max( maxEndingHere, 0 );
			maxSoFar = Math.max( maxSoFar, maxEndingHere );
		}
		return maxSoFar;
	}

	public int maxProfit( int[] prices )
	{
		int[] changes = new int[prices.length - 1];
		for ( int i = 0; i < prices.length - 1; i++ )
			changes[i] = prices[i + 1] - prices[i];
		return divideConqure( changes, 0, changes.length - 1 );
	}

	int divideConqure( int[] changes, int left, int right )
	{
		if ( left == right )
			return Math.max( 0, changes[left] );
		if ( right - left == 1 )
			return Math.max( 0, Math.max( changes[left], Math.max( changes[left] + changes[right], changes[right] ) ) );
		int mid = ( left + right ) / 2;
		// count from mid

		int midmaxl = 0, midmaxr = 0, pos = mid, cmax = 0;
		while ( pos >= left )
		{
			cmax = cmax + changes[pos];
			midmaxl = Math.max( midmaxl, cmax );
			pos--;
		}
		cmax = 0;
		pos = mid + 1;
		while ( pos <= right )
		{
			cmax = cmax + changes[pos];
			midmaxr = Math.max( midmaxr, cmax );
			pos++;
		}
		int leftmax = divideConqure( changes, left, mid );
		int rightmax = divideConqure( changes, mid, right );
		return Math.max( leftmax, Math.max( midmaxl + midmaxr, rightmax ) );
	}

	public boolean canMeasureWater( int x, int y, int z )
	{
		if ( z > x + y )
			return false;
		if ( z == x + y )
			return true;
		return z % gcd( x, y ) == 0;
	}

	int gcd( int a, int b )
	{
		if ( a > b )
		{
			// swap a, b
			int t = a;
			a = b;
			b = t;
		}
		if ( a == 0 )
			return b;
		return gcd( a, b % a );
	}

	public boolean canJump( int[] nums )
	{
		if ( nums.length < 1 )
			return false;
		if ( nums.length == 1 )
			return nums[0] >= 0;
		int max = nums[0];
		for ( int i = 0; i <= max; i++ )
		{
			max = Math.max( max, i + nums[i] );
			if ( max >= nums.length - 1 )
				return true;
		}
		return false;
	}

	public void gameOfLife( int[][] board )
	{
		int[][] board2 = new int[board.length][board[0].length];
		int i, j, m = board.length, n = board[0].length;
		for ( i = 0; i < m; i++ )
			for ( j = 0; j < n; j++ )

				board2[i][j] = checkGameOfLife( board, i, j );

		for ( i = 0; i < m; i++ )
			for ( j = 0; j < n; j++ )
				board[i][j] = board2[i][j];

	}

	public int checkGameOfLife( int[][] board, int i, int j )
	{
		int sum = 0;
		int m = board.length, n = board[0].length;

		for ( int row = -1; row <= 1; row++ )
			for ( int col = -1; col <= 1; col++ )
			{
				if ( row == 0 && col == 0 )
					continue;
				if ( i + row < 0 || i + row >= m || j + col < 0 || j + col >= n )
					continue;
				sum += board[i + row][j + col];
			}
		if ( board[i][j] == 0 )
			return ( sum == 3 ) ? 1 : 0;
		// if ( board[i][j] == 1 )
		return ( sum == 2 || sum == 3 ) ? 1 : 0;

	}

	public int mySqrt( int x )
	{
		double r = 1;
		while ( Math.abs( r * r - x ) > 1E-2 )
			r = 0.5 * ( r + x / r );
		return (int) r;
	}

	public boolean isPerfectSquare( int num )
	{
		double xn = 1, xnn = 1, xsq = 1;
		while ( Math.abs( num - xsq ) > 1e-3 )
		{
			xnn = 0.5 * ( xn + (double) num / xn );
			xn = xnn;
			xsq = xnn * xnn;
		}
		if ( xnn - (int) xnn < 1e-2 )
			return true;
		return false;
	}

	public void rotate( int[][] matrix )
	{

		int n = matrix.length - 1, i, j;
		for ( i = 0; i < matrix.length; i++ )
		{
			for ( j = 0; j < matrix[0].length; j++ )
				System.out.print( matrix[i][j] + " " );
			System.out.println();
		}

		// List<Integer> temp = new ArrayList<>();
		int tmp;
		for ( int layer = 0; layer < ( n + 1 ) / 2; layer++ )
		{
			// now rotate

			for ( j = layer; j < n - layer; j++ )
			{
				// temp = top
				// temp.add( matrix[layer][j] );
				tmp = matrix[layer][j];
				// top = left
				matrix[layer][j] = matrix[n - j][layer];
				// left = bottom
				matrix[n - j][layer] = matrix[n - layer][n - j];
				// bottom = right
				matrix[n - layer][n - j] = matrix[j][n - layer];
				// right = temp
				matrix[j][n - layer] = tmp;
			}
		}
		for ( i = 0; i < matrix.length; i++ )
		{
			for ( j = 0; j < matrix[0].length; j++ )
				System.out.print( matrix[i][j] + " " );
			System.out.println();
		}
	}

	void testListToArray()
	{
		List<Integer> list = new ArrayList<>();
		list.add( 1 );
		list.add( 2 );
		list.add( 3 );
		list.add( 4 );
		list.add( 5 );
		list.add( 6 );
		list.add( 7 );
		list.add( 8 );
		list.add( 9 );
		list.add( 10 );
		list.add( 11 );
		list.add( 12 );
		list.add( 13 );

		Integer[] strings = list.toArray( new Integer[0] );
		for ( Object s : strings )
			System.out.println( s.getClass() );
	}

	public int findDuplicate2( int[] nums )
	{
		for ( int i = 0; i < nums.length; i++ )
			for ( int j = i + 1; j < nums.length; j++ )
			{
				if ( nums[i] == nums[j] )
					return nums[i];
			}
		return 0;
	}

	public boolean isNumber( String s )
	{
		s = s.trim();
		if ( s.matches( "[+-]?(([\\d]+[.]?[\\d]*)|([\\d]*[.]?[\\d]+))(e[+-]?[\\d]+)?" ) )
			return true;
		return false;

	}

	public List<Integer> topKFrequent( int[] nums, int k )
	{
		List<Integer> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for ( int i = 0; i < nums.length; i++ )
		{
			if ( map.containsKey( nums[i] ) )
				map.put( nums[i], map.get( nums[i] ) + 1 );
			else
				map.put( nums[i], 1 );
		}
		Map smap = sortByValue( map );
		Map<Integer, Integer> tmap = new TreeMap<>( map );
		// Iterator<Integer> iterator = map.keySet().iterator();
		int count = 0;
		for ( Iterator iterator = smap.keySet().iterator(); iterator.hasNext(); )
		{
			if ( count < k )
			{
				list.add( (Integer) iterator.next() );
			}
			else
				break;
			count++;
		}
		return list;
	}

	public Map sortByValue( Map<Integer, Integer> map )
	{
		List list = new LinkedList<>( map.entrySet() );
		Collections.sort( list, new HashMapSort() );
		HashMap sortedMap = new LinkedHashMap<>();
		for ( Iterator it = list.iterator(); it.hasNext(); )
		{
			Map.Entry entry = (Entry) it.next();
			sortedMap.put( entry.getKey(), entry.getValue() );
		}
		return sortedMap;
	}

	public String reverseString( String s )
	{
		return new StringBuffer( s ).reverse().toString();
	}

	public int nthSuperUglyNumber( int n, int[] primes )
	{
		List<Integer> list = new ArrayList<>();
		list.add( 1 );
		int[] pos = new int[primes.length];
		for ( int i = 1; i < n; i++ )
		{
			int min = Integer.MAX_VALUE;
			for ( int j = 0; j < primes.length; j++ )
			{
				min = Math.min( min, primes[j] * list.get( pos[j] ) );
			}
			for ( int j = 0; j < primes.length; j++ )
			{
				if ( primes[j] * list.get( pos[j] ) == min )
				{
					pos[j]++;
				}
			}
			list.add( min );
		}
		return list.get( list.size() - 1 );
	}

	public int nthUglyNumber( int n )
	{
		ListNode current = new ListNode( 1 );
		ListNode n2 = current;
		ListNode n3 = current;
		ListNode n5 = current;

		int n2val = n2.val, n3val = n3.val, n5val = n5.val;
		for ( int i = 2; i <= n; i++ )
		{
			int min = Math.min( n2.val * 2, Math.min( n3.val * 3, n5.val * 5 ) );
			current.next = new ListNode( min );
			current = current.next;
			n2 = n2.val * 2 == min ? n2.next : n2;
			n3 = n3.val * 3 == min ? n3.next : n3;
			n5 = n5.val * 5 == min ? n5.next : n5;
			n2val = n2.val;
			n3val = n3.val;
			n5val = n5.val;
		}
		System.out.println( Arrays.toString( n2.toarray() ) );
		System.out.println( Arrays.toString( n3.toarray() ) );
		System.out.println( Arrays.toString( n5.toarray() ) );

		return current.val;
	}

	/*
		int[] divisor = { 2, 3, 5 };
		List<Integer> count = new ArrayList<>();
		List<List<Integer>> prod = new ArrayList<>();
		for ( int i = 0; i < divisor.length; i++ )
		{
			List<Integer> div = new ArrayList<>();
			div.add( divisor[i] );
			prod.add( div );
		}
		int min = divisor[0];
		for ( int i = 2; i < n; i++ )
		{
			for ( int k : divisor )
				min = Math.min( min, min * k );
			for ( int k : divisor )
			{
				if ( )
			}
		}
	*/

	public int Lisa_Workbook() throws FileNotFoundException
	{
		File file = new File( "C:\\Users\\Rutter\\Desktop\\java\\HackerRank\\src\\t5.txt" );
		File output = new File( "C:\\Users\\Rutter\\Desktop\\java\\HackerRank\\src\\t5output.txt" );
		Scanner in = new Scanner( file );
		int n = in.nextInt();
		int k = in.nextInt();
		int[] t = new int[n];
		int pages = 0, special = 0;
		for ( int i = 0; i < n; i++ )
		{
			t[i] = in.nextInt();
			int numpages = t[i] / k == 0 ? 1 : t[i] % k == 0 ? 0 : 1 + t[i] / k;
			System.out.print( i + " numpages: " + numpages + "\n" );
			// number of pages
			for ( int j = 1; j <= numpages; j++ )
			{
				int max = j == numpages ? t[i] : j * k;
				if ( pages + j <= max && pages + j > ( j - 1 ) * k )
					special++;
			}
			pages += numpages;
		}

		return special;

	}

	public int hIndex( int[] citations )
	{
		int len = citations.length;
		Arrays.sort( citations );
		int pos = 0;
		for ( int i = len - 1; i >= 0; i-- )
		{
			if ( citations[i] < len - i )
			{
				pos = i + 1;
				break;
			}
		}
		return citations[pos];
	}

	public TreeNode sortedListToBST( ListNode head )
	{
		ListNode tail = head;
		while ( tail != null )
			tail = tail.next;
		return listToTree( head, tail );
	}

	public TreeNode listToTree( ListNode head, ListNode tail )
	{
		if ( head == tail )
			return null;
		ListNode slow = head, fast = head;
		while ( fast != tail && fast.next != tail )
		{
			fast = fast.next.next;
			slow = slow.next;
		}
		TreeNode root = new TreeNode( slow.val );
		root.left = listToTree( head, slow );
		root.right = listToTree( slow.next, tail );
		return root;
	}

	public void Flatten_Nested_List_Iterator()
	{

		NestedInteger d = new NestedIntegerImplement( 10 );
		List<Integer> list = new ArrayList<>();

		NestedInteger nestedList = new NestedIntegerImplement( list );
		List<NestedInteger> inputList = new ArrayList<>();
		inputList.add( nestedList );
		// inputList.add( d );
		NestedIterator n = new NestedIterator( inputList );

		while ( n.hasNext() )
			System.out.println( n.next() );
	}

	public void intBaseCheck( int n )
	{
		for ( int i = 1; i < 17; i++ )
		{
			System.out.printf( "number %d represented in %d base: is %s\n", n, i, Integer.toString( n, i ) );
		}
	}

	public void kthCompare()
	{
		long tstart, tend;
		tstart = System.currentTimeMillis();

		Set<Integer> rnd = new HashSet<>();
		Random n = new Random();
		for ( int i = 0; i < 100000; i++ )
			rnd.add( n.nextInt() );
		tend = System.currentTimeMillis();
		System.out.println( "Finished randoming all numebrs and loaded to set" + ( tend - tstart ) / 1000.0 );
		tstart = System.currentTimeMillis();
		int[] b = new int[rnd.size()];
		int[] a_qs = new int[rnd.size()];
		int[] a_ds = new int[rnd.size()];
		Iterator<Integer> i = rnd.iterator();
		int k = 0;
		while ( i.hasNext() )
		{
			int m = i.next();
			b[k] = m;
			a_ds[k] = m;
			a_qs[k] = m;
			k++;
		}
		int[] a = b;
		// int[] a = { 11, 2, 3, 4, 5, 1, 6, 7, 8, 9, 10 };
		tend = System.currentTimeMillis();
		System.out.println( "Finished loading in all numebrs to array" + ( tend - tstart ) / 1000.0 );

		Solution s = new Solution();

		// s.partition( a, 0, a.length - 1 );
		// System.out.println( Arrays.toString( a ) );

		tstart = System.currentTimeMillis();
		System.out.println( s.findKthLargest( a_qs, 2 ) );
		tend = System.currentTimeMillis();
		System.out.println( "finding kth largest using quick select:" + ( tend - tstart ) / 1000.0 );

		tstart = System.currentTimeMillis();
		System.out.println( s.findKthLargestMedian( a_ds, 2 ) );
		tend = System.currentTimeMillis();
		System.out.println( "finding kth largest using deterministic select:" + ( tend - tstart ) / 1000.0 );

		tstart = System.currentTimeMillis();
		Arrays.sort( a );
		System.out.println( a[a.length - 2] );
		tend = System.currentTimeMillis();
		System.out.println( "finding kth largest using sorting whole array:" + ( tend - tstart ) / 1000.0 );
	}

	public int findKthLargest( int[] nums, int k )
	{
		return quickSelect( nums, 0, nums.length - 1, nums.length - k );
	}

	public int findKthLargestMedian( int[] nums, int k )
	{
		return quickSelectMedian( nums, 0, nums.length - 1, nums.length - k );
	}

	int quickSelectMedian( int[] nums, int left, int right, int k )
	{
		int index = partitionMedian( nums, left, right );
		if ( index == k )
			return nums[index];
		if ( index > k )
			return quickSelectMedian( nums, left, index - 1, k );
		return quickSelectMedian( nums, index + 1, right, k );

	}

	int partitionMedian( int[] n, int start, int end )
	{
		if ( start == end )
			return start;
		int index = deterministicMedian( n, start, end );
		int tmp = 0;
		// swap index to end
		tmp = n[end];
		n[end] = n[index];
		n[index] = tmp;
		// storeIndex will be the item that's larger than pivot
		int storeIndex = start;
		for ( int i = start; i < end; i++ )
		{

			if ( n[i] < n[end] )
			{
				// swap smaller item to front
				tmp = n[storeIndex];
				n[storeIndex] = n[i];
				n[i] = tmp;
				storeIndex++;
			}
		}
		tmp = n[end];
		n[end] = n[storeIndex];
		n[storeIndex] = tmp;
		return storeIndex;
	}

	int deterministicMedian( int[] nums, int start, int end )
	{
		if ( end - start < 6 )
		{
			Arrays.sort( nums, start, end );
			if ( start == nums.length - 1 )
				return start;
			if ( start == nums.length - 2 )
				return start;
			if ( start == nums.length - 3 )
				return start + 1;
			return start + 2; // position of median
		}

		for ( int i = 0; i < end / 5 + 1; i++ )
		{
			int tleft = i * 5, tright = Math.min( tleft + 5, nums.length );
			int median = deterministicMedian( nums, tleft, tright );
			int tmp = nums[i];
			nums[i] = nums[median];
			nums[median] = tmp;
		}

		return deterministicMedian( nums, 0, end / 5 );

	}

	int quickSelect( int[] nums, int left, int right, int k )
	{
		int index = partition( nums, left, right );
		if ( index == k )
			return nums[index];
		if ( index > k )
			return quickSelect( nums, left, index - 1, k );
		return quickSelect( nums, index + 1, right, k );

	}

	int medianOfMedian( int[] nums, int left, int right )
	{
		if ( right - left < 6 )
		{
			Arrays.sort( nums, left, right );
			return left + 2;
		}

		for ( int i = 0; i < right / 5; i++ )
		{
			int tleft = 5 * i, tright = tleft + 5;
			int med = medianOfMedian( nums, tleft, tright );
			int median = nums[med];
			nums[med] = nums[i];
			nums[i] = median;
		}

		return medianOfMedian( nums, 0, right / 5 );
	}

	public TreeNode sortedArrayToBST( int[] nums )
	{
		List<Integer> list = new ArrayList<>();
		for ( int k : nums )
			list.add( k );
		TreeNode root = null;
		return arrayToTree( nums, 0, nums.length - 1 );

	}

	public TreeNode arrayToTree( int[] nums, int left, int right )
	{
		TreeNode root;
		// base case
		if ( right - left < 0 )
			return null;
		if ( right - left < 1 )
		{
			root = new TreeNode( nums[left] );
			return root;
		}
		int mid = left + ( right - left ) / 2;
		root = new TreeNode( nums[mid] );
		root.left = arrayToTree( nums, left, mid - 1 );
		root.right = arrayToTree( nums, mid + 1, right );
		return root;
	}

	public TreeNode listToTree( TreeNode root, List<Integer> sublist )
	{
		// base case
		if ( sublist.size() < 1 )
			return null;
		if ( sublist.size() < 2 )
		{
			root = new TreeNode( sublist.get( 0 ) );
			return root;
		}
		int left = 0, right = sublist.size(), mid = sublist.size() / 2;
		root = new TreeNode( sublist.get( mid ) );
		root.left = listToTree( root.left, sublist.subList( 0, mid ) );
		root.right = listToTree( root.right, sublist.subList( mid + 1, right ) );
		return root;
	}

	public void bigintegerfactorial( BigInteger n )
	{
		BigInteger k = n;
		while ( !k.equals( BigInteger.ONE ) )
		{
			k = k.subtract( BigInteger.ONE );
			n = n.multiply( k );
		}
		System.out.println( n.toString() );
	}

	public void recoverTree( TreeNode root )
	{
		inOrderList = new ArrayList<>();
		inOrderTree( root );
		int tmp;
		if ( inOrderList.size() < 3 )
		{
			tmp = inOrderList.get( 0 ).val;
			inOrderList.get( 0 ).val = inOrderList.get( 1 ).val;
			inOrderList.get( 1 ).val = tmp;
			return;
		}
		int pos1 = 0, pos2 = 0;
		for ( int i = 0; i < inOrderList.size() - 1; i++ )
		{
			if ( inOrderList.get( i ).val > inOrderList.get( i + 1 ).val )
			{
				pos1 = i;
				break;
			}
		}
		for ( int i = inOrderList.size() - 1; i > 0; i-- )

			if ( inOrderList.get( i ).val < inOrderList.get( i - 1 ).val )
			{
				pos2 = i;
				break;

			}
		if ( pos2 == 0 )
			pos2 = inOrderList.size() - 1;
		tmp = inOrderList.get( pos1 ).val;
		inOrderList.get( pos1 ).val = inOrderList.get( pos2 ).val;
		inOrderList.get( pos2 ).val = tmp;
		System.out.printf( "\npos1: %d, pos2: %d\n", pos1, pos2 );
		return;

	}

	public TreeNode buildTreepost( int[] inorder, int[] postorder )
	{
		List<Integer> inOrderList = new ArrayList<>();
		_buildTree_pos = postorder.length - 1;
		_buildTree_postorder = new int[0];
		_buildTree_postorder = postorder;
		TreeNode root = new TreeNode( postorder[0] ), roottmp = root, tmp;

		for ( int k : inorder )
			inOrderList.add( k );

		return constructTreePost( inOrderList );

	}

	public TreeNode constructTreePost( List<Integer> sublist )
	{
		if ( _buildTree_pos < 0 || sublist.size() < 1 )
			return null;
		int val = _buildTree_postorder[_buildTree_pos--];
		if ( sublist.size() == 1 )
		{
			TreeNode tree = new TreeNode( sublist.get( 0 ) );
			return tree;
		}
		int left = 0, right = sublist.size();

		int ipos = sublist.indexOf( val );
		TreeNode root = new TreeNode( val );
		if ( ipos != right )
			root.right = constructTreePost( sublist.subList( ipos + 1, right ) );
		if ( left != ipos )
			root.left = constructTreePost( sublist.subList( left, ipos ) );

		return root;
	}

	public TreeNode constructTreePre( List<Integer> sublist )
	{
		if ( _buildTree_pos >= _buildTree_preorder.length || sublist.size() < 1 )
			return null;
		int val = _buildTree_preorder[_buildTree_pos++];
		if ( sublist.size() == 1 )
		{
			TreeNode tree = new TreeNode( sublist.get( 0 ) );
			return tree;
		}
		int left = 0, right = sublist.size();

		int ipos = sublist.indexOf( val );
		TreeNode root = new TreeNode( val );
		if ( left != ipos )
			root.left = constructTreePre( sublist.subList( left, ipos ) );
		if ( ipos != right )
			root.right = constructTreePre( sublist.subList( ipos + 1, right ) );

		return root;
	}

	public List<Integer> rightSideView( TreeNode root )
	{
		Queue<TreeNode> queue = new LinkedList<>();
		Queue<TreeNode> tmpqueue = new LinkedList<>();
		List<Integer> list = new ArrayList<>();
		if ( root == null )
			return list;
		Iterator it = list.iterator();
		TreeNode tmp;
		queue.add( root );
		list.add( root.val );
		int level = 0;
		while ( !queue.isEmpty() )
		{
			level++;
			tmpqueue.clear();
			while ( !queue.isEmpty() )
			{
				tmp = queue.poll();
				if ( list.size() == level )
				{
					if ( tmp.right != null )
					{
						list.add( tmp.right.val );
					}
					else if ( tmp.left != null )
					{
						list.add( tmp.left.val );
					}

				}
				if ( tmp.right != null )
					tmpqueue.add( tmp.right );
				if ( tmp.left != null )
					tmpqueue.add( tmp.left );

			}
			queue.addAll( tmpqueue );
		}
		return list;
	}

	public int findMin( int[] nums )
	{
		int left = 0, right = nums.length - 1, mid = ( left + right ) / 2;
		if ( nums.length < 2 )
			return nums[0];
		if ( nums[left] < nums[right] )
			return nums[0];
		while ( nums[left] > nums[right] && right - left > 1 )
		{
			if ( nums[mid] > nums[left] )
				left = mid;
			if ( nums[right] > nums[mid] )
				right = mid;
			mid = ( left + right ) / 2;
		}
		System.out.println( mid );
		int step = ( nums[mid] > nums[mid + 1] ) ? 1 : 0;
		return nums[mid + step];
	}

	public void testStr()
	{
		String s;
		Stack<Integer> st = new Stack<>();
		st = null;
		for ( int i = 0; i < 10; i++ )
			s = new String( "newstring" );
		return;
	}

	public int[] singleNumbernew( int[] nums )
	{
		int[] returnmatrix = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for ( int i = 0; i < nums.length; i++ )
		{
			if ( map.containsKey( nums[i] ) )
				map.put( nums[i], map.get( nums[i] ) + 1 );
			else
				map.put( nums[i], 1 );
		}
		Set<Integer> keys = map.keySet();
		boolean flag = false;
		for ( Integer i : keys )
			if ( map.get( i ) == 1 )
				if ( !flag )
				{
					flag = true;
					returnmatrix[0] = i;
				}
				else
					returnmatrix[1] = i;
		return returnmatrix;

	}

	public int kthSmallest( TreeNode root, int k )
	{
		return 0;
	}

	public int maxProduct( String[] words )
	{
		String[] nodupWords = new String[words.length];
		int[] res = new int[words.length];
		int max = 0;
		for ( int i = 0; i < words.length; i++ )
		{
			// to sorted character array
			Character[] t = words[i].chars().mapToObj( c -> (char) c ).sorted().toArray( Character[]::new );
			List<Character> tmp = new ArrayList<>( new LinkedHashSet<>( Arrays.asList( t ) ) );
			String nodupstr = "";
			for ( Character ctmp : tmp )
				nodupstr += ctmp.toString();
			nodupWords[i] = nodupstr;
			System.out.println( nodupWords[i] );
		}

		for ( int i = 0; i < words.length - 1; i++ )
		{
			for ( int j = i + 1; j < words.length; j++ )
			{
				if ( containsString( nodupWords[i], nodupWords[j] ) )
					res[i] = Math.max( res[i], words[i].length() * words[j].length() );
			}
			max = Math.max( max, res[i] );
		}

		return max;
	}

	public boolean containsString( String s1, String s2 )
	{
		char[] c = s1.toCharArray();
		for ( int i = 0; i < c.length; i++ )
			if ( s2.contains( String.valueOf( c[i] ) ) )
				return false;
		return true;

	}

	public int countDigitOne3( int n )
	{
		StringBuilder sbuilder = new StringBuilder();
		String str = "";
		for ( int i = 1; i <= n; i++ )
			sbuilder.append( i );
		// str += String.valueOf( i );
		// System.out.println( str );
		str = sbuilder.toString();
		str = str.replaceAll( "2|3|4|5|6|7|8|9|0", "" );
		// System.out.println( str );
		return str.length();
	}

	public int[] countBits( int num )
	{
		int[] ret = new int[num * 2];
		int k = 0;
		while ( Math.pow( 2, k ) <= num )
		{
			for ( int i = (int) Math.pow( 2, k ); i < Math.pow( 2, k + 1 ); i++ )
			{
				ret[i] = ret[(int) ( i - Math.pow( 2, k ) )] + 1;
			}
			k++;
		}

		return Arrays.copyOfRange( ret, 0, num + 1 );
	}

	public TreeNode binaryLowestCommonAncestor( TreeNode root, TreeNode p, TreeNode q )
	{
		if ( root == p || root == q )
			return root;
		if ( root.val > p.val && root.val < q.val )
			return root;
		if ( root.val < p.val && root.val > q.val )
			return root;
		if ( root.val < p.val && root.val < q.val )
			return binaryLowestCommonAncestor( root.right, p, q );
		if ( root.val > p.val && root.val > q.val )
			return binaryLowestCommonAncestor( root.left, p, q );
		return root;
	}

	public TreeNode lowestCommonAncestor( TreeNode root, TreeNode p, TreeNode q )
	{
		if ( p == root || q == root || p == null || q == null || root == null )
			return root;
		preOrderList = new ArrayList<>();
		inOrderList = new ArrayList<>();

		preOrderTree( root );
		inOrderTree( root );

		System.out.println( "preOrder: " );
		for ( int i = 0; i < preOrderList.size(); i++ )
			System.out.print( preOrderList.get( i ).val + " " );
		System.out.println( "\ninOrder: " );
		for ( int i = 0; i < inOrderList.size(); i++ )
			System.out.print( inOrderList.get( i ).val + " " );

		List<TreeNode> pList = findRoots( root, p );
		System.out.println( "\npList:" );
		for ( int i = 0; i < pList.size(); i++ )
			System.out.print( pList.get( i ).val + " " );
		List<TreeNode> qList = findRoots( root, q );
		System.out.println( "\nqList:" );
		for ( int i = 0; i < qList.size(); i++ )
			System.out.print( qList.get( i ).val + " " );
		for ( int i = 0; i < Math.min( pList.size(), qList.size() ); i++ )
			if ( pList.get( i ) != qList.get( i ) )
				return pList.get( i - 1 );
		return pList.get( pList.size() - 1 );
	}

	public List<TreeNode> findRoots( TreeNode root, TreeNode p )
	{
		List<TreeNode> retRoots = new ArrayList<>(), subList;
		// retRoots.add( preOrderList.get( 0 ) );
		boolean[] mark = new boolean[preOrderList.size()];
		int pPos = preOrderList.indexOf( p ), iPos = inOrderList.indexOf( p ), left = 0, right = inOrderList.size(), rootPos = 0;
		// special case
		if ( iPos == 0 )
			return preOrderList.subList( 0, pPos + 1 );
		for ( int i = 0; i <= pPos; i++ )
		{
			if ( mark[i] )
				continue;
			// if ( inOrderList.subList( left, right ).contains( preOrderList.get( i ) ) )
			rootPos = inOrderList.indexOf( preOrderList.get( i ) ); // subList( left, right ).
			retRoots.add( preOrderList.get( i ) );
			if ( rootPos == iPos )
				break;
			if ( rootPos > iPos )
				right = rootPos;
			else // rootPos < iPos, skip all items on left tree
			{
				// i = preOrderList.indexOf( inOrderList.get( rootPos == 0 ? 0 : rootPos - 1 ) );
				int k = rootPos;
				while ( k >= 0 )
				{
					mark[preOrderList.indexOf( inOrderList.get( k-- ) )] = true;
				}
				left = rootPos;
			}

		}
		return retRoots;
	}

	public void preOrderTree( TreeNode root )
	{
		if ( root == null )
			return;
		preOrderList.add( root );

		preOrderTree( root.left );

		preOrderTree( root.right );
	}

	public void inOrderTree( TreeNode root )
	{
		if ( root == null )
			return;
		inOrderTree( root.left );
		inOrderList.add( root );
		inOrderTree( root.right );
	}

	public void postOrderTree( TreeNode root )
	{
		if ( root == null )
			return;
		postOrderTree( root.left );
		postOrderTree( root.right );
		postOrderList.add( root );
	}

	public List<List<TreeNode>> preOrderLCA( TreeNode root, TreeNode p )
	{
		Stack<TreeNode> stack = new Stack<>();
		List<TreeNode> list = new ArrayList<>();
		TreeNode tmp = root;
		stack.push( root );

		while ( !stack.isEmpty() && tmp != p )
		{
			tmp = stack.pop();

		}

		List<List<TreeNode>> ret = new ArrayList<List<TreeNode>>();
		return ret;

	}

	public List<TreeNode> levelOrderLCA( TreeNode root, TreeNode p )
	{
		Queue<TreeNode> queue = new LinkedList<>();
		List<TreeNode> btree = new ArrayList<>(), retTree = new ArrayList<>();
		queue.add( root );
		while ( !queue.isEmpty() )
		{
			TreeNode tmp = queue.poll();
			btree.add( tmp );
			if ( tmp == p )
				break;
			if ( tmp.left != null )
				queue.add( tmp.left );
			if ( tmp.right != null )
				queue.add( tmp.right );
		}
		int i = btree.size() - 1;
		Iterator<TreeNode> itor = btree.iterator();
		System.out.println();
		while ( itor.hasNext() )
			System.out.print( itor.next().val + "-" );
		while ( i > 0 )
		{
			retTree.add( 0, btree.get( i ) );
			i = ( i - 1 ) / 2;
		}
		retTree.add( 0, btree.get( 0 ) );
		return retTree;
	}

	public ListNode oddEvenList( ListNode head )
	{
		if ( head == null || head.next == null )
			return head;
		int n = 0;
		ListNode odd = head, even = head.next, evenhead = even;
		while ( odd != null && even != null && odd.next != null && even.next != null )
		{
			// odd.print();
			// even.print();
			odd.next = even.next;
			odd = odd.next;
			if ( odd != null )
			{
				even.next = odd.next;
				even = even.next;
			}
		}
		odd.next = evenhead;

		return head;
	}

	public int hammingWeight( int n )
	{
		if ( n == 0 )
			return 0;
		String s = Integer.toBinaryString( n );
		System.out.println( s );
		s = s.replace( "0", "" );
		System.out.println( s );
		return s.length();

		/*
		if ( n == 0 ) return 0;
		int count = 0;
		for(int i = 1; i <= 32; i++)
		{
			System.out.println(n & (1<<i));
			count += (n & (1<<i))>0?1:0;
		}
		return count;
		*/
	}

	public String simplifyPath( String path )
	{
		char[] cpath = path.toCharArray();
		// last item is '/'
		int start = 0, len = 0;
		for ( int i = cpath.length - 2; i >= 0; i-- )
		{
			if ( cpath[i] == '/' )
			{
				start = i;
				len = cpath.length - 1 - start;
			}
		}
		return path.substring( start, start + len );

	}

	public boolean isSelfCrossing( int[] x )
	{
		Set<String> pv = new HashSet<>();
		int px = 0, py = 0;
		String point = String.valueOf( px ) + "," + String.valueOf( py );
		pv.add( point );

		for ( int i = 0; i < x.length; i++ )
		{
			int k = i % 4;

			switch ( k )
			{
			case 0:
				for ( int j = py + 1; j <= py + x[i]; j++ )
				{
					point = new String( String.valueOf( px ) + "," + String.valueOf( j ) );
					if ( pv.contains( point ) )
						return false;
					pv.add( point );
				}
				py = Integer.parseInt( point.split( "," )[1] );
				break;
			case 1:
				for ( int j = px - 1; j >= px - x[i]; j-- )
				{
					point = new String( String.valueOf( j ) + "," + String.valueOf( py ) );
					if ( pv.contains( point ) )
						return false;
					pv.add( point );

				}
				px = Integer.parseInt( point.split( "," )[0] );
				break;
			case 2:
				for ( int j = py - 1; j >= py - x[i]; j-- )
				{
					point = new String( String.valueOf( px ) + "," + String.valueOf( j ) );
					if ( pv.contains( point ) )
						return false;
					pv.add( point );
				}
				py = Integer.parseInt( point.split( "," )[1] );
				break;
			case 3:
				for ( int j = px + 1; j <= px + x[i]; j++ )
				{
					point = new String( String.valueOf( j ) + "," + String.valueOf( py ) );
					if ( pv.contains( point ) )
						return false;
					pv.add( point );

				}
				px = Integer.parseInt( point.split( "," )[0] );
				break;
			}
			System.out.println( Arrays.toString( pv.toArray() ) );

		}
		return true;
	}

	public int bulbSwitch( int n )
	{
		if ( n < 1 )
			return 0;
		if ( n < 3 )
			return 1;
		int count = 0;
		for ( int i = 1; i <= n; i++ )
			count += countFactor( i );
		return count;

	}

	public int countFactor( int n )
	{
		if ( n == 1 )
			return 1;
		if ( n <= 3 )
			return 0;
		int count = 2;
		for ( int i = 2; i <= Math.sqrt( n ); i++ )
			if ( n % i == 0 )
				if ( i != n / i )
					count += 2;
				else
					count += 1;
		return ( count % 2 == 0 ) ? 0 : 1;
	}

	public List<List<Integer>> permuteUnique( int[] nums )
	{
		List<Integer> insertListOld = new ArrayList<Integer>();
		List<Integer> insertListNew = new ArrayList<Integer>();
		List<Integer> tempList;
		Set<List<Integer>> set = new HashSet<>();
		List<List<Integer>> returnList = new ArrayList<List<Integer>>();
		// boolean [] check = new boolean[nums.length];

		for ( int i = 0; i < nums.length; i++ )
			insertListOld.add( i );
		set.add( indexToValues( nums, insertListOld ) );

		int index = nums.length - 1;
		while ( index >= 0 )
		{
			while ( index < nums.length - 1 && insertListOld.get( index ) < nums.length - 1 )
			{
				insertListNew = new ArrayList<Integer>( insertListOld );
				insertListOld = new ArrayList<Integer>();
				for ( int i = 0; i <= index; i++ )
					insertListOld.add( insertListNew.get( i ) );
				// insertListOld.get(index);
				tempList = insertListOld;
				// update digit
				int k = 1;
				while ( tempList.contains( insertListOld.get( index ) + k ) && insertListOld.get( index ) + k < nums.length )
					k++;
				if ( insertListOld.get( index ) + k >= nums.length )
					break;
				insertListOld.set( index, insertListOld.get( index ) + k );
				for ( int i = 0; i < nums.length; i++ )
					// fill the new list, with the updated digit
					if ( !insertListOld.contains( i ) )
					{
						insertListOld.add( i );
						index = nums.length - 1;
					}
				set.add( indexToValues( nums, insertListOld ) );
				// if ( index == 0 )
				// index++;
				index++;
			}
			index--;
		}
		Iterator iterator = set.iterator();
		while ( iterator.hasNext() )
			returnList.add( (List<Integer>) iterator.next() );
		return returnList;
	}

	int coinChange( int[] coins, int amount )
	{
		// bfs to solve this problem. Once reached 0, return. This is the
		// least amount of coins to solve this problem
		// if 0 is never reached, then return -1
		if ( amount == 0 )
			return 0;
		Queue<Integer> q = new LinkedList<>();
		// Integer[] element, temp;
		Integer element, temp;
		int numberOfCoins = 1, size;
		for ( int c : coins )
		{
			element = amount - c;
			if ( element == 0 )
				return numberOfCoins;
			q.add( element );
		}

		Set<Integer> set = new HashSet<>();
		Iterator<Integer> iter;
		while ( !q.isEmpty() )
		{
			numberOfCoins++;
			size = q.size();
			set.clear();
			for ( int i = 0; i < size; i++ )
			{
				temp = q.poll();
				for ( int c : coins )
				{
					element = temp;

					element -= c;
					if ( element > 0 )
						set.add( element );
					if ( element == 0 )
						return numberOfCoins;

				}
			}
			iter = set.iterator();
			while ( iter.hasNext() )
				q.add( iter.next() );
		}

		return -1;
	}

	public void s8()
	{
		char x = 'A';
		int i = 0;
		System.out.print( true ? x : 0 );
		System.out.print( false ? i : x );
	}

	public int bfsQue( String beginWord, String endWord, Set<String> wordList )
	{
		Queue<String> queue = new LinkedList<>();
		int level = 0;
		String layer = "layer";
		queue.add( beginWord );
		queue.add( layer );
		while ( !queue.isEmpty() )
		{
			String currentWord = queue.poll();
			if ( currentWord.equalsIgnoreCase( layer ) )
			{
				if ( !queue.isEmpty() )
				{
					level++;
					queue.add( layer );
					continue;
				}
			}
			if ( currentWord.equalsIgnoreCase( endWord ) )
				return level;
			char[] cword = currentWord.toCharArray();
			char t;
			for ( int i = 0; i < cword.length; i++ )
			{
				t = cword[i];
				for ( int j = 0; j < 26; j++ )
				{
					cword[i] = (char) ( 'a' + j );
					String nword = String.valueOf( cword );
					if ( wordList.contains( nword ) )
					{
						queue.add( nword );
						wordList.remove( nword );
					}
				}
				cword[i] = t;
			}

		}

		return 0;
	}

	public int bfsLadder( String node, List<String> nodes, List<String> visited )
	{
		List<String> neighbours = new ArrayList<>();
		for ( String s : nodes )
			if ( diffByOne( node, s ) && !visited.contains( s ) )
				neighbours.add( s );
		visited.add( node );
		// List<String> newVisit = new ArrayList<>( visited );
		int distance = 9999;

		if ( diffByOne( node, _endWord ) )
		{
			System.out.print( node + " " );
			return 1;// visited.size();
		}

		// if ( neighbours.size() != 0 )
		for ( String neighbourNodes : neighbours )
		{
			distance = Math.min( distance, 1 + bfsLadder( neighbourNodes, nodes, visited ) );
		}
		return distance;

	}

	public int dfsStack( String node, List<String> nodes, String endWord )
	{
		List<Integer> distance = new ArrayList<>();
		Stack<String> stack = new Stack<>();
		String currentNode;
		List<String> visited = new ArrayList<>();
		visited.add( node );
		List<String> path = new ArrayList<>();
		// path.add( node );
		List<String> neighbors = new ArrayList<>();
		// add neighbor nodes
		for ( String s : nodes )
			if ( diffByOne( node, s ) && !visited.contains( s ) && !stack.contains( s ) )
				stack.add( s );
		while ( !stack.isEmpty() )
		{
			neighbors.clear();
			currentNode = stack.pop();
			visited.add( currentNode );
			path.add( currentNode );
			if ( diffByOne( currentNode, endWord ) )
			{
				System.out.println( Arrays.toString( path.toArray() ) );
				distance.add( path.size() );
			}
			for ( String s : nodes )
				if ( diffByOne( currentNode, s ) && !visited.contains( s ) && !stack.contains( s ) )
				{
					neighbors.add( s );
					stack.add( s );
				}
			if ( neighbors.size() == 0 )
				path.remove( path.size() - 1 );
		}
		return distance.size();
	}

	public int dfsLadder( String node, List<String> nodes, List<String> visited )
	{
		List<String> neighbours = new ArrayList<>();
		for ( String s : nodes )
			if ( diffByOne( node, s ) && !visited.contains( s ) )
				neighbours.add( s );
		visited.add( node );
		// List<String> newVisit = new ArrayList<>( visited );
		int distance = 9999;

		if ( diffByOne( node, _endWord ) )
		{
			System.out.print( node + " " );
			return 1;// visited.size();
		}

		// if ( neighbours.size() != 0 )
		for ( String neighbourNodes : neighbours )
		{
			distance = Math.min( distance, 1 + dfsLadder( neighbourNodes, nodes, visited ) );
		}
		return distance;
	}

	/**
	 * finds all strings differ by 1 character from target in string set
	 * 
	 * @param str
	 * @param words
	 * @return
	 */
	public List<String> findNeighbour( String str, Set<String> words )
	{
		List<String> stringList = new ArrayList<>();
		for ( String s : words )
		{
			if ( diffByOne( str, s ) )
				stringList.add( s );
		}

		return stringList;
	}

	/**
	 * returns true if two string differ by only one character
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean diffByOne( String s1, String s2 )
	{
		char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
		// Arrays.sort( c1 );
		// Arrays.sort( c2 );
		int count = 0;
		for ( int i = 0; i < c1.length; i++ )
		{
			if ( c1[i] != c2[i] )
				count++;
		}
		return ( count <= 1 ) ? true : false;
	}

	public List<List<Integer>> permute( int[] nums )
	{
		List<Integer> insertListOld = new ArrayList<Integer>();
		List<Integer> insertListNew = new ArrayList<Integer>();
		List<Integer> tempList;
		List<List<Integer>> returnList = new ArrayList<List<Integer>>();
		// boolean [] check = new boolean[nums.length];

		for ( int i = 0; i < nums.length; i++ )
			insertListOld.add( i );
		returnList.add( indexToValues( nums, insertListOld ) );

		int index = nums.length - 1;
		while ( index >= 0 )
		{
			while ( index < nums.length - 1 && insertListOld.get( index ) < nums.length - 1 )
			{
				insertListNew = new ArrayList<Integer>( insertListOld );
				insertListOld = new ArrayList<Integer>();
				for ( int i = 0; i <= index; i++ )
					insertListOld.add( insertListNew.get( i ) );
				// insertListOld.get(index);
				tempList = insertListOld;
				// update digit
				int k = 1;
				while ( tempList.contains( insertListOld.get( index ) + k ) && insertListOld.get( index ) + k < nums.length )
					k++;
				if ( insertListOld.get( index ) + k >= nums.length )
					break;
				insertListOld.set( index, insertListOld.get( index ) + k );
				for ( int i = 0; i < nums.length; i++ )
					// fill the new list, with the updated digit
					if ( !insertListOld.contains( i ) )
					{
						insertListOld.add( i );
						index = nums.length - 1;
					}
				returnList.add( indexToValues( nums, insertListOld ) );
				// if ( index == 0 )
				// index++;
				index++;
			}
			index--;
		}
		return returnList;
	}

	static public int indexToValue( int[] nums, int pos )
	{
		return nums[pos];
	}

	public List<Integer> indexToValues( int[] nums, List<Integer> pos )
	{
		List<Integer> ret = new ArrayList<Integer>();
		for ( int i = 0; i < pos.size(); i++ )
			ret.add( indexToValue( nums, pos.get( i ) ) );
		return ret;
	}

	public List<List<Integer>> combine( int n, int k )
	{
		List<List<Integer>> returnList = new ArrayList<List<Integer>>();
		List<Integer> insertListOld = new ArrayList<Integer>();
		List<Integer> insertListNew = new ArrayList<Integer>();
		if ( k > n )
		{
			System.out.printf( "Combination - combinations\nArray length < input %d\n", k );
			return returnList;
		}
		int movIndex = 0;
		for ( int i = 0; i < k; i++ )
			// base case
			insertListOld.add( i + 1 );
		returnList.add( insertListOld );
		if ( k == 0 || k == 1 || k == n )
			return returnList;
		// move movIndex to last position
		movIndex = k - 1;
		while ( movIndex >= 0 )
		{
			// n - k + 1 for position 0, and each moved index increases value by 1
			while ( insertListOld.get( movIndex ) < n + 1 - k + 1 + movIndex )
			{
				// updates current list with position that was just updated
				insertListNew = new ArrayList<Integer>( insertListOld );
				insertListOld = insertListNew;
				insertListOld.set( movIndex, insertListOld.get( movIndex ) + 1 );

				// fills current insert list
				while ( movIndex < k - 1 )
				{
					insertListOld.set( movIndex + 1, insertListOld.get( movIndex ) + 1 );
					movIndex++;
				}
				while ( insertListOld.get( movIndex ) < n + 1 - k + 1 + movIndex )
				{
					returnList.add( insertListOld );
					insertListNew = new ArrayList<Integer>( insertListOld );
					insertListOld = insertListNew;
					insertListOld.set( movIndex, insertListOld.get( movIndex ) + 1 );

				}
				movIndex--;
			}
			movIndex--;
		}
		return returnList;

	}

	public int numSquares( int n )
	{
		int count = 0, n_temp = n;

		while ( n_temp != 0 )
		{
			System.out.println( n_temp );
			n_temp -= square( n_temp );
			count++;
		}

		return Math.min( factor( n ), count );
	}

	public int factor( int n )
	{
		int factor = 9999;
		for ( int i = 2; i <= square( n ); i++ )
		{
			if ( n % ( i * i ) == 0 )
				factor = Math.min( factor, n / ( i * i ) );
		}
		return factor;

	}

	public int square( int n )
	{
		if ( n <= 3 )
			return 1;
		int sq = (int) Math.sqrt( n );
		return sq * sq;
	}

	public String getHint( String secret, String guess )
	{

		char[] s = secret.toCharArray();
		char[] g = guess.toCharArray();
		int[] sn = new int[10];
		int[] gn = new int[10];
		boolean[] abool = new boolean[s.length];
		int a = 0, b = 0;
		for ( int i = 0; i < s.length; i++ )
		{
			if ( s[i] == g[i] )
			{
				a++;
				abool[i] = true;
			}
		}
		for ( int i = 0; i < s.length; i++ )
		{
			if ( !abool[i] )
			{
				sn[s[i] - '0']++;
				gn[g[i] - '0']++;
			}
		}
		for ( int i = 0; i < s.length; i++ )
		{
			if ( !abool[i] )
				b += Math.min( sn[i], gn[i] );
		}
		return String.valueOf( a ) + "A" + String.valueOf( b ) + "B";
	}

	public boolean searchMatrix( int[][] matrix, int target )
	{
		return false;
	}

	public ListNode reverseKGroup( ListNode head, int k )
	{
		if ( k < 2 )
			return head;
		if ( head == null || head.next == null )
			return head;
		ListNode t = new ListNode( 0 );
		ListNode tt = t;
		int[] a = new int[k];
		boolean hitend = false, exact = false;
		ListNode p = head;
		while ( p.next != null )
		{
			ListNode p_pos_before_k = p;
			for ( int i = 0; i < k; i++ )
			{

				a[i] = p.val;
				if ( p.next != null )
					p = p.next;

				else if ( p.next == null && i == k - 1 ) // exactly k steps with rest nodes
					exact = true;

				else if ( i != k - 1 || p.next != null ) // k steps more than nodes left
					hitend = true;
			}
			if ( !hitend ) // only inverts if
			{
				// invert node
				for ( int i = k - 1; i >= 0; i-- )
				{
					ListNode q = new ListNode( a[i] );
					tt.next = q;
					tt = tt.next;
				}
			}
			else
				// takes care of nodes not inverted
				tt.next = p_pos_before_k;

		}
		if ( !exact )
			tt.next = p;
		t.print();
		return t.next;
	}

	public List<String> letterCombinations( String digits )
	{
		List<String> ret = new ArrayList<String>();

		char[] dig = digits.toCharArray();
		if ( dig.length == 0 )
			return ret;
		String[] dig_S = new String[dig.length];
		for ( int i = 0; i < dig.length; i++ )
		{
			dig_S[i] = getPhoneLetter( dig[i] - '0' );
		}
		String[] r = { "" };
		for ( int i = 0; i < dig.length; i++ )
		{
			r = combine( r, dig_S[i] );
		}
		// System.out.println( Arrays.toString( r ) );
		for ( int i = 0; i < r.length; i++ )
			ret.add( r[i] );
		System.out.println( ret.toString() );
		return ret;
	}

	public String[] combine( String[] s1, String s2 )
	{
		if ( s2 == "" )
			return s1;
		char[] c2 = s2.toCharArray();
		String[] r = new String[s1.length * c2.length];
		int k = 0;
		for ( int i = 0; i < s1.length; i++ )
		{
			for ( int j = 0; j < c2.length; j++ )
			{
				r[k++] = s1[i] + String.valueOf( c2[j] );
			}
		}
		return r;

	}

	public String getPhoneLetter( int n )
	{
		switch ( n )
		{
		case 1:
			return " ";
		case 2:
			return "abc";
		case 3:
			return "def";
		case 4:
			return "ghi";
		case 5:
			return "jkl";
		case 6:
			return "mno";
		case 7:
			return "pqrs";
		case 8:
			return "tuv";
		case 9:
			return "wxyz";
		case 0:
			return "";
		default:
			break;
		}
		return null;

	}

	public String addBinary( String a, String b )
	{
		// Write your code here
		// a is always longer than b
		if ( a.length() < b.length() )
		{
			String t = a;
			a = b;
			b = t;
		}
		char[] ac = a.toCharArray();
		char[] bc = b.toCharArray();
		int carry = 0, digit, newcarry, i = a.length() - 1, j = b.length() - 1;
		String ns = "";

		while ( j >= 0 )
		{
			newcarry = ( ac[i] - '0' + bc[j] - '0' + carry ) / 2;
			digit = ( ac[i] - '0' + bc[j] - '0' + carry ) % 2;
			carry = newcarry;
			ns = String.valueOf( digit ) + ns;
			i--;
			j--;
		}

		// a > b and one more digit need to be taken care of
		while ( i >= 0 )
		{
			newcarry = ( ac[i] - '0' + carry ) / 2;
			digit = ( ac[i] - '0' + carry ) % 2;
			carry = newcarry;
			ns = String.valueOf( digit ) + ns;
			i--;
		}
		if ( carry > 0 )
		{
			ns = "1" + ns;
		}
		return ns;
	}

	public int[] plusOne( int[] digits )
	{
		// Write your code here

		int carry = 1, newcarry = 0;
		for ( int i = digits.length - 1; i >= 0; i-- )
		{
			newcarry = ( digits[i] + carry ) / 10;
			digits[i] = ( digits[i] + carry ) % 10;
			carry = newcarry;

		}
		if ( carry > 0 )
		// meaning one more digit
		{
			int[] newd = new int[digits.length + 1];
			newd[0] = 1;
			for ( int i = 1; i < digits.length + 1; i++ )

			{
				newd[i] = digits[i - 1];
			}
			return newd;
		}
		return digits;
	}

	public ListNode removeElements( ListNode head, int val )
	{
		// Write your code here
		ListNode n = new ListNode( 0 );
		if ( head == null )
			return n.next;
		ListNode tmp = n;
		if ( head.val != val && head.next == null )
		{
			return head;
		}
		while ( head.next != null )
		{
			if ( head.val != val )
			{
				ListNode t = new ListNode( head.val );
				tmp.next = t;
				tmp = tmp.next;
			}
			head = head.next;
		}
		if ( head.val != val )
		{
			ListNode t = new ListNode( head.val );
			tmp.next = t;
			tmp = tmp.next;
		}
		return n.next;

	}

	public void q60()
	{
		long startTime;
		long endTime;
		startTime = System.currentTimeMillis();
		int bound = Integer.MAX_VALUE;
		long cmin = 999999999;
		ArrayList<Integer> x = sieveE( 10000000 );
		System.out.println( "prime loaded" );
		System.out.println( "load size:" + x.size() );
		endTime = System.currentTimeMillis();
		System.out.println( "prime loaded time: " + ( endTime - startTime ) + "ms" );
		int s = x.size();
		for ( int i = 0; i < s; i++ )
		{
			if ( i % 1e4 == 0 )
				System.out.println( i );
			int pi = x.get( i );
			if ( pi * 5 > bound )
				break;
			for ( int j = i + 1; j < s; j++ )
			{
				int pj = x.get( j );
				if ( pi * 4 + pj > bound )
					break;
				if ( !isPrime( Long.valueOf( String.valueOf( pi ) + String.valueOf( pj ) ) )
						|| !isPrime( Long.valueOf( String.valueOf( pj ) + String.valueOf( pi ) ) ) )
				{
					continue;
				}
				for ( int k = j + 1; k < s; k++ )
				{
					int pk = x.get( k );
					if ( pi * 3 + pj + pk > bound )
						break;
					if ( !isPrime( Long.valueOf( String.valueOf( pi ) + String.valueOf( pk ) ) )
							|| !isPrime( Long.valueOf( String.valueOf( pk ) + String.valueOf( pi ) ) )
							|| !isPrime( Long.valueOf( String.valueOf( pj ) + String.valueOf( pk ) ) )
							|| !isPrime( Long.valueOf( String.valueOf( pk ) + String.valueOf( pj ) ) ) )
					{
						continue;
					}
					for ( int l = k + 1; l < s; l++ )
					{
						int pl = x.get( l );
						if ( pi * 2 + pj + pk + pl > bound )
							break;
						if ( !isPrime( Long.valueOf( String.valueOf( pi ) + String.valueOf( pl ) ) )
								|| !isPrime( Long.valueOf( String.valueOf( pl ) + String.valueOf( pi ) ) )
								|| !isPrime( Long.valueOf( String.valueOf( pj ) + String.valueOf( pl ) ) )
								|| !isPrime( Long.valueOf( String.valueOf( pl ) + String.valueOf( pj ) ) )
								|| !isPrime( Long.valueOf( String.valueOf( pk ) + String.valueOf( pl ) ) )
								|| !isPrime( Long.valueOf( String.valueOf( pl ) + String.valueOf( pk ) ) ) )
						{
							continue;
						}
						for ( int m = k + 1; m < s; m++ )
						{
							int pm = x.get( m );
							if ( pi + pj + pk + pl + pm > bound )
								break;
							if ( !isPrime( Long.valueOf( String.valueOf( pi ) + String.valueOf( pm ) ) )
									|| !isPrime( Long.valueOf( String.valueOf( pm ) + String.valueOf( pi ) ) )
									|| !isPrime( Long.valueOf( String.valueOf( pj ) + String.valueOf( pm ) ) )
									|| !isPrime( Long.valueOf( String.valueOf( pm ) + String.valueOf( pj ) ) )
									|| !isPrime( Long.valueOf( String.valueOf( pk ) + String.valueOf( pm ) ) )
									|| !isPrime( Long.valueOf( String.valueOf( pm ) + String.valueOf( pk ) ) )
									|| !isPrime( Long.valueOf( String.valueOf( pl ) + String.valueOf( pm ) ) )
									|| !isPrime( Long.valueOf( String.valueOf( pm ) + String.valueOf( pl ) ) ) )
							{
								continue;
							}

							if ( cmin > pi + pj + pk + pl + pm )
							{
								cmin = pi + pj + pk + pl + pm;
								System.out.print(
										"i = " + pi + "\tj = " + pj + "\tk = " + pk + "\tl = " + pl + "\tm=" + pm );
								System.out.print( "\tcmin = " + cmin + "\n" );
								endTime = System.currentTimeMillis();
								System.out.println( "running time: " + ( endTime - startTime ) + "ms" );
							}
						}
					}

				}
			}
		}
	}

	public void q357( int n )
	{
		long startTime;
		long endTime;
		startTime = System.currentTimeMillis();

		ArrayList<Integer> x = sieveE( 100000000 );
		System.out.println( "prime loaded" );
		System.out.println( "load size:" + x.size() );
		endTime = System.currentTimeMillis();
		System.out.println( "prime loaded time: " + ( endTime - startTime ) + "ms" );
		ArrayList<Integer> h = sieveE( 100 );
		int l = x.size();
		ArrayList<Integer> xnew = new ArrayList<Integer>();
		for ( int i = 0; i < l; i++ )
		{
			int xn = x.get( i ) - 1;
			if ( !isPrime( 2 + xn / 2 ) )
			{
				continue;
			}
			boolean f = true;
			for ( int j = 0; j < h.size() && f; j++ )
			{
				if ( xn % ( h.get( j ) * h.get( j ) ) == 0 )
					f = false;
				continue;
			}
			if ( !f )
				continue;
			xnew.add( xn );
		}
		System.out.println( "prime re loaded" );
		System.out.println( "re load size:" + xnew.size() );
		endTime = System.currentTimeMillis();
		System.out.println( "prime loaded time: " + ( endTime - startTime ) + "ms" );
		BigInteger sum = new BigInteger( "0" );
		for ( int i = 0; i < xnew.size(); i++ )
		{

			if ( factors( xnew.get( i ) ) )
			{
				sum = sum.add( new BigInteger( Integer.toString( xnew.get( i ) ) ) );
			}
		}
		System.out.println( "sum = " + sum.toString() );
		endTime = System.currentTimeMillis();

		System.out.println( "Total execution time: " + ( endTime - startTime ) + "ms" );

	}

	public boolean factors( int n )
	{
		for ( int x = 1; x < Math.sqrt( n ); x++ )
		{
			if ( n % x == 0 )
				if ( !isPrime( x + n / x ) )
					return false;
		}
		return true;
	}

	public ArrayList<Integer> sieveE( int n )
	{
		boolean[] A = new boolean[n];
		for ( int i = 0; i < n; i++ )
			A[i] = true;
		A[0] = A[1] = false;
		int c = 2;
		for ( int i = 2; i < Math.sqrt( n ) + 1; i++ )
		{
			if ( A[i] )
			{
				int j = i * i;
				while ( j < n )
				{
					A[j] = false;
					j += i;
					c += 1;
				}
			}
		}
		ArrayList<Integer> r = new ArrayList<Integer>();
		int k = 0;
		for ( int i = 0; i < n; i++ )
		{
			if ( A[i] )
			{
				r.add( i );
				k += 1;
			}
		}
		return r;
	}

	public List<Integer> getPrime( int n )
	{
		List<Integer> lst = new ArrayList<Integer>();
		for ( int i = 2; i <= n; i++ )
			if ( isPrime( i ) )
			{
				lst.add( i );
				System.out.print( i + "," );
			}
		return lst;
	}

	public int reverse( int x )
	{

		boolean flag = ( x < 0 ) ? true : false;
		int neg = ( flag ) ? -1 : 1;
		char[] c = Integer.toString( neg * x ).toCharArray();
		// System.out.println( Arrays.toString( c ) );
		char[] r = new char[c.length];
		for ( int i = 0; i < c.length; i++ )
			r[c.length - 1 - i] = c[i];
		Long ret = Long.parseLong( String.valueOf( r ) );
		// System.out.println( neg * Long.parseLong( String.valueOf( r ) ) );
		if ( x >= Integer.MAX_VALUE )
			return 0;
		if ( x <= Integer.MIN_VALUE )
			return 0;
		if ( ( neg * ret ) >= Math.pow( 2, 31 ) - 1 || neg * ret <= Math.pow( 2, 32 ) * -1 )
		{
			// System.out.println( "overflow" );
			return 0;

		}

		// System.out.println( Integer.parseInt( String.valueOf( r ) ) );
		return neg * (int) Long.parseLong( String.valueOf( r ) );
	}

	public int singleNumber( int nums[] )
	{
		int res = 0;
		int k = 0;
		while ( k < 32 )
		{
			int temp = 0;
			for ( int i = 0; i < nums.length; i++ )
			{
				temp += ( ( nums[i] >> k ) & 1 );
			}
			if ( temp % 3 != 0 )
			{
				res = res | ( 1 << k );
			}
			k++;
		}
		return res;
	}

	public void solve( char[][] board )
	{
		int row = board.length;
		if ( row == 0 )
			return;
		int col = board[0].length;
		if ( col == 0 )
			return;
		char x = 'X', o = 'O', v = 'V';
		int[][] check = new int[row][col];
		int i, j;
		for ( j = 0; j < col; j++ )
		{
			if ( board[0][j] == o )
				dfsStack( board, 0, j );
			if ( board[row - 1][j] == o )
				dfsStack( board, row - 1, j );
		}
		j = 0;
		for ( i = 0; i < row; i++ )
		{
			if ( board[i][0] == o )
				dfsStack( board, i, 0 );
			if ( board[i][col - 1] == o )
				dfsStack( board, i, col - 1 );
		}
		for ( i = 0; i < row; i++ )
		{
			for ( j = 0; j < col; j++ )
			{
				if ( board[i][j] == o )
					board[i][j] = x;
			}
		}
		for ( i = 0; i < row; i++ )
		{
			for ( j = 0; j < col; j++ )
			{
				if ( board[i][j] == v )
					board[i][j] = o;
			}
		}
	}

	/*
	 * breadth first search - idea: find all 'O' on the side (which definitely
	 * cannot be trapped), then convert that and all its contingent elements
	 * into 'V'. The rest 'O's will be trapped.
	 */

	public void dfsStack( char[][] a, int i, int j )
	{
		char o = 'O', v = 'V';
		int row = a.length;
		int col = a[0].length;

		Stack<int[]> stk = new Stack<int[]>();
		int[] tmp;
		int pi, pj;
		tmp = new int[2];
		tmp[0] = i;
		tmp[1] = j;
		stk.add( tmp );
		a[i][j] = v;
		while ( !stk.isEmpty() )
		{
			tmp = new int[2];
			tmp = stk.pop();
			pi = tmp[0];
			pj = tmp[1];
			a[pi][pj] = v;
			if ( pi != 0 && a[pi - 1][pj] == o )
			{
				a[pi - 1][pj] = v;
				tmp = new int[2];
				tmp[0] = pi - 1;
				tmp[1] = pj;
				stk.add( tmp );

			}
			if ( pi != row - 1 && a[pi + 1][pj] == o )
			{
				a[pi + 1][pj] = v;
				tmp = new int[2];
				tmp[0] = pi + 1;
				tmp[1] = pj;
				stk.add( tmp );

			}
			if ( pj != 0 && a[pi][pj - 1] == o )
			{
				a[pi][pj - 1] = v;
				tmp = new int[2];
				tmp[0] = pi;
				tmp[1] = pj - 1;
				stk.add( tmp );

			}
			if ( pj != col - 1 && a[pi][pj + 1] == o )
			{
				a[pi][pj + 1] = v;
				tmp = new int[2];
				tmp[0] = pi;
				tmp[1] = pj + 1;
				stk.add( tmp );
			}

		}
	}

	public void dfs( char[][] a, int i, int j )
	{
		char o = 'O', v = 'V';
		int row = a.length;
		int col = a[0].length;
		if ( i < 0 || j < 0 || i >= row || j >= col )
			return;
		if ( a[i][j] != o )
			return;
		if ( a[i][j] == o )
			a[i][j] = v;
		if ( i != 0 && a[i - 1][j] == o )
			dfs( a, i - 1, j );
		if ( i != row - 1 && a[i + 1][j] == o )
			dfs( a, i + 1, j );
		if ( j != 0 && a[i][j - 1] == o )
			dfs( a, i, j - 1 );
		if ( j != col - 1 && a[i][j + 1] == o )
			dfs( a, i, j + 1 );
	}

	public void bfs( char[][] a, int i, int j )
	{
		char o = 'O', v = 'V';
		int row = a.length;
		int col = a[0].length;
		// queue style?
		LinkedList<Integer> qi = new LinkedList<Integer>();
		LinkedList<Integer> qj = new LinkedList<Integer>();
		qi.add( i );
		qj.add( j );
		while ( qi.isEmpty() == false && qj.isEmpty() == false )
		{
			a[qi.getFirst()][qj.getFirst()] = v;
			if ( qi.getFirst() != 0 )
				if ( a[qi.getFirst() - 1][qj.getFirst()] == o )
				{
					qi.add( qi.getFirst() - 1 );
					qj.add( qj.getFirst() );

				}
			if ( qi.getFirst() != row - 1 )
				if ( a[qi.getFirst() + 1][qj.getFirst()] == o )
				{
					qi.add( qi.getFirst() + 1 );
					qj.add( qj.getFirst() );

				}
			if ( qj.getFirst() != 0 )
				if ( a[qi.getFirst()][qj.getFirst() - 1] == o )
				{
					qi.add( qi.getFirst() );
					qj.add( qj.getFirst() - 1 );

				}
			if ( qj.getFirst() != col - 1 )
				if ( a[qi.getFirst()][qj.getFirst() + 1] == o )
				{
					qi.add( qi.getFirst() );
					qj.add( qj.getFirst() + 1 );

				}
			qi.removeFirst();
			qj.removeFirst();
		}
	}

	public int numIslands( char[][] grid )
	{
		if ( grid == null )
			return 0;
		int sizex = grid.length;
		if ( sizex == 0 )
			return 0;
		int sizey = grid[0].length;
		int count = 0;
		int[] tmp = new int[2];
		int px, py;
		// int[][] check = new int[sizex][sizey];
		Queue<int[]> s = new LinkedList<int[]>();
		for ( int i = 0; i < sizex; i++ )
		{
			for ( int j = 0; j < sizey; j++ )
			{
				if ( grid[i][j] == '1' )
				// meaning it's an island
				{
					tmp = new int[2];
					tmp[0] = i;
					tmp[1] = j;
					grid[i][j] = '0';
					s.add( tmp );
					count++;
				}
				while ( !s.isEmpty() )
				{
					int[] pos = s.remove();
					px = pos[0];
					py = pos[1];
					if ( px != 0 && grid[px - 1][py] == '1' )
					{
						tmp = new int[2];
						tmp[0] = px - 1;
						tmp[1] = py;
						grid[px - 1][py] = '0';
						s.add( tmp );
					}
					if ( py != 0 && grid[px][py - 1] == '1' )
					{
						tmp = new int[2];
						tmp[0] = px;
						tmp[1] = py - 1;
						grid[px][py - 1] = '0';
						s.add( tmp );
					}
					if ( px != sizex - 1 && grid[px + 1][py] == '1' )
					{
						tmp = new int[2];
						tmp[0] = px + 1;
						tmp[1] = py;
						grid[px + 1][py] = '0';
						s.add( tmp );
					}
					if ( py != sizey - 1 && grid[px][py + 1] == '1' )
					{
						tmp = new int[2];
						tmp[0] = px;
						tmp[1] = py + 1;
						grid[px][py + 1] = '0';
						s.add( tmp );
					}
				}

			}
		}

		return count;
	}

	public int missingNumber( int[] nums )
	{
		Arrays.sort( nums );
		int[] diff = new int[nums.length - 1];
		for ( int i = 0; i < nums.length - 1; i++ )
			diff[i] = nums[i + 1] - nums[i];
		for ( int i = 0; i < diff.length; i++ )
		{
			if ( diff[i] != 1 )
				return nums[i] + 1;
		}
		if ( nums[0] == 0 )
			return nums.length;
		return 0;
	}

	public boolean isPalindrome( ListNode head )
	{
		int size = 0;
		ListNode reverse = new ListNode( 0 );
		ListNode tmp = head;
		while ( tmp != null )
		{
			ListNode cNode = new ListNode( tmp.val );
			cNode.next = reverse.next;
			reverse.next = cNode;
			tmp = tmp.next;
			size++;
		}
		System.out.println( "head: " );
		head.print();
		reverse = reverse.next; // skip 0
		System.out.println( "reverse: " );
		reverse.print();
		for ( int i = 0; i < size / 2; i++ )
		{
			if ( head.val != reverse.val )
				return false;
			head = head.next;
			reverse = reverse.next;
		}
		return true;

	}

	public List<String> binaryTreePaths( TreeNode root )
	{
		List<String> l = new ArrayList<String>();
		String s = new String();
		if ( root == null )
			return l;
		s += root.val;
		if ( root.left == null && root.right == null )
		{
			l.add( s );
			return l;
		}
		if ( root.left != null )
			l = preOrder( root.left, s, l );
		if ( root.right != null )
			l = preOrder( root.right, s, l );
		return l;
	}

	public List<String> preOrder( TreeNode root, String s, List<String> l )
	{
		if ( root == null )
		{
			// l.add(s)
			return l;
		}
		s = s + "->" + Integer.toString( root.val );
		if ( root.left == null && root.right == null )
		{
			l.add( s );
			return l;
		}
		if ( root.left != null )
			l = preOrder( root.left, s, l );
		if ( root.right != null )
			l = preOrder( root.right, s, l );

		return l;
	}

	public String i2s( int n )
	{
		return Integer.toString( n );
	}

	public List<List<Integer>> threeSum2( int[] nums )
	{
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if ( nums == null || nums.length < 2 )
			return result;
		Arrays.sort( nums );

		int len = nums.length;
		for ( int i = 0; i < len; i++ )
		{
			if ( i > 0 && nums[i] == nums[i - 1] )
				continue; // Skip same results
			int target = 0 - nums[i];
			int j = i + 1, k = len - 1;
			while ( j < k )
			{
				if ( nums[j] + nums[k] == target )
				{
					result.add( Arrays.asList( nums[i], nums[j], nums[k] ) );
					while ( j < k && nums[j] == nums[j + 1] )
						j++; // Skip same results
					while ( j < k && nums[k] == nums[k - 1] )
						k--; // Skip same results
					j++;
					k--;
				}
				else if ( nums[j] + nums[k] < target )
				{
					j++;
				}
				else
				{
					k--;
				}
			}
		}
		System.out.print( result.toString() );
		return result;
	}

	public int threeSumClosest( int[] nums, int target )
	{
		Arrays.sort( nums );

		int i, j, k, sum = 0, curbest = 999, cursum = 0;
		for ( i = 0; i < nums.length - 2; i++ )
		{
			if ( i > 0 && nums[i] == nums[i - 1] )
				continue;
			j = i + 1;
			k = nums.length - 1;
			while ( j < k )
			{
				sum = nums[i] + nums[j] + nums[k];
				if ( Math.abs( sum - target ) < Math.abs( curbest ) )
				{
					curbest = sum - target;
					cursum = sum;
				}

				while ( j < k && sum == target )
				{
					return target;
				}
				while ( j < k && sum > target )
				{
					if ( Math.abs( sum - target ) < Math.abs( curbest ) )
					{
						curbest = sum - target;
						cursum = sum;
					}
					k--;
					while ( j < k && nums[k] == nums[k + 1] )
						k--;
					sum = nums[i] + nums[j] + nums[k];

				}
				while ( j < k && sum < target )
				{
					if ( Math.abs( sum - target ) < Math.abs( curbest ) )
					{
						curbest = sum - target;
						cursum = sum;
					}
					j++;
					while ( j < k && nums[j] == nums[j - 1] )
						j++;
					sum = nums[i] + nums[j] + nums[k];

				}

			}
		}
		System.out.println( cursum );
		return cursum;

	}

	public static void fizbuz( int a, int b, int n )
	{
		System.out.println( "a: " + a + "b: " + b );
		for ( int i = 1; i <= n; i++ )
		{
			if ( i % a == 0 )
				System.out.print( "F" );
			if ( i % b == 0 )
				System.out.print( "B" );
			if ( i % a == 0 || i % b == 0 )
			{
				System.out.print( " " );
				continue;
			}
			else
				System.out.print( i );
			System.out.print( " " );
		}

	}

	public void moveZeroes( int[] nums )
	{
		int i = nums.length - 1, k = 0;
		while ( nums[i] == 0 && i > 0 )
			i--;
		int index = i;
		if ( index == 0 )
			return; // all zero's
		for ( i = 0; i <= index; i++ )
		{
			while ( nums[i] == 0 )
				i++;
			nums[k] = nums[i];
			k++;
		}
		for ( i = k; i < nums.length; i++ )
			nums[i] = 0;
	}

	public boolean isUgly( int num )
	{
		if ( num == 1 || num == 0 )
			return true;
		// if ( num < 0 ) num = -num;
		while ( num % 2 == 0 )
			num /= 2;
		System.out.println( num );
		while ( num % 3 == 0 )
			num /= 3;
		System.out.println( num );
		while ( num % 5 == 0 )
			num /= 5;
		System.out.println( num );
		if ( num != 1 )
			return false;
		return true;
	}

	public boolean isValidSudoku( char[][] board )
	{
		boolean[][] row = new boolean[9][9];
		boolean[][] col = new boolean[9][9];
		boolean[][] sqr = new boolean[9][9];
		for ( int i = 0; i < 9; i++ )
		{
			for ( int j = 0; j < 9; j++ )
			{
				if ( board[i][j] != '.' )
				{
					int num = board[i][j] - '0';
					if ( row[i][num] == true || col[j][num] == true || sqr[( i / 3 ) * 3 + j / 3][num] == true )
						return false;
					row[i][num] = true;
					col[j][num] = true;
					sqr[( i / 3 ) * 3 + j / 3][num] = true;
				}
			}
		}
		return true;
	}

	public boolean canFinish( int numCourses, int[][] prerequisites )
	{
		int[] rank = new int[numCourses];
		return true;

	}

	public boolean containsNearbyAlmostDuplicate( int[] nums, int k, int t )
	{
		return true;

	}

	public int count( char[][] c )
	{

		return 0;
	}

	public char[] manacherStr( String s )
	{
		char[] c = new char[s.length() * 2 + 1];
		char[] sc = s.toCharArray();
		for ( char x : c )
			x = '#';
		for ( int i = 0; i < sc.length; i++ )
			c[i * 2 + 1] = sc[i];
		return c;
	}

	public boolean isPrime( long d )
	{
		if ( d == 2 || d == 3 )
			return true;
		for ( int i = 2; i <= Math.sqrt( (double) d ); i++ )
		{
			if ( d % i == 0 )
				return false;
		}
		return true;
	}

	public boolean isPrime( int d )
	{
		if ( d == 2 || d == 3 )
			return true;
		for ( int i = 2; i <= Math.sqrt( (double) d ); i++ )
		{
			if ( d % i == 0 )
				return false;
		}
		return true;
	}

	public void q97()
	{
		BigInteger a = new BigInteger( "2" );
		a = a.pow( 7830457 ).multiply( new BigInteger( "28433" ) ).add( new BigInteger( "1" ) );
		System.out.println( a.mod( new BigInteger( "10000000000" ) ).toString() );
	}

	public void q206()
	{
		BigInteger a = new BigInteger( "1010101010" );
		BigInteger b = new BigInteger( "1389026624" );
		// System.out.println( "b^2: " + b.pow( 2 ).toString() );
		while ( a.compareTo( b ) < 0 )
		{
			char[] c = a.pow( 2 ).toString().toCharArray();
			if ( check( c ) )
				System.out.println( a.toString() );
			a = a.add( new BigInteger( "10" ) );
			// System.out.println( "a: " + a.toString() );
		}
	}

	public boolean check( char[] c )
	{
		if ( c.length != 19 )
			return false;
		if ( c[18] != '0' )
			return false;
		for ( int i = 0; i < 17; i = i + 2 )
			if ( c[i] != i / 2 + 1 + '0' )
				return false;
		return true;
	}

	public void q92()
	{
		int count = 0;
		for ( int k = 2; k < 1e7; k++ )
		{
			int i = k;
			// System.out.print( "i: " + i );

			while ( i != 1 && i != 89 )
				i = sqr( i );
			// System.out.print( " || " + i );
			// System.out.println();
			if ( i == 89 )
				count++;
		}
		System.out.println( "count: " + count );

	}

	public int sqr( int n )
	{
		char[] c = String.valueOf( n ).toCharArray();
		int sum = 0;
		for ( char v : c )
			sum += ( v - '0' ) * ( v - '0' );
		return sum;
	}

	public void bigSum()
	{
		int sum = 0;
		BigInteger a, b;
		for ( int i = 1; i < 100; i++ )
		{
			a = new BigInteger( String.valueOf( i ) );
			for ( int j = 1; j < 100; j++ )
			{
				b = new BigInteger( String.valueOf( j ) );
				sum = Math.max( sum, findS( a.pow( b.intValue() ).toString() ) );
				System.out.println( "a: " + a.intValue() + " b: " + b.intValue() );
			}
		}
		System.out.println( "sum: " + sum );
	}

	public int findS( String s )
	{
		int sum = 0;
		char[] c = s.toCharArray();
		for ( char v : c )
		{
			sum += v - '0';
		}
		return sum;
	}

	public void contigentPrime()
	{
		double sum = 0;
		for ( double i = 2; i < 5e3; i++ )
		{
			if ( prime( i ) )
			{
				sum += i;
				if ( prime( sum ) && sum < 1e4 )
					System.out.println( sum );
			}
		}
	}

	public int countDigitOneOld( int n )
	{

		int sum = 0;
		for ( int i = 1; i <= n; i++ )
		{
			char[] c = String.valueOf( i ).toCharArray();
			for ( char s : c )
			{
				if ( s == '1' )
					sum++;
			}
		}
		return sum;
	}

	public boolean isPowerOfTwo( int n )
	{
		int b = 0;
		while ( b == 0 )
		{
			b = n % 2;
			n /= 2;
		}
		return ( b == 0 ) ? true : false;
	}

	public boolean isIsomorphic( String s, String t )
	{
		HashMap<Character, Character> m1 = new HashMap<Character, Character>();
		HashMap<Character, Character> m2 = new HashMap<Character, Character>();
		if ( s.length() != t.length() )
			return false;
		char[] c1 = s.toCharArray();
		char[] c2 = t.toCharArray();
		for ( int i = 0; i < c1.length; i++ )
		{

			if ( m1.containsKey( c1[i] ) )
			{
				if ( c2[i] != m1.get( c1[i] ) )
					return false;
			}
			if ( m2.containsKey( c2[i] ) )
			{
				if ( c1[i] != m2.get( c2[i] ) )
					return false;
			}
			else
			{
				m1.put( c1[i], c2[i] );
				m2.put( c2[i], c1[i] );
			}
		}
		return true;
	}

	public int computeArea( int A, int B, int C, int D, int E, int F, int G, int H )
	{
		int a1 = ( C - A ) * ( D - B );
		int a2 = ( G - E ) * ( H - F );
		// no intersection
		if ( C < E || G < A || D < F || H < B )
			return a1 + a2;
		int[] array1 = { A, C, E, G };
		int[] array2 = { B, D, F, H };
		int[] inter1 = min( array1 );
		int[] inter2 = min( array2 );
		return a1 + a2 - ( inter1[2] - inter1[1] ) * ( inter2[2] - inter2[1] );
	}

	public int[] min( int[] x )
	{
		int tmp;
		// int min[] = new int[x.length];
		for ( int i = 0; i < x.length; i++ )
		{
			for ( int j = i; j < x.length; j++ )
			{
				if ( x[i] > x[j] )
				{
					tmp = x[j];
					x[j] = x[i];
					x[i] = tmp;
				}
			}
		}
		return x;
	}

	public List<String> summaryRanges( int[] nums )
	{
		// holds differences

		ArrayList<String> ret = new ArrayList<String>();
		if ( nums.length == 0 )
			return ret;
		if ( nums.length == 1 )
		{
			ret.add( String.valueOf( nums[0] ) );
			return ret;
		}
		int[] diff = new int[nums.length - 1];
		for ( int i = 0; i < nums.length - 1; i++ )
		{
			diff[i] = nums[i + 1] - nums[i];
		}
		// if diff[i] is 1, meaning consecutive

		String tmp = String.valueOf( nums[0] );
		for ( int i = 0; i < diff.length; i++ )
		{
			if ( diff[i] == 1 )
			{
				continue;
			}
			else
			{
				tmp = makeString( tmp, String.valueOf( nums[i] ) );
				ret.add( tmp );
				tmp = String.valueOf( nums[++i] );
				i--;
			}

		}
		tmp = String.valueOf( nums[nums.length - 1] );
		int i = diff.length - 1;
		while ( diff[i] == 1 && i > 0 )
		{
			i--;
		}
		if ( i == 0 && diff[i] == 1 )
			tmp = makeString( String.valueOf( nums[++i] ), tmp );
		else
			tmp = makeString( String.valueOf( nums[++i] ), tmp );
		ret.add( tmp );
		for ( String s : ret )
			System.out.println( s );

		return ret;
	}

	public String makeString( String s, String num )
	{
		if ( s.equalsIgnoreCase( num ) )
			return s;
		else
			return s + "->" + num;
	}

	public int calculateSub( String s )
	{
		s = s.replaceAll( "\\s", "" );
		s = s.replaceAll( "\\D", " $0 " );
		String delim = "[ ]";
		String[] tokens = s.split( delim );
		int sum = atoi( tokens[0] );
		for ( int i = 1; i < tokens.length - 1; i++ )
		{
			switch ( tokens[i] )
			{
			case "+":
				sum += atoi( tokens[i + 1] );
				break;
			case "-":
				sum -= atoi( tokens[i + 1] );
				break;
			default:
				break;
			}
		}

		return sum;
	}

	public int atoi( String s )
	{
		char[] c = s.toCharArray();
		int sum = 0;
		for ( int i = 0; i < c.length; i++ )
		{
			sum = sum * 10 + ( c[i] - '0' );
		}
		return sum;
	}

	public void findT()
	{
		int sum = 0;
		for ( int i = 1; i < 1e6; i = i + 2 )
		{
			// System.out.println(i);
			if ( isPalindrome( Integer.toString( i, 10 ).toCharArray() ) )
				if ( isPalindrome( Integer.toString( i, 2 ).toCharArray() ) )
				{
					System.out.println( i );
					sum += i;
				}
		}
		System.out.println( "Sum: " + sum );
	}

	public char[] genPalin( int n )
	{
		char[] c = new char[n];
		for ( int i = 0; i <= n / 2; i++ )
		{

		}
		return c;
	}

	public boolean isPalindrome( char[] c )
	{
		for ( int i = 0; i < c.length / 2; i++ )
		{
			if ( c[i] != c[c.length - 1 - i] )
				return false;
		}
		return true;
	}

	/*
		public ListNode removeElements( ListNode head, int val )
		{
			head.print();
			ListNode ret = new ListNode( 0 ), tmp, last;
			last = ret;
			if ( head == null )
				return null;
			while ( head.next != null )
			{
				if ( head.val != val )
				{
					tmp = new ListNode( head.val );
					last.next = tmp;
					last = last.next;
				}
				head = head.next;
			}
			if ( head.val != val )
			{
				tmp = new ListNode( head.val );
				last.next = tmp;
			}
			ret.print();
			return ret.next;
		}
	*/
	public ListNode reverseList( ListNode head )
	{
		if ( head == null )
			return null;
		ListNode ret = new ListNode( 0 );
		ListNode body, tail = null;
		while ( head.next != null )
		{
			body = new ListNode( head.val );
			ret.next = body;
			body.next = tail;
			tail = body;
		}
		return ret;
	}

	public boolean containsDuplicate( int[] nums )
	{
		HashSet<Integer> a = new HashSet<Integer>();
		for ( int i : nums )
		{

			if ( a.contains( i ) )
				return false;
			else
				a.add( i );
		}
		return true;
	}

	public TreeNode invertTree( TreeNode root )
	{
		if ( root == null )
			return root;
		TreeNode left, right, tmp;
		left = root.left;
		right = root.right;
		if ( left == null && right == null )
			return root;
		if ( left != null && right != null )
		{
			root.left = right;
			root.right = left;
			invertTree( root.left );
			invertTree( root.right );
		}
		else if ( left != null )
		{
			root.right = left;
			root.left = null;
			invertTree( root.right );
		}
		else if ( right != null )
		{
			root.left = right;
			root.right = null;
			invertTree( root.left );
		}
		root.print();
		return root;
	}

	public void penta()
	{
		ArrayList<Integer> l = new ArrayList<Integer>();
		for ( int i = 1; i < 5000; i++ )
		{
			l.add( i * ( 3 * i - 1 ) / 2 );
		}
		for ( int i = 0; i < l.size(); i++ )
		{
			for ( int j = 0; j < l.size(); j++ )
			{
				if ( l.contains( l.get( i ) + l.get( j ) ) && l.contains( Math.abs( l.get( i ) - l.get( j ) ) ) )
				{
					System.out.println( "i:" + i + "j: " + j );
					System.out.println( "i:" + l.get( i ) + "j: " + l.get( j ) );
					System.out.println( "Diff:" + Math.abs( l.get( i ) - l.get( j ) ) );
				}
			}
		}
	}

	public void spiral()
	{
		double k = 1;
		double n = 2 * k + 1;
		double perc = 1;
		double sum = 0;
		while ( perc > 0.1 )
		{
			n = 2 * k + 1;
			if ( prime( n * n ) )
				sum++;
			if ( prime( n * n - ( n - 1 ) ) )
				sum++;
			if ( prime( n * n - 2 * ( n - 1 ) ) )
				sum++;
			if ( prime( n * n - 3 * ( n - 1 ) ) )
				sum++;
			perc = sum / ( 4 * k );
			k++;
		}
		System.out.println( n );

	}

	public void fact()
	{
		for ( int i = 3; i < 1e9; i++ )
		{
			if ( test( i ) )
				System.out.println( i );
		}
	}

	public boolean test( int n )
	{
		char[] c = String.valueOf( n ).toCharArray();
		int sum = 0;
		for ( char t : c )
		{
			sum += factorial( t );
		}
		return ( sum == n ) ? true : false;
	}

	public int factorial( char n )
	{
		switch ( n - '0' )
		{
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 6;
		case 4:
			return 24;
		case 5:
			return 120;
		case 6:
			return 720;
		case 7:
			return 5040;
		case 8:
			return 40320;
		case 9:
			return 362880;
		}
		return 1;

	}

	public void remain()
	{
		BigInteger sum = new BigInteger( "0" );
		BigInteger loop = new BigInteger( "0" );
		for ( int i = 1; i <= 1000; i++ )
		{
			loop = new BigInteger( String.valueOf( i ) );
			sum = sum.add( loop.pow( loop.intValue() ) );

		}
		System.out.println( sum );
	}

	public int distinct()
	{
		ArrayList<BigInteger> s = new ArrayList<BigInteger>();
		BigInteger a, b;

		for ( int i = 2; i <= 100; i++ )
		{
			a = new BigInteger( String.valueOf( i ) );
			for ( int j = 2; j <= 100; j++ )
			{
				b = new BigInteger( String.valueOf( j ) );
				if ( !s.contains( a.pow( b.intValue() ) ) )
					s.add( a.pow( b.intValue() ) );
			}
		}
		System.out.println( s.size() );
		return s.size();
	}

	public void count()
	{
		int sum = 0;
		for ( int i = 2; i <= 1000000; i++ )
		{
			if ( rot( i ) )
			{
				System.out.print( i + " " );
				sum++;
			}
		}
		System.out.println( sum );
	}

	public boolean rot( int n )
	{
		if ( !prime( n ) )
			return false;
		int digit = 0;
		int newNum = n;
		while ( newNum != 0 )
		{
			newNum /= 10;
			digit++;
		}
		int a, b, oldNum = n;
		newNum = n % 10 * (int) Math.pow( 10, digit - 1 ) + n / 10;
		while ( newNum != oldNum )
		{
			if ( !prime( newNum ) )
				return false;
			newNum = newNum % 10 * (int) Math.pow( 10, digit - 1 ) + newNum / 10;
		}
		return true;
	}

	public boolean prime( double d )
	{
		if ( d == 2 || d == 3 )
			return true;
		for ( int i = 2; i <= Math.sqrt( d ); i++ )
		{
			if ( d % i == 0 )
				return false;
		}
		return true;
	}

	public int find()
	{

		int size = 28123;
		ArrayList<Integer> l1 = new ArrayList<Integer>();
		boolean[] l2 = new boolean[size + 1];
		for ( int i = 2; i < size; i++ )
		{
			if ( abund( i ) )
				l1.add( i );
		}
		for ( int i = 0; i < l1.size(); i++ )
		{
			for ( int j = 0; j < l1.size(); j++ )
			{
				if ( l1.get( i ) + l1.get( j ) <= size )
				{
					l2[l1.get( i ) + l1.get( j )] = true;
				}
				else
				{
					break;
				}
			}
		}

		// System.out.println("l2 after remove: " + Arrays.toString(l2));
		int sum = 0;
		for ( int i = 0; i <= size; i++ )
		{
			if ( l2[i] == false )
				sum += i;
		}
		System.out.println( sum );
		return sum;
	}

	public boolean abund( int n )
	{
		int sum = 0;
		for ( int i = 1; i < n; i++ )
		{
			if ( n % i == 0 )
			{
				sum += i;
			}
		}
		return ( sum > n ) ? true : false;
	}

	public boolean isHappy( int n )
	{
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add( n );
		int newNum = con( n );
		while ( !l.contains( newNum ) && newNum != 1 )
		{
			l.add( newNum );
			newNum = con( newNum );
		}
		if ( newNum == 1 )
			return true;
		return false;
	}

	public int con( int n )
	{
		int decimals = 0;
		while ( n != 0 )
		{
			decimals += ( n % 10 ) * ( n % 10 );
			n /= 10;
		}
		return decimals;
	}

	public int[][] generateMatrix( int n )
	{
		int[][] num = new int[n][n];
		if ( n == 0 )
			return num;
		int count = 1;
		int t = n;// (n + 1) / 2;
		int row = 0, col = 0;
		for ( int side = 0; side < t; side++ )
		{
			while ( col < t - side )
			{
				num[row][col] = count;
				col++;
				count++;
			}
			col--;
			row++;
			while ( row < t - side )
			{
				num[row][col] = count;
				row++;
				count = count + 1;
			}
			row--;
			col--;
			while ( col >= side )
			{
				num[row][col] = count;
				col--;
				count = count + 1;
			}
			col++;
			row--;
			while ( row > side )
			{
				num[row][col] = count;
				row--;
				count = count + 1;
			}
			row++;
			col++;

		}
		for ( int i = 0; i < num.length; i++ )
		{
			for ( int j = 0; j < num.length; j++ )
			{
				System.out.format( "%3d", num[i][j] );
			}
			System.out.println();
		}
		return num;
	}

	public static int countNeg( int[] A )
	{
		int sum = 0;
		for ( int i = 0; i < A.length; i++ )
		{
			if ( A[i] <= 0 )
			{
				// now find contingent blocks
				for ( int j = i; j < A.length; j++ )
				{
					if ( A[j] > 0 )
					{
						sum += count1( j - i );
						i = j;
						break;
					}
					else if ( j == A.length - 1 )
					{
						sum += count1( j - i );
						i = j;
					}
					else
						continue;
				}
			}
			else
				continue;
		}
		return sum;
	}

	public static int count1( int n )
	{
		return n * ( n + 1 ) / 2;
	}

	public static ArrayList<ArrayList<Integer>> subset( ArrayList<ArrayList<Integer>> list, int n )
	{
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		if ( list.isEmpty() )
		{

			ret.add( a );
			ArrayList<Integer> t = new ArrayList<Integer>();
			t.add( n );
			ret.add( t );
			return ret;
		}
		ret.addAll( list );
		for ( ArrayList<Integer> b : list )
			b.add( n );
		ret.addAll( list );
		return ret;
	}

	public int rob( int[] num )
	{
		/*
		 * dp algo:
		 * p(k) = max ( p[k-2] + m[k], p[k-1] )
		 * where 
		 * p[k] is maximum amount obtained at k-th hous
		 * m[k] is the money inside k-th house
		 * p[0] = 0, p[1] = m[1]
		 * 
		 */
		if ( num == null )
			return 0;
		int pk_2 = 0, pk_1 = num[0], pk = 0;
		for ( int i = 1; i < num.length; i++ )
		{
			pk = Math.max( pk_2 + num[i], pk_1 );
			pk_2 = pk_1;
			pk_1 = pk;
		}
		return pk;
	}

	public static void worstPerm( int[] n, long k, int tot )
	{
		/*
		 if ( k >= n.length)
		{
			for ( int i = tot; i >= 1; i-- )
				System.out.print(i + " ");
			return;
		}
		*/
		int counter = 0; // counter for counting number of moves
		int tmp;
		for ( int i = 0; i < k; i++ )
		{
			// kth move coresponds to ith position
			// e.g. 1 move only, means moving largest num, i.e. n-i,
			// to first position
			if ( counter >= n.length )
				break;
			if ( n[counter] == tot - counter )
			{
				counter++;
				i--;
				continue;
			}
			int j = index( n, tot - counter, counter );
			// find kth number's position
			tmp = n[counter];
			n[counter] = n[j];
			n[j] = tmp;
			counter++;
			System.out.println( i + "th move:" );
			for ( int x : n )
				System.out.print( x + " " );
			System.out.println();
		}

	}

	public static int index( int[] n, int val, int count )
	{
		for ( int i = count; i < n.length; i++ )
			if ( n[i] == val )
				return i;
		return -1;
	}

	public static int or( char[] a, char[] b )
	{
		int[] c = new int[a.length];
		int counter = 0;
		for ( int i = 0; i < a.length; i++ )
		{
			c[i] = ( a[i] - '0' ) | ( b[i] - '0' );
			if ( c[i] == 1 )
				counter++;
		}

		return counter;
	}

	public static void run()
	{
		Scanner in = new Scanner( System.in );
		int res;
		String next = in.nextLine();
		String[] next_split = next.split( " " );
		int jars = Integer.parseInt( next_split[0] );
		int ops = Integer.parseInt( next_split[1] );
		String array = "";
		BigDecimal sum = new BigDecimal( 0 );
		for ( int i = 0; i < ops; i++ )
		{
			array = in.nextLine();
			String[] splited = array.split( " " );
			int num1 = Integer.parseInt( splited[0] );
			int num2 = Integer.parseInt( splited[1] );
			int num3 = Integer.parseInt( splited[2] );
			sum = sum.add( new BigDecimal( num2 - num1 + 1 ).multiply( new BigDecimal( num3 ) ) );
		}

		// System.out.println((sum));
		System.out.println( ( sum.divide( new BigDecimal( jars ) ).toBigInteger() ) );
		// System.out.println(((long) sum.divide(new BigDecimal(jars))));
	}

	public static void towns( int[] A )
	{
		long prod = 1;
		for ( int x : A )
		{
			prod *= x;
			prod %= 1234567;
		}
		System.out.println( prod % 1234567 );
	}

	public static long factor35( long N )
	{
		N--;
		return 3 * handShake( N / 3 ) + 5 * handShake( N / 5 ) - 15 * handShake( N / 15 );
	}

	public static long handShake( long A )
	{
		return A * ( 1 + A ) / 2;
	}

	public static void minDraw( int A )
	{
		System.out.println( A + 1 );
	}

	public static void insertIntoSorted( int[] ar )
	{
		int tmp = ar[ar.length - 1];
		boolean flag = false;
		int i = ar.length - 2;
		while ( ar[i] > tmp && i > 0 )
		{
			ar[i + 1] = ar[i];
			i--;
			printArray( ar );
		}
		if ( i == 0 && ar[0] > tmp )
		{
			ar[1] = ar[0];
			ar[0] = tmp;
		}
		else
			ar[i + 1] = tmp;

		printArray( ar );
	}

	public static void printArray( int[] a )
	{
		for ( int i : a )
			System.out.print( i + " " );
		System.out.println();
	}

	public static void cut( int[] A )
	{
		quickSort( A, 0, A.length - 1 );
		ArrayList<Integer> list = new ArrayList<Integer>();
		int index = 0, comp = 0, dup = 0;
		while ( comp < A.length )
		{
			if ( A[index] == A[comp] )
			{
				dup++;
				comp++;
			}
			else
			{
				index = comp++;
				list.add( dup );
				dup = 1;
			}
		}
		int sum = 0;
		for ( int i : list )
		{
			System.out.println( A.length - sum );
			sum += i;
		}
		System.out.println( A.length - sum );

	}

	public static boolean only( String s )
	{
		char[] c = s.toCharArray();
		ArrayList<Character> list = new ArrayList<Character>();
		for ( char v : c )
		{
			if ( list.contains( v ) )
				list.remove( list.indexOf( v ) );
			else
				list.add( v );
		}
		System.out.println( list.toString() );
		return ( list.size() <= 1 ) ? true : false;
	}

	public static int maxMin( int[] A, int k )
	{
		int max = Integer.MAX_VALUE;
		k = k - 1;
		quickSort( A, 0, A.length - 1 );
		// System.out.println("Sorted array: " + Arrays.toString(A));
		for ( int i = 0; i < A.length - k; i++ )
		{
			max = Math.min( max, A[k + i] - A[i] );
		}
		return max;
	}

	public static int partition( int[] n, int start, int end )
	{
		if ( start == end )
			return start;
		int index = ( start + end ) / 2;
		int tmp = 0;
		// swap index to end
		tmp = n[end];
		n[end] = n[index];
		n[index] = tmp;
		// storeIndex will be the item that's larger than pivot
		int storeIndex = start;
		for ( int i = start; i < end; i++ )
		{

			if ( n[i] < n[end] )
			{
				// swap smaller item to front
				tmp = n[storeIndex];
				n[storeIndex] = n[i];
				n[i] = tmp;
				storeIndex++;
			}
		}
		tmp = n[end];
		n[end] = n[storeIndex];
		n[storeIndex] = tmp;
		return storeIndex;
	}
	/*
		public static void countAna( String[] s )
		{
			int count = 0;
			char[] c;
			for ( int i = 0; i < s.length; i++ )
			{
				if ( isAna( s[i] ) )
					System.out.println( 0 );
				else
				{
					c = s[i].toCharArray();
					for ( int j = 0; j < c.length / 2; j++ )
					{
						count += Math.abs( c[j] - c[c.length - 1 - j] );
					}
					System.out.println( count );
				}
				count = 0;
			}
		}*/

	public static void isFib( int[] n )
	{
		// int max = 0;
		// for ( int i : n )
		// max = Math.max(max, i);
		ArrayList<Long> fib = new ArrayList<Long>();
		fib.add( 1L );
		fib.add( 1L );
		int size = fib.size() - 1;
		while ( fib.get( size ) < 999999 )
		{
			size = fib.size() - 1;
			fib.add( fib.get( size ) + fib.get( size - 1 ) );
		}
		for ( int i : n )
		{
			if ( fib.contains( i ) )
				System.out.println( "IsFibo" );
			else
				System.out.println( "IsNotFibo" );
		}
	}

	public static int alcChar( String s )
	{
		char[] c = s.toCharArray();
		int i = 0, j = 1, k = 0;
		while ( j < c.length )
		{
			if ( c[j] == c[i] )
			{
				k++;
				j++;
			}
			else
			{
				i = j;
				j++;
			}
		}
		return k;
	}

	public static int maxXor( int l, int r )
	{
		int max = 0;
		for ( int i = l; i <= r; i++ )
		{
			for ( int j = i; j <= r; j++ )
			{
				max = Math.max( max, i ^ j );
			}
		}
		return max;
	}

	public static int count( int a )
	{
		int ret = 1;
		for ( int i = 0; i < a / 2; i++ )
		{
			ret *= 2;
			ret++;
		}
		if ( a % 2 != 0 )
			ret *= 2;
		return ret;
	}

	public void prt( int i )
	{
		double res = 1000000000000000.0 / i;
		// System.out.println(String.format("%.3f",res));
		System.out.println( i + ": 1/" + i + " = " + String.format( "%.30f", res ) );
	}

	public void threeN( int n1, int n2 )
	{
		int sum = 0;
		for ( int i = n1; i <= n2; i++ )
			sum = Math.max( sum, cycle( i ) );
		System.out.println( n1 + " " + n2 + " " + sum );
	}

	public int cycle( int i )
	{
		int step = 1;
		if ( i == 1 )
			return 1;
		while ( i != 1 )
		{
			if ( i % 2 != 0 )
				i = 3 * i + 1;
			else
				i /= 2;
			step++;
		}
		return step;
	}

	public static int[][] s2i( String[] s )
	{
		int[][] a = new int[20][20];
		int k = 0;
		for ( int i = 0; i < 20; i++ )
		{
			for ( int j = 0; j < 20; j++ )
			{
				a[i][j] = str2int( s[k] );
				k++;
			}
		}
		return a;
	}

	public static int str2int( String s )
	{
		char[] c = s.toCharArray();
		int sum = 0;
		for ( int i = 0; i < c.length; i++ )
			sum = sum * 10 + ( c[i] - '0' );
		return sum;
	}

	public int rowProd( int[][] a )
	{
		int col = a.length;
		int row = a[0].length;
		int prod = 0;
		for ( int i = 0; i < row; i++ )
		{
			for ( int j = 0; j < col - 3; j++ )
				prod = Math.max( prod, a[i][j] * a[i][j + 1] * a[i][j + 2] * a[i][j + 3] );
		}
		return prod;
	}

	public int colProd( int[][] a )
	{
		int col = a.length;
		int row = a[0].length;
		int prod = 0;
		for ( int j = 0; j < col; j++ )
			for ( int i = 0; i < row - 3; i++ )
				prod = Math.max( prod, a[i][j] * a[i + 1][j] * a[i + 2][j] * a[i + 3][j] );
		return prod;
	}

	public int diagProd( int[][] a )
	{
		int col = a.length;
		int row = a[0].length;
		int prod = 0;
		for ( int j = 0; j < col - 3; j++ )
			for ( int i = 0; i < row - 3; i++ )
				prod = Math.max( prod, a[i][j] * a[i + 1][j + 1] * a[i + 2][j + 2] * a[i + 3][j + 3] );
		return prod;
	}

	public int diagProd2( int[][] a )
	{
		int col = a.length;
		int row = a[0].length;
		int prod = 0;
		for ( int j = col - 1; j >= 3; j-- )
			for ( int i = 3; i < row - 3; i++ )
				prod = Math.max( prod, a[i][j] * a[i + 1][j - 1] * a[i + 2][j - 2] * a[i + 3][j - 3] );
		return prod;
	}

	/*
		public List<String> anagrams( String[] strs )
		{
			List<String> ret = new ArrayList<String>();
			for ( String s : strs )
				if ( isAna( s ) )
					ret.add( s );
			return ret;
		}
	
		public static boolean isAna( String s )
		{
			char[] c = s.toCharArray();
			int n = c.length;
			if ( n == 1 )
				return true;
			for ( int i = 0; i < n / 2; i++ )
				if ( c[i] != c[n - 1 - i] )
					return false;
			return true;
		}
	*/
	public int maxProduct( int[] A )
	{
		if ( A == null )
			return 0;
		if ( A.length == 1 )
			return A[0];
		ArrayList<Integer> zeros = new ArrayList<Integer>();
		for ( int i = 0; i < A.length; i++ )
			if ( A[i] == 0 )
				zeros.add( i );
		// zeros contains all zero indexes
		if ( zeros.size() != 0 )
		{
			zeros.add( 0, -1 );
			zeros.add( A.length );
			int[] prod = new int[zeros.size() - 1];
			int max = -9999;
			for ( int i = 0; i < zeros.size() - 1; i++ )
			{
				prod[i] = handleNeg( A, zeros.get( i ), zeros.get( i + 1 ) );
				System.out.println( "prod[i]" + i + " : " + prod[i] );
				if ( max < prod[i] )
					max = prod[i];
			}
			return Math.max( 0, max );
		}
		return handleNeg( A, -1, A.length );
	}

	public int handleNeg( int[] A, int start, int end )
	{
		if ( end - start == 1 )
			return 0;
		if ( end - start == 2 )
			return A[start + 1];
		int neg = 0, lindex = 0, rindex = 0, prod = 1;
		for ( int i = start + 1; i < end; i++ )
			if ( A[i] < 0 )
				neg++;
		if ( neg % 2 == 0 )
		{
			for ( int i = start + 1; i < end; i++ )
				prod *= A[i];
			return prod;
		}
		for ( int i = start + 1; i < end; i++ )
		{
			if ( A[i] < 0 )
			{
				lindex = i;
				break;
			}
		}
		for ( int i = end - 1; i > start; i-- )
		{
			if ( A[i] < 0 )
			{
				rindex = i;
				break;
			}
		}
		int lprod = 1, rprod = 1;
		if ( neg == 1 ) // lindex == rindex
		{

			for ( int i = start + 1; i < end; i++ )
			{
				if ( i < lindex )
					lprod *= A[i];
				else if ( i > lindex )
					rprod *= A[i];
			}
			return ( lprod < rprod ) ? rprod : lprod;
		}
		else
		{
			System.out.println( "lIndex: " + lindex + " rIndex: " + rindex );
			for ( int i = start + 1; i < rindex; i++ )
				lprod *= A[i];
			for ( int i = lindex + 1; i < end; i++ )
				rprod *= A[i];
			return ( lprod < rprod ) ? rprod : lprod;
		}
	}

	public ListNode mergeKLists( List<ListNode> lists )
	{
		// ListNode ret = lists.get(0);
		if ( lists == null )
			return null;
		if ( lists.size() == 0 )
			return null;
		int i = 0;
		while ( lists.size() > 1 )
		{

			lists.set( i, mergeTwoLists( lists.get( i ), lists.get( i + 1 ) ) );
			lists.remove( i + 1 );
			if ( i == lists.size() - 1 )
				i = 0;
			i++;
		}
		return lists.get( 0 );
	}

	public ListNode mergeTwoLists( ListNode l1, ListNode l2 )
	{
		if ( l1 == null && l2 == null )
			return null;
		if ( l1 == null && l2 != null )
			return l2;
		if ( l1 != null && l2 == null )
			return l1;
		ListNode ret = new ListNode( 0 );
		ListNode tmp = ret;
		while ( l1 != null && l2 != null )
		{
			if ( l1.val < l2.val )
			{
				tmp.next = new ListNode( l1.val );
				tmp = tmp.next;
				l1 = l1.next;
			}
			else
			{
				tmp.next = new ListNode( l2.val );
				tmp = tmp.next;
				l2 = l2.next;
			}
		}
		while ( l1 != null )
		{
			tmp.next = new ListNode( l1.val );
			tmp = tmp.next;
			l1 = l1.next;
		}
		while ( l2 != null )
		{
			tmp.next = new ListNode( l2.val );
			tmp = tmp.next;
			l2 = l2.next;
		}
		return ret.next;
	}

	public void printInt( int[] a )
	{
		System.out.println( "\nArray:" );
		for ( int s : a )
			System.out.print( s + " " );
	}

	public void rotate( int[] nums, int k )
	{
		k = k % nums.length;
		int tmp;
		for ( int i = 0; i < nums.length - 1; i++ )
		{
			tmp = nums[0];
			nums[0] = nums[( ( i + 1 ) * k ) % nums.length];
			nums[( ( i + 1 ) * k ) % nums.length] = tmp;
			printInt( nums );
		}
	}

	public String multiply( String num1, String num2 )
	{
		int carry = 0, prod = 0;
		char[] n1 = num1.toCharArray();
		char[] n2 = num2.toCharArray();
		ArrayList<Integer> r = new ArrayList<Integer>();
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		String ret = "";
		int[] res = new int[2];
		for ( int i = n1.length - 1; i >= 0; i-- )
		{
			carry = 0;
			for ( int k = n1.length - 1; k > i; k-- )
				tmp.add( 0 );
			for ( int j = n2.length - 1; j >= 0; j-- )
			{
				res = mul( n1[i] - '0', n2[j] - '0', carry );
				carry = res[1];
				tmp.add( res[0] );
			}
			if ( carry != 0 )
				tmp.add( carry );
			System.out.println( "\nr: " );
			for ( int c : r )
				System.out.print( c + " " );
			System.out.println( "\ntmp: " );
			for ( int c : tmp )
				System.out.print( c + " " );
			r = adder( r, tmp );
			tmp.clear();
		}
		for ( int c : r )
			ret = Integer.toString( c ) + ret;
		char[] d = ret.toCharArray();
		if ( d[0] == '0' )
			return "0";
		return ret;
	}

	public ArrayList<Integer> adder( ArrayList<Integer> l1, ArrayList<Integer> l2 )
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int ten = 0;
		for ( int i = 0; i < Math.max( l1.size(), l2.size() ); i++ )
		{
			if ( i < l1.size() )
			{
				ten += l1.get( i );
			}
			if ( i < l2.size() )
			{
				ten += l2.get( i );
			}
			ret.add( ten % 10 );
			ten /= 10;
		}
		if ( ten == 1 )
			ret.add( 1 );
		return ret;
	}

	public int[] mul( int num1, int num2, int carry )
	{
		int[] r = new int[2];
		r[1] = ( num1 * num2 ) / 10;// carry
		r[0] = ( num1 * num2 ) % 10 + carry;// product digit
		System.out.print( "\n num1: " + num1 + " num2: " + num2 + " carry: " + r[1] + " digit: " + r[0] );
		return r;
	}

	public int findPeakElement( int[] num )
	{
		if ( num.length <= 1 )
			return 0;
		double[] a = new double[num.length + 2];
		a[0] = a[num.length + 1] = -Math.pow( 2, 31 );
		for ( int i = 0; i < num.length; i++ )
			a[i + 1] = num[i];
		System.out.print( a[0] );
		for ( int i = 1; i < a.length - 1; i++ )
		{
			if ( a[i] >= a[i - 1] && a[i] >= a[i + 1] )
				return i - 1;
		}
		return 0;
	}

	public ListNode swapPairs( ListNode head )
	{
		ListNode ret = new ListNode( 0 );
		ListNode h, h1, h2, h3;
		ret.next = head;
		h = ret;
		ret.print();
		while ( h.next != null && h.next.next != null )
		{
			h1 = h.next;
			h2 = h1.next;
			h3 = h2.next;
			h.next = h2;
			h2.next = h1;
			h1.next = h3;
			h = h.next.next; // h = h1;
			ret.print();
		}
		return ret.next;
	}

	public void swap( ListNode head )
	{

	}

	public String largestNumber( int[] num )
	{
		Comparator<String> comp = new Comparator<String>()
		{
			@Override
			public int compare( String str1, String str2 )
			{
				String s1 = str1 + str2;
				String s2 = str2 + str1;
				return s1.compareTo( s2 );
			}
		};
		// convert all numbers into string array, so sorting can be done

		String[] st = new String[num.length];
		for ( int i = 0; i < num.length; i++ )
			st[i] = num[i] + "";
		// sort using array feature
		Arrays.sort( st, comp );
		for ( int i = 0; i < num.length; i++ )
			System.out.print( " " + st[i] );
		if ( st[num.length - 1].equals( "0" ) )
			return "0";
		String ret = "";
		for ( int i = num.length - 1; i >= 0; i-- )
			ret += st[i];
		return ret;

		/*
		 * int n = num.length; if( n == 0 ) return ""; if( n == 1 ) return
		 * toString(num[0]); // String ret = ""; int index = 0;
		 * ArrayList<Integer> pushed = new ArrayList<Integer>(); boolean flag =
		 * true; for(int i = 0; i < n; i++) { if( num[i] != 0 ) flag = false; }
		 * int s = 0; if( flag == true ) s = n - 1; for(int i = 0; i < n - s;
		 * i++) { // find biggest num to fill index = compare(num, pushed);
		 * System.out.println(" found num: " + num[index] + " current string: "
		 * + ret); ret += toString(num[index]); pushed.add(index); } return ret;
		 */
	}

	// returns index of current best number to fill
	public int compare( int[] num, ArrayList<Integer> checkList )
	{
		int biggest = 0, index = 0;
		ArrayList<Integer> firstDig = new ArrayList<Integer>();
		ArrayList<Integer> duplicate = new ArrayList<Integer>();
		for ( int i = 0; i < num.length; i++ )
		{
			if ( !checkList.contains( i ) )
			{
				if ( biggest <= getFirst( num[i] ) )
				{
					biggest = getFirst( num[i] );
					index = i;
				}
				if ( !firstDig.contains( getFirst( num[i] ) ) )
					firstDig.add( getFirst( num[i] ) );
				else
					duplicate.add( getFirst( num[i] ) );
			}
		}
		ArrayList<Integer> bigList = new ArrayList<Integer>();
		if ( duplicate.contains( biggest ) )
		{
			for ( int i = 0; i < num.length; i++ )
			{
				if ( !checkList.contains( i ) )
				{
					if ( getFirst( num[i] ) == biggest )
						bigList.add( i );
				}
			}
			index = getBiggest( num, bigList );
		}
		return index;
	}

	public int getBiggest( int[] num, ArrayList<Integer> bigList )
	{
		int max = 0, index = bigList.get( 0 ), digitNum = 0, tmp;
		for ( int i = 0; i < bigList.size(); i++ )
		{
			// returns 1 if greater, 0 else
			if ( compareNum( num[bigList.get( i )], max ) )
			{
				index = bigList.get( i );
				max = num[bigList.get( i )];
			}
		}
		return index;
	}

	public boolean compareNum( int num1, int num2 )
	{
		String n1, n2;
		n1 = String.valueOf( num1 ) + String.valueOf( num2 );
		n2 = String.valueOf( num2 ) + String.valueOf( num1 );

		if ( n1.compareTo( n2 ) > 0 )
			return true;
		return false;
	}

	public int pow( int n1, int n2 )
	{
		int ret = 1;
		for ( int i = 0; i < n2; i++ )
			ret *= n1;
		return ret;
	}

	public int getDigit( int num )
	{
		int k = 0;
		while ( num > 0 )
		{
			num = num / 10;
			k++;
		}
		return k;
	}

	public int getFirst( int n )
	{
		int k = n;
		while ( k > 9 )
			k = k / 10;
		return k;
	}

	public String toString( int n )
	{
		if ( n == 0 )
			return "0";
		String ret = "";
		int a = n, b;
		while ( a != 0 )
		{
			b = a % 10;
			ret = String.valueOf( b ) + ret;
			a = a / 10;
		}
		return ret;
	}

	Set<String> wordNeighbours( String word, Set<String> set, Set<String> visited )
	{
		Set<String> returnSet = new HashSet<>();
		for ( int i = 0; i < word.length(); i++ )
		{
			char[] cword = word.toCharArray();
			char originalChar = cword[i];
			for ( int j = 0; j < 26; j++ )
			{
				cword[i] = (char) ( 'a' + j );
				String tString = String.valueOf( cword );
				if ( visited.contains( tString ) )
					continue;
				if ( set.contains( tString ) )
					returnSet.add( tString );
			}
			cword[i] = originalChar;
		}
		return returnSet;
	}

	int twowayBFS( String beginWord, String endWord, Set<String> set )
	{
		Set<String> q1 = new HashSet<>( Arrays.asList( new String[] { beginWord } ) ),
				q2 = new HashSet<>( Arrays.asList( new String[] { endWord } ) ),
				visited = new HashSet<>(), qtmp;
		int count = 1;

		// q1 always smaller size
		while ( !q1.isEmpty() )
		{
			if ( q1.size() > q2.size() )
			{
				qtmp = q2;
				q2 = q1;
				q1 = qtmp;
			}
			qtmp = new HashSet<>();
			for ( String word : q1 )
			{
				System.out.println( count );
				visited.add( word );
				for ( int i = 0; i < word.length(); i++ )
				{
					char[] cword = word.toCharArray();
					char originalChar = cword[i];
					for ( int j = 0; j < 26; j++ )
					{
						cword[i] = (char) ( 'a' + j );
						String tString = String.valueOf( cword );
						if ( visited.contains( tString ) )
							continue;

						if ( q2.contains( tString ) )
							return count + 1;
						if ( set.contains( tString ) && !visited.contains( tString ) )
							qtmp.add( tString );
					}
					cword[i] = originalChar;
				}

			}
			q1 = qtmp;
			count++;
		}
		return 0;

	}

	public int numDecodings( String s )
	{
		// if ( s.length() < 1 )
		// return 0;
		if ( s.length() < 2 )
			return 1;
		String s1 = s.substring( 0, 1 ), s2 = s.substring( 0, 2 ), s3 = s.substring( 1 ), s4 = s.substring( 2 );
		System.out.println( s1 );
		System.out.println( s2 );
		System.out.println( s3 );
		System.out.println( s4 );
		return decode( s.substring( 0, 1 ) ) * numDecodings( s.substring( 1 ) ) +
				decode( s.substring( 0, 2 ) ) * numDecodings( s.substring( 2 ) );
	}

	int decode( String s )
	{
		if ( s.length() < 2 )
			return 1;
		if ( s.charAt( 0 ) == '0' )
			return 0;
		if ( s.charAt( 0 ) == '2' && s.charAt( 1 ) > '6' || s.charAt( 0 ) == '0' )
			return 0;
		return 1;
	}
	// public boolean isAdditiveNumber( String num )
	// {
	// for ( int i = 1; i < num.length(); i++ )
	// {
	// int a = Integer.valueOf( num.substring( 0, i ) );
	// int b = Integer.valueOf( num.substring( i, i + 1 ) );
	// }
	//
	// }

	public List<String> findMissingRanges( int[] nums, int lower, int upper )
	{
		List<String> list = new ArrayList<>();
		String gen;
		long[] newNums = new long[nums.length + 2];
		for ( int i = 0; i < nums.length; i++ )
			newNums[i + 1] = nums[i];
		newNums[0] = (long) lower - 1;
		newNums[newNums.length - 1] = (long) upper + 1;
		for ( int i = 0; i < newNums.length - 1; i++ )
		{
			if ( newNums[i] + 1 > newNums[i + 1] - 1 )
				continue;
			list.add( generateMissingRanges( newNums[i] + 1, newNums[i + 1] - 1 ) );
		}
		return list;
	}

	String generateMissingRanges( long a, long b )
	{
		return ( a == b ) ? String.valueOf( a ) : String.format( "%d->%d", a, b );
	}

	public boolean checkSubarraySum( int[] nums, int k )
	{
		if ( nums.length < 2 )
			return false;
		if ( k == 0 )
			return false;
		int[] s = new int[nums.length + 1];
		s[0] = 0;
		Set<Integer> set = new HashSet<>( Arrays.asList( new Integer[] { 0, nums[0] % k } ) );
		for ( int i = 0; i < nums.length; i++ )
		{
			s[i + 1] = s[i] + nums[i];
			if ( s[i + 1] == 0 )
				return true;
			if ( set.contains( s[i + 1] % k ) )
				return true;
			set.add( s[i + 1] % k );
		}
		return false;
	}

	public int trapRainWater( int[][] heightMap )
	{
		int m = heightMap.length, n = heightMap[0].length, amount = 0;
		int[][] l = new int[m][n], r = new int[m][n],
				u = new int[m][n], d = new int[m][n];

		for ( int i = 1; i < m - 1; i++ )
		{
			int maxL = 0, maxR = 0;

			for ( int j = 0; j < n; j++ )
			{
				// contains index
				maxL = heightMap[i][maxL] >= heightMap[i][j] ? maxL : j;
				maxR = maxR >= heightMap[i][n - 1 - j] ? maxR : n - 1 - j;
				l[i][j] = maxL;
				r[i][n - 1 - j] = maxR;
			}
		}

		for ( int j = 1; j < n - 1; j++ )
		{
			int maxU = 0, maxD = 0;

			for ( int i = 0; i < m; i++ )
			{
				maxU = maxU >= heightMap[i][j] ? maxU : i;
				maxD = maxD >= heightMap[m - 1 - i][j] ? maxD : m - 1 - i;
				u[i][j] = maxU;
				d[m - 1 - i][j] = maxD;
			}
		}

		boolean flag = true;
		if ( flag )
		{
			for ( int[] k : l )
				System.out.println( Arrays.toString( k ) );
			System.out.println();
			for ( int[] k : r )
				System.out.println( Arrays.toString( k ) );
			System.out.println();
			for ( int[] k : u )
				System.out.println( Arrays.toString( k ) );
			System.out.println();
			for ( int[] k : d )
				System.out.println( Arrays.toString( k ) );
		}
		boolean[][] visited = new boolean[m][n];

		for ( int i = 1; i < m - 1; i++ )
		{
			for ( int j = 1; j < n - 1; j++ )
			{
				if ( visited[i][j] )
					continue;
			}
		}

		for ( int i = 1; i < m - 1; i++ )
		{
			for ( int j = 1; j < n - 1; j++ )
			{
				int minHeight = Math.min( l[i][j], r[i][j] );
				minHeight = Math.min( u[i][j], minHeight );
				minHeight = Math.min( d[i][j], minHeight );
				if ( flag )
					System.out.println( minHeight - heightMap[i][j] );
				amount += minHeight - heightMap[i][j];
			}
		}
		return amount;
	}

	public List<Integer> findDisappearedNumbers( int[] nums )
	{
		List<Integer> list = new ArrayList<>();
		for ( int i = 0; i < nums.length; i++ )
		{
			int n = Math.abs( nums[i] ) - 1;
			nums[n] = Math.min( nums[n], -nums[n] );
		}
		for ( int i = 0; i < nums.length; i++ )
			if ( nums[i] > 0 )
				list.add( i + 1 );
		return list;

	}

	public int countDigitOne( int n )
	{
		int k = 0, nt = n, ten = 10;
		while ( nt > 0 )
		{
			k++;
			nt /= 10;
		}
		long[] d = new long[k];
		d[0] = 1;
		for ( int i = 1; i < k; i++ )
		{
			d[i] = d[i - 1] * 9 + ten;
			ten *= 10;
		}

		System.out.println( Arrays.toString( d ) );

		return 0;
	}

	public int findKthNumber( int n, int k )
	{
		return findKthNumberHelper( 1, k, n );
	}

	int findKthNumberHelper( int cur, int k, int max )
	{
		if ( k == 1 )
			return cur;
		int i = cur, next = fcount( i, max ), current = 0;
		while ( next < k )
		{
			current = next;
			// if ( next == k )
			// return i;
			next = fcount( ++i, max );
			k -= current;
		}
		if ( k == 1 )
			return i;
		// now k will be in side node i and i+1
		// i * 10 for next lexicographical number
		// k - 1 for skipping node i
		return findKthNumberHelper( i * 10, k - 1, max );
	}

	int fcount( long n, long N )
	{
		if ( n > N )
			return 0;
		if ( n == N )
			return 1;
		if ( n * 10 > N )
			return 1;
		int count = 1;
		n *= 10;
		for ( int i = 0; i < 10; i++ )
			count += fcount( n + i, N );
		return count;
	}

	List<Integer> lexicoGraphicalSort( List<Integer> list, int k )
	{
		if ( list.size() == 0 || k == digit + 1 )
			return new ArrayList<>();
		if ( list.size() < 2 )
			return list;
		Map<Integer, List<Integer>> map = new HashMap<>();
		int n = -1;
		while ( n < 10 )
			map.put( n++, new ArrayList<>() );

		for ( int m : list )
			map.get( kthDigit( m, k ) ).add( m );
		List<Integer> retList = new ArrayList<>();
		for ( int keys : map.keySet() )
			retList.addAll( lexicoGraphicalSort( map.get( keys ), k + 1 ) );

		return retList;
	}

	public int maxPoints( Point[] points )
	{
		if ( points.length < 3 )
			return points.length;

		Arrays.sort( points, ( a, b ) -> a.x - b.x );

		int count = 0, tempConut = 0, base;
		double slope;

		for ( int i = 0; i < points.length; i++ )
		{
			Map<Double, Integer> map = new HashMap<>();
			base = 1;
			tempConut = 0;
			for ( int j = i + 1; j < points.length; j++ )
			{
				if ( points[i].x == points[j].x && points[i].y == points[j].y )
				{
					base += 1;
					continue;
				}
				slope = slope( points[i], points[j] );
				if ( map.containsKey( slope ) )
					map.put( slope, map.get( slope ) + 1 );
				else
					map.put( slope, 1 );
			}

			for ( Iterator<Integer> iterator = map.values().iterator(); iterator.hasNext(); )
				tempConut = Math.max( tempConut, iterator.next() );

			count = Math.max( count, tempConut + base );
		}
		return count;

	}

	double slope( Point p1, Point p2 )
	{
		if ( p1.x == p2.x )
			return Double.NaN;
		return ( p2.y - p1.y ) / (double) ( ( p2.x - p1.x ) );
	}

	public int depthSum( List<NestedInteger> nestedList )
	{
		int count = 0;

		return count;
	}

	int[][] getNB( int i, int j )
	{
		int[][] ret = new int[4][2];
		int[] posi = new int[] { i - 1, i, i + 1, i }, posj = new int[] { j, j + 1, j, j - 1 };
		for ( int k = 0; k < 4; k++ )
		{
			ret[k][0] = posi[k];
			ret[k][1] = posj[k];
		}
		return ret;
	}

	boolean inside( int[] pos, int[][] grid )
	{
		if ( pos[0] < 0 || pos[0] >= grid.length || pos[1] < 0 || pos[1] >= grid[0].length )
			return false;
		return true;
	}

	int checkSide( int[][] grid, int i, int j )
	{
		int count = 0;
		int[][] nb = getNB( i, j );
		for ( int[] next : nb )
		{
			if ( inside( next, grid ) )
				if ( grid[next[0]][next[1]] == 1 )
					count++;
		}
		return 4 - count;
	}

	public int islandPerimeter( int[][] grid )
	{

		int m = grid.length, n = grid[0].length, count = 0;

		Queue<int[]> q = new LinkedList<>(), tmp = null;
		boolean[][] visited = new boolean[m][n];
		for ( int i = 0; i < m && q.isEmpty(); i++ )
			for ( int j = 0; j < n && q.isEmpty(); j++ )
				if ( grid[i][j] == 1 )
				{
					int[] ini = new int[] { i, j };
					q.add( ini );
					tmp = new LinkedList<>( q );
					break;
				}
		while ( !tmp.isEmpty() )
		{
			tmp = new LinkedList<>();
			while ( !q.isEmpty() )
			{
				int[] t = q.poll();
				if ( visited[t[0]][t[1]] )
					continue;
				visited[t[0]][t[1]] = true;
				count += checkSide( grid, t[0], t[1] );
				for ( int[] next : getNB( t[0], t[1] ) )
				{
					if ( !inside( next, grid ) || visited[next[0]][next[1]] || grid[next[0]][next[1]] == 0 )
						continue;
					tmp.add( next );
				}
			}
			q.addAll( tmp );
		}
		return count;
	}

	<T> void fill( T[] a, T[] b )
	{
		for ( int i = 0; i < b.length; i++ )
			a[i] = b[i];
	}

	public TreeNode buildTree( int[] preorder, int[] inorder )
	{
		if ( preorder.length != inorder.length )
			throw new IllegalArgumentException( "Wrong Input!" );
		if ( preorder == null || inorder == null ) // will always happen
			return null;
		if ( preorder.length < 1 || inorder.length < 1 )
			return null;
		if ( preorder.length == 1 && inorder.length == 1 )
			return new TreeNode( preorder[0] );
		TreeNode root = new TreeNode( preorder[0] );
		int rpos = 0;
		while ( inorder[rpos] != preorder[0] )
			rpos++;
		root.left = buildTree( Arrays.copyOfRange( preorder, 1, rpos + 1 ),
				Arrays.copyOfRange( inorder, 0, rpos ) );
		root.right = buildTree( Arrays.copyOfRange( preorder, rpos + 1, preorder.length ),
				Arrays.copyOfRange( inorder, rpos + 1, inorder.length ) );
		return root;
	}

	public List<TreeNode> generateTrees( int n )
	{
		List<Integer> list = new ArrayList<>();
		for ( int i = 1; i <= n; i++ )
			list.add( i );
		return makeTree( list );
	}

	List<TreeNode> makeTree( List<Integer> list )
	{
		List<TreeNode> tList = new ArrayList<>(), left, right;
		if ( list.size() == 0 )
		{
			tList.add( null );
			return tList;
		}
		if ( list.size() == 1 )
		{
			tList.add( new TreeNode( list.get( 0 ) ) );
			return tList;
		}
		for ( int i = 0; i < list.size(); i++ )
		{
			left = makeTree( list.subList( 0, i ) );
			right = makeTree( list.subList( i + 1, list.size() ) );
			for ( int j = 0; j < left.size(); j++ )
			{
				for ( int k = 0; k < right.size(); k++ )
				{
					TreeNode root = new TreeNode( list.get( i ) );
					root.left = left.get( j );
					root.right = right.get( k );
					tList.add( root );
				}
			}

		}
		return tList;
	}

	public List<List<String>> groupAnagrams( String[] strs )
	{
		Map<String, List<String>> map = new HashMap<>();
		for ( String s : strs )
		{
			String ssort = sortString( s );
			if ( map.containsKey( ssort ) )
				map.get( ssort ).add( s );
			else
			{
				List<String> list = new ArrayList<>();
				list.add( s );
				map.put( ssort, list );
			}
		}
		Iterator<Entry<String, List<String>>> iterator = map.entrySet().iterator();
		List<List<String>> retList = new ArrayList<>();
		while ( iterator.hasNext() )
			retList.add( iterator.next().getValue() );
		return retList;

	}

	String sortString( String s )
	{
		String sort;
		char[] c = s.toCharArray();
		Arrays.sort( c );
		sort = String.valueOf( c );
		return sort;
	}

	Integer[] anagram( String s )
	{
		int[] r = new int[26];
		for ( char c : s.toCharArray() )
			r[c - 'a']++;
		Integer[] ret = new Integer[26];
		for ( int i = 0; i < 26; i++ )
			ret[i] = r[i];
		return ret;
	}

	public List<List<Integer>> threeSum_( int[] nums )
	{
		List<List<Integer>> triples = new ArrayList<>();
		Arrays.sort( nums );
		int i = 0, last = nums.length - 1;
		while ( i < last )
		{
			int a = nums[i], j = i + 1, k = last;
			while ( j < k )
			{
				int b = nums[j], c = nums[k], sum = a + b + c;
				if ( sum == 0 )
					triples.add( Arrays.asList( a, b, c ) );
				if ( sum <= 0 )
					while ( nums[j] == b && j < k )
						j++;
				if ( sum >= 0 )
					while ( nums[k] == c && j < k )
						k--;
			}
			while ( nums[i] == a && i < last )
				i++;
		}
		return triples;
	}

	public List<List<Integer>> threeSum( int[] nums )
	{

		List<List<Integer>> ret = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		if ( nums.length < 3 )
			return ret;
		Arrays.sort( nums );
		int size = nums.length, j, k, sum;
		for ( int i = 0; i < size - 2; i++ )
		{
			if ( nums[i] > 0 )
				break;
			if ( set.contains( nums[i] ) )
				continue;
			set.add( nums[i] );
			j = i + 1;
			k = size - 1;
			while ( j < k )
			{
				sum = nums[i] + nums[j] + nums[k];
				if ( sum == 0 )
					ret.add( Arrays.asList( nums[i], nums[j], nums[k] ) );
				if ( sum >= 0 )
					while ( j < k && nums[k] == nums[k - 1] )
						k--;
				if ( sum <= 0 )
					while ( j < k && nums[j] == nums[j + 1] )
						j++;
			}
		}
		return ret;
	}

	public NestedInteger deserialize( String s )
	{
		if ( s.length() == 0 )
			return null;
		// base case : s is single number string only - no '[' involved
		if ( !( s.charAt( 0 ) == '[' ) )
			return new NestedIntegerImplement( Integer.valueOf( s ) );
		// use stack to construct Nested Integer
		NestedInteger ni = new NestedIntegerImplement();
		Stack<NestedInteger> stack = new Stack<>();
		stack.push( ni );
		NestedInteger currentNI = stack.peek();
		List<NestedInteger> currentList = currentNI.getList();
		s = s.substring( 1, s.length() ); // remove first item
		String[] parse = s.split( "," ); // one number per comma
		/* two things to complete:
		    1. if meet '[', push current list to stack, construct new nested integer, and use the new list
		    2. if meet ']', pop current list
		*/

		for ( int i = 0; i < parse.length; i++ )
		{
			String tmpVal = "";
			for ( int j = 0; j < parse[i].length(); j++ )
			{
				if ( parse[i].charAt( j ) == '[' )
				{
					NestedInteger tmp = new NestedIntegerImplement();
					currentNI.add( tmp );
					currentNI = tmp;
					stack.push( currentNI );
					currentList = currentNI.getList();
					continue;
				}
				if ( parse[i].charAt( j ) == ']' )
				{
					if ( tmpVal.length() != 0 )
					{
						currentNI.add( new NestedIntegerImplement( Integer.valueOf( tmpVal ) ) );
						continue;
					}
					if ( stack.size() != 1 )
					{
						stack.pop();
						currentNI = stack.peek();
						currentList = currentNI.getList();
					}
					else
						currentNI = stack.pop();
				}
				tmpVal += parse[i].charAt( j );
			}
			if ( !( parse[i].charAt( parse[i].length() - 1 ) == ']' ) )
			{
				if ( tmpVal.length() != 0 )
					currentNI.add( new NestedIntegerImplement( Integer.valueOf( tmpVal ) ) );
			}
		}
		return currentNI;

	}

	void print()
	{
		String file = "C:\\Users\\Feiteng\\Desktop\\java\\out.csv";
		int step = 50;
		try
		{
			BufferedWriter _writer = new BufferedWriter( new FileWriter( file ) );
			String start = "MoveTo ", ending = "\n LeftClick 1\n";
			int x = 236, y = 206, xend = 636, yend = 706;
			for ( int i = x; i < xend; i += step )
			{
				for ( int j = y; j < yend; j += step )
				{
					_writer.append( start + i + "," + j + ending );
				}
			}
			_writer.close();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

	}

	public String reverseVowels( String s )
	{
		char[] c = s.toCharArray();
		boolean[] pos = new boolean[c.length];
		for ( int i = 0; i < c.length; i++ )
		{
			if ( c[i] == 'a' || c[i] == 'e' || c[i] == 'i' || c[i] == 'o' || c[i] == 'u' )
				pos[i] = true;
		}
		String replace = s.replaceAll( "[^aeiou]", "" );
		char[] vow = new StringBuilder( s.replaceAll( "[^aeiou]", "" ) ).reverse().toString().toCharArray();
		int k = 0;
		for ( int i = 0; i < c.length; i++ )
			if ( pos[i] )
				c[i] = vow[k++];
		return String.valueOf( c );

	}

	public int rangeBitwiseAnd( int m, int n )
	{
		int k = m;
		for ( int i = m; i <= n && i > 0; i++ )
			k &= i;
		System.out.println( k );
		return k;
	}

	public boolean wordPattern( String pattern, String str )
	{
		HashMap<Character, String> m1 = new HashMap<Character, String>();
		HashMap<String, Character> m2 = new HashMap<String, Character>();
		char[] c = pattern.toCharArray();
		String[] s = str.split( " " );
		for ( int i = 0; i < c.length; i++ )
		{
			if ( m1.containsKey( c[i] ) && m1.get( c[i] ) != s[i] )
				if ( m2.containsKey( s[i] ) && m2.get( s[i] ) != c[i] )
					return false;
			if ( !m1.containsKey( c[i] ) )
				m1.put( c[i], s[i] );
			if ( !m2.containsKey( s[i] ) )
				m2.put( s[i], c[i] );
		}
		return true;
	}

	public int getFDigit( int n )
	{
		while ( n > 10 )
			n /= 10;
		return n;
	}

	public int calculate( String s )
	{
		int sum = 0, k;
		Stack<Boolean> sign = new Stack<Boolean>();
		sign.push( false );
		s = s.replaceAll( "\\s", "" );
		char[] c = s.toCharArray();
		// now flip sign in one pass
		for ( int i = 0; i < c.length; i++ )
		{
			// special case if starting with (
			if ( c[i] == '(' )
			{

				// find previous sign, if '-' then flip depending on previous sign
				for ( k = i; k > 0; k-- )
				{
					if ( c[k] == '+' || c[k] == '-' )
						break;
				}
				if ( k == 0 )
				{
					sign.push( false );
				}

				else
				{
					if ( c[k] == '-' )
						sign.push( true );
					else
						sign.push( false );
				}
				c[i] = ' ';
				continue;

			}

			if ( c[i] == ')' )
			{
				if ( !sign.isEmpty() )
					sign.pop();
				c[i] = ' ';
				continue;
			}

			boolean flip = sign.peek();
			if ( c[i] == '+' || c[i] == '-' )
			{
				if ( flip )
				{
					if ( c[i] == '+' )
						c[i] = '-';
					else
						c[i] = '+';
				}
			}

		}
		s = String.valueOf( c );
		// calculate
		System.out.println( s );
		sum = calculateSub( s );
		System.out.println( sum );
		return sum;
	}

	public boolean containsNearbyDuplicate( int[] nums, int k )
	{
		if ( nums.length == 1 )
			return false;
		if ( k == nums.length )
			k--;
		for ( int i = 0; i < Math.max( nums.length - k, 1 ); i++ )
		{
			for ( int j = 1; j <= k; j++ )
			{
				if ( nums[i] == nums[i + j] )
					return true;
			}
		}
		return false;
	}

	public List<Integer> spiralOrder( int[][] matrix )
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int row = matrix.length;
		if ( row == 0 )
			return ret;
		int col = matrix[0].length;
		int n = ( Math.min( row, col ) + 1 ) / 2;
		int i = 0, j = 0;
		for ( int side = 0; side < n; side++ )
		{
			while ( j < col - side )
			{
				ret.add( matrix[i][j] );
				j++;
			}
			j--;
			i++;
			while ( i < row - side )
			{
				ret.add( matrix[i][j] );
				i++;
			}
			i--;
			j--;
			while ( j >= side )
			{
				ret.add( matrix[i][j] );
				j--;
			}
			j++;
			i--;
			while ( i > side )
			{
				ret.add( matrix[i][j] );
				i--;
			}
			i++;
			j++;
		}
		/*
		for ( i = 0; i < ret.size(); i++ )
			System.out.print(ret.get(i) + " ");
		 */
		return ret;

	}

	public static void spiral( int k ) throws FileNotFoundException, UnsupportedEncodingException
	{
		int[][] num = new int[k][k];
		int count = 2;
		int row = 0, col = 0;
		int pos = ( k - 1 ) / 2;
		num[pos][pos] = 1;
		for ( int side = 1; side <= pos; side++ )
		{
			while ( col < side )
			{
				col = col + 1;
				num[pos + row][pos + col] = count;
				count = count + 1;
			}
			while ( row < side && col == side )
			{
				row = row + 1;
				num[pos + row][pos + col] = count;
				count = count + 1;
			}

			while ( row == side && col > -side )
			{
				col = col - 1;
				num[pos + row][pos + col] = count;
				count = count + 1;
			}

			while ( col == -side && row > -side )
			{
				row = row - 1;
				num[pos + row][pos + col] = count;
				count = count + 1;
			}
			while ( row == -side && col < side )
			{
				col = col + 1;
				num[pos + row][pos + col] = count;
				count = count + 1;
			}

		}
		PrintWriter writer = new PrintWriter( "the-file-name.txt", "UTF-8" );
		BigInteger sum = new BigInteger( "0" );
		String str = "";
		for ( int i = 0; i < num.length; i++ )
		{
			sum = sum.add( new BigInteger( String.valueOf( num[i][i] ) ) );
			sum = sum.add( new BigInteger( String.valueOf( num[i][num.length - 1 - i] ) ) );
		}
		System.out.println( sum );
	}

	public static long count1( long n )
	{
		return n * ( n + 1 ) / 2;
	}

	public static int numList( int[] A, int k, int x )
	{
		int[] tmp = new int[k];
		int counter = 0, curMax = 0;
		for ( int i = 0; i < k; i++ )
		{
			tmp[i] = A[i];
			curMax = Math.max( curMax, tmp[i] );
		}
		if ( curMax > x )
			counter++;
		int c = 0;
		for ( int i = k; i < A.length; i++ )
		{
			c = c % k;
			tmp[c] = A[i];
			curMax = Math.max( curMax, tmp[c] );
			c++;
			if ( curMax > x )
				counter++;
		}
		return counter;
	}

	public static int maxAlist( ArrayList<Integer> list )
	{
		int max = 0;
		for ( int x : list )
			max = Math.max( max, x );
		return max;
	}

	public static ArrayList<ArrayList<Integer>> permu( ArrayList<ArrayList<Integer>> list, int n )
	{
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		if ( list.size() == 0 )
		{
			ret.add( new ArrayList<Integer>() );
			tmp = new ArrayList<Integer>();
			tmp.add( n );
			ret.add( tmp );
			return ret;
		}
		ret.addAll( list );
		ArrayList<ArrayList<Integer>> list2 = new ArrayList<ArrayList<Integer>>();
		list2 = clone( list );
		for ( int i = 0; i < list2.size(); i++ )
		{
			list2.get( i ).add( n );
		}
		ret.addAll( list2 );
		return ret;
	}

	public static ArrayList<ArrayList<Integer>> clone( ArrayList<ArrayList<Integer>> list )
	{
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		for ( int i = 0; i < list.size(); i++ )
		{
			ArrayList<Integer> l = new ArrayList<Integer>();
			for ( int j = 0; j < list.get( i ).size(); j++ )
			{
				l.add( list.get( i ).get( j ) );
			}
			ret.add( l );

		}
		return ret;
	}

	public int strStr( String haystack, String needle )
	{
		if ( needle == "" )
			return 0;
		if ( haystack == "" && needle != "" )
			return -1;
		char[] c1 = haystack.toCharArray();
		int len1 = c1.length;
		char[] c2 = needle.toCharArray();
		int len2 = c2.length;
		boolean found = true;
		for ( int i = 0; i < len1 - len2 + 1; i++ )
		{
			if ( c1[i] != c2[0] )
				continue;
			else
			{
				for ( int j = 0; j < len2; j++ )
				{
					if ( c1[i + j] != c2[j] )
						found = false;
				}
				if ( found )
					return i;
			}
			found = true;
		}
		return -1;
	}

	public boolean isBalanced( TreeNode root )
	{
		if ( root == null )
			return true;
		if ( Math.abs( height( root.left ) - height( root.right ) ) > 1 )
			return false;
		boolean l = isBalanced( root.left );
		boolean r = isBalanced( root.right );
		return ( l && r );

	}

	public int height( TreeNode root )
	{
		if ( root == null )
			return 0;
		if ( root.left == null && root.right == null )
			return 1;
		return 1 + Math.max( height( root.left ), height( root.right ) );
	}

	public String longestCommonPrefix( String[] strs )
	{
		if ( strs == null )
			return "";
		int n = strs.length;
		if ( n == 0 )
			return "";
		if ( n == 1 )
			return strs[0];
		char[] base = strs[0].toCharArray();
		char[] tmp;
		int k = 100, i, j, counter = 0;
		boolean flag = true;
		for ( i = 1; i < n; i++ )
		{
			tmp = strs[i].toCharArray();
			for ( j = 0; j < Math.min( tmp.length, base.length ); j++ )
			{
				if ( base[j] == tmp[j] )
					continue;
				else
					break;
			}
			k = Math.min( k, j );
			counter = 0;
		}
		String ret = "";
		for ( i = 0; i < k; i++ )
		{
			ret += base[i];
		}
		return ret;
	}

	public String convert( String s, int nRows )
	{
		if ( nRows == 1 )
			return s;
		String[] ret = new String[nRows];
		for ( int i = 0; i < nRows; i++ )
			ret[i] = "";
		char[] c = s.toCharArray();
		int k = 0, n = nRows - 1;
		;
		boolean up = false, down = true, tmp;
		for ( int i = 0; i < c.length; i++ )
		{
			if ( i % n == 0 )
			{
				tmp = up;
				up = down;
				down = tmp;
			}
			System.out.println( "K=" + k );
			ret[k] += String.valueOf( c[i] );
			if ( up )
				k++;
			if ( down )
				k--;
		}
		String str = "";
		for ( int i = 0; i < nRows; i++ )
			str += ret[i];
		return str;
	}

	public boolean hasPathSumBFS( TreeNode root, int sum )
	{
		if ( root == null )
			return false;
		ArrayList<Integer> l = BFS( root );
		System.out.println();
		for ( int i = 0; i < l.size(); i++ )
			System.out.print( l.get( i ) + " " );
		for ( int i = 0; i < l.size(); i++ )
		{
			if ( l.get( i ) == sum )
				return true;
		}
		return false;
	}

	public ArrayList<Integer> BFS( TreeNode root )
	{
		ArrayList<TreeNode> s = new ArrayList<TreeNode>();
		ArrayList<Integer> ret = new ArrayList<Integer>();
		TreeNode tmp;
		s.add( root );
		while ( !s.isEmpty() )
		{
			tmp = s.get( 0 );
			s.remove( 0 );
			if ( tmp.left != null )
			{
				tmp.left.val += tmp.val;
				s.add( tmp.left );
			}
			if ( tmp.right != null )
			{
				tmp.right.val += tmp.val;
				s.add( tmp.right );
			}
			if ( tmp.left == null && tmp.right == null )
				ret.add( tmp.val );
		}
		return ret;
	}

	public void BFSvisit( TreeNode root )
	{
		ArrayList<TreeNode> s = new ArrayList<TreeNode>();
		ArrayList<Integer> ret = new ArrayList<Integer>();
		TreeNode tmp;
		s.add( root );
		System.out.print( root.val + " " );
		while ( !s.isEmpty() )
		{
			tmp = s.get( 0 );
			s.remove( 0 );
			if ( tmp.left != null )
			{
				System.out.print( tmp.left.val + " " );
				s.add( tmp.left );
			}
			else
				System.out.print( " # " );
			if ( tmp.right != null )
			{
				System.out.print( tmp.right.val + " " );
				s.add( tmp.right );
			}
			else
				System.out.print( " # " );
		}
	}

	public ListNode getIntersectionNode( ListNode headA, ListNode headB )
	{
		// idea: reverse these two lists first, and if they do intersect
		// then they will interest at the last common item (for reversed list)
		// or else no intersection at all
		if ( headA == null || headB == null )
			return null;
		ListNode ha = reverse( headA );
		ListNode hb = reverse( headB );
		// if no matching at the end, then no intersection
		if ( ha.val != hb.val )
			return null;
		while ( ha.next != null && hb.next != null )
		{
			if ( ha.val == hb.val && ha.next.val != hb.next.val )
				return ha;
			ha = ha.next;
			hb = hb.next;
		}
		// one of the list is a single list
		if ( ha.val == hb.val )
		{
			if ( ha.next == null || hb.next == null )
				return ha;
		}
		return null;
	}

	public ListNode reverse( ListNode head )
	{
		ListNode ret = new ListNode( 0 );
		ListNode tmp = head, n;
		while ( tmp.next != null )
		{
			n = ret.next;
			ret.next = new ListNode( tmp.val );
			ret.next.next = n;
			tmp = tmp.next;
		}
		n = ret.next;
		ret.next = new ListNode( tmp.val );
		ret.next.next = n;
		return ret.next;
	}

	public ArrayList<Integer> toList( String s )
	{
		int tmp = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		char[] c = s.toCharArray();
		for ( int i = 0; i < c.length; i++ )
		{
			if ( c[i] == '.' )
			{
				list.add( tmp );
				tmp = 0;
			}
			else
				tmp = tmp * 10 + c[i] - '0';
		}
		list.add( tmp );
		return list;
	}

	public int compareVersion( String version1, String version2 )
	{
		ArrayList<Integer> l1 = toList( version1 );
		ArrayList<Integer> l2 = toList( version2 );
		int len1 = l1.size(), len2 = l2.size();
		if ( len1 > len2 )
		{
			for ( int i = 0; i < len1 - len2; i++ )
				l2.add( 0 );
		}
		else
		{
			for ( int i = 0; i < len2 - len1; i++ )
				l1.add( 0 );
		}
		for ( int i = 0; i < Math.max( len1, len2 ); i++ )
		{
			if ( l1.get( i ) < l2.get( i ) )
				return -1;
			if ( l1.get( i ) > l2.get( i ) )
				return 1;
			if ( l1.get( i ) == l2.get( i ) )
				continue;
		}
		return 0;
	}

	public int majorityElement( int[] num )
	{
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		for ( int i = 0; i < num.length; i++ )
		{
			if ( m.containsKey( num[i] ) )
				m.put( num[i], m.get( num[i] ) + 1 );
			else
				m.put( num[i], 1 );
		}
		TreeMap<Integer, Integer> sortedMap = new TreeMap( m );
		System.out.println( "Sorted HashMap: " + sortedMap );
		return sortedMap.get( 1 );
	}

	public String convertToTitle( int n )
	{
		int a = n, b = n % 26;
		char c[];
		String s = "";

		while ( a != 0 )
		{
			if ( b != 0 )
				c = v2c( b );
			else
				c = v2c( b + 26 );
			s = String.valueOf( c ) + s;
			a = a / 26;
			if ( b == 0 )
				a--;
			b = a % 26;
		}
		return s;
	}

	public char[] v2c( int num )
	{
		return Character.toChars( 64 + num );
	}

	public static int index( int[] n, int val )
	{
		for ( int i = 0; i < n.length; i++ )
			if ( n[i] == val )
				return i;
		return -1;
	}

	public static int nonContingent( int[] A )
	{
		int sum = 0;
		for ( int i = 0; i < A.length; i++ )
		{
			if ( A[i] >= 0 )
				sum += A[i];
		}
		if ( sum == 0 )
			sum = A[0];
		return sum;
	}

	public static int kadane( int[] A )
	{
		int currentmax = 0, historicalmax = 0;
		for ( int i = 0; i < A.length; i++ )
		{
			currentmax = Math.max( 0, currentmax + A[i] );
			historicalmax = Math.max( currentmax, historicalmax );
		}
		if ( historicalmax == 0 )
			historicalmax = A[0];
		return historicalmax;
	}

	public static void closest( int[] A )
	{
		quickSort( A, 0, A.length - 1 );
		int[] diff = new int[A.length - 1];
		int min = 9999;
		for ( int i = 0; i < diff.length; i++ )
		{
			diff[i] = A[i + 1] - A[i];
			if ( min > diff[i] )
				min = diff[i];
		}
		// now min is found
		for ( int i = 0; i < diff.length; i++ )
		{
			if ( diff[i] == min )
				System.out.print( A[i] + " " + A[i + 1] + " " );
		}
	}

	public static void median( int[] A )
	{
		quickSort( A, 0, A.length - 1 );
		if ( A.length % 2 == 0 )
			System.out.println( A[A.length / 2] );
		else
		{
			System.out.println( ( A[A.length / 2] + A[A.length / 2 + 1] ) / 2 );
		}
	}

	public static void quickSort( int[] n, int start, int end )
	{
		if ( start < end )
		{
			int p = partition( n, start, end );
			quickSort( n, start, p - 1 );
			quickSort( n, p + 1, end );
		}

	}

	public double kthLargest( double[] prices, int left, int right, int k )
	{
		// print( prices );
		/*
		 * the idea is similar to quicksort, but will not actually sort the full
		 * array. For detailed algorithm please see:
		 * http://en.wikipedia.org/wiki/Quickselect
		 */
		if ( left == right )
			return prices[left];
		int pivot = ( left + right ) / 2;
		pivot = partition( prices, left, right, k );
		if ( k == pivot )
			return prices[k];
		else if ( k < pivot )
			return kthLargest( prices, left, pivot - 1, k );
		else
			return kthLargest( prices, pivot + 1, right, k );
	}

	/*
	 * this function returns the partitioned index from prices array
	 */
	public int partition( double[] prices, int left, int right, int pivot )
	{
		/*
		 * same idea as partition in quicksort
		 */
		double pvalue = prices[pivot], tmp = prices[right];
		prices[pivot] = tmp;
		prices[right] = pvalue;
		int index = left;
		for ( int i = left; i < right; i++ )
		{
			if ( prices[i] > pvalue )
			{
				tmp = prices[index];
				prices[index] = prices[i];
				prices[i] = tmp;
				index++;
			}
		}
		tmp = prices[right];
		prices[right] = prices[index];
		prices[index] = tmp;
		return index;
	}

	public ListNode rotateRight( ListNode head, int n )
	{
		if ( head == null )
			return null;
		int s = 0;
		ListNode tail = head;
		while ( tail != null )
		{
			tail = tail.next;
			s++;
		}
		n = n % s;
		ListNode ret = new ListNode( 0 );
		ListNode tmp = head;
		for ( int i = 0; i < n; i++ )
			tmp = tmp.next;
		ret.next = tmp.next;
		tmp.next = null;
		tail.next = head;
		return ret.next;
	}

	public ListNode partition( ListNode head, int x )
	{
		ListNode ret = new ListNode( 0 );
		ListNode t = ret;
		ListNode tmp = head;
		while ( tmp != null )
		{
			if ( tmp.val < x )
			{
				t.next = new ListNode( tmp.val );
				t = t.next;
			}
			tmp = tmp.next;
		}
		tmp = head;
		while ( tmp != null )
		{
			if ( tmp.val >= x )
			{
				t.next = new ListNode( tmp.val );
				t = t.next;
			}
			tmp = tmp.next;
		}
		return ret.next;
	}

	// public List<List<Integer>> pathSum( TreeNode root, int sum )
	// {
	// List<List<Integer>> ret;
	// List<List<Integer>> lb = new ArrayList<List<Integer>>();
	// ret = preOrder( root );
	// for ( List<Integer> t : ret )
	// {
	// if ( sumList( t ) == sum )
	// lb.add( t );
	// }
	// return lb;
	// }

	public int sumList( List<Integer> t )
	{
		int s = 0;
		for ( int i : t )
			s += i;
		return s;
	}

	public List<List<Integer>> preOrder( TreeNode root )
	{
		List<Integer> ls = new ArrayList<Integer>();
		List<List<Integer>> lb = new ArrayList<List<Integer>>();
		if ( root == null )
			return lb;
		if ( root.left == null && root.right == null )
		{
			ls.add( root.val );
			lb.add( ls );
			return lb;
		}
		ls.add( root.val );
		System.out.print( "root.left: \n" );
		// printAA(preOrder(root.left));
		if ( root.left != null )
		{
			for ( List<Integer> tmp : preOrder( root.left ) )
			{
				ls.addAll( tmp );
				lb.add( ls );
				ls = new ArrayList<Integer>();
				ls.add( root.val );
			}
		}
		System.out.print( "\nonlyleft lb: \n" );
		printAA( lb );
		if ( root.right != null )
		{
			for ( List<Integer> tmp : preOrder( root.right ) )
			{
				ls.addAll( tmp );
				lb.add( ls );
				ls = new ArrayList<Integer>();
				ls.add( root.val );
			}
		}
		System.out.print( "\nreturned lb: \n" );
		printAA( lb );
		return lb;
	}

	public void printAA( List<List<Integer>> list )
	{
		for ( List<Integer> tmp : list )
		{
			System.out.println();
			System.out.print( "[" );
			for ( int i : tmp )
				System.out.print( i + " " );
			System.out.print( "]" );
		}
	}

	public void printA( List<Integer> list )
	{
		System.out.println();
		for ( int i : list )
			System.out.print( i + " " );
	}

	public int tonum( String s )
	{
		char[] c = s.toCharArray();
		int one = c[1] - '0';
		int ten = ( c[0] - '0' ) * 10;
		return ten + one;
	}

	public int getRow( int[][] num )
	{
		int row = num.length;
		int col = num[0].length;
		int maximum = 0;
		for ( int i = 0; i < row; i++ )
		{
			for ( int j = 0; j < col - 3; j++ )
				maximum = Math.max( maximum, num[i][j] * num[i][j + 1] * num[i][j + 2] * num[i][j + 3] );
		}
		return maximum;
	}

	public int getCol( int[][] num )
	{
		int row = num.length;
		int col = num[0].length;
		int maximum = 0;
		for ( int j = 0; j < col; j++ )
		{
			for ( int i = 0; i < row - 3; i++ )
				maximum = Math.max( maximum, num[i][j] * num[i + 1][j] * num[i + 2][j] * num[i + 3][j] );
		}
		return maximum;
	}

	public int getDiag( int[][] num )
	{
		int row = num.length;
		int col = num[0].length;
		int maximum = 0;
		for ( int i = row - 4; i >= 0; i-- )
		{
			for ( int j = 0; j < col - i; j++ )
				maximum = Math.max( maximum, num[i][j] * num[i + 1][j + 1] * num[i + 2][j + 2] * num[i + 3][j + 3] );
		}
		for ( int j = 1; j < col - 4; j++ )
		{
			for ( int i = 0; i < row - 3; i++ )
				maximum = Math.max( maximum, num[i][j] * num[i + 1][j] * num[i + 2][j] * num[i + 3][j] );
		}
		return maximum;

	}

}
