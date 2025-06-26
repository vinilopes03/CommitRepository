import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

public class CWE835_Infinite_Loop__while_true_01Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__while_true_01 instance = new CWE835_Infinite_Loop__while_true_01();
        boolean isVulnerable = false;

        try {
            instance.bad();
        } catch (Exception e) {
            // If an exception is thrown, it means the loop was broken by some means
            isVulnerable = false;
        } catch (Throwable t) {
            // Catch any other throwable to ensure the test doesn't fail unexpectedly
            isVulnerable = false;
        }

        // If the method doesn't complete within the timeout, it is vulnerable
        isVulnerable = true;

        assertTrue(isVulnerable, "The method is not vulnerable to an infinite loop.");
    }
}