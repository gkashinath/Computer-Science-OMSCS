package edu.gatech.seclass.a7;
import edu.gatech.seclass.a7.FaultyClass;

import static org.junit.Assert.*;

import org.junit.Test;

public class FaultyClassTestPC3 {

	//Achieves 100% path coverage - does not reveals fault
	@Test
	public void test1() {
		assertEquals(-1, FaultyClass.faultyMethod3(2, -2),0.01);
	}

	@Test
	public void test2() {
		assertEquals(-0.25, FaultyClass.faultyMethod3(-2, 2),0.01);
	}
	
	@Test
	public void test3() {
		assertEquals(0.5, FaultyClass.faultyMethod3(2, 2),0.01);
	}
	
	@Test
	public void test4() {
		assertEquals(0.5, FaultyClass.faultyMethod3(-2, -2),0.01);
	}
}
