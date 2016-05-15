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

public class AreaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_area);
	}

	/* Square meter conversions */
	@SuppressLint("DefaultLocale")
	public static String sqmeterToSqFoot(double sqm) {
		double sqfoot = sqm * 10.7639;
		String Sqfoot = String.format("%.3f", sqfoot);
		return Sqfoot;
	}

	/* Square foot conversions */
	@SuppressLint("DefaultLocale")
	public static String sqfootToSqMeter(double sqft) {
		double sqmeter = sqft / 10.7639;
		String Sqmeter = String.format("%.3f", sqmeter);
		return Sqmeter;
	}

	public void handleClickArea(View view) {

		Double area = 0.0;
		EditText txt = (EditText) findViewById(R.id.text1);
		if (txt.getText().toString() == "")
			return;
		try {
			area = Double.parseDouble(txt.getText().toString());
		} catch (NumberFormatException e) {
			txt.setText("Enter the area to be converted");
		}

		boolean checked = ((RadioButton) view).isChecked();
		area = Double.parseDouble(txt.getText().toString());

		switch (view.getId()) {

		case R.id.radio0:
			if (checked)
				txt.setText(sqmeterToSqFoot(area));
			break;
		case R.id.radio1:
			if (checked)
				txt.setText(sqfootToSqMeter(area));
			break;
		}
	}

	public void goBack(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}
