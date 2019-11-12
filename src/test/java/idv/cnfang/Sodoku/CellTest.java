package idv.cnfang.Sodoku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

class CellTest {

    Cell cell;
    @BeforeEach
    void setUp() throws Exception {
        cell = new Cell(2, 5);
    }

    @AfterEach
    void tearDown() throws Exception {
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
    
    /**
     * an example of using hamcrest framework
     */
    @Test
    void testHamcrestNotNullValue() {
        assertThat(cell.getCol(), notNullValue());
    }

}
