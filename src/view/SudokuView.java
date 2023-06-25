package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import controller.MasterController;
import firstGUI.enterName;
import watch.stopWatch;

public class SudokuView extends JPanel {
	private JButton[][] grids;
	private JButton[] res;
	private stopWatch time;
	private JButton btnPause;
	
	public SudokuView(ActionListener controller) {
		setLayout(new BorderLayout());
		// Created the Sudoku grid
		JPanel sudoGrids = new JPanel();
		sudoGrids.setLayout(new GridLayout(9, 9));
		sudoGrids.setBorder(BorderFactory.createTitledBorder(""));

		grids = new JButton[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				JButton grid = new JButton();
				int[] b = new int[] { 1, 1, 1, 1 };
				if (i == 0)
					b[0] = 0;
				if (j == 0)
					b[1] = 0;
				if (i == 8)
					b[2] = 0;
				if (j == 8)
					b[3] = 0;
				if (i == 3 || i == 6)
					b[0] = 3;
				if (j == 3 || j == 6)
					b[1] = 3;
				if (i == 2 || i == 5)
					b[2] = 3;
				if (j == 2 || j == 5)
					b[3] = 3;
				grid.setBorder(BorderFactory.createMatteBorder(b[0], b[1], b[2], b[3], Color.black));
				grid.setActionCommand("Grid" + i + j);
				grid.addActionListener(controller);
				grid.setPreferredSize(new Dimension(10, 10));
				sudoGrids.add(grid);
				grids[i][j] = grid;
				grids[i][j].setFont(new Font(grids[i][j].getFont().getFontName(), Font.PLAIN, 22));
				grids[i][j].setFocusable(false);
			}
		}
		add(sudoGrids, BorderLayout.CENTER);

		// Created number answer gird
		res = new JButton[10];
		JPanel numGrids = new JPanel();
		numGrids.setLayout(new GridLayout(3, 3));
		numGrids.setBorder(BorderFactory.createTitledBorder(""));
		for (int i = 0; i <= 9; i++) {
			JButton numBtn = new JButton("" + i);
			if(i != 0) {
				numBtn.addActionListener(controller);
				numBtn.setMnemonic(KeyEvent.VK_0 + i);
				numBtn.setActionCommand("Num " + i);
				numBtn.setPreferredSize(new Dimension(50, 50));
				numBtn.setFocusable(false);
				numGrids.add(numBtn);
				res[i] = numBtn;
			}
		}
		
		
		add(numGrids, BorderLayout.EAST);
		
		// Created stopWatch
		time = new stopWatch();
//		time.start();
		add(time.createStopWatch(), BorderLayout.NORTH);

		// Button
		// Check
		JPanel panelBtn = new JPanel();
		JButton btnCheck = new JButton();
		btnCheck.setText("Check");
		btnCheck.addActionListener(controller);
		btnCheck.setActionCommand("Check");
		btnCheck.setFocusable(false);
		panelBtn.add(btnCheck);

		// Refresh
		JButton btnRefresh = new JButton();
		btnRefresh.setText("Refresh");
		btnRefresh.addActionListener(controller);
		btnRefresh.setActionCommand("Refresh");
		btnRefresh.setFocusable(false);
		panelBtn.add(btnRefresh);
		
		// Remove
		JButton btnRemove = new JButton();
		btnRemove.setText("Remove");
		btnRemove.addActionListener(controller);
		btnRemove.setActionCommand("Remove");
		btnRemove.setFocusable(false);
		panelBtn.add(btnRemove);
		
		// Pause
		btnPause = new JButton();
		btnPause.setText("Pause");
		btnPause.addActionListener(controller);
		btnPause.setActionCommand("Pause");
		btnPause.setFocusable(false);
		panelBtn.add(btnPause);
		
		// Exit
		JButton btnExit = new JButton();
		btnExit.setText("Exit");
		btnExit.addActionListener(controller);
		btnExit.setActionCommand("Exit");
		btnExit.setFocusable(false);
		panelBtn.add(btnExit);

		add(panelBtn, BorderLayout.SOUTH);
	}

	public stopWatch Time() {
		return time;
	}
	
	public int[][] getGridNumbers(){
		int[][] ans = new int[9][9];
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				try {
					ans[i][j] = Integer.parseInt(grids[i][j].getText());
				}
				catch(Exception e) {
					ans[i][j] = 0;
				}
			}
		}
		return ans;
	}
	
	public void setGridNumber(int[][] grid) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(grid[i][j] != 0) {
					grids[i][j].setText("" + grid[i][j]);
				}
				else {
					grids[i][j].setText("");
				}
			}
		}
	}
	
	public void fixGridNumbers(int[][] grid) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grid[i][j] != 0) {
					grids[i][j].setEnabled(true);	//
					grids[i][j].setText("" + grid[i][j]);
					grids[i][j].setFont(new Font(grids[i][j].getFont().getFontName(), Font.PLAIN, 22));
					grids[i][j].setForeground(Color.blue);
				}
				else {
					grids[i][j].setText("");
				}
			}
		}
	}
	
	public int getGridAt(int row, int col) {
		String val = grids[row][col].getText();
		try {
			return Integer.parseInt(val);
		}
		catch (Exception e){
			return 0;
		}
	}

	public void setGridAt(int row, int col, String val) {
		grids[row][col].setText(val);
	}
	
	public void changeColor(Color color, int val) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (grids[i][j].getText().equals("" + val)) {
					grids[i][j].setBackground(color);
				}
			}
		}
	}

	public void btnChangeColor(Color color, int row, int col) {
		grids[row][col].setBackground(color);
	}
	
	public void changeTextColor(Color color, int row, int col) {
		grids[row][col].setForeground(color);
	}

	public Color textColor(int row, int col) {
		return grids[row][col].getForeground();
	}
	


	public void resetColorAt(int row, int col){
		grids[row][col].setBackground(Color.white);
	}
	
	public void resetColor() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grids[i][j].setBackground(Color.white);
			}
		}
	}


	public void resetAll() {
		resetColor();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				grids[i][j].setEnabled(true);
				grids[i][j].setText("");
				grids[i][j].setFont(new JButton().getFont());
			}
		}
	}
	

}
