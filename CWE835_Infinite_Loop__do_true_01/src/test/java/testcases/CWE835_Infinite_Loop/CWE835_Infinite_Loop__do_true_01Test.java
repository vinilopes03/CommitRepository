package testcases.CWE835_Infinite_Loop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.*;

public class CWE835_Infinite_Loop__do_true_01Test {

    @Test
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__do_true_01 instance = new CWE835_Infinite_Loop__do_true_01();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            try {
                instance.bad();
            } catch (Throwable e) {
                // Catch any throwable to prevent the test from failing due to exceptions
            }
        });

        try {
            future.get(2, TimeUnit.SECONDS); // Wait for 2 seconds for the method to complete
            fail("The method completed, which indicates there might not be an infinite loop.");
        } catch (TimeoutException e) {
            // Expected outcome, the method should not complete within the time frame
            assertTrue(true, "The method did not complete, indicating an infinite loop is present.");
        } catch (Exception e) {
            fail("An unexpected exception occurred: " + e.getMessage());
        } finally {
            future.cancel(true); // Cancel the task if it's still running
            executor.shutdownNow(); // Shutdown the executor
        }
    }
}