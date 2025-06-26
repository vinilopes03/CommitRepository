import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

public class CWE835_Infinite_Loop__while_true_01Test {

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__while_true_01 instance = new CWE835_Infinite_Loop__while_true_01();
        
        boolean isVulnerable = false;
        
        try {
            instance.bad();
        } catch (Exception e) {
            // If an exception occurs, it means the loop was broken due to some error
            isVulnerable = false;
        }

        // If the method completes without timing out, it means it's not an infinite loop
        // However, if it times out, the test will automatically fail, indicating a vulnerability
        assertTrue(isVulnerable, "The method is vulnerable to an infinite loop.");
    }
}