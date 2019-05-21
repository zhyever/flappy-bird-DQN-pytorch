package applications;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.MyException;
import logging.LogTest;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class GUITrackGame {

	private JFrame frmTrackgame;
	private JTextField fileName;

	/**
	 * 被调用
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
		frmTrackgame.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		frmTrackgame.setTitle("TrackGame");
		frmTrackgame.setBounds(100, 100, 450, 300);
		//frmTrackgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrackgame.getContentPane().setLayout(null);
		
		JLabel inputFileNameLabel = new JLabel("Input the file name:");
		inputFileNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		inputFileNameLabel.setBounds(14, 38, 168, 24);
		frmTrackgame.getContentPane().add(inputFileNameLabel);
		
		fileName = new JTextField();
		fileName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		fileName.setBounds(187, 38, 202, 24);
		frmTrackgame.getContentPane().add(fileName);
		fileName.setColumns(10);
		
		JLabel chooseLabel = new JLabel("Choose random or sorted");
		chooseLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		chooseLabel.setBounds(14, 96, 202, 18);
		frmTrackgame.getContentPane().add(chooseLabel);
		
		JButton buttonRandom = new JButton("Random");
		buttonRandom.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = fileName.getText();
				File f = new File("src/circularOrbit/test/" + str + ".txt");
				TrackGame trackgame = new TrackGameRandom();
				try {
					trackgame.readFile(f);
					GUITrackGameFinal.start(trackgame);
				}catch(IOException e1) {
					//System.out.println("输入文件有误");
					LogTest.logger.error("输入文件不存在");
					JOptionPane.showMessageDialog(frmTrackgame, "输入文件不存在","Error",
			                 JOptionPane.WARNING_MESSAGE);
				}catch(MyException e2) {
					//e2.printInfo();
					LogTest.logger.error(e2.getInfo());
					JOptionPane.showMessageDialog(frmTrackgame, e2.getInfo() ,"Error",
			                 JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		buttonRandom.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		buttonRandom.setBounds(50, 147, 113, 27);
		frmTrackgame.getContentPane().add(buttonRandom);
		
		JButton buttonSorted = new JButton("Sorted");
		buttonSorted.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = fileName.getText();
				File f = new File("src/circularOrbit/test/" + str + ".txt");
				TrackGame trackgame = new TrackGameSorted();
				try {
					trackgame.readFile(f);
					GUITrackGameFinal.start(trackgame);
				}catch(IOException e1) {
					//System.out.println("输入文件有误");
					LogTest.logger.error("输入文件不存在");
					JOptionPane.showMessageDialog(frmTrackgame, "输入文件不存在","Error",
			                 JOptionPane.WARNING_MESSAGE);
				}catch(MyException e2) {
					//e2.printInfo();
					LogTest.logger.error(e2.getInfo());
					JOptionPane.showMessageDialog(frmTrackgame, e2.getInfo() ,"Error",
			                 JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		buttonSorted.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		buttonSorted.setBounds(238, 147, 113, 27);
		frmTrackgame.getContentPane().add(buttonSorted);
	}
}
