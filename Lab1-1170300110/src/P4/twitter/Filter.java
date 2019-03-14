/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

	/**
	 * Find tweets written by a particular user.
	 * 
	 * @param tweets   a list of tweets with distinct ids, not modified by this
	 *                 method.
	 * @param username Twitter username, required to be a valid Twitter username as
	 *                 defined by Tweet.getAuthor()'s spec.
	 * @return all and only the tweets in the list whose author is username, in the
	 *         same order as in the input list.
	 */
	public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
		List<Tweet> result = new ArrayList<Tweet>();
		String name;
		for (int i = 0; i < tweets.size(); i++) {
			name = tweets.get(i).getAuthor();
			///遍历列表，将所有作者名是username的人的tweet添加到result列表中
			if (name.toLowerCase().equals(username.toLowerCase())) {
				result.add(tweets.get(i));
			}
		}
		return result;
	}

	/**
	 * Find tweets that were sent during a particular timespan.
	 * 
	 * @param tweets   a list of tweets with distinct ids, not modified by this
	 *                 method.
	 * @param timespan timespan
	 * @return all and only the tweets in the list that were sent during the
	 *         timespan, in the same order as in the input list.
	 */
	public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
		Instant start = timespan.getStart();
		Instant end = timespan.getEnd();
		Instant time;
		List<Tweet> result = new ArrayList<Tweet>();

		for (int i = 0; i < tweets.size(); i++) {
			time = tweets.get(i).getTimestamp();
			if (time.isBefore(end) && time.isAfter(start)) {
				///如果这条推特的发布时间在start~end范围内则将tweet加入到result列表中
				result.add(tweets.get(i));
			}
		}

		return result;

	}

	/**
	 * Find tweets that contain certain words.
	 * 
	 * @param tweets a list of tweets with distinct ids, not modified by this
	 *               method.
	 * @param words  a list of words to search for in the tweets. A word is a
	 *               nonempty sequence of nonspace characters.
	 * @return all and only the tweets in the list such that the tweet text (when
	 *         represented as a sequence of nonempty words bounded by space
	 *         characters and the ends of the string) includes *at least one* of the
	 *         words found in the words list. Word comparison is not case-sensitive,
	 *         so "Obama" is the same as "obama". The returned tweets are in the
	 *         same order as in the input list.
	 */
	public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
		/// 如果有一个用空格隔开的单词的首字母是@的话，取@后面的部分存至word
		String word;
		/// text中的每一个用空格隔开的单词
		String[] textWord;
		int index = 0;
		List<Tweet> result = new ArrayList<Tweet>();

		for (int i = 0; i < tweets.size(); i++) {
			/// 设立flag 方便进行判断操作（加入列表中）
			textWord = tweets.get(i).getText().split(" ");
			for (int j = 0; j < textWord.length; j++) {
				word = textWord[j];
				/// 如果存在那么就加入列表中并且break掉后面的内容 无需继续判断啦
				if (ifContain(word, words)) {
					///注意这里的ifContain要进行符号的处理 比如@之类的
					result.add(index,tweets.get(i));
					index++;
					break;
				}
			}
		}
		
	
		return result;
	}

	/**
	 * 判断一个word 中是否含有 words 列表中的某一个词
	 * @param word
	 * @param words
	 * @return 返回真假
	 */
	public static boolean ifContain(String word, List<String> words) {
		List<String> lowerCase = new ArrayList<String>();
		// 先将这些LIST中的词转化为小写
		for (String i : words) {
			lowerCase.add(i.toLowerCase());
		}
		
		int nums = 0;
		boolean flag = false;
		
		///将word进行小操作 分离一下字符
		String[] wordDoing;
		wordDoing = word.split("[, ? @ : ' \" ! .]");
		
		for(int i = 0; i < wordDoing.length; i++) {
			if(!wordDoing[i].isEmpty()) {
				nums++;
				///这里也要引入计数器 来防止非法输入
				if(lowerCase.contains(wordDoing[i].toLowerCase())) {
					///判断是否包含
					flag = true;
				}
			}
		}
		
		if(nums != 1) {
			flag = false;
		}
		
		return flag;
	}
	
//	small test
//	public static void main(String[] args) {
//		Instant d3 = Instant.parse("2016-02-15T10:00:00Z");
//		Instant d4 = Instant.parse("2016-02-16T11:00:00Z");
//		Tweet tweet3 = new Tweet(3, "a", "LZY", d3);
//		Tweet tweet4 = new Tweet(3, "a", "@Lzy  ", d4);
//		
////		List<Tweet> containing = containing(Arrays.asList(tweet3, tweet4), Arrays.asList("lzy"));
////		
////		System.out.println(containing.indexOf(tweet3));
////		System.out.println(containing.indexOf(tweet4));
////		
//		List<Tweet> text = Arrays.asList(tweet3, tweet4);
//		System.out.println(text.indexOf(tweet3));
//		System.out.println(text.indexOf(tweet4));
//		
//	}

}
