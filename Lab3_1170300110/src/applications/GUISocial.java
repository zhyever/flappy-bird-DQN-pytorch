package applications;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUISocial {

	private JFrame frmSocialnetwork;
	private JTextField textField;

	public static void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUISocial window = new GUISocial();
					window.frmSocialnetwork.setVisible(true);
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
					GUISocial window = new GUISocial();
					window.frmSocialnetwork.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUISocial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSocialnetwork = new JFrame();
		frmSocialnetwork.setTitle("SocialNetwork");
		frmSocialnetwork.setBounds(100, 100, 450, 300);
		//frmSocialnetwork.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSocialnetwork.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u6587\u4EF6\u540D\uFF1A");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		lblNewLabel.setBounds(58, 62, 109, 18);
		frmSocialnetwork.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		textField.setBounds(192, 59, 170, 24);
		frmSocialnetwork.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = textField.getText();
				
				GUISocialFinal.start(str);
			}
		});
		btnNewButton.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		btnNewButton.setBounds(153, 134, 113, 27);
		frmSocialnetwork.getContentPane().add(btnNewButton);
	}
}
