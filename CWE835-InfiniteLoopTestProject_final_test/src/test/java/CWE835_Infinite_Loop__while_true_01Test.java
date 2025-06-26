import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.*;

public class CWE835_Infinite_Loop__while_true_01Test {

    @Test
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__while_true_01 instance = new CWE835_Infinite_Loop__while_true_01();

        // Create a callable to run the bad method
        Callable<Boolean> task = () -> {
            try {
                instance.bad();
            } catch (Throwable t) {
                // Catch any throwable to prevent the test from failing due to exceptions
            }
            return true; // If it completes, return true
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executor.submit(task);

        boolean isVulnerable = false;
        try {
            // Wait for a short period to see if the method completes
            future.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            // If a timeout occurs, it indicates an infinite loop
            isVulnerable = true;
        } catch (Exception e) {
            // Handle other exceptions
        } finally {
            executor.shutdownNow(); // Ensure the executor is shut down
        }

        assertTrue(isVulnerable, "The method is not vulnerable to an infinite loop.");
    }
}