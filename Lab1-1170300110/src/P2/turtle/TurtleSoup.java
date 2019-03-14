/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;

import java.util.ArrayList;
import java.util.HashSet;

public class TurtleSoup {

	/**
	 * Draw a square.
	 * 
	 * @param turtle     the turtle context
	 * @param sideLength length of each side
	 */
	public static void drawSquare(Turtle turtle, int sideLength) {
		///直走 转90 直走 转90 直走 转90 直走 可以形成一个正方形
		turtle.forward(sideLength);
		turtle.turn(90);
		turtle.forward(sideLength);
		turtle.turn(90);
		turtle.forward(sideLength);
		turtle.turn(90);
		turtle.forward(sideLength);
	}

	/**
	 * Determine inside angles of a regular polygon.
	 * 
	 * There is a simple formula for calculating the inside angles of a polygon; you
	 * should derive it and use it here.
	 * 
	 * @param sides number of sides, where sides must be > 2
	 * @return angle in degrees, where 0 <= angle < 360
	 */
	public static double calculateRegularPolygonAngle(int sides) {
		/// 多边形内角大小
		/// 直接根据公式即可
		return (double) 180 * (double) (sides - 2) / (double) sides;
	}

	/**
	 * Determine number of sides given the size of interior angles of a regular
	 * polygon.
	 * 
	 * There is a simple formula for this; you should derive it and use it here.
	 * Make sure you *properly round* the answer before you return it (see
	 * java.lang.Math). HINT: it is easier if you think about the exterior angles.
	 * 
	 * @param angle size of interior angles in degrees, where 0 < angle < 180
	 * @return the integer number of sides
	 */
	public static int calculatePolygonSidesFromAngle(double angle) {
		/// 通过外角的关系来计算
		return (int) Math.ceil((double) 360 / (double) (180 - angle));
	}

	/**
	 * Given the number of sides, draw a regular polygon.
	 * 
	 * (0,0) is the lower-left corner of the polygon; use only right-hand turns to
	 * draw.
	 * 
	 * @param turtle     the turtle context
	 * @param sides      number of sides of the polygon to draw
	 * @param sideLength length of each side
	 */
	public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
		double angle;
		/// 转过的角度通过调用上面的函数得到
		angle = calculateRegularPolygonAngle(sides);

		// 向右出发
		turtle.turn(90);

		for (int i = 1; i <= sides; i++) {
			turtle.forward(sideLength);
			/// 调头 hh
			turtle.turn(90);
			turtle.turn(90);
			turtle.turn(angle);
		}
	}

	/**
	 * Given the current direction, current location, and a target location,
	 * calculate the Bearing towards the target point.
	 * 
	 * The return value is the angle input to turn() that would point the turtle in
	 * the direction of the target point (targetX,targetY), given that the turtle is
	 * already at the point (currentX,currentY) and is facing at angle
	 * currentBearing. The angle must be expressed in degrees, where 0 <= angle <
	 * 360.
	 *
	 * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
	 * 
	 * @param currentBearing current direction as clockwise from north
	 * @param currentX       current location x-coordinate
	 * @param currentY       current location y-coordinate
	 * @param targetX        target point x-coordinate
	 * @param targetY        target point y-coordinate
	 * @return adjustment to Bearing (right turn amount) to get to target point,
	 *         must be 0 <= angle < 360
	 */
	public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY, int targetX,
			int targetY) {
		int differenceX = Math.abs(currentX - targetX);
		int differenceY = Math.abs(currentY - targetY);

		/// 首先是一些特殊情况 两点x相等，y相等分别讨论
		if (currentX < targetX) {
			if (currentY == targetY) {
				if (currentBearing <= 90) {
					return 90 - currentBearing;
				} else {
					return (360 - currentBearing) + 90;
				}
			}
		}

		if (currentX > targetX) {
			if (currentY == targetY) {
				if (currentBearing <= 270) {
					return 270 - currentBearing;
				} else {
					return (360 - currentBearing) + 270;
				}

			}
		}

		if (currentY < targetY) {
			if (currentX == targetX) {
				if (currentBearing == 0) {
					return 0;
				} else {
					return 360 - currentBearing;
				}
			}
		}

		if (currentY > targetY) {
			if (currentX == targetX) {
				if (currentBearing <= 180) {
					return 180 - currentBearing;
				} else {
					return (360 - currentBearing) + 180;
				}
			}
		}

		double tan = differenceY / differenceX;
		double angle = Math.atan(tan) * 57.29578;

		// 如果是特殊的tan = 1 返回45°。当作一个整数
		if (tan == 1) {
			angle = 45;
		} else if (tan == -1) {
			angle = -45;
		}

		///分情况讨论
		///如果目标在右上方
		if (targetX > currentX && targetY > currentY) {
			if (currentBearing <= 90 - angle) {
				return 90 - (angle + currentBearing);
			} else {
				return (360 - currentBearing) + (90 - angle);
			}
		} else if (targetX > currentX && targetY < currentY) {
			///如果目标在右下方
			if (currentBearing <= 90 + angle) {
				return 90 - currentBearing + angle;
			} else {
				return 90 + 360 - currentBearing + angle;
			}
		} else if (targetX < currentX && targetY > currentY) {
			///如果目标在左上方
			if (currentBearing <= 90 + 180 + angle) {
				return 90 - currentBearing + 180 + angle;
			} else {
				return (360 - currentBearing) + (3 * 90) + angle;
			}
		} else if (targetX < currentX && targetY < currentY) {
			///如果目标在左下方
			if (currentBearing <= 180 - angle + 90) {
				return (180 - angle) + (90 - currentBearing);
			} else {
				return (360 - currentBearing) + (2 * 90) + (90 - angle);
			}
		}

		return 0;
	}

	/**
	 * Given a sequence of points, calculate the Bearing adjustments needed to get
	 * from each point to the next.
	 * 
	 * Assumes that the turtle starts at the first point given, facing up (i.e. 0
	 * degrees). For each subsequent point, assumes that the turtle is still facing
	 * in the direction it was facing when it moved to the previous point. You
	 * should use calculateBearingToPoint() to implement this function.
	 * 
	 * @param xCoords list of x-coordinates (must be same length as yCoords)
	 * @param yCoords list of y-coordinates (must be same length as xCoords)
	 * @return list of Bearing adjustments between points, of size 0 if (# of
	 *         points) == 0, otherwise of size (# of points) - 1
	 */
	public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
		int nums = xCoords.size();
		double turn;

		//这个函数中 疯狂调用上边的那个函数即可。
		//先从(0,0)到(1,1)
		turn = calculateBearingToPoint(0, xCoords.get(0), yCoords.get(0), xCoords.get(1), yCoords.get(1));

		List<Double> turningSet = new ArrayList<Double>();
		turningSet.add(turn);

		for (int i = 1; i <= nums - 2; i++) {
			turn = calculateBearingToPoint(turn, xCoords.get(i), yCoords.get(i), xCoords.get(i + 1),
					yCoords.get(i + 1));
			turningSet.add(turn);
		}

		return turningSet;
	}

	/**
	 * Given a set of points, compute the convex hull, the smallest convex set that
	 * contains all the points in a set of input points. The gift-wrapping algorithm
	 * is one simple approach to this problem, and there are other algorithms too.
	 * 
	 * @param points a set of points with xCoords and yCoords. It might be empty,
	 *               contain only 1 point, two points or more.
	 * @return minimal subset of the input points that form the vertices of the
	 *         perimeter of the convex hull
	 */
	public static Set<Point> convexHull(Set<Point> points) {

		/// 创建结果set
		Set<Point> result = new HashSet<Point>();
		
		/// 辅助数组s
		Point[] s = points.toArray(new Point[0]);
				
		/// 得到这个LIST的长度
		int nums = points.size();
		/// 处理特殊情况：只有零个、一个、两个时直接返回即可。
		if (nums == 0 || nums == 1 || nums == 2) {
			return points;
		}

		int cnt = 0;
		double x = 0;
		double y = 0;

		/// 寻找最左下角的那个点

		for (Point i : points) {
			if (cnt == 0) {
				/// 第一次的时候随机找点
				cnt = 1;
				x = i.x();
				y = i.y();
			} else {
				if (i.x() < x || (i.x() == x && i.y() < y)) {
					/// 如果找到一个更靠近左下角的坐标那么就更新x,y
					x = i.x();
					y = i.y();
				}
			}
		}
		
		///定义 结束点、正在进行操作的点、起始点
		Point endpoint;
		Point startPoint = new Point(x,y);
		Point pointOnHull = new Point(x,y);
		
		
		
		int i = 0;
		///当然操作的次数要小于等于原来points中的元素数
		while(i < nums){
			///将endpoint暂时定为S[0]
			endpoint = s[0];
			///遍历其他的点
			for(int j = 1; j < nums; j++) {
				///如果endpoint恰巧是正在操作的点，赶紧用其他的点代替即endpoint = s[j]
				///通过向量的叉乘，判断如果在外部还有别的点那么就要进行endpoint = s[j]
				///这里要判断共线 如果两点共线 要选最远的那个点
//				if(endpoint == pointOnHull
//						||compute(pointOnHull, endpoint, s[j]) > 0) {
//					endpoint = s[j];
//				}
				if(endpoint == pointOnHull
						||compute(pointOnHull, endpoint, s[j]) >= 0) {
					if(compute(pointOnHull, endpoint, s[j]) > 0) {
						endpoint = s[j];
					}else if(compute(pointOnHull, endpoint, s[j]) == 0) {
						if(distance(pointOnHull,s[j]) > distance(pointOnHull,endpoint)) {
							endpoint = s[j];
						}else {
							// do noting
						}
					}
					
				}
			}
			i++;
			pointOnHull = endpoint;
			///更新正在操作的点
			result.add(endpoint);
			///加入结果集合中
			if(startPoint == endpoint) {
				break;
			}
		}
				
		return result;
	}
	/**
	 * 计算两点距离
	 * @param A
	 * @param B
	 * @return AB两点距离的平方
	 */
	
	public static double distance(Point A, Point B) {
		double dx = Math.abs(A.x() - B.x());
		double dy = Math.abs(A.y() - B.y());
		
		return Math.pow(dx, 2) + Math.pow(dy, 2);
	}
	
	/**
	 * ///计算向量叉乘函数
	 * @param pointOnHull
	 * @param i
	 * @param j
	 * @return 向量叉乘结果
	 */
	public static double compute(Point pointOnHull, Point i, Point j){
		//x1 y1 第一个向量 pointOnHUll to i
		double x1;
		double y1;
		x1 = i.x() - pointOnHull.x();
		y1 = i.y() - pointOnHull.y();
		
		//x2 y2 第二个向量 pointOnHUll to j
		double x2;
		double y2;
		x2 = j.x() - pointOnHull.x();
		y2 = j.y() - pointOnHull.y();
		
		///算法很简单，计算 ax ay bx by的一个二阶行列式 注意当前的 叉乘 另一个
		double result = x1 * y2 - x2 * y1;
		return result;
		
		
	}

	/**
	 * Draw your personal, custom art.
	 * 
	 * Many interesting images can be drawn using the simple implementation of a
	 * turtle. For this function, draw something interesting; the complexity can be
	 * as little or as much as you want.
	 * 
	 * @param turtle the turtle context
	 */
	public static void drawPersonalArt(Turtle turtle) {
		
		int x = 20;
		int i = 1;
		int j = 1;
		while(j <= 30) {
			while(i <= 10)
			{
				turtle.forward(x);
				turtle.turn(90);
				turtle.color(PenColor.BLUE);
				turtle.forward(x);
				turtle.turn(90);
				turtle.forward(x);
				turtle.color(PenColor.RED);
				turtle.forward(x);
				turtle.turn(90);
				turtle.forward(x);
				turtle.turn(90);
				turtle.color(PenColor.BLACK);
				turtle.forward(2*x);
				turtle.turn(270);
				turtle.color(PenColor.GRAY);
				turtle.forward(x);
				turtle.turn(270);
				turtle.forward(x);
				turtle.color(PenColor.CYAN);
				turtle.forward(x);
				turtle.turn(270);
				turtle.forward(x);
				turtle.turn(270);
				turtle.color(PenColor.BLACK);
				turtle.forward(x);
				turtle.turn(36);
				i++;
			}
			x = 20 + j * 5;
			i = 0;
			j++;
		}
		
		
		
		
	}

	/**
	 * Main method.
	 * 
	 * This is the method that runs when you run "java TurtleSoup".
	 * 
	 * @param args unused
	 */
	public static void main(String args[]) {
		DrawableTurtle turtle = new DrawableTurtle();

		drawPersonalArt(turtle);
		// draw the window
		turtle.draw();
	}

}
