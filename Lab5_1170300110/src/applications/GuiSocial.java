package applications;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import exception.MyException;
import logging.LogTest;

public class GuiSocial {

  private JFrame frmSocialnetwork;
  private JTextField textField;
  /**be called.
   * 
   */

  public static void start() {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          GuiSocial window = new GuiSocial();
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
      @Override
      public void run() {
        try {
          GuiSocial window = new GuiSocial();
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
  public GuiSocial() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmSocialnetwork = new JFrame();
    frmSocialnetwork.setTitle("SocialNetwork");
    frmSocialnetwork.setBounds(100, 100, 450, 300);
    // frmSocialnetwork.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmSocialnetwork.getContentPane().setLayout(null);

    JLabel lblNewLabel = new JLabel("请输入文件名：");
    lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    lblNewLabel.setBounds(58, 62, 109, 18);
    frmSocialnetwork.getContentPane().add(lblNewLabel);

    textField = new JTextField();
    textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    textField.setBounds(192, 59, 170, 24);
    frmSocialnetwork.getContentPane().add(textField);
    textField.setColumns(10);

    JButton btnNewButton = new JButton("确定");
    btnNewButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String str = textField.getText();
        File f = new File("src/circularOrbit/test/" + str + ".txt");
        SocialNetworkCircle client = new SocialNetworkCircle();
        try {
          client.readFile(f);
          GuiSocialFinal.start(client);
        } catch (IOException e1) {
          // System.out.println("输入文件有误");
          LogTest.logger.error("输入文件不存在");
          JOptionPane.showMessageDialog(frmSocialnetwork, 
              "输入文件不存在", "Error", JOptionPane.WARNING_MESSAGE);
        } catch (MyException e2) {
          // e2.printInfo();
          LogTest.logger.error(e2.getInfo());
          JOptionPane.showMessageDialog(frmSocialnetwork, 
              e2.getInfo(), "Error", JOptionPane.WARNING_MESSAGE);
        }

      }
    });
    btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    btnNewButton.setBounds(153, 134, 113, 27);
    frmSocialnetwork.getContentPane().add(btnNewButton);
  }
}
