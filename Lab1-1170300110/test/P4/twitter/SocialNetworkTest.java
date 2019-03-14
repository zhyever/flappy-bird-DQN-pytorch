/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import P4.twitter.SocialNetwork;
import P4.twitter.Tweet;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */
    
  
    
	private static final Instant d1 = Instant.parse("2016-02-15T10:00:00Z");
	private static final Instant d2 = Instant.parse("2016-02-16T11:00:00Z");
	private static final Instant d3 = Instant.parse("2016-02-18T11:00:00Z");
	private static final Instant d4 = Instant.parse("2016-02-19T11:00:00Z");
	private static final Instant d5 = Instant.parse("2016-02-19T11:00:00Z");
	private static final Instant d6 = Instant.parse("2016-02-14T11:00:00Z");
	private static final Instant d7 = Instant.parse("2016-02-14T11:00:00Z");
	private static final Instant d8 = Instant.parse("2016-02-14T11:00:00Z");
	private static final Instant d9 = Instant.parse("2016-02-14T11:00:00Z");
	private static final Instant d10 = Instant.parse("2016-02-14T11:00:00Z");

	private static final Tweet tweet1 = new Tweet(1, "a", "@b", d1);
	private static final Tweet tweet2 = new Tweet(2, "b", "@a @c", d2);
	private static final Tweet tweet3 = new Tweet(3, "c", "@b", d3);
	/// abc 应该互相关注
	private static final Tweet tweet4 = new Tweet(4, "d", "#abb", d4);
	private static final Tweet tweet5 = new Tweet(5, "e", "#abb", d5);
	/// de 互相关注
	private static final Tweet tweet6 = new Tweet(6, "f", "@g", d6);
	private static final Tweet tweet7 = new Tweet(7, "g", "RT @h: balabala", d7);
	private static final Tweet tweet8 = new Tweet(8, "h", "balabala", d8);
	/// f follows g ///  g follows h /// g retweet h  -> f follows h
	private static final Tweet tweet9 = new Tweet(9, "i", "@h", d9);
	private static final Tweet tweet10 = new Tweet(10, "j", "@lc", d10);
	/// h > a/b/c > d/e/f/g > lc (i,j无人follow)
	
	 @Test
    public void text() {
		 
		 Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, 
				 tweet3, tweet4, tweet5, tweet6, tweet7, tweet8, tweet9, tweet10));
		 
		 Map<String, Set<String>> followsExpected = new ConcurrentHashMap<String, Set<String>>();
		 Set<String> set1 = new HashSet<>();
		 set1.add("b");
		 set1.add("c");
		 followsExpected.put("a", set1 );
		 
		 Set<String> set2 = new HashSet<>();
		 set2.add("a");
		 set2.add("c");
		 followsExpected.put("b", set2 );
		 
		 Set<String> set3 = new HashSet<>();
		 set3.clear();
		 set3.add("a");
		 set3.add("b");
		 followsExpected.put("c", set3 );
		 
		 Set<String> set4 = new HashSet<>();
		 set4.clear();
		 set4.add("e");
		 followsExpected.put("d", set4 );
		 
		 Set<String> set5 = new HashSet<>();
		 set5.clear();
		 set5.add("d");
		 followsExpected.put("e", set5 );
		 
		 Set<String> set6 = new HashSet<>();
		 set6.clear();
		 set6.add("g");
		 set6.add("h");
		 followsExpected.put("f", set6 );
		 
		 Set<String> set7 = new HashSet<>();
		 set7.clear();
		 set7.add("h");
		 followsExpected.put("g", set7 );
		 
		 Set<String> set8 = new HashSet<>();
		 set8.clear();
		 followsExpected.put("h", set8 );
		 
		 Set<String> set9 = new HashSet<>();
		 set9.clear();
		 set9.add("h");
		 followsExpected.put("i", set9 );
		 
		 Set<String> set10 = new HashSet<>();
		 set10.clear();
		 set10.add("lc");
		 followsExpected.put("j", set10 );
		 
		 assertEquals("expected followsGraph!",followsExpected ,followsGraph);
		 
		 
		 List<String> influence = new ArrayList<String>();
		 influence = SocialNetwork.influencers(followsGraph);
		 
		 /// h > a/b/c > d/e/g > lc (i,j无人follow)
		 
		 for (String i : influence) {
				System.out.print(i);
			}
		 
		 /// 这里打印出来的顺序与上面的那条注释内容一样即可。

    	
    }

}
