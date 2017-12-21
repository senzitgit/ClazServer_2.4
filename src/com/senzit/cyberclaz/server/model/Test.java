package com.senzit.cyberclaz.server.model;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import org.apache.commons.codec.binary.Base64;
import java.util.Locale;

import com.senzit.cyberclaz.server.subservice.Convert;

public class Test {
	
	public static void main(String[] args){
		
	
		byte[] decoded = Base64.decodeBase64("c2Vueml0");
		try {
			System.out.println(new String(decoded, "UTF-8") + "\n");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
//		Calendar c = Calendar.getInstance();
////		int month = c.get(Calendar.MONTH);
////		System.out.println("MONTH"+month);
//		String month= c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH );
//        System.out.println(month);
////		String month1 = null;
////		 switch(month){
//		 case 1: month1= "Sun";
//		         if()
//		         break;
//		 case 2 : month1= "Mon";
//		          break;
//		 case 3 : month1= "Tue";
//                  break;
//		 case 4 : month1= "Wed";
//                  break;   
//		 case 5 : month1= "Thu";
//                  break;
//		 case 6 : month1= "Fri";
//                  break;    
//		 case 7 : month1= "Sat";
//                  break;           
//		              }
		
//		if(month=="May" && month=="June" && month=="August" )
//		{
//			String term="first";
//		 System.out.println("dayIdDDDDDDDDDDDDDDDDDDDDDDDDD"+term);
//		}
	}

	
}
