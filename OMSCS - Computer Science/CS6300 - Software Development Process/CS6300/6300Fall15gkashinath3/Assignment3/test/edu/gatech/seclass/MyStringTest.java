package edu.gatech.seclass;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import org.junit.After;
import org.junit.Before;

public class MyStringTest {

	private MyStringInterface mystring;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		mystring = new MyString();
	}

	@After
	public void tearDown() throws Exception {
		mystring = null;
	}

	///////////////////////////////////////////////////////////
	//            Testing GetConsonants Function             //
	///////////////////////////////////////////////////////////
	
	//This test will pick all the consonants in the sentence and check if it is 
	//returned in correct order. This is one of the tests provided by the Instructor.
	@Test
	public void testGetConsonants1() {
		mystring.setString("I like vowels better than consonants");
		assertEquals("lkvwlsbttrthncnsnnts", mystring.getConsonants());
	}
	
	//Test 1: This test will make sure the letter 'y' is considered a 
	//consonant.
	@Test
	public void testGetConsonants2() {
		mystring.setString("is 'y' a consonant?");
        assertEquals("sycnsnnt", mystring.getConsonants());
	}
	//Test 2: This test will make sure only letters are considered while
	//counting consonants and not "special" characters.
	@Test
	public void testGetConsonants3() {
		mystring.setString("% what?! @one billion $$ ... %");
		assertEquals("whtnblln", mystring.getConsonants());
	}
	//Test 3: This test will make sure only letters are considered while
	//counting consonants and not numbers.
	@Test
	public void testGetConsonants4() {
		mystring.setString("1 bi1li0n do1l4r5");
		assertEquals("blndlr", mystring.getConsonants());
	}
	//Test 4: This test will make sure the case of the letters are same
	//as the input.
	@Test
	public void testGetConsonants5() {
		mystring.setString("iS this CASE sensiTiVE");
		assertEquals("SthsCSsnsTV", mystring.getConsonants());
	}
	//Test 5: This test will make sure null is returned when all letters
	//entered are vowels
	@Test
	public void testGetConsonants6() {
		mystring.setString("aeiou");
		assertEquals("", mystring.getConsonants());
	}
	
	///////////////////////////////////////////////////////////
	//            Testing NumberOfConsonants Function        //
	///////////////////////////////////////////////////////////
	
	//This test will count the number of consonants and check if it is 
	//correct. This is one of the tests provided by the Instructor.
	@Test
	public void testNumberOfConsonants1() {
		mystring.setString("I like vowels better than consonants");
		assertEquals(20, mystring.numberOfConsonants());
	}
	//Test 1: This test will make sure 0 is returned when all letters
    //entered are vowels.
	@Test
	public void testNumberOfConsonants2() {
		mystring.setString("aeiou");
		assertEquals(0, mystring.numberOfConsonants());
	}
	//Test 2: This test will make sure 0 is returned when all letters
    //entered are special characters.
	@Test
	public void testNumberOfConsonants3() {
		mystring.setString("#*&$%");
		assertEquals(0, mystring.numberOfConsonants());
	}
	//Test 3: This test will make sure 0 is returned when all letters
    //entered are numbers.
	@Test
	public void testNumberOfConsonants4() {
		mystring.setString("93674128");
		assertEquals(0, mystring.numberOfConsonants());
	}
	//Test 4: This test will make sure only the consonants irrespective  
    //of the case are counted.
	@Test
	public void testNumberOfConsonants5() {
		mystring.setString("iS this CASE sensiTiVE");
		assertEquals(11, mystring.numberOfConsonants());
	}
	
	///////////////////////////////////////////////////////////
	//            Testing GetCharacter Function              //
	///////////////////////////////////////////////////////////
	
	//This test will check if the right character is picked in the 
	//string. This is one of the tests provided by the Instructor.
	@Test
	public void testGetCharacter1() {
		mystring.setString("I like vowels better than consonants");
		assertEquals('e', mystring.getCharacter(16));
	}
	//Test 1: This will test if the illegal argument exception is 
	//invoked for negative input.
	@Test
	public void testGetCharacter2() {
		expectedEx.expect(IllegalArgumentException.class);
	    expectedEx.expectMessage(MyString.INDEX_LESS_THAN_1);
	    
		MyString testClass = new MyString();
		testClass.setString("I like vowels better than consonants");
		testClass.getCharacter(-1);
	}
	//Test 2: This will test if the illegal argument exception is 
	//invoked for 0 input.
	@Test
	public void testGetCharacter3() {
		expectedEx.expect(IllegalArgumentException.class);
	    expectedEx.expectMessage(MyString.INDEX_LESS_THAN_1);
	    
		MyString testClass = new MyString();
		testClass.setString("I like vowels better than consonants");
		testClass.getCharacter(0);
	}
	//Test 3: This will test if the illegal index exception is 
	//invoked for input greater than the length of the input string.
	@Test
	public void testGetCharacter4() {
		expectedEx.expect(IllegalIndexException.class);
		
		MyString testClass = new MyString();
		testClass.setString("I like vowels better than consonants");
		testClass.getCharacter(37);
	}
	//Test 4: This will test if the first character is picked correctly.
	@Test
	public void testGetCharacter5() {
		mystring.setString("I like vowels better than consonants");
		assertEquals('I', mystring.getCharacter(1));
	}
	//Test 5: This will test if the last character which could be a 
	//number is picked correctly.
	@Test
	public void testGetCharacter6() {
		mystring.setString("I like v0wel5 better than c0ns0nant5");
		assertEquals('5', mystring.getCharacter(36));
	}
	
	///////////////////////////////////////////////////////////
	//      Testing testFlipCaseInSubstring Function         //
	///////////////////////////////////////////////////////////
	
	//This test will check if the case of the right characters 
	//in the string is flip correctly. This is one of the 
	//tests provided by the Instructor.
	@Test
	public void testFlipCaseInSubstring1() {
		mystring.setString("I Like Vowels Better Than Consonants");
		mystring.flipCaseInSubstring(7, 21);
		assertEquals("I Like vOWELS bETTER Than Consonants", mystring.getString());	
	}

	//Test 1: This will test if the illegal argument exception is 
	//invoked for negative start index.
	@Test
	public void testFlipCaseInSubstring2() {
		expectedEx.expect(IllegalArgumentException.class);
	    expectedEx.expectMessage(MyString.INDEX_LESS_THAN_1);
	    
		MyString testClass = new MyString();
		testClass.setString("I Like Vowels Better Than Consonants");
		testClass.flipCaseInSubstring(0, 4);
	}
	//Test 2: This will test if the illegal argument exception is 
	//invoked for negative end index.
	@Test
	public void testFlipCaseInSubstring3() {
		expectedEx.expect(IllegalArgumentException.class);
	    expectedEx.expectMessage(MyString.INDEX_LESS_THAN_1);
	    
		MyString testClass = new MyString();
		testClass.setString("I Like Vowels Better Than Consonants");
		testClass.flipCaseInSubstring(1, -1);
	}
	//Test 3: This will test if the illegal index exception exception 
	//is invoked for input start index greater than end index.
	@Test
	public void testFlipCaseInSubstring4() {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage(MyString.START_GREATER_THAN_END_INDEX);
	    
		MyString testClass = new MyString();
		testClass.setString("I Like Vowels Better Than Consonants");
		testClass.flipCaseInSubstring(5, 1);
	}
	//Test 4: This will test if the illegal index exception is 
	//invoked for input greater than the length of the input string.
	@Test
	public void testFlipCaseInSubstring5() {
		expectedEx.expect(IllegalIndexException.class);
		
		MyString testClass = new MyString();
		testClass.setString("I Like Vowels Better Than Consonants");
		testClass.flipCaseInSubstring(5, 37);
	}
}
