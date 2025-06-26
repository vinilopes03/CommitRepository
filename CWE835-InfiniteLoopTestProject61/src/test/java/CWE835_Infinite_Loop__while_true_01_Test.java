import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.*;

public class CWE835_Infinite_Loop__while_true_01_Test {

    @Test
    public void testBadMethodForInfiniteLoop() {
        CWE835_Infinite_Loop__while_true_01 instance = new CWE835_Infinite_Loop__while_true_01();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
            instance.bad();
        });

        boolean isVulnerable = false;
        try {
            future.get(1, TimeUnit.SECONDS); // Wait for 1 second for the task to complete
        } catch (TimeoutException e) {
            isVulnerable = true; // If a timeout occurs, it indicates an infinite loop
            future.cancel(true); // Cancel the task
        } catch (Exception e) {
            // Handle other exceptions if necessary
        } finally {
            executor.shutdownNow(); // Ensure the executor is shut down
        }

        assertTrue(isVulnerable, "The bad method should be vulnerable to an infinite loop.");
    }
}