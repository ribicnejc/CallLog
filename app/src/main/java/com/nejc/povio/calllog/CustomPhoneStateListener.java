package com.nejc.povio.calllog;

import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CustomPhoneStateListener extends PhoneStateListener {

    Context context;
    public CustomPhoneStateListener(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:

                Toast.makeText(context, "Phone call has ended", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "Starting CallLog", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, AddCallActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:

                Toast.makeText(context, "Phone call has taken", Toast.LENGTH_SHORT).show();
                break;
            case TelephonyManager.CALL_STATE_RINGING:

                Toast.makeText(context, "Phone is ringing", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
