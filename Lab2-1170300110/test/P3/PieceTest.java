package P3;


import static org.junit.Assert.*;



import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class PieceTest {
    
    // Testing strategy
    // TODO 创建一个Player类型的对象对其所有方法依次进行测试
    @Test
    public void test() {
    	Piece piece = new Piece("Lzy", 1, 1);
    	
    	assertEquals("expected equal", "Lzy", piece.getName());
    	assertEquals("expected equal", new Position(1, 1), piece.getPosition());
    	
    	piece.setPosition(2, 2);
    	assertEquals("expected equal", new Position(2, 2), piece.getPosition());
    }
}