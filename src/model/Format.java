package model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author felipe.ferreira
 */
public class Format {

    //CONVERT DATE SQL
    public static java.sql.Date convertDataSql(Date date) throws ParseException {
        return new java.sql.Date(date.getTime());
    }

    //CONVERT DATETIME SQL
    public static Timestamp convertDataTimeSql(Date date) throws ParseException {
        return new Timestamp(date.getTime());
    }

    //CONVERT LOCALDATETIME SQL
    public static Timestamp convertLocalDataTimeSql(LocalDateTime ldt) throws ParseException {
        DateTimeFormatter dtf = 
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Timestamp ts = Timestamp.valueOf(ldt);
        return ts;
    }
}
