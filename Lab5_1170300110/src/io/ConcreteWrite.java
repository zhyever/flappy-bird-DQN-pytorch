package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

import applications.AtomStructure;
import applications.SocialNetworkCircleBigger;
import applications.TrackGame;

public class ConcreteWrite implements Write {

  public double add(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    double res = b1.add(b2).doubleValue();
    return res;
  }

  public double sub(double v1, double v2) {
    BigDecimal b1 = new BigDecimal(Double.toString(v1));
    BigDecimal b2 = new BigDecimal(Double.toString(v2));
    double res = b1.subtract(b2).doubleValue();
    return res;
  }


  @Override
  public void writeFileWriter1(TrackGame system) {
    try {
      File file = new File("src/circularOrbit/test/TrackGameOutput.txt");
      // if file doesnt exists, then create it

      if (!file.exists()) {
        file.createNewFile();
      }

      // true = append file
      FileWriter fileWritter = new FileWriter(file, true);
      FileWriter fileClean = new FileWriter(file);

      // flush
      fileClean.write("");
      fileClean.flush();
      fileClean.close();

      double sum = 0;
      double startTime = System.currentTimeMillis();

      // write something
      fileWritter.write("Game ::= " + system.game + "\r\n");
      fileWritter.write("NumOfTracks ::= " + String.valueOf(system.tracks.size()) + "\r\n");

      for (int i = 0; i < system.objects.size(); i++) {
        fileWritter.write("Athlete ::= <" + system.objects.get(i).getName().toString() + ","
            + system.objects.get(i).getNum() + "," + system.objects.get(i).getNation() + ","
            + system.objects.get(i).getAge() + "," + system.objects.get(i).getGrade() + ">\r\n");
      }

      double endTime = System.currentTimeMillis();
      double temp = sub(endTime, startTime);
      sum = add(sum, temp);
      System.out.println(sum);

      fileWritter.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void writeBufferWriter1(TrackGame system) {
    try {
      File file = new File("src/circularOrbit/test/TrackGameOutput.txt");
      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);

      double sum = 0;
      double startTime = System.currentTimeMillis();

      // write something
      bw.write("Game ::= " + system.game + "\r\n");
      bw.write("NumOfTracks ::= " + String.valueOf(system.tracks.size()) + "\r\n");

      for (int i = 0; i < system.objects.size(); i++) {
        bw.write("Athlete ::= <" + system.objects.get(i).getName().toString() + "," + system.objects.get(i).getNum()
            + "," + system.objects.get(i).getNation() + "," + system.objects.get(i).getAge() + ","
            + system.objects.get(i).getGrade() + ">\r\n");
      }

      double endTime = System.currentTimeMillis();
      double temp = sub(endTime, startTime);
      sum = add(sum, temp);
      System.out.println(sum);

      bw.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void writeStream1(TrackGame system) {
    FileOutputStream fop = null;
    StringBuffer stb = new StringBuffer();

    File file = new File("src/circularOrbit/test/TrackGameOutput.txt");


    stb.append("Game ::= " + system.game + "\r\n");
    stb.append("NumOfTracks ::= " + String.valueOf(system.tracks.size()) + "\r\n");

    for (int i = 0; i < system.objects.size(); i++) {
      stb.append("Athlete ::= <" + system.objects.get(i).getName().toString() + "," + system.objects.get(i).getNum()
          + "," + system.objects.get(i).getNation() + "," + system.objects.get(i).getAge() + ","
          + system.objects.get(i).getGrade() + ">\r\n");
    }

    try {

      double sum = 0;
      double startTime = System.currentTimeMillis();

      fop = new FileOutputStream(file);

      double endTime = System.currentTimeMillis();
      double temp = sub(endTime, startTime);
      sum = add(sum, temp);
      System.out.println(sum);

      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      // get the content in bytes
      byte[] contentInBytes = stb.toString().getBytes();

      fop.write(contentInBytes);
      fop.flush();
      fop.close();

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (fop != null) {
          fop.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  @Override
  public void writeFileWriter2(AtomStructure system) {
    try {
      File file = new File("src/circularOrbit/test/AtomicStructureOutput.txt");
      // if file doesnt exists, then create it

      if (!file.exists()) {
        file.createNewFile();
      }

      // true = append file
      FileWriter fileWritter = new FileWriter(file, true);
      FileWriter fileClean = new FileWriter(file);

      // flush
      fileClean.write("");
      fileClean.flush();
      fileClean.close();

      // write something
      fileWritter.write("ElementName ::= " + system.elementName + "\r\n");
      fileWritter.write("NumberOfTracks ::= " + String.valueOf(system.tracks.size()) + "\r\n");
      fileWritter.write("NumberOfElectron ::= ");

      for (int i = 0; i < system.tracks.size(); i++) {
        if (i != system.tracks.size() - 1) {
          fileWritter.write(system.tracks.get(i).getNum() + "/" + system.rel.get(system.tracks.get(i)).size() + ";");
        } else {
          fileWritter.write(system.tracks.get(i).getNum() + "/" + system.rel.get(system.tracks.get(i)).size());
        }
      }

      fileWritter.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void writeBufferWriter2(AtomStructure system) {

    try {
      File file = new File("src/circularOrbit/test/AtomicStructureOutput.txt");
      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      // write something
      bw.write("ElementName ::= " + system.elementName + "\r\n");
      bw.write("NumberOfTracks ::= " + String.valueOf(system.tracks.size()) + "\r\n");
      bw.write("NumberOfElectron ::= ");

      for (int i = 0; i < system.tracks.size(); i++) {
        if (i != system.tracks.size() - 1) {
          bw.write(system.tracks.get(i).getNum() + "/" + system.rel.get(system.tracks.get(i)).size() + ";");
        } else {
          bw.write(system.tracks.get(i).getNum() + "/" + system.rel.get(system.tracks.get(i)).size());
        }
      }

      bw.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void writeStream2(AtomStructure system) {
    FileOutputStream fop = null;
    StringBuffer stb = new StringBuffer();

    File file = new File("src/circularOrbit/test/AtomicStructureOutput.txt");

    stb.append("ElementName ::= " + system.elementName + "\r\n");
    stb.append("NumberOfTracks ::= " + String.valueOf(system.tracks.size()) + "\r\n");
    stb.append("NumberOfElectron ::= ");

    for (int i = 0; i < system.tracks.size(); i++) {
      if (i != system.tracks.size() - 1) {
        stb.append(system.tracks.get(i).getNum() + "/" + system.rel.get(system.tracks.get(i)).size() + ";");
      } else {
        stb.append(system.tracks.get(i).getNum() + "/" + system.rel.get(system.tracks.get(i)).size());
      }
    }

    try {
      fop = new FileOutputStream(file);

      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      // get the content in bytes
      byte[] contentInBytes = stb.toString().getBytes();

      fop.write(contentInBytes);
      fop.flush();
      fop.close();

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (fop != null) {
          fop.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  @Override
  public void writeFileWriter3(SocialNetworkCircleBigger system) {
    try {
      File file = new File("src/circularOrbit/test/SocialNetworkCircleOutput.txt");
      // if file doesnt exists, then create it

      if (!file.exists()) {
        file.createNewFile();
      }

      // true = append file
      FileWriter fileWritter = new FileWriter(file, true);
      FileWriter fileClean = new FileWriter(file);

      // flush
      fileClean.write("");
      fileClean.flush();
      fileClean.close();

      // write something
      double sum = 0;
      double startTime = System.currentTimeMillis();
      System.out.println("~");

      fileWritter.write("CentralUser ::= <" + system.central.getName() + "," + system.central.getAge() + ","
          + system.central.getGender() + ">\r\n");

      for (int i = 0; i < system.objects.size(); i++) {
        fileWritter.write("Friend ::= <" + system.objects.get(i).getName() + "," + system.objects.get(i).getAge() + ","
            + system.objects.get(i).getGender() + ">\r\n");
      }

      for (int i = 0; i < system.removeObjects.size(); i++) {
        fileWritter.write("Friend ::= <" + system.removeObjects.get(i).getName() + ","
            + system.removeObjects.get(i).getAge() + "," + system.removeObjects.get(i).getGender() + ">\r\n");
      }

      for (int i = 0; i < system.ties.size(); i++) {
        fileWritter.write("SocialTie ::= <" + system.ties.get(i).getName1() + "," + system.ties.get(i).getName2() + ","
            + system.ties.get(i).getClose() + ">\r\n");
      }

      double endTime = System.currentTimeMillis();
      double temp = sub(endTime, startTime);
      sum = add(sum, temp);
      System.out.println(sum);

      fileWritter.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void writeBufferWriter3(SocialNetworkCircleBigger system) {
    try {

      File file = new File("src/circularOrbit/test/SocialNetworkCircleOutput.txt");

      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);

      double sum = 0;
      double startTime = System.currentTimeMillis();
      System.out.println("~");

      // write
      bw.write("CentralUser ::= <" + system.central.getName() + "," + system.central.getAge() + ","
          + system.central.getGender() + ">\r\n");

      for (int i = 0; i < system.objects.size(); i++) {
        bw.write("Friend ::= <" + system.objects.get(i).getName() + "," + system.objects.get(i).getAge() + ","
            + system.objects.get(i).getGender() + ">\r\n");
      }

      for (int i = 0; i < system.removeObjects.size(); i++) {
        bw.write("Friend ::= <" + system.removeObjects.get(i).getName() + "," + system.removeObjects.get(i).getAge()
            + "," + system.removeObjects.get(i).getGender() + ">\r\n");
      }

      for (int i = 0; i < system.ties.size(); i++) {
        bw.write("SocialTie ::= <" + system.ties.get(i).getName1() + "," + system.ties.get(i).getName2() + ","
            + system.ties.get(i).getClose() + ">\r\n");
      }

      double endTime = System.currentTimeMillis();
      double temp = sub(endTime, startTime);
      sum = add(sum, temp);
      System.out.println(sum);

      bw.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void writeStream3(SocialNetworkCircleBigger system) {
    FileOutputStream fop = null;
    File file = new File("src/circularOrbit/test/SocialNetworkCircleOutput.txt");
    StringBuffer stb = new StringBuffer();

    stb.append("CentralUser ::= <" + system.central.getName() + "," + system.central.getAge() + ","
        + system.central.getGender() + ">\r\n");

    for (int i = 0; i < system.objects.size(); i++) {
      stb.append("Friend ::= <" + system.objects.get(i).getName() + "," + system.objects.get(i).getAge() + ","
          + system.objects.get(i).getGender() + ">\r\n");
    }

    for (int i = 0; i < system.removeObjects.size(); i++) {
      stb.append("Friend ::= <" + system.removeObjects.get(i).getName() + "," + system.removeObjects.get(i).getAge()
          + "," + system.removeObjects.get(i).getGender() + ">\r\n");
    }

    for (int i = 0; i < system.ties.size(); i++) {
      stb.append("SocialTie ::= <" + system.ties.get(i).getName1() + "," + system.ties.get(i).getName2() + ","
          + system.ties.get(i).getClose() + ">\r\n");
    }

    try {
      fop = new FileOutputStream(file);

      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      // get the content in bytes
      byte[] contentInBytes = stb.toString().getBytes();


      double sum = 0;
      double startTime = System.currentTimeMillis();
      System.out.println("~");
      fop.write(contentInBytes);
      fop.flush();
      fop.close();
      double endTime = System.currentTimeMillis();
      double temp = sub(endTime, startTime);
      sum = add(sum, temp);
      System.out.println(sum);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (fop != null) {
          fop.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

}
