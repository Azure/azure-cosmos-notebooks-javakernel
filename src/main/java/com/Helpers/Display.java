package com.Helpers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Display {

	private static String Delimiter = "0f961030-74dc-11ea-bc55-0242ac130003";
	
	public static void asHtml(String htmlString) {
		System.out.print(Delimiter + "text/html:" + htmlString + Delimiter);
	}
	
	public static DisplayResult extractDisplayparameters(String streamOutput) {
		if (streamOutput.contains(Delimiter)) {
			DisplayResult displayResult = new DisplayResult();
			displayResult.setOriginalStreamOutput(streamOutput);
			
			ArrayList<String> displayParams = new ArrayList<String>();
			Matcher m = Pattern.compile(Delimiter + "(.*?)" + Delimiter).matcher(streamOutput);
			while (m.find()) {
				displayParams.add(m.group());
			}
			for (String displayParam : displayParams) {
				streamOutput = streamOutput.replace(displayParam, "");
				displayParam = displayParam.replace(Delimiter, "");
				String[] displayParamsArray = displayParam.split(":");
				displayResult.addDisplayPair(displayParamsArray[0], displayParamsArray[1]);
								
			}
			displayResult.setOriginalStreamOutput(streamOutput);
			
			return displayResult;
		}
		return null;
	}
}
