package firstGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

import controller.MasterController;

public class enterName implements ActionListener {
	public JFrame frame = new JFrame();
	public JButton btn = new JButton();
	public JTextField playerName = new JTextField(15);
	private String name;
	
	
	public enterName() {
		// panel1
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new InsetsUIResource(10, 10, 10, 10);
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.3;
		panel1.add(new JLabel("Name:"), c);
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.weightx = 0.7;
		panel1.add(playerName, c);
		
		// panel2
		JPanel panel2 = new JPanel();
		btn.setText("Start");
		btn.setFocusable(false);
		btn.addActionListener(this);
		panel2.add(btn);
		
		// frame
		frame.add(BorderLayout.CENTER, panel1);
		frame.add(BorderLayout.SOUTH, panel2);
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Enter name");
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btn) {
			name = playerName.getText();
			if(name.equals("") != true) {
				frame.dispose();
				MasterController master = new MasterController(name);

			}
		}
	}
}
