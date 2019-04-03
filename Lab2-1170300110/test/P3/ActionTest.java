package P3;


import static org.junit.Assert.*;



import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class ActionTest {
    
	// Testing strategy
    // TODO 创建一个Player类型的对象对其所有方法依次进行测试
    @Test
    public void test() {
    	Player p1 = new Player("A");
    	Player p2 = new Player("B");
    	Board b = new Board("Chess");
    	
    	
    	Action action = new Action(p1, p2, b);
    	action.putPiece(p1, new Piece("A1", -1, -1), 5, 5);
    	assertEquals("expected equal", "A1", b.getPiece(5, 5).getName());
    	assertEquals("expected equal", true, p1.ifContain(new Piece("A1", 5, 5)));
    	
    	p1.addPieces(new Piece("Rook1", 0, 0));
    	action.movePiece(p1, 0, 0, 3, 3);
    	assertEquals("expected equal", null, b.getPiece(0, 0));
    	assertEquals("expected equal", "Rook1", b.getPiece(3, 3).getName());
    	assertEquals("expected equal", false, p1.ifContain(new Piece("Rook1", 0, 0)));
    	assertEquals("expected equal", true, p1.ifContain(new Piece("Rook1", 3, 3)));
    	
    	p1.addPieces(new Piece("Queen", 3, 0));
    	action.removePiece(p1, 3, 0);
    	assertEquals("expected equal", null, b.getPiece(3, 0));
    	assertEquals("expected equal", false, p1.ifContain(new Piece("Queen", 3, 0)));
    	
    	action.eatPiece(p1, 3, 3, 3, 6, p2);
    	assertEquals("expected equal", "Rook1", b.getPiece(3, 6).getName());
    	assertEquals("expected equal", false, p1.ifContain(new Piece("Rook1", 3, 3)));
    	assertEquals("expected equal", true, p1.ifContain(new Piece("Rook1", 3, 6)));
    	
    	b.showBoard();
    	
    }
}