package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MasterView extends JFrame {
	private JPanel currentView;

	public MasterView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MasterView(JPanel view) {
		super();
		this.currentView = view;
		add(currentView);
	}
	
	public void setCurrentView(JPanel view) {
		remove(currentView);
		currentView = view;
		add(currentView);
	}
	
	public JPanel getCurrentView() {
		return currentView;
	}
	
	
}
