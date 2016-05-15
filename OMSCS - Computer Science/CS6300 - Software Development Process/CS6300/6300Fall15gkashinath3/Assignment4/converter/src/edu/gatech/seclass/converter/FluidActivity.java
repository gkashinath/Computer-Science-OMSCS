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

public class FluidActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fluid);
	}

	@SuppressLint("DefaultLocale")
	public static String ounceToLiter(double ounce) {
		double liter = ounce * 33.814;
		String Liter = String.format("%.3f", liter);
		return Liter;
	}

	@SuppressLint("DefaultLocale")
	public static String literToOunce(double liter) {
		double ounce = liter / 33.814;
		String Ounce = String.format("%.3f", ounce);
		return Ounce;
	}

	public void handleClickFluid(View view) {

		Double fluid = 0.0;
		EditText txt = (EditText) findViewById(R.id.text1);
		try {
			fluid = Double.parseDouble(txt.getText().toString());

		} catch (NumberFormatException e) {
			txt.setText("Enter the currency to be converted");
		}

		boolean checked = ((RadioButton) view).isChecked();
		fluid = Double.parseDouble(txt.getText().toString());

		switch (view.getId()) {

		case R.id.radio0:
			if (checked)
				txt.setText(ounceToLiter(fluid));
			break;
		case R.id.radio1:
			if (checked)
				txt.setText(literToOunce(fluid));
			break;
		}
	}

	public void goBack(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
