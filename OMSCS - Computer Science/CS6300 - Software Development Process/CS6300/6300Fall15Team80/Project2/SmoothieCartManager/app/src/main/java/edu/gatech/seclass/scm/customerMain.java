package edu.gatech.seclass.scm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class customerMain extends AppCompatActivity {
    Button bCustAdd;
    Button bCustEdit;
    Button bPrintTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        bCustAdd = (Button)findViewById(R.id.custMainAdd);
        bCustAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent id = new Intent(getApplicationContext(), customerAddEdit.class);
                startActivity(id);
            }
        });

        bCustEdit = (Button)findViewById(R.id.custMainEdit);
        bCustEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent id = new Intent(getApplicationContext(), customerEdit.class);
                startActivity(id);
            }
        });

        bPrintTrans = (Button)findViewById(R.id.custMainPrintTrans);
        bPrintTrans.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent id = new Intent(getApplicationContext(), customerPrintTrans.class);
                startActivity(id);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer_main, menu);
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
}
