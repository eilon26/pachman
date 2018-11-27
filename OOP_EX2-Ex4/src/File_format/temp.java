package File_format;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class temp {
public static void main(String[] args) throws ParseException
{
    String sd = "Thu Aug 14 16:45:37 UTC 2011";


    String dp = "EEE MMM dd HH:mm:ss zzz yyyy";

    final SimpleDateFormat sdf = new SimpleDateFormat(dp,Locale.ENGLISH);

    java.util.Date d = null;
	try {
		d = sdf.parse(sd);
	} catch (java.text.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//03/12/2017 08:53
	
	
    long y = Date.UTC(117,0,3,8,53,0);
    System.out.println(y);
    System.out.println(Date.parse("03/12/2017 08:53"));
    
    
}
}


//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//	     final Date date = string_to_date("EEE MMM dd HH:mm:ss zzz yyyy",
//	                "Thu Aug 14 16:45:37 UTC 2011");
//	     System.out.println(date);
//	}
//	private static Date string_to_date(final String date_format,
//            final String textual_date)
//    {
//        Date ret = null;
//
//        final SimpleDateFormat date_formatter = new SimpleDateFormat(
//                date_format,Locale.ENGLISH);
//        try
//        {
//            ret = (Date) date_formatter.parse(textual_date);
//        }
//        catch (ParseException e)
//        {
//            e.printStackTrace();
//        } catch (java.text.ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        
//    }
//}
