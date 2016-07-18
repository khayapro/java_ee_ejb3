package com.salutation;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by khayapro on 2016/07/18
 */
@Stateless
public class ReportsManagerBean {

    public String getMemoryReport(){
        final StringBuilder sb = new StringBuilder();
        GregorianCalendar gc = new GregorianCalendar();
        final Date date = gc.getTime();
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        System.out.println(df.format(date));
        sb.append("Runtime memory: ").append(Runtime.getRuntime().totalMemory());
        sb.append("\n");
        sb.append(", Runtime max memory: ").append(Runtime.getRuntime().maxMemory());
        sb.append("\n");
        sb.append(", Runtime free memory: ").append(Runtime.getRuntime().freeMemory());
        return sb.toString();
    }

    /**
     * Declarative timer - and scheduling the timer on a callback method.
     * @param timer
     */
    @Schedule()
    public void getMemoryReport(Timer timer){
        System.out.println("ReportsManagerBean - getMemoryReport timer fired");
        System.out.println(getMemoryReport());
    }
}
