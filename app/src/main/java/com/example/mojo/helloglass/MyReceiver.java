package com.example.mojo.helloglass;

/**
 * Created by Mojo on 3/25/15.
 */


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Bluetooth", "Message");
    }

}