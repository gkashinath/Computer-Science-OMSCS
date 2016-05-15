package edu.gatech.seclass.converter;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import edu.gatech.unitconverter.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void distance(View view) {
		// call Distance_Activity
		Intent intent = new Intent(this, DistanceActivity.class);
		startActivity(intent);
	}

	public void area(View view) {
		// call Area_Activity
		Intent intent = new Intent(this, AreaActivity.class);
		startActivity(intent);
	}

	public void currency(View view) {
		// call Currency_Activity
		Intent intent = new Intent(this, CurrencyActivity.class);
		startActivity(intent);
	}

	public void fluid(View view) {
		// call Fluid_Activity
		Intent intent = new Intent(this, FluidActivity.class);
		startActivity(intent);
	}

	public void weight(View view) {
		// call Weight_Activity
		Intent intent = new Intent(this, WeightActivity.class);
		startActivity(intent);
	}

}
