package aso.asoroomvtraditionaltrad;

public class LogTime {

    public static final void logIt(String stageinfo, ChronicleDBHelper chronicleDBHelper, long runnumber) {

        chronicleDBHelper.insertChronicleEntry(stageinfo,runnumber);
    }
}
