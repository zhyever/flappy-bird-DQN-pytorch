package passBrige;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField textField_n;
  private JTextField textField_h;
  private JTextField textField_t;
  private JTextField textField_N;
  private JTextField textField_k;
  private JTextField textField_MV;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          GUI frame = new GUI();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public GUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 753, 341);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel label = new JLabel("\u8F93\u5165\u53C2\u6570\uFF1A");
    label.setFont(new Font("宋体", Font.PLAIN, 15));
    label.setBounds(31, 13, 101, 18);
    contentPane.add(label);

    JLabel lblNewLabel = new JLabel("\u68AF\u5B50\u6570");
    lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
    lblNewLabel.setBounds(62, 44, 120, 18);
    contentPane.add(lblNewLabel);

    JLabel lblNewLabel_1 = new JLabel("\u68AF\u5B50\u957F\u5EA6 ");
    lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
    lblNewLabel_1.setBounds(62, 75, 108, 18);
    contentPane.add(lblNewLabel_1);

    JLabel lblNewLabel_2 = new JLabel("\u95F4\u9694\u65F6\u95F4");
    lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));
    lblNewLabel_2.setBounds(62, 106, 132, 18);
    contentPane.add(lblNewLabel_2);

    JLabel lblNewLabel_3 = new JLabel("\u751F\u6210\u7334\u5B50\u603B\u6570");
    lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 15));
    lblNewLabel_3.setBounds(62, 137, 120, 18);
    contentPane.add(lblNewLabel_3);

    JLabel lblNewLabel_4 = new JLabel("\u6BCF\u6B21\u751F\u6210\u7334\u5B50\u6570");
    lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 15));
    lblNewLabel_4.setBounds(62, 168, 120, 18);
    contentPane.add(lblNewLabel_4);

    JLabel lblNewLabel_5 = new JLabel("\u7334\u5B50\u6700\u5927\u901F\u5EA6");
    lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 15));
    lblNewLabel_5.setBounds(62, 199, 132, 18);
    contentPane.add(lblNewLabel_5);

    textField_n = new JTextField();
    textField_n.setBounds(196, 41, 86, 24);
    contentPane.add(textField_n);
    textField_n.setColumns(10);

    textField_h = new JTextField();
    textField_h.setBounds(196, 72, 86, 24);
    contentPane.add(textField_h);
    textField_h.setColumns(10);

    textField_t = new JTextField();
    textField_t.setBounds(196, 103, 86, 24);
    contentPane.add(textField_t);
    textField_t.setColumns(10);

    textField_N = new JTextField();
    textField_N.setBounds(196, 134, 86, 24);
    contentPane.add(textField_N);
    textField_N.setColumns(10);

    textField_k = new JTextField();
    textField_k.setBounds(196, 165, 86, 24);
    contentPane.add(textField_k);
    textField_k.setColumns(10);

    textField_MV = new JTextField();
    textField_MV.setBounds(196, 196, 86, 24);
    contentPane.add(textField_MV);
    textField_MV.setColumns(10);

    TextArea textArea = new TextArea();
    textArea.setBounds(302, 44, 423, 216);
    contentPane.add(textArea);

    JLabel lblNewLabel_6 = new JLabel("\u6A21\u62DF\u533A\u57DF\uFF1A");
    lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 15));
    lblNewLabel_6.setBounds(302, 13, 101, 18);
    contentPane.add(lblNewLabel_6);

    JButton button = new JButton("\u786E\u5B9A\u5E76\u5F00\u59CB\u8FC7\u6CB3\u6A21\u62DF\u5668");
    button.setFont(new Font("宋体", Font.PLAIN, 15));
    button.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
      }
    });
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int n = Integer.parseInt(textField_n.getText());
        int h = Integer.parseInt(textField_h.getText());
        int t = Integer.parseInt(textField_t.getText());
        int N = Integer.parseInt(textField_N.getText());
        int k = Integer.parseInt(textField_k.getText());
        int MV = Integer.parseInt(textField_MV.getText());

        StringBuffer buffer = new StringBuffer();

        MySystem mysys = new MySystem(n, h, t, N, k, MV);

        try {
          mysys.systemStart();
        } catch (IOException e2) {
          // TODO Auto-generated catch block
          e2.printStackTrace();
        }

        File f = new File("logs/log.log");

        FileReader fr = null;
        BufferedReader br = null;
        String read = null;

        try {
          fr = new FileReader(f);
        } catch (FileNotFoundException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        br = new BufferedReader(fr);

        try {
          while ((read = br.readLine()) != null) {
            buffer.append(read + "\n");
          }
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }

        try {
          br.close();
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }

        textArea.setText(buffer.toString());
      }
    });
    button.setBounds(62, 233, 210, 27);
    contentPane.add(button);
  }
}
