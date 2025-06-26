import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.*;

public class CWE835_Infinite_Loop__while_true_01_Test {

    @Test
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__while_true_01 instance = new CWE835_Infinite_Loop__while_true_01();
        
        // Use an ExecutorService to run the bad method in a separate thread
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            instance.bad();
        });

        boolean isVulnerable = false;
        try {
            // Wait for the task to complete within 2 seconds
            future.get(2, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            // If a TimeoutException occurs, it indicates an infinite loop
            isVulnerable = true;
        } catch (Exception e) {
            // Handle other exceptions if necessary
        } finally {
            // Cancel the task and shutdown the executor
            future.cancel(true);
            executor.shutdownNow();
        }

        // Assert that the class is vulnerable to an infinite loop
        assertTrue(isVulnerable, "The method is not vulnerable to an infinite loop.");
    }
}