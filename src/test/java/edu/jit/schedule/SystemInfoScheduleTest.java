package edu.jit.schedule;

import org.junit.Test;

import static org.junit.Assert.*;

public class SystemInfoScheduleTest {

    @Test
    public void readSystemInfo() {
        SystemInfoSchedule sis = new SystemInfoSchedule();
        try {
            sis.readSystemInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loadChartInfo(){
        SystemInfoSchedule sis=new SystemInfoSchedule();
        sis.loadChartInfo();
    }

    @Test
    public void runAppState(){
        SystemInfoSchedule sis=new SystemInfoSchedule();
        sis.runAppState();
    }

    @Test
    public void updateDbState(){
        SystemInfoSchedule sis=new SystemInfoSchedule();
        try {
            sis.updateDbState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateTableCount(){
        SystemInfoSchedule sis=new SystemInfoSchedule();
        try {
            sis.updateTableCount();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void updateServiceStatus(){
        SystemInfoSchedule sis=new SystemInfoSchedule();
        try {
            sis.updateServiceStatus();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}