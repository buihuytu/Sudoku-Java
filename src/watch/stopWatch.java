package watch;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class stopWatch {
	JFrame frame = new JFrame();
	JLabel timeLabel = new JLabel();
	JPanel panelTime = new JPanel();
	
	int elapsedTime = 0;
	int seconds = 0;
	int minutes = 0;
	int hours = 0;
	int resultSeconds = 0, resultMinutes = 0, resultHours = 0;
	boolean started = false;
	String strSeconds = String.format("%02d", seconds);
	String strMinutes = String.format("%02d", minutes);
	String strHours = String.format("%02d", hours);

	Timer timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			elapsedTime = elapsedTime+1000;
			hours = (elapsedTime/3600000);
			minutes = (elapsedTime/60000) % 60;
			seconds = (elapsedTime/1000) % 60;
			strSeconds = String.format("%02d", seconds);
			strMinutes = String.format("%02d", minutes);
			strHours = String.format("%02d", hours);
			timeLabel.setText(strHours + ":" + strMinutes + ":" + strSeconds);
		}
	});

	public stopWatch(){
		timer.start();
	}

	public JPanel createStopWatch() {
		timeLabel.setText(strHours + ":" + strMinutes + ":" + strSeconds);
		timeLabel.setBounds(100, 100, 200, 100);
		timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
		panelTime.add(timeLabel);
		return panelTime;
	}
	
	public String setDisplay() {
		return strHours + ":" + strMinutes + ":" + strSeconds;
	}
	
	public void start() {
		timer.start();
	}
	
	public String stop() {
		timer.stop();
		resultSeconds = seconds;
		resultMinutes = minutes;
		resultHours = hours;
		return (resultHours + " : " + resultMinutes + " : " + resultSeconds).toString();
	}
	
	public void reset() {
		timer.stop();
		elapsedTime = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		strSeconds = String.format("%02d", seconds);
		strMinutes = String.format("%02d", minutes);
		strHours = String.format("%02d", hours);
		timeLabel.setText(strHours + ":" + strMinutes + ":" + strSeconds);
	}
}
