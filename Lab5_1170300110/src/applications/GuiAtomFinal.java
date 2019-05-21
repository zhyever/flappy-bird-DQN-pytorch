package applications;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
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
import centralobject.CentralAtom;
import logging.LogTest;
import physicalobject.Electron;
import track.Track;

public class GuiAtomFinal {

  private JFrame frmAtomstructure;
  private JTextField load;
  private JTextField from;
  private JTextField to;
  private JTextField saved;
  private JTextField addTrack;
  private JTextField delTrack;
  private JTextField addEle;
  private JTextField delEle;

  private int numSave = 0;
  private static AtomStructure client = new AtomStructure();
  private static AtomStructureCaretaker caretaker = new AtomStructureCaretaker();
  CircularOrbitApis<CentralAtom, Electron> api = new CircularOrbitApis<CentralAtom, Electron>();
  private JTextField addEleT;
  private JTextField nameAtom;

  /** be called.
   * ±»µ÷ÓÃ
   */
  public static void start(AtomStructure atom) {
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
        client = atom;
        GuiAtomFinal window = new GuiAtomFinal();
        window.frmAtomstructure.setVisible(true);

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
          File f = new File("src/circularOrbit/test/AtomicStructure.txt");
          client.readFile(f);

          GuiAtomFinal window = new GuiAtomFinal();
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
  public GuiAtomFinal() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmAtomstructure = new JFrame();
    frmAtomstructure.setTitle("AtomStructure");
    frmAtomstructure.getContentPane().setEnabled(false);
    frmAtomstructure.setBounds(100, 100, 503, 554);
    frmAtomstructure.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmAtomstructure.getContentPane().setLayout(null);

    addTrack = new JTextField();
    addTrack.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    addTrack.setColumns(10);
    addTrack.setBounds(141, 373, 86, 24);
    frmAtomstructure.getContentPane().add(addTrack);

    addEleT = new JTextField();
    addEleT.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    addEleT.setColumns(10);
    addEleT.setBounds(258, 435, 86, 24);
    frmAtomstructure.getContentPane().add(addEleT);

    delTrack = new JTextField();
    delTrack.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    delTrack.setColumns(10);
    delTrack.setBounds(141, 404, 86, 24);
    frmAtomstructure.getContentPane().add(delTrack);

    addEle = new JTextField();
    addEle.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    addEle.setColumns(10);
    addEle.setBounds(120, 435, 86, 24);
    frmAtomstructure.getContentPane().add(addEle);

    delEle = new JTextField();
    delEle.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    delEle.setColumns(10);
    delEle.setBounds(141, 466, 86, 24);
    frmAtomstructure.getContentPane().add(delEle);

    JLabel lblNewLabel = new JLabel("¶Áµµ(¶ÁµµºÅ)£º");
    lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    lblNewLabel.setBounds(141, 289, 113, 18);
    frmAtomstructure.getContentPane().add(lblNewLabel);

    load = new JTextField();
    load.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    load.setBounds(248, 286, 86, 24);
    frmAtomstructure.getContentPane().add(load);
    load.setColumns(10);

    TextArea textArea = new TextArea();
    textArea.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
    textArea.setBounds(10, 10, 465, 191);
    frmAtomstructure.getContentPane().add(textArea);
    textArea.setText(client.guishowResult());

    JLabel label = new JLabel("µç×ÓÔ¾Ç¨");
    label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    label.setBounds(24, 325, 72, 18);
    frmAtomstructure.getContentPane().add(label);

    JLabel label1 = new JLabel("Ô´¹ìµÀ£º");
    label1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    label1.setBounds(24, 345, 72, 18);
    frmAtomstructure.getContentPane().add(label1);

    from = new JTextField();
    from.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    from.setColumns(10);
    from.setBounds(85, 343, 86, 24);
    frmAtomstructure.getContentPane().add(from);

    JLabel label2 = new JLabel("Ä¿±ê¹ìµÀ£º");
    label2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    label2.setBounds(176, 346, 88, 18);
    frmAtomstructure.getContentPane().add(label2);

    to = new JTextField();
    to.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    to.setColumns(10);
    to.setBounds(248, 343, 86, 24);
    frmAtomstructure.getContentPane().add(to);

    saved = new JTextField();
    saved.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    saved.setEditable(false);
    saved.setBounds(363, 246, 20, 24);
    frmAtomstructure.getContentPane().add(saved);
    saved.setColumns(10);
    saved.setText(String.valueOf(numSave));

    JLabel lblNewLabel1 = new JLabel("ÒÑÓÐ´æµµÊý£º");
    lblNewLabel1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    lblNewLabel1.setBounds(273, 249, 98, 18);
    frmAtomstructure.getContentPane().add(lblNewLabel1);

    JLabel lblNewLabel2 = new JLabel("ÐÂÔö¹ìµÀºÅ£º");
    lblNewLabel2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    lblNewLabel2.setBounds(24, 376, 103, 18);
    frmAtomstructure.getContentPane().add(lblNewLabel2);

    JLabel label3 = new JLabel("É¾³ý¹ìµÀºÅ£º");
    label3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    label3.setBounds(24, 407, 103, 18);
    frmAtomstructure.getContentPane().add(label3);

    JLabel label4 = new JLabel("ÐÂÔöµç×ÓºÅ£º");
    label4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    label4.setBounds(24, 438, 103, 18);
    frmAtomstructure.getContentPane().add(label4);

    JLabel label5 = new JLabel("É¾³ýµç×ÓºÅ£º");
    label5.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    label5.setBounds(24, 469, 103, 18);
    frmAtomstructure.getContentPane().add(label5);

    JButton buttonGraph = new JButton("Í¼ÐÎ»¯ÏÔÊ¾");
    buttonGraph.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    buttonGraph.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        PanelTest.start(client);
      }
    });
    buttonGraph.setBounds(147, 249, 113, 27);
    frmAtomstructure.getContentPane().add(buttonGraph);

    JButton buttonSave = new JButton("´æµµ");
    buttonSave.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    buttonSave.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          caretaker.set(client.save());
          JOptionPane.showMessageDialog(frmAtomstructure, "Success", 
              "information", JOptionPane.INFORMATION_MESSAGE);
          numSave++;
          saved.setText(String.valueOf(numSave));
        } catch (ClassNotFoundException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }
    });
    buttonSave.setBounds(17, 284, 113, 27);
    frmAtomstructure.getContentPane().add(buttonSave);

    JButton buttonLoad = new JButton("È·¶¨");
    buttonLoad.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    buttonLoad.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int num = Integer.parseInt(load.getText());
        if (num < numSave) {
          try {
            client.recover(caretaker.get(num));
            JOptionPane.showMessageDialog(frmAtomstructure, 
                "Success", "information", JOptionPane.INFORMATION_MESSAGE);
            textArea.setText(client.guishowResult());
            load.setText("");

          } catch (ClassNotFoundException | IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
          }
        } else {
          LogTest.logger.error("¶ÁµµºÅÊäÈë´íÎó");
          JOptionPane.showMessageDialog(frmAtomstructure, 
              "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          load.setText("");
        }

      }
    });
    buttonLoad.setBounds(348, 285, 113, 27);
    frmAtomstructure.getContentPane().add(buttonLoad);

    JButton buttonTransit = new JButton("È·¶¨");
    buttonTransit.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    buttonTransit.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int num1 = Integer.parseInt(from.getText());
        int num2 = Integer.parseInt(to.getText());
        if (num1 != num2 && num1 <= client.numberOfTracks && num2 <= client.numberOfTracks) {
          client.tranAll(num1, num2);
          JOptionPane.showMessageDialog(frmAtomstructure, "Success", 
              "information", JOptionPane.INFORMATION_MESSAGE);
          textArea.setText(client.guishowResult());
          to.setText("");
          from.setText("");
        } else {
          LogTest.logger.error("×ªÒÆÊäÈë´íÎó");
          JOptionPane.showMessageDialog(frmAtomstructure, 
              "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          to.setText("");
          from.setText("");
        }
      }
    });
    buttonTransit.setBounds(348, 342, 113, 27);
    frmAtomstructure.getContentPane().add(buttonTransit);

    JButton buttonAddTrack = new JButton("È·¶¨");
    buttonAddTrack.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    buttonAddTrack.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        boolean flag = true;
        int num = Integer.parseInt(addTrack.getText());
        for (int i = 0; i < client.tracks.size(); i++) {
          if (client.tracks.get(i).getNum() == num) {
            flag = false;
          }
        }
        if (flag) {
          Track t = new Track(num);
          client.addNewTrack(t);
          JOptionPane.showMessageDialog(frmAtomstructure, 
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          addTrack.setText("");
          textArea.setText(client.guishowResult());
        } else {
          LogTest.logger.error("Ìí¼Ó¹ìµÀÊäÈë´íÎó");
          JOptionPane.showMessageDialog(frmAtomstructure, 
              "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          addTrack.setText("");
        }
      }
    });
    buttonAddTrack.setBounds(348, 372, 113, 27);
    frmAtomstructure.getContentPane().add(buttonAddTrack);

    JButton buttonDelTrack = new JButton("È·¶¨");
    buttonDelTrack.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    buttonDelTrack.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        boolean flag = false;
        int num = Integer.parseInt(delTrack.getText());
        for (int i = 0; i < client.tracks.size(); i++) {
          if (client.tracks.get(i).getNum() == num) {
            flag = true;
          }
        }

        if (flag) {
          client.delATrack(num);
          JOptionPane.showMessageDialog(frmAtomstructure, 
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          delTrack.setText("");
          textArea.setText(client.guishowResult());
        } else {
          LogTest.logger.error("É¾³ý¹ìµÀÊäÈë´íÎó");
          JOptionPane.showMessageDialog(frmAtomstructure, 
              "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          delTrack.setText("");
        }

      }
    });
    buttonDelTrack.setBounds(348, 403, 113, 27);
    frmAtomstructure.getContentPane().add(buttonDelTrack);

    JButton buttonAddEle = new JButton("È·¶¨");
    buttonAddEle.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    buttonAddEle.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int numE = Integer.parseInt(addEle.getText());
        int numT = Integer.parseInt(addEleT.getText());

        boolean flag = true;
        for (int i = 0; i < client.objects.size(); i++) {
          if (client.objects.get(i).getNum() == numE) {
            flag = false;
          }
        }
        Track t = null;
        boolean flag1 = false;
        for (int i = 0; i < client.tracks.size(); i++) {
          if (client.tracks.get(i).getNum() == numT) {
            flag1 = true;
            t = client.tracks.get(i);
          }
        }

        if (flag && flag1) {
          JOptionPane.showMessageDialog(frmAtomstructure,
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          // Electron ele = new Electron(numE, t);
          Electron ele = new Electron(numE);
          client.rel2.put(ele, t);
          client.objects.add(ele);
          client.addObjectToTrack(t, ele);
          addEle.setText("");
          addEleT.setText("");
          textArea.setText(client.guishowResult());

        } else {
          LogTest.logger.error("Ìí¼Óµç×ÓÊäÈë´íÎó");
          JOptionPane.showMessageDialog(frmAtomstructure,
              "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          addEle.setText("");
          addEleT.setText("");
        }

      }
    });
    buttonAddEle.setBounds(348, 434, 113, 27);
    frmAtomstructure.getContentPane().add(buttonAddEle);

    JButton buttonDelEle = new JButton("È·¶¨");
    buttonDelEle.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    buttonDelEle.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int num = Integer.parseInt(delEle.getText());
        boolean flag = false;
        for (int i = 0; i < client.objects.size(); i++) {
          if (client.objects.get(i).getNum() == num) {
            flag = true;
          }
        }

        if (flag) {

          Iterator<Electron> iterator = client.objects.iterator();
          while (iterator.hasNext()) {
            Electron it = iterator.next();
            if (it.getNum() == num) {
              iterator.remove();
            }
          }

          Track t = null;
          ArrayList<Electron> arryChange = null;

          Iterator<Map.Entry<Track, ArrayList<Electron>>> iterator1 = 
              client.rel.entrySet().iterator();
          while (iterator1.hasNext()) {
            Entry<Track, ArrayList<Electron>> it = iterator1.next();
            ArrayList<Electron> arry = it.getValue();

            Iterator<Electron> iterator2 = arry.iterator();
            while (iterator2.hasNext()) {
              Electron it1 = iterator2.next();
              if (it1.getNum() == num) {
                arryChange = arry;
                flag = true;
                t = it.getKey();
              }
            }
          }

          JOptionPane.showMessageDialog(frmAtomstructure,
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          Iterator<Electron> iterator3 = arryChange.iterator();
          while (iterator3.hasNext()) {
            Electron it = iterator3.next();
            if (it.getNum() == num) {
              iterator3.remove();
            }
          }
          client.rel.put(t, arryChange);
          delEle.setText("");
          ///
          LogTest.logger.info("É¾³ý" + num + "ºÅµç×Ó");
          textArea.setText(client.guishowResult());
        } else {
          LogTest.logger.error("É¾³ýµç×ÓÊäÈë´íÎó");
          JOptionPane.showMessageDialog(frmAtomstructure, 
              "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          delEle.setText("");
        }

      }
    });
    buttonDelEle.setBounds(348, 465, 113, 27);
    frmAtomstructure.getContentPane().add(buttonDelEle);
    JLabel lblNewLabel3 = new JLabel("¹ìµÀ£º");
    lblNewLabel3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    lblNewLabel3.setBounds(209, 438, 72, 18);
    frmAtomstructure.getContentPane().add(lblNewLabel3);

    JButton buttonCompute = new JButton("¼ÆËãìØ");
    buttonCompute.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    buttonCompute.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        double num = api.getObjectDistributionEntropy(client);
        textArea.setText(client.guishowResult() + String.valueOf(num));
      }
    });
    buttonCompute.setBounds(16, 250, 113, 27);
    frmAtomstructure.getContentPane().add(buttonCompute);

    JLabel lblNewLabel4 = new JLabel("ÖÐÐÄÔ­×ÓÃû³Æ£º");
    lblNewLabel4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    lblNewLabel4.setBounds(24, 216, 118, 18);
    frmAtomstructure.getContentPane().add(lblNewLabel4);

    nameAtom = new JTextField();
    nameAtom.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    nameAtom.setEditable(false);
    nameAtom.setColumns(10);
    nameAtom.setBounds(146, 212, 35, 24);
    frmAtomstructure.getContentPane().add(nameAtom);

    nameAtom.setText(client.elementName);

  }
}
