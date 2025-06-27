package testcases.CWE835_Infinite_Loop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE835_Infinite_Loop__while_01Test {

    @Test
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__while_01 instance = new CWE835_Infinite_Loop__while_01();
        
        // Since the bad() method contains an infinite loop, we need to test for it.
        // We will run the method in a separate thread and check if it terminates.
        
        Thread thread = new Thread(() -> {
            instance.bad();
        });

        thread.start();

        try {
            // Wait for a short period to see if the thread terminates
            thread.join(1000);
        } catch (InterruptedException e) {
            // Handle interruption
        }

        // Check if the thread is still alive, which indicates an infinite loop
        boolean isInfiniteLoop = thread.isAlive();

        // If the thread is still alive, it means the loop is infinite
        assertTrue(isInfiniteLoop, "The method is vulnerable to an infinite loop.");
        
        // If the thread is not alive, it means the loop terminated (unexpectedly)
        assertFalse(!isInfiniteLoop, "The method is not vulnerable to an infinite loop.");
    }
}