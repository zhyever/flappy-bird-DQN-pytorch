/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO 测试不同输入下的结果
	// 特殊情况：加入、不在范围内、标点符号、输入只有一个单词
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void test() throws IOException {
    	final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/test.txt"));
        String input = "a b c d e";
        assertEquals("expected equal", "A c b c d e!", nimoy.poem(input));
        
        //只有一个输入
        input = "a";
        assertEquals("expected equal", "A!", nimoy.poem(input));
        
        //不存在此顶点
        input = "h";
        assertEquals("expected equal", "H!", nimoy.poem(input));
        
        //添加操作
        input = "a b";
        assertEquals("expected equal", "A c b!", nimoy.poem(input));
        
        input = "a, b,";
        assertEquals("expected equal", "A c b!", nimoy.poem(input));
        //test toString
        System.out.println(nimoy.toString());
        
        final GraphPoet nimoy1 = new GraphPoet(new File("test/P1/poet/mugar-omni-theater.txt"));
        input = "Test the system.";
        assertEquals("expected equal", "Test of the system!", nimoy1.poem(input));
    }
}
