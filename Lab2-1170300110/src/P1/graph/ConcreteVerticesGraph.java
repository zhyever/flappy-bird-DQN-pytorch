/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {

	private final List<Vertex<L>> vertices = new ArrayList<>();

	// Abstraction function:
	// TODO 构建一个图，图中所有元素为顶点集。每个顶点含有该点名称(target 指向该点的source、weight建立起来的map
	// Representation invariant:
	// TODO 其中每个顶点集的target不能相同，任意一个顶点集中的顶点的map中的source不能与该点target相同，weight要大于零。
	// Safety from rep exposure:
	// TODO 都建立的副本然后返回

	// TODO constructor
	public ConcreteVerticesGraph(List<Vertex<L>> vertices) {
		for(Vertex<L> v : vertices) {
			this.vertices.add(v);
		}
		checkRep();
	}
	public ConcreteVerticesGraph() {
		this.vertices.clear();
		checkRep();
	}
	
	// TODO checkRep
	private void checkRep() {
		for(Vertex<L> i : vertices) {
			Iterator<Map.Entry<L, Integer>> it = i.vertices.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<L, Integer> entry = it.next();
				assert (entry.getValue() > 0);
				assert (entry.getKey().equals(i.target) != true);
			}
		}
	}
		
	/// 用string.equel
	@Override
	public boolean add(L vertex) {
		///如果空
		Vertex<L> v = new Vertex<L>(vertex);
		if(vertices.isEmpty()) {
			vertices.add(v);
			checkRep();
			return true;
		}else {
			for(Vertex<L> i : vertices) {
				//发现有重复的就不加了返回false
				if(i.target.equals(vertex)) {
					checkRep();
					return false;
				}
			}
			vertices.add(v);
			checkRep();
			return true;
		}
	}

	@Override
	public int set(L source, L target, int weight) {
		if(weight != 0) {
			Vertex<L> v = new Vertex<L>(source, target, weight);
			for(Vertex<L> i : vertices) {
				///同一个顶点的
				if(i.equals(v)) {
					if(i.vertices.containsKey(source)) {
						i.vertices.replace(source, weight);
					}
					else {
						i.vertices.put(source, weight);
					}
				}
			}
			checkRep();
			return 0;
		}else {
			int ret = 0;
			Vertex<L> v = new Vertex<L>(source, target, 1);
			for(Vertex<L> i : vertices) {
				///仅仅是为了好操作
				if(i.equals(v)) {
					///同一个顶点
					if(i.vertices.containsKey(source)) {
						ret = i.vertices.get(source).intValue();
						///删除掉这条边
						i.vertices.remove(source);
					}
				}
			}
			checkRep();
			return ret;
		}
	}

	@Override
	public boolean remove(L vertex) {
		boolean flag = false;
		
		Iterator<Vertex<L>> it = vertices.iterator();
		while(it.hasNext()){
			///遍历顶点集合
			///如果定点就是要remove的那个vertex
		    Vertex<L> x = it.next();
		    if(x.target.equals(vertex)) {
				it.remove();
				flag = true;
			}
		  ///如果某个顶点的source是哪个要remove的vertex
			if(x.vertices.containsKey(vertex)) {
				x.vertices.remove(vertex);
				flag = true;
			}
		}
		checkRep();
		return flag;
	}

	@Override
	public Set<L> vertices() {
		Set<L> v = new HashSet<>();
		for(Vertex<L> i : vertices) {
			v.add(i.target);
		}
		checkRep();
		return v;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		Map<L, Integer> map = new HashMap<L, Integer>();
		for(Vertex<L> i : vertices) {
			if(i.target.equals(target)) {
				Iterator<Map.Entry<L, Integer>> it = i.vertices.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<L, Integer> entry = it.next();
					map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		checkRep();
		return map;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		Map<L, Integer> map = new HashMap<L, Integer>();
		for(Vertex<L> i : vertices) {
			if(i.vertices.containsKey(source)) {
				map.put(i.target, i.vertices.get(source));
			}
		}
		checkRep();
		return map;
	}

	// TODO toString()
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		
		str.append("Vertices:\n");
		for(Vertex<L> i : vertices) {
			str.append(i.target);
			str.append(" ");
		}
		str.append("\n");
		str.append("Edges:\n");
		for(Vertex<L> v : vertices) {
			str.append(v.toString());
			str.append("\n");
		}

		String ret = str.toString();
		checkRep();
		return ret;
	}
	
//	public static void main(String[] args) {
//		ConcreteVerticesGraph<String> g = new ConcreteVerticesGraph<String>();
//		g.add("Lc");
//    	g.add("Lzy");
//    	g.add("ShiLao");
//    	g.add("Ywq");
//    	
//    	g.set("Lc", "Lzy", 8);
//    	g.set("Lc", "ShiLao", 5);
//    	g.set("Lzy", "ShiLao", 3);
//    	g.set("Ywq", "Lc", 2);
//    	System.out.println(g.toString());
//	}
//	
}

/**
 * TODO specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

	// TODO fields
	// source - weight
	public Map<L, Integer> vertices = new HashMap<L, Integer>();
	// target
	public L target;
	
	// Abstraction function:
	// TODO 给source、target、weight 创建vertex 该顶点集中的targe既为参数target、map建立source到target的weight的映射。
	// Representation invariant:
	// TODO target与vertices中的key不能相同。vertices中的weight必须大于零
	// Safety from rep exposure:
	// TODO 为可变类型，属性都存为public形式

	// TODO constructor
	public Vertex() {
		vertices.clear();
		checkRep();
	}
	
	public Vertex(L name) {
		vertices.clear();
		//还没有sources
		this.target = name;
		checkRep();
	}
	
	public Vertex(L name, L target, int weight) {
		vertices.clear();
		vertices.put(name, new Integer(weight));
		this.target = target;
		checkRep();
	}

	// TODO checkRep
	private void checkRep() {
		Iterator<Map.Entry<L, Integer>> it = vertices.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<L, Integer> entry = it.next();
			assert (entry.getValue() > 0);
			assert (entry.getKey().equals(this.target) != true);
		}
	}

	// TODO methods
	
	@Override
	public boolean equals(Object e) {
		if( ((Vertex<?>) e).target.equals(this.target)) {
			checkRep();
			return true;
		}else {
			checkRep();
			return false;
		}
		
	}
	
	// TODO toString()
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		int num = 0;
		Iterator<Map.Entry<L, Integer>> it = vertices.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<L, Integer> entry = it.next();
			if(num == 0) {
				str.append(entry.getKey());
				str.append(" -> ");
				str.append(this.target);
				str.append("\t");
				str.append("weight: ");
				str.append(entry.getValue().toString());
				num++;
			}else {
				str.append("\n");
				str.append(entry.getKey());
				str.append(" -> ");
				str.append(this.target);
				str.append("\t");
				str.append("weight: ");
				str.append(entry.getValue().toString());
				num++;
			}
			
		}
		checkRep();
		return str.toString();
	}

}
