package backend;

public class Answer {
	private static boolean vertical(int[][] grid, int col, int val)
	{
		// Kiem tra phan tu co da ton tai trong hang doc chua
		for (int row = 0; row < 9; row++)
			if (grid[row][col] == val)
				return false;
		return true;
	}

	private static boolean horizontal(int[][] grid, int row, int val)
	{
		// Kiem tra phan tu co da ton tai trong hang ngang chua
		for (int col = 0; col < 9; col++)
			if (grid[row][col] == val)
				return false;
		return true;
	}

	private static boolean gridWay(int[][] grid, int i, int j, int val)
	{
		// Kiem tra xem phan tu co ton tai trong ma tran 3*3 khong
		for (int row = 0; row < 3; row++)
			for (int col = 0; col < 3; col++)
				if (grid[row + i/3 * 3][col + j/3 * 3] == val)
					return false;
		return true;
	}

	public static boolean canPut(int[][] grid, int row, int col, int val) {
		return vertical(grid, col, val) && horizontal(grid, row, val) && gridWay(grid, row, col, val);
	}
	
	private static boolean strictVertical(int[][] grid)
	{
		for (int col = 0; col < grid.length; col++)
		{
			int[] count = new int[10];
			for (int row = 0; row < grid[0].length; row++)
			{
				count[grid[row][col]] ++;
			}
			
			for (int i = 1; i <= grid.length; i++)
				if (count[i] != 1)
					return false;
		}
		return true;
	}
	
	private static boolean strictHorizontal(int[][] grid)
	{
		for (int row = 0; row < grid.length; row++)
		{
			int[] count = new int[10];
			for (int col = 0; col < grid[0].length; col++)
			{
				count[grid[row][col]] ++;
			}
			
			for (int i = 1; i <= grid.length; i++)
				if (count[i] != 1)
					return false;
		}
		return true;
	}
	
	private static boolean strictGridWay(int[][] grid)
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				int[] count = new int[10];
				for (int row = 0; row < 3; row++)
				{
					for (int col = 0; col < 3; col++)
					{
						count[grid[row + i * 3][col + j * 3]] ++ ;
					}
				}
				
				for (int k = 1; k <= grid.length; k++)
					if (count[k] != 1)
						return false;
			}
		}
		return true;
	}
	
	public static boolean solved(int[][] grid) {
		return strictVertical(grid) && strictHorizontal(grid) && strictGridWay(grid);
	}
}
