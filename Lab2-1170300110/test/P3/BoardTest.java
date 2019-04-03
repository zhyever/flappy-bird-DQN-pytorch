package P3;


import static org.junit.Assert.*;



import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class BoardTest {
    
	// Testing strategy
    // TODO 创建一个Player类型的对象对其所有方法依次进行测试
    @Test
    public void test() {
    	Board b1 = new Board("Chess");
    	Board b2 = new Board("Go");
    	
    	b1.showBoard();
    	
    	Piece p = new Piece("Pawn1_",0,6);
    	
    	assertEquals("expected equal", "Pawn1_", b1.getPiece(0, 6).getName());
    	assertEquals("expected equal", null, b2.getPiece(0, 6));
    	
    	assertEquals("expected equal", true, b1.isContain(0, 6));
    	assertEquals("expected equal", false, b2.isContain(0, 6));
  

    	Piece p0 = new Piece("A", -1, -1);
    	b2.putPiece(1, 1, p0);
    	assertEquals("expected equal", "A", b2.getPiece(1, 1).getName());
    	assertEquals("expected equal", 1, p0.getPosition().x);
    	
    }
}