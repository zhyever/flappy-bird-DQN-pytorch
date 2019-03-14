/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even
 * exist as a key in the map; this is true even if A is followed by other people
 * in the network. Twitter usernames are not case sensitive, so "ernie" is the
 * same as "ERNie". A username should appear at most once as a key in the map or
 * in any given map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

	/**
	 * Guess who might follow whom, from evidence found in tweets.
	 * 
	 * @param tweets a list of tweets providing the evidence, not modified by this
	 *               method.
	 * @return a social network (as defined above) in which Ernie follows Bert if
	 *         and only if there is evidence for it in the given list of tweets. One
	 *         kind of evidence that Ernie follows Bert is if Ernie
	 * @-mentions Bert in a tweet. This must be implemented. Other kinds of evidence
	 *            may be used at the implementor's discretion. All the Twitter
	 *            usernames in the returned social network must be either authors
	 *            or @-mentions in the list of tweets.
	 */
	public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
		String name;
		Set<String> nameSet = null;
		Map<String, Set<String>> follow = new ConcurrentHashMap<String, Set<String>>();
		int times = 0;

		// Set<String> getMentionedUsers(List<Tweet> tweets)

		List<Tweet> tweetsOnes = new ArrayList<Tweet>();

		for (Tweet i : tweets) {
			name = i.getAuthor();
			///搜集所有作者名字相同的推特

			if (times == 0) {
				///搜集所有作者名字相同的推特
				for (Tweet j : tweets) {
					if (j.getAuthor().toLowerCase().equals(i.getAuthor().toLowerCase())) {
						tweetsOnes.add(j);
					}
				}
				//搜集完一起加进去
				nameSet = Extract.getMentionedUsers(tweetsOnes);
				follow.put(name, nameSet);
				times++;
			} else {
				if (!follow.containsKey(name.toLowerCase())) {
					for (Tweet j : tweets) {
						if (j.getAuthor().toLowerCase().equals(i.getAuthor().toLowerCase())) {
							tweetsOnes.add(j);
						}
					}
					nameSet = Extract.getMentionedUsers(tweetsOnes);
					follow.put(name, nameSet);
				}
			}
			//每次进行一个循环后将这个tweets集合清空
			tweetsOnes.clear();
		}

		/// smarter!!!
		/// hashtags!!!
		Set<String> hashtags1;
		Set<String> hashtags2;
		String name1;
		String name2;
		for (Tweet i : tweets) {
			name1 = i.getAuthor();
			hashtags1 = getHashtags(i);
			for (Tweet j : tweets) {
				name2 = j.getAuthor();
				/// 在不相同的tweet中遍历
				if (!j.equals(i)) {
					hashtags2 = getHashtags(j);
					/// 得到里面的hashtag
					if (ifContainEquel(hashtags1, hashtags2)) {
						/// 如果含有相同的那么要增加啦~
						/// 但是需要在原映射中不存在 这个关系 所以要再判断。
						/// 只需要增加一半，另一半将在for循环中再次加入
						nameSet = follow.get(name1);
						if (!nameSet.contains(name2)) {
							nameSet.add(name2);
							follow.put(name1, nameSet);
						}
					}
				}
			}
		}

		/// smarter!!!
		/// a<-->b   b<--->c  则 a <--> c
		Map<String, Set<String>> followNew = new ConcurrentHashMap<String, Set<String>>();
		followNew = follow;

		Set<String> nameSet1;
		Set<String> nameSet2;
		Set<String> nameSet3;
		Set<String> nameSetNew1 = new HashSet<String>();
		Set<String> nameSetNew2 = new HashSet<String>();
		
		/// 遍历A的所有提及到的人，如果其中有一个B，然后遍历B所有提及到的人。如果有A说明 A--B
		// 然后遍历B提及到的人 同理 得出 B--C 那么就有 A--C
		///循环/if嵌套的有点多
		for (Tweet i : tweets) {
			name1 = i.getAuthor();
			nameSet1 = Extract.getMentionedUsers(Filter.writtenBy(tweets, name1));
			if (!nameSet1.isEmpty()) {
				///对A提及到的人的遍历
				for (String j : nameSet1) {
					if (!j.toLowerCase().equals(name1.toLowerCase())) {
						boolean flag = false;
						///设立flag如果发现有一个互相关注关系 
						if (ifContainEachOther(follow, name1, j)) {
							flag = true;
						}
						if (flag == true) {
							///如果有互相关注关系 再找那个人对其他人是否有类似的相互关注关系
							boolean flag2 = false;
							nameSet2 = Extract.getMentionedUsers(Filter.writtenBy(tweets, j));
							if (!nameSet2.isEmpty()) {
								for (String k : nameSet2) {
									if (!k.toLowerCase().equals(name1.toLowerCase())) {
										///如果找到了~
										if (ifContainEachOther(follow, j, k)) {
											flag2 = true;
										}
										if (flag2 == true) {
											///应该将其加入到新的映射里。
											nameSet3 = Extract.getMentionedUsers(Filter.writtenBy(tweets, k));
											/// name1 - i , i - j 互相
											/// 因为无法直接修改 要创建一个副本，然后增加进去.
											/// 我也不知道为啥不能修改？
											/// 因为遍历无法修改？
											/// 百度一下好像是这样的。
											if (!nameSet1.contains(k.toLowerCase())) {
												nameSetNew1.clear();
												for(String m : nameSet1) {
													nameSetNew1.add(m);
												}
												nameSetNew1.add(k.toLowerCase());
												followNew.put(name1, nameSetNew1);
											}

											if (!nameSet3.contains(name1.toLowerCase())) {
												nameSetNew2.clear();
												for(String m : nameSet3) {
													nameSetNew2.add(m);
												}
												nameSetNew2.add(name1.toLowerCase());
												followNew.put(k, nameSetNew2);
											}

										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		//对转发进行处理 如果A 跟随 B  B 跟随 C  B转发了一条C的推特，那么可以猜到A跟随C
		//辅助函数 mapRetweet 返回某个作者转发的所有推特的作者集合(为一个map映射
		Map<String, Set<String>> mapRetweet = retweetMap(tweets);
		Map<String, Set<String>> followNewNew = new ConcurrentHashMap<String, Set<String>>();
		Set<String> nameSetNew = new HashSet<String>();
		followNewNew = followNew;
		
		for (Tweet i : tweets) {
			name1 = i.getAuthor();
			nameSet1 = Extract.getMentionedUsers(Filter.writtenBy(tweets, name1));
			if (!nameSet1.isEmpty()) {
				///对A提及到的人进行遍历
				for (String j : nameSet1) {
					if (!j.toLowerCase().equals(name1.toLowerCase())) {
						if(mapRetweet.containsKey(j)) {
							///在B转发的tweets的作者集合中遍历
							nameSet2 = mapRetweet.get(j);
							for(String k : nameSet2) {
								///添加新的跟随关系
								if(!k.toLowerCase().equals(name1.toLowerCase())) {
									if(!nameSet1.contains(k.toLowerCase())) {
										nameSetNew.clear();
										for(String m : nameSet1) {
											nameSetNew.add(m);
										}
										nameSetNew.add(k.toLowerCase());
										followNewNew.put(name1, nameSetNew);
									}
								}
							}
						}
					}
				}
			}
		}
		
		

		return followNewNew;
	}

	/**
	 * 某个推特作者 ---- 转发的推特作者的映射
	 * @param tweets
	 * @return 一个map图
	 */
	public static Map<String, Set<String>> retweetMap(List<Tweet> tweets) {
		Map<String, Set<String>> map = new ConcurrentHashMap<String, Set<String>>();
		int cnt = 0;
		String name;
		String namedoing;
		Set<String> nameRT = new HashSet<String>();
		String text;
		///循环遍历所有推特得到作者 --> 转发推特作者的关系集合
		for(Tweet i : tweets) {
			if(cnt == 0) {
				///第一次进入循环
				name = i.getAuthor();
				text = i.getText();
				if(text.charAt(0)=='R'&&text.charAt(1)=='T'&&text.charAt(2)==' '&&text.charAt(3)=='@') {
					namedoing = text.split(" ")[1];
					namedoing = namedoing.split("@")[1];
					namedoing = namedoing.split(":")[0];
					//对 RT @xxx: 进行简单的处理
					nameRT.add(namedoing);
					//加入map中
					map.put(name,nameRT);
					cnt++;
				}
				
			}else {
				name = i.getAuthor();
				text = i.getText();
				if(text.charAt(0)=='R'&&text.charAt(1)=='T'&&text.charAt(2)==' '&&text.charAt(3)=='@') {
					if(map.containsKey(name.toLowerCase())) {
						///与上边同理
						nameRT = map.get(name);
						namedoing = text.split(" ")[1];
						namedoing = namedoing.split("@")[1];
						namedoing = namedoing.split(":")[0];
						nameRT.add(namedoing);
						map.put(name,nameRT);
					}else {
						nameRT.clear();
						namedoing = text.split(" ")[1];
						namedoing = namedoing.split("@")[1];
						namedoing = namedoing.split(":")[0];
						nameRT.add(namedoing);
						map.put(name,nameRT);
					}
				}
			}
		}
		return map;
	}
	/**
	 * 判断一个图中 x 的映射 集合中是否存在 y 有y的映射的集合中含有x
	 * 显然是为了方便 判断两个人之间是否互相关注了
	 * @param map
	 * @param x
	 * @param y
	 * @return 真假
	 */
	public static boolean ifContainEachOther(Map<String, Set<String>> map, String x, String y) {
		Set<String> nameSet1;
		Set<String> nameSet2;
		if (map.containsKey(x)) {
			nameSet1 = map.get(x);
		} else {
			return false;
		}
		if (map.containsKey(y)) {
			nameSet2 = map.get(y);
		} else {
			return false;
		}

		if (nameSet1.isEmpty() || nameSet2.isEmpty()) {
			return false;
		} else {
			for (String i : nameSet1) {
				if (i.toLowerCase().equals(y.toLowerCase())) {
					for (String j : nameSet2) {
						if (j.toLowerCase().equals(x.toLowerCase())) {
							return true;
						}
					}
				}
			}
		}

		/// can't get here
		return false;

	}

	/**
	 * 判断SET中是否存在某一个String元素
	 * @param x
	 * @param name
	 * @return 真假
	 */
	public static boolean ifContain2(Set<String> x, String name) {
		boolean flag = false;
		for (String i : x) {
			if (i.toLowerCase().equals(name.toLowerCase())) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 判断两个set String 中是否含有相同的String
	 * @param x
	 * @param y
	 * @return 真假
	 */
	public static boolean ifContainEquel(Set<String> x, Set<String> y) {
		boolean falg = false;
		for (String i : x) {
			for (String j : y) {
				if (j.toLowerCase().equals(i.toLowerCase())) {
					falg = true;
				}
			}
		}
		return falg;
	}

	/**
	 * 用来处理#情况
	 * @param tweet
	 * @return返回一条推特中的#到的hashtags。
	 */
	public static Set<String> getHashtags(Tweet tweet) {
		Set<String> result = new HashSet<String>();
		String[] text = tweet.getText().split(" ");
		String[] hashtags;
		for (String i : text) {
			if (!i.isEmpty()) {
				if (i.charAt(0) == '#') {
					hashtags = i.split("#");
					for (String j : hashtags) {
						if (!j.isEmpty()) {
							result.add(j);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Find the people in a social network who have the greatest influence, in the
	 * sense that they have the most followers.
	 * 
	 * @param followsGraph a social network (as defined above)
	 * @return a list of all distinct Twitter usernames in followsGraph, in
	 *         descending order of follower count.
	 */
	public static List<String> influencers(Map<String, Set<String>> followsGraph) {

		List<String> result = new ArrayList<String>();
		Set<Map.Entry<String, Set<String>>> entries = followsGraph.entrySet();
		Iterator<Map.Entry<String, Set<String>>> iteratorMap = entries.iterator();
		Set<String> names;

		///维护一个名字、被@次数的数组
		String[] name = new String[4000];
		int[] nums = new int[4000];
		int cnt = 0;
		int index = 0;
		///数组边界 方便遍历、排序
		int bounder = 0;

		///遍历followsGraph
		while (iteratorMap.hasNext()) {
			Map.Entry<String, Set<String>> next = iteratorMap.next();
			if (cnt == 0) {
				///第一次进入
				names = next.getValue();
				for (String i : names) {
					name[index] = i;
					nums[index] = 1;
					index++;
					bounder++;
					cnt++;
				}
			} else {
				/// names 是该条推特含有的提及到的人的名字
				names = next.getValue();
				for (String i : names) {
					if (ifContain(name, i, bounder)) {
						///如果有了就找到那个人的对应的下标
						index = getIndex(name, i, bounder);
						///被提及数+1
						nums[index]++;
					} else {
						///如果没有就再创建新的
						name[bounder] = i;
						nums[bounder] = 1;
						///注意边界+1
						bounder++;
					}
				}
			}
		}

		/// 以上处理后得到一个：String数组存放着所有被提及到的人的名字。
		/// int数组存放着对应的人的被提及的次数

		int i, j, temp;
		String tempString;
		for (j = 0; j < bounder - 1; j++) {
			for (i = 0; i < bounder - 1 - j; i++) {
				if (nums[i] < nums[i + 1]) { /// 从大到小
					temp = nums[i];
					nums[i] = nums[i + 1];
					nums[i + 1] = temp;

					tempString = name[i];
					name[i] = name[i + 1];
					name[i + 1] = tempString;

				}
			}
		}

		/// 排序

		/// 依次添加
		for (int k = 0; k < bounder; k++) {
			result.add(name[k].toLowerCase());
		}

		/// 加入

		return result;
	}

	/**
	 * 得到某个人的在集合中的下标
	 * @param names
	 * @param name
	 * @param bounder
	 * @return 下标
	 */
	public static int getIndex(String[] names, String name, int bounder) {
		int index = -1;
		for (int i = 0; i < bounder; i++) {
			if (names[i].toLowerCase().equals(name.toLowerCase())) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * 在集合中搜索是否含有name
	 * @param names
	 * @param name
	 * @param bounder
	 * @return 真假
	 */
	public static boolean ifContain(String[] names, String name, int bounder) {
		boolean flag = false;
		for (int i = 0; i < bounder; i++) {
			if (names[i].toLowerCase().equals(name.toLowerCase())) {
				flag = true;
			}
		}
		return flag;
	}

	///small test
	public static void main(String[] args) {

		Instant d1 = Instant.parse("2016-02-15T10:00:00Z");
		Instant d2 = Instant.parse("2016-02-16T11:00:00Z");
		Instant d3 = Instant.parse("2016-02-18T11:00:00Z");
		Instant d4 = Instant.parse("2016-02-19T11:00:00Z");
		Instant d5 = Instant.parse("2016-02-19T11:00:00Z");
		Instant d6 = Instant.parse("2016-02-14T11:00:00Z");
		Instant d7 = Instant.parse("2016-02-14T11:00:00Z");
		Instant d8 = Instant.parse("2016-02-14T11:00:00Z");
		Instant d9 = Instant.parse("2016-02-14T11:00:00Z");
		Instant d10 = Instant.parse("2016-02-14T11:00:00Z");

		Tweet tweet1 = new Tweet(1, "a", "@b", d1);
		Tweet tweet2 = new Tweet(2, "b", "@a @c", d2);
		Tweet tweet3 = new Tweet(3, "c", "@b", d3);
		/// abc 应该互相关注
		Tweet tweet4 = new Tweet(4, "d", "#abb", d4);
		Tweet tweet5 = new Tweet(5, "e", "#abb", d5);
		/// de 互相关注
		Tweet tweet6 = new Tweet(6, "f", "@g", d6);
		Tweet tweet7 = new Tweet(7, "g", "RT @h: balabala", d7);
		Tweet tweet8 = new Tweet(8, "h", "balabala", d8);
		/// f follows g ///  g follows h /// g retweet h  -> f follows h
		Tweet tweet9 = new Tweet(9, "i", "@h", d9);
		Tweet tweet10 = new Tweet(10, "j", "@lc", d10);
		/// h > a/b/c > d/e/g > lc (i,j无人follow)
		
		
		Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, 
				 tweet3, tweet4, tweet5, tweet6, tweet7, tweet8, tweet9, tweet10));
		// tweet4, tweet5, tweet6,
		// tweet7, tweet8, tweet9, tweet10)

		
		Set<Map.Entry<String, Set<String>>> entries = followsGraph.entrySet();
		Iterator<Map.Entry<String, Set<String>>> iteratorMap = entries.iterator();

		while (iteratorMap.hasNext()) {
			Map.Entry<String, Set<String>> next = iteratorMap.next();
			System.out.println(next.getKey());
			for(String i : next.getValue()) {
				System.out.println(i);
			}
		}
		

		System.out.println("\n");

		List<String> influence = new ArrayList<String>();

		influence = influencers(followsGraph);

		for (String i : influence) {
			System.out.print(i);
		}

	}

}
