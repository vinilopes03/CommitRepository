import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

public class CWE835_Infinite_Loop__while_true_01Test {

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void testBadMethodForInfiniteLoop() {
        CWE835_Infinite_Loop__while_true_01 instance = new CWE835_Infinite_Loop__while_true_01();
        
        try {
            instance.bad();
            // If the method returns, it means there was no infinite loop
            // Therefore, the test should fail because we expect an infinite loop
            assertTrue(false, "The method did not result in an infinite loop as expected.");
        } catch (Exception e) {
            // If an exception occurs, it should not be considered as a successful test
            assertTrue(false, "An unexpected exception occurred: " + e.getMessage());
        }
    }
}