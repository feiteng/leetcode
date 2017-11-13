import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Excel
{
	Cell[][] cell;

	class Cell
	{
		int _row, _col, _val;
		// prev for formula to this cell
		// next for formula from this cell
		Map<Cell, Integer> _prev = new HashMap<>(),
				_next = new HashMap<>();

		Cell( int row, int col, int val )
		{
			_row = row;
			_col = col;
			_val = val;
		}
	}

	public Excel( int H, char W )
	{
		int col = W - 'A' + 1;
		cell = new Cell[H + 1][col + 1];
		for ( int i = 1; i < H + 1; i++ )
		{
			for ( int j = 1; j < col + 1; j++ )
			{
				cell[i][j] = new Cell( i, j, 0 );
			}

		}
	}

	public void set( int r, char c, int v )
	{
		// set will remove any formula to this cell
		// and updates any formula from this cell

		int originalVal = 0;
		Cell currCell = cell[r][c - 'A' + 1];

		originalVal = currCell._val;

		// remove formulas to this cell
		for ( Cell p : currCell._prev.keySet() )
			p._next.remove( currCell );

		currCell._prev = new HashMap<>();

		// updates formulas from this cell

		currCell._val = v;
		Queue<Cell> queue = new LinkedList<>();
		queue.add( currCell );
		// do bfs updates
		while ( !queue.isEmpty() )
		{
			Cell tmp = queue.poll();
			for ( Cell nextCell : tmp._next.keySet() )
			{
				nextCell._val += ( v - originalVal ) * tmp._next.get( nextCell );
				queue.add( nextCell );
			}
		}

	}

	public int get( int r, char c )
	{
		return cell[r][c - 'A' + 1]._val;
	}

	public int sum( int r, char c, String[] strs )
	{
		// strs are all in format {[A-Z][1-26]}
		// updates prev and next for involved cells
		Cell to = cell[r][c - 'A' + 1];
		int re = 0;
		for ( String string : strs )
		{
			for ( Cell from : stringToListCell( string ) )
			{
				// update prev
				if ( !to._prev.containsKey( from ) )
					to._prev.put( from, 0 );
				to._prev.put( from, to._prev.get( from ) + 1 );

				// update next
				if ( !from._next.containsKey( to ) )
					from._next.put( to, 0 );
				from._next.put( to, from._next.get( to ) + 1 );

				re += from._val;
			}
		}
		to._val = re;
		return re;
	}

	List<Cell> stringToListCell( String s )
	{
		List<Cell> re = new ArrayList<>();
		if ( s.indexOf( ":" ) < 0 )
		{
			int[] pos = StringToRC( s );
			re.add( cell[pos[0]][pos[1]] );
			return re;
		}
		int index = s.indexOf( ":" );
		String sub1 = s.substring( 0, index ), sub2 = s.substring( index + 1 );
		int[] pos1 = StringToRC( sub1 ), pos2 = StringToRC( sub2 );
		int row1 = pos1[0], col1 = pos1[1], row2 = pos2[0], col2 = pos2[1];
		for ( int i = row1; i <= row2; i++ )
		{
			for ( int j = col1; j <= col2; j++ )
			{
				re.add( cell[i][j] );
			}
		}
		return re;
	}

	int[] StringToRC( String s )
	{
		int row = Integer.valueOf( s.substring( 1 ) ), col = s.charAt( 0 ) - 'A' + 1;
		return new int[] { row, col };
	}

	public static void main( String[] args )
	{
		String[] ops = { "Excel", "set", "set" };
		String[][] vals = {
				{ "3", "C" },
				{ "1", "A", "2" },
				{ "2", "B", "2" } };

		Excel e = new Excel( Integer.valueOf( vals[0][0] ), vals[0][1].charAt( 0 ) );

		for ( int i = 1; i < ops.length; i++ )
		{
			if ( i == 2 )
			{
				System.out.println(
						e.sum( 3, 'C', new String[] { "A1", "A1:B2" } ) );
			}
			if ( ops[i].equals( "get" ) )
				System.out.println( e.get( Integer.valueOf( vals[i][0] ), vals[i][1].charAt( 0 ) ) );
			else if ( ops[i].equals( "set" ) )
			{
				e.set( Integer.valueOf( vals[i][0] ), vals[i][1].charAt( 0 ),
						Integer.valueOf( vals[i][2] ) );
				System.out.println( "null" );
			}
		}
	}

}

/**
 * Your Excel object will be instantiated and called as such:
 * Excel obj = new Excel(H, W);
 * obj.set(r,c,v);
 * int param_2 = obj.get(r,c);
 * int param_3 = obj.sum(r,c,strs);
 */