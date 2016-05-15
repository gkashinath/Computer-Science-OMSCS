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

public class DistanceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_distance);
	}

	/* Kilometer conversion */
	@SuppressLint("DefaultLocale")
	public static String kmToMile(double km) {
		double mile = km / 1.60934;
		String Mile = String.format("%.3f", mile);
		return Mile;
	}

	/* Miles conversion */
	@SuppressLint("DefaultLocale")
	public static String mileToKilometer(double mile) {
		double kilometer = mile * 1.60934;
		String Kilometer = String.format("%.3f", kilometer);
		return Kilometer;
	}

	public void handleClickDistance(View view) {

		Double distance = 0.0;
		EditText txt = (EditText) findViewById(R.id.text1);
		try {
			distance = Double.parseDouble(txt.getText().toString());

		} catch (NumberFormatException e) {
			txt.setText("Enter the distance to be converted");
		}

		boolean checked = ((RadioButton) view).isChecked();

		distance = Double.parseDouble(txt.getText().toString());
		switch (view.getId()) {

		case R.id.radio0:
			if (checked)
				txt.setText(kmToMile(distance));
			break;
		case R.id.radio1:
			if (checked)
				txt.setText(mileToKilometer(distance));
			break;
		}
	}

	public void goBack(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
