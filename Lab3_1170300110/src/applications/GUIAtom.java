package applications;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class GUIAtom {

	private JFrame frmAtomstructure;
	private JTextField textField;
	
	//±»µ÷ÓÃ
	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAtom window = new GUIAtom();
					window.frmAtomstructure.setVisible(true);
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
					GUIAtom window = new GUIAtom();
					window.frmAtomstructure.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIAtom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAtomstructure = new JFrame();
		frmAtomstructure.setTitle("AtomStructure");
		frmAtomstructure.setBounds(100, 100, 450, 300);
		//frmAtomstructure.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAtomstructure.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8F93\u5165\u6587\u4EF6\u540D\uFF1A");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		label.setBounds(93, 59, 101, 18);
		frmAtomstructure.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		textField.setBounds(208, 56, 174, 24);
		frmAtomstructure.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIAtomFinal.start(textField.getText());
			}
		});
		btnNewButton.setBounds(145, 115, 113, 27);
		frmAtomstructure.getContentPane().add(btnNewButton);
	}
}
