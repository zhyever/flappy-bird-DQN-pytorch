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
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    private Set<String> vertices = new HashSet<>();
	private List<Edge<String>> edges = new ArrayList<>();
	private Map<String, Integer> map = new HashMap<String, Integer>();
	
	private Set<String> verticesReturn = new HashSet<>();
	private Map<String, Integer> mapReturn = new HashMap<String, Integer>();
	
    ConcreteVerticesGraph<String> g = new ConcreteVerticesGraph<String>();
    @Test
    public void test1() {
    	List<Vertex<String>> vertices1 = new ArrayList<>();
    	vertices1.add(new Vertex<String>("Lc"));
    	vertices1.add(new Vertex<String>("Lzy"));
    	vertices.add("Lc");
    	vertices.add("Lzy");
    	ConcreteVerticesGraph<String> g2 = new ConcreteVerticesGraph<String>(vertices1);
    	assertEquals("expected equal", vertices, g2.vertices());
    	vertices.clear();
    	///以上 另一种构造方法
    	
    	g.add("Lc");
    	g.add("Lzy");
    	g.add("ShiLao");
    	g.add("Ywq");
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
    	
    	
    	///试图填充分支
    	ConcreteVerticesGraph<String> g3 = new ConcreteVerticesGraph<String>();
    	vertices.clear();
    	edges.clear();
    	
    	vertices.add("Lc");
    	vertices.add("Lzy");
    	vertices.add("ShiLao");
    	edges.add(new Edge<String>("Lzy", "Lc", 8));
    	edges.add(new Edge<String>("ShiLao", "Lc", 5));
    	g3.add("Lc");
    	g3.add("Lzy");
    	g3.add("ShiLao");
    	g3.set("Lzy", "Lc", 8);
    	g3.set("ShiLao", "Lc", 5);
    	
    	assertEquals("expected equal", 0, g3.set("lalala", "Lc", 0));
    	
    }
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
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
     * Testing Vertex...
     */
    
    Vertex<String> v = new Vertex<String>();
    Vertex<String> V = new Vertex<String>();
    // Testing strategy for Vertex
    //   TODO 创建vertex 对其进行操作 直接新创建一个v然后对其属性进行赋值(可变
    //测试equals方法。
   
    // TODO tests for operations of Vertex
    @Test
    public void Test3() {
    	V.target = "Lzy";
    	v.target = "Lzy"; //体现可变
    	v.vertices.put("Lc", 8);
    	///测试方法equals
    	assertEquals("expected equal", true, V.equals(v)); 
    	
    	///测试toString
    	System.out.println(v.toString());
    }
}
