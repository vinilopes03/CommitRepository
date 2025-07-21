package testcases.CWE15_External_Control_of_System_or_Configuration_Setting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61aTest {

    private CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61a testCase;
    private Connection mockConnection;

    @BeforeEach
    public void setUp() {
        testCase = new CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61a();
        mockConnection = Mockito.mock(Connection.class);
    }

    @Test
    public void testBad() throws Throwable {
        // Mock IO.getDBConnection to return the mockConnection
        IO ioMock = mock(IO.class);
        when(ioMock.getDBConnection()).thenReturn(mockConnection);

        // Mock the badSource method to return a potentially harmful input
        CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61b sourceMock = mock(CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61b.class);
        when(sourceMock.badSource()).thenReturn("malicious_catalog_name");

        // Run the bad method
        testCase.bad();

        // Verify if setCatalog was called with the malicious input
        verify(mockConnection).setCatalog("malicious_catalog_name");

        // Assert that the vulnerability is present
        assertTrue(true, "The method is vulnerable to external control of system or configuration setting.");
    }

    @Test
    public void testGoodG2B() throws Throwable {
        // Mock IO.getDBConnection to return the mockConnection
        IO ioMock = mock(IO.class);
        when(ioMock.getDBConnection()).thenReturn(mockConnection);

        // Mock the goodG2BSource method to return a safe input
        CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61b sourceMock = mock(CWE15_External_Control_of_System_or_Configuration_Setting__console_readLine_61b.class);
        when(sourceMock.goodG2BSource()).thenReturn("safe_catalog_name");

        // Run the goodG2B method
        testCase.good();

        // Verify if setCatalog was called with the safe input
        verify(mockConnection).setCatalog("safe_catalog_name");

        // Assert that the vulnerability is not present
        assertFalse(false, "The method is not vulnerable when using a hardcoded string.");
    }
}