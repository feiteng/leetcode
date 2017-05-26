

import java.util.Stack;

public class MyQueue
{

	private Stack<Integer> s;

	public MyQueue()
	{
		s = new Stack<Integer>();
	}

	public MyQueue(int[] x)
	{
		s = new Stack<Integer>();
		for ( int i : x )
			s.push(i);
	}

	// Push element x to the back of queue.
	public void push(int x)
	{
		s.push(x);
	}

	// Removes the element from in front of queue.
	public void pop()
	{
		/*/ old
		System.out.print("s: ");
		for ( int i = 0; i < s.size(); i++ )
			System.out.print(s.get(i) + " ");
		System.out.println();
		*/

		Stack<Integer> tmp = new Stack<Integer>();
		while ( !s.isEmpty() )
			tmp.push(s.pop());
		tmp.pop();
		while ( !tmp.isEmpty() )
			s.push(tmp.pop());

		/*/ new 
		System.out.print("s - new: ");
		for ( int i = 0; i < s.size(); i++ )
			System.out.print(s.get(i) + " ");
		System.out.println();
		 */
	}

	// Get the front element.
	public int peek()
	{
		Stack<Integer> tmp = new Stack<Integer>();
		while ( !s.isEmpty() )
			tmp.push(s.pop());
		int ret = tmp.peek();
		while ( !tmp.isEmpty() )
			s.push(tmp.pop());
		return ret;
	}

	// Return whether the queue is empty.
	public boolean empty()
	{
		return s.isEmpty();
	}
}