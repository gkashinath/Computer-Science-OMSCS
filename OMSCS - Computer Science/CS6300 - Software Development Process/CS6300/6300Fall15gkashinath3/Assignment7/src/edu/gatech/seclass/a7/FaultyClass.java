package edu.gatech.seclass.a7;

public class FaultyClass {
	
	public static float faultyMethod1(float i) {
		float result = 1;
		if(i>0){
			i=i-1;
		}
		result = result/i;
		return result;
	}
	
	public static float faultyMethod2(float i) {
		float result = 1;
		if(i>0){
			i=i-1;
		}
		result = result/i;	
		return result;
	}
	
	public static float faultyMethod3(float i, float j) {
		float result = 1;
		if(i>0){
			i=i-1;
		}
		if(j<0){
			j=j+1;
		}
		result = result/(i*j);
		return result;
	} 
	
	public static float faultyMethod4(float i) {
		float result = 1;
		if(i<0){
			i=i+1;
		}
		result = result/i;
		//System.out.println(result);	
		return result;
	}
	
}
