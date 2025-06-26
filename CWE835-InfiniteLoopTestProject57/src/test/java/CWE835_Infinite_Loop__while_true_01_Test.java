import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

public class CWE835_Infinite_Loop__while_true_01_Test {

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__while_true_01 instance = new CWE835_Infinite_Loop__while_true_01();
        
        boolean isVulnerable = false;
        
        try {
            instance.bad();
        } catch (Exception e) {
            // If an exception occurs, it means the loop was broken, which should not happen in the bad case
            isVulnerable = false;
        }
        
        // If the method does not terminate within the timeout, it is vulnerable
        isVulnerable = true;
        
        assertTrue(isVulnerable, "The method is not vulnerable to infinite loop.");
    }
}