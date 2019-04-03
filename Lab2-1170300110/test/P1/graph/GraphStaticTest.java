/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // TODO test other vertex label types in Problem 3.2
    @Test
    
    public void test() {
    	ConcreteEdgesGraph<Integer> g = new ConcreteEdgesGraph<Integer>();
    	g.add(new Integer(1));
    	Set<Integer> vertices = new HashSet<Integer>();
    	Set<Integer> verticesReturn = new HashSet<Integer>();
    	vertices.add(new Integer(1));
    	verticesReturn = g.vertices();
    	assertEquals("expected equal", vertices, verticesReturn);
   
    }
    
}
