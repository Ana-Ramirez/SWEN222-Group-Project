package logicTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * If you run this file, it automatically runs the whole test suite
 * @author Lewis McEwan
 *
 */

@RunWith(Suite.class)
@SuiteClasses( {DoorTests.class, LevelTests.class, RoomTests.class} )
public class AllLogicTests {

}