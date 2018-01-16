package com.senzit.cyberclaz.server.subservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import com.senzit.cyberclaz.server.model.RebbonData;

public class RebbonService {
	
	
	/*public static boolean startRebbon(String rebbonLink){
		
		String postData = "origin=StartMediaServer";
		StringBuilder responseSB = new StringBuilder();

		URL url;
		try {
			url = new URL("http://192.168.10.50:8080/Rebbon/RebbonHandler");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length",  String.valueOf(postData.length()));
			
			// Write data
			OutputStream os = connection.getOutputStream();
			os.write(postData.getBytes());
			 
			// Read response
			responseSB = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ( (line = br.readLine()) != null)
				responseSB.append(line);
			   
			 // Close streams
			 br.close();
			 os.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(ProtocolException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			
//			if(responseSB==null)
//				return null;
//			else
//				return responseSB.toString();
		}

		String jsonString= responseSB.toString();
		int resultCode=0;
		try {
			JSONObject jsonData = new JSONObject(jsonString);
			jsonData=jsonData.getJSONObject("response");
			resultCode=jsonData.getInt("resultcode");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultCode==1)
			return true;
		return false;
		
	}*/

	public static String getMediaUrls(String rebbonLink,Boolean value,String device){
		
		String postData = "origin=StartRebbon&flag="+value;
		StringBuilder responseSB = new StringBuilder();

		URL url;
		try {
			url = new URL("http://192.168.10.50:8080/Rebbon/RebbonHandler");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length",  String.valueOf(postData.length()));
			
			// Write data
			OutputStream os = connection.getOutputStream();
			os.write(postData.getBytes());
			 
			// Read response
			responseSB = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ( (line = br.readLine()) != null)
				responseSB.append(line);
			   
			 // Close streams
			 br.close();
			 os.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(ProtocolException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			
//			if(responseSB==null)
//				return null;
//			else
//				return responseSB.toString();
		}

		return responseSB.toString();

	}
	
	
	
	
	
	
	public static String getMediaUrlsRTSP(String rebbonLink,Boolean value,String device){
		
		String postData = "origin=getRTSP&flag="+value;
		StringBuilder responseSB = new StringBuilder();

		URL url;
		try {
			url = new URL("http://192.168.10.50:8080/Rebbon/RebbonHandler");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length",  String.valueOf(postData.length()));
			
			// Write data
			OutputStream os = connection.getOutputStream();
			os.write(postData.getBytes());
			 
			// Read response
			responseSB = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ( (line = br.readLine()) != null)
				responseSB.append(line);
			   
			 // Close streams
			 br.close();
			 os.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(ProtocolException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			
//			if(responseSB==null)
//				return null;
//			else
//				return responseSB.toString();
		}

		return responseSB.toString();

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Properties getMediaLinkFromJson(JSONObject jsonData){
		
		Properties cameras=new Properties();
		try {
			JSONObject camObj=jsonData.getJSONObject("response");
			camObj=camObj.getJSONObject("liveCamDetails");
//			
			Hashtable<String,Object> hash1=new Hashtable<String,Object>();
			JSONObject obj1=camObj.getJSONObject("CAM1");
			String aud1=obj1.getString("Aud");
			String url1=obj1.getString("Url");
			hash1.put("Aud", aud1);
			hash1.put("Url", url1);
			cameras.put("CAM1", hash1);
//			
			Hashtable<String,Object> hash2=new Hashtable<String,Object>();
			JSONObject obj2=camObj.getJSONObject("CAM2");
			String aud2=obj2.getString("Aud");
			String url2=obj2.getString("Url");
			hash2.put("Aud", aud2);
			hash2.put("Url", url2);
			cameras.put("CAM2", hash2);
//			
			Hashtable<String,Object> hash3=new Hashtable<String,Object>();
			JSONObject obj3=camObj.getJSONObject("CAM3");
			String aud3=obj3.getString("Aud");
			String url3=obj3.getString("Url");
			hash3.put("Aud", aud3);
			hash3.put("Url", url3);
			cameras.put("CAM3", hash3);
//	        Hashtable<String,Object> hash4=new Hashtable<String,Object>();
//			hash4.put("Url", "http://192.168.10.50:8080/Rebbon/presantationVideo.mp4");
//			hash4.put("Aud", "");
//			cameras.put("CAM4", hash4);
//			
			
			Hashtable<String,Object> hash4=new Hashtable<String,Object>();
			JSONObject obj4=camObj.getJSONObject("CAM4");
			String aud4=obj4.getString("Aud");
			String url4=obj4.getString("Url");
			hash4.put("Url", url4);
			hash4.put("Aud", aud4);
			cameras.put("CAM4", hash4);

			
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		return cameras;
	}
	
	
	
	
	
	
	
	
	
	
	public static Properties getMediaLinkFromJsonRTSP(JSONObject jsonData){
		
		Properties cameras=new Properties();
		try {
			JSONObject camObj=jsonData.getJSONObject("response");
			camObj=camObj.getJSONObject("liveRTSPCamDetails");
//			
			Hashtable<String,Object> hash1=new Hashtable<String,Object>();
			JSONObject obj1=camObj.getJSONObject("CAM1");
			String aud1=obj1.getString("Aud");
			String url1=obj1.getString("Url");
			hash1.put("Aud", aud1);
			hash1.put("Url", url1);
			cameras.put("CAM1", hash1);
//			
			Hashtable<String,Object> hash2=new Hashtable<String,Object>();
			JSONObject obj2=camObj.getJSONObject("CAM2");
			String aud2=obj2.getString("Aud");
			String url2=obj2.getString("Url");
			hash2.put("Aud", aud2);
			hash2.put("Url", url2);
			cameras.put("CAM2", hash2);
//			
			Hashtable<String,Object> hash3=new Hashtable<String,Object>();
			JSONObject obj3=camObj.getJSONObject("CAM3");
			String aud3=obj3.getString("Aud");
			String url3=obj3.getString("Url");
			hash3.put("Aud", aud3);
			hash3.put("Url", url3);
			cameras.put("CAM3", hash3);
			
			
			Hashtable<String,Object> hash4=new Hashtable<String,Object>();
			JSONObject obj4=camObj.getJSONObject("CAM4");
			String aud4=obj4.getString("Aud");
			String url4=obj4.getString("Url");
			hash4.put("Url", url4);
			hash4.put("Aud", aud4);
			cameras.put("CAM4", hash4);

			
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		return cameras;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean stopRebbon(String rebbonLink,int classEventDetailId){
		
		String postData = "origin=StopClazRebbon&classEventId="+classEventDetailId+"&jsonImage=[]";
		StringBuilder responseSB = new StringBuilder();

		URL url;
		try {
			url = new URL("http://192.168.10.50:8080/Rebbon/RebbonHandler");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length",  String.valueOf(postData.length()));
			System.out.println("SSSSSSSSSSSSSSSSSS"+postData);
			// Write data
			OutputStream os = connection.getOutputStream();
			os.write(postData.getBytes());
						 
			// Read response
			responseSB = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ( (line = br.readLine()) != null)
				responseSB.append(line);
			   
			 // Close streams
			 br.close();
			 os.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(ProtocolException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			
//			if(responseSB==null)
//				return null;
//			else
//				return responseSB.toString();
		}

		String jsonString= responseSB.toString();
		
		//////////////////////////////////////////
		
		System.out.println("REbbon Stop Response");
		System.out.println(jsonString);
		
		/////////////////////////////////
		
		
		int resultCode=0;
		try {
			JSONObject jsonData = new JSONObject(jsonString);
			jsonData=jsonData.getJSONObject("response");
			resultCode=jsonData.getInt("resultcode");		
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultCode==1)
			return true;
		return false;
	}
	public static String getAvFilesFromRebbon(String rebbonLink,int classEventDetailId,String userId) throws IOException {
		
		String postData = "origin=GetClazAvFiles&classEventId="+classEventDetailId+"&userName"+userId;
		StringBuilder responseSB = new StringBuilder();
		URL url;
		
		try {
			url = new URL("http://192.168.10.50:8080/Rebbon/RebbonHandler");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length",  String.valueOf(postData.length()));
			
			// Write data
			OutputStream os = connection.getOutputStream();
			os.write(postData.getBytes());
			
			// Read response
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		     
		    String line;
		    while ( (line = br.readLine()) != null)
		    	responseSB.append(line);
					
			// Close streams
			br.close();
			os.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return responseSB.toString();
	}
	
	public static void storeRebbonLink(int batchId,String rebbonLinkJson){
		
		RebbonData obj=new RebbonData();
		obj.putNewData(batchId, rebbonLinkJson);
	}
	public static Properties getRebbonLink(int batchId){
		
		RebbonData obj=new RebbonData();
		String rebbonLinkJson=obj.getRebbonLink(batchId);
		Properties prop=new Properties();
		Properties nulllist =new Properties();
		try {
			System.out.println("rebbonlinkjson"+rebbonLinkJson);
			if(!(rebbonLinkJson.isEmpty()))
			{	
			    JSONObject jsonData = new JSONObject(rebbonLinkJson);
				prop=RebbonService.getMediaLinkFromJson(jsonData);
			}
			else
			{
			
				prop=nulllist;
				System.out.println("no class session");
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	public void removeData(int CourseBatchId)
	{
		RebbonData.remove(CourseBatchId);
	
	}


}
