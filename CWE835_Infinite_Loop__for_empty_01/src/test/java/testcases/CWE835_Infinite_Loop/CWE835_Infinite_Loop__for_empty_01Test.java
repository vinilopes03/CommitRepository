package testcases.CWE835_Infinite_Loop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CWE835_Infinite_Loop__for_empty_01Test {

    @Test
    public void testInfiniteLoopVulnerability() {
        CWE835_Infinite_Loop__for_empty_01 instance = new CWE835_Infinite_Loop__for_empty_01();
        
        try {
            // Run the bad method with a timeout to detect infinite loop
            assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
                instance.bad();
            });
            // If the method completes, it is not vulnerable
            fail("The method did not result in an infinite loop.");
        } catch (Exception e) {
            // If an exception is thrown, it means the loop was infinite
            assertTrue(true, "The method is vulnerable to an infinite loop.");
        }
    }
}