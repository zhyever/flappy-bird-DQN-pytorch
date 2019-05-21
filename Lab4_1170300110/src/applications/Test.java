package applications;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		String str = ": asd ";
		String regex_1 = "[\\s]?([\\.A-Za-z0-9]+)[\\s]?";
		Pattern p_1 = Pattern.compile(regex_1);
		
		Matcher m_1 = p_1.matcher(str);
		if (m_1.find()) {
			System.out.println(m_1.group(1));
		}
	}
}
