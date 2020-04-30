package com.DataModels;

public enum StreamName {
	StandardIn("stdin"),
	StandardOut("stdout"),
	StandardError("stderr");
    
    private final String name;
    
    private StreamName (String name) {
    	this.name = name;
    }
    
    public String toString() {
    	return this.name;
    }
}
