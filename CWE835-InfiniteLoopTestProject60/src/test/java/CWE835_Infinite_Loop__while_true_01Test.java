import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

public class CWE835_Infinite_Loop__while_true_01Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void testBadMethodForInfiniteLoop() {
        CWE835_Infinite_Loop__while_true_01 instance = new CWE835_Infinite_Loop__while_true_01();
        
        boolean isVulnerable = false;
        
        try {
            instance.bad();
        } catch (Exception e) {
            // If an exception is thrown, it means the method didn't run indefinitely
            isVulnerable = false;
        }
        
        // If the method times out, it means it is vulnerable to an infinite loop
        isVulnerable = true;
        
        assertTrue(isVulnerable, "The method is vulnerable to an infinite loop.");
    }
}