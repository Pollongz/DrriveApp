package com.example.drrive;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

    public static String getCurrentDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("EEEE, dd MMMM yyyy");//Monday, 01 January 1970
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate.substring(0, 1).toUpperCase() + strDate.substring(1);
    }
}
