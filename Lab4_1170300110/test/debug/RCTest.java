package debug;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class RCTest {
	RemoveComments rc = new RemoveComments();
	
	@Test
	public void Test1() {
		String[] str1 = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
		
	
		List<String> str1Res = rc.removeComments(str1);
		for(int i = 0; i < str1Res.size(); i++) {
			System.out.println(str1Res.get(i));
		}
		
		
		String[] str2 = {"a/*comment", "line", "more_comment*/b"};
		List<String> str2Res = rc.removeComments(str2);
		for(int i = 0; i < str2Res.size(); i++) {
			System.out.println(str2Res.get(i));
		}
		
	}
}
