package com.senzit.cyberclaz.server.subservice;

import java.util.ArrayList;
import java.util.List;


public class CyberProperty {
	
	public static List<String> getFileLinks(String folderLink){
		
		
		ArrayList<String> list=new ArrayList<String>();
		
		list.add("IBM");
		list.add("http://192.168.0.109:8080/ATTACHMENT/ibm.pdf");
		
		list.add("JAVA");
		list.add("http://192.168.0.109:8080/ATTACHMENT/java.pdf");
		
		list.add("IT");
		list.add("http://192.168.0.109:8080/ATTACHMENT/it.pdf");
		
		list.add("PLSQL");
		list.add("http://192.168.0.109:8080/ATTACHMENT/plsql.pdf");
		
		list.add("CIVIL");
		list.add("http://192.168.0.109:8080/ATTACHMENT/civil.pdf");
		
//		list.add("MVC Design");
//		list.add("https://drive.google.com/file/d/0B3nDjOuIVjFHQVdIdTNqQWtKNEk/edit?usp=sharing");
//		
//		list.add("plsql performance");
//		list.add("https://drive.google.com/file/d/0B3nDjOuIVjFHc2JtVjNzbXJtMlU/edit?usp=sharing");
//		
//		list.add("Java");
//		list.add("https://drive.google.com/file/d/0B3nDjOuIVjFHVThfamFEcTNYTzQ/edit?usp=sharing");
//		
//		list.add("The Websphere");
//		list.add("https://drive.google.com/file/d/0B3nDjOuIVjFHZ0lJcGtnbDktaEE/edit?usp=sharing");
//		
		
		
//		File folder=new File(folderLink);
//			
//		
//		File[] listOfFiles = folder.listFiles();
//		int len=listOfFiles.length;
//
//	    for (int i = 0; i < len; i++) {
//	      if (listOfFiles[i].isFile()) {
//	    	  String fileName=listOfFiles[i].getName();
//	    	  String link="http://192.168.1.82:8080/CyberAttachment/2"+"/"+fileName;
//	    	  list.add(fileName);
//	    	  list.add(link);
//	      } else if (listOfFiles[i].isDirectory()) {
//	      }
//	    }
		return list;
	}
	
}
