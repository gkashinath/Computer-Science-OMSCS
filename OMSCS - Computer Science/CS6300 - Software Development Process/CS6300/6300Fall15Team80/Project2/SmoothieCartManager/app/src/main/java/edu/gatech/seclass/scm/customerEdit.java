package edu.gatech.seclass.scm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.CustomerManager;
import edu.gatech.seclass.scm.model.DBAdapter;
import edu.gatech.seclass.scm.model.exceptions.*;

public class customerEdit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer_edit, menu);
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

    public void handleCustomerGet(android.view.View view){
        CustomerManager customerManager = new CustomerManager(new DBAdapter(this));

        EditText txtNAME = (EditText) findViewById(R.id.editText7);//Name
        EditText txtADDRESS = (EditText) findViewById(R.id.editText19);//Address
        EditText txtEMAIL = (EditText) findViewById(R.id.editText23);//Email
        EditText txtCUSTOMERID = (EditText) findViewById(R.id.editText24);//CustomerID

        Customer customer;

        try {
            customer = customerManager.getCustomerByQRCard();
        } catch(CouldNotReadCustomerTable e) {
            Toast.makeText(getApplicationContext(), "Error reading customer info from database.",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch(NoSuchCustomer e) {
            Toast.makeText(getApplicationContext(), "Customer does not exist.",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch(ErrorScanningQRCard e) {
            Toast.makeText(getApplicationContext(), "Error scanning QR card.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        txtCUSTOMERID.setText(customer.getID());
        txtNAME.setText(customer.getName());
        txtADDRESS.setText(customer.getAddress());
        txtEMAIL.setText(customer.getEmailAddress());
    }

    public void handleCustomerUpdate(android.view.View view){

        CustomerManager customerManager = new CustomerManager(new DBAdapter(this));

        EditText txtNAME = (EditText) findViewById(R.id.editText7);//Name
        EditText txtADDRESS = (EditText) findViewById(R.id.editText19);//Address
        EditText txtEMAIL = (EditText) findViewById(R.id.editText23);//Email
        EditText txtCUSTOMERID = (EditText) findViewById(R.id.editText24);//CustomerID

        String name = txtNAME.getText().toString();
        String address = txtADDRESS.getText().toString();
        String email =txtEMAIL.getText().toString();
        String ID = txtCUSTOMERID.getText().toString();

        try {
            customerManager.editCustomerDetails(ID, name, address, email);
        } catch(CouldNotReadCustomerTable e) {
            Toast.makeText(getApplicationContext(), "Error reading customer info from database.",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch(CouldNotUpdateCustomerDetails e) {
            Toast.makeText(getApplicationContext(), "Error writing customer info to database.",
                    Toast.LENGTH_SHORT).show();
            return;
        } catch(NoSuchCustomer e) {
            Toast.makeText(getApplicationContext(), "Customer with ID " + ID + " does not exist.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getApplicationContext(), "SUCCESS: Customer Updated",
                Toast.LENGTH_SHORT).show();
    }
}
