package com.salutation;

import javax.ejb.Singleton;

/**
 * Created by khayapro on 2016/07/04
 */
@Singleton
public class ApplicationStatistics {

    private int count;
    private long totalTime;

    public void incrementCount(){
        count++;
    }

    public void increaseTotalTime(long time){
        totalTime += time;
    }

    public int getCount() {
        return count;
    }

    public long getTotalTime() {
        return totalTime;
    }
}
