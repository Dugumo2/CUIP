package com.epoch.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommUtils {
    public static String getNowDateLongStr(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }
}

