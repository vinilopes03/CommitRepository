package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_07 extends AbstractTestCaseServlet {
    private int privateFive = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // bad method implementation
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // goodG2B1 method implementation
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // goodG2B2 method implementation
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive == 5) {
            data = ""; // Initialize data
            File file = new File("C:\\data.txt");
            try (FileInputStream streamFileInput = new FileInputStream(file);
                 InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data from a file
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // Dead code to avoid compiler errors
        }

        if (privateFive != 5) {
            IO.writeLine("Benign, fixed string");
        } else {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                response.addCookie(cookieSink); // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFive == 5) {
            data = ""; // Initialize data
            File file = new File("C:\\data.txt");
            try (FileInputStream streamFileInput = new FileInputStream(file);
                 InputStreamReader readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
                 BufferedReader readerBuffered = new BufferedReader(readerInputStream)) {

                data = readerBuffered.readLine(); // POTENTIAL FLAW: Read data from a file
            } catch (IOException exceptIO) {
                IO.logger.log(Level.WARNING, "Error with stream reading", exceptIO);
            }
        } else {
            data = null; // Dead code to avoid compiler errors
        }

        if (privateFive == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                response.addCookie(cookieSink); // FIX: use URLEncoder.encode to hex-encode non-alphanumerics
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}