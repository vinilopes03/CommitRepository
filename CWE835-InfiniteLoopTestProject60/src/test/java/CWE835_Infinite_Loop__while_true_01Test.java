import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.*;

public class CWE835_Infinite_Loop__while_true_01Test {

    @Test
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__while_true_01 instance = new CWE835_Infinite_Loop__while_true_01();
        
        // Create a Callable to run the bad method
        Callable<Boolean> task = () -> {
            try {
                instance.bad();
            } catch (Throwable t) {
                // If any exception occurs, consider it as not infinite loop
                return false;
            }
            return true; // If it completes, it means no infinite loop
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(task);

        boolean isVulnerable = false;
        try {
            // Try to get the result with a timeout
            future.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            // If a timeout occurs, it means the method is likely in an infinite loop
            isVulnerable = true;
        } catch (Exception e) {
            // Handle other exceptions
            isVulnerable = false;
        } finally {
            future.cancel(true); // Cancel the task if it's still running
            executor.shutdownNow(); // Shutdown the executor
        }

        assertTrue(isVulnerable, "The method is not vulnerable to an infinite loop.");
    }
}