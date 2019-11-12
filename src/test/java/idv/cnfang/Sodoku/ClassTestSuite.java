package idv.cnfang.Sodoku;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * JUnit 4 based suit testing [Cannot find runner]
 * Annotation @RunWith, @SuiteClasses allow performing tests across different classes 
 *
 */

@RunWith(Suite.class)
@SuiteClasses({ CellTest.class, SudokuServiceTest.class})
public class ClassTestSuite {

}
