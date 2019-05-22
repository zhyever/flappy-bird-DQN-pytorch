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
import centralobject.Sport;
import circularorbits.Word;
import logging.LogTest;
import physicalobject.Athlete;
import track.Track;

public class GuiTrackGameFinal {

  private JFrame frmTrackgame;
  private JTextField changeAthName1;
  private JTextField changeAthName2;

  private static TrackGame trackgame;
  private JTextField newTrackNum;
  private JTextField delTrackNum;
  private JTextField name;
  private JTextField number;
  private JTextField nation;
  private JTextField age;
  private JTextField grade;
  private JTextField trackNum;
  private JTextField delAth;

  private static TrackGameContext client = new TrackGameContext();
  CircularOrbitApis<Sport, Athlete> api = new CircularOrbitApis<Sport, Athlete>();
  /**be called.
   * 
   * @param g system
   */

  public static void start(TrackGame g) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {

        // File f = new File("src/circularOrbit/test/" + str + ".txt");
        // if(n == 1) {
        // trackgame = new TrackGameRandom();
        // }else {
        // trackgame = new TrackGameSorted();
        // }

        // try {
        // trackgame.readFile(f);
        // } catch (IOException | MyException e) {
        // // never come here
        // e.printStackTrace();
        // }
        //
        trackgame = g;
        // client.game = g;
        client.run(trackgame);

        GuiTrackGameFinal window = new GuiTrackGameFinal();
        window.frmTrackgame.setVisible(true);
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

          File f = new File("src/circularOrbit/test/TrackGame.txt");
          trackgame = new TrackGameRandom();
          trackgame.readFile(f);
          client.run(trackgame);

          GuiTrackGameFinal window = new GuiTrackGameFinal();
          window.frmTrackgame.setVisible(true);

          // client.game.showResult();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public GuiTrackGameFinal() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmTrackgame = new JFrame();
    frmTrackgame.setTitle("TrackGame");
    frmTrackgame.setBounds(100, 100, 597, 579);
    frmTrackgame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    TextArea textArea = new TextArea();
    textArea.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
    textArea.setEditable(false);
    textArea.setBounds(26, 21, 524, 257);
    frmTrackgame.getContentPane().add(textArea);
    textArea.setText(client.game.guishowResult());

    JLabel changeAthLabel = new JLabel("ÊäÈë½»»»ÔË¶¯Ô±Ãû×Ö");
    changeAthLabel.setBounds(29, 327, 150, 18);
    changeAthLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(changeAthLabel);

    changeAthName1 = new JTextField();
    changeAthName1.setBounds(193, 324, 86, 24);
    changeAthName1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(changeAthName1);
    changeAthName1.setColumns(10);

    changeAthName2 = new JTextField();
    changeAthName2.setBounds(301, 324, 86, 24);
    changeAthName2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(changeAthName2);
    changeAthName2.setColumns(10);

    JLabel newTrackNumLabel = new JLabel("Ìí¼ÓÐÂ¹ìµÀºÅ£º");
    newTrackNumLabel.setBounds(29, 358, 150, 18);
    newTrackNumLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(newTrackNumLabel);

    JLabel delTrackNumLabel = new JLabel("É¾³ý¹ìµÀºÅ£º");
    delTrackNumLabel.setBounds(29, 389, 150, 18);
    delTrackNumLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(delTrackNumLabel);

    JLabel newAthLabel = new JLabel(
        "Ìí¼ÓÐÂÔË¶¯Ô±£º£¨ÐÕÃû¡¢ºÅÂë¡¢¹ú¼®¡¢ÄêÁä¡¢³É¼¨¡¢¹ìµÀºÅ£©");
    newAthLabel.setBounds(29, 420, 408, 18);
    newAthLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(newAthLabel);

    JLabel delAthLabel = new JLabel("É¾³ýÄ³Ò»ÔË¶¯Ô±£º£¨Ãû×Ö£©");
    delAthLabel.setBounds(29, 488, 190, 18);
    delAthLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(delAthLabel);

    newTrackNum = new JTextField();
    newTrackNum.setBounds(193, 355, 86, 24);
    newTrackNum.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    newTrackNum.setColumns(10);
    frmTrackgame.getContentPane().add(newTrackNum);

    delTrackNum = new JTextField();
    delTrackNum.setBounds(193, 386, 86, 24);
    delTrackNum.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    delTrackNum.setColumns(10);
    frmTrackgame.getContentPane().add(delTrackNum);

    name = new JTextField();
    name.setBounds(29, 451, 86, 24);
    name.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    name.setColumns(10);
    frmTrackgame.getContentPane().add(name);

    number = new JTextField();
    number.setBounds(118, 451, 86, 24);
    number.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    number.setColumns(10);
    frmTrackgame.getContentPane().add(number);

    nation = new JTextField();
    nation.setBounds(206, 451, 86, 24);
    nation.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    nation.setColumns(10);
    frmTrackgame.getContentPane().add(nation);

    age = new JTextField();
    age.setBounds(299, 451, 86, 24);
    age.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    age.setColumns(10);
    frmTrackgame.getContentPane().add(age);

    grade = new JTextField();
    grade.setBounds(389, 451, 86, 24);
    grade.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    grade.setColumns(10);
    frmTrackgame.getContentPane().add(grade);

    trackNum = new JTextField();
    trackNum.setBounds(477, 451, 86, 24);
    trackNum.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    trackNum.setColumns(10);
    frmTrackgame.getContentPane().add(trackNum);

    delAth = new JTextField();
    delAth.setBounds(224, 485, 86, 24);
    delAth.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    delAth.setColumns(10);
    frmTrackgame.getContentPane().add(delAth);

    /// button!
    JButton buttonShowGraph = new JButton("Í¼ÐÎ»¯ÏÔÊ¾");
    buttonShowGraph.setBounds(224, 284, 113, 27);
    buttonShowGraph.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        PanelTest.start(client.game);
      }
    });
    frmTrackgame.getContentPane().setLayout(null);
    buttonShowGraph.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(buttonShowGraph);

    JButton buttonChangeAth = new JButton("È·¶¨");
    buttonChangeAth.setBounds(413, 323, 113, 27);
    buttonChangeAth.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String name1 = changeAthName1.getText();
        String name2 = changeAthName2.getText();

        Athlete ath1 = null;
        Athlete ath2 = null;

        for (int i = 0; i < client.game.objects.size(); i++) {
          if (client.game.objects.get(i).getName().equals(new Word(name1))) {
            ath1 = client.game.objects.get(i);
          }

          if (client.game.objects.get(i).getName().equals(new Word(name2))) {
            ath2 = client.game.objects.get(i);
          }
        }

        if (ath1 == null || ath2 == null) {
          // ÄÚÈÝ //±êÌâ
          LogTest.logger.error("ÐÂÔöÔË¶¯Ô±ÊäÈë´íÎó");
          JOptionPane.showMessageDialog(frmTrackgame, 
              "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          changeAthName1.setText("");
          changeAthName2.setText("");

        } else {
          JOptionPane.showMessageDialog(frmTrackgame,
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          client.game.change(ath1, ath2);
          changeAthName1.setText("");
          changeAthName2.setText("");

        }
        textArea.setText("");
        textArea.setText(client.game.guishowResult());

      }
    });
    buttonChangeAth.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(buttonChangeAth);

    JButton buttonAddTrack = new JButton("È·¶¨");
    buttonAddTrack.setBounds(299, 355, 113, 27);
    buttonAddTrack.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {

        String str = newTrackNum.getText();
        int trackNum = Integer.parseInt(str);

        if (trackNum <= client.game.tracks.size()) {
          LogTest.logger.error("ÐÂÔö¹ìµÀÊäÈë´íÎó");
          JOptionPane.showMessageDialog(frmTrackgame, 
              "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          newTrackNum.setText("");
        } else {
          JOptionPane.showMessageDialog(frmTrackgame, 
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          Track track = new Track(trackNum);
          client.game.addNewTrack(track);
          newTrackNum.setText("");
        }
        textArea.setText("");
        textArea.setText(client.game.guishowResult());
      }
    });
    buttonAddTrack.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(buttonAddTrack);

    JButton buttonDelTrack = new JButton("È·¶¨");
    buttonDelTrack.setBounds(299, 386, 113, 27);
    buttonDelTrack.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String str = delTrackNum.getText();
        int n = Integer.parseInt(str);
        boolean flag = false;

        for (int i = 0; i < client.game.tracks.size(); i++) {
          if (client.game.tracks.get(i).getNum() == n) {
            flag = true;
          }
        }

        if (flag) {
          JOptionPane.showMessageDialog(frmTrackgame, 
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          client.game.delATrack(n);
          delTrackNum.setText("");
        } else {
          LogTest.logger.error("É¾³ý¹ìµÀÊäÈë´íÎó");
          JOptionPane.showMessageDialog(frmTrackgame, 
              "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          delTrackNum.setText("");
        }

        textArea.setText("");
        textArea.setText(client.game.guishowResult());
      }
    });
    buttonDelTrack.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(buttonDelTrack);

    JButton buttonAddAth = new JButton("È·¶¨");
    buttonAddAth.setBounds(434, 416, 113, 27);
    buttonAddAth.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        Track t = null;

        String str1 = name.getText();
        String str2 = number.getText();
        String str3 = nation.getText();
        String str4 = age.getText();
        String str5 = grade.getText();
        String str6 = trackNum.getText();

        // word name, int num, String nation, int age, double grade
        Athlete ath = new Athlete(new Word(str1), 
            Integer.parseInt(str2), str3, Integer.parseInt(str4),
            Double.parseDouble(str5));

        boolean flag = false;

        for (int i = 0; i < client.game.tracks.size(); i++) {
          if (client.game.tracks.get(i).getNum() == Integer.parseInt(str6)) {
            flag = true;
            t = client.game.tracks.get(i);
            client.game.rel2.put(ath, t);
            // ath.setTrack(t);
          }
        }

        if (flag) {
          JOptionPane.showMessageDialog(frmTrackgame, 
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          client.game.objects.add(ath);
          client.game.addObjectToTrack(t, ath);
          name.setText("");
          number.setText("");
          nation.setText("");
          age.setText("");
          grade.setText("");
          trackNum.setText("");
        } else {
          LogTest.logger.error("Ìí¼ÓÔË¶¯Ô±ÊäÈëÐÅÏ¢´íÎó");
          JOptionPane.showMessageDialog(frmTrackgame, 
              "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          name.setText("");
          number.setText("");
          nation.setText("");
          age.setText("");
          grade.setText("");
          trackNum.setText("");
        }

        textArea.setText("");
        textArea.setText(client.game.guishowResult());
      }
    });

    buttonAddAth.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(buttonAddAth);

    JButton buttonDelAth = new JButton("È·¶¨");
    buttonDelAth.setBounds(324, 484, 113, 27);
    buttonDelAth.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        String str = delAth.getText();
        boolean flag = false;

        Iterator<Athlete> iterator = client.game.objects.iterator();
        while (iterator.hasNext()) {
          Athlete it = iterator.next();
          if (it.getName().toString().equals(str)) {
            iterator.remove();
          }
        }

        Track t = null;
        ArrayList<Athlete> arryChange = null;

        Iterator<Map.Entry<Track, ArrayList<Athlete>>> iterator1 = 
            client.game.rel.entrySet().iterator();
        while (iterator1.hasNext()) {
          Entry<Track, ArrayList<Athlete>> it = iterator1.next();
          ArrayList<Athlete> arry = it.getValue();

          Iterator<Athlete> iterator2 = arry.iterator();
          while (iterator2.hasNext()) {
            Athlete it1 = iterator2.next();
            if (it1.getName().toString().equals(str)) {
              arryChange = arry;
              flag = true;
              t = it.getKey();
            }
          }
        }

        if (flag) {
          JOptionPane.showMessageDialog(frmTrackgame,
              "Success", "information", JOptionPane.INFORMATION_MESSAGE);
          Iterator<Athlete> iterator3 = arryChange.iterator();
          while (iterator3.hasNext()) {
            Athlete it = iterator3.next();
            if (it.getName().toString().equals(str)) {
              iterator3.remove();
            }
          }
          client.game.rel.put(t, arryChange);

          //
          LogTest.logger.info("É¾³ý" + str);

          delAth.setText("");
        } else {
          LogTest.logger.error("É¾³ýÔË¶¯Ô±ÊäÈëÐÅÏ¢´íÎó");
          JOptionPane.showMessageDialog(frmTrackgame,
              "Input Error", "Error", JOptionPane.WARNING_MESSAGE);
          delAth.setText("");
        }

        textArea.setText("");
        textArea.setText(client.game.guishowResult());

      }
    });
    buttonDelAth.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    frmTrackgame.getContentPane().add(buttonDelAth);

    JButton buttonCompute = new JButton("¼ÆËãìØ");
    buttonCompute.setBounds(450, 484, 113, 27);
    buttonCompute.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 15));
    buttonCompute.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        double num = api.getObjectDistributionEntropy(client.game);
        textArea.setText(client.game.guishowResult() + String.valueOf(num));
      }
    });
    frmTrackgame.getContentPane().add(buttonCompute);

  }
}
