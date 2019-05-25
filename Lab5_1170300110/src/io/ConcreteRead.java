package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Scanner;

import applications.AtomStructure;
import applications.SocialNetworkCircleBigger;
import applications.TrackGame;
import exception.MyException;
import logging.LogTest;

public class ConcreteRead implements Read {

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
  public void readfileBuffer1(File filename, TrackGame system) throws IOException, MyException {
    // Buffer

    LogTest.logger.info("读取" + filename.toString() + "文件");
    FileReader fr = null;
    BufferedReader br = null;
    String read = null;

    fr = new FileReader(filename);
    br = new BufferedReader(fr);

    double sum = 0;
    double startTime = System.currentTimeMillis();

    while ((read = br.readLine()) != null) {

      double endTime = System.currentTimeMillis();
      double temp = sub(endTime, startTime);
      sum = add(sum, temp);

      if (read.contains("Athlete")) {
        system.athleteAdd(read);
      }

      if (read.contains("Game")) {
        system.gameTypeSet(read);
      }

      if (read.contains("NumOfTracks")) {
        system.gameTracksSet(read);
      }
      // System.out.println("~");
      startTime = System.currentTimeMillis();
    }
    System.out.println(sum);

    br.close();
  }

  @Override
  public void readfileScanner1(File filename, TrackGame system) throws FileNotFoundException, IOException, MyException {
    // Scanner
    String encoding = "utf-8";

    Scanner scanner = new Scanner(filename, encoding);
    // StringBuffer sb = new StringBuffer();

    double startTime = System.currentTimeMillis();
    double sum = 0;

    int counter = 0;

    while (scanner.hasNextLine()) {
      // sb.append(scanner.nextLine()).toString();
      // System.out.println(sb.toString());
      String dealing = scanner.nextLine().toString();
      double endTime = System.currentTimeMillis();
      double temp = sub(endTime, startTime);
      sum = add(sum, temp);

      if (dealing.contains("Athlete")) {
        system.athleteAdd(dealing);
      }

      if (dealing.contains("Game")) {
        system.gameTypeSet(dealing);
      }

      if (dealing.contains("NumOfTracks")) {
        system.gameTracksSet(dealing);
      }

      startTime = System.currentTimeMillis();

      counter++;
      if(counter % 100 == 0) {
        System.out.println(counter);
      }
    }
    scanner.close();

    System.out.println(sum);
  }

  @Override
  public void readfileStream1(File filename, TrackGame system) throws FileNotFoundException, IOException, MyException {
    InputStreamReader inputReader = new InputStreamReader(new FileInputStream(filename));
    BufferedReader bf = new BufferedReader(inputReader);
    // 按行读取字符串
    String str;

    double startTime = System.currentTimeMillis();
    double sum = 0;
    int counter = 0;

    while ((str = bf.readLine()) != null) {

      double endTime = System.currentTimeMillis();
      double temp = sub(endTime, startTime);
      sum = add(sum, temp);


      if (str.contains("Athlete")) {
        system.athleteAdd(str);
      }

      if (str.contains("Game")) {
        system.gameTypeSet(str);
      }

      if (str.contains("NumOfTracks")) {
        system.gameTracksSet(str);
      }

      // System.out.println("~");
      startTime = System.currentTimeMillis();
      counter++;
      if(counter % 100 == 0) {
        System.out.println(counter);
      }

    }
    bf.close();
    inputReader.close();

    System.out.println(sum);

  }

  @Override
  public void readfileBuffer2(File filename, AtomStructure system)
      throws FileNotFoundException, IOException, MyException {
    // TODO Auto-generated method stub
    LogTest.logger.info("读取" + filename.toString() + "文件");
    FileReader fr = null;
    BufferedReader br = null;
    String read = null;

    fr = new FileReader(filename);
    br = new BufferedReader(fr);
    while ((read = br.readLine()) != null) {
      if (read.contains("ElementName")) {
        system.elementNameSet(read);
      }

      if (read.contains("NumberOfTracks")) {
        if (read.contains(".")) {
          throw new MyException("轨道数信息处不应该出现小数");
        }
        system.numberOfTracksSet(read);
      }

      if (read.contains("NumberOfElectron")) {
        if (read.contains(".")) {
          throw new MyException("电子/轨道信息处不应该出现小数");
        }
        system.electronSet(read);
      }
    }

    br.close();

  }

  @Override
  public void readfileScanner2(File filename, AtomStructure system)
      throws FileNotFoundException, IOException, MyException {
    // Scanner
    String encoding = "utf-8";

    Scanner scanner = new Scanner(filename, encoding);
    // StringBuffer sb = new StringBuffer();
    while (scanner.hasNextLine()) {
      // sb.append(scanner.nextLine()).toString();
      // System.out.println(sb.toString());
      String dealing = scanner.nextLine().toString();

      if (dealing.contains("ElementName")) {
        system.elementNameSet(dealing);
      }

      if (dealing.contains("NumberOfTracks")) {
        if (dealing.contains(".")) {
          throw new MyException("轨道数信息处不应该出现小数");
        }
        system.numberOfTracksSet(dealing);
      }

      if (dealing.contains("NumberOfElectron")) {
        if (dealing.contains(".")) {
          throw new MyException("电子/轨道信息处不应该出现小数");
        }
        system.electronSet(dealing);
      }

    }
    scanner.close();
  }

  @Override
  public void readfileStream2(File filename, AtomStructure system)
      throws FileNotFoundException, IOException, MyException {
    InputStreamReader inputReader = new InputStreamReader(new FileInputStream(filename));
    BufferedReader bf = new BufferedReader(inputReader);
    // 按行读取字符串
    String str;
    while ((str = bf.readLine()) != null) {
      if (str.contains("ElementName")) {
        system.elementNameSet(str);
      }

      if (str.contains("NumberOfTracks")) {
        if (str.contains(".")) {
          throw new MyException("轨道数信息处不应该出现小数");
        }
        system.numberOfTracksSet(str);
      }

      if (str.contains("NumberOfElectron")) {
        if (str.contains(".")) {
          throw new MyException("电子/轨道信息处不应该出现小数");
        }
        system.electronSet(str);
      }
    }
    bf.close();
    inputReader.close();

  }

  @Override
  public void readfileBuffer3(File filename, SocialNetworkCircleBigger system)
      throws FileNotFoundException, IOException, MyException {
    LogTest.logger.info("读取" + filename.toString() + "文件");
    LogTest.logger.info("构造轨道系统");
    FileReader fr = null;
    BufferedReader br = null;
    String read = null;



    fr = new FileReader(filename);
    br = new BufferedReader(fr);

    double startTime = System.currentTimeMillis();
    double sum = 0;
    int counter = 0;

    while ((read = br.readLine()) != null) {

      double endTime = System.currentTimeMillis();
      double temp = sub(endTime, startTime);
      sum = add(sum, temp);

      if (read.contains("CentralUser")) {
        if (read.contains(".")) {
          throw new MyException("中心物体年龄不应该为小数形式");
        }
        system.centralUserSet(read);
      }

      if (read.contains("SocialTie")) {
        system.socialTieSet(read);
      }

      if (read.contains("Friend")) {
        if (read.contains(".")) {
          throw new MyException("轨道物体年龄不应该为小数形式");
        }
        system.friendSet(read);
      }

      startTime = System.currentTimeMillis();
      counter++;
      if(counter % 100 == 0) {
        System.out.println(counter);
      }
    }

    System.out.println(sum);

    br.close();

    system.buildSystem();

  }

  @Override
  public void readfileScanner3(File filename, SocialNetworkCircleBigger system)
      throws FileNotFoundException, IOException, MyException {
    // Scanner
    String encoding = "utf-8";

    Scanner scanner = new Scanner(filename, encoding);

    double startTime = System.currentTimeMillis();
    double sum = 0;
    int counter = 0;


    // StringBuffer sb = new StringBuffer();
    while (scanner.hasNextLine()) {
      // sb.append(scanner.nextLine()).toString();
      // System.out.println(sb.toString());
      String dealing = scanner.nextLine().toString();

      double endTime = System.currentTimeMillis();
      double temp = sub(endTime, startTime);
      sum = add(sum, temp);


      if (dealing.contains("CentralUser")) {
        if (dealing.contains(".")) {
          throw new MyException("中心物体年龄不应该为小数形式");
        }
        system.centralUserSet(dealing);
      }

      if (dealing.contains("SocialTie")) {

        system.socialTieSet(dealing);
      }

      if (dealing.contains("Friend")) {
        if (dealing.contains(".")) {
          throw new MyException("轨道物体年龄不应该为小数形式");
        }
        system.friendSet(dealing);
      }

      startTime = System.currentTimeMillis();
      counter++;
      if(counter % 100 == 0) {
        System.out.println(counter);
      }

    }
    System.out.println(sum);
    scanner.close();


    system.buildSystem();

  }

  @Override
  public void readfileStream3(File filename, SocialNetworkCircleBigger system)
      throws FileNotFoundException, IOException, MyException {
    InputStreamReader inputReader = new InputStreamReader(new FileInputStream(filename));
    BufferedReader bf = new BufferedReader(inputReader);

    double startTime = System.currentTimeMillis();
    double sum = 0;
    int counter = 0;


    String str;
    while ((str = bf.readLine()) != null) {

      double endTime = System.currentTimeMillis();
      double temp = sub(endTime, startTime);
      sum = add(sum, temp);



      if (str.contains("CentralUser")) {
        if (str.contains(".")) {
          throw new MyException("中心物体年龄不应该为小数形式");
        }
        system.centralUserSet(str);
      }

      if (str.contains("SocialTie")) {

        system.socialTieSet(str);
      }

      if (str.contains("Friend")) {
        if (str.contains(".")) {
          throw new MyException("轨道物体年龄不应该为小数形式");
        }
        system.friendSet(str);
      }
      startTime = System.currentTimeMillis();
      counter++;
      if(counter % 100 == 0) {
        System.out.println(counter);
      }
    }

    System.out.println(sum);
    bf.close();
    inputReader.close();


    system.buildSystem();

  }

}
