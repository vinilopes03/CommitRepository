package testcases.CWE835_Infinite_Loop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.*;

public class CWE835InfiniteLoopWhileTrueTest {

    /**
     * Returns true if 'bad' is vulnerable (i.e., gets stuck in an infinite loop).
     * Returns false if the code is fixed and method returns within timeout.
     */
    public boolean isInfiniteLoopPresent() {
        CWE835_Infinite_Loop__while_true_01 testcase = new CWE835_Infinite_Loop__while_true_01();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(testcase::bad);

        boolean isVulnerable = false;
        try {
            // Wait up to 2 seconds for method to return
            future.get(2, TimeUnit.SECONDS);
            // If we get here, method returned: not vulnerable
            isVulnerable = false;
        } catch (TimeoutException e) {
            // Infinite loop detected (vulnerable)
            isVulnerable = true;
        } catch (Exception e) {
            // Any other exception: treat as not vulnerable for this test
            isVulnerable = false;
        } finally {
            future.cancel(true);
            executor.shutdownNow();
        }
        return isVulnerable;
    }

    @Test
    public void testInfiniteLoopVulnerability() {
        assertTrue(isInfiniteLoopPresent(), "The 'bad' method should be vulnerable (infinite loop detected)");
    }
}