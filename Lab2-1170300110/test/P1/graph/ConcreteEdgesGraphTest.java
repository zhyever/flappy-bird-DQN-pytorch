/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
	private Set<String> vertices = new HashSet<>();
	private List<Edge<String>> edges = new ArrayList<>();
	private Map<String, Integer> map = new HashMap<String, Integer>();
	
	private Set<String> verticesReturn = new HashSet<>();
	private Map<String, Integer> mapReturn = new HashMap<String, Integer>();
	
	private ConcreteEdgesGraph<String> g = new ConcreteEdgesGraph<String>(vertices, edges);
	
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    @Test
    
	public void test1()
	{
    	vertices.add("Lc");
    	vertices.add("Lzy");
    	g.add("ShiLao");
    	edges.add(new Edge<String>("Lc", "Lzy", 8));
    	edges.add(new Edge<String>("Lc", "ShiLao", 5));
    	edges.add(new Edge<String>("Lzy", "ShiLao", 3));
    	ConcreteEdgesGraph<String> g1 = new ConcreteEdgesGraph<String>(vertices, edges);
    	verticesReturn = g1.vertices();
    	assertEquals("expected equal", vertices, verticesReturn);
    	vertices.clear();
    	edges.clear();
    	
    	
    	g.add("Lc");
    	g.add("Lzy");
    	g.add("ShiLao");
    	g.add("Ywq");
    	///重复添加
    	assertEquals("expected equal", false, g.add("Lc"));
    	
    	g.set("Lc", "Lzy", 8);
    	g.set("Lc", "ShiLao", 5);
    	g.set("Lzy", "ShiLao", 3);
    	g.set("Ywq", "Lc", 2);
    	
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
    	verticesReturn = g.vertices();
    	assertEquals("expected equal", vertices, verticesReturn);
    	
    	///sources
    	mapReturn = g.sources("Lzy");
    	assertEquals("expected equal", map, mapReturn);
    	
    	///targets
    	map.clear();
    	map.put("Lzy", 8);
    	map.put("ShiLao", 5);
    	mapReturn = g.targets("Lc");
    	assertEquals("expected equal", map, mapReturn);
    	
    	///删除之后再增加 改变weight
    	g.set("Lc", "Lzy", 4);
    	edges.remove(new Edge<String>("Lc", "Lzy", 8));
    	assertEquals("expected equal", false, g.remove("lala"));
    	edges.add(new Edge<String>("Lc", "Lzy", 4));
    	map.clear();
    	map.put("Lzy", 4);
    	map.put("ShiLao", 5);
    	mapReturn = g.targets("Lc");
    	assertEquals("expected equal", map, mapReturn);
    	
    	
    	///删除 Lzy应该没有target了
    	g.set("Lzy", "ShiLao", 0);
    	map.clear();
    	mapReturn = g.targets("Lzy");
    	assertEquals("expected equal", map, mapReturn);
    	
    	///删除 测试
    	g.set("Lzy", "Ywq", 7);
    	g.remove("Ywq");
    	vertices.remove("Ywq");
    	verticesReturn = g.vertices();
    	assertEquals("expected equal", vertices, verticesReturn);
    	map.clear();
    	mapReturn = g.sources("Lc");
    	assertEquals("expected equal", map, mapReturn);
    	
    	
	}
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO 新建一个图，设立一些顶点和边，然后打印这个图
    // 期望：
    //Vertices:
    //Ywq Lc Lzy ShiLao 
    //Edges:
    //Lc -> Lzy 	weight: 8
    //Lc -> ShiLao 	weight: 5
    //Lzy -> ShiLao weight: 3
    //Ywq -> Lc 	weight: 2
  
    
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void Test2() {
    	g.add("Lc");
    	g.add("Lzy");
    	g.add("ShiLao");
    	g.add("Ywq");
    	
    	g.set("Lc", "Lzy", 8);
    	g.set("Lc", "ShiLao", 5);
    	g.set("Lzy", "ShiLao", 3);
    	g.set("Ywq", "Lc", 2);
    	
    	System.out.println(g.toString());
    	///符合期望
    }
    /*
     * Testing Edge...
     */
    
    Edge<String> e = new Edge<String>("Lzy", "Lc", 8);
    
    // Testing strategy for Edge
    //   TODO 创建一些边，对其进行操作。
    
    // TODO tests for operations of Edge
    @Test
    public void Test3() {
    	assertEquals("expected equal", "Lzy", e.getSource());
    	assertEquals("expected equal", "Lc", e.getTarget());
    	assertEquals("expected equal", 8, e.getWeight());
    	
 
    	// 期望：
        // Lzy -> Lc 	weight: 8
    	System.out.println("\n");
    	System.out.println(e.toString());
    }
    
}
