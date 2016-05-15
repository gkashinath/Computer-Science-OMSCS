package edu.gatech.seclass.a7;
import edu.gatech.seclass.a7.FaultyClass;

import static org.junit.Assert.*;

import org.junit.Test;

public class FaultyClassTestBC3 {

	//Achieves 100% branch coverage - reveals fault
	@Test
	public void test1() {
		assertEquals(1, FaultyClass.faultyMethod3(1, -1),0.01);
	}

	@Test
	public void test2() {
		assertEquals(1, FaultyClass.faultyMethod3(0, 0),0.01);
	}

}
