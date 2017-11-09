import java.util.Stack;

class MaxStack
{
	Stack<Integer> stack = new Stack<>();
	int _max = Integer.MIN_VALUE;

	/** initialize your data structure here. */
	public MaxStack()
	{

	}

	public void push( int x )
	{
		_max = Math.max( _max, x );
		stack.push( x );
	}

	public int pop()
	{
		if ( stack.peek() != _max )
			return stack.pop();
		// update max value
		int m = stack.pop(); // m happens to be max
		_max = Integer.MIN_VALUE;
		for ( int k : stack )
			// update max
			_max = Math.max( _max, k );
		return m;
	}

	public int top()
	{
		return stack.peek();
	}

	public int peekMax()
	{
		return _max;
	}

	public int popMax()
	{
		Stack<Integer> newStack = new Stack<>();
		while ( stack.peek() != _max )
			newStack.add( stack.pop() );
		int m = stack.pop(); // m is max to be popped
		// update max value
		_max = Integer.MIN_VALUE;
		for ( int k : stack )
			_max = Math.max( _max, k );
		while ( !newStack.isEmpty() )
		{
			stack.add( newStack.pop() );
			_max = Math.max( _max, stack.peek() );
		}
		return m;
	}
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */