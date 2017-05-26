
import java.util.Comparator;
import java.util.Map;

class HashMapSort implements Comparator
{
	public int compare( Object o1, Object o2 )
	{
		return ( (Comparable) ( (Map.Entry) ( o2 ) ).getValue() ).compareTo( ( (Map.Entry) ( o1 ) ).getValue() );
	}

}

class Envelope
{
	int _w, _h;

	public Envelope( int w, int h )
	{
		_w = w;
		_h = h;
	}

	public String Member()
	{
		return "" + _w + " " + _h;
	}

}

class EnvelopeSort implements Comparator
{

	public int compare( Object e1, Object e2 )
	{
		int r1 = Integer.compare( ( (Envelope) ( e1 ) )._w, ( (Envelope) ( e2 ) )._w ),
				r2 = Integer.compare( ( (Envelope) ( e1 ) )._h, ( (Envelope) ( e2 ) )._h );
		return r1 != 0 ? r1 : -r2;
	}
}