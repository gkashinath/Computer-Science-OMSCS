package edu.gatech.seclass.scm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class rewardsGetSet extends AppCompatActivity {
    Button bRGet;
    Button bRSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards_get_set);
        bRGet = (Button)findViewById(R.id.bRewardGet);
        bRGet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent id = new Intent(getApplicationContext(), rewardsGet.class);
                startActivity(id);
            }
        });

        bRSet = (Button)findViewById(R.id.bRewardSet);
        bRSet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent id = new Intent(getApplicationContext(), rewardsSet.class);
                startActivity(id);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rewards_get_set, menu);
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
