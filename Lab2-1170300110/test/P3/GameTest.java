package P3;



import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GameTest {
    
	// Testing strategy
    // TODO Run 真正的玩一下
    @Test
    public void test() {
    	MyChessAndGoGame game = new MyChessAndGoGame();
    	game.runGame();
    }
}