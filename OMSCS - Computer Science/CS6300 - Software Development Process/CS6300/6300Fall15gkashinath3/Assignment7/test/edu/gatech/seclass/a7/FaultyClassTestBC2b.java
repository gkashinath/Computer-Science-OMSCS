package edu.gatech.seclass.a7;
import edu.gatech.seclass.a7.FaultyClass;

import static org.junit.Assert.*;

import org.junit.Test;

public class FaultyClassTestBC2b {

	//Achieves 50% branch coverage - reveals fault	
	@Test
	public void test1() {
		//assertEquals("Infinity",String.valueOf(FaultyClass.faultyMethod1(1.0)));
		assertEquals(1, FaultyClass.faultyMethod2(0),0.01);
	}
}
