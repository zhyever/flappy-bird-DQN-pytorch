package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import applications.AtomStructure;
import applications.SocialNetworkCircleBigger;
import applications.TrackGame;
import exception.MyException;

public class ReadContext {
  //Factory

  Read readStrategy = new ConcreteRead();

  public void readfileBuffer1(File filename, TrackGame system) throws FileNotFoundException, IOException, MyException {
    readStrategy.readfileBuffer1(filename, system);
  }

  public void readfileScanner1(File filename, TrackGame system) throws FileNotFoundException, IOException, MyException{
    readStrategy.readfileScanner1(filename, system);
  }

  public void readfileStream1(File filename, TrackGame system) throws FileNotFoundException, IOException, MyException {
    readStrategy.readfileStream1(filename, system);
  }

  public void readfileBuffer2(File filename, AtomStructure system) throws FileNotFoundException, IOException, MyException {
    readStrategy.readfileBuffer2(filename, system);
  }

  public void readfileScanner2(File filename, AtomStructure system) throws FileNotFoundException, IOException, MyException{
    readStrategy.readfileScanner2(filename, system);
  }

  public void readfileStream2(File filename, AtomStructure system) throws FileNotFoundException, IOException, MyException {
    readStrategy.readfileStream2(filename, system);
  }

  public void readfileBuffer3(File filename, SocialNetworkCircleBigger system) throws FileNotFoundException, IOException, MyException {
    readStrategy.readfileBuffer3(filename, system);
  }

  public void readfileScanner3(File filename, SocialNetworkCircleBigger system) throws FileNotFoundException, IOException, MyException{
    readStrategy.readfileScanner3(filename, system);
  }

  public void readfileStream3(File filename, SocialNetworkCircleBigger system) throws FileNotFoundException, IOException, MyException {
    readStrategy.readfileStream3(filename, system);
  }

}
