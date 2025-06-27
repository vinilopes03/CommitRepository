package testcases.CWE835_Infinite_Loop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CWE835_Infinite_Loop__for_01Test {

    @Test
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__for_01 instance = new CWE835_Infinite_Loop__for_01();
        Thread testThread = new Thread(() -> {
            try {
                instance.bad();
            } catch (Throwable e) {
                fail("Exception occurred during test execution: " + e.getMessage());
            }
        });

        testThread.start();
        try {
            // Wait for 2 seconds for the thread to complete
            testThread.join(2000);
            if (testThread.isAlive()) {
                // If the thread is still alive, it means the loop is infinite
                testThread.interrupt(); // Attempt to stop the thread
                assertTrue(true, "The method is vulnerable to an infinite loop.");
            } else {
                // If the thread has finished, the loop is not infinite
                assertTrue(false, "The method is not vulnerable to an infinite loop.");
            }
        } catch (InterruptedException e) {
            fail("Test was interrupted: " + e.getMessage());
        }
    }
}