package testcases.CWE835_Infinite_Loop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CWE835_Infinite_Loop__do_true_01Test {

    @Test
    public void testBadMethodForInfiniteLoop() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create an instance of the class
        CWE835_Infinite_Loop__do_true_01 instance = new CWE835_Infinite_Loop__do_true_01();

        // Run the bad method in a separate thread to prevent blocking the test
        Thread testThread = new Thread(() -> {
            try {
                instance.bad();
            } catch (Throwable e) {
                // Catch any throwable to prevent the test from crashing
            }
        });

        // Start the thread
        testThread.start();

        // Allow the thread to run for a short time
        try {
            Thread.sleep(100); // 100 milliseconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Interrupt the thread to stop the infinite loop
        testThread.interrupt();

        // Check if the output is non-empty, indicating an infinite loop
        String output = outContent.toString();
        assertTrue(output.contains("00"), "The method is vulnerable to an infinite loop.");

        // Reset System.out
        System.setOut(System.out);
    }

    @Test
    public void testGoodMethodForNoInfiniteLoop() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create an instance of the class
        CWE835_Infinite_Loop__do_true_01 instance = new CWE835_Infinite_Loop__do_true_01();

        // Run the good method
        instance.good();

        // Check if the output contains the expected sequence
        String output = outContent.toString();
        assertFalse(output.contains("00"), "The method should not be vulnerable to an infinite loop.");

        // Reset System.out
        System.setOut(System.out);
    }
}