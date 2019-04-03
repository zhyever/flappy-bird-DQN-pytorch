package P3;


import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class PlayerTest {
    
    // Testing strategy
    // TODO 创建一个Player类型的对象对其所有方法依次进行测试
	
    @Test
    public void test() {
    	Piece piece = new Piece("L", 1, 1);
    	Player p = new Player("Lzy");
    	
    	assertEquals("expected equal", "Lzy", p.getName());
    	
    	p.putStep("~");
    	assertEquals("expected equal", "~\n", p.getHistory());
    	
    	p.addPieces(piece);
    	List<Piece> pieces = new ArrayList<Piece>();
    	pieces.add(piece);
    	
    	
    	assertEquals("expected equal", pieces, p.getPieces());
    	
    	
    	p.moveTo(1, 1, 2, 2);
    	assertEquals("expected equal", 2, piece.getPosition().x);
    	
    	p.removePiece(2, 2);
    	pieces.remove(piece);
    	assertEquals("expected equal", pieces, p.getPieces());
    	
    	assertEquals("expected equal", false, p.ifContain(piece));
    }
}