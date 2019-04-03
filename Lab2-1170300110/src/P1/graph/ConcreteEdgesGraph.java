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
public class ConcreteEdgesGraph<L> implements Graph<L> {

	private final Set<L> vertices = new HashSet<>();
	private final List<Edge<L>> edges = new ArrayList<>();

	// Abstraction function:
	// TODO 若有 x指向y x到y的权重为weight 那么存在一条边e：e.source - x  e.target - y e.weight - weight
	// vertices 储存图中所有顶点
	// edges 储存图中的所有边
	// Representation invariant:
	// TODO edges边集中没有重复元素，即一个人到另一个人不会有多条路径
	// 没有一条边会自己指向自己 即不会出现 e.source = e.target
	// Safety from rep exposure:
	// TODO vertices sources targets都是返回一个新的(new)副本
	// 每个属性都是 private final类型

	// TODO constructor

	public ConcreteEdgesGraph(Set<L> vertices, List<Edge<L>> edges) {
		for(L s : vertices) {
			this.vertices.add(s);
		}
		for(Edge<L> e : edges) {
			this.edges.add(e);
		}
		checkRep();
	}
	
	//重载
	public ConcreteEdgesGraph() {
		this.vertices.clear();
		this.edges.clear();
		checkRep();
	}
	
	// TODO checkRep
	// 无重复边
	private void checkRep() {
		boolean flag = false;
		Edge<L> e;
		for(int i = 0; i < edges.size() - 1; i++) {
			e = edges.get(i);
			for(int j = i + 1; j < edges.size(); j++) {
				if(e.equals(edges.get(j))) {
					flag = true;
				}
			}
		}
		assert (flag == false);
		
	}


	@Override
	public boolean add(L vertex) {
		if (vertices.contains(vertex) == true) {
			checkRep();
			return false;
		} else {
			checkRep();
			return vertices.add(vertex);

		}
	}

	@Override
	public int set(L source, L target, int weight) {
//		if(source.equals(target)) {
//			return 99;
//			///emmm 不合法的加边
//		}
		if (weight != 0) {
			Edge<L> e = new Edge<L>(source, target, weight);
			boolean flag = false;
			for (int index = 0; index < edges.size(); index++) {
				if (edges.get(index).equals(e)) {
					// 删除之后再增加
					edges.remove(index);
					edges.add(e);
					flag = true;
				}
			}
			if (flag == false) {
				edges.add(e);
			}
			checkRep();
			return 0;
		}else {
			int ret = 0;
			///为了方便查询 设置为1否则edge repCheck过不去！
			Edge<L> e = new Edge<L>(source, target, 1);
			for (int index = 0; index < edges.size(); index++) {
				if (edges.get(index).equals(e)) {
					//记录值
					ret = edges.get(index).getWeight();
					//删掉
					edges.remove(index);
					//直接over
					break;
				}
			}
			checkRep();
			return ret;
		}
	}

	@Override
	public boolean remove(L vertex) {
		boolean flag = false;
		
		Iterator<Edge<L>> it = edges.iterator();
		while(it.hasNext()){
			Edge<L> x = it.next();
			if(x.getSource().equals(vertex)||x.getTarget().equals(vertex)) {
				it.remove();
				flag = true;
			}
		}
		
		if(vertices.contains(vertex)) {
			vertices.remove(vertex);
			flag = true;
		}
		checkRep();
		return flag;
	}

	@Override
	public Set<L> vertices() {
		/// 不可改变
		Set<L> v = new HashSet<>();
		for(L s : vertices) {
			v.add(s);
		}
		/// 创立一个副本
		checkRep();
		return v;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		Map<L, Integer> map = new HashMap<L, Integer>();
		for(Edge<L> e : edges) {
			if(e.getTarget().equals(target)) {
				map.put(e.getSource(), e.getWeight());
			}
		}
		checkRep();
		return map;
		
	}

	@Override
	public Map<L, Integer> targets(L source) {
		Map<L, Integer> map = new HashMap<L, Integer>();
		for(Edge<L> e : edges) {
			if(e.getSource().equals(source)) {
				map.put(e.getTarget(), e.getWeight());
			}
		}
		checkRep();
		return map;
	}

	// TODO toString()
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		int index = 0;
		
		str.append("Vertices:\n");
		for(L i : vertices) {
			str.append(i);
			str.append(" ");
			index++;
		}
		str.append("\n");
		str.append("Edges:\n");
		for(int  i = 0; i < edges.size(); index = index + 2, i++) {
			str.append(edges.get(i).toString());
			str.append("\n");
		}
		
		
		String ret = str.toString();
		checkRep();
		return ret;
	}
	
//	public static void main(String[] args) {
//		Set<String> vertices = new HashSet<>();
//		List<Edge<String>> edges = new ArrayList<>();
//		ConcreteEdgesGraph<String> g = new ConcreteEdgesGraph<String>(vertices, edges);
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

}

/**
 * TODO specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge<L> {

	// TODO fields 变量
	private final L source;
	private final L target;
	private final int weight;
	// 起始点 终点 权值

	// Abstraction function: 抽象函数
	// TODO 图中有x指向y 权值为weight 那么有Edge e e.source = x e.target = y e.weight = weight
	// Representation invariant: 不变量
	// TODO weight > 0; 每个边的权值不能为负
	// source 与 target不相同 没有一条边可以指向自己
	// this.source.equal(this.target) != true;
	// Safety from rep exposure: 如何防止暴露
	// TODO 对于source target weight 使用 private final 

	// TODO constructor 构造函数 //需要啥写啥
	public Edge(L source, L target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
		checkRep();
	}
	// TODO checkRep 检查那些不变的量 检查不变量
	// 不能自己指向自己 权值为正
	private void checkRep() {
		assert (this.weight > 0);
		assert (this.source.equals(this.target) != true);
	}

	// TODO methods 边里实现一些方法 保护

	public int getWeight(){
		checkRep();
		return this.weight;
	}
	public L getSource(){
		checkRep();
		return this.source;
	}
	public L getTarget(){
		checkRep();
		return this.target;
	}
	
	
	@Override
	public boolean equals(Object e) {
		if( ((Edge<?>) e).getSource().equals(this.source) && 
				((Edge<?>) e).getTarget().equals(this.target)) {
			checkRep();
			return true;
		}else {
			checkRep();
			return false;
		}
		
	}
	
	// TODO toString() 方便一些
	@Override
	public String toString() {
		String res;
		res = this.source + " -> " + this.target + " \tweight: " + this.weight;
		checkRep();
		return res;
	}

}
