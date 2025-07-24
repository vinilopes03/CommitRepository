package testcases.CWE113_HTTP_Response_Splitting.s01;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17Test {

    @Test
    public void testBad() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17 servlet = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set environment variable for testing
        String originalEnv = System.getenv("ADD");
        try {
            setEnvironmentVariable("ADD", "en-US\r\nContent-Length: 0");

            // Act
            servlet.bad(request, response);

            // Assert
            Mockito.verify(response).setHeader(Mockito.eq("Location"), Mockito.contains("\r\n"));
            assertTrue(true, "The servlet is vulnerable to HTTP Response Splitting.");
        } finally {
            // Restore original environment variable
            setEnvironmentVariable("ADD", originalEnv);
        }
    }

    @Test
    public void testGoodG2B() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17 servlet = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Act
        servlet.goodG2B(request, response);

        // Assert
        Mockito.verify(response).setHeader(Mockito.eq("Location"), Mockito.eq("/author.jsp?lang=foo"));
        assertFalse(false, "The servlet is not vulnerable in goodG2B method.");
    }

    @Test
    public void testGoodB2G() throws Throwable {
        // Arrange
        CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17 servlet = new CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_17();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Set environment variable for testing
        String originalEnv = System.getenv("ADD");
        try {
            setEnvironmentVariable("ADD", "en-US\r\nContent-Length: 0");

            // Act
            servlet.goodB2G(request, response);

            // Assert
            Mockito.verify(response).setHeader(Mockito.eq("Location"), Mockito.not(Mockito.contains("\r\n")));
            assertFalse(false, "The servlet is not vulnerable in goodB2G method.");
        } finally {
            // Restore original environment variable
            setEnvironmentVariable("ADD", originalEnv);
        }
    }

    // Helper method to set environment variables for testing
    private static void setEnvironmentVariable(String key, String value) {
        try {
            java.lang.reflect.Field field = System.getenv().getClass().getDeclaredField("m");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            java.util.Map<String, String> env = (java.util.Map<String, String>) field.get(System.getenv());
            if (value == null) {
                env.remove(key);
            } else {
                env.put(key, value);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to set environment variable", e);
        }
    }
}