package applications;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
  /**main.
   * 
   * @param args no means
   */

  public static void main(String[] args) {
    String str = ": asd ";
    String regex1 = "[\\s]?([\\.A-Za-z0-9]+)[\\s]?";
    Pattern p1 = Pattern.compile(regex1);

    Matcher m1 = p1.matcher(str);
    if (m1.find()) {
      System.out.println(m1.group(1));
    }
  }
}
