package applications;

import circularOrbit.label;
import exception.MyException;
import logging.LogTest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.TextArea;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import APIs.CircularOrbitAPIs;
import APIs.PanelTest;
import centralObject.Person;
import physicalObject.People;
import track.Track;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUISocialFinal {

	private JFrame frame;
	private JTextField compute;
	private JTextField from;
	private JTextField to;
	private JTextField number;
	private JTextField fromDel;
	private JTextField toDel;
	private JTextField name;
	private JTextField age;
	private JTextField gender;
	
	private static SocialNetworkCircle client = new SocialNetworkCircle();
	CircularOrbitAPIs<Person, People> api = new CircularOrbitAPIs<Person, People>();
	private JTextField centralText;
	private JTextField name1;
	private JTextField name2;
	
	public static void start(SocialNetworkCircle social) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

//				File f = new File("src/circularOrbit/test/" + str + ".txt");
//				try {
//					client.readFile(f);
//				} catch (IOException | MyException e) {
//					// can't get here
//					e.printStackTrace();
//				}
				
				client = social;
				GUISocialFinal window = new GUISocialFinal();
				window.frame.setVisible(true);
		
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
					File f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
					client.readFile(f);
					
					GUISocialFinal window = new GUISocialFinal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUISocialFinal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 587, 780);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		TextArea textArea_1 = new TextArea();
		textArea_1.setEditable(false);
		textArea_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		textArea_1.setBounds(33, 482, 500, 57);
		frame.getContentPane().add(textArea_1);
		textArea_1.setText(client.GUIremoveObjects());
		
		JLabel label = new JLabel("\u793E\u4F1A\u5173\u7CFB\uFF1A");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		label.setBounds(10, 545, 233, 18);
		frame.getContentPane().add(label);
		
		JLabel central = new JLabel("\u4E2D\u5FC3\uFF1A");
		central.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		central.setBounds(309, 223, 72, 18);
		frame.getContentPane().add(central);
		
		centralText = new JTextField();
		centralText.setEditable(false);
		centralText.setBounds(384, 221, 86, 24);
		frame.getContentPane().add(centralText);
		centralText.setColumns(10);
		centralText.setText(client.central.getName().toString());
		
		TextArea textArea_2 = new TextArea();
		textArea_2.setEditable(false);
		textArea_2.setText("");
		textArea_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		textArea_2.setBounds(33, 569, 500, 154);
		frame.getContentPane().add(textArea_2);
		textArea_2.setText(client.GUIties());
		
		JLabel lblNewLabel_4 = new JLabel("\u672A\u52A0\u5165\u8F68\u9053\u7CFB\u7EDF\u7684\u4EBA\u5458\uFF1A");
		lblNewLabel_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(10, 458, 233, 18);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel label_1 = new JLabel("\u8BA1\u7B97\u4E24\u4EBA\u4E4B\u95F4\u903B\u8F91\u8DDD\u79BB\uFF1A");
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		label_1.setBounds(10, 259, 186, 18);
		frame.getContentPane().add(label_1);
		
		name1 = new JTextField();
		name1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		name1.setColumns(10);
		name1.setBounds(194, 257, 101, 24);
		frame.getContentPane().add(name1);
		
		name2 = new JTextField();
		name2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		name2.setColumns(10);
		name2.setBounds(309, 257, 101, 24);
		frame.getContentPane().add(name2);
		
		name = new JTextField();
		name.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		name.setColumns(10);
		name.setBounds(10, 427, 86, 24);
		frame.getContentPane().add(name);
		
		age = new JTextField();
		age.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		age.setColumns(10);
		age.setBounds(110, 427, 86, 24);
		frame.getContentPane().add(age);
		
		gender = new JTextField();
		gender.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		gender.setColumns(10);
		gender.setBounds(209, 427, 86, 24);
		frame.getContentPane().add(gender);
		
		JLabel lblNewLabel = new JLabel("\u8BA1\u7B97\u67D0\u4EBA\u4FE1\u606F\u6269\u6563\u5EA6\uFF1A");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 290, 150, 18);
		frame.getContentPane().add(lblNewLabel);
		
		compute = new JTextField();
		compute.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		compute.setBounds(194, 287, 101, 24);
		frame.getContentPane().add(compute);
		compute.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u589E\u52A0\u4E00\u6761\u793E\u4F1A\u5173\u7CFB\uFF1A");
		lblNewLabel_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 321, 150, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		from = new JTextField();
		from.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		from.setBounds(157, 318, 86, 24);
		frame.getContentPane().add(from);
		from.setColumns(10);
		
		to = new JTextField();
		to.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		to.setBounds(257, 318, 86, 24);
		frame.getContentPane().add(to);
		to.setColumns(10);
		
		number = new JTextField();
		number.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		number.setBounds(357, 318, 86, 24);
		frame.getContentPane().add(number);
		number.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u59D3\u540D                  \u59D3\u540D                  \u4EB2\u5BC6\u5EA6");
		lblNewLabel_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(157, 341, 286, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u5220\u9664\u4E00\u6761\u793E\u4F1A\u5173\u7CFB\uFF1A");
		lblNewLabel_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 365, 136, 18);
		frame.getContentPane().add(lblNewLabel_3);
		
		fromDel = new JTextField();
		fromDel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		fromDel.setColumns(10);
		fromDel.setBounds(157, 362, 86, 24);
		frame.getContentPane().add(fromDel);
		
		toDel = new JTextField();
		toDel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		toDel.setColumns(10);
		toDel.setBounds(257, 362, 86, 24);
		frame.getContentPane().add(toDel);
		
		JLabel label_2 = new JLabel("\u65B0\u589E\u67D0\u4EBA\uFF1A\uFF08\u59D3\u540D\u3001\u5E74\u9F84\u3001\u6027\u522B\uFF09");
		label_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		label_2.setBounds(10, 396, 311, 18);
		frame.getContentPane().add(label_2);
		
		TextArea textArea = new TextArea();
		textArea.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		textArea.setBounds(10, 10, 554, 190);
		frame.getContentPane().add(textArea);
		textArea.setText(client.GUIshowResult());
		
		JButton buttonGraph = new JButton("\u56FE\u5F62\u5316\u663E\u793A");
		buttonGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PanelTest.start(client);
			}
		});
		buttonGraph.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		buttonGraph.setBounds(14, 219, 113, 27);
		frame.getContentPane().add(buttonGraph);
		
		JButton buttonCompute = new JButton("\u8BA1\u7B97\u71B5");
		buttonCompute.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				double num = api.getObjectDistributionEntropy(client);
				textArea.setText(client.GUIshowResult() + String.valueOf(num));
			}
		});
		buttonCompute.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		buttonCompute.setBounds(157, 219, 113, 27);
		frame.getContentPane().add(buttonCompute);
		
		
		
		JButton buttonComp = new JButton("\u786E\u5B9A");
		buttonComp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = compute.getText();
				label nameLabel = new label(name);
				boolean flag = false;
				
				Iterator<Map.Entry<Track, ArrayList<People>>> iterator1 = client.rel.entrySet().iterator();
				while(iterator1.hasNext()) {
					Entry<Track, ArrayList<People>> it = iterator1.next();
					if(it.getKey().getNum() == 1) {
						for(int i = 0; i < it.getValue().size(); i++) {
							if(it.getValue().get(i).getName().equals(nameLabel)) {
								flag = true;
							}
						}
					}
				}
				
				if(flag) {
					JOptionPane.showMessageDialog(frame, "Success","information",
							JOptionPane.INFORMATION_MESSAGE);
					double number = client.getCloseLevel(nameLabel);
					textArea.setText(client.GUIshowResult() + String.valueOf(number));
					compute.setText("");
				}else {
					LogTest.logger.error("Ç×ÃÜ¶ÈÊäÈë´íÎó");
					JOptionPane.showMessageDialog(frame, "Input Error","Error",
			                 JOptionPane.WARNING_MESSAGE);
					compute.setText("");
				}
				
			}
		});
		buttonComp.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		buttonComp.setBounds(309, 286, 113, 27);
		frame.getContentPane().add(buttonComp);
		
		
		JButton buttonAddTie = new JButton("\u786E\u5B9A");
		buttonAddTie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label name1 = new label(from.getText());
				label name2 = new label(to.getText());
				
				double num = Double.parseDouble(number.getText());
				//Ô­À´²»´æÔÚÕâ¸ötie±êÖ¾
				boolean flag = true;
				
				for(int i = 0; i < client.ties.size(); i++) {
					if(name1.equals(client.ties.get(i).getName1()) && name2.equals(client.ties.get(i).getName2())
							||name1.equals(client.ties.get(i).getName2()) && name2.equals(client.ties.get(i).getName1())) {
						flag = false;
					}
				}
				
				boolean flag1 = false;
				for(int i = 0; i < client.objects.size(); i++) {
					if(client.objects.get(i).getName().equals(name1)) {
						flag1 = true;
					}
				}
				
				for(int i = 0; i < client.removeObjects.size(); i++) {
					if(client.removeObjects.get(i).getName().equals(name1)) {
						flag1 = true;
					}
				}
				
				boolean flag2 = false;
				for(int i = 0; i < client.objects.size(); i++) {
					if(client.objects.get(i).getName().equals(name1)) {
						flag2 = true;
					}
				}
				
				for(int i = 0; i < client.removeObjects.size(); i++) {
					if(client.removeObjects.get(i).getName().equals(name1)) {
						flag2 = true;
					}
				}
				
				//ÖÐÐÄÎïÌå±êÖ¾
				boolean flag3 = false;
				if(client.central.getName().equals(name1)||
						client.central.getName().equals(name2)) {
					flag3 = true;
				}
					
				
				if(flag && flag1 && flag2 || flag && flag3 && (flag1 || flag2)) {
					JOptionPane.showMessageDialog(frame, "Success","information",
							JOptionPane.INFORMATION_MESSAGE);
					client.addTie(name1, name2, num);
					textArea_2.setText(client.GUIties());
					textArea.setText(client.GUIshowResult());
					textArea_1.setText(client.GUIremoveObjects());
					from.setText("");
					to.setText("");
					number.setText("");
					
				}else {
					LogTest.logger.error("Ôö¼Ó¹ØÏµÊäÈë´íÎó");
					JOptionPane.showMessageDialog(frame, "Input Error","Error",
			                 JOptionPane.WARNING_MESSAGE);
					from.setText("");
					to.setText("");
					number.setText("");
				}
			}
		});
		buttonAddTie.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		buttonAddTie.setBounds(451, 317, 113, 27);
		frame.getContentPane().add(buttonAddTie);
		
		
		
		JButton buttonDelTie = new JButton("\u786E\u5B9A");
		buttonDelTie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label name1 = new label(fromDel.getText());
				label name2 = new label(toDel.getText());
				
				//Õâ¸ö¹ØÏµ´æÔÚ
				boolean flag = false;
				
				for(int i = 0; i < client.ties.size(); i++) {
					if(name1.equals(client.ties.get(i).getName1()) && name2.equals(client.ties.get(i).getName2())||
							name1.equals(client.ties.get(i).getName2()) && name2.equals(client.ties.get(i).getName1())) {
						flag = true;
					}
				}
				
				if(flag) {
					JOptionPane.showMessageDialog(frame, "Success","information",
							JOptionPane.INFORMATION_MESSAGE);
					client.deleteTie(name1, name2);
					textArea_2.setText(client.GUIties());
					textArea.setText(client.GUIshowResult());
					textArea_1.setText(client.GUIremoveObjects());
					fromDel.setText("");
					toDel.setText("");
				}else {
					LogTest.logger.error("É¾³ý¹ØÏµÊäÈë´íÎó");
					JOptionPane.showMessageDialog(frame, "Input Error","Error",
			                 JOptionPane.WARNING_MESSAGE);
					fromDel.setText("");
					toDel.setText("");
				}
			}
		});
		buttonDelTie.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		buttonDelTie.setBounds(357, 361, 113, 27);
		frame.getContentPane().add(buttonDelTie);
		
		
		
		JButton buttonAddPerson = new JButton("\u786E\u5B9A");
		buttonAddPerson.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label nameAdd = new label(name.getText());
				int ageAdd = Integer.valueOf(age.getText());
				char genderAdd = gender.getText().charAt(0);
				
				boolean flag = true;
				for(int i = 0; i < client.objects.size(); i++) {
					if(client.objects.get(i).getName().equals(nameAdd)) {
						flag = false;
					}
				}
				
				for(int i = 0; i < client.removeObjects.size(); i++) {
					if(client.removeObjects.get(i).getName().equals(nameAdd)) {
						flag = false;
					}
				}
				
				if(flag) {
					JOptionPane.showMessageDialog(frame, "Success","information",
							JOptionPane.INFORMATION_MESSAGE);
					client.addAPeople(nameAdd, ageAdd, genderAdd);
					textArea.setText(client.GUIshowResult());
					textArea_1.setText(client.GUIremoveObjects());
					name.setText("");
					age.setText("");
					gender.setText("");
				}else {
					LogTest.logger.error("Ôö¼ÓÈËÊäÈë´íÎó");
					JOptionPane.showMessageDialog(frame, "Input Error","Error",
			                 JOptionPane.WARNING_MESSAGE);
					name.setText("");
					age.setText("");
					gender.setText("");
				}
			}
		});
		buttonAddPerson.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		buttonAddPerson.setBounds(309, 426, 113, 27);
		frame.getContentPane().add(buttonAddPerson);
		
		
		
		JButton buttonDistance = new JButton("\u786E\u5B9A");
		buttonDistance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label name01 = new label(name1.getText());
				label name02 = new label(name2.getText());
				
				People p1 = null;
				People p2 = null;
				
				boolean flag1 = false;
				boolean flag2 = false;
				
				for(int i = 0; i < client.objects.size(); i++) {
					if(client.objects.get(i).getName().equals(name01)) {
						flag1 = true;
						p1 = client.objects.get(i);
					}
				}
				
				
				for(int i = 0; i < client.objects.size(); i++) {
					if(client.objects.get(i).getName().equals(name02)) {
						flag2 = true;
						p2 = client.objects.get(i);
					}
				}
				
				if(flag1 && flag2 && !name01.equals(name02)) {
					JOptionPane.showMessageDialog(frame, "Success","information",
							JOptionPane.INFORMATION_MESSAGE);
					double res = api.getLogicalDistance(client, p1, p2);
					textArea.setText(client.GUIshowResult() + String.valueOf(res));
					name1.setText("");
					name2.setText("");
				}else {
					LogTest.logger.error("»ñµÃÁ½ÈË¾àÀëÊäÈë´íÎó");
					JOptionPane.showMessageDialog(frame, "Input Error","Error",
			                 JOptionPane.WARNING_MESSAGE);
					name1.setText("");
					name2.setText("");
				}
			}
		});
		buttonDistance.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
		buttonDistance.setBounds(430, 255, 113, 27);
		frame.getContentPane().add(buttonDistance);
		
		
		
	}
}
