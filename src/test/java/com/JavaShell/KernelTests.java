package com.JavaShell;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.websocket.DecodeException;
import javax.websocket.EncodeException;

import com.DataModels.ExecuteRequestMessage;
import com.DataModels.MessageDecoder;
import com.DataModels.MessageEncoder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.core.JsonParser;

import jdk.jshell.JShellException;
import com.Helpers.Display;
public class KernelTests {

	public static void main(String args[]) throws DecodeException, EncodeException, IOException
	{
		Constants.MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		Constants.MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		
		
		String kernelId = UUID.randomUUID().toString();
	    JavaKernel.createJShell(kernelId);	

	    /*
		JShellResult result2 = JavaKernel.getResult(kernelId, "import com.fasterxml.jackson.databind.ObjectMapper;\r\n" + 
				"import com.fasterxml.jackson.databind.JsonNode; \r\n" + 
				"import com.fasterxml.jackson.core.JsonParser;\r\n" + 
				" \r\n" + 
				"ObjectMapper MAPPER = new ObjectMapper();  	\r\n" + 
				"MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);\r\n" + 
				"\r\n" + 
				"JsonNode a = MAPPER.readTree(\"{'field': 'value'}\"); \r\n" + 
				"String s = MAPPER.writeValueAsString(a);\r\n" + 
				"System.out.println(s + \"1\");");
				*/
		JShellResult result2 = JavaKernel.getResult(kernelId, "import com.fasterxml.jackson.databind.ObjectMapper;\r\n" + 
				"import com.fasterxml.jackson.databind.JsonNode;\r\n" + 
				"import com.fasterxml.jackson.core.JsonParser;\r\n" + 
				"import com.Helpers.Constants; \r\n" + 
				"ObjectMapper MAPPER = Constants.getMapper();\r\n" + 
				"String s = MAPPER.toString();\r\n" + 
				"s;");
		System.out.println("\nstream:" + result2.getStreamOutput());
		System.out.println("error:" + result2.getError());		
		System.out.println("output: " + result2.getResult());
		
	}
}
