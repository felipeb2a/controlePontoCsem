package model;

import java.text.ParseException;
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
}
