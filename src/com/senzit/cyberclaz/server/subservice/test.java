package com.senzit.cyberclaz.server.subservice;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class test {

	public static void main(String[] args) {
		
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("WebContent/WEB-INF/cyberclaz.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("profilePicPath"));
			

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
	}

}
