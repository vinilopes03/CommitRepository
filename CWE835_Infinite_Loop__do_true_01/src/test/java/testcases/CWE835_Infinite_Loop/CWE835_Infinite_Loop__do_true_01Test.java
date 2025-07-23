package testcases.CWE835_Infinite_Loop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import java.time.Duration;

public class CWE835_Infinite_Loop__do_true_01Test {

    @Test
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__do_true_01 instance = new CWE835_Infinite_Loop__do_true_01();

        // We expect the bad method to run indefinitely, so we use a timeout to detect this.
        boolean isVulnerable = assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            try {
                instance.bad();
            } catch (Exception e) {
                // If an exception occurs, it's not the infinite loop we're testing for.
                return false;
            }
            // If the method returns normally, it's not an infinite loop.
            return false;
        });

        // If the method times out, it indicates an infinite loop, hence vulnerable.
        assertTrue(isVulnerable, "The method is vulnerable to an infinite loop.");
    }
}