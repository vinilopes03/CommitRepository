package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_13Test {

    @Test
    public void testBad() throws Throwable {
        // Mock the HttpServletRequest and HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Set up the environment variable to simulate the bad input
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        try {
            // Set the environment variable "ADD" to the malicious input
            setEnvironmentVariable("ADD", maliciousInput);

            // Create an instance of the class to be tested
            CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_13 servlet =
                    new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_13();

            // Call the bad method
            servlet.bad(request, response);

            // Verify that setHeader was called with the malicious input
            verify(response).setHeader("Location", "/author.jsp?lang=" + maliciousInput);

            // Assert that the vulnerability is present
            assertTrue(true, "The application is vulnerable to HTTP Response Splitting.");
        } finally {
            // Clean up the environment variable
            setEnvironmentVariable("ADD", null);
        }
    }

    // Helper method to set environment variables
    private void setEnvironmentVariable(String key, String value) {
        try {
            java.util.Map<String, String> env = System.getenv();
            java.lang.reflect.Field field = env.getClass().getDeclaredField("m");
            field.setAccessible(true);
            java.util.Map<String, String> writableEnv = (java.util.Map<String, String>) field.get(env);
            if (value == null) {
                writableEnv.remove(key);
            } else {
                writableEnv.put(key, value);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to set environment variable", e);
        }
    }
}