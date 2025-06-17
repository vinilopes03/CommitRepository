// Added antbuild/testcases/CWE835_Infinite_Loop/Main.java
package ;

import testcasesupport.*;

public class Main {

	public static void main(String[] args) {
		
		if(args.length != 0) {
		
			if(args[0].equals("-h") || args[0].equals("--help")) {
		
				System.err.println("To use this main, you can either run the program with no " +
				"command line argu...