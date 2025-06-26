import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.*;

public class CWE835_Infinite_Loop__while_true_01Test {

    @Test
    public void testBadMethodForInfiniteLoop() {
        CWE835_Infinite_Loop__while_true_01 instance = new CWE835_Infinite_Loop__while_true_01();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            try {
                instance.bad();
            } catch (Throwable t) {
                // Catch any throwable to prevent the test from failing due to exceptions
            }
        });

        try {
            future.get(2, TimeUnit.SECONDS); // Wait for 2 seconds for the task to complete
            fail("The method completed, which means it is not an infinite loop.");
        } catch (TimeoutException e) {
            // Expected outcome, the method should not complete within the timeout
            assertTrue(true, "The method is vulnerable to an infinite loop.");
        } catch (Exception e) {
            fail("An unexpected exception occurred: " + e.getMessage());
        } finally {
            future.cancel(true); // Cancel the task if it's still running
            executor.shutdownNow(); // Shutdown the executor
        }
    }
}