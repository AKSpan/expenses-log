package span.home.entities;

import java.io.Serializable;

public class RequestForAllExpAndInc implements Serializable{
    public static final String DAY = "DAY";
    public static final String PERIOD = "PERIOD";

    private byte type;
    private String da;
    private String db;

    public RequestForAllExpAndInc() {
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }


    public String getDa() {

        return da;
    }

    public void setDa(String da) {
        this.da = da;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
    public DateAndTime getDateAndTime()
    {
        return  DateAndTime.getDateForPeriod(da,db);
    }
}
