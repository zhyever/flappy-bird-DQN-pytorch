/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO 构建一个图（通过增加节点和边） 然后删除边、调用实例方法，与期望判断是否相等。
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    
    @Test
    public void test() {
    	Set<String> vertices = new HashSet<>();
    	List<Edge<String>> edges = new ArrayList<>();
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	
    	Set<String> verticesReturn = new HashSet<>();
    	Map<String, Integer> mapReturn = new HashMap<String, Integer>();
    	
    	Graph<String> graph = emptyInstance();
    	
    	graph.add("Lc");
    	graph.add("Lzy");
    	graph.add("ShiLao");
    	graph.add("Ywq");
    	
    	graph.set("Lc", "Lzy", 8);
    	graph.set("Lc", "ShiLao", 5);
    	graph.set("Lzy", "ShiLao", 3);
    	graph.set("Ywq", "Lc", 2);
    	
    	vertices.add("Lc");
    	vertices.add("Lzy");
    	vertices.add("ShiLao");
    	vertices.add("Ywq");
    	
    	edges.add(new Edge<String>("Lc", "Lzy", 8));
    	edges.add(new Edge<String>("Lc", "ShiLao", 5));
    	edges.add(new Edge<String>("Lzy", "ShiLao", 3));
    	edges.add(new Edge<String>("Ywq", "Lc", 2));
    	
    	map.put("Lc", 8);
    	
    	///vertices
    	verticesReturn = graph.vertices();
    	assertEquals("expected equal", vertices, verticesReturn);
    	
    	///sources
    	mapReturn = graph.sources("Lzy");
    	assertEquals("expected equal", map, mapReturn);
    	
    	///targets
    	map.clear();
    	map.put("Lzy", 8);
    	map.put("ShiLao", 5);
    	mapReturn = graph.targets("Lc");
    	assertEquals("expected equal", map, mapReturn);
    	
    	///删除之后再增加 改变weight
    	graph.set("Lc", "Lzy", 4);
    	edges.remove(new Edge<String>("Lc", "Lzy", 8));
    	edges.add(new Edge<String>("Lc", "Lzy", 4));
    	map.clear();
    	map.put("Lzy", 4);
    	map.put("ShiLao", 5);
    	mapReturn = graph.targets("Lc");
    	assertEquals("expected equal", map, mapReturn);
    	
    	
    	///删除 Lzy应该没有target了
    	graph.set("Lzy", "ShiLao", 0);
    	map.clear();
    	mapReturn = graph.targets("Lzy");
    	assertEquals("expected equal", map, mapReturn);
    	
    	///删除 测试
    	graph.remove("Ywq");
    	vertices.remove("Ywq");
    	verticesReturn = graph.vertices();
    	assertEquals("expected equal", vertices, verticesReturn);
    	map.clear();
    	mapReturn = graph.sources("Lc");
    	assertEquals("expected equal", map, mapReturn);
    	
    	
    }
    
}
