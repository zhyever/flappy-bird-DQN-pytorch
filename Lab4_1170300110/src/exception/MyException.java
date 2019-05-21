package exception;

import logging.LogTest;

public class MyException extends Exception {
  String str;

  public MyException(String info) {
    this.str = info;
  }

  public void printInfo() {
    System.out.println(str);
    LogTest.logger.error(str);
  }

  public String getInfo() {
    return this.str;
  }
}
