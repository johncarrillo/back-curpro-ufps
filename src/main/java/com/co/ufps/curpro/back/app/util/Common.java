package com.co.ufps.curpro.back.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Common {

	public static String dateToString (Date fecha) {
		if (fecha == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
		return formatter.format(fecha);
	}

	public static Date stringToFecha (String fecha) {
		if ("".equals(fecha) || fecha == null) {
			return null;
		}
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-dd-MM");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) {
            System.out.println(ex);
        }
        return fechaDate;
    }
}
