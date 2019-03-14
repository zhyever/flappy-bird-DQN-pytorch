/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

	/**
	 * Get the time period spanned by tweets.
	 * 
	 * @param tweets list of tweets with distinct ids, not modified by this method.
	 * @return a minimum-length time interval that contains the timestamp of every
	 *         tweet in the list.
	 */
	public static Timespan getTimespan(List<Tweet> tweets) {
		// min记录最早的时间
		Instant min = tweets.get(0).getTimestamp();
		// max记录最晚的时间
		Instant max = tweets.get(0).getTimestamp();
		// 遍历 更新min max
		for (Tweet i : tweets) {
			if (i.getTimestamp().isAfter(max)) {
				max = i.getTimestamp();
			}
			if (i.getTimestamp().isBefore(min)) {
				min = i.getTimestamp();
			}
		}
		Timespan timespan = new Timespan(min, max);
		return timespan;
	}

	/**
	 * Get usernames mentioned in a list of tweets.
	 * 
	 * @param tweets list of tweets with distinct ids, not modified by this method.
	 * @return the set of usernames who are mentioned in the text of the tweets. A
	 *         username-mention is "@" followed by a Twitter username (as defined by
	 *         Tweet.getAuthor()'s spec). The username-mention cannot be immediately
	 *         preceded or followed by any character valid in a Twitter username.
	 *         For this reason, an email address like bitdiddle@mit.edu does NOT
	 *         contain a mention of the username mit. Twitter usernames are
	 *         case-insensitive, and the returned set may include a username at most
	 *         once.
	 */
	public static Set<String> getMentionedUsers(List<Tweet> tweets) {

		Set<String> result = new HashSet<String>();
		boolean flag = true;
		String[] text;
		///text 是每一个要处理的getText内容
		String[] name;
		///name 是提及到的名字
		String n = null;

		int nums = 0;

		for (Tweet i : tweets) {
			text = i.getText().split(" ");
			//先用空格分开
			for (String j : text) {
				///对里面的每一个字符串进行遍历
				if (!j.isEmpty()) {
					if (j.charAt(0) == '@') {
						///如果发现某一个字符串的第一位是@
						nums = 0;
						flag = false;
						/// 找到@ xxxx
						name = j.split("[, ? @ : ' \" ! .]");
						/// 为了防止名字后面跟着字符，再split一下
						/// 引入计数器nums 记录分词后 得到字符串的数量，如果有两个就代表了形式：xxx.xxx不合法不用加入
						for (int k = 0; k < name.length; k++) {
							if (!name[k].isEmpty()) {
								nums++;
								if (ifLegal(name[k])) {
									///判断名字是否合法
									n = name[k];
									/// n就是那个名字
									flag = true;
								}
							}
						}
						if (nums == 1 && flag == true) {
							if (!ifContain(result, n)) {
								///检查result集合中是否含有n 如果没有则加入进去
								result.add(n.toLowerCase());
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 判断一个集合中是否含有某个字符串
	 * @param result
	 * @param name
	 * @return 返回真假(是否含有
	 */
	public static boolean ifContain(Set<String> result, String name) {
		boolean flag = false;
		for(String i : result) {
			if(i.toLowerCase().equals(name.toLowerCase())) {
				flag = true;
			}
		}
		return flag;
		
	}

	/**
	 * 判断一个名字是否合法
	 * @param name
	 * @return 返回真假
	 */
	public static boolean ifLegal(String name) {
//    	char[] legal  = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
//        		'o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D',
//        		'E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T',
//        		'U','V','W','X','Y','Z','-','_'};
		for (int i = 0; i < name.length(); i++) {
			int nums = name.charAt(i);
			///使用ASCII码来进行判断
			if (nums < 45 || (nums > 45 && nums < 48) || (nums > 57 && nums < 65) || (nums > 90 && nums < 95)
					|| (nums > 95 && nums < 97) || nums > 122) {
				return false;
			}
		}
		return true;
	}

	/// small test
	public static void main(String[] args) {
		Instant d3 = Instant.parse("2016-02-16T10:00:00Z");
		Tweet tweet3 = new Tweet(1, "alyssa", "@lzy: @hit.com @lc-12138 @Lzy @lc_123", d3);

		Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
		for (String i : mentionedUsers) {
			System.out.println(i);
		}
	}

}
