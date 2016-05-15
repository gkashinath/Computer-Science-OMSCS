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

public class CurrencyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_currency);
	}

	/* US dollar to Euro conversion */
	@SuppressLint("DefaultLocale")
	public static String usdToEUR(double usd) {
		double eur = usd / 1.12;
		String Eur = String.format("%.2f", eur);
		return Eur;
	}

	/* Euro to US dollar conversion */
	@SuppressLint("DefaultLocale")
	public static String eurToUSD(double eur) {
		double usd = eur * 1.12;
		String Usd = String.format("%.2f", usd);
		return Usd;
	}

	public void handleClickCurrency(View view) {

		Double currency = 0.0;
		EditText txt = (EditText) findViewById(R.id.text1);
		try {
			currency = Double.parseDouble(txt.getText().toString());

		} catch (NumberFormatException e) {
			txt.setText("Enter the currency to be converted");
		}

		boolean checked = ((RadioButton) view).isChecked();
		currency = Double.parseDouble(txt.getText().toString());

		switch (view.getId()) {

		case R.id.radio0:
			if (checked)
				txt.setText(eurToUSD(currency));
			break;
		case R.id.radio1:
			if (checked)
				txt.setText(usdToEUR(currency));
			break;
		}
	}

	public void goBack(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
