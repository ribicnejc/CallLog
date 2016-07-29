package com.nejc.povio.calllog;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.Date;

public class AddCallActivity extends AppCompatActivity {
    public static String phNumber = "";
    public static String callType = "";
    public static String callDate = "";
    public static Date callDayTime;
    public static String callDuration = "";
    public static String dir = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //MainActivity.addCity(api.getCityName(), api.getIconDesc(), api.getMainDesc(), api.getIcon(), api.getMainTemp(), api.getMinTemp(), api.getMaxTemp());
                        //
                        int id = R.drawable.received_call;
                        if (dir.equals("MISSED"))
                            id = R.drawable.missed_call;
                        if (dir.equals("OUTGOING"))
                            id = R.drawable.called_call;
                        if (dir.equals("INCOMING"))
                            id = R.drawable.received_call;
                        MainActivity.addCity(id, dir, phNumber, callDayTime.toString(), callDuration);
                        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                        //Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        //startActivity(intent);

                        Toast.makeText(AddCallActivity.this, "Phone call added", Toast.LENGTH_SHORT).show();
                        Intent setIntent = new Intent(Intent.ACTION_MAIN);
                        setIntent.addCategory(Intent.CATEGORY_HOME);
                        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(setIntent);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        Intent setIntent2 = new Intent(Intent.ACTION_MAIN);
                        setIntent2.addCategory(Intent.CATEGORY_HOME);
                        setIntent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(setIntent2);
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(AddCallActivity.this);
        builder.setMessage(getCallDetails(this)).setPositiveButton("Add", dialogClickListener)
                .setNegativeButton("Cancel", dialogClickListener).show();
    }

    public static String getCallDetails(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_CALL_LOG)
                == PackageManager.PERMISSION_GRANTED) {


            Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                    null, null, null, CallLog.Calls.DATE + " DESC");
            int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int date = cursor.getColumnIndex(CallLog.Calls.DATE);
            int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
            while (cursor.moveToNext() && i < 1) {
                phNumber = cursor.getString(number);
                callType = cursor.getString(type);
                callDate = cursor.getString(date);
                callDayTime = new Date(Long.valueOf(callDate));
                callDuration = cursor.getString(duration);
                dir = null;
                int dircode = Integer.parseInt(callType);
                switch (dircode) {
                    case CallLog.Calls.OUTGOING_TYPE:
                        dir = "OUTGOING";
                        break;
                    case CallLog.Calls.INCOMING_TYPE:
                        dir = "INCOMING";
                        break;

                    case CallLog.Calls.MISSED_TYPE:
                        dir = "MISSED";
                        break;
                }
                stringBuffer.append("\nPhone Number:--- " + phNumber + " \nCall Type:--- "
                        + dir + " \nCall Date:--- " + callDayTime
                        + " \nCall duration in sec :--- " + callDuration);
                i++;
            }
            cursor.close();
        }
        return stringBuffer.toString();
    }
}
