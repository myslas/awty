package edu.washington.yslasm.awty;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context c, Intent intent) {

        String msg = intent.getStringExtra("message");
        String number = intent.getStringExtra("phone");
        // For our recurring task, we'll just display a message
        Toast.makeText(c, number + ": " + msg, Toast.LENGTH_SHORT).show();

    }

}