package applications;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUITrackGame {

	private JFrame frmTrackgame;
	private JTextField fileName;

	/**
	 * ±»µ÷ÓÃ
	 */
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITrackGame window = new GUITrackGame();
					window.frmTrackgame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITrackGame window = new GUITrackGame();
					window.frmTrackgame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUITrackGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrackgame = new JFrame();
		frmTrackgame.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		frmTrackgame.setTitle("TrackGame");
		frmTrackgame.setBounds(100, 100, 450, 300);
		//frmTrackgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrackgame.getContentPane().setLayout(null);
		
		JLabel inputFileNameLabel = new JLabel("Input the file name:");
		inputFileNameLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		inputFileNameLabel.setBounds(14, 38, 168, 24);
		frmTrackgame.getContentPane().add(inputFileNameLabel);
		
		fileName = new JTextField();
		fileName.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		fileName.setBounds(187, 38, 202, 24);
		frmTrackgame.getContentPane().add(fileName);
		fileName.setColumns(10);
		
		JLabel chooseLabel = new JLabel("Choose random or sorted");
		chooseLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		chooseLabel.setBounds(14, 96, 202, 18);
		frmTrackgame.getContentPane().add(chooseLabel);
		
		JButton buttonRandom = new JButton("Random");
		buttonRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = fileName.getText();
				GUITrackGameFinal.start(1, str);
			}
		});
		buttonRandom.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		buttonRandom.setBounds(50, 147, 113, 27);
		frmTrackgame.getContentPane().add(buttonRandom);
		
		JButton buttonSorted = new JButton("Sorted");
		buttonSorted.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = fileName.getText();
				GUITrackGameFinal.start(2, str);
			}
		});
		buttonSorted.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		buttonSorted.setBounds(238, 147, 113, 27);
		frmTrackgame.getContentPane().add(buttonSorted);
	}
}
