package edu.washington.yslasm.awty;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView message = (TextView) findViewById(R.id.message);
        final TextView phone = (TextView) findViewById(R.id.phone);
        final TextView interval = (TextView) findViewById(R.id.interval);
        final Button b = (Button) findViewById(R.id.button);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(message.getText() != "" && phone.getText() != "" && interval.getText() != "") {
                    if (b.getText() == "Start") {
                        b.setText("Stop");
                        Intent myIntent = new Intent(MainActivity.this, AlarmReceiver.class);
                        myIntent.putExtra("message", "" + message.getText());
                        myIntent.putExtra("phone", "" + phone.getText());
                        pendingIntent = PendingIntent.getBroadcast(MainActivity.this,  0, myIntent, 0);

                        alarmManager = (AlarmManager)MainActivity.this.getSystemService(Context.ALARM_SERVICE);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        long frequency= Integer.parseInt("" + interval.getText()) * 1000; // in ms
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), frequency, pendingIntent);
                        Toast.makeText(MainActivity.this, phone.getText() + ": " + message.getText(), Toast.LENGTH_LONG).show();
                    } else {
                        b.setText("Start");
                        alarmManager = (AlarmManager)MainActivity.this.getSystemService(Context.ALARM_SERVICE);
                        alarmManager.cancel(pendingIntent);

                    }

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


