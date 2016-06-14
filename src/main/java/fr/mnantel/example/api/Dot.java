package fr.mnantel.example.api;

public class Dot
{
	private final int x;
	private final int y;

	public Dot(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString()
	{
		return x + "," + y;
	}

	public static Dot fromString(String value)
	{
		String[] coords = value.split(",");
		int x = Integer.parseInt(coords[0]);
		int y = Integer.parseInt(coords[1]);
		return new Dot(x, y);
	}
}
