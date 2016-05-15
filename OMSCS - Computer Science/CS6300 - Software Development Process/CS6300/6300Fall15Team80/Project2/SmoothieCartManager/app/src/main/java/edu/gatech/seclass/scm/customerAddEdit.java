package edu.gatech.seclass.scm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.scm.model.Customer;
import edu.gatech.seclass.scm.model.CustomerManager;
import edu.gatech.seclass.scm.model.DBAdapter;
import edu.gatech.seclass.scm.model.exceptions.CouldNotAddCustomer;
import edu.gatech.seclass.scm.model.exceptions.CouldNotReadCustomerTable;
import edu.gatech.seclass.scm.model.exceptions.ErrorScanningQRCard;
import edu.gatech.seclass.scm.model.exceptions.NoSuchCustomer;

public class customerAddEdit extends AppCompatActivity {
    Button bPTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_add_edit);
//        bPTrans = (Button)findViewById(R.id.bPrintHistory);
//        bPTrans.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent id = new Intent(getApplicationContext(), customerPrintTrans.class);
//                startActivity(id);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer_add_edit, menu);
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

    public void handleCustomerAdd(android.view.View view){

        CustomerManager customerManager = new CustomerManager(new DBAdapter(this));

        String custID;
        EditText txtNAME = (EditText) findViewById(R.id.editText);//Name
        EditText txtADDRESS = (EditText) findViewById(R.id.editText2);//Address
        EditText txtEMAIL = (EditText) findViewById(R.id.editText3);//Email
        TextView txtCUSTOMERID = (TextView) findViewById(R.id.editText4);//CustomerID

        String name = txtNAME.getText().toString();
        String address = txtADDRESS.getText().toString();
        String email =txtEMAIL.getText().toString();
        if ((txtNAME.getText().toString().length() == 0) || (txtNAME.getText().toString() == null)){
            Toast.makeText(getApplicationContext(), "ERROR: Name field empty",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if ((txtADDRESS.getText().toString().length() == 0) || (txtADDRESS.getText().toString() == null)){
            Toast.makeText(getApplicationContext(), "ERROR: Address field empty",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if ((txtEMAIL.getText().toString().length() == 0) || (txtEMAIL.getText().toString() == null)){
            Toast.makeText(getApplicationContext(), "ERROR: Email ID empty",
                    Toast.LENGTH_SHORT).show();
            return;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getApplicationContext(), "ERROR: Email ID invalid",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            custID = customerManager.addCustomer(name, address, email);
        } catch(CouldNotAddCustomer couldNotAddCustomer) {
            Toast.makeText(getApplicationContext(), "ERROR: Unable to Add Customer",
            Toast.LENGTH_SHORT).show();
            return;
        }
        if ((custID.length() == 0) || (custID == null)){
            Toast.makeText(getApplicationContext(), "ERROR: Customer ID empty",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        txtCUSTOMERID.setText(custID);
        Toast.makeText(getApplicationContext(), "SUCCESS: Customer Added",
                Toast.LENGTH_SHORT).show();
    }
}
