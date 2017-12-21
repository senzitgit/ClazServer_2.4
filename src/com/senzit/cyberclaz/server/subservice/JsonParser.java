package com.senzit.cyberclaz.server.subservice;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;


public class JsonParser<ResultforClientKey, ResultforClientValue> {
	
	ResultforClientKey   resultKey; 
	ResultforClientValue resultValue;
	JsonResponseFormatter<ResultforClientKey, ResultforClientValue> jsonResponse;

	public JsonParser(String origin, int resultcode, String message,String SessionID, ResultforClientKey resultKey , ResultforClientValue resultValue)
	{
		this.resultKey = resultKey;
		this.resultValue = resultValue;
		jsonResponse = new JsonResponseFormatter<ResultforClientKey, ResultforClientValue>(origin, resultcode, message, resultKey,SessionID, resultValue);
	}
	
	public String JsonResponseText() 
	{
		
	   //create ObjectMapper instance
	   ObjectMapper objectMapper = new ObjectMapper();
	
	   //configure Object mapper for pretty print
	   objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	
	   //writing to console, can write to any output stream such as file
	   StringWriter jsonStringWriter = new StringWriter();
	  
	   try
		{
			objectMapper.writeValue(jsonStringWriter, jsonResponse);
	   }
		catch (JsonGenerationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		return jsonStringWriter.toString();
	}
	
	public String JsonResponseTextNew() throws JSONException 
	{
		JSONObject jOb= new JSONObject();
		jOb.put("origin", jsonResponse.origin);
		jOb.append("response", jsonResponse.response);
		//jOb.put("response", jsonResponse.response);
//		Properties prop = new Properties();
//		prop.put("origin", jsonResponse.origin);
//		prop.put("response", jsonResponse.response);
//		
		System.out.println("Response:"+jOb.toString());
		
		return jOb.toString();
	}

}
