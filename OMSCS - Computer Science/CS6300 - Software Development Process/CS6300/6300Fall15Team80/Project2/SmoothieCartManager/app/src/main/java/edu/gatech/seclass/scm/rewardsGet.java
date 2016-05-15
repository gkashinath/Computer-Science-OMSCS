package edu.gatech.seclass.scm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.gatech.seclass.scm.model.Customer;

import edu.gatech.seclass.scm.model.*;
import edu.gatech.seclass.scm.model.exceptions.CouldNotReadCustomerTable;
import edu.gatech.seclass.scm.model.exceptions.ErrorScanningQRCard;
import edu.gatech.seclass.scm.model.exceptions.NoSuchCustomer;

public class rewardsGet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards_get);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rewards_get, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void handleRewardsGet(android.view.View view) {

        CustomerManager customerManager = new CustomerManager(new DBAdapter(this));

        Customer customer;

        try {
            customer = customerManager.getCustomerByQRCard();
        } catch(ErrorScanningQRCard e) {
            Toast.makeText(getApplicationContext(), "Error scanning QR card",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch(CouldNotReadCustomerTable e) {
            Toast.makeText(getApplicationContext(), "Error reading customer info from database.",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch(NoSuchCustomer e) {
            Toast.makeText(getApplicationContext(), "Customer does not exist.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        setContentView(R.layout.activity_rewards_get);

        EditText txtGS = (EditText) findViewById(R.id.editText20);
        EditText txtCD = (EditText) findViewById(R.id.editText21);
        EditText txtED = (EditText) findViewById(R.id.editText22);
        EditText txtCustomerName = (EditText) findViewById(R.id.editText27);
        EditText txtCustomerID = (EditText) findViewById(R.id.editText26);

        txtCustomerName.setText(customer.getName());
        txtCustomerID.setText(customer.getID());

        txtGS.setText(Boolean.toString(customer.getGoldStatus()));
        txtCD.setText(Double.toString(customer.getCredit()));

        Date creditExpiryDate = customer.getCreditExpiryDate();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String creditExpiryDateString = df.format(creditExpiryDate);

        txtED.setText(creditExpiryDateString);


    }
}
