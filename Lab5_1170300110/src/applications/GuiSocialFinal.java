package applications;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import apis.CircularOrbitApis;
import apis.PanelTest;
import centralobject.Person;
import circularorbits.Label;
import logging.LogTest;
import physicalobject.People;
import track.Track;

public class GuiSocialFinal {

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
  CircularOrbitApis<Person, People> api = new CircularOrbitApis<Person, People>();
  private JTextField centralText;
  private JTextField name1;
  private JTextField name2;

  /**be called.
   * 
   * @param social social system
   */
  public static void start(SocialNetworkCircle social) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {

        // File f = new File("src/circularOrbit/test/" + str + ".txt");
        // try {
        // client.readFile(f);
        // } catch (IOException | MyException e) {
        // // can't get here
        // e.printStackTrace();
        // }

        client = social;
        GuiSocialFinal window = new GuiSocialFinal();
        window.frame.setVisible(true);

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
          File f = new File("src/circularOrbit/test/SocialNetworkCircle.txt");
          client.readFile(f);

          GuiSocialFinal window = new GuiSocialFinal();
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
  public GuiSocialFinal() {
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

    TextArea textArea1 = new TextArea();
    textArea1.setEditable(false);
    textArea1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
    textArea1.setBounds(33, 482, 500, 57);
    frame.getContentPane().add(textArea1);
    textArea1.setText(client.guiremoveObjects());

    JLabel label = new JLabel("社会关系：");
    label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    label.setBounds(10, 545, 233, 18);
    frame.getContentPane().add(label);

    JLabel central = new JLabel("中心：");
    central.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    central.setBounds(309, 223, 72, 18);
    frame.getContentPane().add(central);

    centralText = new JTextField();
    centralText.setEditable(false);
    centralText.setBounds(384, 221, 86, 24);
    frame.getContentPane().add(centralText);
    centralText.setColumns(10);
    centralText.setText(client.central.getName().toString());

    TextArea textArea2 = new TextArea();
    textArea2.setEditable(false);
    textArea2.setText("");
    textArea2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
    textArea2.setBounds(33, 569, 500, 154);
    frame.getContentPane().add(textArea2);
    textArea2.setText(client.guities());

    JLabel lblNewLabel4 = new JLabel("未加入轨道系统的人员：");
    lblNewLabel4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    lblNewLabel4.setBounds(10, 458, 233, 18);
    frame.getContentPane().add(lblNewLabel4);

    JLabel label1 = new JLabel("计算两人之间逻辑距离：");
    label1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    label1.setBounds(10, 259, 186, 18);
    frame.getContentPane().add(label1);

    name1 = new JTextField();
    name1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    name1.setColumns(10);
    name1.setBounds(194, 257, 101, 24);
    frame.getContentPane().add(name1);

    name2 = new JTextField();
    name2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    name2.setColumns(10);
    name2.setBounds(309, 257, 101, 24);
    frame.getContentPane().add(name2);

    name = new JTextField();
    name.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    name.setColumns(10);
    name.setBounds(10, 427, 86, 24);
    frame.getContentPane().add(name);

    age = new JTextField();
    age.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    age.setColumns(10);
    age.setBounds(110, 427, 86, 24);
    frame.getContentPane().add(age);

    gender = new JTextField();
    gender.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    gender.setColumns(10);
    gender.setBounds(209, 427, 86, 24);
    frame.getContentPane().add(gender);

    JLabel lblNewLabel = new JLabel("计算某人信息扩散度：");
    lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    lblNewLabel.setBounds(10, 290, 150, 18);
    frame.getContentPane().add(lblNewLabel);

    compute = new JTextField();
    compute.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    compute.setBounds(194, 287, 101, 24);
    frame.getContentPane().add(compute);
    compute.setColumns(10);

    JLabel lblNewLabel1 = new JLabel("增加一条社会关系：");
    lblNewLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    lblNewLabel1.setBounds(10, 321, 150, 18);
    frame.getContentPane().add(lblNewLabel1);

    from = new JTextField();
    from.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    from.setBounds(157, 318, 86, 24);
    frame.getContentPane().add(from);
    from.setColumns(10);

    to = new JTextField();
    to.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    to.setBounds(257, 318, 86, 24);
    frame.getContentPane().add(to);
    to.setColumns(10);

    number = new JTextField();
    number.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    number.setBounds(357, 318, 86, 24);
    frame.getContentPane().add(number);
    number.setColumns(10);

    JLabel lblNewLabel2 = new JLabel("姓名                  姓名                  亲密度");
    lblNewLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    lblNewLabel2.setBounds(157, 341, 286, 18);
    frame.getContentPane().add(lblNewLabel2);

    JLabel lblNewLabel3 = new JLabel("删除一条社会关系：");
    lblNewLabel3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    lblNewLabel3.setBounds(10, 365, 136, 18);
    frame.getContentPane().add(lblNewLabel3);

    fromDel = new JTextField();
    fromDel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    fromDel.setColumns(10);
    fromDel.setBounds(157, 362, 86, 24);
    frame.getContentPane().add(fromDel);

    toDel = new JTextField();
    toDel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    toDel.setColumns(10);
    toDel.setBounds(257, 362, 86, 24);
    frame.getContentPane().add(toDel);

    JLabel label2 = new JLabel(
        "新增某人：（姓名、年龄、性别）");
    label2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    label2.setBounds(10, 396, 311, 18);
    frame.getContentPane().add(label2);

    TextArea textArea = new TextArea();
    textArea.setFont(new Font("微软雅黑", Font.PLAIN, 12));
    textArea.setBounds(10, 10, 554, 190);
    frame.getContentPane().add(textArea);
    textArea.setText(client.guishowResult());

    JButton buttonGraph = new JButton("图形化显示");
    buttonGraph.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        PanelTest.start(client);
      }
    });
    buttonGraph.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    buttonGraph.setBounds(14, 219, 113, 27);
    frame.getContentPane().add(buttonGraph);

    JButton buttonCompute = new JButton("计算熵");
    buttonCompute.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        double num = api.getObjectDistributionEntropy(client);
        textArea.setText(client.guishowResult() + String.valueOf(num));
      }
    });
    buttonCompute.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    buttonCompute.setBounds(157, 219, 113, 27);
    frame.getContentPane().add(buttonCompute);

    JButton buttonComp = new JButton("确定");
    buttonComp.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String name = compute.getText();
        Label nameLabel = new Label(name);
        boolean flag = false;

        Iterator<Map.Entry<Track, ArrayList<People>>> iterator1 = client.rel.entrySet().iterator();
        while (iterator1.hasNext()) {
          Entry<Track, ArrayList<People>> it = iterator1.next();
          if (it.getKey().getNum() == 1) {
            for (int i = 0; i < it.getValue().size(); i++) {
              if (it.getValue().get(i).getName().equals(nameLabel)) {
                flag = true;
              }
            }
          }
        }

        if (flag) {
          JOptionPane.showMessageDialog(frame, 
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          double number = client.getCloseLevel(nameLabel);
          textArea.setText(client.guishowResult() + String.valueOf(number));
          compute.setText("");
        } else {
          LogTest.logger.error("亲密度输入错误");
          JOptionPane.showMessageDialog(frame, "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          compute.setText("");
        }

      }
    });
    buttonComp.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    buttonComp.setBounds(309, 286, 113, 27);
    frame.getContentPane().add(buttonComp);

    JButton buttonAddTie = new JButton("确定");
    buttonAddTie.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Label name1 = new Label(from.getText());
        Label name2 = new Label(to.getText());

        double num = Double.parseDouble(number.getText());
        // 原来不存在这个tie标志
        boolean flag = true;

        for (int i = 0; i < client.ties.size(); i++) {
          if (name1.equals(client.ties.get(i).getName1()) 
              && name2.equals(client.ties.get(i).getName2())
              || name1.equals(client.ties.get(i).getName2()) 
              && name2.equals(client.ties.get(i).getName1())) {
            flag = false;
          }
        }

        boolean flag1 = false;
        for (int i = 0; i < client.objects.size(); i++) {
          if (client.objects.get(i).getName().equals(name1)) {
            flag1 = true;
          }
        }

        for (int i = 0; i < client.removeObjects.size(); i++) {
          if (client.removeObjects.get(i).getName().equals(name1)) {
            flag1 = true;
          }
        }

        boolean flag2 = false;
        for (int i = 0; i < client.objects.size(); i++) {
          if (client.objects.get(i).getName().equals(name1)) {
            flag2 = true;
          }
        }

        for (int i = 0; i < client.removeObjects.size(); i++) {
          if (client.removeObjects.get(i).getName().equals(name1)) {
            flag2 = true;
          }
        }

        // 中心物体标志
        boolean flag3 = false;
        if (client.central.getName().equals(name1) || client.central.getName().equals(name2)) {
          flag3 = true;
        }

        if (flag && flag1 && flag2 || flag && flag3 && (flag1 || flag2)) {
          JOptionPane.showMessageDialog(frame,
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          client.addTie(name1, name2, num);
          textArea2.setText(client.guities());
          textArea.setText(client.guishowResult());
          textArea1.setText(client.guiremoveObjects());
          from.setText("");
          to.setText("");
          number.setText("");

        } else {
          LogTest.logger.error("增加关系输入错误");
          JOptionPane.showMessageDialog(frame, "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          from.setText("");
          to.setText("");
          number.setText("");
        }
      }
    });
    buttonAddTie.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    buttonAddTie.setBounds(451, 317, 113, 27);
    frame.getContentPane().add(buttonAddTie);

    JButton buttonDelTie = new JButton("确定");
    buttonDelTie.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Label name1 = new Label(fromDel.getText());
        Label name2 = new Label(toDel.getText());

        // 这个关系存在
        boolean flag = false;

        for (int i = 0; i < client.ties.size(); i++) {
          if (name1.equals(client.ties.get(i).getName1())
              && name2.equals(client.ties.get(i).getName2())
              || name1.equals(client.ties.get(i).getName2())
              && name2.equals(client.ties.get(i).getName1())) {
            flag = true;
          }
        }

        if (flag) {
          JOptionPane.showMessageDialog(frame, 
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          client.deleteTie(name1, name2);
          textArea2.setText(client.guities());
          textArea.setText(client.guishowResult());
          textArea1.setText(client.guiremoveObjects());
          fromDel.setText("");
          toDel.setText("");
        } else {
          LogTest.logger.error("删除关系输入错误");
          JOptionPane.showMessageDialog(frame, "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          fromDel.setText("");
          toDel.setText("");
        }
      }
    });
    buttonDelTie.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    buttonDelTie.setBounds(357, 361, 113, 27);
    frame.getContentPane().add(buttonDelTie);

    JButton buttonAddPerson = new JButton("确定");
    buttonAddPerson.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Label nameAdd = new Label(name.getText());
        int ageAdd = Integer.valueOf(age.getText());
        char genderAdd = gender.getText().charAt(0);

        boolean flag = true;
        for (int i = 0; i < client.objects.size(); i++) {
          if (client.objects.get(i).getName().equals(nameAdd)) {
            flag = false;
          }
        }

        for (int i = 0; i < client.removeObjects.size(); i++) {
          if (client.removeObjects.get(i).getName().equals(nameAdd)) {
            flag = false;
          }
        }

        if (flag) {
          JOptionPane.showMessageDialog(frame, 
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          client.addAPeople(nameAdd, ageAdd, genderAdd);
          textArea.setText(client.guishowResult());
          textArea1.setText(client.guiremoveObjects());
          name.setText("");
          age.setText("");
          gender.setText("");
        } else {
          LogTest.logger.error("增加人输入错误");
          JOptionPane.showMessageDialog(frame, "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          name.setText("");
          age.setText("");
          gender.setText("");
        }
      }
    });
    buttonAddPerson.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    buttonAddPerson.setBounds(309, 426, 113, 27);
    frame.getContentPane().add(buttonAddPerson);

    JButton buttonDistance = new JButton("确定");
    buttonDistance.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Label name01 = new Label(name1.getText());
        Label name02 = new Label(name2.getText());

        People p1 = null;
        People p2 = null;

        boolean flag1 = false;
        boolean flag2 = false;

        for (int i = 0; i < client.objects.size(); i++) {
          if (client.objects.get(i).getName().equals(name01)) {
            flag1 = true;
            p1 = client.objects.get(i);
          }
        }

        for (int i = 0; i < client.objects.size(); i++) {
          if (client.objects.get(i).getName().equals(name02)) {
            flag2 = true;
            p2 = client.objects.get(i);
          }
        }

        if (flag1 && flag2 && !name01.equals(name02)) {
          JOptionPane.showMessageDialog(frame, 
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          double res = api.getLogicalDistance(client, p1, p2);
          textArea.setText(client.guishowResult() + String.valueOf(res));
          name1.setText("");
          name2.setText("");
        } else {
          LogTest.logger.error("获得两人距离输入错误");
          JOptionPane.showMessageDialog(frame, "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          name1.setText("");
          name2.setText("");
        }
      }
    });
    buttonDistance.setFont(new Font("微软雅黑", Font.PLAIN, 15));
    buttonDistance.setBounds(430, 255, 113, 27);
    frame.getContentPane().add(buttonDistance);

  }
}
