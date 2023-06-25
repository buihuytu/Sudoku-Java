package model;

public class Sudoku {
	private int[][] grid;
	private int[][] originnal;
	private int[][] answer;
	
	public Sudoku() {
		super();
	}

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public int[][] getOriginal() {
		return originnal;
	}

	public void setOriginal(int[][] originnal) {
		this.originnal = originnal;
	}

	public int[][] getAnswer() {
		return answer;
	}

	public void setAnswer(int[][] answer) {
		this.answer = answer;
	}
	
}
