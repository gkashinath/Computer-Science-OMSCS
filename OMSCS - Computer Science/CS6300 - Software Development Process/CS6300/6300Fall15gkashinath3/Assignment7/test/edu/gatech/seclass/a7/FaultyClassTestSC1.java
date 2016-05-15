package edu.gatech.seclass.a7;
import edu.gatech.seclass.a7.FaultyClass;

import static org.junit.Assert.*;

import org.junit.Test;

public class FaultyClassTestSC1 {

	@Test
	//Achieves 100% statement coverage - does not reveal fault
	public void test1() {
		assertEquals(1, FaultyClass.faultyMethod1(2),0.01);
	}

}
