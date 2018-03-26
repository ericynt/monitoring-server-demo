package com.eric.monitoringserverjava.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 */
public class DateUtil {
    public static Date toDate (LocalDateTime ldt) {
        return Date.from(ldt.atZone(ZoneId.systemDefault())
                            .toInstant());
    }

    public static LocalDateTime toLocalDateTime (Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
