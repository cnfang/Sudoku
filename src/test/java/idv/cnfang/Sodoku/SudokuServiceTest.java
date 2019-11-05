package idv.cnfang.Sodoku;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.*;
import static org.mockito.Mockito.*;

import java.time.Duration;


public class SudokuServiceTest {
    
    @Mock 
    BackgroundCheck bgcMock;
    
    @InjectMocks
    SudokuService service;
    
    
    private char[][]map;
    
    @BeforeEach
    public void setUp() {
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
    
    /**
     * an example of using mock object along with (when then Return) function  
     */
    @Test
    public void test_CheckValidBoardWithoutCheckingDataBase() {
        int id = 5; 
        // we wanna check if isValidSudoku functons well without really checking the database
        // assuming user id exists in the database, how does isValidSudoku perform? is it like what we expect?
        when(bgcMock.checkID(id)).thenReturn(true);
        assertTrue(service.isValidSudoku(map, id));
    }
    
    @Test
    public void test_SolvedSudoku() {
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
    
    /**
     * an example of using @ParameterizedTest and @NullSource to catch exception
     * more on @ParameterizedTest, please see LeetcodeCollection/src/.../Problem240.java and Problem279.java
     * @param mockmap
     */
    @ParameterizedTest
    @NullSource
    public void test_ExpectedNullPointerException(char [][]mockmap) {
        assertThrows(NullPointerException.class, ()->service.solveSudoku(mockmap));
    }
    
    
    /**
     * an example of using timeout function
     */
    @Test
    public void test_ExpectedTimeNotExceed10Ms() {
        assertTimeout(Duration.ofMillis(10), ()->service.solveSudoku(map));
    }

}
