package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_08Test {

    @Test
    public void testBadMethodVulnerability() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_08 servlet = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_08();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Mock the environment variable
        String maliciousInput = "en-US\r\nSet-Cookie: sessionId=abc123";
        try {
            // Set the environment variable ADD to a malicious value
            setEnvironmentVariable("ADD", maliciousInput);

            // Act
            servlet.bad(request, response);

            // Assert
            verify(response).setHeader("Location", "/author.jsp?lang=" + maliciousInput);
            assertTrue(true, "The method is vulnerable to HTTP Response Splitting.");
        } finally {
            // Clean up the environment variable
            setEnvironmentVariable("ADD", null);
        }
    }

    // Helper method to set environment variables (works only in testing context)
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