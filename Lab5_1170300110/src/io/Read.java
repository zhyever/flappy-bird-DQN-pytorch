package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import applications.AtomStructure;
import applications.SocialNetworkCircleBigger;
import applications.TrackGame;
import exception.MyException;

public interface Read {

  public void readfileBuffer1(File filename, TrackGame system) throws FileNotFoundException, IOException, MyException;
  public void readfileScanner1(File filename, TrackGame system) throws FileNotFoundException, IOException, MyException;
  public void readfileStream1(File filename, TrackGame system) throws FileNotFoundException, IOException, MyException;

  public void readfileBuffer2(File filename, AtomStructure system) throws FileNotFoundException, IOException, MyException;
  public void readfileScanner2(File filename, AtomStructure system) throws FileNotFoundException, IOException, MyException;
  public void readfileStream2(File filename, AtomStructure system) throws FileNotFoundException, IOException, MyException;

  public void readfileBuffer3(File filename, SocialNetworkCircleBigger system) throws FileNotFoundException, IOException, MyException;
  public void readfileScanner3(File filename, SocialNetworkCircleBigger system) throws FileNotFoundException, IOException, MyException;
  public void readfileStream3(File filename, SocialNetworkCircleBigger system) throws FileNotFoundException, IOException, MyException;
}
