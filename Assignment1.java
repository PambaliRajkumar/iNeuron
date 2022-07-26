
public class Assignment1
{

	public static void main(String[] args)
	{
		System.out.println("Solution 1 :");
		System.out.println();
		int n = 7;
		for (int i = 1; i <= n; i++)
		{
			for (int j = 1; j <= n; j++)// I
			{
				if (i == 1 || i == n || j == (n + 1) / 2)
					System.out.print("* ");
				else
					System.out.print("  ");

			}
			System.out.print(" ");// space
			for (int j = 1; j <= n; j++)// N
			{
				if (j == 1 || j == n || i == j)
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.print(" ");// space
			for (int j = 1; j <= n; j++)// E
			{
				if (i == 1 || i == n || i == (n + 1) / 2 || j == 1)
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.print(" ");// space
			for (int j = 1; j <= n; j++)// U
			{
				if (j == 1 && i != n || j == n && i != n || i == n && j != 1 && j != n)
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.print(" ");// space
			for (int j = 1; j <= n; j++)// R
			{
				if (j == 1 || i == 1 && j != n || i == (n + 1) / 2 && j != n || j == n && i != 1 && i != (n + 1) / 2)
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.print(" ");// space
			for (int j = 1; j <= n; j++)// O
			{
				if ((j == 1 || j == n) && i != 1 && i != n || (i == 1 || i == n) && j != 1 && j != n)
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.print(" ");// space
			for (int j = 1; j <= n; j++)// N
			{
				if (j == 1 || j == n || i == j)
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("Solution 2 :");
		System.out.println();
		
		int m = 4;
		for (int i = 1; i <= m; i++)
		{
			for (int j = 1; j <= m; j++)
			{
				System.out.print(i);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("Solution 3 :");
		System.out.println();
		int p = 15;
		for (int i = 1; i <= p; i++)
		{
			for (int j = 1; j <= p; j++)
			{
				if (i <= (p + 1) / 2 && (j + i <= (p + 3) / 2 || j - i >= (p - 1) / 2) || i == p || j == p || j == 1)
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("Solution 4 :");
		System.out.println();
		int q = 14;
		for (int i = 1; i <= q; i++)
		{
			for (int j = 1; j <= q; j++)
			{
				if (i >= q / 2 && (i - j >= (q - 2) / 2 || i + j >= (3 * q) / 2) || i == q - 1)
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.println();
			
		}
		System.out.println();
		System.out.println("Solution 5 :");
		System.out.println();
		int x = 14;
		for (int i = 1; i <= x; i++)
		{
			for (int j = 1; j <= x; j++)
			{
				if (i == 1 || i == x || i <= x / 2 && i + j <= (x + 2) / 2 || i > x / 2 && i - j >= (x - 2) / 2)
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}

	}

}
