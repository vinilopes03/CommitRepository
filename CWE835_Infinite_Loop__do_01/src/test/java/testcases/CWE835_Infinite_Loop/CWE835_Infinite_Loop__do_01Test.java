package testcases.CWE835_Infinite_Loop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CWE835_Infinite_Loop__do_01Test {

    @Test
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__do_01 instance = new CWE835_Infinite_Loop__do_01();
        
        Thread testThread = new Thread(() -> {
            try {
                instance.bad();
            } catch (Throwable e) {
                // Catch any unexpected exceptions
                fail("Unexpected exception: " + e.getMessage());
            }
        });

        testThread.start();

        try {
            // Wait for the thread to complete within 2 seconds
            testThread.join(2000);
        } catch (InterruptedException e) {
            fail("Test thread was interrupted");
        }

        // If the thread is still alive after 2 seconds, it indicates an infinite loop
        assertTrue(testThread.isAlive(), "The method is vulnerable to an infinite loop");
        
        // Stop the thread if it's still running to clean up
        if (testThread.isAlive()) {
            testThread.interrupt();
        }
    }
}