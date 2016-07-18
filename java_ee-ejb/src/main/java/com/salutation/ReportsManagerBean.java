package com.salutation;

import javax.ejb.Stateless;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by khayapro on 2016/07/18
 */
@Stateless
public class ReportsManagerBean {

//    @Timeout //@Schedule
    public String getMemoryReport(){
        final StringBuilder sb = new StringBuilder();
        GregorianCalendar gc = new GregorianCalendar();
        final Date date = gc.getTime();
        sb.append("Runtime memory: ").append(Runtime.getRuntime().totalMemory());
        sb.append("\n");
        sb.append(", Runtime max memory: ").append(Runtime.getRuntime().maxMemory());
        sb.append("\n");
        sb.append(", Runtime free memory: ").append(Runtime.getRuntime().freeMemory());
        return sb.toString();
    }
}
