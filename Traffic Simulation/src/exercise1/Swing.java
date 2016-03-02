package exercise1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Swing extends JPanel {


	private String[] textArray = new String[6]; 
	private String title; 
	private int count = 0; 
	private JLabel label = new JLabel("Count = " + count);
	private JTextField simTime = new JTextField("60"); 
	private JTextField carGenInput = new JTextField("20"); 
	private JTextField driveStyleBlue = new JTextField("1");
	private JTextField driveStylePurple = new JTextField("2");
	private JTextField driveStyleRed = new JTextField("3");
	private JTextField driveStyleYellow = new JTextField("4");
	private JButton button = new JButton("Start Simulation"); 
	//	private JFileChooser fileChooser = new JFileChooser(); 
	private ReadAndWrite readWrite = new ReadAndWrite(); 


	@Override
	public String toString() {
		return "Swing [textArray=" + Arrays.toString(textArray) + "]";
	}


	//when you implement you're working with interfaces. 
	private class ButtonListener implements ActionListener{

		@Override 
		public void actionPerformed(ActionEvent e){
			//count++; 
			//label.setText("count = " + count);
			textArray[0]=simTime.getText();
			textArray[1]=carGenInput.getText();
			textArray[2]=driveStyleBlue.getText();
			textArray[3]=driveStylePurple.getText();
			textArray[4]=driveStyleRed.getText();
			textArray[5]=driveStyleYellow.getText();
			readWrite.setSimTime(Integer.parseInt(textArray[0]));
			readWrite.setCarGenInput(Integer.parseInt(textArray[1]));
			readWrite.setDriveStyleBlue(Integer.parseInt(textArray[2]));
			readWrite.setDriveStylePurple(Integer.parseInt(textArray[3]));
			readWrite.setDriveStyleRed(Integer.parseInt(textArray[4]));
			readWrite.setDriveStyleYellow(Integer.parseInt(textArray[5]));
			readWrite.write(textArray); 
		}
	}

	public String getTextArray() {
		return textArray[0];
	}


	public Swing(String title){
		//this is for the tfirst label otherwise it's black 
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(new JLabel("Simulation Time (in seconds): "));
		add(simTime); 
		this.add(new JLabel("Car Generation (seconds between new cars entering): "));
		add(carGenInput);  
		this.add(new JLabel("Drive Style (blue) 1-Alternate, 2-right, 3-left, 4-straight: "));
		add(driveStyleBlue);  
		this.add(new JLabel("Drive Style (purple) 1-Alternate, 2-right, 3-left, 4-straight: "));
		add(driveStylePurple); 
		this.add(new JLabel("Drive Style (red) 1-Alternate, 2-right, 3-left, 4-straight: "));
		add(driveStyleRed); 
		this.add(new JLabel("Drive Style (yellow) 1-Alternate, 2-right, 3-left, 4-straight: "));
		add(driveStyleYellow); 
		this.title = title; 
		JLabel titleLabel = new JLabel("Start"); 
		add(titleLabel); 
		add(button); 
		titleLabel.setForeground(Color.white);
		button.addActionListener(new ButtonListener());
		add(label); 
		//		text.setPreferredSize(new Dimension 20, 20));
		//		add(fileChooser); 
		label.setForeground(Color.white);
		this.setBackground(new Color(153,204,238));
		this.setPreferredSize(new Dimension(300, 40));
	}

	public static void main(String[] args) {
		//Panel for setting world 
		JFrame frame = new JFrame("Run Configurations"); 
		//		frame.setLocationRelativeTo();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Swing panel = new Swing("Yo Yo"); 
		frame.getContentPane().add(panel);
		frame.setPreferredSize(new Dimension(500,1000));
		//frame.pack() sizes frame to fit the contents
		frame.pack();
		frame.setVisible(true);
		//JFrame is the window; it can have one or more JPanel instances inside it.  JPanel is not the window.

	}
}

