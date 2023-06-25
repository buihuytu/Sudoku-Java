package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.text.BreakIterator;

import javax.swing.JOptionPane;

import backend.Answer;
import backend.SudokuGenerator;
import firstGUI.enterName;
import model.Sudoku;
import view.SudokuView;
import watch.stopWatch;

public class SudokuController implements ActionListener {
	private Sudoku model;
	private MasterController master;
	private SudokuView view;
	private int curCol, curRow; 

	
	public SudokuView getView() {
		return view;
	}

	public void setView(SudokuView view) {
		this.view = view;
	}

	public SudokuController(Sudoku model, MasterController master) {
		this.model = model;
		this.master = master;
		this.setView(new SudokuView(this));
		view.fixGridNumbers(model.getOriginal());
		view.resetColor();
		view.Time().start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		// Click grid to fill number
		if(action.substring(0, 4).equals("Grid")) {
			view.resetColor();
			curRow = action.charAt(4) - '0';
			curCol = action.charAt(5) - '0';
			view.btnChangeColor(Color.LIGHT_GRAY, curRow, curCol);
			System.out.println(curRow + " " + curCol); // in ra vị trí bấm chuột 
			if(view.getGridAt(curRow, curCol) != 0) {
				view.changeColor(Color.cyan, view.getGridAt(curRow, curCol));	
				if(view.textColor(curRow, curCol) == Color.ORANGE) {	
					view.btnChangeColor(Color.RED, curRow, curCol);
					
				}
			}
		}
		
		// Click number to fill 
		else if(curRow != -1 && curCol != -1 && action.substring(0, 3).equals("Num")) {
			view.resetColor();
			int val = action.charAt(4) - '0';
			int[][] check = view.getGridNumbers();
			if(val != 0 && view.textColor(curRow, curCol) != Color.blue) { // 
				view.setGridAt(curRow, curCol, "" + val);
				view.changeColor(Color.cyan, val);	
				if(Answer.canPut(check, curRow, curCol, val) == true) {
					view.btnChangeColor(Color.green, curRow, curCol);
					view.changeTextColor(Color.BLACK, curRow, curCol);
				}
				else {
					view.btnChangeColor(Color.RED, curRow, curCol);
					view.changeTextColor(Color.ORANGE, curRow, curCol);
				}
			}
			if(val == 0) {
				view.setGridAt(curRow, curCol, "");
			}
		}
		
		// Click button Check
		else if(action.equals("Check")) {
			int[][] check = view.getGridNumbers();
			if(Answer.solved(check)) {
				// In ra console
				System.out.println("Player name: " + master.getName() + " played in " + view.Time().stop());
				// Lưu vào file
				String value1 = master.getName();
				var value2 = view.Time().stop();
				String outputStr = "In4 nekk: \n" + "Name: " + value1 + "\n" + "Score: " + value2;
				try {
					FileWriter fw = new FileWriter("D:\\Subjects_ki2Nam2\\JavaCore\\BaiTapLon_game\\in4Winner.txt");
					fw.write(outputStr);
					fw.close();
				} catch (Exception ex) {
					System.out.println(ex);
				}
				JOptionPane.showMessageDialog(view, "It is Correct!");
				int option = JOptionPane.showConfirmDialog(view, "Would you like to Continue?", "Continue?", JOptionPane.YES_NO_OPTION);
				if(option == 0) {
					model = SudokuGenerator.generateSudoku();
					view.resetAll();
					view.fixGridNumbers(model.getOriginal());
					view.Time().reset();
					view.Time().start();
				}
			}
			else {
				JOptionPane.showMessageDialog(view, "It is Wrong");
			}
		}
		
		// Click button New
		else if(action.equals("Refresh")) {
			int option = JOptionPane.showConfirmDialog(view, "Would you like to play new question?", "New", JOptionPane.YES_NO_OPTION);
			if(option == 0) {
				model = SudokuGenerator.generateSudoku();
				view.resetAll();
				view.fixGridNumbers(model.getOriginal());
				view.Time().reset();
				view.Time().start();
			}
		}
		
		// Click button Remove
		else if(action.equals("Remove")) {
			view.changeColor(Color.WHITE, view.getGridAt(curRow, curCol));
			view.setGridAt(curRow, curCol, "");
			view.resetColorAt(curRow, curCol);
		}
		
		// Click button exit
		else if(action.equals("Exit")) {
			int option = JOptionPane.showConfirmDialog(view, "Would you like to exit?", "Exit", JOptionPane.YES_NO_OPTION);
			if(option == 0) {
				master.exit();				
			}
		}
		
		// Click button Pause
		else if(action.equals("Pause")) {
			view.Time().stop();
			int option = JOptionPane.showConfirmDialog(view, "Do you want to continue?", "Continue", JOptionPane.YES_NO_OPTION);
			if(option == 0) {
				view.Time().start();
			}
		}
		
	}
}
