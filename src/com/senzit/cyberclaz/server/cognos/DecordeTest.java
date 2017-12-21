package com.senzit.cyberclaz.server.cognos;
import java.io.UnsupportedEncodingException;    
import javax.xml.bind.DatatypeConverter;

public class DecordeTest {
	 public static void main(String[] args) throws UnsupportedEncodingException {

	        String str = "wat is the 5% of 350";
	        // encode data using BASE64
	        String encoded = DatatypeConverter.printBase64Binary(str.getBytes());
	        System.out.println("encoded value is \t" + encoded);
//
//	        // Decode data 
//	        String decoded = new String(DatatypeConverter.parseBase64Binary(encoded));
//	        System.out.println("decoded value is \t" + decoded);
//
//	        System.out.println("original value is \t" + str);

		// String str1 = DatatypeConverter.
				 //Decording.UTF8.GetString(encoded);
	    }
	

}
