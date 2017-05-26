


import java.io.IOException;

public class FileData
{
	private String fileName;

	FileData(String file)
	{
		fileName = file;
	}

	public int[] readInt() throws IOException
	{
		int[] aryLines = null;

		try
		{
			ReadFile file = new ReadFile(fileName);
			aryLines = file.OpenInt();

		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		return aryLines;
	}

	public int[] readTwoLine() throws IOException
	{
		int[] aryLines = null;

		try
		{
			ReadFile file = new ReadFile(fileName);
			aryLines = file.OpenTwoLine();

		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		return aryLines;
	}

	public static void main(String[] args) throws IOException
	{
		String filename = "C:\\Users\\Rutter-Felix\\Desktop\\java\\leetcode\\src\\lc\\t.txt";
		try
		{
			ReadFile file = new ReadFile(filename);
			int[] aryLines = file.OpenInt();
			int i;
			for ( i = 0; i < aryLines.length; i++ )
			{
				System.out.println(aryLines[i]);
			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}
