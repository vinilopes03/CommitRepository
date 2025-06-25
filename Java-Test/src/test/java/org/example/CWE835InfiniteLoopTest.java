package org.example;

import org.junit.Test;
import static org.junit.Assert.*;

import org.example.CWE835_Infinite_Loop__do_01;

import java.util.concurrent.*;

public class CWE835InfiniteLoopTest {

    @Test
    public void testBadMethod_IsInfiniteLoop() {
        CWE835_Infinite_Loop__do_01 testcase = new CWE835_Infinite_Loop__do_01();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> testcase.bad());
        boolean infinite = false;

        try {
            future.get(2, TimeUnit.SECONDS);
            fail("Method returned before timeout (not infinite loop)");
        } catch (TimeoutException e) {
            infinite = true; // Success: stuck in loop
        } catch (Exception e) {
            fail("Method threw exception: " + e);
        } finally {
            future.cancel(true);
            executor.shutdownNow();
        }

        assertTrue("Method should be an infinite loop and not return within timeout.", infinite);
    }
}