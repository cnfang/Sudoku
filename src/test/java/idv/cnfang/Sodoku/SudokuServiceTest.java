package idv.cnfang.Sodoku;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import org.mockito.*;
import static org.mockito.Mockito.*;


public class SudokuServiceTest {
    
    @Mock 
    BackgroundCheck bgcMock;
    
    @InjectMocks
    SudokuService service;
    
    @Before
    public void setUp() {
         MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testIsValidSudoku_WithoutCheckingID() {
        int id = 5;
        String [][]map = {
                {"5","3",".",".","7",".",".",".","."},
                {"6",".",".","1","9","5",".",".","."},
                {".","9","8",".",".",".",".","6","."},
                {"8",".",".",".","6",".",".",".","3"},
                {"4",".",".","8",".","3",".",".","1"},
                {"7",".",".",".","2",".",".",".","6"},
                {".","6",".",".",".",".","2","8","."},
                {".",".",".","4","1","9",".",".","5"},
                {".",".",".",".","8",".",".","7","9"}
              };
        // we wanna check if isValidSudoku functons well without really checking the database
        // assuming user id exists in the database, how does isValidSudoku perform? is it like what we expect?
        when(bgcMock.checkID(id)).thenReturn(true);
        assertTrue(service.isValidSudoku(map, id));
    }

}
