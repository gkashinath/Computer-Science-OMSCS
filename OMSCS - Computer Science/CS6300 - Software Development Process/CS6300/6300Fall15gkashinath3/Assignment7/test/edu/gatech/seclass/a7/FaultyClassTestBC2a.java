package edu.gatech.seclass.a7;
import edu.gatech.seclass.a7.FaultyClass;

import static org.junit.Assert.*;

import org.junit.Test;

public class FaultyClassTestBC2a {

	//Achieves 100% branch coverage - does not reveals fault	
	@Test
	public void test1() {
		//assertEquals("Infinity",String.valueOf(FaultyClass.faultyMethod1(1.0)));
		assertEquals(1, FaultyClass.faultyMethod2(2),0.01);
	}
	
	@Test
	public void test2() {
		//assertEquals("Infinity",String.valueOf(FaultyClass.faultyMethod1(-1.0)));
		assertEquals(-1, FaultyClass.faultyMethod2(-1),0.01);
	}
}
