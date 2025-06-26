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
            // If an exception is caught, it means the loop was broken due to an error
            isVulnerable = false;
        } catch (Throwable t) {
            // Catch any other throwable that might occur
            isVulnerable = false;
        }
        
        // If the method completes without timing out, it is not vulnerable
        assertTrue(isVulnerable, "The method should be vulnerable to infinite loop.");
    }
}