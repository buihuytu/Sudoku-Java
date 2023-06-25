package controller;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.SudokuGenerator;
import backend.SudokuIO;
import model.Sudoku;
import view.MasterView;

public class MasterController {
	private MasterView mainView;
	private SudokuController sudoku;
	private Stack<JPanel> stack;
	private int SIZE = 800;
	private String player;	
	public MasterController(String name) {
		super();
		// panel intro
		JPanel intro = new JPanel();
		
		// label name
		JLabel playerName = new JLabel("Player " + name);
		playerName.setFont(new Font(null, Font.ITALIC, 26));
		player = name;	
		intro.add(playerName);
		
		Sudoku example = new Sudoku();
		example = SudokuGenerator.generateSudoku();
		sudoku = new SudokuController(example, this);
		mainView = new MasterView(sudoku.getView());
		mainView.add(BorderLayout.NORTH, intro);
		mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainView.setSize(SIZE, SIZE-100);
		mainView.setResizable(false);
		mainView.setVisible(true);
		mainView.setTitle("Sudoku");
		stack = new Stack<>();
		stack.push(sudoku.getView());
	}
	
	public void back() {
		stack.pop();
		JPanel view = stack.peek();
		mainView.setCurrentView(view);
		updateMainView();
	}

	public void updateMainView() {
		mainView.setSize(SIZE-1, SIZE-1);
	}
	
	public void setCurrentView(Sudoku sudo) {
		sudoku = new SudokuController(sudo, this);
		mainView.setCurrentView(sudoku.getView());
		updateMainView();
		stack.push(sudoku.getView());
	}
	
	public void exit() {
		mainView.dispose();
	}
	
	public String getName() {	
		return player;
	}
}
