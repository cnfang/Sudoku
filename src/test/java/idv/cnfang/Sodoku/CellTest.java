package idv.cnfang.Sodoku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellTest {

    Cell cell;
    @BeforeEach
    void setUp() throws Exception {
        System.out.println("before");
        cell = new Cell(2, 5);
    }

    @AfterEach
    void tearDown() throws Exception {
        System.out.println("after");
    }

    @Test
    void testGetRow() {
        System.out.println("row test");
        assertEquals(2, cell.getRow());
    }

    @Test
    void testGetCol() {
        System.out.println("col test");
        assertEquals(5, cell.getCol());
    }

}
