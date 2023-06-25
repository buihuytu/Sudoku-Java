package backend;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import model.Sudoku;

public class SudokuGenerator {
	private static int[][] generateResultGrid(){
		int ans[][] = new int[9][9];
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				int n = (int)(Math.random()*9 + 1);
				HashSet<Integer> count = new HashSet<>();
				while(!Answer.canPut(ans, i, j, n) && count.size() < 9) {
					n = (int)(Math.random()*9 + 1);
					count.add(n);
				}
				if(count.size() == 9) {
					return null;
				}
				ans[i][j] = n;
			}
		}
		return ans;
	}
	
	public static Sudoku generateSudoku() {
		int[][] generate = generateResultGrid();
		while(generate == null) {
			generate = generateResultGrid();
		}
		int[][] question = new int[9][9];
		String[] order = new String[81];
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				order[i*9+j] = i+""+j;
				question[i][j] = generate[i][j];
			}
		}
		List<String> strList = Arrays.asList(order);
		Collections.shuffle(strList);
		strList.toArray(order);
		for(int i = 0; i < strList.size(); i++) {
			int row = strList.get(i).charAt(0) - '0';
			int col = strList.get(i).charAt(1) - '0';
			int val = question[row][col];
			question[row][col] = 0;
			int count = 0;
			for(int num = 1; num < 10; num++) {
				if(Answer.canPut(question, row, col, num)) {
					count++;
				}
			}
			if(count != 1) {
				question[row][col] = val;
			}
		}
		//int[][] generate = SudokuIO.getRandomGrid(); 
		Sudoku answer = new Sudoku();
		answer.setAnswer(generate);
		answer.setOriginal(question);
		return answer;
	}
}
