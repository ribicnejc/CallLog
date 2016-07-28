package com.nejc.povio.calllog;

import java.io.Serializable;

public class Data implements Serializable {
    public int icon;
    public String call;
    public String phoneNumber;
    public String date;
    public String duration;

    Data(int icon, String call, String phoneNumber, String date, String duration){
        this.icon = icon;
        this.call = call;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.duration = duration;
    }
}
