package idv.cnfang.Sodoku;


import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

/**
 * JUnit 5 based suit testing
 * This is @SelectClasses test that allows performing tests across different classes 
 *
 */

@RunWith(JUnitPlatform.class)
@SelectClasses({CellTest.class, SudokuServiceTest.class})

public class AllUnitTestAcrossClass {

}
