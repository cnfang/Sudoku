package idv.cnfang.Sodoku;

import static org.junit.Assert.assertArrayEquals;
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
    
    private char[][]map;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        map = new char[][] {
                            {'5','3','.','.','7','.','.','.','.'},
                            {'6','.','.','1','9','5','.','.','.'},
                            {'.','9','8','.','.','.','.','6','.'},
                            {'8','.','.','.','6','.','.','.','3'},
                            {'4','.','.','8','.','3','.','.','1'},
                            {'7','.','.','.','2','.','.','.','6'},
                            {'.','6','.','.','.','.','2','8','.'},
                            {'.','.','.','4','1','9','.','.','5'},
                            {'.','.','.','.','8','.','.','7','9'}
                          };
    }
    
    @Test
    public void WhenBoardIsNotValid_ThenFales() {
        int id = 5; 
        // we wanna check if isValidSudoku functons well without really checking the database
        // assuming user id exists in the database, how does isValidSudoku perform? is it like what we expect?
        when(bgcMock.checkID(id)).thenReturn(true);
        assertTrue(service.isValidSudoku(map, id));
    }
    
    @Test
    public void SolvedSudoku() {
        service.solveSudoku(map);
        char [][]expected = {{'5','3','4','6','7','8','9','1','2'},
                             {'6','7','2','1','9','5','3','4','8'},
                             {'1','9','8','3','4','2','5','6','7'},
                             {'8','5','9','7','6','1','4','2','3'},
                             {'4','2','6','8','5','3','7','9','1'},
                             {'7','1','3','9','2','4','8','5','6'},
                             {'9','6','1','5','3','7','2','8','4'},
                             {'2','8','7','4','1','9','6','3','5'},
                             {'3','4','5','2','8','6','1','7','9'}};
        assertArrayEquals(expected, map);
    }

}
