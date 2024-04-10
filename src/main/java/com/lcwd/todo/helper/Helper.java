package com.lcwd.todo.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class Helper {
    public static Date parseDate(LocalDateTime localDateTime) throws ParseException {
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        //date formatting
        Date date =Date.from(instant);
        return date;
    }
}
