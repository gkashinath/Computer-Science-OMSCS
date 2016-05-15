package edu.gatech.seclass.a7;
import edu.gatech.seclass.a7.FaultyClass;

import static org.junit.Assert.*;

import org.junit.Test;

public class FaultyClassTestBC4 {

	//Achieves 100% branch coverage - not reveal fault
	@Test
	public void test1() {
		assertEquals(-1, FaultyClass.faultyMethod4(-2),0.01);
	}
	
	@Test
	public void test2() {
		assertEquals(0.5, FaultyClass.faultyMethod4(2),0.01);
	}

}
