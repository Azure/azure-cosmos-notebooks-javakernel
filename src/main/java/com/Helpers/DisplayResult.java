package com.Helpers;

import java.util.ArrayList;

public class DisplayResult {

	private ArrayList<String> mimeFormats;
	private ArrayList<String> displayStrings;
	private String originalStreamOutput;
	
	public DisplayResult() {
		this.mimeFormats = new ArrayList<String>();
		this.displayStrings = new ArrayList<String>();
	}
	
	public void addDisplayPair(String mimeFormat, String displayString) {
		this.mimeFormats.add(mimeFormat);
		this.displayStrings.add(displayString);
	}
	
	public ArrayList<String> getMimeFormats() {
		return this.mimeFormats;
	}
	
	public ArrayList<String> getDisplayStrings() {
		return this.displayStrings;
	}
	
	public void setOriginalStreamOutput(String originalStreamOutput) {
		this.originalStreamOutput = originalStreamOutput;
	}
	
	public String getOriginalStreamOutput() {
		return this.originalStreamOutput;
	}	
}
