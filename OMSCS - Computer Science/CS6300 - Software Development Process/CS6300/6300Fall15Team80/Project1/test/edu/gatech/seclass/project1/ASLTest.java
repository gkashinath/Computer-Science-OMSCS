package edu.gatech.seclass.project1;
import edu.gatech.seclass.project1.*;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ASLTest {
	private ASL myASL;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		myASL = new ASL();
		
	}

	@After
	public void tearDown() throws Exception {
		myASL = null;
	}

	////////////////////////////////////////
	//ASL Test Cases
	////////////////////////////////////////
	
	
	@Test
	public void nominalASLTest1() throws Exception {
		//This test checks that the proper value of average sentence length is returned with modified delimiter and word length
		
		int asl = myASL.averageSentenceLength("testcase/test_text_document.txt", ".", 2);
		assertEquals( 12, asl);
	}
	
	@Test
	public void nominalASLTest2() throws Exception {
		//This test checks that the proper value of average sentence length is returned with modified delimiter and word length
		
		int asl = myASL.averageSentenceLength("testcase/test_text_document.txt", ".?!", 4);
		assertEquals( 7, asl);
	}	
	
	@Test
	public void nominalASLTest3() throws Exception {
		//This test checks that the proper value of average sentence length is returned with default as applied to non standard files
		
		int asl = myASL.averageSentenceLength("testcase/t1", ".?!:;", 4);
		assertEquals( 0, asl);
		asl = myASL.averageSentenceLength("testcase/t2", ".?!:;", 4);
		assertEquals( 1, asl);
		asl = myASL.averageSentenceLength("testcase/t3", ".?!:;", 4);
		assertEquals( 0, asl);
		asl = myASL.averageSentenceLength("testcase/t4", ".?!:;", 4);
		assertEquals( 5, asl);
		asl = myASL.averageSentenceLength("testcase/t5", ".?!:;", 4);
		assertEquals( 3, asl);
		asl = myASL.averageSentenceLength("testcase/t6", ".?!:;", 4);
		assertEquals( 0, asl);
		asl = myASL.averageSentenceLength("testcase/t7", ".?!:;", 4);
		assertEquals( 2, asl);
		asl = myASL.averageSentenceLength("testcase/t8", ".?!:;", 4);
		assertEquals( 2, asl);
		asl = myASL.averageSentenceLength("testcase/t9", ".?!:;", 4);
		assertEquals( 3, asl);
		asl = myASL.averageSentenceLength("testcase/t10", ".?!:;", 4);
		assertEquals( 2, asl);
	}	
	
	
	////////////////////////////////////////
	//WC Test Cases
	////////////////////////////////////////
	
	@Test
	public void nominalWCTest1() throws Exception {
		//This test checks that the software runs with no exceptions
		
		String[] text_file = { "testcase/test_text_document.txt", "-d", ".", "-l", "4" };
		WC.main(text_file);
	}
	
	public void nominalWCTest2() throws Exception {
		//this test checks that the software can handle an alternate orientation of commands - delimiter supplied first
		
		String[] text_file = {"-d", ".? !;", "testcase/test_text_document.txt" };
		WC.main(text_file);
	}	
	
	@Test(expected=FileDoesNotExistException.class)
	public void test_FileDoesNotExistException() throws Exception {
		//This test will check that the proper exception is thrown when the specified file does not exist
		
		String[] text_file = { "testcase/test_textt.txt" };
		WC.main(text_file);
	}
	
	@Test(expected=IllFormedCommandException.class)
	public void testIllFormedCommandException1() throws Exception {
		//This test checks that the proper exception is thrown when the command format is incorrect
		
		String[] text_file = { "testcase/test_text_document.txt", "-l", "-3" };
		WC.main(text_file);
	}
	
	@Test(expected=IllFormedCommandException.class)
	public void testIllFormedCommandException2() throws Exception {
		//This test checks that the proper exception is thrown when the delimiter is improperly supplied
		
		String[] text_file = {"-d", "testcase/test_text_document.txt" };
		WC.main(text_file);
	}
	
	@Test(expected=UnexpectedErrorException.class)
	public void testUnexpectedErrorException() throws Exception {
		//This test checks that the proper exception is thrown when the error is unknown
		String[] text_file = { "testcase/test_text_document.txt", "-l", "-3", "-d" , ";:." , "-G", "10" };
		WC.main(text_file);
	}

	@Test
	public void testWCOutput() throws Exception {
		//This test ensures that the proper value is printed to the command line when the WC class is called directly
		
		//Capture command line output - println
		final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));
		
		//Run test
		String[] command = { "testcase/test_text_document.txt", "-d", ".?!", "-l", "4" };
		WC.main(command);
		
		//copy command line output to string
		final String standardOutput = myOut.toString();
		//compare output to expected output
		assertEquals('7', standardOutput.charAt(0));
	}	

}
