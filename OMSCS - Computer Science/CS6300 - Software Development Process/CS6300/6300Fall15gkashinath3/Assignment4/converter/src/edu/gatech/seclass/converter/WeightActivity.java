package edu.gatech.seclass.converter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
//import edu.gatech.unitconverter.MainActivity;
import edu.gatech.unitconverter.R;

public class WeightActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weight);
	}

	@SuppressLint("DefaultLocale")
	public static String poundsToKg(double pounds) {
		double kg = pounds * 2.20462;
		String Kg = String.format("%.3f", kg);
		return Kg;
	}

	@SuppressLint("DefaultLocale")
	public static String kgToPounds(double kg) {
		double pounds = kg / 2.20462;
		String Pounds = String.format("%.3f", pounds);
		return Pounds;
	}

	public void handleClickWeight(View view) {

		Double weight = 0.0;
		EditText txt = (EditText) findViewById(R.id.text1);
		try {
			weight = Double.parseDouble(txt.getText().toString());

		} catch (NumberFormatException e) {
			txt.setText("Enter the currency to be converted");
		}

		boolean checked = ((RadioButton) view).isChecked();
		weight = Double.parseDouble(txt.getText().toString());

		switch (view.getId()) {

		case R.id.radio0:
			if (checked)
				txt.setText(poundsToKg(weight));
			break;
		case R.id.radio1:
			if (checked)
				txt.setText(kgToPounds(weight));
			break;
		}
	}

	public void goBack(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
