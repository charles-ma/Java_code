package sequences;

/**
 * @author David Matuszek
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

    @RunWith(value=Suite.class)
    @Suite.SuiteClasses(value = {
            StackTest.class,
            QueueTest.class,
            DequeTest.class
    })
        
    public class AllTests { }