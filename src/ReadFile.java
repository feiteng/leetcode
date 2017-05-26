


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile
{
	private String path;

	public ReadFile(String file_path)
	{
		path = file_path;
	}

	public String[] OpenFile() throws IOException
	{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];
		int i;

		for ( i = 0; i < numberOfLines; i++ )
			textData[i] = textReader.readLine();
		textReader.close();
		return textData;
	}

	public int[] OpenTwoLine() throws IOException
	{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		int _a_size = Integer.parseInt(textReader.readLine());
		int[] _a = new int[_a_size];
		int _a_item;
		String next = textReader.readLine();
		String[] next_split = next.split(" ");

		for ( int _a_i = 0; _a_i < _a_size; _a_i++ )
		{
			_a_item = Integer.parseInt(next_split[_a_i]);
			_a[_a_i] = _a_item;
		}
		return _a;
	}

	public int[] OpenInt() throws IOException
	{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);

		int numberOfLines = readLines();
		int[] textData = new int[numberOfLines];
		int i;

		for ( i = 0; i < numberOfLines; i++ )
			textData[i] = Integer.parseInt(textReader.readLine());
		textReader.close();
		return textData;
	}

	public int readLines() throws IOException
	{
		FileReader fr = new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		String aLine;
		int numberOfLines = 0;
		while ( (aLine = textReader.readLine()) != null )
			numberOfLines++;
		textReader.close();
		return numberOfLines;
	}
}
