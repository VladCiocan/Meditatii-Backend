package Utils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by dioni on 2/13/2017.
 */
public class ConnectionController {
    public static Logger logger = LoggerFactory.getLogger(ConnectionController.class);
    public static Connection conn = (Connection) DBConnection.getConnection();
    public static PreparedStatement pst;
public static String sqlTime(){
    String time=new String();
    DateTime dateTime=new DateTime(getCurent().getTime());

    time=dateTime.getDayOfMonth()+"-"+dateTime.getMonthOfYear()+"-"+dateTime.getYear()+"-"+dateTime.getHourOfDay()+"-"+(dateTime.getMinuteOfDay()-30);
    return time;
}
    public static Timestamp getCurent(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }
    public static Timestamp endSessionTime(Timestamp t, int duration){
        Timestamp endT = null;
        endT.setTime(t.getTime()+(duration*60000));
        return endT;
    }
    public static int GetInt(String x){
        return Integer.parseInt(x);
    }
    public static Timestamp getFromString(String time){
        //format dd-mm-yyyy-hour-minute

        GregorianCalendar cal=new GregorianCalendar();
        cal.set(GetInt(time.split("-")[2]),GetInt(time.split("-")[1])-1,GetInt(time.split("-")[0]),GetInt(time.split("-")[3]),GetInt(time.split("-")[4])+30);
        Timestamp t=new Timestamp(cal.getTimeInMillis());

        return t;
    }
    public static String getFromTimestamp(Timestamp t){
        String time=new String();
        DateTime dateTime=new DateTime(t.getTime());
        time=dateTime.getDayOfMonth()+"-"+dateTime.getMonthOfYear()+"-"+dateTime.getYear()+"-"+dateTime.getHourOfDay()+"-"+dateTime.getMinuteOfDay();
        return time;
    }



}
