package testcases.CWE835_Infinite_Loop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CWE835_Infinite_Loop__do_true_01Test {

    @Test
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__do_true_01 instance = new CWE835_Infinite_Loop__do_true_01();
        
        // Use a separate thread to run the bad() method and check for infinite loop
        Thread testThread = new Thread(() -> {
            try {
                instance.bad();
            } catch (Throwable e) {
                // Catch any throwable to prevent the thread from crashing
            }
        });

        testThread.start();

        try {
            // Wait for a short period to see if the thread completes
            testThread.join(1000); // 1000 milliseconds = 1 second
        } catch (InterruptedException e) {
            fail("Test was interrupted");
        }

        // If the thread is still alive, it indicates an infinite loop
        boolean isVulnerable = testThread.isAlive();

        // Interrupt the thread if it's still running to clean up
        if (isVulnerable) {
            testThread.interrupt();
        }

        // Assert that the vulnerability is present
        assertTrue(isVulnerable, "The method is not vulnerable to an infinite loop.");
    }
}