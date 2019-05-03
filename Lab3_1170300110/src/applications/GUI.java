package applications;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class GUI {

	private JFrame frmSimpleApplication;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmSimpleApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSimpleApplication = new JFrame();
		frmSimpleApplication.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		frmSimpleApplication.setTitle("Simple application");
		frmSimpleApplication.setBounds(100, 100, 450, 300);
		frmSimpleApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSimpleApplication.getContentPane().setLayout(null);
		
		JButton btnTrackgame = new JButton("TrackGame");
		btnTrackgame.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		btnTrackgame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUITrackGame.start();
			}
		});
		btnTrackgame.setBounds(141, 84, 156, 38);
		frmSimpleApplication.getContentPane().add(btnTrackgame);
		
		JButton btnAtomstructure = new JButton("AtomStructure");
		btnAtomstructure.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUIAtom.start(); 
			}
		});
		btnAtomstructure.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		btnAtomstructure.setBounds(141, 135, 156, 38);
		frmSimpleApplication.getContentPane().add(btnAtomstructure);
		
		JButton btnSocialnetwork = new JButton("SocialNetwork");
		btnSocialnetwork.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GUISocial.start();
			}
		});
		btnSocialnetwork.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		btnSocialnetwork.setBounds(141, 189, 156, 38);
		frmSimpleApplication.getContentPane().add(btnSocialnetwork);
		
		JLabel lblChooseTheApp = new JLabel("Choose the app you want to run");
		lblChooseTheApp.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		lblChooseTheApp.setBounds(100, 28, 266, 49);
		frmSimpleApplication.getContentPane().add(lblChooseTheApp);
	}
}
