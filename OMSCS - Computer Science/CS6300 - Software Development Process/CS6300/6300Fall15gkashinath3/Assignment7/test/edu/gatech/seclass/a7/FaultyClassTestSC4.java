package edu.gatech.seclass.a7;
import edu.gatech.seclass.a7.FaultyClass;

import static org.junit.Assert.*;

import org.junit.Test;

public class FaultyClassTestSC4 {

	@Test
	//Achieves 100% statement coverage but not 100% branch coverage - reveal fault
	public void test1() {
		assertEquals(1, FaultyClass.faultyMethod4(-1),0.01);
	}

}
