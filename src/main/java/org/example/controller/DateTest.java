package org.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTest {

    public static void main(String[] args) throws ParseException {

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dateResult = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        String[] dateInterval = {"2022-10-11", "2022-10-30"};
        Date[] dates = new Date[dateInterval.length];
        for (int i = 0; i < dateInterval.length; i++) {
            String[] ymd = dateInterval[i].split("[^\\d]+");
            cal.set(Integer.parseInt(ymd[0]), Integer.parseInt(ymd[1]) - 1, Integer.parseInt(ymd[2]));
            dates[i] = cal.getTime();
        }
        for (Date date = dates[0]; date.compareTo(dates[1]) <= 0; ) {
            cal.setTime(date);
            if (cal.get(Calendar.DAY_OF_WEEK) - 1 == 2) {
                String format = sdf.format(date);
                dateResult.add(format);
            }
            cal.add(Calendar.DATE, 1);
            date = cal.getTime();
        }
        System.out.println(dateResult);*/

        List<Date> list = new ArrayList<>();
        SimpleDateFormat formatter=new SimpleDateFormat("HH:mm");

        list.add(formatter.parse("11:12"));
        list.add(formatter.parse("11:10"));
        list.add(formatter.parse("11:15"));
        Collections.sort(list);
        System.out.println("====" +list);

    }
}
