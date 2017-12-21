package com.senzit.cyberclaz.server.model;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CyberInit implements ServletContextListener{
	
	public static Properties cyberProperty;
	public static String webAppFolder;
	public static String ipAddress;
	public static String profilePicPath;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		///////////////////////////////////////////////////////////////////////////////
		String propertyFilePath=(String)arg0.getServletContext().getInitParameter("propertyFilePath");
		cyberProperty=new Properties();
		try {
			InputStream input = new FileInputStream(propertyFilePath);
			cyberProperty.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		///////////////////////////////////////////////////////////////////////////////
		char separator=File.separatorChar;
		String realPath=arg0.getServletContext().getRealPath("");
        realPath=realPath.substring(0, realPath.lastIndexOf(separator));        
        realPath=realPath.substring(0, realPath.lastIndexOf(separator));        
        realPath=realPath+separator+"webapps";        
        webAppFolder=realPath;  
        ///////////////////////////////////////////////////////////////////////////////
        ipAddress="http://"+cyberProperty.getProperty("myIp")+":"+cyberProperty.getProperty("portNumber");
        
        
        profilePicPath = cyberProperty.getProperty("profilePicPath");
        
        System.out.println(cyberProperty);
        System.out.println(webAppFolder);
        System.out.println(ipAddress);
        System.out.println(propertyFilePath);
//        int a=5/0;
                
	}
	
}
