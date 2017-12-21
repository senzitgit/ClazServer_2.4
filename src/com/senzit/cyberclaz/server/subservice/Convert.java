package com.senzit.cyberclaz.server.subservice;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;


public class Convert {
	
	public static void main(String args[]){
		
		String imageString=convertImageToString();
		System.out.println("Input Image String : "+imageString);
		convertStringToImageByteArray(imageString);
	}
	
	public static void convertStringToImageByteArray(String 
            imageString){
        
        OutputStream outputStream = null;
        byte [] imageInByteArray = Base64.decodeBase64(
                imageString);
        try {
            outputStream = new FileOutputStream("C:\\CyberImg\\imagedest.png");
            outputStream.write(imageInByteArray);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
	public static String convertImageToString(){
        
        InputStream inputStream = null;
        
        String imageString = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            inputStream = new FileInputStream("C:\\CyberImg\\imagedest.png");

            byte[] buffer = new byte[1024];
            baos = new ByteArrayOutputStream();

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = baos.toByteArray();

            imageString = Base64.encodeBase64String(imageBytes);
            
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                baos.close();
                inputStream.close();                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        return imageString;
    }

}
